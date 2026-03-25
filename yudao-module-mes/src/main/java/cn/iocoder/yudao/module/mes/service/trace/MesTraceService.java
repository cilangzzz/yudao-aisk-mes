package cn.iocoder.yudao.module.mes.service.trace;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mes.controller.admin.trace.vo.*;

/**
 * MES 追溯 Service 接口
 *
 * @author 芋道源码
 */
public interface MesTraceService {

    /**
     * VIN 正向追溯查询
     *
     * @param vin VIN码
     * @return 完整追溯信息
     */
    MesVinTraceRespVO traceByVin(String vin);

    /**
     * 关键件反向追溯
     *
     * @param partSn 零部件序列号
     * @return 关键件追溯信息
     */
    MesPartTraceRespVO traceByPartSn(String partSn);

    /**
     * 工序作业详情查询
     *
     * @param recordId 作业记录ID
     * @return 作业详情
     */
    MesOperationRecordDetailRespVO getOperationDetail(Long recordId);

    /**
     * 操作员作业记录分页查询
     *
     * @param pageReqVO 分页请求
     * @return 作业记录分页结果
     */
    PageResult<MesOperationRecordDetailRespVO> getOperatorRecords(MesOperatorRecordPageReqVO pageReqVO);

}