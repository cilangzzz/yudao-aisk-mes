package cn.iocoder.yudao.module.mes.controller.admin.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 关键件绑定请求 VO")
@Data
public class MesKeyPartBindReqVO {

    @Schema(description = "工单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "工单ID不能为空")
    private Long workOrderId;

    @Schema(description = "作业记录ID", example = "1")
    private Long operationRecordId;

    @Schema(description = "VIN码", requiredMode = Schema.RequiredMode.REQUIRED, example = "LSVNV2182E2100001")
    @NotBlank(message = "VIN码不能为空")
    private String vin;

    @Schema(description = "零部件编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "P001")
    @NotBlank(message = "零部件编码不能为空")
    private String partCode;

    @Schema(description = "零部件名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "发动机")
    @NotBlank(message = "零部件名称不能为空")
    private String partName;

    @Schema(description = "零部件序列号", requiredMode = Schema.RequiredMode.REQUIRED, example = "SN001")
    @NotBlank(message = "零部件序列号不能为空")
    private String partSn;

    @Schema(description = "供应商编码", example = "S001")
    private String supplierCode;

    @Schema(description = "供应商名称", example = "供应商A")
    private String supplierName;

    @Schema(description = "绑定工位ID", example = "1")
    private Long workstationId;

    @Schema(description = "备注", example = "备注信息")
    private String remark;

}
