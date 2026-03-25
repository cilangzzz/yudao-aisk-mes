package cn.iocoder.yudao.module.mes.controller.admin.workstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 工作站 Response VO")
@Data
public class WorkstationRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "工作站编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "WS001")
    private String workstationCode;

    @Schema(description = "工作站名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "工位A01")
    private String workstationName;

    @Schema(description = "车间ID", example = "1")
    private Long workshopId;

    @Schema(description = "车间名称", example = "总装车间")
    private String workshopName;

    @Schema(description = "产线ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long lineId;

    @Schema(description = "产线名称", example = "主线A")
    private String lineName;

    @Schema(description = "工作站类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Integer workstationType;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Integer status;

    @Schema(description = "关联设备ID列表", example = "[1, 2, 3]")
    private List<Long> equipmentIds;

    @Schema(description = "描述", example = "这是工位A01")
    private String description;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}