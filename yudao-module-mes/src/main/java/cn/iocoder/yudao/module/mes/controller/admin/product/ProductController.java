package cn.iocoder.yudao.module.mes.controller.admin.product;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.mes.controller.admin.product.vo.ProductPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.product.vo.ProductRespVO;
import cn.iocoder.yudao.module.mes.controller.admin.product.vo.ProductSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.product.ProductDO;
import cn.iocoder.yudao.module.mes.service.product.ProductService;
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

@Tag(name = "管理后台 - 产品")
@RestController
@RequestMapping("/mes/product")
@Validated
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping("/create")
    @Operation(summary = "创建产品")
    @PreAuthorize("@ss.hasPermission('mes:product:create')")
    public CommonResult<Long> createProduct(@Valid @RequestBody ProductSaveReqVO createReqVO) {
        return success(productService.createProduct(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品")
    @PreAuthorize("@ss.hasPermission('mes:product:update')")
    public CommonResult<Boolean> updateProduct(@Valid @RequestBody ProductSaveReqVO updateReqVO) {
        productService.updateProduct(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:product:delete')")
    public CommonResult<Boolean> deleteProduct(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:product:query')")
    public CommonResult<ProductRespVO> getProduct(@RequestParam("id") Long id) {
        ProductDO product = productService.getProduct(id);
        return success(BeanUtils.toBean(product, ProductRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品分页")
    @PreAuthorize("@ss.hasPermission('mes:product:query')")
    public CommonResult<PageResult<ProductRespVO>> getProductPage(@Valid ProductPageReqVO pageReqVO) {
        PageResult<ProductDO> pageResult = productService.getProductPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ProductRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品列表")
    @PreAuthorize("@ss.hasPermission('mes:product:query')")
    public CommonResult<List<ProductRespVO>> getProductList(@Valid ProductPageReqVO reqVO) {
        List<ProductDO> list = productService.getProductList(reqVO);
        return success(BeanUtils.toBean(list, ProductRespVO.class));
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获得产品精简信息列表", description = "只包含被开启的产品，主要用于前端的下拉选项")
    public CommonResult<List<ProductRespVO>> getSimpleProductList() {
        List<ProductDO> list = productService.getProductList(
                new ProductPageReqVO().setStatus(CommonStatusEnum.ENABLE.getStatus()));
        return success(BeanUtils.toBean(list, ProductRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品 Excel")
    @PreAuthorize("@ss.hasPermission('mes:product:export')")
    public void exportProductExcel(@Valid ProductPageReqVO pageReqVO,
                                    HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageResult.PAGE_SIZE_NONE);
        List<ProductDO> list = productService.getProductList(pageReqVO);
        ExcelUtils.write(response, "产品.xls", "数据", ProductRespVO.class,
                BeanUtils.toBean(list, ProductRespVO.class));
    }

}