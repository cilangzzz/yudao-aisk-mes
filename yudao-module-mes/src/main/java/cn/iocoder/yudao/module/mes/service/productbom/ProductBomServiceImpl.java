package cn.iocoder.yudao.module.mes.service.productbom;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.productbom.vo.ProductBomPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.productbom.vo.ProductBomSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.product.ProductDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.productbom.ProductBomDO;
import cn.iocoder.yudao.module.mes.dal.mysql.productbom.ProductBomMapper;
import cn.iocoder.yudao.module.mes.service.product.ProductService;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.*;

/**
 * 产品BOM Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProductBomServiceImpl implements ProductBomService {

    @Resource
    private ProductBomMapper productBomMapper;

    @Resource
    private ProductService productService;

    @Override
    public Long createProductBom(ProductBomSaveReqVO createReqVO) {
        // 1. 校验产品存在
        ProductDO product = validateProductExists(createReqVO.getProductId());
        // 2. 校验物料唯一
        validateMaterialUnique(null, createReqVO.getProductId(), createReqVO.getMaterialCode());
        // 3. 校验用量
        validateQty(createReqVO.getQty());
        // 4. 插入数据
        ProductBomDO productBom = BeanUtils.toBean(createReqVO, ProductBomDO.class);
        // 冗余产品编码
        if (product != null) {
            productBom.setProductCode(product.getProductCode());
        }
        // 默认非关键件
        if (productBom.getKeyPart() == null) {
            productBom.setKeyPart(0);
        }
        productBomMapper.insert(productBom);
        return productBom.getId();
    }

    @Override
    public void updateProductBom(ProductBomSaveReqVO updateReqVO) {
        // 1. 校验存在
        validateProductBomExists(updateReqVO.getId());
        // 2. 校验产品存在
        validateProductExists(updateReqVO.getProductId());
        // 3. 校验物料唯一
        validateMaterialUnique(updateReqVO.getId(), updateReqVO.getProductId(), updateReqVO.getMaterialCode());
        // 4. 校验用量
        validateQty(updateReqVO.getQty());
        // 5. 更新数据
        ProductBomDO updateObj = BeanUtils.toBean(updateReqVO, ProductBomDO.class);
        productBomMapper.updateById(updateObj);
    }

    @Override
    public void deleteProductBom(Long id) {
        // 1. 校验存在
        validateProductBomExists(id);
        // 2. 删除
        productBomMapper.deleteById(id);
    }

    @Override
    public ProductBomDO getProductBom(Long id) {
        return productBomMapper.selectById(id);
    }

    @Override
    public PageResult<ProductBomDO> getProductBomPage(ProductBomPageReqVO pageReqVO) {
        return productBomMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductBomDO> getProductBomList(ProductBomPageReqVO reqVO) {
        return productBomMapper.selectList(reqVO);
    }

    @Override
    public List<ProductBomDO> getProductBomListByProductId(Long productId) {
        return productBomMapper.selectListByProductId(productId);
    }

    @Override
    public ProductBomDO validateProductBomExists(Long id) {
        if (id == null) {
            return null;
        }
        ProductBomDO productBom = productBomMapper.selectById(id);
        if (productBom == null) {
            throw exception(PRODUCT_BOM_NOT_EXISTS);
        }
        return productBom;
    }

    private ProductDO validateProductExists(Long productId) {
        if (productId == null) {
            return null;
        }
        ProductDO product = productService.getProduct(productId);
        if (product == null) {
            throw exception(PRODUCT_BOM_PRODUCT_NOT_EXISTS);
        }
        return product;
    }

    @VisibleForTesting
    void validateMaterialUnique(Long id, Long productId, String materialCode) {
        if (materialCode == null) {
            return;
        }
        ProductBomDO productBom = productBomMapper.selectByProductIdAndMaterialCode(productId, materialCode);
        if (productBom == null) {
            return;
        }
        if (id == null) {
            throw exception(PRODUCT_BOM_MATERIAL_DUPLICATE);
        }
        if (!productBom.getId().equals(id)) {
            throw exception(PRODUCT_BOM_MATERIAL_DUPLICATE);
        }
    }

    @VisibleForTesting
    void validateQty(BigDecimal qty) {
        if (qty == null) {
            return;
        }
        if (qty.compareTo(BigDecimal.ZERO) <= 0) {
            throw exception(PRODUCT_BOM_QTY_ERROR);
        }
    }

}