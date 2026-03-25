package cn.iocoder.yudao.module.mes.controller.admin.material.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "管理后台 - MES线边物料入库 Request VO")
@Data
public class MesLineStockInReqVO {

    @Schema(description = "物料编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "MAT001")
    @NotBlank(message = "物料编码不能为空")
    private String materialCode;

    @Schema(description = "物料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "焊丝")
    @NotBlank(message = "物料名称不能为空")
    private String materialName;

    @Schema(description = "工位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "工位不能为空")
    private Long workstationId;

    @Schema(description = "入库数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "100.5")
    @NotNull(message = "入库数量不能为空")
    private BigDecimal qty;

    @Schema(description = "安全库存", example = "20.0")
    private BigDecimal safetyQty;

    @Schema(description = "单位", example = "kg")
    private String unit;

}