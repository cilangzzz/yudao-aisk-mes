package cn.iocoder.yudao.module.mes.dal.dataobject.routing;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * MES 工序物料 DO
 *
 * @author 芋道源码
 */
@TableName("mes_operation_material")
@KeySequence("mes_operation_material_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MesOperationMaterialDO extends TenantBaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 工序ID
     *
     * 关联 {@link MesOperationDO#getId()}
     */
    private Long operationId;

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
     * 是否关键件: 0-否, 1-是
     */
    private Integer keyPart;

}