package cn.iocoder.yudao.module.mes.controller.admin.workorder.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 生产工单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MesWorkOrderRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "工单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "WO202603250001")
    @ExcelProperty("工单编号")
    private String orderNo;

    @Schema(description = "ERP订单编号", example = "ERP001")
    @ExcelProperty("ERP订单编号")
    private String erpOrderNo;

    @Schema(description = "产品ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long productId;

    @Schema(description = "产品编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "P001")
    @ExcelProperty("产品编码")
    private String productCode;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "汽车A")
    @ExcelProperty("产品名称")
    private String productName;

    @Schema(description = "计划数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    @ExcelProperty("计划数量")
    private Integer planQty;

    @Schema(description = "实际数量", example = "50")
    @ExcelProperty("实际数量")
    private Integer actualQty;

    @Schema(description = "工艺路线ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long routingId;

    @Schema(description = "工艺路线名称", example = "总装线A")
    @ExcelProperty("工艺路线名称")
    private String routingName;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @ExcelProperty("状态")
    private Integer status;

    @Schema(description = "优先级", example = "5")
    @ExcelProperty("优先级")
    private Integer priority;

    @Schema(description = "计划开始时间")
    @ExcelProperty("计划开始时间")
    private LocalDateTime planStartTime;

    @Schema(description = "计划结束时间")
    @ExcelProperty("计划结束时间")
    private LocalDateTime planEndTime;

    @Schema(description = "实际开始时间")
    @ExcelProperty("实际开始时间")
    private LocalDateTime actualStartTime;

    @Schema(description = "实际结束时间")
    @ExcelProperty("实际结束时间")
    private LocalDateTime actualEndTime;

    @Schema(description = "车间ID")
    private Long workshopId;

    @Schema(description = "车间名称", example = "总装车间")
    @ExcelProperty("车间名称")
    private String workshopName;

    @Schema(description = "产线ID")
    private Long lineId;

    @Schema(description = "产线名称", example = "产线A")
    @ExcelProperty("产线名称")
    private String lineName;

    @Schema(description = "备注", example = "备注信息")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}