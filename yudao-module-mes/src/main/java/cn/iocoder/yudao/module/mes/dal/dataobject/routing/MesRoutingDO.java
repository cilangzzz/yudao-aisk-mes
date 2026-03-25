package cn.iocoder.yudao.module.mes.dal.dataobject.routing;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import cn.iocoder.yudao.module.mes.enums.RoutingStatusEnum;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.List;

/**
 * MES 工艺路线 DO
 *
 * @author 芋道源码
 */
@TableName("mes_routing")
@KeySequence("mes_routing_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MesRoutingDO extends TenantBaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 工艺路线编码
     */
    private String routingCode;

    /**
     * 工艺路线名称
     */
    private String routingName;

    /**
     * 关联产品ID
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
     * 版本号
     */
    private String version;

    /**
     * 状态: 0-草稿, 1-生效, 2-失效
     *
     * 枚举 {@link RoutingStatusEnum}
     */
    private Integer status;

    /**
     * 描述
     */
    private String description;

    // ========== 关联字段 ==========

    /**
     * 工序列表
     *
     * 非数据库字段，用于存储关联的工序数据
     */
    @TableField(exist = false)
    private List<MesOperationDO> operations;

}