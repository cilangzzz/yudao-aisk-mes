package cn.iocoder.yudao.module.mes.controller.admin.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Schema(description = "管理后台 - 扫码请求 VO")
@Data
public class MesScanReqVO {

    @Schema(description = "扫码内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "LSVNV2182E2100001")
    @NotBlank(message = "扫码内容不能为空")
    private String code;

    @Schema(description = "工作站ID", example = "1")
    private Long workstationId;

    @Schema(description = "工单ID（用于切换当前工单）", example = "1")
    private Long workOrderId;

}
