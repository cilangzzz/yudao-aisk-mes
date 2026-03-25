package cn.iocoder.yudao.module.mes.controller.admin.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 关键件绑定 Response VO")
@Data
public class MesKeyPartBindRespVO {

    @Schema(description = "编号", example = "1")
    private Long id;

    @Schema(description = "工单ID", example = "1")
    private Long workOrderId;

    @Schema(description = "VIN码", example = "LSVNV2182E2100001")
    private String vin;

    @Schema(description = "零部件编码", example = "P001")
    private String partCode;

    @Schema(description = "零部件名称", example = "发动机")
    private String partName;

    @Schema(description = "零部件序列号", example = "SN001")
    private String partSn;

    @Schema(description = "供应商编码", example = "S001")
    private String supplierCode;

    @Schema(description = "供应商名称", example = "供应商A")
    private String supplierName;

    @Schema(description = "绑定时间")
    private LocalDateTime bindTime;

    @Schema(description = "绑定工位ID", example = "1")
    private Long workstationId;

    @Schema(description = "操作员姓名", example = "张三")
    private String operatorName;

}
