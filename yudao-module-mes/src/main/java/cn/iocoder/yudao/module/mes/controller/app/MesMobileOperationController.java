package cn.iocoder.yudao.module.mes.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.operation.vo.MesExceptionReportReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.operation.vo.MesKeyPartBindReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.operation.vo.MesOperationCompleteReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.operation.vo.MesOperationStartReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.operation.vo.MesScanReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.operation.vo.MesScanRespVO;
import cn.iocoder.yudao.module.mes.controller.app.vo.MesScanAPPReqVO;
import cn.iocoder.yudao.module.mes.controller.app.vo.MesScanAppRespVO;
import cn.iocoder.yudao.module.mes.service.operation.MesOperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 移动端 - MES 作业 Controller
 *
 * @author 芋道源码
 */
@Tag(name = "移动端 - MES 作业")
@RestController
@RequestMapping("/mes")
@Validated
public class MesMobileOperationController {

    @Resource
    private MesOperationService operationService;

    @PostMapping("/operation/scan")
    @Operation(summary = "扫码解析")
    public CommonResult<MesScanAppRespVO> scan(@Valid @RequestBody MesScanAPPReqVO reqAppVO) {
        MesScanReqVO reqVO = BeanUtils.toBean(reqAppVO, MesScanReqVO.class);
        reqVO.setCode(reqAppVO.getScanCode());
        MesScanRespVO respVO = operationService.scan(reqVO);
        return success(BeanUtils.toBean(respVO, MesScanAppRespVO.class));
    }

    @PostMapping("/operation/start")
    @Operation(summary = "开始作业")
    public CommonResult<Long> startOperation(@Valid @RequestBody MesOperationStartReqVO reqVO) {
        return success(operationService.startOperation(reqVO));
    }

    @PutMapping("/operation/complete")
    @Operation(summary = "完成作业")
    public CommonResult<Boolean> completeOperation(@Valid @RequestBody MesOperationCompleteReqVO reqVO) {
        operationService.completeOperation(reqVO);
        return success(true);
    }

    @PostMapping("/operation/bind-part")
    @Operation(summary = "绑定关键件")
    public CommonResult<Long> bindKeyPart(@Valid @RequestBody MesKeyPartBindReqVO reqVO) {
        return success(operationService.bindKeyPart(reqVO));
    }

    @PostMapping("/exception/report")
    @Operation(summary = "异常上报")
    public CommonResult<Boolean> reportException(@Valid @RequestBody MesExceptionReportReqVO reqVO) {
        operationService.reportException(reqVO);
        return success(true);
    }

}