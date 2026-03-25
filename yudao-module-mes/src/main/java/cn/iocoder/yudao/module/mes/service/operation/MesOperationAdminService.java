package cn.iocoder.yudao.module.mes.service.operation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mes.controller.admin.operation.vo.*;

import javax.validation.Valid;

/**
 * MES 管理后台作业 Service 接口
 *
 * @author 芋道源码
 */
public interface MesOperationAdminService {

    /**
     * 扫码解析
     *
     * @param reqVO 扫码请求
     * @return 扫码响应
     */
    MesScanRespVO scan(@Valid MesScanReqVO reqVO);

    /**
     * 开始作业
     *
     * @param reqVO 开始作业请求
     * @return 作业记录ID
     */
    Long startOperation(@Valid MesOperationStartReqVO reqVO);

    /**
     * 完成作业
     *
     * @param reqVO 完成作业请求
     */
    void completeOperation(@Valid MesOperationCompleteReqVO reqVO);

    /**
     * 绑定关键件
     *
     * @param reqVO 绑定请求
     */
    void bindKeyPart(@Valid MesKeyPartBindReqVO reqVO);

    /**
     * 获得作业记录分页
     *
     * @param reqVO 分页请求
     * @return 作业记录分页
     */
    PageResult<MesOperationRecordRespVO> getOperationRecordPage(MesOperationRecordPageReqVO reqVO);

    /**
     * 获得作业记录详情
     *
     * @param id 记录ID
     * @return 作业记录详情
     */
    MesOperationRecordRespVO getOperationRecord(Long id);

    /**
     * 获得车辆进度
     *
     * @param vin VIN码
     * @return 车辆进度
     */
    MesVehicleProgressRespVO getVehicleProgress(String vin);

}