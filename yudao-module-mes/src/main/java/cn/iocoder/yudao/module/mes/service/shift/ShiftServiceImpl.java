package cn.iocoder.yudao.module.mes.service.shift;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.shift.vo.ShiftPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.shift.vo.ShiftSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.shift.ShiftDO;
import cn.iocoder.yudao.module.mes.dal.mysql.shift.ShiftMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.*;

/**
 * 班次 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ShiftServiceImpl implements ShiftService {

    @Resource
    private ShiftMapper shiftMapper;

    @Override
    public Long createShift(ShiftSaveReqVO createReqVO) {
        // 1. 校验编码唯一
        validateShiftCodeUnique(null, createReqVO.getShiftCode());
        // 2. 校验名称唯一
        validateShiftNameUnique(null, createReqVO.getShiftName());
        // 3. 校验时间
        validateShiftTime(createReqVO.getStartTime(), createReqVO.getEndTime());
        // 4. 插入数据
        ShiftDO shift = BeanUtils.toBean(createReqVO, ShiftDO.class);
        // 设置默认状态
        if (shift.getStatus() == null) {
            shift.setStatus(CommonStatusEnum.ENABLE.getStatus());
        }
        shiftMapper.insert(shift);
        return shift.getId();
    }

    @Override
    public void updateShift(ShiftSaveReqVO updateReqVO) {
        // 1. 校验存在
        validateShiftExists(updateReqVO.getId());
        // 2. 校验编码唯一
        validateShiftCodeUnique(updateReqVO.getId(), updateReqVO.getShiftCode());
        // 3. 校验名称唯一
        validateShiftNameUnique(updateReqVO.getId(), updateReqVO.getShiftName());
        // 4. 校验时间
        validateShiftTime(updateReqVO.getStartTime(), updateReqVO.getEndTime());
        // 5. 更新数据
        ShiftDO updateObj = BeanUtils.toBean(updateReqVO, ShiftDO.class);
        shiftMapper.updateById(updateObj);
    }

    @Override
    public void deleteShift(Long id) {
        // 1. 校验存在
        validateShiftExists(id);
        // 2. 删除
        shiftMapper.deleteById(id);
    }

    @Override
    public ShiftDO getShift(Long id) {
        return shiftMapper.selectById(id);
    }

    @Override
    public PageResult<ShiftDO> getShiftPage(ShiftPageReqVO pageReqVO) {
        return shiftMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ShiftDO> getShiftList(ShiftPageReqVO pageReqVO) {
        return shiftMapper.selectList(pageReqVO);
    }

    @Override
    public ShiftDO validateShiftExists(Long id) {
        if (id == null) {
            return null;
        }
        ShiftDO shift = shiftMapper.selectById(id);
        if (shift == null) {
            throw exception(SHIFT_NOT_EXISTS);
        }
        return shift;
    }

    private void validateShiftCodeUnique(Long id, String shiftCode) {
        ShiftDO shift = shiftMapper.selectByShiftCode(shiftCode);
        if (shift == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的班次
        if (id == null) {
            throw exception(SHIFT_CODE_DUPLICATE);
        }
        if (!shift.getId().equals(id)) {
            throw exception(SHIFT_CODE_DUPLICATE);
        }
    }

    private void validateShiftNameUnique(Long id, String shiftName) {
        ShiftDO shift = shiftMapper.selectByShiftName(shiftName);
        if (shift == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的班次
        if (id == null) {
            throw exception(SHIFT_NAME_DUPLICATE);
        }
        if (!shift.getId().equals(id)) {
            throw exception(SHIFT_NAME_DUPLICATE);
        }
    }

    private void validateShiftTime(java.time.LocalTime startTime, java.time.LocalTime endTime) {
        if (startTime != null && endTime != null && startTime.isAfter(endTime)) {
            throw exception(SHIFT_TIME_ERROR);
        }
    }

}