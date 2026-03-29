package cn.iocoder.yudao.module.mes.controller.admin.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 扫码响应 VO")
@Data
public class MesScanRespVO {

    @Schema(description = "扫码类型", example = "vin")
    private String scanType;

    @Schema(description = "扫码类型名称", example = "VIN码")
    private String scanTypeName;

    @Schema(description = "是否可以开始作业", example = "true")
    private Boolean canStart;

    @Schema(description = "VIN码（扫码结果）", example = "LSVNV2182E2100001")
    private String vin;

    @Schema(description = "工单ID", example = "1")
    private Long workOrderId;

    @Schema(description = "工单编号", example = "WO202603250001")
    private String workOrderNo;

    @Schema(description = "产品编码", example = "P001")
    private String productCode;

    @Schema(description = "产品名称", example = "汽车A")
    private String productName;

    @Schema(description = "物料编码", example = "M001")
    private String materialCode;

    @Schema(description = "VIN码信息")
    private VinInfo vinInfo;

    @Schema(description = "工单信息")
    private WorkOrderInfo workOrderInfo;

    @Schema(description = "物料信息")
    private MaterialInfo materialInfo;

    @Schema(description = "提示消息", example = "请选择工序开始作业")
    private String message;

    @Data
    @Schema(description = "VIN码信息")
    public static class VinInfo {
        @Schema(description = "VIN码", example = "LSVNV2182E2100001")
        private String vin;

        @Schema(description = "工单ID", example = "1")
        private Long workOrderId;

        @Schema(description = "工单编号", example = "WO202603250001")
        private String workOrderNo;

        @Schema(description = "产品名称", example = "汽车A")
        private String productName;

        @Schema(description = "当前工序信息")
        private List<OperationInfo> operations;
    }

    @Data
    @Schema(description = "工单信息")
    public static class WorkOrderInfo {
        @Schema(description = "工单ID", example = "1")
        private Long id;

        @Schema(description = "工单编号", example = "WO202603250001")
        private String orderNo;

        @Schema(description = "产品名称", example = "汽车A")
        private String productName;

        @Schema(description = "状态", example = "2")
        private Integer status;

        @Schema(description = "状态名称", example = "生产中")
        private String statusName;

        @Schema(description = "工序列表")
        private List<OperationInfo> operations;
    }

    @Data
    @Schema(description = "物料信息")
    public static class MaterialInfo {
        @Schema(description = "物料编码", example = "M001")
        private String partCode;

        @Schema(description = "物料名称", example = "发动机")
        private String partName;

        @Schema(description = "序列号", example = "SN001")
        private String partSn;

        @Schema(description = "是否已绑定", example = "false")
        private Boolean binded;
    }

    @Data
    @Schema(description = "工序信息")
    public static class OperationInfo {
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

        @Schema(description = "作业记录ID（如果存在）", example = "1")
        private Long recordId;

        @Schema(description = "作业状态", example = "0")
        private Integer status;
    }

}
