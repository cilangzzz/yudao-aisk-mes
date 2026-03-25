package cn.iocoder.yudao.module.mes.dal.dataobject.productbom;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 产品BOM表
 *
 * @author 芋道源码
 */
@TableName("mes_product_bom")
@KeySequence("mes_product_bom_seq")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductBomDO extends TenantBaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 产品ID
     *
     * 关联 {@link cn.iocoder.yudao.module.mes.dal.dataobject.product.ProductDO#getId()}
     */
    private Long productId;
    /**
     * 产品编码
     */
    private String productCode;
    /**
     * 物料编码
     */
    private String materialCode;
    /**
     * 物料名称
     */
    private String materialName;
    /**
     * 用量
     */
    private BigDecimal qty;
    /**
     * 单位
     */
    private String unit;
    /**
     * 是否关键件
     */
    private Integer keyPart;

}