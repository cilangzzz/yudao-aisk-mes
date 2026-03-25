package cn.iocoder.yudao.module.mes.controller.admin.productbom.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 产品BOM分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductBomPageReqVO extends PageParam {

    @Schema(description = "产品ID", example = "1")
    private Long productId;

    @Schema(description = "物料编码", example = "MAT001")
    private String materialCode;

    @Schema(description = "物料名称", example = "发动机")
    private String materialName;

}