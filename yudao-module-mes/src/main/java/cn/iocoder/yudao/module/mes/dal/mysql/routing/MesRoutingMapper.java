package cn.iocoder.yudao.module.mes.dal.mysql.routing;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.controller.admin.routing.vo.MesRoutingPageReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.routing.MesRoutingDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * MES 工艺路线 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MesRoutingMapper extends BaseMapperX<MesRoutingDO> {

    default PageResult<MesRoutingDO> selectPage(MesRoutingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MesRoutingDO>()
                .likeIfPresent(MesRoutingDO::getRoutingCode, reqVO.getRoutingCode())
                .likeIfPresent(MesRoutingDO::getRoutingName, reqVO.getRoutingName())
                .eqIfPresent(MesRoutingDO::getProductCode, reqVO.getProductCode())
                .eqIfPresent(MesRoutingDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(MesRoutingDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MesRoutingDO::getId));
    }

    default MesRoutingDO selectByRoutingCode(String routingCode) {
        return selectOne(MesRoutingDO::getRoutingCode, routingCode);
    }

    default List<MesRoutingDO> selectListByProductId(Long productId) {
        return selectList(MesRoutingDO::getProductId, productId);
    }

    default List<MesRoutingDO> selectListByStatus(Integer status) {
        return selectList(MesRoutingDO::getStatus, status);
    }

}