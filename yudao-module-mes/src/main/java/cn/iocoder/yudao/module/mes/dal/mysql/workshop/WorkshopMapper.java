package cn.iocoder.yudao.module.mes.dal.mysql.workshop;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.controller.admin.workshop.vo.WorkshopListReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.workshop.WorkshopDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * 车间 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface WorkshopMapper extends BaseMapperX<WorkshopDO> {

    default List<WorkshopDO> selectList(WorkshopListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<WorkshopDO>()
                .likeIfPresent(WorkshopDO::getWorkshopCode, reqVO.getWorkshopCode())
                .likeIfPresent(WorkshopDO::getWorkshopName, reqVO.getWorkshopName())
                .eqIfPresent(WorkshopDO::getStatus, reqVO.getStatus())
                .orderByAsc(WorkshopDO::getSort));
    }

    default WorkshopDO selectByWorkshopCode(String workshopCode) {
        return selectOne(WorkshopDO::getWorkshopCode, workshopCode);
    }

    default WorkshopDO selectByWorkshopName(String workshopName) {
        return selectOne(WorkshopDO::getWorkshopName, workshopName);
    }

    default WorkshopDO selectByParentIdAndName(Long parentId, String workshopName) {
        return selectOne(WorkshopDO::getParentId, parentId, WorkshopDO::getWorkshopName, workshopName);
    }

    default Long selectCountByParentId(Long parentId) {
        return selectCount(WorkshopDO::getParentId, parentId);
    }

    default List<WorkshopDO> selectListByParentId(Collection<Long> parentIds) {
        return selectList(WorkshopDO::getParentId, parentIds);
    }

}