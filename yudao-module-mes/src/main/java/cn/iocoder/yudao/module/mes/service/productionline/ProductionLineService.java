package cn.iocoder.yudao.module.mes.service.productionline;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.mes.controller.admin.productionline.vo.ProductionLinePageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.productionline.vo.ProductionLineSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.productionline.ProductionLineDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 产线 Service 接口
 *
 * @author 芋道源码
 */
public interface ProductionLineService {

    /**
     * 创建产线
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProductionLine(@Valid ProductionLineSaveReqVO createReqVO);

    /**
     * 更新产线
     *
     * @param updateReqVO 更新信息
     */
    void updateProductionLine(@Valid ProductionLineSaveReqVO updateReqVO);

    /**
     * 删除产线
     *
     * @param id 编号
     */
    void deleteProductionLine(Long id);

    /**
     * 获得产线
     *
     * @param id 编号
     * @return 产线
     */
    ProductionLineDO getProductionLine(Long id);

    /**
     * 获得产线列表
     *
     * @param ids 编号列表
     * @return 产线列表
     */
    List<ProductionLineDO> getProductionLineList(Collection<Long> ids);

    /**
     * 获得产线分页
     *
     * @param pageReqVO 分页查询
     * @return 产线分页
     */
    PageResult<ProductionLineDO> getProductionLinePage(ProductionLinePageReqVO pageReqVO);

    /**
     * 获得产线列表
     *
     * @param pageReqVO 查询条件
     * @return 产线列表
     */
    List<ProductionLineDO> getProductionLineList(ProductionLinePageReqVO pageReqVO);

    /**
     * 获得指定编号的产线 Map
     *
     * @param ids 产线编号数组
     * @return 产线 Map
     */
    default Map<Long, ProductionLineDO> getProductionLineMap(Collection<Long> ids) {
        List<ProductionLineDO> list = getProductionLineList(ids);
        return CollectionUtils.convertMap(list, ProductionLineDO::getId);
    }

    /**
     * 获得车间下的产线列表
     *
     * @param workshopId 车间编号
     * @return 产线列表
     */
    List<ProductionLineDO> getProductionLineListByWorkshopId(Long workshopId);

    /**
     * 校验产线是否存在
     *
     * @param id 编号
     * @return 产线
     */
    ProductionLineDO validateProductionLineExists(Long id);

    /**
     * 校验产线们是否有效
     *
     * @param ids 产线编号数组
     */
    void validateProductionLineList(Collection<Long> ids);

}