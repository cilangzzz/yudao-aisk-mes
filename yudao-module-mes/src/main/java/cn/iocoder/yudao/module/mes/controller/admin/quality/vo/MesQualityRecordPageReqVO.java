package cn.iocoder.yudao.module.mes.controller.admin.quality.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 质量检验记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class MesQualityRecordPageReqVO extends PageParam {

    @Schema(description = "车辆VIN", example = "LSVXXXXXXXXXXXXXXX")
    private String vin;

    @Schema(description = "工单ID", example = "1")
    private Long workOrderId;

    @Schema(description = "检验类型", example = "0")
    private Integer checkType;

    @Schema(description = "检验项目名称", example = "外观检查")
    private String checkItemName;

    @Schema(description = "检验结果", example = "0")
    private Integer result;

    @Schema(description = "检验时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] checkTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}