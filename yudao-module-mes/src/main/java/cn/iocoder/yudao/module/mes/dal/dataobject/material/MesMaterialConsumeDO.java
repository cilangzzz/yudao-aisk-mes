package cn.iocoder.yudao.module.mes.dal.dataobject.material;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * MES 物料消耗记录 DO
 *
 * @author 芋道源码
 */
@TableName("mes_material_consume")
@KeySequence("mes_material_consume_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MesMaterialConsumeDO extends TenantBaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 车辆VIN
     */
    private String vin;

    /**
     * 工单ID
     */
    private Long workOrderId;

    /**
     * 物料编码
     */
    private String materialCode;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 消耗数量
     */
    private BigDecimal qty;

    /**
     * 单位
     */
    private String unit;

    /**
     * 消耗工位ID
     */
    private Long workstationId;

    /**
     * 操作员ID
     */
    private Long operatorId;

    /**
     * 操作员姓名
     */
    private String operatorName;

    /**
     * 消耗时间
     */
    private LocalDateTime consumeTime;

    /**
     * 备注
     */
    private String remark;

}