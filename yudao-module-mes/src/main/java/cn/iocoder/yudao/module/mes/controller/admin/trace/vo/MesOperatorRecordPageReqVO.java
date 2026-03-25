package cn.iocoder.yudao.module.mes.controller.admin.trace.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 操作员作业记录分页请求 VO
 */
@Schema(description = "管理后台 - 操作员作业记录分页请求 VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MesOperatorRecordPageReqVO extends PageParam {

    @Schema(description = "操作员ID")
    private Long operatorId;

    @Schema(description = "VIN码")
    private String vin;

    @Schema(description = "工单编号")
    private String workOrderNo;

    @Schema(description = "工序名称")
    private String operationName;

    @Schema(description = "作业状态")
    private Integer status;

    @Schema(description = "开始时间-起")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTimeBegin;

    @Schema(description = "开始时间-止")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTimeEnd;

}