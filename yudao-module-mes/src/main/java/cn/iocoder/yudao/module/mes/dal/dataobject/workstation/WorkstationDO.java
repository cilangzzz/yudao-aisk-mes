package cn.iocoder.yudao.module.mes.dal.dataobject.workstation;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import cn.iocoder.yudao.module.mes.enums.WorkstationTypeEnum;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 工作站表
 *
 * @author 芋道源码
 */
@TableName("mes_workstation")
@KeySequence("mes_workstation_seq")
@Data
@EqualsAndHashCode(callSuper = true)
public class WorkstationDO extends TenantBaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 工作站编码
     */
    private String workstationCode;
    /**
     * 工作站名称
     */
    private String workstationName;
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
     * 产线ID
     *
     * 关联 {@link cn.iocoder.yudao.module.mes.dal.dataobject.productionline.ProductionLineDO#getId()}
     */
    private Long lineId;
    /**
     * 产线名称（冗余字段）
     */
    private String lineName;
    /**
     * 工作站类型
     *
     * 枚举 {@link WorkstationTypeEnum}
     */
    private Integer workstationType;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 关联设备ID列表（JSON格式）
     */
    private String equipmentIds;
    /**
     * 描述
     */
    private String description;

}