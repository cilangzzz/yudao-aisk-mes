package cn.iocoder.yudao.module.mes.controller.admin.team;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.team.vo.TeamPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.team.vo.TeamRespVO;
import cn.iocoder.yudao.module.mes.controller.admin.team.vo.TeamSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.team.TeamDO;
import cn.iocoder.yudao.module.mes.service.team.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 班组")
@RestController
@RequestMapping("/mes/team")
@Validated
public class TeamController {

    @Resource
    private TeamService teamService;

    @PostMapping("/create")
    @Operation(summary = "创建班组")
    @PreAuthorize("@ss.hasPermission('mes:team:create')")
    public CommonResult<Long> createTeam(@Valid @RequestBody TeamSaveReqVO createReqVO) {
        return success(teamService.createTeam(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新班组")
    @PreAuthorize("@ss.hasPermission('mes:team:update')")
    public CommonResult<Boolean> updateTeam(@Valid @RequestBody TeamSaveReqVO updateReqVO) {
        teamService.updateTeam(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除班组")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:team:delete')")
    public CommonResult<Boolean> deleteTeam(@RequestParam("id") Long id) {
        teamService.deleteTeam(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得班组")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:team:query')")
    public CommonResult<TeamRespVO> getTeam(@RequestParam("id") Long id) {
        TeamDO team = teamService.getTeam(id);
        return success(BeanUtils.toBean(team, TeamRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得班组分页")
    @PreAuthorize("@ss.hasPermission('mes:team:query')")
    public CommonResult<PageResult<TeamRespVO>> getTeamPage(@Valid TeamPageReqVO pageReqVO) {
        PageResult<TeamDO> pageResult = teamService.getTeamPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TeamRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得班组列表")
    @PreAuthorize("@ss.hasPermission('mes:team:query')")
    public CommonResult<List<TeamRespVO>> getTeamList(@Valid TeamPageReqVO reqVO) {
        List<TeamDO> list = teamService.getTeamList(reqVO);
        return success(BeanUtils.toBean(list, TeamRespVO.class));
    }

    @GetMapping("/list-by-line")
    @Operation(summary = "获得产线下的班组列表")
    @Parameter(name = "lineId", description = "产线编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:team:query')")
    public CommonResult<List<TeamRespVO>> getTeamListByLine(@RequestParam("lineId") Long lineId) {
        List<TeamDO> list = teamService.getTeamListByLineId(lineId);
        return success(BeanUtils.toBean(list, TeamRespVO.class));
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获得班组精简信息列表", description = "只包含被开启的班组，主要用于前端的下拉选项")
    public CommonResult<List<TeamRespVO>> getSimpleTeamList() {
        List<TeamDO> list = teamService.getTeamList(
                new TeamPageReqVO().setStatus(CommonStatusEnum.ENABLE.getStatus()));
        return success(BeanUtils.toBean(list, TeamRespVO.class));
    }

}