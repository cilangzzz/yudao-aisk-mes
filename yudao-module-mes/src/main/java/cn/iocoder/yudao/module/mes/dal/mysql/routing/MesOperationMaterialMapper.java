package cn.iocoder.yudao.module.mes.dal.mysql.routing;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.dal.dataobject.routing.MesOperationMaterialDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * MES 工序物料 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MesOperationMaterialMapper extends BaseMapperX<MesOperationMaterialDO> {

    default List<MesOperationMaterialDO> selectListByOperationId(Long operationId) {
        return selectList(MesOperationMaterialDO::getOperationId, operationId);
    }

    default void deleteByOperationId(Long operationId) {
        delete(new LambdaQueryWrapperX<MesOperationMaterialDO>()
                .eq(MesOperationMaterialDO::getOperationId, operationId));
    }

    default void deleteByOperationIds(List<Long> operationIds) {
        delete(new LambdaQueryWrapperX<MesOperationMaterialDO>()
                .in(MesOperationMaterialDO::getOperationId, operationIds));
    }

}