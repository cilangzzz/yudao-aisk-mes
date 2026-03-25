package cn.iocoder.yudao.module.mes.dal.dataobject.workorder;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 生产工单 DO
 *
 * @author 芋道源码
 */
@TableName("mes_work_order")
@KeySequence("mes_work_order_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MesWorkOrderDO extends TenantBaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 工单编号(WO+年月日+4位流水)
     */
    private String orderNo;
    /**
     * ERP订单编号
     */
    private String erpOrderNo;
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 产品编码
     */
    private String productCode;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 计划数量
     */
    private Integer planQty;
    /**
     * 实际数量
     */
    private Integer actualQty;
    /**
     * 工艺路线ID
     */
    private Long routingId;
    /**
     * 工艺路线名称
     */
    private String routingName;
    /**
     * 状态
     *
     * 枚举 {@link cn.iocoder.yudao.module.mes.enums.WorkOrderStatusEnum}
     */
    private Integer status;
    /**
     * 优先级(1-10)
     */
    private Integer priority;
    /**
     * 计划开始时间
     */
    private LocalDateTime planStartTime;
    /**
     * 计划结束时间
     */
    private LocalDateTime planEndTime;
    /**
     * 实际开始时间
     */
    private LocalDateTime actualStartTime;
    /**
     * 实际结束时间
     */
    private LocalDateTime actualEndTime;
    /**
     * 车间ID
     */
    private Long workshopId;
    /**
     * 车间名称
     */
    private String workshopName;
    /**
     * 产线ID
     */
    private Long lineId;
    /**
     * 产线名称
     */
    private String lineName;
    /**
     * 备注
     */
    private String remark;

}