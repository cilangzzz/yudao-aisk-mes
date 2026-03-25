package cn.iocoder.yudao.module.mes.service.workorder;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mes.controller.admin.workorder.vo.MesWorkOrderPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.workorder.vo.MesWorkOrderSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.workorder.MesWorkOrderDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 生产工单 Service 接口
 *
 * @author 芋道源码
 */
public interface MesWorkOrderService {

    /**
     * 创建生产工单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWorkOrder(@Valid MesWorkOrderSaveReqVO createReqVO);

    /**
     * 更新生产工单
     *
     * @param updateReqVO 更新信息
     */
    void updateWorkOrder(@Valid MesWorkOrderSaveReqVO updateReqVO);

    /**
     * 删除生产工单
     *
     * @param id 编号
     */
    void deleteWorkOrder(Long id);

    /**
     * 获得生产工单
     *
     * @param id 编号
     * @return 生产工单
     */
    MesWorkOrderDO getWorkOrder(Long id);

    /**
     * 获得生产工单分页
     *
     * @param pageReqVO 分页查询
     * @return 生产工单分页
     */
    PageResult<MesWorkOrderDO> getWorkOrderPage(MesWorkOrderPageReqVO pageReqVO);

    /**
     * 获得生产工单列表
     *
     * @param pageReqVO 查询条件
     * @return 生产工单列表
     */
    List<MesWorkOrderDO> getWorkOrderList(MesWorkOrderPageReqVO pageReqVO);

    /**
     * 下发工单
     *
     * @param id 编号
     */
    void releaseWorkOrder(Long id);

    /**
     * 开始生产
     *
     * @param id 编号
     */
    void startWorkOrder(Long id);

    /**
     * 完成工单
     *
     * @param id 编号
     */
    void completeWorkOrder(Long id);

    /**
     * 关闭工单
     *
     * @param id 编号
     */
    void closeWorkOrder(Long id);

    /**
     * 校验工单是否存在
     *
     * @param id 编号
     * @return 工单
     */
    MesWorkOrderDO validateWorkOrderExists(Long id);

}