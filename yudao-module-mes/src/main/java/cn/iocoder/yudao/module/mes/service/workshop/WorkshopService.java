package cn.iocoder.yudao.module.mes.service.workshop;

import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.mes.controller.admin.workshop.vo.WorkshopListReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.workshop.vo.WorkshopSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.workshop.WorkshopDO;

import java.util.*;

/**
 * 车间 Service 接口
 *
 * @author 芋道源码
 */
public interface WorkshopService {

    /**
     * 创建车间
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWorkshop(WorkshopSaveReqVO createReqVO);

    /**
     * 更新车间
     *
     * @param updateReqVO 更新信息
     */
    void updateWorkshop(WorkshopSaveReqVO updateReqVO);

    /**
     * 删除车间
     *
     * @param id 编号
     */
    void deleteWorkshop(Long id);

    /**
     * 获得车间
     *
     * @param id 编号
     * @return 车间
     */
    WorkshopDO getWorkshop(Long id);

    /**
     * 获得车间列表
     *
     * @param ids 编号列表
     * @return 车间列表
     */
    List<WorkshopDO> getWorkshopList(Collection<Long> ids);

    /**
     * 获得车间列表
     *
     * @param reqVO 查询条件
     * @return 车间列表
     */
    List<WorkshopDO> getWorkshopList(WorkshopListReqVO reqVO);

    /**
     * 获得指定编号的部门 Map
     *
     * @param ids 部门编号数组
     * @return 部门 Map
     */
    default Map<Long, WorkshopDO> getWorkshopMap(Collection<Long> ids) {
        List<WorkshopDO> list = getWorkshopList(ids);
        return CollectionUtils.convertMap(list, WorkshopDO::getId);
    }

    /**
     * 获得指定车间的所有子车间
     *
     * @param id 车间编号
     * @return 子车间列表
     */
    default List<WorkshopDO> getChildWorkshopList(Long id) {
        return getChildWorkshopList(Collections.singleton(id));
    }

    /**
     * 获得指定车间的所有子车间
     *
     * @param ids 车间编号数组
     * @return 子车间列表
     */
    List<WorkshopDO> getChildWorkshopList(Collection<Long> ids);

    /**
     * 获得所有子车间，从缓存中
     *
     * @param id 父车间编号
     * @return 子车间编号列表
     */
    Set<Long> getChildWorkshopIdListFromCache(Long id);

    /**
     * 校验车间是否存在
     *
     * @param id 编号
     * @return 车间
     */
    WorkshopDO validateWorkshopExists(Long id);

    /**
     * 校验车间们是否有效。如下情况，视为无效：
     * 1. 车间编号不存在
     * 2. 车间被禁用
     *
     * @param ids 车间编号数组
     */
    void validateWorkshopList(Collection<Long> ids);

}