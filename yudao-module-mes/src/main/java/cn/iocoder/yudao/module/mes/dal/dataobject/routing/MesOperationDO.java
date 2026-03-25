package cn.iocoder.yudao.module.mes.dal.dataobject.routing;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * MES 工序定义 DO
 *
 * @author 芋道源码
 */
@TableName("mes_operation")
@KeySequence("mes_operation_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MesOperationDO extends TenantBaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 工艺路线ID
     *
     * 关联 {@link MesRoutingDO#getId()}
     */
    private Long routingId;

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
    private Integer sequence;

    /**
     * 默认工作站ID
     */
    private Long workstationId;

    /**
     * 工作站名称
     */
    private String workstationName;

    /**
     * 标准工时(分钟)
     */
    private BigDecimal standardTime;

    /**
     * 工序描述
     */
    private String description;

    /**
     * 是否关键工序: 0-否, 1-是
     */
    private Integer keyOperation;

    /**
     * 是否需要质检: 0-否, 1-是
     */
    private Integer qualityCheck;

    /**
     * 作业指导内容
     */
    private String instruction;

    /**
     * 作业指导文件URL
     */
    private String instructionFile;

    // ========== 关联字段 ==========

    /**
     * 物料列表
     *
     * 非数据库字段，用于存储关联的物料数据
     */
    @TableField(exist = false)
    private List<MesOperationMaterialDO> materials;

}