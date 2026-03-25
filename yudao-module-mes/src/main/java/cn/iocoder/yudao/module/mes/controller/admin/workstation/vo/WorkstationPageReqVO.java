package cn.iocoder.yudao.module.mes.controller.admin.workstation.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 工作站分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class WorkstationPageReqVO extends PageParam {

    @Schema(description = "工作站编码", example = "WS001")
    private String workstationCode;

    @Schema(description = "工作站名称", example = "工位A01")
    private String workstationName;

    @Schema(description = "车间ID", example = "1")
    private Long workshopId;

    @Schema(description = "产线ID", example = "1")
    private Long lineId;

    @Schema(description = "工作站类型", example = "0")
    private Integer workstationType;

    @Schema(description = "状态", example = "0")
    private Integer status;

}