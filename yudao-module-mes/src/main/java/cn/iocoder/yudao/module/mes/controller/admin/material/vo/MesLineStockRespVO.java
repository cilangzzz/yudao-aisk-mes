package cn.iocoder.yudao.module.mes.controller.admin.material.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - MES线边库存 Response VO")
@Data
public class MesLineStockRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "物料编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "MAT001")
    private String materialCode;

    @Schema(description = "物料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "焊丝")
    private String materialName;

    @Schema(description = "工位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long workstationId;

    @Schema(description = "工位名称", example = "焊接工位1")
    private String workstationName;

    @Schema(description = "库存数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "100.5")
    private BigDecimal qty;

    @Schema(description = "安全库存", requiredMode = Schema.RequiredMode.REQUIRED, example = "20.0")
    private BigDecimal safetyQty;

    @Schema(description = "单位", example = "kg")
    private String unit;

    @Schema(description = "最后更新时间")
    private LocalDateTime lastUpdateTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "是否缺料预警", example = "false")
    private Boolean warning;

}