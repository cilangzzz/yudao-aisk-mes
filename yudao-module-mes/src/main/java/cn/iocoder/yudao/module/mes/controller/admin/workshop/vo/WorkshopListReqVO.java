package cn.iocoder.yudao.module.mes.controller.admin.workshop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 车间列表 Request VO")
@Data
public class WorkshopListReqVO {

    @Schema(description = "车间编码", example = "WS001")
    private String workshopCode;

    @Schema(description = "车间名称", example = "总装车间")
    private String workshopName;

    @Schema(description = "状态", example = "0")
    private Integer status;

}