package cn.iocoder.yudao.module.mes.controller.admin.workshop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 车间 Response VO")
@Data
public class WorkshopRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "车间编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "WS001")
    private String workshopCode;

    @Schema(description = "车间名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "总装车间")
    private String workshopName;

    @Schema(description = "父级ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Long parentId;

    @Schema(description = "显示顺序", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer sort;

    @Schema(description = "负责人用户ID", example = "1")
    private Long leaderUserId;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Integer status;

    @Schema(description = "描述", example = "这是总装车间")
    private String description;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    // ========== 子车间 ==========

    @Schema(description = "子车间列表")
    private List<WorkshopRespVO> children;

}