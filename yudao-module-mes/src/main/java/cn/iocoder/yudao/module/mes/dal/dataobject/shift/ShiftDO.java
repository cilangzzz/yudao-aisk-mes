package cn.iocoder.yudao.module.mes.dal.dataobject.shift;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalTime;

/**
 * 班次表
 *
 * @author 芋道源码
 */
@TableName("mes_shift")
@KeySequence("mes_shift_seq")
@Data
@EqualsAndHashCode(callSuper = true)
public class ShiftDO extends TenantBaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 班次编码
     */
    private String shiftCode;
    /**
     * 班次名称
     */
    private String shiftName;
    /**
     * 开始时间
     */
    private LocalTime startTime;
    /**
     * 结束时间
     */
    private LocalTime endTime;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

}