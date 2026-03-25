package cn.iocoder.yudao.module.mes.service.workorder;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.workorder.vo.MesWorkOrderPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.workorder.vo.MesWorkOrderSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.workorder.MesWorkOrderDO;
import cn.iocoder.yudao.module.mes.dal.mysql.workorder.MesWorkOrderMapper;
import cn.iocoder.yudao.module.mes.enums.WorkOrderStatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.*;

/**
 * 生产工单 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MesWorkOrderServiceImpl implements MesWorkOrderService {

    @Resource
    private MesWorkOrderMapper workOrderMapper;

    @Override
    public Long createWorkOrder(MesWorkOrderSaveReqVO createReqVO) {
        // 1. 生成工单编号
        String orderNo = generateOrderNo();
        // 2. 插入数据
        MesWorkOrderDO workOrder = BeanUtils.toBean(createReqVO, MesWorkOrderDO.class);
        workOrder.setOrderNo(orderNo);
        workOrder.setStatus(WorkOrderStatusEnum.CREATED.getStatus());
        workOrder.setActualQty(0);
        if (workOrder.getPriority() == null) {
            workOrder.setPriority(5);
        }
        workOrderMapper.insert(workOrder);
        return workOrder.getId();
    }

    @Override
    public void updateWorkOrder(MesWorkOrderSaveReqVO updateReqVO) {
        // 1. 校验存在
        MesWorkOrderDO workOrder = validateWorkOrderExists(updateReqVO.getId());
        // 2. 校验状态：只有待下发状态才能修改
        if (!WorkOrderStatusEnum.CREATED.getStatus().equals(workOrder.getStatus())) {
            throw exception(WORK_ORDER_STATUS_ERROR);
        }
        // 3. 更新数据
        MesWorkOrderDO updateObj = BeanUtils.toBean(updateReqVO, MesWorkOrderDO.class);
        workOrderMapper.updateById(updateObj);
    }

    @Override
    public void deleteWorkOrder(Long id) {
        // 1. 校验存在
        MesWorkOrderDO workOrder = validateWorkOrderExists(id);
        // 2. 校验状态：只有待下发状态才能删除
        if (!WorkOrderStatusEnum.CREATED.getStatus().equals(workOrder.getStatus())) {
            throw exception(WORK_ORDER_STATUS_ERROR);
        }
        // 3. 删除
        workOrderMapper.deleteById(id);
    }

    @Override
    public MesWorkOrderDO getWorkOrder(Long id) {
        return workOrderMapper.selectById(id);
    }

    @Override
    public PageResult<MesWorkOrderDO> getWorkOrderPage(MesWorkOrderPageReqVO pageReqVO) {
        return workOrderMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MesWorkOrderDO> getWorkOrderList(MesWorkOrderPageReqVO pageReqVO) {
        return workOrderMapper.selectList(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void releaseWorkOrder(Long id) {
        // 1. 校验存在
        MesWorkOrderDO workOrder = validateWorkOrderExists(id);
        // 2. 校验状态：只有待下发状态才能下发
        if (!WorkOrderStatusEnum.CREATED.getStatus().equals(workOrder.getStatus())) {
            throw exception(WORK_ORDER_CANNOT_RELEASE);
        }
        // 3. 更新状态
        MesWorkOrderDO updateObj = new MesWorkOrderDO();
        updateObj.setId(id);
        updateObj.setStatus(WorkOrderStatusEnum.RELEASED.getStatus());
        workOrderMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startWorkOrder(Long id) {
        // 1. 校验存在
        MesWorkOrderDO workOrder = validateWorkOrderExists(id);
        // 2. 校验状态：只有已下发状态才能开始
        if (!WorkOrderStatusEnum.RELEASED.getStatus().equals(workOrder.getStatus())) {
            throw exception(WORK_ORDER_CANNOT_START);
        }
        // 3. 校验产线：检查是否有其他生产中的工单
        if (workOrderMapper.hasProducingWorkOrder(workOrder.getLineId())) {
            throw exception(PRODUCTION_LINE_HAS_WORK_ORDERS);
        }
        // 4. 更新状态
        MesWorkOrderDO updateObj = new MesWorkOrderDO();
        updateObj.setId(id);
        updateObj.setStatus(WorkOrderStatusEnum.IN_PROGRESS.getStatus());
        updateObj.setActualStartTime(LocalDateTime.now());
        workOrderMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void completeWorkOrder(Long id) {
        // 1. 校验存在
        MesWorkOrderDO workOrder = validateWorkOrderExists(id);
        // 2. 校验状态：只有生产中状态才能完成
        if (!WorkOrderStatusEnum.IN_PROGRESS.getStatus().equals(workOrder.getStatus())) {
            throw exception(WORK_ORDER_CANNOT_COMPLETE);
        }
        // 3. 更新状态
        MesWorkOrderDO updateObj = new MesWorkOrderDO();
        updateObj.setId(id);
        updateObj.setStatus(WorkOrderStatusEnum.COMPLETED.getStatus());
        updateObj.setActualEndTime(LocalDateTime.now());
        workOrderMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void closeWorkOrder(Long id) {
        // 1. 校验存在
        MesWorkOrderDO workOrder = validateWorkOrderExists(id);
        // 2. 校验状态：待下发、已下发、生产中可以关闭
        if (WorkOrderStatusEnum.COMPLETED.getStatus().equals(workOrder.getStatus())
                || WorkOrderStatusEnum.CLOSED.getStatus().equals(workOrder.getStatus())) {
            throw exception(WORK_ORDER_CANNOT_CLOSE);
        }
        // 3. 更新状态
        MesWorkOrderDO updateObj = new MesWorkOrderDO();
        updateObj.setId(id);
        updateObj.setStatus(WorkOrderStatusEnum.CLOSED.getStatus());
        workOrderMapper.updateById(updateObj);
    }

    @Override
    public MesWorkOrderDO validateWorkOrderExists(Long id) {
        if (id == null) {
            return null;
        }
        MesWorkOrderDO workOrder = workOrderMapper.selectById(id);
        if (workOrder == null) {
            throw exception(WORK_ORDER_NOT_EXISTS);
        }
        return workOrder;
    }

    /**
     * 生成工单编号
     * 格式：WO + 年月日 + 4位流水号
     */
    private String generateOrderNo() {
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String prefix = "WO" + dateStr;
        // 查询当天最大编号
        // TODO: 实际项目中应使用 Redis 生成序号
        Long count = workOrderMapper.selectCount();
        return prefix + String.format("%04d", (count % 10000) + 1);
    }

}