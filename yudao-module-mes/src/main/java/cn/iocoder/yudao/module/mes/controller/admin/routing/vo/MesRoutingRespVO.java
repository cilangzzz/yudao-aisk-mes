package cn.iocoder.yudao.module.mes.controller.admin.routing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - MES工艺路线 Response VO")
@Data
public class MesRoutingRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "工艺路线编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "RT001")
    private String routingCode;

    @Schema(description = "工艺路线名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "车身焊接工艺")
    private String routingName;

    @Schema(description = "关联产品ID", example = "1")
    private Long productId;

    @Schema(description = "产品编码", example = "PROD001")
    private String productCode;

    @Schema(description = "产品名称", example = "汽车车身")
    private String productName;

    @Schema(description = "版本号", requiredMode = Schema.RequiredMode.REQUIRED, example = "V1.0")
    private String version;

    @Schema(description = "状态:0-草稿,1-生效,2-失效", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Integer status;

    @Schema(description = "描述", example = "车身焊接工艺路线")
    private String description;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "工序列表")
    private List<MesOperationRespVO> operations;

}