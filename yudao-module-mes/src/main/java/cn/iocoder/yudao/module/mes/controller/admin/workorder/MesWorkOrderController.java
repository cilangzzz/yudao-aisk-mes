package cn.iocoder.yudao.module.mes.controller.admin.workorder;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.mes.controller.admin.workorder.vo.MesWorkOrderPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.workorder.vo.MesWorkOrderRespVO;
import cn.iocoder.yudao.module.mes.controller.admin.workorder.vo.MesWorkOrderSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.workorder.MesWorkOrderDO;
import cn.iocoder.yudao.module.mes.service.workorder.MesWorkOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 生产工单")
@RestController
@RequestMapping("/mes/work-order")
@Validated
public class MesWorkOrderController {

    @Resource
    private MesWorkOrderService workOrderService;

    @PostMapping("/create")
    @Operation(summary = "创建生产工单")
    @PreAuthorize("@ss.hasPermission('mes:work-order:create')")
    public CommonResult<Long> createWorkOrder(@Valid @RequestBody MesWorkOrderSaveReqVO createReqVO) {
        return success(workOrderService.createWorkOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新生产工单")
    @PreAuthorize("@ss.hasPermission('mes:work-order:update')")
    public CommonResult<Boolean> updateWorkOrder(@Valid @RequestBody MesWorkOrderSaveReqVO updateReqVO) {
        workOrderService.updateWorkOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除生产工单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:work-order:delete')")
    public CommonResult<Boolean> deleteWorkOrder(@RequestParam("id") Long id) {
        workOrderService.deleteWorkOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得生产工单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:work-order:query')")
    public CommonResult<MesWorkOrderRespVO> getWorkOrder(@RequestParam("id") Long id) {
        MesWorkOrderDO workOrder = workOrderService.getWorkOrder(id);
        return success(BeanUtils.toBean(workOrder, MesWorkOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得生产工单分页")
    @PreAuthorize("@ss.hasPermission('mes:work-order:query')")
    public CommonResult<PageResult<MesWorkOrderRespVO>> getWorkOrderPage(@Valid MesWorkOrderPageReqVO pageReqVO) {
        PageResult<MesWorkOrderDO> pageResult = workOrderService.getWorkOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MesWorkOrderRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得生产工单列表")
    @PreAuthorize("@ss.hasPermission('mes:work-order:query')")
    public CommonResult<List<MesWorkOrderRespVO>> getWorkOrderList(@Valid MesWorkOrderPageReqVO reqVO) {
        List<MesWorkOrderDO> list = workOrderService.getWorkOrderList(reqVO);
        return success(BeanUtils.toBean(list, MesWorkOrderRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出生产工单 Excel")
    @PreAuthorize("@ss.hasPermission('mes:work-order:export')")
    public void exportWorkOrderExcel(@Valid MesWorkOrderPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MesWorkOrderDO> list = workOrderService.getWorkOrderList(pageReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "生产工单.xls", "数据", MesWorkOrderRespVO.class,
                BeanUtils.toBean(list, MesWorkOrderRespVO.class));
    }

    @PutMapping("/release")
    @Operation(summary = "下发工单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:work-order:operate')")
    public CommonResult<Boolean> releaseWorkOrder(@RequestParam("id") Long id) {
        workOrderService.releaseWorkOrder(id);
        return success(true);
    }

    @PutMapping("/start")
    @Operation(summary = "开始生产")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:work-order:operate')")
    public CommonResult<Boolean> startWorkOrder(@RequestParam("id") Long id) {
        workOrderService.startWorkOrder(id);
        return success(true);
    }

    @PutMapping("/complete")
    @Operation(summary = "完成工单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:work-order:operate')")
    public CommonResult<Boolean> completeWorkOrder(@RequestParam("id") Long id) {
        workOrderService.completeWorkOrder(id);
        return success(true);
    }

    @PutMapping("/close")
    @Operation(summary = "关闭工单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:work-order:operate')")
    public CommonResult<Boolean> closeWorkOrder(@RequestParam("id") Long id) {
        workOrderService.closeWorkOrder(id);
        return success(true);
    }

}