package cn.iocoder.yudao.module.mes.service.workstation;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.workstation.vo.WorkstationPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.workstation.vo.WorkstationSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.productionline.ProductionLineDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.workshop.WorkshopDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.workstation.WorkstationDO;
import cn.iocoder.yudao.module.mes.dal.mysql.workstation.WorkstationMapper;
import cn.iocoder.yudao.module.mes.enums.WorkstationTypeEnum;
import cn.iocoder.yudao.module.mes.service.productionline.ProductionLineService;
import cn.iocoder.yudao.module.mes.service.workshop.WorkshopService;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.*;

/**
 * 工作站 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class WorkstationServiceImpl implements WorkstationService {

    @Resource
    private WorkstationMapper workstationMapper;

    @Resource
    private ProductionLineService productionLineService;

    @Resource
    private WorkshopService workshopService;

    @Override
    public Long createWorkstation(WorkstationSaveReqVO createReqVO) {
        // 1. 校验编码唯一
        validateWorkstationCodeUnique(null, createReqVO.getWorkstationCode());
        // 2. 校验名称唯一
        validateWorkstationNameUnique(null, createReqVO.getWorkstationName());
        // 3. 校验产线存在
        ProductionLineDO productionLine = validateProductionLine(createReqVO.getLineId());
        // 4. 校验车间存在
        WorkshopDO workshop = validateWorkshop(createReqVO.getWorkshopId());
        // 5. 校验工作站类型
        validateWorkstationType(createReqVO.getWorkstationType());
        // 6. 插入数据
        WorkstationDO workstation = BeanUtils.toBean(createReqVO, WorkstationDO.class);
        // 设置默认状态
        if (workstation.getStatus() == null) {
            workstation.setStatus(CommonStatusEnum.ENABLE.getStatus());
        }
        // 冗余字段
        if (productionLine != null) {
            workstation.setLineName(productionLine.getLineName());
        }
        if (workshop != null) {
            workstation.setWorkshopName(workshop.getWorkshopName());
        }
        // 处理设备ID列表
        if (CollUtil.isNotEmpty(createReqVO.getEquipmentIds())) {
            workstation.setEquipmentIds(JsonUtils.toJsonString(createReqVO.getEquipmentIds()));
        }
        workstationMapper.insert(workstation);
        return workstation.getId();
    }

    @Override
    public void updateWorkstation(WorkstationSaveReqVO updateReqVO) {
        // 1. 校验存在
        validateWorkstationExists(updateReqVO.getId());
        // 2. 校验编码唯一
        validateWorkstationCodeUnique(updateReqVO.getId(), updateReqVO.getWorkstationCode());
        // 3. 校验名称唯一
        validateWorkstationNameUnique(updateReqVO.getId(), updateReqVO.getWorkstationName());
        // 4. 校验产线存在
        ProductionLineDO productionLine = validateProductionLine(updateReqVO.getLineId());
        // 5. 校验车间存在
        WorkshopDO workshop = validateWorkshop(updateReqVO.getWorkshopId());
        // 6. 校验工作站类型
        validateWorkstationType(updateReqVO.getWorkstationType());
        // 7. 更新数据
        WorkstationDO updateObj = BeanUtils.toBean(updateReqVO, WorkstationDO.class);
        // 冗余字段
        if (productionLine != null) {
            updateObj.setLineName(productionLine.getLineName());
        }
        if (workshop != null) {
            updateObj.setWorkshopName(workshop.getWorkshopName());
        }
        // 处理设备ID列表
        if (CollUtil.isNotEmpty(updateReqVO.getEquipmentIds())) {
            updateObj.setEquipmentIds(JsonUtils.toJsonString(updateReqVO.getEquipmentIds()));
        }
        workstationMapper.updateById(updateObj);
    }

    @Override
    public void deleteWorkstation(Long id) {
        // 1. 校验存在
        validateWorkstationExists(id);
        // 2. 删除
        workstationMapper.deleteById(id);
    }

    @Override
    public WorkstationDO getWorkstation(Long id) {
        return workstationMapper.selectById(id);
    }

    @Override
    public List<WorkstationDO> getWorkstationList(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return workstationMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<WorkstationDO> getWorkstationPage(WorkstationPageReqVO pageReqVO) {
        return workstationMapper.selectPage(pageReqVO);
    }

    @Override
    public List<WorkstationDO> getWorkstationList(WorkstationPageReqVO reqVO) {
        return workstationMapper.selectList(reqVO);
    }

    @Override
    public List<WorkstationDO> getWorkstationListByLineId(Long lineId) {
        return workstationMapper.selectListByLineId(lineId);
    }

    @Override
    public WorkstationDO validateWorkstationExists(Long id) {
        if (id == null) {
            return null;
        }
        WorkstationDO workstation = workstationMapper.selectById(id);
        if (workstation == null) {
            throw exception(WORKSTATION_NOT_EXISTS);
        }
        return workstation;
    }

    @Override
    public void validateWorkstationList(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        List<WorkstationDO> list = workstationMapper.selectBatchIds(ids);
        for (Long id : ids) {
            WorkstationDO workstation = list.stream().filter(w -> w.getId().equals(id)).findFirst().orElse(null);
            if (workstation == null) {
                throw exception(WORKSTATION_NOT_EXISTS);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(workstation.getStatus())) {
                throw exception(WORKSTATION_DISABLED);
            }
        }
    }

    @Override
    public void enableWorkstation(Long id) {
        // 1. 校验存在
        WorkstationDO workstation = validateWorkstationExists(id);
        // 2. 校验产线和车间状态
        validateRelatedStatus(workstation);
        // 3. 更新状态
        WorkstationDO updateObj = new WorkstationDO();
        updateObj.setId(id);
        updateObj.setStatus(CommonStatusEnum.ENABLE.getStatus());
        workstationMapper.updateById(updateObj);
    }

    @Override
    public void disableWorkstation(Long id) {
        // 1. 校验存在
        validateWorkstationExists(id);
        // 2. 更新状态
        WorkstationDO updateObj = new WorkstationDO();
        updateObj.setId(id);
        updateObj.setStatus(CommonStatusEnum.DISABLE.getStatus());
        workstationMapper.updateById(updateObj);
    }

    @Override
    public void bindEquipment(Long id, List<Long> equipmentIds) {
        // 1. 校验存在
        validateWorkstationExists(id);
        // 2. 更新设备绑定
        WorkstationDO updateObj = new WorkstationDO();
        updateObj.setId(id);
        if (CollUtil.isNotEmpty(equipmentIds)) {
            updateObj.setEquipmentIds(JsonUtils.toJsonString(equipmentIds));
        } else {
            updateObj.setEquipmentIds(null);
        }
        workstationMapper.updateById(updateObj);
    }

    private ProductionLineDO validateProductionLine(Long lineId) {
        if (lineId == null) {
            return null;
        }
        ProductionLineDO productionLine = productionLineService.getProductionLine(lineId);
        if (productionLine == null) {
            throw exception(PRODUCTION_LINE_NOT_EXISTS);
        }
        if (!CommonStatusEnum.ENABLE.getStatus().equals(productionLine.getStatus())) {
            throw exception(WORKSTATION_LINE_DISABLED);
        }
        return productionLine;
    }

    private WorkshopDO validateWorkshop(Long workshopId) {
        if (workshopId == null) {
            return null;
        }
        WorkshopDO workshop = workshopService.getWorkshop(workshopId);
        if (workshop == null) {
            throw exception(WORKSHOP_NOT_EXISTS);
        }
        if (!CommonStatusEnum.ENABLE.getStatus().equals(workshop.getStatus())) {
            throw exception(WORKSTATION_WORKSHOP_DISABLED);
        }
        return workshop;
    }

    private void validateWorkstationType(Integer workstationType) {
        if (workstationType == null) {
            return;
        }
        if (!WorkstationTypeEnum.NORMAL.getType().equals(workstationType)
                && !WorkstationTypeEnum.KEY.getType().equals(workstationType)
                && !WorkstationTypeEnum.INSPECTION.getType().equals(workstationType)) {
            throw exception(WORKSTATION_NOT_EXISTS); // 类型不存在
        }
    }

    private void validateRelatedStatus(WorkstationDO workstation) {
        // 校验产线状态
        if (workstation.getLineId() != null) {
            ProductionLineDO productionLine = productionLineService.getProductionLine(workstation.getLineId());
            if (productionLine != null && !CommonStatusEnum.ENABLE.getStatus().equals(productionLine.getStatus())) {
                throw exception(WORKSTATION_LINE_DISABLED);
            }
        }
        // 校验车间状态
        if (workstation.getWorkshopId() != null) {
            WorkshopDO workshop = workshopService.getWorkshop(workstation.getWorkshopId());
            if (workshop != null && !CommonStatusEnum.ENABLE.getStatus().equals(workshop.getStatus())) {
                throw exception(WORKSTATION_WORKSHOP_DISABLED);
            }
        }
    }

    @VisibleForTesting
    void validateWorkstationCodeUnique(Long id, String workstationCode) {
        if (StrUtil.isBlank(workstationCode)) {
            return;
        }
        WorkstationDO workstation = workstationMapper.selectByWorkstationCode(workstationCode);
        if (workstation == null) {
            return;
        }
        if (id == null) {
            throw exception(WORKSTATION_CODE_DUPLICATE);
        }
        if (!workstation.getId().equals(id)) {
            throw exception(WORKSTATION_CODE_DUPLICATE);
        }
    }

    @VisibleForTesting
    void validateWorkstationNameUnique(Long id, String workstationName) {
        if (StrUtil.isBlank(workstationName)) {
            return;
        }
        WorkstationDO workstation = workstationMapper.selectByWorkstationName(workstationName);
        if (workstation == null) {
            return;
        }
        if (id == null) {
            throw exception(WORKSTATION_NAME_DUPLICATE);
        }
        if (!workstation.getId().equals(id)) {
            throw exception(WORKSTATION_NAME_DUPLICATE);
        }
    }

}