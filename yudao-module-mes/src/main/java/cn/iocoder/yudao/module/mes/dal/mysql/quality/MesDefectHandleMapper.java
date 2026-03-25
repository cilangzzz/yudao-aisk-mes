package cn.iocoder.yudao.module.mes.dal.mysql.quality;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesDefectHandlePageReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.quality.MesDefectHandleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 不合格处理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MesDefectHandleMapper extends BaseMapperX<MesDefectHandleDO> {

    default PageResult<MesDefectHandleDO> selectPage(MesDefectHandlePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MesDefectHandleDO>()
                .eqIfPresent(MesDefectHandleDO::getVin, reqVO.getVin())
                .eqIfPresent(MesDefectHandleDO::getQualityRecordId, reqVO.getQualityRecordId())
                .eqIfPresent(MesDefectHandleDO::getHandleType, reqVO.getHandleType())
                .eqIfPresent(MesDefectHandleDO::getHandleStatus, reqVO.getHandleStatus())
                .likeIfPresent(MesDefectHandleDO::getDefectType, reqVO.getDefectType())
                .betweenIfPresent(MesDefectHandleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MesDefectHandleDO::getId));
    }

    default MesDefectHandleDO selectByQualityRecordId(Long qualityRecordId) {
        return selectOne(MesDefectHandleDO::getQualityRecordId, qualityRecordId);
    }

    default List<MesDefectHandleDO> selectListByVin(String vin) {
        return selectList(new LambdaQueryWrapperX<MesDefectHandleDO>()
                .eq(MesDefectHandleDO::getVin, vin)
                .orderByDesc(MesDefectHandleDO::getCreateTime));
    }

    default List<MesDefectHandleDO> selectListByStatus(Integer status) {
        return selectList(MesDefectHandleDO::getHandleStatus, status);
    }

}