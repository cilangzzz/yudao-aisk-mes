package cn.iocoder.yudao.module.mes.dal.dataobject.material;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * MES 线边库存 DO
 *
 * @author 芋道源码
 */
@TableName("mes_line_stock")
@KeySequence("mes_line_stock_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MesLineStockDO extends TenantBaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 物料编码
     */
    private String materialCode;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 工位ID
     */
    private Long workstationId;

    /**
     * 工位名称
     */
    private String workstationName;

    /**
     * 库存数量
     */
    private BigDecimal qty;

    /**
     * 安全库存
     */
    private BigDecimal safetyQty;

    /**
     * 单位
     */
    private String unit;

    /**
     * 最后更新时间
     */
    private LocalDateTime lastUpdateTime;

}