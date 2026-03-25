package cn.iocoder.yudao.module.mes.service.operation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mes.controller.admin.operation.vo.*;
import cn.iocoder.yudao.module.mes.dal.dataobject.operation.MesKeyPartBindDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.operation.MesOperationRecordDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 装配作业 Service 接口
 *
 * @author 芋道源码
 */
public interface MesOperationService {

    /**
     * 扫码识别
     *
     * @param reqVO 扫码请求
     * @return 扫码结果
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
     * @return 绑定记录ID
     */
    Long bindKeyPart(@Valid MesKeyPartBindReqVO reqVO);

    /**
     * 获得作业记录分页
     *
     * @param pageReqVO 分页查询
     * @return 作业记录分页
     */
    PageResult<MesOperationRecordDO> getOperationRecordPage(MesOperationRecordPageReqVO pageReqVO);

    /**
     * 获得作业记录
     *
     * @param id 编号
     * @return 作业记录
     */
    MesOperationRecordDO getOperationRecord(Long id);

    /**
     * 获得车辆进度
     *
     * @param vin VIN码
     * @return 车辆进度
     */
    MesVehicleProgressRespVO getVehicleProgress(String vin);

    /**
     * 获得关键件绑定列表
     *
     * @param vin VIN码
     * @return 关键件绑定列表
     */
    List<MesKeyPartBindDO> getKeyPartBindListByVin(String vin);

    /**
     * 异常上报
     *
     * @param reqVO 异常上报请求
     */
    void reportException(@Valid MesExceptionReportReqVO reqVO);

}
