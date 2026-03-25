package cn.iocoder.yudao.module.mes.controller.admin.quality.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 不合格处理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class MesDefectHandlePageReqVO extends PageParam {

    @Schema(description = "车辆VIN", example = "LSVXXXXXXXXXXXXXXX")
    private String vin;

    @Schema(description = "质量记录ID", example = "1")
    private Long qualityRecordId;

    @Schema(description = "处理方式", example = "0")
    private Integer handleType;

    @Schema(description = "处理状态", example = "0")
    private Integer handleStatus;

    @Schema(description = "缺陷类型", example = "外观缺陷")
    private String defectType;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}