package cn.iocoder.yudao.module.mes.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 移动端扫码请求 VO
 */
@Schema(description = "移动端 - 扫码请求 VO")
@Data
public class MesScanAPPReqVO {

    @Schema(description = "扫码内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "扫码内容不能为空")
    private String scanCode;

    @Schema(description = "当前工位ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long workstationId;

}