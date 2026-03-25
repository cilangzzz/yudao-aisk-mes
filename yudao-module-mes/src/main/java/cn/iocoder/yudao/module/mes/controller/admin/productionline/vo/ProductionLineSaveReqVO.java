package cn.iocoder.yudao.module.mes.controller.admin.productionline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Schema(description = "管理后台 - 产线新增/修改 Request VO")
@Data
public class ProductionLineSaveReqVO {

    @Schema(description = "编号", example = "1")
    private Long id;

    @Schema(description = "产线编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "LINE001")
    @NotBlank(message = "产线编码不能为空")
    private String lineCode;

    @Schema(description = "产线名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "主线A")
    @NotBlank(message = "产线名称不能为空")
    private String lineName;

    @Schema(description = "车间ID", example = "1")
    private Long workshopId;

    @Schema(description = "状态", example = "0")
    private Integer status;

    @Schema(description = "描述", example = "这是主线A")
    private String description;

}