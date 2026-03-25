package cn.iocoder.yudao.module.mes.controller.admin.workshop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 车间精简信息 Response VO")
@Data
public class WorkshopSimpleRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "车间编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "WS001")
    private String workshopCode;

    @Schema(description = "车间名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "总装车间")
    private String workshopName;

    @Schema(description = "父级ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Long parentId;

}