package cn.iocoder.yudao.module.mes.controller.admin.trace.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 关键件反向追溯响应 VO
 */
@Schema(description = "管理后台 - 关键件反向追溯响应 VO")
@Data
public class MesPartTraceRespVO {

    @Schema(description = "零部件编码")
    private String partCode;

    @Schema(description = "零部件名称")
    private String partName;

    @Schema(description = "零部件序列号")
    private String partSn;

    @Schema(description = "供应商编码")
    private String supplierCode;

    @Schema(description = "供应商名称")
    private String supplierName;

    @Schema(description = "绑定车辆VIN")
    private String bindVin;

    @Schema(description = "绑定时间")
    private LocalDateTime bindTime;

    @Schema(description = "绑定工位ID")
    private Long workstationId;

    @Schema(description = "绑定工位名称")
    private String workstationName;

    @Schema(description = "绑定操作员ID")
    private Long operatorId;

    @Schema(description = "绑定操作员姓名")
    private String operatorName;

    @Schema(description = "工单编号")
    private String workOrderNo;

}