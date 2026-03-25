package cn.iocoder.yudao.module.mes.dal.mysql.workstation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.controller.admin.workstation.vo.WorkstationPageReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.workstation.WorkstationDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * 工作站 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface WorkstationMapper extends BaseMapperX<WorkstationDO> {

    default PageResult<WorkstationDO> selectPage(WorkstationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WorkstationDO>()
                .likeIfPresent(WorkstationDO::getWorkstationCode, reqVO.getWorkstationCode())
                .likeIfPresent(WorkstationDO::getWorkstationName, reqVO.getWorkstationName())
                .eqIfPresent(WorkstationDO::getWorkshopId, reqVO.getWorkshopId())
                .eqIfPresent(WorkstationDO::getLineId, reqVO.getLineId())
                .eqIfPresent(WorkstationDO::getWorkstationType, reqVO.getWorkstationType())
                .eqIfPresent(WorkstationDO::getStatus, reqVO.getStatus())
                .orderByDesc(WorkstationDO::getId));
    }

    default List<WorkstationDO> selectList(WorkstationPageReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<WorkstationDO>()
                .likeIfPresent(WorkstationDO::getWorkstationCode, reqVO.getWorkstationCode())
                .likeIfPresent(WorkstationDO::getWorkstationName, reqVO.getWorkstationName())
                .eqIfPresent(WorkstationDO::getWorkshopId, reqVO.getWorkshopId())
                .eqIfPresent(WorkstationDO::getLineId, reqVO.getLineId())
                .eqIfPresent(WorkstationDO::getWorkstationType, reqVO.getWorkstationType())
                .eqIfPresent(WorkstationDO::getStatus, reqVO.getStatus())
                .orderByDesc(WorkstationDO::getId));
    }

    default WorkstationDO selectByWorkstationCode(String workstationCode) {
        return selectOne(WorkstationDO::getWorkstationCode, workstationCode);
    }

    default WorkstationDO selectByWorkstationName(String workstationName) {
        return selectOne(WorkstationDO::getWorkstationName, workstationName);
    }

    default List<WorkstationDO> selectListByLineId(Long lineId) {
        return selectList(WorkstationDO::getLineId, lineId);
    }

    default List<WorkstationDO> selectListByLineIds(Collection<Long> lineIds) {
        return selectList(WorkstationDO::getLineId, lineIds);
    }

    default Long selectCountByLineId(Long lineId) {
        return selectCount(WorkstationDO::getLineId, lineId);
    }

    default Long selectCountByWorkshopId(Long workshopId) {
        return selectCount(WorkstationDO::getWorkshopId, workshopId);
    }

}