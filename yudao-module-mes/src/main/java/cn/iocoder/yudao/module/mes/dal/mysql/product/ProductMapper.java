package cn.iocoder.yudao.module.mes.dal.mysql.product;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.controller.admin.product.vo.ProductPageReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.product.ProductDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * 产品 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductMapper extends BaseMapperX<ProductDO> {

    default PageResult<ProductDO> selectPage(ProductPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductDO>()
                .likeIfPresent(ProductDO::getProductCode, reqVO.getProductCode())
                .likeIfPresent(ProductDO::getProductName, reqVO.getProductName())
                .eqIfPresent(ProductDO::getProductType, reqVO.getProductType())
                .eqIfPresent(ProductDO::getStatus, reqVO.getStatus())
                .orderByDesc(ProductDO::getId));
    }

    default List<ProductDO> selectList(ProductPageReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductDO>()
                .likeIfPresent(ProductDO::getProductCode, reqVO.getProductCode())
                .likeIfPresent(ProductDO::getProductName, reqVO.getProductName())
                .eqIfPresent(ProductDO::getProductType, reqVO.getProductType())
                .eqIfPresent(ProductDO::getStatus, reqVO.getStatus())
                .orderByDesc(ProductDO::getId));
    }

    default ProductDO selectByProductCode(String productCode) {
        return selectOne(ProductDO::getProductCode, productCode);
    }

    default ProductDO selectByProductName(String productName) {
        return selectOne(ProductDO::getProductName, productName);
    }

    default Long selectCountByProductId(Long productId) {
        return selectCount(ProductDO::getId, productId);
    }

}