package cn.iocoder.yudao.module.mes.controller.admin.trace.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 作业记录详情响应 VO
 */
@Schema(description = "管理后台 - 作业记录详情响应 VO")
@Data
public class MesOperationRecordDetailRespVO {

    @Schema(description = "记录ID")
    private Long id;

    @Schema(description = "VIN码")
    private String vin;

    @Schema(description = "工单编号")
    private String workOrderNo;

    @Schema(description = "工序ID")
    private Long operationId;

    @Schema(description = "工序编码")
    private String operationCode;

    @Schema(description = "工序名称")
    private String operationName;

    @Schema(description = "工位ID")
    private Long workstationId;

    @Schema(description = "工位编码")
    private String workstationCode;

    @Schema(description = "工位名称")
    private String workstationName;

    @Schema(description = "操作员ID")
    private Long operatorId;

    @Schema(description = "操作员姓名")
    private String operatorName;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    @Schema(description = "作业时长(秒)")
    private Integer duration;

    @Schema(description = "作业状态:0-进行中,1-已完成,2-异常")
    private Integer status;

    @Schema(description = "状态名称")
    private String statusName;

    @Schema(description = "作业结果:0-合格,1-不合格")
    private Integer result;

    @Schema(description = "结果名称")
    private String resultName;

    @Schema(description = "扭矩值(N·m)")
    private BigDecimal torqueValue;

    @Schema(description = "扭矩判定:0-合格,1-不合格")
    private Integer torqueResult;

    @Schema(description = "备注")
    private String remark;

}