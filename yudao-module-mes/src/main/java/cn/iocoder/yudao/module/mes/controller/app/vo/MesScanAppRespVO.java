package cn.iocoder.yudao.module.mes.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 移动端扫码响应 VO
 */
@Schema(description = "移动端 - 扫码响应 VO")
@Data
public class MesScanAppRespVO {

    @Schema(description = "扫码类型: VIN/WORK_ORDER/MATERIAL/KEY_PART")
    private String scanType;

    @Schema(description = "VIN码")
    private String vin;

    @Schema(description = "工单编号")
    private String workOrderNo;

    @Schema(description = "工单ID")
    private Long workOrderId;

    @Schema(description = "产品名称")
    private String productName;

    @Schema(description = "当前工序信息")
    private CurrentOperationVO currentOperation;

    @Schema(description = "已绑定关键件")
    private List<BoundPartVO> boundParts;

    @Schema(description = "扫码结果: true-成功, false-失败")
    private Boolean success;

    @Schema(description = "失败原因")
    private String failReason;

    @Schema(description = "当前工序")
    @Data
    public static class CurrentOperationVO {
        @Schema(description = "工序ID")
        private Long operationId;
        @Schema(description = "工序编码")
        private String operationCode;
        @Schema(description = "工序名称")
        private String operationName;
        @Schema(description = "工序顺序")
        private Integer sequence;
        @Schema(description = "是否关键工序")
        private Boolean keyOperation;
        @Schema(description = "作业指导")
        private String instruction;
        @Schema(description = "扭矩要求")
        private String torqueRequirement;
        @Schema(description = "所需关键件列表")
        private List<KeyPartRequirementVO> keyParts;
    }

    @Schema(description = "关键件要求")
    @Data
    public static class KeyPartRequirementVO {
        @Schema(description = "零部件编码")
        private String partCode;
        @Schema(description = "零部件名称")
        private String partName;
        @Schema(description = "是否必须")
        private Boolean required;
    }

    @Schema(description = "已绑定关键件")
    @Data
    public static class BoundPartVO {
        @Schema(description = "零部件编码")
        private String partCode;
        @Schema(description = "零部件名称")
        private String partName;
        @Schema(description = "零部件序列号")
        private String partSn;
        @Schema(description = "绑定状态")
        private Boolean bound;
    }

}