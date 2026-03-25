package cn.iocoder.yudao.module.mes.service.routing;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.routing.vo.MesOperationMaterialSaveReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.routing.vo.MesOperationSaveReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.routing.vo.MesRoutingPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.routing.vo.MesRoutingSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.routing.MesOperationDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.routing.MesOperationMaterialDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.routing.MesRoutingDO;
import cn.iocoder.yudao.module.mes.dal.mysql.routing.MesOperationMapper;
import cn.iocoder.yudao.module.mes.dal.mysql.routing.MesOperationMaterialMapper;
import cn.iocoder.yudao.module.mes.dal.mysql.routing.MesRoutingMapper;
import cn.iocoder.yudao.module.mes.enums.RoutingStatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.*;

/**
 * MES 工艺路线 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MesRoutingServiceImpl implements MesRoutingService {

    @Resource
    private MesRoutingMapper routingMapper;

    @Resource
    private MesOperationMapper operationMapper;

    @Resource
    private MesOperationMaterialMapper operationMaterialMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createRouting(MesRoutingSaveReqVO createReqVO) {
        // 1. 校验编码唯一
        validateRoutingCodeUnique(null, createReqVO.getRoutingCode());
        // 2. 插入主表
        MesRoutingDO routing = BeanUtils.toBean(createReqVO, MesRoutingDO.class);
        routing.setStatus(RoutingStatusEnum.DRAFT.getStatus());
        routingMapper.insert(routing);
        // 3. 插入工序和物料
        if (createReqVO.getOperations() != null && !createReqVO.getOperations().isEmpty()) {
            createOperations(routing.getId(), createReqVO.getOperations());
        }
        return routing.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRouting(MesRoutingSaveReqVO updateReqVO) {
        // 1. 校验存在
        MesRoutingDO routing = validateRoutingExists(updateReqVO.getId());
        // 2. 校验状态（只有草稿状态可以修改）
        if (!RoutingStatusEnum.DRAFT.getStatus().equals(routing.getStatus())) {
            throw exception(ROUTING_STATUS_ERROR);
        }
        // 3. 校验编码唯一
        validateRoutingCodeUnique(updateReqVO.getId(), updateReqVO.getRoutingCode());
        // 4. 更新主表
        MesRoutingDO updateObj = BeanUtils.toBean(updateReqVO, MesRoutingDO.class);
        routingMapper.updateById(updateObj);
        // 5. 删除旧的工序和物料
        deleteOperationsByRoutingId(updateReqVO.getId());
        // 6. 插入新的工序和物料
        if (updateReqVO.getOperations() != null && !updateReqVO.getOperations().isEmpty()) {
            createOperations(updateReqVO.getId(), updateReqVO.getOperations());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRouting(Long id) {
        // 1. 校验存在
        MesRoutingDO routing = validateRoutingExists(id);
        // 2. 校验状态（只有草稿状态可以删除）
        if (!RoutingStatusEnum.DRAFT.getStatus().equals(routing.getStatus())) {
            throw exception(ROUTING_STATUS_ERROR);
        }
        // 3. 删除工序和物料
        deleteOperationsByRoutingId(id);
        // 4. 删除主表
        routingMapper.deleteById(id);
    }

    @Override
    public MesRoutingDO getRouting(Long id) {
        MesRoutingDO routing = routingMapper.selectById(id);
        if (routing == null) {
            return null;
        }
        // 查询工序列表
        List<MesOperationDO> operations = operationMapper.selectListByRoutingId(id);
        if (!operations.isEmpty()) {
            // 查询物料列表
            for (MesOperationDO operation : operations) {
                List<MesOperationMaterialDO> materials = operationMaterialMapper.selectListByOperationId(operation.getId());
                operation.setMaterials(materials != null ? materials : new ArrayList<>());
            }
        }
        routing.setOperations(operations != null ? operations : new ArrayList<>());
        return routing;
    }

    @Override
    public PageResult<MesRoutingDO> getRoutingPage(MesRoutingPageReqVO pageReqVO) {
        return routingMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void activateRouting(Long id) {
        // 1. 校验存在
        MesRoutingDO routing = validateRoutingExists(id);
        // 2. 校验状态（只有草稿状态可以生效）
        if (!RoutingStatusEnum.DRAFT.getStatus().equals(routing.getStatus())) {
            throw exception(ROUTING_STATUS_ERROR);
        }
        // 3. 校验同一产品是否已有生效版本
        if (routing.getProductId() != null) {
            MesRoutingDO activeRouting = getActiveRoutingByProductId(routing.getProductId());
            if (activeRouting != null && !activeRouting.getId().equals(id)) {
                throw exception(ROUTING_PRODUCT_ACTIVATED);
            }
        }
        // 4. 更新状态
        routingMapper.updateById(new MesRoutingDO().setId(id).setStatus(RoutingStatusEnum.ACTIVE.getStatus()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deactivateRouting(Long id) {
        // 1. 校验存在
        MesRoutingDO routing = validateRoutingExists(id);
        // 2. 校验状态（只有生效状态可以失效）
        if (!RoutingStatusEnum.ACTIVE.getStatus().equals(routing.getStatus())) {
            throw exception(ROUTING_STATUS_ERROR);
        }
        // TODO: 3. 校验是否被工单引用
        // 4. 更新状态
        routingMapper.updateById(new MesRoutingDO().setId(id).setStatus(RoutingStatusEnum.INACTIVE.getStatus()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long copyRouting(Long id) {
        // 1. 获取原工艺路线
        MesRoutingDO sourceRouting = getRouting(id);
        if (sourceRouting == null) {
            throw exception(ROUTING_NOT_EXISTS);
        }
        // 2. 创建新工艺路线
        MesRoutingDO newRouting = BeanUtils.toBean(sourceRouting, MesRoutingDO.class);
        newRouting.setId(null);
        newRouting.setRoutingCode(sourceRouting.getRoutingCode() + "_COPY");
        newRouting.setRoutingName(sourceRouting.getRoutingName() + "(副本)");
        newRouting.setVersion("V1.0");
        newRouting.setStatus(RoutingStatusEnum.DRAFT.getStatus());
        routingMapper.insert(newRouting);
        // 3. 复制工序和物料
        if (sourceRouting.getOperations() != null && !sourceRouting.getOperations().isEmpty()) {
            List<MesOperationSaveReqVO> operations = new ArrayList<>();
            for (MesOperationDO operation : sourceRouting.getOperations()) {
                MesOperationSaveReqVO operationVO = BeanUtils.toBean(operation, MesOperationSaveReqVO.class);
                operationVO.setId(null);
                // 复制物料
                if (operation.getMaterials() != null && !operation.getMaterials().isEmpty()) {
                    List<MesOperationMaterialSaveReqVO> materials = new ArrayList<>();
                    for (MesOperationMaterialDO material : operation.getMaterials()) {
                        MesOperationMaterialSaveReqVO materialVO = BeanUtils.toBean(material, MesOperationMaterialSaveReqVO.class);
                        materialVO.setId(null);
                        materials.add(materialVO);
                    }
                    operationVO.setMaterials(materials);
                }
                operations.add(operationVO);
            }
            createOperations(newRouting.getId(), operations);
        }
        return newRouting.getId();
    }

    @Override
    public MesRoutingDO validateRoutingExists(Long id) {
        if (id == null) {
            return null;
        }
        MesRoutingDO routing = routingMapper.selectById(id);
        if (routing == null) {
            throw exception(ROUTING_NOT_EXISTS);
        }
        return routing;
    }

    @Override
    public MesRoutingDO getActiveRoutingByProductId(Long productId) {
        if (productId == null) {
            return null;
        }
        List<MesRoutingDO> routings = routingMapper.selectListByProductId(productId);
        for (MesRoutingDO routing : routings) {
            if (RoutingStatusEnum.ACTIVE.getStatus().equals(routing.getStatus())) {
                return routing;
            }
        }
        return null;
    }

    /**
     * 创建工序和物料
     */
    private void createOperations(Long routingId, List<MesOperationSaveReqVO> operations) {
        for (MesOperationSaveReqVO operationVO : operations) {
            MesOperationDO operation = BeanUtils.toBean(operationVO, MesOperationDO.class);
            operation.setRoutingId(routingId);
            operationMapper.insert(operation);
            // 创建物料
            if (operationVO.getMaterials() != null && !operationVO.getMaterials().isEmpty()) {
                for (MesOperationMaterialSaveReqVO materialVO : operationVO.getMaterials()) {
                    MesOperationMaterialDO material = BeanUtils.toBean(materialVO, MesOperationMaterialDO.class);
                    material.setOperationId(operation.getId());
                    operationMaterialMapper.insert(material);
                }
            }
        }
    }

    /**
     * 删除工序和物料
     */
    private void deleteOperationsByRoutingId(Long routingId) {
        List<MesOperationDO> operations = operationMapper.selectListByRoutingId(routingId);
        if (!operations.isEmpty()) {
            // 删除物料
            List<Long> operationIds = new ArrayList<>();
            for (MesOperationDO operation : operations) {
                operationIds.add(operation.getId());
            }
            operationMaterialMapper.deleteByOperationIds(operationIds);
            // 删除工序
            operationMapper.deleteByRoutingId(routingId);
        }
    }

    private void validateRoutingCodeUnique(Long id, String routingCode) {
        MesRoutingDO routing = routingMapper.selectByRoutingCode(routingCode);
        if (routing == null) {
            return;
        }
        if (id == null) {
            throw exception(ROUTING_CODE_DUPLICATE);
        }
        if (!routing.getId().equals(id)) {
            throw exception(ROUTING_CODE_DUPLICATE);
        }
    }

}