package cn.iocoder.yudao.module.mes.controller.admin.productionline.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 产线分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductionLinePageReqVO extends PageParam {

    @Schema(description = "产线编码", example = "LINE001")
    private String lineCode;

    @Schema(description = "产线名称", example = "主线A")
    private String lineName;

    @Schema(description = "车间ID", example = "1")
    private Long workshopId;

    @Schema(description = "状态", example = "0")
    private Integer status;

}