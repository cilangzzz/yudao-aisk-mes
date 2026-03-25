package cn.iocoder.yudao.module.mes.dal.mysql.operation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.controller.admin.operation.vo.MesOperationRecordPageReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.operation.MesOperationRecordDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 作业记录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MesOperationRecordMapper extends BaseMapperX<MesOperationRecordDO> {

    default PageResult<MesOperationRecordDO> selectPage(MesOperationRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MesOperationRecordDO>()
                .eqIfPresent(MesOperationRecordDO::getWorkOrderId, reqVO.getWorkOrderId())
                .likeIfPresent(MesOperationRecordDO::getWorkOrderNo, reqVO.getWorkOrderNo())
                .likeIfPresent(MesOperationRecordDO::getVin, reqVO.getVin())
                .likeIfPresent(MesOperationRecordDO::getOperationCode, reqVO.getOperationCode())
                .likeIfPresent(MesOperationRecordDO::getOperationName, reqVO.getOperationName())
                .eqIfPresent(MesOperationRecordDO::getWorkstationId, reqVO.getWorkstationId())
                .eqIfPresent(MesOperationRecordDO::getOperatorId, reqVO.getOperatorId())
                .eqIfPresent(MesOperationRecordDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(MesOperationRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MesOperationRecordDO::getId));
    }

    default List<MesOperationRecordDO> selectList(MesOperationRecordPageReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MesOperationRecordDO>()
                .eqIfPresent(MesOperationRecordDO::getWorkOrderId, reqVO.getWorkOrderId())
                .likeIfPresent(MesOperationRecordDO::getVin, reqVO.getVin())
                .eqIfPresent(MesOperationRecordDO::getStatus, reqVO.getStatus())
                .orderByDesc(MesOperationRecordDO::getId));
    }

    default MesOperationRecordDO selectByVinAndOperationId(String vin, Long operationId) {
        return selectOne(new LambdaQueryWrapperX<MesOperationRecordDO>()
                .eq(MesOperationRecordDO::getVin, vin)
                .eq(MesOperationRecordDO::getOperationId, operationId));
    }

    default List<MesOperationRecordDO> selectListByVin(String vin) {
        return selectList(MesOperationRecordDO::getVin, vin);
    }

    default List<MesOperationRecordDO> selectListByWorkOrderId(Long workOrderId) {
        return selectList(MesOperationRecordDO::getWorkOrderId, workOrderId);
    }

    default Long selectCountByWorkOrderIdAndStatus(Long workOrderId, Integer status) {
        return selectCount(new LambdaQueryWrapperX<MesOperationRecordDO>()
                .eq(MesOperationRecordDO::getWorkOrderId, workOrderId)
                .eq(MesOperationRecordDO::getStatus, status));
    }

}
