package cn.iocoder.yudao.module.mes.controller.admin.team.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Schema(description = "管理后台 - 班组新增/修改 Request VO")
@Data
public class TeamSaveReqVO {

    @Schema(description = "编号", example = "1")
    private Long id;

    @Schema(description = "班组编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "TEAM001")
    @NotBlank(message = "班组编码不能为空")
    private String teamCode;

    @Schema(description = "班组名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "甲班")
    @NotBlank(message = "班组名称不能为空")
    private String teamName;

    @Schema(description = "产线ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long lineId;

    @Schema(description = "班组长用户ID", example = "1")
    private Long leaderId;

    @Schema(description = "班组长姓名", example = "张三")
    private String leaderName;

    @Schema(description = "状态", example = "0")
    private Integer status;

}