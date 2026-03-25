package cn.iocoder.yudao.module.mes.controller.admin.productbom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 产品BOM新增/修改 Request VO")
@Data
public class ProductBomSaveReqVO {

    @Schema(description = "编号", example = "1")
    private Long id;

    @Schema(description = "产品ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "产品ID不能为空")
    private Long productId;

    @Schema(description = "产品编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "PROD001")
    @NotBlank(message = "产品编码不能为空")
    private String productCode;

    @Schema(description = "物料编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "MAT001")
    @NotBlank(message = "物料编码不能为空")
    private String materialCode;

    @Schema(description = "物料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "发动机")
    @NotBlank(message = "物料名称不能为空")
    private String materialName;

    @Schema(description = "用量", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "用量不能为空")
    private BigDecimal qty;

    @Schema(description = "单位", example = "个")
    private String unit;

    @Schema(description = "是否关键件", example = "0")
    private Integer keyPart;

}