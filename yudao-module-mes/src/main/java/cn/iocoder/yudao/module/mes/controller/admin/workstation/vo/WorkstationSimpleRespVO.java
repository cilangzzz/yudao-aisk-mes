package cn.iocoder.yudao.module.mes.controller.admin.workstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 工作站精简信息 Response VO")
@Data
public class WorkstationSimpleRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "工作站编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "WS001")
    private String workstationCode;

    @Schema(description = "工作站名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "工位A01")
    private String workstationName;

    @Schema(description = "产线ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long lineId;

    @Schema(description = "工作站类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Integer workstationType;

}