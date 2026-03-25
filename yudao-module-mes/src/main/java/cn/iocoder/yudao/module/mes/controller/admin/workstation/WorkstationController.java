package cn.iocoder.yudao.module.mes.controller.admin.workstation;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.mes.controller.admin.workstation.vo.WorkstationPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.workstation.vo.WorkstationRespVO;
import cn.iocoder.yudao.module.mes.controller.admin.workstation.vo.WorkstationSaveReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.workstation.vo.WorkstationSimpleRespVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.workstation.WorkstationDO;
import cn.iocoder.yudao.module.mes.service.workstation.WorkstationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 工作站")
@RestController
@RequestMapping("/mes/workstation")
@Validated
public class WorkstationController {

    @Resource
    private WorkstationService workstationService;

    @PostMapping("/create")
    @Operation(summary = "创建工作站")
    @PreAuthorize("@ss.hasPermission('mes:workstation:create')")
    public CommonResult<Long> createWorkstation(@Valid @RequestBody WorkstationSaveReqVO createReqVO) {
        return success(workstationService.createWorkstation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工作站")
    @PreAuthorize("@ss.hasPermission('mes:workstation:update')")
    public CommonResult<Boolean> updateWorkstation(@Valid @RequestBody WorkstationSaveReqVO updateReqVO) {
        workstationService.updateWorkstation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工作站")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:workstation:delete')")
    public CommonResult<Boolean> deleteWorkstation(@RequestParam("id") Long id) {
        workstationService.deleteWorkstation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工作站")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:workstation:query')")
    public CommonResult<WorkstationRespVO> getWorkstation(@RequestParam("id") Long id) {
        WorkstationDO workstation = workstationService.getWorkstation(id);
        return success(convertToRespVO(workstation));
    }

    @GetMapping("/page")
    @Operation(summary = "获得工作站分页")
    @PreAuthorize("@ss.hasPermission('mes:workstation:query')")
    public CommonResult<PageResult<WorkstationRespVO>> getWorkstationPage(@Valid WorkstationPageReqVO pageReqVO) {
        PageResult<WorkstationDO> pageResult = workstationService.getWorkstationPage(pageReqVO);
        return success(convertToRespVOPage(pageResult));
    }

    @GetMapping("/list")
    @Operation(summary = "获得工作站列表")
    @PreAuthorize("@ss.hasPermission('mes:workstation:query')")
    public CommonResult<List<WorkstationRespVO>> getWorkstationList(@Valid WorkstationPageReqVO reqVO) {
        List<WorkstationDO> list = workstationService.getWorkstationList(reqVO);
        return success(BeanUtils.toBean(list, WorkstationRespVO.class));
    }

    @GetMapping(value = {"/list-all-simple", "/simple-list"})
    @Operation(summary = "获得工作站精简信息列表", description = "只包含被开启的工作站，主要用于前端的下拉选项")
    public CommonResult<List<WorkstationSimpleRespVO>> getSimpleWorkstationList() {
        List<WorkstationDO> list = workstationService.getWorkstationList(
                new WorkstationPageReqVO().setStatus(CommonStatusEnum.ENABLE.getStatus()));
        return success(BeanUtils.toBean(list, WorkstationSimpleRespVO.class));
    }

    @GetMapping("/list-by-line")
    @Operation(summary = "获得产线下的工作站列表")
    @Parameter(name = "lineId", description = "产线编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:workstation:query')")
    public CommonResult<List<WorkstationSimpleRespVO>> getWorkstationListByLine(@RequestParam("lineId") Long lineId) {
        List<WorkstationDO> list = workstationService.getWorkstationListByLineId(lineId);
        return success(BeanUtils.toBean(list, WorkstationSimpleRespVO.class));
    }

    @PutMapping("/enable")
    @Operation(summary = "启用工作站")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:workstation:enable')")
    public CommonResult<Boolean> enableWorkstation(@RequestParam("id") Long id) {
        workstationService.enableWorkstation(id);
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "停用工作站")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:workstation:disable')")
    public CommonResult<Boolean> disableWorkstation(@RequestParam("id") Long id) {
        workstationService.disableWorkstation(id);
        return success(true);
    }

    @PutMapping("/bind-equipment")
    @Operation(summary = "绑定设备")
    @Parameters({
            @Parameter(name = "id", description = "工作站编号", required = true),
            @Parameter(name = "equipmentIds", description = "设备ID列表", required = true)
    })
    @PreAuthorize("@ss.hasPermission('mes:workstation:bind-equipment')")
    public CommonResult<Boolean> bindEquipment(@RequestParam("id") Long id,
                                                @RequestParam("equipmentIds") List<Long> equipmentIds) {
        workstationService.bindEquipment(id, equipmentIds);
        return success(true);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工作站 Excel")
    @PreAuthorize("@ss.hasPermission('mes:workstation:export')")
    public void exportWorkstationExcel(@Valid WorkstationPageReqVO pageReqVO,
                                        HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WorkstationDO> list = workstationService.getWorkstationList(pageReqVO);
        ExcelUtils.write(response, "工作站.xls", "数据", WorkstationRespVO.class,
                BeanUtils.toBean(list, WorkstationRespVO.class));
    }

    // ========== 辅助方法 ==========

    private WorkstationRespVO convertToRespVO(WorkstationDO workstation) {
        if (workstation == null) {
            return null;
        }
        WorkstationRespVO respVO = BeanUtils.toBean(workstation, WorkstationRespVO.class);
        // 解析设备ID列表
        if (CollUtil.isNotEmpty(Collections.singleton(workstation.getEquipmentIds()))) {
            respVO.setEquipmentIds(JsonUtils.parseArray(workstation.getEquipmentIds(), Long.class));
        }
        return respVO;
    }

    private PageResult<WorkstationRespVO> convertToRespVOPage(PageResult<WorkstationDO> pageResult) {
        List<WorkstationRespVO> list = BeanUtils.toBean(pageResult.getList(), WorkstationRespVO.class);
        // 解析设备ID列表
        for (int i = 0; i < list.size(); i++) {
            WorkstationDO source = pageResult.getList().get(i);
            if (CollUtil.isNotEmpty(Collections.singleton(source.getEquipmentIds()))) {
                list.get(i).setEquipmentIds(JsonUtils.parseArray(source.getEquipmentIds(), Long.class));
            }
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

}