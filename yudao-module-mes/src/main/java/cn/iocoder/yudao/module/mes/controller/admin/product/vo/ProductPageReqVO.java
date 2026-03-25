package cn.iocoder.yudao.module.mes.controller.admin.product.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 产品分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductPageReqVO extends PageParam {

    @Schema(description = "产品编码", example = "PROD001")
    private String productCode;

    @Schema(description = "产品名称", example = "SUV整车")
    private String productName;

    @Schema(description = "产品类型", example = "整车")
    private String productType;

    @Schema(description = "状态", example = "0")
    private Integer status;

}