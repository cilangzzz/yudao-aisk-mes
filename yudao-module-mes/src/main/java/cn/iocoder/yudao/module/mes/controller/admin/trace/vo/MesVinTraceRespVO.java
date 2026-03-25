package cn.iocoder.yudao.module.mes.controller.admin.trace.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * VIN 追溯响应 VO
 */
@Schema(description = "管理后台 - VIN 追溯响应 VO")
@Data
public class MesVinTraceRespVO {

    @Schema(description = "VIN码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String vin;

    @Schema(description = "车辆信息")
    private VehicleInfo vehicleInfo;

    @Schema(description = "工单信息")
    private WorkOrderInfo workOrderInfo;

    @Schema(description = "工序作业记录列表")
    private List<OperationRecordVO> operationRecords;

    @Schema(description = "关键件绑定列表")
    private List<KeyPartVO> keyParts;

    @Schema(description = "质量检验记录列表")
    private List<QualityRecordVO> qualityRecords;

    @Schema(description = "设备采集数据列表")
    private List<DeviceDataVO> deviceData;

    @Schema(description = "车辆信息")
    @Data
    public static class VehicleInfo {
        @Schema(description = "产品编码")
        private String productCode;
        @Schema(description = "产品名称")
        private String productName;
        @Schema(description = "颜色")
        private String color;
        @Schema(description = "配置版本")
        private String config;
        @Schema(description = "生产日期")
        private LocalDate produceDate;
    }

    @Schema(description = "工单信息")
    @Data
    public static class WorkOrderInfo {
        @Schema(description = "工单编号")
        private String orderNo;
        @Schema(description = "计划数量")
        private Integer planQty;
        @Schema(description = "实际数量")
        private Integer actualQty;
        @Schema(description = "产线名称")
        private String lineName;
        @Schema(description = "工单状态")
        private Integer status;
        @Schema(description = "状态名称")
        private String statusName;
    }

    @Schema(description = "工序作业记录")
    @Data
    public static class OperationRecordVO {
        @Schema(description = "记录ID")
        private Long id;
        @Schema(description = "工序编码")
        private String operationCode;
        @Schema(description = "工序名称")
        private String operationName;
        @Schema(description = "工位名称")
        private String workstationName;
        @Schema(description = "操作员姓名")
        private String operatorName;
        @Schema(description = "开始时间")
        private LocalDateTime startTime;
        @Schema(description = "结束时间")
        private LocalDateTime endTime;
        @Schema(description = "作业时长(秒)")
        private Integer duration;
        @Schema(description = "作业状态")
        private Integer status;
        @Schema(description = "状态名称")
        private String statusName;
        @Schema(description = "作业结果")
        private String result;
    }

    @Schema(description = "关键件信息")
    @Data
    public static class KeyPartVO {
        @Schema(description = "零部件编码")
        private String partCode;
        @Schema(description = "零部件名称")
        private String partName;
        @Schema(description = "零部件序列号")
        private String partSn;
        @Schema(description = "供应商名称")
        private String supplierName;
        @Schema(description = "绑定时间")
        private LocalDateTime bindTime;
        @Schema(description = "绑定工位")
        private String workstationName;
        @Schema(description = "绑定操作员")
        private String operatorName;
    }

    @Schema(description = "质量检验记录")
    @Data
    public static class QualityRecordVO {
        @Schema(description = "检验类型")
        private String checkType;
        @Schema(description = "检验项名称")
        private String checkItemName;
        @Schema(description = "检验结果")
        private String result;
        @Schema(description = "检验员姓名")
        private String inspectorName;
        @Schema(description = "检验时间")
        private LocalDateTime checkTime;
    }

    @Schema(description = "设备采集数据")
    @Data
    public static class DeviceDataVO {
        @Schema(description = "设备编码")
        private String deviceCode;
        @Schema(description = "设备名称")
        private String deviceName;
        @Schema(description = "数据类型")
        private String dataType;
        @Schema(description = "数据值")
        private BigDecimal dataValue;
        @Schema(description = "数据单位")
        private String dataUnit;
        @Schema(description = "判定结果")
        private String result;
        @Schema(description = "采集时间")
        private LocalDateTime collectTime;
    }

}