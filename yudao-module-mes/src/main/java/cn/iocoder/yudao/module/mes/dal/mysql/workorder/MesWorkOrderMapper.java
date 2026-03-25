package cn.iocoder.yudao.module.mes.dal.mysql.workorder;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.controller.admin.workorder.vo.MesWorkOrderPageReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.workorder.MesWorkOrderDO;
import cn.iocoder.yudao.module.mes.enums.WorkOrderStatusEnum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 生产工单 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MesWorkOrderMapper extends BaseMapperX<MesWorkOrderDO> {

    default PageResult<MesWorkOrderDO> selectPage(MesWorkOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MesWorkOrderDO>()
                .likeIfPresent(MesWorkOrderDO::getOrderNo, reqVO.getOrderNo())
                .likeIfPresent(MesWorkOrderDO::getErpOrderNo, reqVO.getErpOrderNo())
                .likeIfPresent(MesWorkOrderDO::getProductCode, reqVO.getProductCode())
                .likeIfPresent(MesWorkOrderDO::getProductName, reqVO.getProductName())
                .eqIfPresent(MesWorkOrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MesWorkOrderDO::getLineId, reqVO.getLineId())
                .eqIfPresent(MesWorkOrderDO::getWorkshopId, reqVO.getWorkshopId())
                .betweenIfPresent(MesWorkOrderDO::getPlanStartTime, reqVO.getPlanStartTime())
                .betweenIfPresent(MesWorkOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MesWorkOrderDO::getId));
    }

    default List<MesWorkOrderDO> selectList(MesWorkOrderPageReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MesWorkOrderDO>()
                .likeIfPresent(MesWorkOrderDO::getOrderNo, reqVO.getOrderNo())
                .likeIfPresent(MesWorkOrderDO::getProductCode, reqVO.getProductCode())
                .eqIfPresent(MesWorkOrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MesWorkOrderDO::getLineId, reqVO.getLineId())
                .orderByDesc(MesWorkOrderDO::getId));
    }

    default MesWorkOrderDO selectByOrderNo(String orderNo) {
        return selectOne(MesWorkOrderDO::getOrderNo, orderNo);
    }

    default Long selectCountByLineIdAndStatus(Long lineId, Integer status) {
        return selectCount(new LambdaQueryWrapperX<MesWorkOrderDO>()
                .eq(MesWorkOrderDO::getLineId, lineId)
                .eq(MesWorkOrderDO::getStatus, status));
    }

    default List<MesWorkOrderDO> selectListByStatus(Integer status) {
        return selectList(MesWorkOrderDO::getStatus, status);
    }

    default List<MesWorkOrderDO> selectListByLineId(Long lineId) {
        return selectList(MesWorkOrderDO::getLineId, lineId);
    }

    /**
     * 查询产线是否有生产中的工单
     */
    default boolean hasProducingWorkOrder(Long lineId) {
        return selectCount(new LambdaQueryWrapperX<MesWorkOrderDO>()
                .eq(MesWorkOrderDO::getLineId, lineId)
                .eq(MesWorkOrderDO::getStatus, WorkOrderStatusEnum.IN_PROGRESS.getStatus())) > 0;
    }

}