package cn.iocoder.yudao.module.mes.dal.dataobject.offline;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 离线作业缓存 DO
 *
 * @author 芋道源码
 */
@TableName("mes_offline_cache")
@KeySequence("mes_offline_cache_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MesOfflineCacheDO extends TenantBaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 缓存类型: 1-作业记录, 2-关键件绑定, 3-异常上报
     */
    private Integer cacheType;

    /**
     * 缓存数据 (JSON)
     */
    private String cacheData;

    /**
     * 同步状态: 0-待同步, 1-已同步, 2-同步失败
     */
    private Integer syncStatus;

    /**
     * 同步时间
     */
    private LocalDateTime syncTime;

    /**
     * 同步失败原因
     */
    private String failReason;

}