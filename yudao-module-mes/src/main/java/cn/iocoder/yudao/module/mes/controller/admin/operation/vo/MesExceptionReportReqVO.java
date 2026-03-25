package cn.iocoder.yudao.module.mes.controller.admin.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 异常上报请求 VO
 */
@Schema(description = "管理后台 - 异常上报请求 VO")
@Data
public class MesExceptionReportReqVO {

    @Schema(description = "VIN码", requiredMode = Schema.RequiredMode.REQUIRED, example = "LSVNV2182E2100001")
    @NotBlank(message = "VIN码不能为空")
    private String vin;

    @Schema(description = "工单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "工单ID不能为空")
    private Long workOrderId;

    @Schema(description = "工序ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "工序ID不能为空")
    private Long operationId;

    @Schema(description = "异常原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "设备故障")
    @NotBlank(message = "异常原因不能为空")
    private String exceptionReason;

    @Schema(description = "异常描述", example = "详细描述")
    private String exceptionDesc;

    @Schema(description = "异常图片URL列表")
    private List<String> imageUrls;

    @Schema(description = "工位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "工位ID不能为空")
    private Long workstationId;

}