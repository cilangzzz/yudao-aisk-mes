package cn.iocoder.yudao.module.mes.controller.admin.operation;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mes.controller.admin.operation.vo.*;
import cn.iocoder.yudao.module.mes.service.operation.MesOperationAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 装配作业执行")
@RestController
@RequestMapping("/mes/operation")
@Validated
public class MesOperationController {

    @Resource
    private MesOperationAdminService operationAdminService;

    @PostMapping("/scan")
    @Operation(summary = "扫码解析")
    @PreAuthorize("@ss.hasPermission('mes:operation:scan')")
    public CommonResult<MesScanRespVO> scan(@Valid @RequestBody MesScanReqVO reqVO) {
        return success(operationAdminService.scan(reqVO));
    }

    @PostMapping("/start")
    @Operation(summary = "开始作业")
    @PreAuthorize("@ss.hasPermission('mes:operation:scan')")
    public CommonResult<Long> startOperation(@Valid @RequestBody MesOperationStartReqVO reqVO) {
        return success(operationAdminService.startOperation(reqVO));
    }

    @PutMapping("/complete")
    @Operation(summary = "完成作业")
    @PreAuthorize("@ss.hasPermission('mes:operation:complete')")
    public CommonResult<Boolean> completeOperation(@Valid @RequestBody MesOperationCompleteReqVO reqVO) {
        operationAdminService.completeOperation(reqVO);
        return success(true);
    }

    @PostMapping("/bind-part")
    @Operation(summary = "绑定关键件")
    @PreAuthorize("@ss.hasPermission('mes:operation:bind-part')")
    public CommonResult<Boolean> bindKeyPart(@Valid @RequestBody MesKeyPartBindReqVO reqVO) {
        operationAdminService.bindKeyPart(reqVO);
        return success(true);
    }

    @GetMapping("/record/page")
    @Operation(summary = "作业记录列表")
    @PreAuthorize("@ss.hasPermission('mes:operation:query')")
    public CommonResult<PageResult<MesOperationRecordRespVO>> getOperationRecordPage(@Valid MesOperationRecordPageReqVO reqVO) {
        return success(operationAdminService.getOperationRecordPage(reqVO));
    }

    @GetMapping("/record/get")
    @Operation(summary = "作业记录详情")
    @Parameter(name = "id", description = "记录ID", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('mes:operation:query')")
    public CommonResult<MesOperationRecordRespVO> getOperationRecord(@RequestParam("id") Long id) {
        return success(operationAdminService.getOperationRecord(id));
    }

    @GetMapping("/progress")
    @Operation(summary = "车辆进度")
    @Parameter(name = "vin", description = "VIN码", required = true, example = "LSVAU2180N2123456")
    @PreAuthorize("@ss.hasPermission('mes:operation:query')")
    public CommonResult<MesVehicleProgressRespVO> getVehicleProgress(@RequestParam("vin") String vin) {
        return success(operationAdminService.getVehicleProgress(vin));
    }

}