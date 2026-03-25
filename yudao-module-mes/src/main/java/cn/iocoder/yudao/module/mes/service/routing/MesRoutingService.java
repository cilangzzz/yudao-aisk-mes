package cn.iocoder.yudao.module.mes.service.routing;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mes.controller.admin.routing.vo.MesRoutingPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.routing.vo.MesRoutingSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.routing.MesRoutingDO;

import javax.validation.Valid;
import java.util.List;

/**
 * MES 工艺路线 Service 接口
 *
 * @author 芋道源码
 */
public interface MesRoutingService {

    /**
     * 创建工艺路线
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRouting(@Valid MesRoutingSaveReqVO createReqVO);

    /**
     * 更新工艺路线
     *
     * @param updateReqVO 更新信息
     */
    void updateRouting(@Valid MesRoutingSaveReqVO updateReqVO);

    /**
     * 删除工艺路线
     *
     * @param id 编号
     */
    void deleteRouting(Long id);

    /**
     * 获得工艺路线
     *
     * @param id 编号
     * @return 工艺路线（包含工序列表）
     */
    MesRoutingDO getRouting(Long id);

    /**
     * 获得工艺路线分页
     *
     * @param pageReqVO 分页查询
     * @return 工艺路线分页
     */
    PageResult<MesRoutingDO> getRoutingPage(MesRoutingPageReqVO pageReqVO);

    /**
     * 生效工艺路线
     *
     * @param id 编号
     */
    void activateRouting(Long id);

    /**
     * 失效工艺路线
     *
     * @param id 编号
     */
    void deactivateRouting(Long id);

    /**
     * 复制工艺路线
     *
     * @param id 编号
     * @return 新工艺路线编号
     */
    Long copyRouting(Long id);

    /**
     * 校验工艺路线是否存在
     *
     * @param id 编号
     * @return 工艺路线
     */
    MesRoutingDO validateRoutingExists(Long id);

    /**
     * 获得产品的生效工艺路线
     *
     * @param productId 产品ID
     * @return 工艺路线
     */
    MesRoutingDO getActiveRoutingByProductId(Long productId);

}