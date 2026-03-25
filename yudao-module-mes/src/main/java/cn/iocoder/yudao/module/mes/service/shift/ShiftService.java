package cn.iocoder.yudao.module.mes.service.shift;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mes.controller.admin.shift.vo.ShiftPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.shift.vo.ShiftSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.shift.ShiftDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 班次 Service 接口
 *
 * @author 芋道源码
 */
public interface ShiftService {

    /**
     * 创建班次
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createShift(@Valid ShiftSaveReqVO createReqVO);

    /**
     * 更新班次
     *
     * @param updateReqVO 更新信息
     */
    void updateShift(@Valid ShiftSaveReqVO updateReqVO);

    /**
     * 删除班次
     *
     * @param id 编号
     */
    void deleteShift(Long id);

    /**
     * 获得班次
     *
     * @param id 编号
     * @return 班次
     */
    ShiftDO getShift(Long id);

    /**
     * 获得班次分页
     *
     * @param pageReqVO 分页查询
     * @return 班次分页
     */
    PageResult<ShiftDO> getShiftPage(ShiftPageReqVO pageReqVO);

    /**
     * 获得班次列表
     *
     * @param pageReqVO 查询条件
     * @return 班次列表
     */
    List<ShiftDO> getShiftList(ShiftPageReqVO pageReqVO);

    /**
     * 校验班次是否存在
     *
     * @param id 编号
     * @return 班次
     */
    ShiftDO validateShiftExists(Long id);

}