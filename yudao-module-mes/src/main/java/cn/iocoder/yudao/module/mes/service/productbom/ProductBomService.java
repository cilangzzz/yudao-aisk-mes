package cn.iocoder.yudao.module.mes.service.productbom;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mes.controller.admin.productbom.vo.ProductBomPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.productbom.vo.ProductBomSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.productbom.ProductBomDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 产品BOM Service 接口
 *
 * @author 芋道源码
 */
public interface ProductBomService {

    /**
     * 创建产品BOM
     */
    Long createProductBom(@Valid ProductBomSaveReqVO createReqVO);

    /**
     * 更新产品BOM
     */
    void updateProductBom(@Valid ProductBomSaveReqVO updateReqVO);

    /**
     * 删除产品BOM
     */
    void deleteProductBom(Long id);

    /**
     * 获得产品BOM
     */
    ProductBomDO getProductBom(Long id);

    /**
     * 获得产品BOM分页
     */
    PageResult<ProductBomDO> getProductBomPage(ProductBomPageReqVO pageReqVO);

    /**
     * 获得产品BOM列表
     */
    List<ProductBomDO> getProductBomList(ProductBomPageReqVO reqVO);

    /**
     * 获得产品下的BOM列表
     */
    List<ProductBomDO> getProductBomListByProductId(Long productId);

    /**
     * 校验产品BOM是否存在
     */
    ProductBomDO validateProductBomExists(Long id);

}