package cn.iocoder.yudao.module.mes.controller.admin.material.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - MES物料消耗记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class MesMaterialConsumePageReqVO extends PageParam {

    @Schema(description = "车辆VIN", example = "LSVAU2180N2181234")
    private String vin;

    @Schema(description = "工单ID", example = "1")
    private Long workOrderId;

    @Schema(description = "物料编码", example = "MAT001")
    private String materialCode;

    @Schema(description = "消耗工位ID", example = "1")
    private Long workstationId;

    @Schema(description = "消耗时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] consumeTime;

}