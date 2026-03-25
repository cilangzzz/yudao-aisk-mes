package cn.iocoder.yudao.module.mes.dal.mysql.productbom;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.controller.admin.productbom.vo.ProductBomPageReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.productbom.ProductBomDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 产品BOM Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductBomMapper extends BaseMapperX<ProductBomDO> {

    default PageResult<ProductBomDO> selectPage(ProductBomPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductBomDO>()
                .eqIfPresent(ProductBomDO::getProductId, reqVO.getProductId())
                .likeIfPresent(ProductBomDO::getMaterialCode, reqVO.getMaterialCode())
                .likeIfPresent(ProductBomDO::getMaterialName, reqVO.getMaterialName())
                .orderByDesc(ProductBomDO::getId));
    }

    default List<ProductBomDO> selectList(ProductBomPageReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductBomDO>()
                .eqIfPresent(ProductBomDO::getProductId, reqVO.getProductId())
                .likeIfPresent(ProductBomDO::getMaterialCode, reqVO.getMaterialCode())
                .likeIfPresent(ProductBomDO::getMaterialName, reqVO.getMaterialName())
                .orderByDesc(ProductBomDO::getId));
    }

    default ProductBomDO selectByProductIdAndMaterialCode(Long productId, String materialCode) {
        return selectOne(ProductBomDO::getProductId, productId, ProductBomDO::getMaterialCode, materialCode);
    }

    default List<ProductBomDO> selectListByProductId(Long productId) {
        return selectList(ProductBomDO::getProductId, productId);
    }

    default Long selectCountByProductId(Long productId) {
        return selectCount(ProductBomDO::getProductId, productId);
    }

}