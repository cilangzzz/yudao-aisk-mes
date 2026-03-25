package cn.iocoder.yudao.module.mes.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 移动端绑定关键件请求 VO
 */
@Schema(description = "移动端 - 绑定关键件请求 VO")
@Data
public class MesKeyPartBindReqVO {

    @Schema(description = "VIN码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "VIN不能为空")
    private String vin;

    @Schema(description = "工单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "工单ID不能为空")
    private Long workOrderId;

    @Schema(description = "作业记录ID")
    private Long operationRecordId;

    @Schema(description = "零部件编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "零部件编码不能为空")
    private String partCode;

    @Schema(description = "零部件名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "零部件名称不能为空")
    private String partName;

    @Schema(description = "零部件序列号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "零部件序列号不能为空")
    private String partSn;

    @Schema(description = "供应商编码")
    private String supplierCode;

    @Schema(description = "供应商名称")
    private String supplierName;

    @Schema(description = "工位ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "工位ID不能为空")
    private Long workstationId;

}