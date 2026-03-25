package cn.iocoder.yudao.module.mes.controller.admin.material.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - MES线边库存分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class MesLineStockPageReqVO extends PageParam {

    @Schema(description = "物料编码", example = "MAT001")
    private String materialCode;

    @Schema(description = "物料名称", example = "焊丝")
    private String materialName;

    @Schema(description = "工位ID", example = "1")
    private Long workstationId;

}