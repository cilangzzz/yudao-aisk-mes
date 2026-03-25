package cn.iocoder.yudao.module.mes.controller.admin.routing.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - MES工艺路线分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class MesRoutingPageReqVO extends PageParam {

    @Schema(description = "工艺路线编码", example = "RT001")
    private String routingCode;

    @Schema(description = "工艺路线名称", example = "车身焊接工艺")
    private String routingName;

    @Schema(description = "产品编码", example = "PROD001")
    private String productCode;

    @Schema(description = "状态:0-草稿,1-生效,2-失效", example = "0")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}