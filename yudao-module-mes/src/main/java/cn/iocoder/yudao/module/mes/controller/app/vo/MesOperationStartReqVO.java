package cn.iocoder.yudao.module.mes.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 移动端开始作业请求 VO
 */
@Schema(description = "移动端 - 开始作业请求 VO")
@Data
public class MesOperationStartReqVO {

    @Schema(description = "VIN码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "VIN不能为空")
    private String vin;

    @Schema(description = "工单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "工单ID不能为空")
    private Long workOrderId;

    @Schema(description = "工序ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "工序ID不能为空")
    private Long operationId;

    @Schema(description = "工位ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "工位ID不能为空")
    private Long workstationId;

}