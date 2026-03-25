package cn.iocoder.yudao.module.mes.dal.mysql.offline;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.dal.dataobject.offline.MesOfflineCacheDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 离线作业缓存 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MesOfflineCacheMapper extends BaseMapperX<MesOfflineCacheDO> {

    /**
     * 查询用户待同步的缓存数据
     */
    default List<MesOfflineCacheDO> selectPendingByUserId(Long userId) {
        return selectList(new LambdaQueryWrapperX<MesOfflineCacheDO>()
                .eq(MesOfflineCacheDO::getUserId, userId)
                .eq(MesOfflineCacheDO::getSyncStatus, 0) // 待同步
                .orderByAsc(MesOfflineCacheDO::getCreateTime));
    }

    /**
     * 查询用户同步失败的缓存数据
     */
    default List<MesOfflineCacheDO> selectFailedByUserId(Long userId) {
        return selectList(new LambdaQueryWrapperX<MesOfflineCacheDO>()
                .eq(MesOfflineCacheDO::getUserId, userId)
                .eq(MesOfflineCacheDO::getSyncStatus, 2) // 同步失败
                .orderByAsc(MesOfflineCacheDO::getCreateTime));
    }

    /**
     * 统计用户待同步数据数量
     */
    default Long selectPendingCountByUserId(Long userId) {
        return selectCount(new LambdaQueryWrapperX<MesOfflineCacheDO>()
                .eq(MesOfflineCacheDO::getUserId, userId)
                .eq(MesOfflineCacheDO::getSyncStatus, 0));
    }

}