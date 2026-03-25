package cn.iocoder.yudao.module.mes.controller.admin.material.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "管理后台 - MES物料消耗 Request VO")
@Data
public class MesMaterialConsumeReqVO {

    @Schema(description = "车辆VIN", example = "LSVAU2180N2181234")
    private String vin;

    @Schema(description = "工单ID", example = "1")
    private Long workOrderId;

    @Schema(description = "物料编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "MAT001")
    @NotBlank(message = "物料编码不能为空")
    private String materialCode;

    @Schema(description = "物料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "焊丝")
    @NotBlank(message = "物料名称不能为空")
    private String materialName;

    @Schema(description = "消耗数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "5.5")
    @NotNull(message = "消耗数量不能为空")
    private BigDecimal qty;

    @Schema(description = "单位", example = "kg")
    private String unit;

    @Schema(description = "消耗工位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "消耗工位不能为空")
    private Long workstationId;

    @Schema(description = "操作员ID", example = "1")
    private Long operatorId;

    @Schema(description = "操作员姓名", example = "张三")
    private String operatorName;

    @Schema(description = "备注", example = "正常消耗")
    private String remark;

}