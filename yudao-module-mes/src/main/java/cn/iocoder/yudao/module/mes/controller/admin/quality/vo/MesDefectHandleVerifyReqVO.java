package cn.iocoder.yudao.module.mes.controller.admin.quality.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 不合格验证 Request VO")
@Data
public class MesDefectHandleVerifyReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "编号不能为空")
    private Long id;

    @Schema(description = "验证人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "验证人ID不能为空")
    private Long verifierId;

    @Schema(description = "验证人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotBlank(message = "验证人姓名不能为空")
    private String verifierName;

}