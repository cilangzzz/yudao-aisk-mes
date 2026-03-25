package cn.iocoder.yudao.module.mes.controller.admin.shift.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Schema(description = "管理后台 - 班次新增/修改 Request VO")
@Data
public class ShiftSaveReqVO {

    @Schema(description = "编号", example = "1")
    private Long id;

    @Schema(description = "班次编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "SHIFT001")
    @NotBlank(message = "班次编码不能为空")
    private String shiftCode;

    @Schema(description = "班次名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "白班")
    @NotBlank(message = "班次名称不能为空")
    private String shiftName;

    @Schema(description = "开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开始时间不能为空")
    private LocalTime startTime;

    @Schema(description = "结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "结束时间不能为空")
    private LocalTime endTime;

    @Schema(description = "状态", example = "0")
    private Integer status;

}