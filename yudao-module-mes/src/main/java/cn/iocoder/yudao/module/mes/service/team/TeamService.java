package cn.iocoder.yudao.module.mes.service.team;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mes.controller.admin.team.vo.TeamPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.team.vo.TeamSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.team.TeamDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 班组 Service 接口
 *
 * @author 芋道源码
 */
public interface TeamService {

    /**
     * 创建班组
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTeam(@Valid TeamSaveReqVO createReqVO);

    /**
     * 更新班组
     *
     * @param updateReqVO 更新信息
     */
    void updateTeam(@Valid TeamSaveReqVO updateReqVO);

    /**
     * 删除班组
     *
     * @param id 编号
     */
    void deleteTeam(Long id);

    /**
     * 获得班组
     *
     * @param id 编号
     * @return 班组
     */
    TeamDO getTeam(Long id);

    /**
     * 获得班组列表
     *
     * @param ids 编号列表
     * @return 班组列表
     */
    List<TeamDO> getTeamList(Collection<Long> ids);

    /**
     * 获得班组分页
     *
     * @param pageReqVO 分页查询
     * @return 班组分页
     */
    PageResult<TeamDO> getTeamPage(TeamPageReqVO pageReqVO);

    /**
     * 获得班组列表
     *
     * @param reqVO 查询条件
     * @return 班组列表
     */
    List<TeamDO> getTeamList(TeamPageReqVO reqVO);

    /**
     * 获得产线下的班组列表
     *
     * @param lineId 产线编号
     * @return 班组列表
     */
    List<TeamDO> getTeamListByLineId(Long lineId);

    /**
     * 校验班组是否存在
     *
     * @param id 编号
     * @return 班组
     */
    TeamDO validateTeamExists(Long id);

}