package cn.iocoder.yudao.module.mes.controller.admin.workorder.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 生产工单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class MesWorkOrderPageReqVO extends PageParam {

    @Schema(description = "工单编号", example = "WO202603250001")
    private String orderNo;

    @Schema(description = "ERP订单编号", example = "ERP001")
    private String erpOrderNo;

    @Schema(description = "产品编码", example = "P001")
    private String productCode;

    @Schema(description = "产品名称", example = "汽车A")
    private String productName;

    @Schema(description = "状态", example = "0")
    private Integer status;

    @Schema(description = "产线ID", example = "1")
    private Long lineId;

    @Schema(description = "车间ID", example = "1")
    private Long workshopId;

    @Schema(description = "计划开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] planStartTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}