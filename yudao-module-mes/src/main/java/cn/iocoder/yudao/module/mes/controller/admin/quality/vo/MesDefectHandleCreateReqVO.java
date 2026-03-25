package cn.iocoder.yudao.module.mes.controller.admin.quality.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 不合格登记 Request VO")
@Data
public class MesDefectHandleCreateReqVO {

    @Schema(description = "质量记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "质量记录ID不能为空")
    private Long qualityRecordId;

    @Schema(description = "车辆VIN", requiredMode = Schema.RequiredMode.REQUIRED, example = "LSVXXXXXXXXXXXXXXX")
    @NotBlank(message = "车辆VIN不能为空")
    private String vin;

    @Schema(description = "缺陷类型", example = "外观缺陷")
    private String defectType;

    @Schema(description = "缺陷描述", requiredMode = Schema.RequiredMode.REQUIRED, example = "外观划伤")
    @NotBlank(message = "缺陷描述不能为空")
    private String defectDesc;

    @Schema(description = "处理方式", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "处理方式不能为空")
    private Integer handleType;

}