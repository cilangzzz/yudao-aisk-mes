package cn.iocoder.yudao.module.mes.controller.admin.quality;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.*;
import cn.iocoder.yudao.module.mes.dal.dataobject.quality.MesDefectHandleDO;
import cn.iocoder.yudao.module.mes.service.quality.MesDefectHandleService;
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

@Tag(name = "管理后台 - 不合格处理")
@RestController
@RequestMapping("/mes/defect")
@Validated
public class MesDefectHandleController {

    @Resource
    private MesDefectHandleService defectHandleService;

    @PostMapping("/create")
    @Operation(summary = "不合格登记")
    @PreAuthorize("@ss.hasPermission('mes:defect:create')")
    public CommonResult<Long> createDefectHandle(@Valid @RequestBody MesDefectHandleCreateReqVO createReqVO) {
        return success(defectHandleService.createDefectHandle(createReqVO));
    }

    @PutMapping("/handle")
    @Operation(summary = "不合格处理")
    @PreAuthorize("@ss.hasPermission('mes:defect:handle')")
    public CommonResult<Boolean> handleDefect(@Valid @RequestBody MesDefectHandleReqVO handleReqVO) {
        defectHandleService.handleDefect(handleReqVO);
        return success(true);
    }

    @PutMapping("/verify")
    @Operation(summary = "不合格验证")
    @PreAuthorize("@ss.hasPermission('mes:defect:verify')")
    public CommonResult<Boolean> verifyDefect(@Valid @RequestBody MesDefectHandleVerifyReqVO verifyReqVO) {
        defectHandleService.verifyDefect(verifyReqVO);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得不合格处理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:defect:query')")
    public CommonResult<MesDefectHandleRespVO> getDefectHandle(@RequestParam("id") Long id) {
        MesDefectHandleDO defectHandle = defectHandleService.getDefectHandle(id);
        return success(BeanUtils.toBean(defectHandle, MesDefectHandleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得不合格处理分页")
    @PreAuthorize("@ss.hasPermission('mes:defect:query')")
    public CommonResult<PageResult<MesDefectHandleRespVO>> getDefectHandlePage(@Valid MesDefectHandlePageReqVO pageReqVO) {
        PageResult<MesDefectHandleDO> pageResult = defectHandleService.getDefectHandlePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MesDefectHandleRespVO.class));
    }

    @GetMapping("/list-by-vin")
    @Operation(summary = "根据VIN获得不合格处理列表")
    @Parameter(name = "vin", description = "车辆VIN", required = true)
    @PreAuthorize("@ss.hasPermission('mes:defect:query')")
    public CommonResult<List<MesDefectHandleRespVO>> getDefectHandleListByVin(@RequestParam("vin") String vin) {
        List<MesDefectHandleDO> list = defectHandleService.getDefectHandleListByVin(vin);
        return success(BeanUtils.toBean(list, MesDefectHandleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出不合格处理 Excel")
    @PreAuthorize("@ss.hasPermission('mes:defect:export')")
    public void exportDefectHandleExcel(@Valid MesDefectHandlePageReqVO pageReqVO,
                                        HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<MesDefectHandleDO> pageResult = defectHandleService.getDefectHandlePage(pageReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "不合格处理.xls", "数据", MesDefectHandleRespVO.class,
                BeanUtils.toBean(pageResult.getList(), MesDefectHandleRespVO.class));
    }

}