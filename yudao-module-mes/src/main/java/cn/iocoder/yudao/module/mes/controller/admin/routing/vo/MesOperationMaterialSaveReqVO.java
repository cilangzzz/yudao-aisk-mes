package cn.iocoder.yudao.module.mes.controller.admin.routing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "管理后台 - MES工序物料新增/修改 Request VO")
@Data
public class MesOperationMaterialSaveReqVO {

    @Schema(description = "编号", example = "1")
    private Long id;

    @Schema(description = "物料编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "MAT001")
    @NotBlank(message = "物料编码不能为空")
    private String materialCode;

    @Schema(description = "物料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "焊丝")
    @NotBlank(message = "物料名称不能为空")
    private String materialName;

    @Schema(description = "用量", requiredMode = Schema.RequiredMode.REQUIRED, example = "10.5")
    @NotNull(message = "用量不能为空")
    private BigDecimal qty;

    @Schema(description = "单位", example = "kg")
    private String unit;

    @Schema(description = "是否关键件:0-否,1-是", example = "0")
    private Integer keyPart;

}