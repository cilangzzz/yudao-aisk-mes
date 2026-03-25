package cn.iocoder.yudao.module.mes.controller.admin.productbom;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.productbom.vo.ProductBomPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.productbom.vo.ProductBomRespVO;
import cn.iocoder.yudao.module.mes.controller.admin.productbom.vo.ProductBomSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.productbom.ProductBomDO;
import cn.iocoder.yudao.module.mes.service.productbom.ProductBomService;
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

@Tag(name = "管理后台 - 产品BOM")
@RestController
@RequestMapping("/mes/product-bom")
@Validated
public class ProductBomController {

    @Resource
    private ProductBomService productBomService;

    @PostMapping("/create")
    @Operation(summary = "创建产品BOM")
    @PreAuthorize("@ss.hasPermission('mes:product-bom:create')")
    public CommonResult<Long> createProductBom(@Valid @RequestBody ProductBomSaveReqVO createReqVO) {
        return success(productBomService.createProductBom(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品BOM")
    @PreAuthorize("@ss.hasPermission('mes:product-bom:update')")
    public CommonResult<Boolean> updateProductBom(@Valid @RequestBody ProductBomSaveReqVO updateReqVO) {
        productBomService.updateProductBom(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品BOM")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:product-bom:delete')")
    public CommonResult<Boolean> deleteProductBom(@RequestParam("id") Long id) {
        productBomService.deleteProductBom(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品BOM")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:product-bom:query')")
    public CommonResult<ProductBomRespVO> getProductBom(@RequestParam("id") Long id) {
        ProductBomDO productBom = productBomService.getProductBom(id);
        return success(BeanUtils.toBean(productBom, ProductBomRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品BOM分页")
    @PreAuthorize("@ss.hasPermission('mes:product-bom:query')")
    public CommonResult<PageResult<ProductBomRespVO>> getProductBomPage(@Valid ProductBomPageReqVO pageReqVO) {
        PageResult<ProductBomDO> pageResult = productBomService.getProductBomPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ProductBomRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品BOM列表")
    @PreAuthorize("@ss.hasPermission('mes:product-bom:query')")
    public CommonResult<List<ProductBomRespVO>> getProductBomList(@Valid ProductBomPageReqVO reqVO) {
        List<ProductBomDO> list = productBomService.getProductBomList(reqVO);
        return success(BeanUtils.toBean(list, ProductBomRespVO.class));
    }

    @GetMapping("/list-by-product")
    @Operation(summary = "获得产品下的BOM列表")
    @Parameter(name = "productId", description = "产品编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:product-bom:query')")
    public CommonResult<List<ProductBomRespVO>> getProductBomListByProduct(@RequestParam("productId") Long productId) {
        List<ProductBomDO> list = productBomService.getProductBomListByProductId(productId);
        return success(BeanUtils.toBean(list, ProductBomRespVO.class));
    }

}