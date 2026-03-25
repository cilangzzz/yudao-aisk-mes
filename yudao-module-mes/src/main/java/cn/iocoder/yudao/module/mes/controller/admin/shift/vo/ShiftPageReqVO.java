package cn.iocoder.yudao.module.mes.controller.admin.shift.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 班次分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ShiftPageReqVO extends PageParam {

    @Schema(description = "班次编码", example = "SHIFT001")
    private String shiftCode;

    @Schema(description = "班次名称", example = "白班")
    private String shiftName;

    @Schema(description = "状态", example = "0")
    private Integer status;

}