package cn.iocoder.yudao.module.mes.controller.admin.workshop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 车间新增/修改 Request VO")
@Data
public class WorkshopSaveReqVO {

    @Schema(description = "编号", example = "1")
    private Long id;

    @Schema(description = "车间编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "WS001")
    @NotBlank(message = "车间编码不能为空")
    private String workshopCode;

    @Schema(description = "车间名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "总装车间")
    @NotBlank(message = "车间名称不能为空")
    private String workshopName;

    @Schema(description = "父级ID", example = "0")
    private Long parentId;

    @Schema(description = "显示顺序", example = "1")
    private Integer sort;

    @Schema(description = "负责人用户ID", example = "1")
    private Long leaderUserId;

    @Schema(description = "状态", example = "0")
    private Integer status;

    @Schema(description = "描述", example = "这是总装车间")
    private String description;

}