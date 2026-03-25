package cn.iocoder.yudao.module.mes.controller.admin.routing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "管理后台 - MES工艺路线新增/修改 Request VO")
@Data
public class MesRoutingSaveReqVO {

    @Schema(description = "编号", example = "1")
    private Long id;

    @Schema(description = "工艺路线编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "RT001")
    @NotBlank(message = "工艺路线编码不能为空")
    private String routingCode;

    @Schema(description = "工艺路线名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "车身焊接工艺")
    @NotBlank(message = "工艺路线名称不能为空")
    private String routingName;

    @Schema(description = "关联产品ID", example = "1")
    private Long productId;

    @Schema(description = "产品编码", example = "PROD001")
    private String productCode;

    @Schema(description = "产品名称", example = "汽车车身")
    private String productName;

    @Schema(description = "版本号", requiredMode = Schema.RequiredMode.REQUIRED, example = "V1.0")
    @NotBlank(message = "版本号不能为空")
    private String version;

    @Schema(description = "描述", example = "车身焊接工艺路线")
    private String description;

    @Schema(description = "工序列表")
    @Valid
    private List<MesOperationSaveReqVO> operations;

}