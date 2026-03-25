package cn.iocoder.yudao.module.mes.service.offline;


import cn.iocoder.yudao.module.mes.controller.admin.operation.vo.MesExceptionReportReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.operation.vo.MesKeyPartBindReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.operation.vo.MesOperationCompleteReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.offline.MesOfflineCacheDO;
import cn.iocoder.yudao.module.mes.dal.mysql.offline.MesOfflineCacheMapper;
import cn.iocoder.yudao.module.mes.service.operation.MesOperationService;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.OFFLINE_SYNC_FAILED;

/**
 * MES 离线作业 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class MesOfflineServiceImpl implements MesOfflineService {

    @Resource
    private MesOfflineCacheMapper offlineCacheMapper;
    @Resource
    private MesOperationService operationService;

    @Override
    public Long saveOfflineCache(Long userId, Integer cacheType, String cacheData) {
        MesOfflineCacheDO cache = new MesOfflineCacheDO();
        cache.setUserId(userId);
        cache.setCacheType(cacheType);
        cache.setCacheData(cacheData);
        cache.setSyncStatus(SyncStatus.PENDING);

        offlineCacheMapper.insert(cache);
        log.info("保存离线缓存: userId={}, cacheType={}, cacheId={}", userId, cacheType, cache.getId());

        return cache.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int syncOfflineData(Long userId) {
        List<MesOfflineCacheDO> cacheList = offlineCacheMapper.selectPendingByUserId(userId);
        int successCount = 0;

        for (MesOfflineCacheDO cache : cacheList) {
            try {
                doSync(cache);
                cache.setSyncStatus(SyncStatus.SYNCED);
                cache.setSyncTime(LocalDateTime.now());
                offlineCacheMapper.updateById(cache);
                successCount++;
                log.info("离线数据同步成功: cacheId={}", cache.getId());
            } catch (Exception e) {
                log.error("离线数据同步失败: cacheId={}, error={}", cache.getId(), e.getMessage(), e);
                cache.setSyncStatus(SyncStatus.FAILED);
                cache.setFailReason(e.getMessage());
                offlineCacheMapper.updateById(cache);
            }
        }

        return successCount;
    }

    @Override
    public Long getPendingCount(Long userId) {
        return offlineCacheMapper.selectPendingCountByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int retryFailedSync(Long userId) {
        List<MesOfflineCacheDO> cacheList = offlineCacheMapper.selectFailedByUserId(userId);
        int successCount = 0;

        for (MesOfflineCacheDO cache : cacheList) {
            try {
                doSync(cache);
                cache.setSyncStatus(SyncStatus.SYNCED);
                cache.setSyncTime(LocalDateTime.now());
                cache.setFailReason(null);
                offlineCacheMapper.updateById(cache);
                successCount++;
                log.info("离线数据重试同步成功: cacheId={}", cache.getId());
            } catch (Exception e) {
                log.error("离线数据重试同步失败: cacheId={}, error={}", cache.getId(), e.getMessage(), e);
                cache.setFailReason(e.getMessage());
                offlineCacheMapper.updateById(cache);
            }
        }

        return successCount;
    }

    // ==================== 私有方法 ====================

    private void doSync(MesOfflineCacheDO cache) {
        switch (cache.getCacheType()) {
            case CacheType.OPERATION_RECORD:
                MesOperationCompleteReqVO completeReq = JSON.parseObject(
                        cache.getCacheData(), MesOperationCompleteReqVO.class);
                operationService.completeOperation(completeReq);
                break;

            case CacheType.KEY_PART_BIND:
                MesKeyPartBindReqVO bindReq = JSON.parseObject(
                        cache.getCacheData(), MesKeyPartBindReqVO.class);
                operationService.bindKeyPart(bindReq);
                break;

            case CacheType.EXCEPTION_REPORT:
                MesExceptionReportReqVO exceptionReq = JSON.parseObject(
                        cache.getCacheData(), MesExceptionReportReqVO.class);
                operationService.reportException(exceptionReq);
                break;

            default:
                throw exception(OFFLINE_SYNC_FAILED);
        }
    }

}