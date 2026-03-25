package cn.iocoder.yudao.module.mes.service.operation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.mes.controller.admin.operation.vo.*;
import cn.iocoder.yudao.module.mes.dal.dataobject.operation.MesKeyPartBindDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.operation.MesOperationRecordDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.routing.MesOperationDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.workorder.MesWorkOrderDO;
import cn.iocoder.yudao.module.mes.dal.mysql.operation.MesKeyPartBindMapper;
import cn.iocoder.yudao.module.mes.dal.mysql.operation.MesOperationRecordMapper;
import cn.iocoder.yudao.module.mes.dal.mysql.routing.MesOperationMapper;
import cn.iocoder.yudao.module.mes.dal.mysql.workorder.MesWorkOrderMapper;
import cn.iocoder.yudao.module.mes.enums.OperationStatusEnum;
import cn.iocoder.yudao.module.mes.enums.ScanTypeEnum;
import cn.iocoder.yudao.module.mes.enums.WorkOrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.*;

/**
 * MES 管理后台作业 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class MesOperationAdminServiceImpl implements MesOperationAdminService {

    @Resource
    private MesOperationRecordMapper operationRecordMapper;
    @Resource
    private MesKeyPartBindMapper keyPartBindMapper;
    @Resource
    private MesWorkOrderMapper workOrderMapper;
    @Resource
    private MesOperationMapper mesOperationMapper;

    @Override
    public MesScanRespVO scan(MesScanReqVO reqVO) {
        String code = reqVO.getCode();
        MesScanRespVO respVO = new MesScanRespVO();

        // 1. 解析扫码类型
        ScanTypeEnum scanType = ScanTypeEnum.parse(code);
        if (scanType == null) {
            respVO.setCanStart(false);
            respVO.setMessage("无法识别的码格式");
            return respVO;
        }

        respVO.setScanType(scanType.getType());
        respVO.setScanTypeName(scanType.getName());

        // 2. 根据扫码类型处理
        switch (scanType) {
            case VIN:
                return handleVinScan(code, respVO);
            case WORK_ORDER:
                return handleWorkOrderScan(code, respVO);
            case MATERIAL:
                return handleMaterialScan(code, respVO, reqVO.getOperationId());
            default:
                respVO.setCanStart(false);
                respVO.setMessage("不支持的扫码类型");
                return respVO;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long startOperation(MesOperationStartReqVO reqVO) {
        // 1. 校验工单状态
        MesWorkOrderDO workOrder = workOrderMapper.selectById(reqVO.getWorkOrderId());
        if (workOrder == null) {
            throw exception(WORK_ORDER_NOT_EXISTS);
        }
        if (!WorkOrderStatusEnum.IN_PROGRESS.getStatus().equals(workOrder.getStatus())) {
            throw exception(OPERATION_WORK_ORDER_NOT_PRODUCING);
        }

        // 2. 获取工序信息
        MesOperationDO operation = mesOperationMapper.selectById(reqVO.getOperationId());
        if (operation == null) {
            throw exception(OPERATION_NOT_EXISTS);
        }

        // 3. 校验前置工序是否完成
        validatePreviousOperationCompleted(reqVO.getVin(), operation, workOrder.getRoutingId());

        // 4. 校验是否已有进行中的作业
        MesOperationRecordDO existRecord = operationRecordMapper.selectByVinAndOperationId(
                reqVO.getVin(), reqVO.getOperationId());
        if (existRecord != null) {
            if (OperationStatusEnum.IN_PROGRESS.getStatus().equals(existRecord.getStatus())) {
                throw exception(OPERATION_RECORD_DUPLICATE);
            }
            if (OperationStatusEnum.COMPLETED.getStatus().equals(existRecord.getStatus())) {
                throw exception(OPERATION_RECORD_ALREADY_COMPLETED);
            }
        }

        // 5. 创建作业记录
        MesOperationRecordDO record = new MesOperationRecordDO();
        record.setVin(reqVO.getVin());
        record.setWorkOrderId(reqVO.getWorkOrderId());
        record.setWorkOrderNo(workOrder.getOrderNo());
        record.setOperationId(reqVO.getOperationId());
        record.setOperationCode(operation.getOperationCode());
        record.setOperationName(operation.getOperationName());
        record.setOperationSeq(operation.getSequence());
        record.setWorkstationId(reqVO.getWorkstationId() != null ? reqVO.getWorkstationId() : operation.getWorkstationId());
        record.setStatus(OperationStatusEnum.IN_PROGRESS.getStatus());
        record.setStartTime(LocalDateTime.now());
        record.setRemark(reqVO.getRemark());

        // 设置操作员信息
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        record.setOperatorId(userId);

        operationRecordMapper.insert(record);
        log.info("开始作业: recordId={}, vin={}, operation={}", record.getId(), reqVO.getVin(), operation.getOperationCode());
        return record.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void completeOperation(MesOperationCompleteReqVO reqVO) {
        // 1. 查询作业记录
        MesOperationRecordDO record = operationRecordMapper.selectById(reqVO.getRecordId());
        if (record == null) {
            throw exception(OPERATION_RECORD_NOT_EXISTS);
        }

        // 2. 校验是否可完成
        if (!OperationStatusEnum.IN_PROGRESS.getStatus().equals(record.getStatus())) {
            throw exception(OPERATION_RECORD_ALREADY_COMPLETED);
        }

        // 3. 更新作业记录
        record.setStatus(OperationStatusEnum.COMPLETED.getStatus());
        record.setResult(reqVO.getResult());
        record.setEndTime(LocalDateTime.now());
        record.setDuration(calculateDuration(record.getStartTime(), record.getEndTime()));
        record.setTorqueValue(reqVO.getTorqueValue());
        record.setTorqueResult(reqVO.getTorqueResult());
        record.setRemark(reqVO.getRemark());

        operationRecordMapper.updateById(record);
        log.info("完成作业: recordId={}, result={}", reqVO.getRecordId(), reqVO.getResult());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindKeyPart(MesKeyPartBindReqVO reqVO) {
        // 1. 校验作业记录
        MesOperationRecordDO record = operationRecordMapper.selectById(reqVO.getOperationRecordId());
        if (record == null) {
            throw exception(OPERATION_RECORD_NOT_EXISTS);
        }

        // 2. 校验关键件是否已绑定
        MesKeyPartBindDO existBind = keyPartBindMapper.selectByPartSn(reqVO.getPartSn());
        if (existBind != null) {
            throw exception(KEY_PART_ALREADY_BIND);
        }

        // 3. 创建绑定记录
        MesKeyPartBindDO bind = new MesKeyPartBindDO();
        bind.setWorkOrderId(record.getWorkOrderId());
        bind.setOperationRecordId(reqVO.getOperationRecordId());
        bind.setVin(record.getVin());
        bind.setPartCode(reqVO.getPartCode());
        bind.setPartName(reqVO.getPartName());
        bind.setPartSn(reqVO.getPartSn());
        bind.setSupplierCode(reqVO.getSupplierCode());
        bind.setSupplierName(reqVO.getSupplierName());
        bind.setWorkstationId(record.getWorkstationId());
        bind.setBindTime(LocalDateTime.now());

        // 设置操作员信息
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        bind.setOperatorId(userId);
        bind.setOperatorName(null); // TODO: 获取用户名称

        keyPartBindMapper.insert(bind);
        log.info("关键件绑定: partSn={}, vin={}", reqVO.getPartSn(), record.getVin());
    }

    @Override
    public PageResult<MesOperationRecordRespVO> getOperationRecordPage(MesOperationRecordPageReqVO reqVO) {
        PageResult<MesOperationRecordDO> pageResult = operationRecordMapper.selectPage(reqVO);
        // 转换为响应VO
        List<MesOperationRecordRespVO> list = pageResult.getList().stream()
                .map(this::convertToRespVO)
                .collect(Collectors.toList());
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public MesOperationRecordRespVO getOperationRecord(Long id) {
        MesOperationRecordDO record = operationRecordMapper.selectById(id);
        if (record == null) {
            throw exception(OPERATION_RECORD_NOT_EXISTS);
        }
        MesOperationRecordRespVO respVO = convertToRespVO(record);
        // 查询关键件绑定列表
        List<MesKeyPartBindDO> keyParts = keyPartBindMapper.selectListByOperationRecordId(id);
        respVO.setKeyParts(BeanUtils.toBean(keyParts, MesKeyPartBindRespVO.class));
        return respVO;
    }

    @Override
    public MesVehicleProgressRespVO getVehicleProgress(String vin) {
        // 1. 查询该VIN的作业记录
        List<MesOperationRecordDO> records = operationRecordMapper.selectListByVin(vin);
        if (records.isEmpty()) {
            return null;
        }

        // 2. 获取工单信息
        MesOperationRecordDO firstRecord = records.get(0);
        MesWorkOrderDO workOrder = workOrderMapper.selectById(firstRecord.getWorkOrderId());

        // 3. 获取工艺路线的所有工序
        List<MesOperationDO> allOperations = mesOperationMapper.selectListByRoutingId(workOrder.getRoutingId());

        // 4. 构建响应
        MesVehicleProgressRespVO respVO = new MesVehicleProgressRespVO();
        respVO.setVin(vin);
        respVO.setWorkOrderId(workOrder.getId());
        respVO.setWorkOrderNo(workOrder.getOrderNo());
        respVO.setProductCode(workOrder.getProductCode());
        respVO.setProductName(workOrder.getProductName());
        respVO.setTotalOperations(allOperations.size());

        // 5. 构建工序进度列表
        Map<Long, MesOperationRecordDO> recordMap = records.stream()
                .collect(Collectors.toMap(MesOperationRecordDO::getOperationId, r -> r, (a, b) -> a));

        List<MesVehicleProgressRespVO.OperationProgressVO> progressList = new ArrayList<>();
        int completedCount = 0;
        MesVehicleProgressRespVO.OperationProgressVO currentOperation = null;

        for (MesOperationDO operation : allOperations) {
            MesVehicleProgressRespVO.OperationProgressVO progress = new MesVehicleProgressRespVO.OperationProgressVO();
            progress.setOperationId(operation.getId());
            progress.setOperationCode(operation.getOperationCode());
            progress.setOperationName(operation.getOperationName());
            progress.setOperationSeq(operation.getSequence());

            MesOperationRecordDO record = recordMap.get(operation.getId());
            if (record != null) {
                progress.setStatus(convertToProgressStatus(record.getStatus()));
                progress.setStatusName(getProgressStatusName(record.getStatus()));
                progress.setOperatorName(record.getOperatorName());
                progress.setStartTime(record.getStartTime());
                progress.setEndTime(record.getEndTime());

                if (OperationStatusEnum.COMPLETED.getStatus().equals(record.getStatus())) {
                    completedCount++;
                } else if (OperationStatusEnum.IN_PROGRESS.getStatus().equals(record.getStatus())) {
                    currentOperation = progress;
                }
            } else {
                progress.setStatus(0);
                progress.setStatusName("待作业");
            }

            progressList.add(progress);
        }

        respVO.setOperationProgressList(progressList);
        respVO.setCompletedOperations(completedCount);
        respVO.setProgressPercent(allOperations.isEmpty() ? 0 : (completedCount * 100.0 / allOperations.size()));
        respVO.setCurrentOperation(currentOperation);

        return respVO;
    }

    // ==================== 私有方法 ====================

    private MesScanRespVO handleVinScan(String vin, MesScanRespVO respVO) {
        respVO.setVin(vin);

        // 查询该 VIN 的作业记录
        List<MesOperationRecordDO> records = operationRecordMapper.selectListByVin(vin);
        if (!records.isEmpty()) {
            // 获取最新一条作业记录
            MesOperationRecordDO latestRecord = records.get(records.size() - 1);
            respVO.setWorkOrderId(latestRecord.getWorkOrderId());
            respVO.setWorkOrderNo(latestRecord.getWorkOrderNo());

            // 获取工单信息
            MesWorkOrderDO workOrder = workOrderMapper.selectById(latestRecord.getWorkOrderId());
            if (workOrder != null) {
                respVO.setProductCode(workOrder.getProductCode());
                respVO.setProductName(workOrder.getProductName());
            }
        }

        respVO.setCanStart(true);
        respVO.setMessage("VIN码识别成功");
        return respVO;
    }

    private MesScanRespVO handleWorkOrderScan(String workOrderNo, MesScanRespVO respVO) {
        MesWorkOrderDO workOrder = workOrderMapper.selectByOrderNo(workOrderNo);
        if (workOrder == null) {
            respVO.setCanStart(false);
            respVO.setMessage("工单不存在");
            return respVO;
        }

        respVO.setWorkOrderId(workOrder.getId());
        respVO.setWorkOrderNo(workOrderNo);
        respVO.setProductCode(workOrder.getProductCode());
        respVO.setProductName(workOrder.getProductName());

        if (!WorkOrderStatusEnum.IN_PROGRESS.getStatus().equals(workOrder.getStatus())) {
            respVO.setCanStart(false);
            respVO.setMessage("工单未处于生产中状态");
            return respVO;
        }

        respVO.setCanStart(true);
        respVO.setMessage("工单识别成功");
        return respVO;
    }

    private MesScanRespVO handleMaterialScan(String materialCode, MesScanRespVO respVO, Long operationId) {
        // TODO: 实现物料扫码逻辑
        respVO.setMaterialCode(materialCode);
        respVO.setCanStart(false);
        respVO.setMessage("物料码识别成功");
        return respVO;
    }

    private void validatePreviousOperationCompleted(String vin, MesOperationDO currentOperation, Long routingId) {
        // 获取当前工序之前的所有工序
        List<MesOperationDO> allOperations = mesOperationMapper.selectListByRoutingId(routingId);
        for (MesOperationDO operation : allOperations) {
            if (operation.getSequence() >= currentOperation.getSequence()) {
                break;
            }
            // 检查前置工序是否已完成
            MesOperationRecordDO record = operationRecordMapper.selectByVinAndOperationId(vin, operation.getId());
            if (record == null || !OperationStatusEnum.COMPLETED.getStatus().equals(record.getStatus())) {
                throw exception(OPERATION_PRE_NOT_COMPLETED);
            }
        }
    }

    private MesOperationRecordRespVO convertToRespVO(MesOperationRecordDO record) {
        MesOperationRecordRespVO respVO = BeanUtils.toBean(record, MesOperationRecordRespVO.class);
        respVO.setStatusName(OperationStatusEnum.getNameByStatus(record.getStatus()));
        respVO.setResultName(record.getResult() != null ? (record.getResult() == 0 ? "合格" : "不合格") : null);
        respVO.setTorqueResultName(record.getTorqueResult() != null ? (record.getTorqueResult() == 0 ? "合格" : "不合格") : null);
        return respVO;
    }

    private Integer calculateDuration(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            return null;
        }
        long seconds = java.time.Duration.between(startTime, endTime).getSeconds();
        return (int) seconds;
    }

    private Integer convertToProgressStatus(Integer recordStatus) {
        if (OperationStatusEnum.IN_PROGRESS.getStatus().equals(recordStatus)) {
            return 1; // 进行中
        } else if (OperationStatusEnum.COMPLETED.getStatus().equals(recordStatus)) {
            return 2; // 已完成
        } else if (OperationStatusEnum.ABNORMAL.getStatus().equals(recordStatus)) {
            return 3; // 异常
        }
        return 0; // 待作业
    }

    private String getProgressStatusName(Integer recordStatus) {
        if (OperationStatusEnum.IN_PROGRESS.getStatus().equals(recordStatus)) {
            return "进行中";
        } else if (OperationStatusEnum.COMPLETED.getStatus().equals(recordStatus)) {
            return "已完成";
        } else if (OperationStatusEnum.ABNORMAL.getStatus().equals(recordStatus)) {
            return "异常";
        }
        return "待作业";
    }

}