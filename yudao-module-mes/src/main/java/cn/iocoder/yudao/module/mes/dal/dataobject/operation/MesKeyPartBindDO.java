package cn.iocoder.yudao.module.mes.dal.dataobject.operation;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 关键件绑定 DO
 *
 * @author 芋道源码
 */
@TableName("mes_key_part_bind")
@KeySequence("mes_key_part_bind_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MesKeyPartBindDO extends TenantBaseDO {

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
     * 作业记录ID
     */
    private Long operationRecordId;
    /**
     * 车辆VIN码
     */
    private String vin;
    /**
     * 零部件编码
     */
    private String partCode;
    /**
     * 零部件名称
     */
    private String partName;
    /**
     * 零部件序列号
     */
    private String partSn;
    /**
     * 供应商编码
     */
    private String supplierCode;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * 绑定时间
     */
    private LocalDateTime bindTime;
    /**
     * 绑定工位ID
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
     * 备注
     */
    private String remark;

}