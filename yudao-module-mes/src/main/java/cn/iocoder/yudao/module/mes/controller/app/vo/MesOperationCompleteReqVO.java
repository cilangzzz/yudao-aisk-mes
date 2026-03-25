package cn.iocoder.yudao.module.mes.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 移动端完成作业请求 VO
 */
@Schema(description = "移动端 - 完成作业请求 VO")
@Data
public class MesOperationCompleteReqVO {

    @Schema(description = "作业记录ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "作业记录ID不能为空")
    private Long id;

    @Schema(description = "作业结果: 0-合格, 1-不合格", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "作业结果不能为空")
    private Integer result;

    @Schema(description = "扭矩值（关键工序）")
    private BigDecimal torqueValue;

    @Schema(description = "备注")
    private String remark;

}