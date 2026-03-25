package cn.iocoder.yudao.module.mes.service.workstation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.mes.controller.admin.workstation.vo.WorkstationPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.workstation.vo.WorkstationSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.workstation.WorkstationDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 工作站 Service 接口
 *
 * @author 芋道源码
 */
public interface WorkstationService {

    /**
     * 创建工作站
     */
    Long createWorkstation(@Valid WorkstationSaveReqVO createReqVO);

    /**
     * 更新工作站
     */
    void updateWorkstation(@Valid WorkstationSaveReqVO updateReqVO);

    /**
     * 删除工作站
     */
    void deleteWorkstation(Long id);

    /**
     * 获得工作站
     */
    WorkstationDO getWorkstation(Long id);

    /**
     * 获得工作站列表
     */
    List<WorkstationDO> getWorkstationList(Collection<Long> ids);

    /**
     * 获得工作站分页
     */
    PageResult<WorkstationDO> getWorkstationPage(WorkstationPageReqVO pageReqVO);

    /**
     * 获得工作站列表
     */
    List<WorkstationDO> getWorkstationList(WorkstationPageReqVO reqVO);

    /**
     * 获得指定编号的工作站 Map
     */
    default Map<Long, WorkstationDO> getWorkstationMap(Collection<Long> ids) {
        List<WorkstationDO> list = getWorkstationList(ids);
        return CollectionUtils.convertMap(list, WorkstationDO::getId);
    }

    /**
     * 获得产线下的工作站列表
     */
    List<WorkstationDO> getWorkstationListByLineId(Long lineId);

    /**
     * 校验工作站是否存在
     */
    WorkstationDO validateWorkstationExists(Long id);

    /**
     * 校验工作站们是否有效
     */
    void validateWorkstationList(Collection<Long> ids);

    /**
     * 启用工作站
     */
    void enableWorkstation(Long id);

    /**
     * 停用工作站
     */
    void disableWorkstation(Long id);

    /**
     * 绑定设备
     */
    void bindEquipment(Long id, List<Long> equipmentIds);

}