package cn.iocoder.yudao.module.mes.service.offline;

/**
 * MES 离线作业 Service 接口
 *
 * @author 芋道源码
 */
public interface MesOfflineService {

    /**
     * 缓存类型枚举
     */
    interface CacheType {
        int OPERATION_RECORD = 1;  // 作业记录
        int KEY_PART_BIND = 2;     // 关键件绑定
        int EXCEPTION_REPORT = 3;  // 异常上报
    }

    /**
     * 同步状态枚举
     */
    interface SyncStatus {
        int PENDING = 0;   // 待同步
        int SYNCED = 1;    // 已同步
        int FAILED = 2;    // 同步失败
    }

    /**
     * 保存离线缓存
     *
     * @param userId    用户ID
     * @param cacheType 缓存类型
     * @param cacheData 缓存数据 (JSON字符串)
     * @return 缓存记录ID
     */
    Long saveOfflineCache(Long userId, Integer cacheType, String cacheData);

    /**
     * 同步离线数据
     *
     * @param userId 用户ID
     * @return 同步成功数量
     */
    int syncOfflineData(Long userId);

    /**
     * 获取用户待同步数据数量
     *
     * @param userId 用户ID
     * @return 待同步数量
     */
    Long getPendingCount(Long userId);

    /**
     * 重试失败的同步
     *
     * @param userId 用户ID
     * @return 同步成功数量
     */
    int retryFailedSync(Long userId);

}