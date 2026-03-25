package cn.iocoder.yudao.module.mes.service.quality;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesDefectHandleCreateReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesDefectHandlePageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesDefectHandleReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesDefectHandleVerifyReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.quality.MesDefectHandleDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 不合格处理 Service 接口
 *
 * @author 芋道源码
 */
public interface MesDefectHandleService {

    /**
     * 不合格登记
     *
     * @param createReqVO 登记信息
     * @return 编号
     */
    Long createDefectHandle(@Valid MesDefectHandleCreateReqVO createReqVO);

    /**
     * 不合格处理
     *
     * @param handleReqVO 处理信息
     */
    void handleDefect(@Valid MesDefectHandleReqVO handleReqVO);

    /**
     * 不合格验证
     *
     * @param verifyReqVO 验证信息
     */
    void verifyDefect(@Valid MesDefectHandleVerifyReqVO verifyReqVO);

    /**
     * 获得不合格处理
     *
     * @param id 编号
     * @return 不合格处理
     */
    MesDefectHandleDO getDefectHandle(Long id);

    /**
     * 获得不合格处理分页
     *
     * @param pageReqVO 分页查询
     * @return 不合格处理分页
     */
    PageResult<MesDefectHandleDO> getDefectHandlePage(MesDefectHandlePageReqVO pageReqVO);

    /**
     * 根据VIN获取不合格处理列表
     *
     * @param vin 车辆VIN
     * @return 不合格处理列表
     */
    List<MesDefectHandleDO> getDefectHandleListByVin(String vin);

    /**
     * 校验不合格处理是否存在
     *
     * @param id 编号
     * @return 不合格处理
     */
    MesDefectHandleDO validateDefectHandleExists(Long id);

}