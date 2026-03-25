package cn.iocoder.yudao.module.mes.dal.dataobject.quality;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 质量检验记录 DO
 *
 * @author 芋道源码
 */
@TableName("mes_quality_record")
@KeySequence("mes_quality_record_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MesQualityRecordDO extends TenantBaseDO {

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
     * 检验类型
     *
     * 枚举 {@link cn.iocoder.yudao.module.mes.enums.CheckTypeEnum}
     */
    private Integer checkType;
    /**
     * 检验项目编码
     */
    private String checkItemCode;
    /**
     * 检验项目名称
     */
    private String checkItemName;
    /**
     * 关联工序ID
     */
    private Long operationId;
    /**
     * 工序名称
     */
    private String operationName;
    /**
     * 检验工位ID
     */
    private Long workstationId;
    /**
     * 检验结果
     *
     * 枚举 {@link cn.iocoder.yudao.module.mes.enums.QualityResultEnum}
     */
    private Integer result;
    /**
     * 实际检验值
     */
    private String checkValue;
    /**
     * 标准值
     */
    private String standardValue;
    /**
     * 检验员ID
     */
    private Long inspectorId;
    /**
     * 检验员姓名
     */
    private String inspectorName;
    /**
     * 检验时间
     */
    private LocalDateTime checkTime;
    /**
     * 不合格原因
     */
    private String defectReason;
    /**
     * 备注
     */
    private String remark;

}