package cn.iocoder.yudao.module.mes.controller.admin.workstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "管理后台 - 工作站新增/修改 Request VO")
@Data
public class WorkstationSaveReqVO {

    @Schema(description = "编号", example = "1")
    private Long id;

    @Schema(description = "工作站编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "WS001")
    @NotBlank(message = "工作站编码不能为空")
    private String workstationCode;

    @Schema(description = "工作站名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "工位A01")
    @NotBlank(message = "工作站名称不能为空")
    private String workstationName;

    @Schema(description = "车间ID", example = "1")
    private Long workshopId;

    @Schema(description = "产线ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "产线ID不能为空")
    private Long lineId;

    @Schema(description = "工作站类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "工作站类型不能为空")
    private Integer workstationType;

    @Schema(description = "状态", example = "0")
    private Integer status;

    @Schema(description = "关联设备ID列表", example = "[1, 2, 3]")
    private List<Long> equipmentIds;

    @Schema(description = "描述", example = "这是工位A01")
    private String description;

}