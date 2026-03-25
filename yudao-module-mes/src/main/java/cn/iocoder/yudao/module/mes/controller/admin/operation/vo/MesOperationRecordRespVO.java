package cn.iocoder.yudao.module.mes.controller.admin.operation.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 作业记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MesOperationRecordRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "工单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long workOrderId;

    @Schema(description = "工单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "WO202603250001")
    @ExcelProperty("工单编号")
    private String workOrderNo;

    @Schema(description = "VIN码", requiredMode = Schema.RequiredMode.REQUIRED, example = "LSVNV2182E2100001")
    @ExcelProperty("VIN码")
    private String vin;

    @Schema(description = "工序ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long operationId;

    @Schema(description = "工序编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "OP001")
    @ExcelProperty("工序编码")
    private String operationCode;

    @Schema(description = "工序名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "总装")
    @ExcelProperty("工序名称")
    private String operationName;

    @Schema(description = "工序顺序", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("工序顺序")
    private Integer operationSeq;

    @Schema(description = "工作站ID")
    private Long workstationId;

    @Schema(description = "工作站编码", example = "WS001")
    @ExcelProperty("工作站编码")
    private String workstationCode;

    @Schema(description = "工作站名称", example = "工位1")
    @ExcelProperty("工作站名称")
    private String workstationName;

    @Schema(description = "操作员ID")
    private Long operatorId;

    @Schema(description = "操作员姓名", example = "张三")
    @ExcelProperty("操作员姓名")
    private String operatorName;

    @Schema(description = "开始时间")
    @ExcelProperty("开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    @ExcelProperty("结束时间")
    private LocalDateTime endTime;

    @Schema(description = "作业时长(秒)", example = "120")
    @ExcelProperty("作业时长(秒)")
    private Integer duration;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @ExcelProperty("状态")
    private Integer status;

    @Schema(description = "结果", example = "0")
    @ExcelProperty("结果")
    private Integer result;

    @Schema(description = "扭矩值(N·m)", example = "100.00")
    @ExcelProperty("扭矩值(N·m)")
    private BigDecimal torqueValue;

    @Schema(description = "扭矩判定", example = "0")
    @ExcelProperty("扭矩判定")
    private Integer torqueResult;

    @Schema(description = "备注", example = "备注信息")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
