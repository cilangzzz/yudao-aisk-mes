package cn.iocoder.yudao.module.mes.controller.admin.team.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 班组分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class TeamPageReqVO extends PageParam {

    @Schema(description = "班组编码", example = "TEAM001")
    private String teamCode;

    @Schema(description = "班组名称", example = "甲班")
    private String teamName;

    @Schema(description = "产线ID", example = "1")
    private Long lineId;

    @Schema(description = "状态", example = "0")
    private Integer status;

}