package cn.iocoder.yudao.module.mes.controller.admin.operation.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 作业记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class MesOperationRecordPageReqVO extends PageParam {

    @Schema(description = "工单ID", example = "1")
    private Long workOrderId;

    @Schema(description = "工单编号", example = "WO202603250001")
    private String workOrderNo;

    @Schema(description = "VIN码", example = "LSVNV2182E2100001")
    private String vin;

    @Schema(description = "工序编码", example = "OP001")
    private String operationCode;

    @Schema(description = "工序名称", example = "总装")
    private String operationName;

    @Schema(description = "工作站ID", example = "1")
    private Long workstationId;

    @Schema(description = "操作员ID", example = "1")
    private Long operatorId;

    @Schema(description = "状态", example = "0")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
