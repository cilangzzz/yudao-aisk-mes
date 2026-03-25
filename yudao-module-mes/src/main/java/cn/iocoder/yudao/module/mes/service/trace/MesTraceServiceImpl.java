package cn.iocoder.yudao.module.mes.service.trace;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.trace.vo.*;
import cn.iocoder.yudao.module.mes.dal.dataobject.operation.MesKeyPartBindDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.operation.MesOperationRecordDO;
import cn.iocoder.yudao.module.mes.dal.mysql.operation.MesKeyPartBindMapper;
import cn.iocoder.yudao.module.mes.dal.mysql.operation.MesOperationRecordMapper;
import cn.iocoder.yudao.module.mes.enums.OperationStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.*;

/**
 * MES 追溯 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class MesTraceServiceImpl implements MesTraceService {

    @Resource
    private MesOperationRecordMapper operationRecordMapper;
    @Resource
    private MesKeyPartBindMapper keyPartBindMapper;

    @Override
    public MesVinTraceRespVO traceByVin(String vin) {
        // 1. 校验 VIN 格式
        if (vin == null || vin.length() != 17) {
            throw exception(TRACE_VIN_FORMAT_ERROR);
        }

        // 2. 查询作业记录（追溯数据永久保存）
        List<MesOperationRecordDO> operationRecords = operationRecordMapper.selectListByVin(vin);
        if (operationRecords.isEmpty()) {
            throw exception(TRACE_VIN_NOT_FOUND);
        }

        // 3. 查询关键件绑定记录
        List<MesKeyPartBindDO> keyParts = keyPartBindMapper.selectListByVin(vin);

        // 4. 构建追溯响应
        MesVinTraceRespVO respVO = new MesVinTraceRespVO();
        respVO.setVin(vin);

        // 4.1 构建作业记录列表
        List<MesVinTraceRespVO.OperationRecordVO> operationRecordVOs = BeanUtils.toBean(operationRecords, MesVinTraceRespVO.OperationRecordVO.class);
        // 设置状态名称
        operationRecordVOs.forEach(vo -> {
            vo.setStatusName(OperationStatusEnum.getNameByStatus(vo.getStatus()));
        });
        respVO.setOperationRecords(operationRecordVOs);

        // 4.2 构建关键件列表
        respVO.setKeyParts(BeanUtils.toBean(keyParts, MesVinTraceRespVO.KeyPartVO.class));

        return respVO;
    }

    @Override
    public MesPartTraceRespVO traceByPartSn(String partSn) {
        // 1. 查询关键件绑定记录
        MesKeyPartBindDO keyPartBind = keyPartBindMapper.selectByPartSn(partSn);
        if (keyPartBind == null) {
            throw exception(TRACE_KEY_PART_NOT_FOUND);
        }

        // 2. 构建反向追溯响应
        MesPartTraceRespVO respVO = BeanUtils.toBean(keyPartBind, MesPartTraceRespVO.class);
        respVO.setBindVin(keyPartBind.getVin());

        return respVO;
    }

    @Override
    public MesOperationRecordDetailRespVO getOperationDetail(Long recordId) {
        // 1. 查询作业记录
        MesOperationRecordDO record = operationRecordMapper.selectById(recordId);
        if (record == null) {
            throw exception(OPERATION_RECORD_NOT_EXISTS);
        }

        // 2. 构建详情响应
        MesOperationRecordDetailRespVO respVO = BeanUtils.toBean(record, MesOperationRecordDetailRespVO.class);
        respVO.setStatusName(OperationStatusEnum.getNameByStatus(record.getStatus()));

        return respVO;
    }

    @Override
    public PageResult<MesOperationRecordDetailRespVO> getOperatorRecords(MesOperatorRecordPageReqVO pageReqVO) {
        // 1. 分页查询作业记录
        PageResult<MesOperationRecordDO> pageResult = operationRecordMapper.selectPage(pageReqVO,
                new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<MesOperationRecordDO>()
                        .eqIfPresent(MesOperationRecordDO::getOperatorId, pageReqVO.getOperatorId())
                        .likeIfPresent(MesOperationRecordDO::getVin, pageReqVO.getVin())
                        .likeIfPresent(MesOperationRecordDO::getWorkOrderNo, pageReqVO.getWorkOrderNo())
                        .likeIfPresent(MesOperationRecordDO::getOperationName, pageReqVO.getOperationName())
                        .eqIfPresent(MesOperationRecordDO::getStatus, pageReqVO.getStatus())
                        .betweenIfPresent(MesOperationRecordDO::getStartTime, pageReqVO.getStartTimeBegin(), pageReqVO.getStartTimeEnd())
                        .orderByDesc(MesOperationRecordDO::getId));

        // 2. 转换响应
        PageResult<MesOperationRecordDetailRespVO> result = BeanUtils.toBean(pageResult, MesOperationRecordDetailRespVO.class);
        // 设置状态名称
        result.getList().forEach(vo -> {
            vo.setStatusName(OperationStatusEnum.getNameByStatus(vo.getStatus()));
        });

        return result;
    }

}