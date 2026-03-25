package cn.iocoder.yudao.module.mes.dal.mysql.routing;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.dal.dataobject.routing.MesOperationDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * MES 工序定义 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MesOperationMapper extends BaseMapperX<MesOperationDO> {

    default List<MesOperationDO> selectListByRoutingId(Long routingId) {
        return selectList(new LambdaQueryWrapperX<MesOperationDO>()
                .eq(MesOperationDO::getRoutingId, routingId)
                .orderByAsc(MesOperationDO::getSequence));
    }

    default MesOperationDO selectByRoutingIdAndOperationCode(Long routingId, String operationCode) {
        return selectOne(new LambdaQueryWrapperX<MesOperationDO>()
                .eq(MesOperationDO::getRoutingId, routingId)
                .eq(MesOperationDO::getOperationCode, operationCode));
    }

    default void deleteByRoutingId(Long routingId) {
        delete(new LambdaQueryWrapperX<MesOperationDO>()
                .eq(MesOperationDO::getRoutingId, routingId));
    }

}