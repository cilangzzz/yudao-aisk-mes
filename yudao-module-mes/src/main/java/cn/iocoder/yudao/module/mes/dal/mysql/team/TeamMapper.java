package cn.iocoder.yudao.module.mes.dal.mysql.team;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.controller.admin.team.vo.TeamPageReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.team.TeamDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 班组 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TeamMapper extends BaseMapperX<TeamDO> {

    default PageResult<TeamDO> selectPage(TeamPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TeamDO>()
                .likeIfPresent(TeamDO::getTeamCode, reqVO.getTeamCode())
                .likeIfPresent(TeamDO::getTeamName, reqVO.getTeamName())
                .eqIfPresent(TeamDO::getLineId, reqVO.getLineId())
                .eqIfPresent(TeamDO::getStatus, reqVO.getStatus())
                .orderByDesc(TeamDO::getId));
    }

    default List<TeamDO> selectList(TeamPageReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TeamDO>()
                .likeIfPresent(TeamDO::getTeamCode, reqVO.getTeamCode())
                .likeIfPresent(TeamDO::getTeamName, reqVO.getTeamName())
                .eqIfPresent(TeamDO::getLineId, reqVO.getLineId())
                .eqIfPresent(TeamDO::getStatus, reqVO.getStatus())
                .orderByDesc(TeamDO::getId));
    }

    default TeamDO selectByTeamCode(String teamCode) {
        return selectOne(TeamDO::getTeamCode, teamCode);
    }

    default TeamDO selectByTeamName(String teamName) {
        return selectOne(TeamDO::getTeamName, teamName);
    }

    default List<TeamDO> selectListByLineId(Long lineId) {
        return selectList(TeamDO::getLineId, lineId);
    }

}