package cn.iocoder.yudao.module.mes.controller.admin.productbom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 产品BOM Response VO")
@Data
public class ProductBomRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "产品ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long productId;

    @Schema(description = "产品编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "PROD001")
    private String productCode;

    @Schema(description = "物料编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "MAT001")
    private String materialCode;

    @Schema(description = "物料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "发动机")
    private String materialName;

    @Schema(description = "用量", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private BigDecimal qty;

    @Schema(description = "单位", example = "个")
    private String unit;

    @Schema(description = "是否关键件", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Integer keyPart;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}