package cn.iocoder.yudao.module.mes.service.operation;

import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.mes.controller.app.vo.*;
import cn.iocoder.yudao.module.mes.dal.dataobject.operation.MesKeyPartBindDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.operation.MesOperationRecordDO;
import cn.iocoder.yudao.module.mes.dal.mysql.operation.MesKeyPartBindMapper;
import cn.iocoder.yudao.module.mes.dal.mysql.operation.MesOperationRecordMapper;
import cn.iocoder.yudao.module.mes.enums.OperationStatusEnum;
import cn.iocoder.yudao.module.mes.enums.ScanTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.*;

/**
 * MES 移动端作业 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class MesOperationServiceImpl implements MesOperationService {

    @Resource
    private MesOperationRecordMapper operationRecordMapper;
    @Resource
    private MesKeyPartBindMapper keyPartBindMapper;

    @Override
    public MesScanRespVO scan(MesScanReqVO reqVO) {
        String scanCode = reqVO.getScanCode();
        MesScanRespVO respVO = new MesScanRespVO();

        // 1. 解析扫码类型
        ScanTypeEnum scanType = ScanTypeEnum.parse(scanCode);
        if (scanType == null) {
            respVO.setSuccess(false);
            respVO.setFailReason("无法识别的码格式");
            return respVO;
        }

        respVO.setScanType(scanType.getType());
        respVO.setSuccess(true);

        // 2. 根据扫码类型处理
        switch (scanType) {
            case VIN:
                return handleVinScan(scanCode, respVO);
            case WORK_ORDER:
                return handleWorkOrderScan(scanCode, respVO);
            case MATERIAL:
                return handleMaterialScan(scanCode, respVO);
            default:
                respVO.setSuccess(false);
                respVO.setFailReason("不支持的扫码类型");
                return respVO;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long startOperation(MesOperationStartReqVO reqVO) {
        // 1. 校验是否已有进行中的作业
        MesOperationRecordDO existRecord = operationRecordMapper.selectByVinAndOperationId(
                reqVO.getVin(), reqVO.getOperationId());
        if (existRecord != null && OperationStatusEnum.IN_PROGRESS.getStatus().equals(existRecord.getStatus())) {
            throw exception(OPERATION_RECORD_DUPLICATE);
        }

        // 2. 创建作业记录
        MesOperationRecordDO record = new MesOperationRecordDO();
        record.setVin(reqVO.getVin());
        record.setWorkOrderId(reqVO.getWorkOrderId());
        record.setOperationId(reqVO.getOperationId());
        record.setWorkstationId(reqVO.getWorkstationId());
        record.setStatus(OperationStatusEnum.IN_PROGRESS.getStatus());
        record.setStartTime(LocalDateTime.now());

        // 设置操作员信息
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        record.setOperatorId(userId);

        operationRecordMapper.insert(record);
        return record.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void completeOperation(MesOperationCompleteReqVO reqVO) {
        // 1. 查询作业记录
        MesOperationRecordDO record = operationRecordMapper.selectById(reqVO.getId());
        if (record == null) {
            throw exception(OPERATION_RECORD_NOT_EXISTS);
        }

        // 2. 校验是否可完成
        if (!OperationStatusEnum.IN_PROGRESS.getStatus().equals(record.getStatus())) {
            throw exception(OPERATION_RECORD_ALREADY_COMPLETED);
        }

        // 3. TODO: 校验关键件是否全部绑定（MT-003）
        // validateKeyPartsBound(record);

        // 4. 更新作业记录
        record.setStatus(OperationStatusEnum.COMPLETED.getStatus());
        record.setResult(reqVO.getResult());
        record.setEndTime(LocalDateTime.now());
        record.setDuration(calculateDuration(record.getStartTime(), record.getEndTime()));
        record.setTorqueValue(reqVO.getTorqueValue());
        record.setRemark(reqVO.getRemark());

        operationRecordMapper.updateById(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindKeyPart(MesKeyPartBindReqVO reqVO) {
        // 1. 校验关键件是否已绑定
        MesKeyPartBindDO existBind = keyPartBindMapper.selectByPartSn(reqVO.getPartSn());
        if (existBind != null) {
            throw exception(KEY_PART_ALREADY_BIND);
        }

        // 2. 创建绑定记录
        MesKeyPartBindDO bind = new MesKeyPartBindDO();
        bind.setWorkOrderId(reqVO.getWorkOrderId());
        bind.setOperationRecordId(reqVO.getOperationRecordId());
        bind.setVin(reqVO.getVin());
        bind.setPartCode(reqVO.getPartCode());
        bind.setPartName(reqVO.getPartName());
        bind.setPartSn(reqVO.getPartSn());
        bind.setSupplierCode(reqVO.getSupplierCode());
        bind.setSupplierName(reqVO.getSupplierName());
        bind.setWorkstationId(reqVO.getWorkstationId());
        bind.setBindTime(LocalDateTime.now());

        // 设置操作员信息
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        bind.setOperatorId(userId);

        keyPartBindMapper.insert(bind);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reportException(MesExceptionReportReqVO reqVO) {
        // 1. 校验异常原因不能为空（MT-004）
        if (reqVO.getExceptionReason() == null || reqVO.getExceptionReason().trim().isEmpty()) {
            throw exception(EXCEPTION_REASON_REQUIRED);
        }

        // 2. 查询当前作业记录
        MesOperationRecordDO record = operationRecordMapper.selectByVinAndOperationId(
                reqVO.getVin(), reqVO.getOperationId());
        if (record == null) {
            // 如果没有作业记录，创建一个异常记录
            record = new MesOperationRecordDO();
            record.setVin(reqVO.getVin());
            record.setWorkOrderId(reqVO.getWorkOrderId());
            record.setOperationId(reqVO.getOperationId());
            record.setWorkstationId(reqVO.getWorkstationId());
            record.setStatus(OperationStatusEnum.ABNORMAL.getStatus());
            record.setStartTime(LocalDateTime.now());
            record.setRemark("异常上报: " + reqVO.getExceptionReason());

            Long userId = SecurityFrameworkUtils.getLoginUserId();
            record.setOperatorId(userId);

            operationRecordMapper.insert(record);
        } else {
            // 更新状态为异常
            record.setStatus(OperationStatusEnum.ABNORMAL.getStatus());
            record.setRemark("异常上报: " + reqVO.getExceptionReason());
            operationRecordMapper.updateById(record);
        }

        log.info("异常上报成功: VIN={}, 工单ID={}, 异常原因={}",
                reqVO.getVin(), reqVO.getWorkOrderId(), reqVO.getExceptionReason());
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

            // 构建当前工序信息
            MesScanRespVO.CurrentOperationVO operationVO = new MesScanRespVO.CurrentOperationVO();
            operationVO.setOperationId(latestRecord.getOperationId());
            operationVO.setOperationCode(latestRecord.getOperationCode());
            operationVO.setOperationName(latestRecord.getOperationName());
            respVO.setCurrentOperation(operationVO);
        }

        // 查询已绑定关键件
        List<MesKeyPartBindDO> boundParts = keyPartBindMapper.selectListByVin(vin);
        respVO.setBoundParts(cn.iocoder.yudao.framework.common.util.object.BeanUtils.toBean(
                boundParts, MesScanRespVO.BoundPartVO.class));

        return respVO;
    }

    private MesScanRespVO handleWorkOrderScan(String workOrderNo, MesScanRespVO respVO) {
        respVO.setWorkOrderNo(workOrderNo);
        // TODO: 查询工单信息并返回
        return respVO;
    }

    private MesScanRespVO handleMaterialScan(String materialCode, MesScanRespVO respVO) {
        // TODO: 处理物料扫码
        return respVO;
    }

    private Integer calculateDuration(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            return null;
        }
        long seconds = java.time.Duration.between(startTime, endTime).getSeconds();
        return (int) seconds;
    }

}