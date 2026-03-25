package cn.iocoder.yudao.module.mes.dal.dataobject.quality;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 不合格处理 DO
 *
 * @author 芋道源码
 */
@TableName("mes_defect_handle")
@KeySequence("mes_defect_handle_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MesDefectHandleDO extends TenantBaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 质量记录ID
     */
    private Long qualityRecordId;
    /**
     * 车辆VIN
     */
    private String vin;
    /**
     * 缺陷类型
     */
    private String defectType;
    /**
     * 缺陷描述
     */
    private String defectDesc;
    /**
     * 处理方式
     *
     * 枚举 {@link cn.iocoder.yudao.module.mes.enums.HandleTypeEnum}
     */
    private Integer handleType;
    /**
     * 处理状态
     *
     * 枚举 {@link cn.iocoder.yudao.module.mes.enums.DefectHandleStatusEnum}
     */
    private Integer handleStatus;
    /**
     * 处理人ID
     */
    private Long handlerId;
    /**
     * 处理人姓名
     */
    private String handlerName;
    /**
     * 处理时间
     */
    private LocalDateTime handleTime;
    /**
     * 处理结果
     */
    private String handleResult;
    /**
     * 验证人ID
     */
    private Long verifierId;
    /**
     * 验证人姓名
     */
    private String verifierName;
    /**
     * 验证时间
     */
    private LocalDateTime verifyTime;

}