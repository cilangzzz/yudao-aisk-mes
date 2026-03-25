package cn.iocoder.yudao.module.mes.controller.admin.productionline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 产线精简信息 Response VO")
@Data
public class ProductionLineSimpleRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "产线编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "LINE001")
    private String lineCode;

    @Schema(description = "产线名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "主线A")
    private String lineName;

    @Schema(description = "车间ID", example = "1")
    private Long workshopId;

}