package cn.iocoder.yudao.module.mes.controller.admin.quality.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 质量检验记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MesQualityRecordRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "车辆VIN", requiredMode = Schema.RequiredMode.REQUIRED, example = "LSVXXXXXXXXXXXXXXX")
    @ExcelProperty("车辆VIN")
    private String vin;

    @Schema(description = "工单ID", example = "1")
    private Long workOrderId;

    @Schema(description = "检验类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @ExcelProperty("检验类型")
    private Integer checkType;

    @Schema(description = "检验项目编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "CHK001")
    @ExcelProperty("检验项目编码")
    private String checkItemCode;

    @Schema(description = "检验项目名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "外观检查")
    @ExcelProperty("检验项目名称")
    private String checkItemName;

    @Schema(description = "关联工序ID", example = "1")
    private Long operationId;

    @Schema(description = "工序名称", example = "装配工序")
    @ExcelProperty("工序名称")
    private String operationName;

    @Schema(description = "检验工位ID", example = "1")
    private Long workstationId;

    @Schema(description = "检验结果", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @ExcelProperty("检验结果")
    private Integer result;

    @Schema(description = "实际检验值", example = "100")
    @ExcelProperty("实际检验值")
    private String checkValue;

    @Schema(description = "标准值", example = "100±5")
    @ExcelProperty("标准值")
    private String standardValue;

    @Schema(description = "检验员ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long inspectorId;

    @Schema(description = "检验员姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("检验员姓名")
    private String inspectorName;

    @Schema(description = "检验时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检验时间")
    private LocalDateTime checkTime;

    @Schema(description = "不合格原因", example = "外观划伤")
    @ExcelProperty("不合格原因")
    private String defectReason;

    @Schema(description = "备注", example = "备注信息")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}