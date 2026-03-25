package cn.iocoder.yudao.module.mes.controller.admin.workshop;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.workshop.vo.WorkshopListReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.workshop.vo.WorkshopRespVO;
import cn.iocoder.yudao.module.mes.controller.admin.workshop.vo.WorkshopSaveReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.workshop.vo.WorkshopSimpleRespVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.workshop.WorkshopDO;
import cn.iocoder.yudao.module.mes.service.workshop.WorkshopService;
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

@Tag(name = "管理后台 - 车间")
@RestController
@RequestMapping("/mes/workshop")
@Validated
public class WorkshopController {

    @Resource
    private WorkshopService workshopService;

    @PostMapping("/create")
    @Operation(summary = "创建车间")
    @PreAuthorize("@ss.hasPermission('mes:workshop:create')")
    public CommonResult<Long> createWorkshop(@Valid @RequestBody WorkshopSaveReqVO createReqVO) {
        return success(workshopService.createWorkshop(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车间")
    @PreAuthorize("@ss.hasPermission('mes:workshop:update')")
    public CommonResult<Boolean> updateWorkshop(@Valid @RequestBody WorkshopSaveReqVO updateReqVO) {
        workshopService.updateWorkshop(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车间")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:workshop:delete')")
    public CommonResult<Boolean> deleteWorkshop(@RequestParam("id") Long id) {
        workshopService.deleteWorkshop(id);
        return success(true);
    }

    @GetMapping("/list")
    @Operation(summary = "获取车间列表")
    @PreAuthorize("@ss.hasPermission('mes:workshop:query')")
    public CommonResult<List<WorkshopRespVO>> getWorkshopList(WorkshopListReqVO reqVO) {
        List<WorkshopDO> list = workshopService.getWorkshopList(reqVO);
        return success(BeanUtils.toBean(list, WorkshopRespVO.class));
    }

    @GetMapping(value = {"/list-all-simple", "/simple-list"})
    @Operation(summary = "获取车间精简信息列表", description = "只包含被开启的车间，主要用于前端的下拉选项")
    public CommonResult<List<WorkshopSimpleRespVO>> getSimpleWorkshopList() {
        List<WorkshopDO> list = workshopService.getWorkshopList(
                new WorkshopListReqVO().setStatus(CommonStatusEnum.ENABLE.getStatus()));
        return success(BeanUtils.toBean(list, WorkshopSimpleRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得车间信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:workshop:query')")
    public CommonResult<WorkshopRespVO> getWorkshop(@RequestParam("id") Long id) {
        WorkshopDO workshop = workshopService.getWorkshop(id);
        return success(BeanUtils.toBean(workshop, WorkshopRespVO.class));
    }

}