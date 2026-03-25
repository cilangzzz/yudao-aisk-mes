package cn.iocoder.yudao.module.mes.controller.admin.quality.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 不合格处理 Request VO")
@Data
public class MesDefectHandleReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "编号不能为空")
    private Long id;

    @Schema(description = "处理方式", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "处理方式不能为空")
    private Integer handleType;

    @Schema(description = "处理人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "处理人ID不能为空")
    private Long handlerId;

    @Schema(description = "处理人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotBlank(message = "处理人姓名不能为空")
    private String handlerName;

    @Schema(description = "处理结果", example = "已返修完成")
    private String handleResult;

}