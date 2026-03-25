package cn.iocoder.yudao.module.mes.controller.admin.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 产品 Response VO")
@Data
public class ProductRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "产品编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "PROD001")
    private String productCode;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "SUV整车")
    private String productName;

    @Schema(description = "产品类型", example = "整车")
    private String productType;

    @Schema(description = "规格", example = "标准版")
    private String specification;

    @Schema(description = "型号", example = "MODEL-A")
    private String model;

    @Schema(description = "单位", example = "辆")
    private String unit;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Integer status;

    @Schema(description = "描述", example = "这是SUV整车产品")
    private String description;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}