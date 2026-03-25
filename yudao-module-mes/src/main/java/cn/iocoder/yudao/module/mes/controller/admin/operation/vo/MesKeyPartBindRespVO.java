package cn.iocoder.yudao.module.mes.controller.admin.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 关键件绑定响应 Response VO")
@Data
public class MesKeyPartBindRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "工单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long workOrderId;

    @Schema(description = "作业记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long operationRecordId;

    @Schema(description = "车辆VIN码", requiredMode = Schema.RequiredMode.REQUIRED, example = "LSVAU2180N2123456")
    private String vin;

    @Schema(description = "零部件编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "PART001")
    private String partCode;

    @Schema(description = "零部件名称", example = "发动机")
    private String partName;

    @Schema(description = "零部件序列号", requiredMode = Schema.RequiredMode.REQUIRED, example = "SN20240325001")
    private String partSn;

    @Schema(description = "供应商编码", example = "SUP001")
    private String supplierCode;

    @Schema(description = "供应商名称", example = "供应商A")
    private String supplierName;

    @Schema(description = "绑定时间")
    private LocalDateTime bindTime;

    @Schema(description = "绑定工位ID", example = "1")
    private Long workstationId;

    @Schema(description = "操作员ID", example = "1")
    private Long operatorId;

    @Schema(description = "操作员姓名", example = "张三")
    private String operatorName;

    @Schema(description = "备注", example = "关键件绑定")
    private String remark;

}