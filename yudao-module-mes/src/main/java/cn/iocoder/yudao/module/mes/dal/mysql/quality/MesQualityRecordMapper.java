package cn.iocoder.yudao.module.mes.dal.mysql.quality;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesQualityRecordPageReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.quality.MesQualityRecordDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 质量检验记录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MesQualityRecordMapper extends BaseMapperX<MesQualityRecordDO> {

    default PageResult<MesQualityRecordDO> selectPage(MesQualityRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MesQualityRecordDO>()
                .eqIfPresent(MesQualityRecordDO::getVin, reqVO.getVin())
                .eqIfPresent(MesQualityRecordDO::getWorkOrderId, reqVO.getWorkOrderId())
                .eqIfPresent(MesQualityRecordDO::getCheckType, reqVO.getCheckType())
                .eqIfPresent(MesQualityRecordDO::getResult, reqVO.getResult())
                .likeIfPresent(MesQualityRecordDO::getCheckItemName, reqVO.getCheckItemName())
                .betweenIfPresent(MesQualityRecordDO::getCheckTime, reqVO.getCheckTime())
                .betweenIfPresent(MesQualityRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MesQualityRecordDO::getId));
    }

    default List<MesQualityRecordDO> selectListByVin(String vin) {
        return selectList(new LambdaQueryWrapperX<MesQualityRecordDO>()
                .eq(MesQualityRecordDO::getVin, vin)
                .orderByDesc(MesQualityRecordDO::getCheckTime));
    }

    default List<MesQualityRecordDO> selectListByWorkOrderId(Long workOrderId) {
        return selectList(new LambdaQueryWrapperX<MesQualityRecordDO>()
                .eq(MesQualityRecordDO::getWorkOrderId, workOrderId)
                .orderByDesc(MesQualityRecordDO::getCheckTime));
    }

    default MesQualityRecordDO selectByVinAndCheckItemCode(String vin, String checkItemCode) {
        return selectOne(new LambdaQueryWrapperX<MesQualityRecordDO>()
                .eq(MesQualityRecordDO::getVin, vin)
                .eq(MesQualityRecordDO::getCheckItemCode, checkItemCode)
                .orderByDesc(MesQualityRecordDO::getCheckTime)
                .last("LIMIT 1"));
    }

}