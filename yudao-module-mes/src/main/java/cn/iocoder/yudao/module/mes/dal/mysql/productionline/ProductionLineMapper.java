package cn.iocoder.yudao.module.mes.dal.mysql.productionline;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.controller.admin.productionline.vo.ProductionLinePageReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.productionline.ProductionLineDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * 产线 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductionLineMapper extends BaseMapperX<ProductionLineDO> {

    default PageResult<ProductionLineDO> selectPage(ProductionLinePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductionLineDO>()
                .likeIfPresent(ProductionLineDO::getLineCode, reqVO.getLineCode())
                .likeIfPresent(ProductionLineDO::getLineName, reqVO.getLineName())
                .eqIfPresent(ProductionLineDO::getWorkshopId, reqVO.getWorkshopId())
                .eqIfPresent(ProductionLineDO::getStatus, reqVO.getStatus())
                .orderByDesc(ProductionLineDO::getId));
    }

    default List<ProductionLineDO> selectList(ProductionLinePageReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductionLineDO>()
                .likeIfPresent(ProductionLineDO::getLineCode, reqVO.getLineCode())
                .likeIfPresent(ProductionLineDO::getLineName, reqVO.getLineName())
                .eqIfPresent(ProductionLineDO::getWorkshopId, reqVO.getWorkshopId())
                .eqIfPresent(ProductionLineDO::getStatus, reqVO.getStatus())
                .orderByDesc(ProductionLineDO::getId));
    }

    default ProductionLineDO selectByLineCode(String lineCode) {
        return selectOne(ProductionLineDO::getLineCode, lineCode);
    }

    default ProductionLineDO selectByLineName(String lineName) {
        return selectOne(ProductionLineDO::getLineName, lineName);
    }

    default List<ProductionLineDO> selectListByWorkshopId(Long workshopId) {
        return selectList(ProductionLineDO::getWorkshopId, workshopId);
    }

    default List<ProductionLineDO> selectListByWorkshopIds(Collection<Long> workshopIds) {
        return selectList(ProductionLineDO::getWorkshopId, workshopIds);
    }

    default Long selectCountByWorkshopId(Long workshopId) {
        return selectCount(ProductionLineDO::getWorkshopId, workshopId);
    }

}