package cn.iocoder.yudao.module.mes.controller.admin.trace;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mes.controller.admin.trace.vo.*;
import cn.iocoder.yudao.module.mes.service.trace.MesTraceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * MES 生产追溯 Controller
 *
 * @author 芋道源码
 */
@Tag(name = "管理后台 - MES 生产追溯")
@RestController
@RequestMapping("/mes/trace")
@Validated
public class MesTraceController {

    @Resource
    private MesTraceService traceService;

    @GetMapping("/vin")
    @Operation(summary = "VIN 正向追溯查询")
    @Parameter(name = "vin", description = "VIN码", required = true, example = "LSVAU2180N2183721")
    @PreAuthorize("@ss.hasPermission('mes:trace:query')")
    public CommonResult<MesVinTraceRespVO> traceByVin(@RequestParam("vin") String vin) {
        return success(traceService.traceByVin(vin));
    }

    @GetMapping("/part")
    @Operation(summary = "关键件反向追溯")
    @Parameter(name = "partSn", description = "关键件序列号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:trace:query')")
    public CommonResult<MesPartTraceRespVO> traceByPartSn(@RequestParam("partSn") String partSn) {
        return success(traceService.traceByPartSn(partSn));
    }

    @GetMapping("/operation")
    @Operation(summary = "工序作业详情查询")
    @Parameter(name = "recordId", description = "作业记录ID", required = true)
    @PreAuthorize("@ss.hasPermission('mes:trace:query')")
    public CommonResult<MesOperationRecordDetailRespVO> getOperationDetail(@RequestParam("recordId") Long recordId) {
        return success(traceService.getOperationDetail(recordId));
    }

    @GetMapping("/operator")
    @Operation(summary = "操作员作业记录查询")
    @PreAuthorize("@ss.hasPermission('mes:trace:query')")
    public CommonResult<PageResult<MesOperationRecordDetailRespVO>> getOperatorRecords(@Valid MesOperatorRecordPageReqVO pageReqVO) {
        return success(traceService.getOperatorRecords(pageReqVO));
    }

}