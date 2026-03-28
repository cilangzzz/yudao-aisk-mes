package cn.iocoder.yudao.module.mes.controller.admin.routing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 工艺路线精简信息 Response VO")
@Data
public class MesRoutingSimpleRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "工艺路线编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "RT001")
    private String routingCode;

    @Schema(description = "工艺路线名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "车身焊接工艺")
    private String routingName;

    @Schema(description = "状态:0-草稿,1-生效,2-失效", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer status;

    @Schema(description = "产品ID", example = "1")
    private Long productId;

    @Schema(description = "产品名称", example = "汽车车身")
    private String productName;

}