package cn.iocoder.yudao.module.mes.dal.mysql.shift;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.controller.admin.shift.vo.ShiftPageReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.shift.ShiftDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 班次 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ShiftMapper extends BaseMapperX<ShiftDO> {

    default PageResult<ShiftDO> selectPage(ShiftPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ShiftDO>()
                .likeIfPresent(ShiftDO::getShiftCode, reqVO.getShiftCode())
                .likeIfPresent(ShiftDO::getShiftName, reqVO.getShiftName())
                .eqIfPresent(ShiftDO::getStatus, reqVO.getStatus())
                .orderByDesc(ShiftDO::getId));
    }

    default List<ShiftDO> selectList(ShiftPageReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ShiftDO>()
                .likeIfPresent(ShiftDO::getShiftCode, reqVO.getShiftCode())
                .likeIfPresent(ShiftDO::getShiftName, reqVO.getShiftName())
                .eqIfPresent(ShiftDO::getStatus, reqVO.getStatus())
                .orderByDesc(ShiftDO::getId));
    }

    default ShiftDO selectByShiftCode(String shiftCode) {
        return selectOne(ShiftDO::getShiftCode, shiftCode);
    }

    default ShiftDO selectByShiftName(String shiftName) {
        return selectOne(ShiftDO::getShiftName, shiftName);
    }

}