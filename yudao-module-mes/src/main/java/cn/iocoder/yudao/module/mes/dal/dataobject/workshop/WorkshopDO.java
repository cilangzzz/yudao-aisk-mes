package cn.iocoder.yudao.module.mes.dal.dataobject.workshop;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车间表
 *
 * @author 芋道源码
 */
@TableName("mes_workshop")
@KeySequence("mes_workshop_seq")
@Data
@EqualsAndHashCode(callSuper = true)
public class WorkshopDO extends TenantBaseDO {

    /**
     * 根节点父ID
     */
    public static final Long PARENT_ID_ROOT = 0L;

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 车间编码
     */
    private String workshopCode;
    /**
     * 车间名称
     */
    private String workshopName;
    /**
     * 父级ID
     *
     * 关联 {@link #id}
     */
    private Long parentId;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 负责人用户ID
     */
    private Long leaderUserId;
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