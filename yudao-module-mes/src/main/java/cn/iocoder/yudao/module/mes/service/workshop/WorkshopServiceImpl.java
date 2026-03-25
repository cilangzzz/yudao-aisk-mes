package cn.iocoder.yudao.module.mes.service.workshop;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.workshop.vo.WorkshopListReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.workshop.vo.WorkshopSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.workshop.WorkshopDO;
import cn.iocoder.yudao.module.mes.dal.mysql.workshop.WorkshopMapper;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.*;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.*;

/**
 * 车间 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class WorkshopServiceImpl implements WorkshopService {

    @Resource
    private WorkshopMapper workshopMapper;

    @Override
    public Long createWorkshop(WorkshopSaveReqVO createReqVO) {
        // 1. 校验父车间有效性
        validateParentWorkshop(null, createReqVO.getParentId());
        // 2. 校验编码唯一
        validateWorkshopCodeUnique(null, createReqVO.getWorkshopCode());
        // 3. 校验名称唯一
        validateWorkshopNameUnique(null, createReqVO.getWorkshopName());
        // 4. 插入数据
        WorkshopDO workshop = BeanUtils.toBean(createReqVO, WorkshopDO.class);
        // 设置默认父ID
        if (workshop.getParentId() == null) {
            workshop.setParentId(WorkshopDO.PARENT_ID_ROOT);
        }
        // 设置默认状态
        if (workshop.getStatus() == null) {
            workshop.setStatus(CommonStatusEnum.ENABLE.getStatus());
        }
        workshopMapper.insert(workshop);
        return workshop.getId();
    }

    @Override
    public void updateWorkshop(WorkshopSaveReqVO updateReqVO) {
        // 1. 校验存在
        validateWorkshopExists(updateReqVO.getId());
        // 2. 校验父车间有效性
        validateParentWorkshop(updateReqVO.getId(), updateReqVO.getParentId());
        // 3. 校验编码唯一
        validateWorkshopCodeUnique(updateReqVO.getId(), updateReqVO.getWorkshopCode());
        // 4. 校验名称唯一
        validateWorkshopNameUnique(updateReqVO.getId(), updateReqVO.getWorkshopName());
        // 5. 更新数据
        WorkshopDO updateObj = BeanUtils.toBean(updateReqVO, WorkshopDO.class);
        workshopMapper.updateById(updateObj);
    }

    @Override
    public void deleteWorkshop(Long id) {
        // 1. 校验存在
        validateWorkshopExists(id);
        // 2. 校验是否有子车间
        if (workshopMapper.selectCountByParentId(id) > 0) {
            throw exception(WORKSHOP_HAS_CHILDREN);
        }
        // 3. 删除
        workshopMapper.deleteById(id);
    }

    @Override
    public WorkshopDO getWorkshop(Long id) {
        return workshopMapper.selectById(id);
    }

    @Override
    public List<WorkshopDO> getWorkshopList(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return workshopMapper.selectBatchIds(ids);
    }

    @Override
    public List<WorkshopDO> getWorkshopList(WorkshopListReqVO reqVO) {
        return workshopMapper.selectList(reqVO);
    }

    @Override
    public List<WorkshopDO> getChildWorkshopList(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return workshopMapper.selectListByParentId(ids);
    }

    @Override
    public Set<Long> getChildWorkshopIdListFromCache(Long id) {
        List<WorkshopDO> children = getChildWorkshopListRecursive(id);
        return CollectionUtils.convertSet(children, WorkshopDO::getId);
    }

    /**
     * 递归获取所有子车间
     */
    private List<WorkshopDO> getChildWorkshopListRecursive(Long parentId) {
        List<WorkshopDO> result = new ArrayList<>();
        List<WorkshopDO> children = workshopMapper.selectListByParentId(Collections.singleton(parentId));
        result.addAll(children);
        for (WorkshopDO child : children) {
            result.addAll(getChildWorkshopListRecursive(child.getId()));
        }
        return result;
    }

    @Override
    public WorkshopDO validateWorkshopExists(Long id) {
        if (id == null) {
            return null;
        }
        WorkshopDO workshop = workshopMapper.selectById(id);
        if (workshop == null) {
            throw exception(WORKSHOP_NOT_EXISTS);
        }
        return workshop;
    }

    @Override
    public void validateWorkshopList(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        // 获得车间信息
        List<WorkshopDO> workshopList = workshopMapper.selectBatchIds(ids);
        Map<Long, WorkshopDO> workshopMap = CollectionUtils.convertMap(workshopList, WorkshopDO::getId);
        // 校验
        for (Long id : ids) {
            WorkshopDO workshop = workshopMap.get(id);
            if (workshop == null) {
                throw exception(WORKSHOP_NOT_EXISTS);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(workshop.getStatus())) {
                throw exception(WORKSHOP_DISABLED);
            }
        }
    }

    @VisibleForTesting
    void validateParentWorkshop(Long id, Long parentId) {
        if (parentId == null || WorkshopDO.PARENT_ID_ROOT.equals(parentId)) {
            return;
        }
        // 1. 不能设置自己为父车间
        if (parentId.equals(id)) {
            throw exception(WORKSHOP_PARENT_ERROR);
        }
        // 2. 父车间不存在
        WorkshopDO parent = workshopMapper.selectById(parentId);
        if (parent == null) {
            throw exception(WORKSHOP_PARENT_NOT_EXISTS);
        }
        // 3. 父车间被禁用
        if (!CommonStatusEnum.ENABLE.getStatus().equals(parent.getStatus())) {
            throw exception(WORKSHOP_DISABLED);
        }
        // 4. 不能设置子车间为父车间
        Set<Long> childIds = getChildWorkshopIdListFromCache(id);
        if (childIds.contains(parentId)) {
            throw exception(WORKSHOP_PARENT_IS_CHILD);
        }
    }

    @VisibleForTesting
    void validateWorkshopCodeUnique(Long id, String workshopCode) {
        if (workshopCode == null) {
            return;
        }
        WorkshopDO workshop = workshopMapper.selectByWorkshopCode(workshopCode);
        if (workshop == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的车间
        if (id == null) {
            throw exception(WORKSHOP_CODE_DUPLICATE);
        }
        if (!workshop.getId().equals(id)) {
            throw exception(WORKSHOP_CODE_DUPLICATE);
        }
    }

    @VisibleForTesting
    void validateWorkshopNameUnique(Long id, String workshopName) {
        if (workshopName == null) {
            return;
        }
        WorkshopDO workshop = workshopMapper.selectByWorkshopName(workshopName);
        if (workshop == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的车间
        if (id == null) {
            throw exception(WORKSHOP_NAME_DUPLICATE);
        }
        if (!workshop.getId().equals(id)) {
            throw exception(WORKSHOP_NAME_DUPLICATE);
        }
    }

}