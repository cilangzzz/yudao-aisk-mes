package cn.iocoder.yudao.module.mes.dal.dataobject.team;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 班组表
 *
 * @author 芋道源码
 */
@TableName("mes_team")
@KeySequence("mes_team_seq")
@Data
@EqualsAndHashCode(callSuper = true)
public class TeamDO extends TenantBaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 班组编码
     */
    private String teamCode;
    /**
     * 班组名称
     */
    private String teamName;
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
     * 班组长用户ID
     */
    private Long leaderId;
    /**
     * 班组长姓名
     */
    private String leaderName;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

}