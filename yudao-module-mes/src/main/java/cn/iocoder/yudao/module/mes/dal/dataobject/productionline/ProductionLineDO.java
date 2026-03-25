package cn.iocoder.yudao.module.mes.dal.dataobject.productionline;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 产线表
 *
 * @author 芋道源码
 */
@TableName("mes_production_line")
@KeySequence("mes_production_line_seq")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductionLineDO extends TenantBaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 产线编码
     */
    private String lineCode;
    /**
     * 产线名称
     */
    private String lineName;
    /**
     * 车间ID
     *
     * 关联 {@link cn.iocoder.yudao.module.mes.dal.dataobject.workshop.WorkshopDO#getId()}
     */
    private Long workshopId;
    /**
     * 车间名称（冗余字段）
     */
    private String workshopName;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 描述
     */
    private String description;

}