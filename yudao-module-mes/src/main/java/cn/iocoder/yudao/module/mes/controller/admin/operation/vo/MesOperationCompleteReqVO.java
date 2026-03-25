package cn.iocoder.yudao.module.mes.controller.admin.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 完成作业请求 VO")
@Data
public class MesOperationCompleteReqVO {

    @Schema(description = "作业记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "作业记录ID不能为空")
    private Long recordId;

    @Schema(description = "结果:0-合格,1-不合格", example = "0")
    private Integer result;

    @Schema(description = "扭矩值(N·m)", example = "100.00")
    private BigDecimal torqueValue;

    @Schema(description = "扭矩判定:0-合格,1-不合格", example = "0")
    private Integer torqueResult;

    @Schema(description = "备注", example = "作业完成")
    private String remark;

}
