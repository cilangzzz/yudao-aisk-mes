package cn.iocoder.yudao.module.mes.controller.admin.routing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - MES工序物料 Response VO")
@Data
public class MesOperationMaterialRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "物料编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "MAT001")
    private String materialCode;

    @Schema(description = "物料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "焊丝")
    private String materialName;

    @Schema(description = "用量", requiredMode = Schema.RequiredMode.REQUIRED, example = "10.5")
    private BigDecimal qty;

    @Schema(description = "单位", example = "kg")
    private String unit;

    @Schema(description = "是否关键件:0-否,1-是", example = "0")
    private Integer keyPart;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}