package cn.iocoder.yudao.module.mes.service.operation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.operation.vo.*;
import cn.iocoder.yudao.module.mes.dal.dataobject.routing.MesOperationDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.operation.MesKeyPartBindDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.operation.MesOperationRecordDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.workorder.MesWorkOrderDO;
import cn.iocoder.yudao.module.mes.dal.mysql.operation.MesKeyPartBindMapper;
import cn.iocoder.yudao.module.mes.dal.mysql.operation.MesOperationRecordMapper;
import cn.iocoder.yudao.module.mes.dal.mysql.routing.MesOperationMapper;
import cn.iocoder.yudao.module.mes.dal.mysql.workorder.MesWorkOrderMapper;
import cn.iocoder.yudao.module.mes.enums.OperationStatusEnum;
import cn.iocoder.yudao.module.mes.enums.ScanTypeEnum;
import cn.iocoder.yudao.module.mes.enums.WorkOrderStatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.*;

@Service
@Validated
public class MesOperationServiceImpl implements MesOperationService {

    @Resource
    private MesOperationRecordMapper operationRecordMapper;

    @Resource
    private MesKeyPartBindMapper keyPartBindMapper;

    @Resource
    private MesWorkOrderMapper workOrderMapper;

    @Resource
    private MesOperationMapper operationMapper;

    @Override
    public MesScanRespVO scan(MesScanReqVO reqVO) {
        String code = reqVO.getCode();
        ScanTypeEnum scanType = ScanTypeEnum.parse(code);
        MesScanRespVO respVO = new MesScanRespVO();
        respVO.setScanType(scanType.getType());
        respVO.setScanTypeName(scanType.getName());

        switch (scanType) {
            case VIN:
                respVO.setVinInfo(handleVinScan(code, reqVO.getWorkOrderId()));
                respVO.setMessage("请选择工序开始作业");
                break;
            case WORK_ORDER:
                respVO.setWorkOrderInfo(handleWorkOrderScan(code));
                respVO.setMessage("工单信息已加载");
                break;
            case MATERIAL:
                respVO.setMaterialInfo(handleMaterialScan(code));
                respVO.setMessage("物料信息已识别");
                break;
        }
        return respVO;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long startOperation(MesOperationStartReqVO reqVO) {
        MesWorkOrderDO workOrder = workOrderMapper.selectById(reqVO.getWorkOrderId());
        if (workOrder == null) {
            throw exception(WORK_ORDER_NOT_EXISTS);
        }
        if (!WorkOrderStatusEnum.IN_PROGRESS.getStatus().equals(workOrder.getStatus())) {
            throw exception(OPERATION_WORK_ORDER_NOT_PRODUCING);
        }
        if (!isValidVin(reqVO.getVin())) {
            throw exception(SCAN_VIN_FORMAT_ERROR);
        }
        MesOperationRecordDO existRecord = operationRecordMapper.selectByVinAndOperationId(
                reqVO.getVin(), reqVO.getOperationId());
        if (existRecord != null) {
            throw exception(OPERATION_RECORD_DUPLICATE);
        }

        MesOperationRecordDO record = new MesOperationRecordDO();
        record.setWorkOrderId(reqVO.getWorkOrderId());
        record.setWorkOrderNo(workOrder.getOrderNo());
        record.setVin(reqVO.getVin());
        record.setOperationId(reqVO.getOperationId());
        record.setOperationCode("OP" + reqVO.getOperationId());
        record.setOperationName("工序" + reqVO.getOperationId());
        record.setOperationSeq(1);
        record.setWorkstationId(reqVO.getWorkstationId());
        record.setStartTime(LocalDateTime.now());
        record.setStatus(OperationStatusEnum.IN_PROGRESS.getStatus());

        operationRecordMapper.insert(record);
        return record.getId();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void completeOperation(MesOperationCompleteReqVO reqVO) {
        MesOperationRecordDO record = operationRecordMapper.selectById(reqVO.getRecordId());
        if (record == null) {
            throw exception(OPERATION_RECORD_NOT_EXISTS);
        }
        if (!OperationStatusEnum.IN_PROGRESS.getStatus().equals(record.getStatus())) {
            throw exception(OPERATION_RECORD_ALREADY_COMPLETED);
        }

        LocalDateTime endTime = LocalDateTime.now();
        MesOperationRecordDO updateObj = new MesOperationRecordDO();
        updateObj.setId(reqVO.getRecordId());
        updateObj.setEndTime(endTime);
        updateObj.setDuration((int) Duration.between(record.getStartTime(), endTime).getSeconds());
        updateObj.setStatus(OperationStatusEnum.COMPLETED.getStatus());
        updateObj.setResult(reqVO.getResult());
        updateObj.setTorqueValue(reqVO.getTorqueValue());
        updateObj.setTorqueResult(reqVO.getTorqueResult());
        updateObj.setRemark(reqVO.getRemark());
        operationRecordMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long bindKeyPart(MesKeyPartBindReqVO reqVO) {
        if (keyPartBindMapper.isPartSnBind(reqVO.getPartSn())) {
            throw exception(KEY_PART_ALREADY_BIND);
        }
        MesKeyPartBindDO bind = BeanUtils.toBean(reqVO, MesKeyPartBindDO.class);
        bind.setBindTime(LocalDateTime.now());
        keyPartBindMapper.insert(bind);
        return bind.getId();
    }

    @Override
    public PageResult<MesOperationRecordDO> getOperationRecordPage(MesOperationRecordPageReqVO pageReqVO) {
        return operationRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public MesOperationRecordDO getOperationRecord(Long id) {
        return operationRecordMapper.selectById(id);
    }


    @Override
    public MesVehicleProgressRespVO getVehicleProgress(String vin) {
        MesVehicleProgressRespVO respVO = new MesVehicleProgressRespVO();
        respVO.setVin(vin);
        List<MesOperationRecordDO> records = operationRecordMapper.selectListByVin(vin);

        int completedCount = 0;
        List<MesVehicleProgressRespVO.OperationProgressVO> operations = new ArrayList<>();

        for (MesOperationRecordDO record : records) {
            MesVehicleProgressRespVO.OperationProgressVO progress = new MesVehicleProgressRespVO.OperationProgressVO();
            progress.setOperationId(record.getOperationId());
            progress.setOperationCode(record.getOperationCode());
            progress.setOperationName(record.getOperationName());
            progress.setOperationSeq(record.getOperationSeq());
            progress.setRecordId(record.getId());
            progress.setStatus(record.getStatus());
            progress.setStatusName(OperationStatusEnum.getNameByStatus(record.getStatus()));
            progress.setCompleted(OperationStatusEnum.COMPLETED.getStatus().equals(record.getStatus()));
            operations.add(progress);
            if (progress.getCompleted()) {
                completedCount++;
            }
        }

        respVO.setOperations(operations);
        respVO.setCompletedCount(completedCount);
        respVO.setTotalCount(operations.size());
        if (operations.size() > 0) {
            respVO.setProgressPercent((completedCount * 100.0) / operations.size());
        }
        return respVO;
    }

    @Override
    public List<MesKeyPartBindDO> getKeyPartBindListByVin(String vin) {
        return keyPartBindMapper.selectListByVin(vin);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reportException(MesExceptionReportReqVO reqVO) {
        // 1. 校验异常原因不能为空
        if (reqVO.getExceptionReason() == null || reqVO.getExceptionReason().trim().isEmpty()) {
            throw exception(EXCEPTION_REASON_REQUIRED);
        }

        // 2. 创建异常作业记录
        MesOperationRecordDO record = new MesOperationRecordDO();
        record.setVin(reqVO.getVin());
        record.setWorkOrderId(reqVO.getWorkOrderId());
        record.setOperationId(reqVO.getOperationId());
        record.setWorkstationId(reqVO.getWorkstationId());
        record.setStatus(OperationStatusEnum.ABNORMAL.getStatus());
        record.setStartTime(LocalDateTime.now());
        record.setRemark("异常上报: " + reqVO.getExceptionReason() +
                (reqVO.getExceptionDesc() != null ? " - " + reqVO.getExceptionDesc() : ""));

        operationRecordMapper.insert(record);
    }


    private MesScanRespVO.VinInfo handleVinScan(String vin, Long workOrderId) {
        MesScanRespVO.VinInfo vinInfo = new MesScanRespVO.VinInfo();
        vinInfo.setVin(vin);
        List<MesOperationRecordDO> records = operationRecordMapper.selectListByVin(vin);

        List<MesScanRespVO.OperationInfo> operations = new ArrayList<>();
        for (MesOperationRecordDO record : records) {
            MesScanRespVO.OperationInfo opInfo = new MesScanRespVO.OperationInfo();
            opInfo.setOperationId(record.getOperationId());
            opInfo.setOperationCode(record.getOperationCode());
            opInfo.setOperationName(record.getOperationName());
            opInfo.setOperationSeq(record.getOperationSeq());
            opInfo.setRecordId(record.getId());
            opInfo.setStatus(record.getStatus());
            opInfo.setCompleted(OperationStatusEnum.COMPLETED.getStatus().equals(record.getStatus()));
            operations.add(opInfo);
        }
        vinInfo.setOperations(operations);
        return vinInfo;
    }

    private MesScanRespVO.WorkOrderInfo handleWorkOrderScan(String code) {
        MesWorkOrderDO workOrder = workOrderMapper.selectByOrderNo(code);
        if (workOrder == null) {
            throw exception(SCAN_WORK_ORDER_NOT_FOUND);
        }
        MesScanRespVO.WorkOrderInfo info = new MesScanRespVO.WorkOrderInfo();
        info.setId(workOrder.getId());
        info.setOrderNo(workOrder.getOrderNo());
        info.setProductName(workOrder.getProductName());
        info.setStatus(workOrder.getStatus());
        WorkOrderStatusEnum statusEnum = WorkOrderStatusEnum.getByStatus(workOrder.getStatus());
        info.setStatusName(statusEnum != null ? statusEnum.getName() : "未知");

        // 查询工序列表
        if (workOrder.getRoutingId() != null) {
            List<MesOperationDO> operationList = operationMapper.selectListByRoutingId(workOrder.getRoutingId());
            // 查询工单的作业记录
            List<MesOperationRecordDO> recordList = operationRecordMapper.selectListByWorkOrderId(workOrder.getId());
            // 构建 operationId -> record 的映射
            Map<Long, MesOperationRecordDO> recordMap = recordList.stream()
                    .collect(Collectors.toMap(MesOperationRecordDO::getOperationId, r -> r, (a, b) -> a));

            List<MesScanRespVO.OperationInfo> operations = new ArrayList<>();
            for (MesOperationDO operation : operationList) {
                MesScanRespVO.OperationInfo opInfo = new MesScanRespVO.OperationInfo();
                opInfo.setOperationId(operation.getId());
                opInfo.setOperationCode(operation.getOperationCode());
                opInfo.setOperationName(operation.getOperationName());
                opInfo.setOperationSeq(operation.getSequence());
                MesOperationRecordDO record = recordMap.get(operation.getId());
                if (record != null) {
                    opInfo.setRecordId(record.getId());
                    opInfo.setStatus(record.getStatus());
                    opInfo.setCompleted(OperationStatusEnum.COMPLETED.getStatus().equals(record.getStatus()));
                } else {
                    opInfo.setCompleted(false);
                }
                operations.add(opInfo);
            }
            info.setOperations(operations);
        }
        return info;
    }

    private MesScanRespVO.MaterialInfo handleMaterialScan(String code) {
        MesScanRespVO.MaterialInfo info = new MesScanRespVO.MaterialInfo();
        info.setPartCode(code);
        info.setPartSn(code);
        info.setBinded(keyPartBindMapper.isPartSnBind(code));
        return info;
    }

    private boolean isValidVin(String vin) {
        if (vin == null || vin.length() != 17) {
            return false;
        }
        return vin.matches("^[A-Za-z0-9]+$");
    }
}
