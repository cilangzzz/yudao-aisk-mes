package cn.iocoder.yudao.module.mes.controller.admin.shift;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.mes.controller.admin.shift.vo.ShiftPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.shift.vo.ShiftRespVO;
import cn.iocoder.yudao.module.mes.controller.admin.shift.vo.ShiftSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.shift.ShiftDO;
import cn.iocoder.yudao.module.mes.service.shift.ShiftService;
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

@Tag(name = "管理后台 - 班次")
@RestController
@RequestMapping("/mes/shift")
@Validated
public class ShiftController {

    @Resource
    private ShiftService shiftService;

    @PostMapping("/create")
    @Operation(summary = "创建班次")
    @PreAuthorize("@ss.hasPermission('mes:shift:create')")
    public CommonResult<Long> createShift(@Valid @RequestBody ShiftSaveReqVO createReqVO) {
        return success(shiftService.createShift(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新班次")
    @PreAuthorize("@ss.hasPermission('mes:shift:update')")
    public CommonResult<Boolean> updateShift(@Valid @RequestBody ShiftSaveReqVO updateReqVO) {
        shiftService.updateShift(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除班次")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:shift:delete')")
    public CommonResult<Boolean> deleteShift(@RequestParam("id") Long id) {
        shiftService.deleteShift(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得班次")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:shift:query')")
    public CommonResult<ShiftRespVO> getShift(@RequestParam("id") Long id) {
        ShiftDO shift = shiftService.getShift(id);
        return success(BeanUtils.toBean(shift, ShiftRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得班次分页")
    @PreAuthorize("@ss.hasPermission('mes:shift:query')")
    public CommonResult<PageResult<ShiftRespVO>> getShiftPage(@Valid ShiftPageReqVO pageReqVO) {
        PageResult<ShiftDO> pageResult = shiftService.getShiftPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ShiftRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得班次列表")
    @PreAuthorize("@ss.hasPermission('mes:shift:query')")
    public CommonResult<List<ShiftRespVO>> getShiftList(@Valid ShiftPageReqVO reqVO) {
        List<ShiftDO> list = shiftService.getShiftList(reqVO);
        return success(BeanUtils.toBean(list, ShiftRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出班次 Excel")
    @PreAuthorize("@ss.hasPermission('mes:shift:export')")
    public void exportShiftExcel(@Valid ShiftPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageResult.PAGE_SIZE_NONE);
        List<ShiftDO> list = shiftService.getShiftList(pageReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "班次.xls", "数据", ShiftRespVO.class,
                BeanUtils.toBean(list, ShiftRespVO.class));
    }

}