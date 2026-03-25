package cn.iocoder.yudao.module.mes.service.product;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.product.vo.ProductPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.product.vo.ProductSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.product.ProductDO;
import cn.iocoder.yudao.module.mes.dal.mysql.product.ProductMapper;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.*;

/**
 * 产品 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public Long createProduct(ProductSaveReqVO createReqVO) {
        // 1. 校验编码唯一
        validateProductCodeUnique(null, createReqVO.getProductCode());
        // 2. 校验名称唯一
        validateProductNameUnique(null, createReqVO.getProductName());
        // 3. 插入数据
        ProductDO product = BeanUtils.toBean(createReqVO, ProductDO.class);
        if (product.getStatus() == null) {
            product.setStatus(CommonStatusEnum.ENABLE.getStatus());
        }
        productMapper.insert(product);
        return product.getId();
    }

    @Override
    public void updateProduct(ProductSaveReqVO updateReqVO) {
        // 1. 校验存在
        validateProductExists(updateReqVO.getId());
        // 2. 校验编码唯一
        validateProductCodeUnique(updateReqVO.getId(), updateReqVO.getProductCode());
        // 3. 校验名称唯一
        validateProductNameUnique(updateReqVO.getId(), updateReqVO.getProductName());
        // 4. 更新数据
        ProductDO updateObj = BeanUtils.toBean(updateReqVO, ProductDO.class);
        productMapper.updateById(updateObj);
    }

    @Override
    public void deleteProduct(Long id) {
        // 1. 校验存在
        validateProductExists(id);
        // 2. 删除
        productMapper.deleteById(id);
    }

    @Override
    public ProductDO getProduct(Long id) {
        return productMapper.selectById(id);
    }

    @Override
    public List<ProductDO> getProductList(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return productMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProductDO> getProductPage(ProductPageReqVO pageReqVO) {
        return productMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductDO> getProductList(ProductPageReqVO reqVO) {
        return productMapper.selectList(reqVO);
    }

    @Override
    public ProductDO validateProductExists(Long id) {
        if (id == null) {
            return null;
        }
        ProductDO product = productMapper.selectById(id);
        if (product == null) {
            throw exception(PRODUCT_NOT_EXISTS);
        }
        return product;
    }

    @VisibleForTesting
    void validateProductCodeUnique(Long id, String productCode) {
        if (productCode == null) {
            return;
        }
        ProductDO product = productMapper.selectByProductCode(productCode);
        if (product == null) {
            return;
        }
        if (id == null) {
            throw exception(PRODUCT_CODE_DUPLICATE);
        }
        if (!product.getId().equals(id)) {
            throw exception(PRODUCT_CODE_DUPLICATE);
        }
    }

    @VisibleForTesting
    void validateProductNameUnique(Long id, String productName) {
        if (productName == null) {
            return;
        }
        ProductDO product = productMapper.selectByProductName(productName);
        if (product == null) {
            return;
        }
        if (id == null) {
            throw exception(PRODUCT_NAME_DUPLICATE);
        }
        if (!product.getId().equals(id)) {
            throw exception(PRODUCT_NAME_DUPLICATE);
        }
    }

}