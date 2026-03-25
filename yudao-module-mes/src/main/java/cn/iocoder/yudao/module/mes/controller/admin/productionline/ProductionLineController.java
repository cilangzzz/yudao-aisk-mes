package cn.iocoder.yudao.module.mes.controller.admin.productionline;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.mes.controller.admin.productionline.vo.ProductionLinePageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.productionline.vo.ProductionLineRespVO;
import cn.iocoder.yudao.module.mes.controller.admin.productionline.vo.ProductionLineSaveReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.productionline.vo.ProductionLineSimpleRespVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.productionline.ProductionLineDO;
import cn.iocoder.yudao.module.mes.service.productionline.ProductionLineService;
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

@Tag(name = "管理后台 - 产线")
@RestController
@RequestMapping("/mes/production-line")
@Validated
public class ProductionLineController {

    @Resource
    private ProductionLineService productionLineService;

    @PostMapping("/create")
    @Operation(summary = "创建产线")
    @PreAuthorize("@ss.hasPermission('mes:production-line:create')")
    public CommonResult<Long> createProductionLine(@Valid @RequestBody ProductionLineSaveReqVO createReqVO) {
        return success(productionLineService.createProductionLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产线")
    @PreAuthorize("@ss.hasPermission('mes:production-line:update')")
    public CommonResult<Boolean> updateProductionLine(@Valid @RequestBody ProductionLineSaveReqVO updateReqVO) {
        productionLineService.updateProductionLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产线")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:production-line:delete')")
    public CommonResult<Boolean> deleteProductionLine(@RequestParam("id") Long id) {
        productionLineService.deleteProductionLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产线")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:production-line:query')")
    public CommonResult<ProductionLineRespVO> getProductionLine(@RequestParam("id") Long id) {
        ProductionLineDO productionLine = productionLineService.getProductionLine(id);
        return success(BeanUtils.toBean(productionLine, ProductionLineRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产线分页")
    @PreAuthorize("@ss.hasPermission('mes:production-line:query')")
    public CommonResult<PageResult<ProductionLineRespVO>> getProductionLinePage(@Valid ProductionLinePageReqVO pageReqVO) {
        PageResult<ProductionLineDO> pageResult = productionLineService.getProductionLinePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ProductionLineRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产线列表")
    @PreAuthorize("@ss.hasPermission('mes:production-line:query')")
    public CommonResult<List<ProductionLineRespVO>> getProductionLineList(@Valid ProductionLinePageReqVO reqVO) {
        List<ProductionLineDO> list = productionLineService.getProductionLineList(reqVO);
        return success(BeanUtils.toBean(list, ProductionLineRespVO.class));
    }

    @GetMapping(value = {"/list-all-simple", "/simple-list"})
    @Operation(summary = "获得产线精简信息列表", description = "只包含被开启的产线，主要用于前端的下拉选项")
    public CommonResult<List<ProductionLineSimpleRespVO>> getSimpleProductionLineList() {
        List<ProductionLineDO> list = productionLineService.getProductionLineList(
                new ProductionLinePageReqVO().setStatus(CommonStatusEnum.ENABLE.getStatus()));
        return success(BeanUtils.toBean(list, ProductionLineSimpleRespVO.class));
    }

    @GetMapping("/list-by-workshop")
    @Operation(summary = "获得车间下的产线列表")
    @Parameter(name = "workshopId", description = "车间编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:production-line:query')")
    public CommonResult<List<ProductionLineSimpleRespVO>> getProductionLineListByWorkshop(@RequestParam("workshopId") Long workshopId) {
        List<ProductionLineDO> list = productionLineService.getProductionLineListByWorkshopId(workshopId);
        return success(BeanUtils.toBean(list, ProductionLineSimpleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产线 Excel")
    @PreAuthorize("@ss.hasPermission('mes:production-line:export')")
    public void exportProductionLineExcel(@Valid ProductionLinePageReqVO pageReqVO,
                                           HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ProductionLineDO> list = productionLineService.getProductionLineList(pageReqVO);
        ExcelUtils.write(response, "产线.xls", "数据", ProductionLineRespVO.class,
                BeanUtils.toBean(list, ProductionLineRespVO.class));
    }

}