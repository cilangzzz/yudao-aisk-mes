package cn.iocoder.yudao.module.mes.controller.admin.quality.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 质量检验记录新增/修改 Request VO")
@Data
public class MesQualityRecordSaveReqVO {

    @Schema(description = "编号", example = "1")
    private Long id;

    @Schema(description = "车辆VIN", requiredMode = Schema.RequiredMode.REQUIRED, example = "LSVXXXXXXXXXXXXXXX")
    @NotBlank(message = "车辆VIN不能为空")
    private String vin;

    @Schema(description = "工单ID", example = "1")
    private Long workOrderId;

    @Schema(description = "检验类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "检验类型不能为空")
    private Integer checkType;

    @Schema(description = "检验项目编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "CHK001")
    @NotBlank(message = "检验项目编码不能为空")
    private String checkItemCode;

    @Schema(description = "检验项目名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "外观检查")
    @NotBlank(message = "检验项目名称不能为空")
    private String checkItemName;

    @Schema(description = "关联工序ID", example = "1")
    private Long operationId;

    @Schema(description = "工序名称", example = "装配工序")
    private String operationName;

    @Schema(description = "检验工位ID", example = "1")
    private Long workstationId;

    @Schema(description = "检验结果", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "检验结果不能为空")
    private Integer result;

    @Schema(description = "实际检验值", example = "100")
    private String checkValue;

    @Schema(description = "标准值", example = "100±5")
    private String standardValue;

    @Schema(description = "检验员ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "检验员ID不能为空")
    private Long inspectorId;

    @Schema(description = "检验员姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotBlank(message = "检验员姓名不能为空")
    private String inspectorName;

    @Schema(description = "检验时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "检验时间不能为空")
    private LocalDateTime checkTime;

    @Schema(description = "不合格原因", example = "外观划伤")
    private String defectReason;

    @Schema(description = "备注", example = "备注信息")
    private String remark;

}