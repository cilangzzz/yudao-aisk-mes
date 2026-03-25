package cn.iocoder.yudao.module.mes.controller.admin.workorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 生产工单新增/修改 Request VO")
@Data
public class MesWorkOrderSaveReqVO {

    @Schema(description = "编号", example = "1")
    private Long id;

    @Schema(description = "工单编号", example = "WO202603250001")
    private String orderNo;

    @Schema(description = "ERP订单编号", example = "ERP001")
    private String erpOrderNo;

    @Schema(description = "产品ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "产品ID不能为空")
    private Long productId;

    @Schema(description = "产品编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "P001")
    @NotBlank(message = "产品编码不能为空")
    private String productCode;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "汽车A")
    @NotBlank(message = "产品名称不能为空")
    private String productName;

    @Schema(description = "计划数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    @NotNull(message = "计划数量不能为空")
    private Integer planQty;

    @Schema(description = "工艺路线ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "工艺路线ID不能为空")
    private Long routingId;

    @Schema(description = "工艺路线名称", example = "总装线A")
    private String routingName;

    @Schema(description = "优先级(1-10)", example = "5")
    private Integer priority;

    @Schema(description = "计划开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "计划开始时间不能为空")
    private LocalDateTime planStartTime;

    @Schema(description = "计划结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "计划结束时间不能为空")
    private LocalDateTime planEndTime;

    @Schema(description = "车间ID", example = "1")
    private Long workshopId;

    @Schema(description = "车间名称", example = "总装车间")
    private String workshopName;

    @Schema(description = "产线ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "产线ID不能为空")
    private Long lineId;

    @Schema(description = "产线名称", example = "产线A")
    private String lineName;

    @Schema(description = "备注", example = "备注信息")
    private String remark;

}