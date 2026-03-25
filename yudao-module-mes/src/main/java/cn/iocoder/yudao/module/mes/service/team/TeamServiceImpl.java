package cn.iocoder.yudao.module.mes.service.team;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.team.vo.TeamPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.team.vo.TeamSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.productionline.ProductionLineDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.team.TeamDO;
import cn.iocoder.yudao.module.mes.dal.mysql.team.TeamMapper;
import cn.iocoder.yudao.module.mes.service.productionline.ProductionLineService;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.*;

/**
 * 班组 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TeamServiceImpl implements TeamService {

    @Resource
    private TeamMapper teamMapper;

    @Resource
    private ProductionLineService productionLineService;

    @Override
    public Long createTeam(TeamSaveReqVO createReqVO) {
        // 1. 校验编码唯一
        validateTeamCodeUnique(null, createReqVO.getTeamCode());
        // 2. 校验名称唯一
        validateTeamNameUnique(null, createReqVO.getTeamName());
        // 3. 校验产线存在
        ProductionLineDO productionLine = validateProductionLine(createReqVO.getLineId());
        // 4. 插入数据
        TeamDO team = BeanUtils.toBean(createReqVO, TeamDO.class);
        // 设置默认状态
        if (team.getStatus() == null) {
            team.setStatus(CommonStatusEnum.ENABLE.getStatus());
        }
        // 冗余产线名称
        if (productionLine != null) {
            team.setLineName(productionLine.getLineName());
        }
        teamMapper.insert(team);
        return team.getId();
    }

    @Override
    public void updateTeam(TeamSaveReqVO updateReqVO) {
        // 1. 校验存在
        validateTeamExists(updateReqVO.getId());
        // 2. 校验编码唯一
        validateTeamCodeUnique(updateReqVO.getId(), updateReqVO.getTeamCode());
        // 3. 校验名称唯一
        validateTeamNameUnique(updateReqVO.getId(), updateReqVO.getTeamName());
        // 4. 校验产线存在
        ProductionLineDO productionLine = validateProductionLine(updateReqVO.getLineId());
        // 5. 更新数据
        TeamDO updateObj = BeanUtils.toBean(updateReqVO, TeamDO.class);
        // 冗余产线名称
        if (productionLine != null) {
            updateObj.setLineName(productionLine.getLineName());
        }
        teamMapper.updateById(updateObj);
    }

    @Override
    public void deleteTeam(Long id) {
        // 1. 校验存在
        validateTeamExists(id);
        // 2. 删除
        teamMapper.deleteById(id);
    }

    @Override
    public TeamDO getTeam(Long id) {
        return teamMapper.selectById(id);
    }

    @Override
    public List<TeamDO> getTeamList(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return teamMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<TeamDO> getTeamPage(TeamPageReqVO pageReqVO) {
        return teamMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TeamDO> getTeamList(TeamPageReqVO reqVO) {
        return teamMapper.selectList(reqVO);
    }

    @Override
    public List<TeamDO> getTeamListByLineId(Long lineId) {
        return teamMapper.selectListByLineId(lineId);
    }

    @Override
    public TeamDO validateTeamExists(Long id) {
        if (id == null) {
            return null;
        }
        TeamDO team = teamMapper.selectById(id);
        if (team == null) {
            throw exception(TEAM_NOT_EXISTS);
        }
        return team;
    }

    private ProductionLineDO validateProductionLine(Long lineId) {
        if (lineId == null) {
            return null;
        }
        ProductionLineDO productionLine = productionLineService.getProductionLine(lineId);
        if (productionLine == null) {
            throw exception(PRODUCTION_LINE_NOT_EXISTS);
        }
        if (!CommonStatusEnum.ENABLE.getStatus().equals(productionLine.getStatus())) {
            throw exception(TEAM_LINE_DISABLED);
        }
        return productionLine;
    }

    @VisibleForTesting
    void validateTeamCodeUnique(Long id, String teamCode) {
        if (teamCode == null) {
            return;
        }
        TeamDO team = teamMapper.selectByTeamCode(teamCode);
        if (team == null) {
            return;
        }
        if (id == null) {
            throw exception(TEAM_CODE_DUPLICATE);
        }
        if (!team.getId().equals(id)) {
            throw exception(TEAM_CODE_DUPLICATE);
        }
    }

    @VisibleForTesting
    void validateTeamNameUnique(Long id, String teamName) {
        if (teamName == null) {
            return;
        }
        TeamDO team = teamMapper.selectByTeamName(teamName);
        if (team == null) {
            return;
        }
        if (id == null) {
            throw exception(TEAM_NAME_DUPLICATE);
        }
        if (!team.getId().equals(id)) {
            throw exception(TEAM_NAME_DUPLICATE);
        }
    }

}