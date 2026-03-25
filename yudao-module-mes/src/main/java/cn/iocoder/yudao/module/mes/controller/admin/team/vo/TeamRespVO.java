package cn.iocoder.yudao.module.mes.controller.admin.team.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 班组 Response VO")
@Data
public class TeamRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "班组编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "TEAM001")
    private String teamCode;

    @Schema(description = "班组名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "甲班")
    private String teamName;

    @Schema(description = "产线ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long lineId;

    @Schema(description = "产线名称", example = "主线A")
    private String lineName;

    @Schema(description = "班组长用户ID", example = "1")
    private Long leaderId;

    @Schema(description = "班组长姓名", example = "张三")
    private String leaderName;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}