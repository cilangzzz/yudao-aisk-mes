package cn.iocoder.yudao.module.mes.dal.dataobject.operation;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 作业记录 DO
 *
 * @author 芋道源码
 */
@TableName("mes_operation_record")
@KeySequence("mes_operation_record_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MesOperationRecordDO extends TenantBaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 工单ID
     */
    private Long workOrderId;
    /**
     * 工单编号
     */
    private String workOrderNo;
    /**
     * VIN码
     */
    private String vin;
    /**
     * 工序ID
     */
    private Long operationId;
    /**
     * 工序编码
     */
    private String operationCode;
    /**
     * 工序名称
     */
    private String operationName;
    /**
     * 工序顺序
     */
    private Integer operationSeq;
    /**
     * 工作站ID
     */
    private Long workstationId;
    /**
     * 工作站编码
     */
    private String workstationCode;
    /**
     * 工作站名称
     */
    private String workstationName;
    /**
     * 操作员ID
     */
    private Long operatorId;
    /**
     * 操作员姓名
     */
    private String operatorName;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 作业时长(秒)
     */
    private Integer duration;
    /**
     * 状态:0-进行中,1-已完成,2-异常
     */
    private Integer status;
    /**
     * 结果:0-合格,1-不合格
     */
    private Integer result;
    /**
     * 扭矩值(N·m)
     */
    private BigDecimal torqueValue;
    /**
     * 扭矩判定:0-合格,1-不合格
     */
    private Integer torqueResult;
    /**
     * 备注
     */
    private String remark;

}