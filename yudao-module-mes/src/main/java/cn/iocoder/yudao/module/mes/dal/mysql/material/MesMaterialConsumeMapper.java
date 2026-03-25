package cn.iocoder.yudao.module.mes.dal.mysql.material;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.controller.admin.material.vo.MesMaterialConsumePageReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.material.MesMaterialConsumeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * MES 物料消耗记录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MesMaterialConsumeMapper extends BaseMapperX<MesMaterialConsumeDO> {

    default PageResult<MesMaterialConsumeDO> selectPage(MesMaterialConsumePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MesMaterialConsumeDO>()
                .eqIfPresent(MesMaterialConsumeDO::getVin, reqVO.getVin())
                .eqIfPresent(MesMaterialConsumeDO::getWorkOrderId, reqVO.getWorkOrderId())
                .likeIfPresent(MesMaterialConsumeDO::getMaterialCode, reqVO.getMaterialCode())
                .eqIfPresent(MesMaterialConsumeDO::getWorkstationId, reqVO.getWorkstationId())
                .betweenIfPresent(MesMaterialConsumeDO::getConsumeTime, reqVO.getConsumeTime())
                .orderByDesc(MesMaterialConsumeDO::getId));
    }

    default List<MesMaterialConsumeDO> selectListByWorkOrderId(Long workOrderId) {
        return selectList(MesMaterialConsumeDO::getWorkOrderId, workOrderId);
    }

    default List<MesMaterialConsumeDO> selectListByVin(String vin) {
        return selectList(MesMaterialConsumeDO::getVin, vin);
    }

}