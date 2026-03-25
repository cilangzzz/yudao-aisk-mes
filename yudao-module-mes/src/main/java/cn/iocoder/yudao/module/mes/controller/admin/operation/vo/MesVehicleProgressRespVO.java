package cn.iocoder.yudao.module.mes.controller.admin.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 车辆进度 Response VO")
@Data
public class MesVehicleProgressRespVO {

    @Schema(description = "VIN码", requiredMode = Schema.RequiredMode.REQUIRED, example = "LSVNV2182E2100001")
    private String vin;

    @Schema(description = "工单ID")
    private Long workOrderId;

    @Schema(description = "工单编号", example = "WO202603250001")
    private String workOrderNo;

    @Schema(description = "产品名称", example = "汽车A")
    private String productName;

    @Schema(description = "总工序列表")
    private List<OperationProgress> operations;

    @Schema(description = "已完成工序数", example = "5")
    private Integer completedCount;

    @Schema(description = "总工序数", example = "10")
    private Integer totalCount;

    @Schema(description = "完成进度(%)", example = "50.00")
    private Double progressPercent;

    @Data
    @Schema(description = "工序进度信息")
    public static class OperationProgress {
        @Schema(description = "工序ID", example = "1")
        private Long operationId;

        @Schema(description = "工序编码", example = "OP001")
        private String operationCode;

        @Schema(description = "工序名称", example = "总装")
        private String operationName;

        @Schema(description = "工序顺序", example = "1")
        private Integer operationSeq;

        @Schema(description = "是否已完成", example = "false")
        private Boolean completed;

        @Schema(description = "作业记录ID", example = "1")
        private Long recordId;

        @Schema(description = "作业状态", example = "0")
        private Integer status;

        @Schema(description = "状态名称", example = "进行中")
        private String statusName;

        @Schema(description = "开始时间")
        private String startTime;

        @Schema(description = "结束时间")
        private String endTime;

        @Schema(description = "关键件绑定数量", example = "2")
        private Integer keyPartCount;
    }

}
