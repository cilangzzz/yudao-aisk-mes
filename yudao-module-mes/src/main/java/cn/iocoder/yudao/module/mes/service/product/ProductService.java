package cn.iocoder.yudao.module.mes.service.product;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mes.controller.admin.product.vo.ProductPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.product.vo.ProductSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.product.ProductDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 产品 Service 接口
 *
 * @author 芋道源码
 */
public interface ProductService {

    /**
     * 创建产品
     */
    Long createProduct(@Valid ProductSaveReqVO createReqVO);

    /**
     * 更新产品
     */
    void updateProduct(@Valid ProductSaveReqVO updateReqVO);

    /**
     * 删除产品
     */
    void deleteProduct(Long id);

    /**
     * 获得产品
     */
    ProductDO getProduct(Long id);

    /**
     * 获得产品列表
     */
    List<ProductDO> getProductList(Collection<Long> ids);

    /**
     * 获得产品分页
     */
    PageResult<ProductDO> getProductPage(ProductPageReqVO pageReqVO);

    /**
     * 获得产品列表
     */
    List<ProductDO> getProductList(ProductPageReqVO reqVO);

    /**
     * 校验产品是否存在
     */
    ProductDO validateProductExists(Long id);

}