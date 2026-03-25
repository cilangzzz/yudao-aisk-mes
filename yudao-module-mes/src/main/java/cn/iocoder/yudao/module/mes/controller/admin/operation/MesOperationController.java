package cn.iocoder.yudao.module.mes.controller.admin.operation;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.operation.vo.*;
import cn.iocoder.yudao.module.mes.dal.dataobject.operation.MesKeyPartBindDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.operation.MesOperationRecordDO;
import cn.iocoder.yudao.module.mes.service.operation.MesOperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 装配作业")
@RestController
@RequestMapping("/mes/operation")
@Validated
public class MesOperationController {

    @Resource
    private MesOperationService operationService;

    @PostMapping("/scan")
    @Operation(summary = "扫码识别")
    @PreAuthorize("@ss.hasPermission('mes:operation:scan')")
    public CommonResult<MesScanRespVO> scan(@Valid @RequestBody MesScanReqVO reqVO) {
        return success(operationService.scan(reqVO));
    }

    @PostMapping("/start")
    @Operation(summary = "开始作业")
    @PreAuthorize("@ss.hasPermission('mes:operation:scan')")
    public CommonResult<Long> startOperation(@Valid @RequestBody MesOperationStartReqVO reqVO) {
        return success(operationService.startOperation(reqVO));
    }

    @PutMapping("/complete")
    @Operation(summary = "完成作业")
    @PreAuthorize("@ss.hasPermission('mes:operation:complete')")
    public CommonResult<Boolean> completeOperation(@Valid @RequestBody MesOperationCompleteReqVO reqVO) {
        operationService.completeOperation(reqVO);
        return success(true);
    }

    @PostMapping("/bind-part")
    @Operation(summary = "绑定关键件")
    @PreAuthorize("@ss.hasPermission('mes:operation:bind-part')")
    public CommonResult<Long> bindKeyPart(@Valid @RequestBody MesKeyPartBindReqVO reqVO) {
        return success(operationService.bindKeyPart(reqVO));
    }

    @GetMapping("/record/page")
    @Operation(summary = "获得作业记录分页")
    @PreAuthorize("@ss.hasPermission('mes:operation:query')")
    public CommonResult<PageResult<MesOperationRecordRespVO>> getOperationRecordPage(@Valid MesOperationRecordPageReqVO pageReqVO) {
        PageResult<MesOperationRecordDO> pageResult = operationService.getOperationRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MesOperationRecordRespVO.class));
    }

    @GetMapping("/record/get")
    @Operation(summary = "获得作业记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:operation:query')")
    public CommonResult<MesOperationRecordRespVO> getOperationRecord(@RequestParam("id") Long id) {
        MesOperationRecordDO record = operationService.getOperationRecord(id);
        return success(BeanUtils.toBean(record, MesOperationRecordRespVO.class));
    }

    @GetMapping("/progress")
    @Operation(summary = "获得车辆进度")
    @Parameter(name = "vin", description = "VIN码", required = true, example = "LSVNV2182E2100001")
    @PreAuthorize("@ss.hasPermission('mes:operation:query')")
    public CommonResult<MesVehicleProgressRespVO> getVehicleProgress(@RequestParam("vin") String vin) {
        return success(operationService.getVehicleProgress(vin));
    }

    @GetMapping("/key-part/list")
    @Operation(summary = "获得关键件绑定列表")
    @Parameter(name = "vin", description = "VIN码", required = true, example = "LSVNV2182E2100001")
    @PreAuthorize("@ss.hasPermission('mes:operation:query')")
    public CommonResult<List<MesKeyPartBindRespVO>> getKeyPartBindList(@RequestParam("vin") String vin) {
        List<MesKeyPartBindDO> list = operationService.getKeyPartBindListByVin(vin);
        return success(BeanUtils.toBean(list, MesKeyPartBindRespVO.class));
    }

}
