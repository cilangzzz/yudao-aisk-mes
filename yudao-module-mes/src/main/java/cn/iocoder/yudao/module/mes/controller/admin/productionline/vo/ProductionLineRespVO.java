package cn.iocoder.yudao.module.mes.controller.admin.productionline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 产线 Response VO")
@Data
public class ProductionLineRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "产线编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "LINE001")
    private String lineCode;

    @Schema(description = "产线名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "主线A")
    private String lineName;

    @Schema(description = "车间ID", example = "1")
    private Long workshopId;

    @Schema(description = "车间名称", example = "总装车间")
    private String workshopName;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Integer status;

    @Schema(description = "描述", example = "这是主线A")
    private String description;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}