package cn.iocoder.yudao.module.mes.controller.admin.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 开始作业请求 VO")
@Data
public class MesOperationStartReqVO {

    @Schema(description = "工单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "工单ID不能为空")
    private Long workOrderId;

    @Schema(description = "VIN码", requiredMode = Schema.RequiredMode.REQUIRED, example = "LSVNV2182E2100001")
    @NotBlank(message = "VIN码不能为空")
    private String vin;

    @Schema(description = "工序ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "工序ID不能为空")
    private Long operationId;

    @Schema(description = "工作站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "工作站ID不能为空")
    private Long workstationId;

}
