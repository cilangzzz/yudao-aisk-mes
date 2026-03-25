package cn.iocoder.yudao.module.mes.dal.mysql.operation;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.dal.dataobject.operation.MesKeyPartBindDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 关键件绑定 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MesKeyPartBindMapper extends BaseMapperX<MesKeyPartBindDO> {

    default MesKeyPartBindDO selectByPartSn(String partSn) {
        return selectOne(MesKeyPartBindDO::getPartSn, partSn);
    }

    default List<MesKeyPartBindDO> selectListByVin(String vin) {
        return selectList(MesKeyPartBindDO::getVin, vin);
    }

    default List<MesKeyPartBindDO> selectListByWorkOrderId(Long workOrderId) {
        return selectList(MesKeyPartBindDO::getWorkOrderId, workOrderId);
    }

    default List<MesKeyPartBindDO> selectListByOperationRecordId(Long operationRecordId) {
        return selectList(MesKeyPartBindDO::getOperationRecordId, operationRecordId);
    }

    default List<MesKeyPartBindDO> selectListByPartCode(String partCode) {
        return selectList(MesKeyPartBindDO::getPartCode, partCode);
    }

    default boolean isPartSnBind(String partSn) {
        return selectCount(MesKeyPartBindDO::getPartSn, partSn) > 0;
    }

    default Integer selectCountByOperationRecordId(Long operationRecordId) {
        return Math.toIntExact(selectCount(MesKeyPartBindDO::getOperationRecordId, operationRecordId));
    }

}
