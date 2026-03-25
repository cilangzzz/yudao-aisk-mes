package cn.iocoder.yudao.module.mes.controller.admin.routing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - MES工序新增/修改 Request VO")
@Data
public class MesOperationSaveReqVO {

    @Schema(description = "编号", example = "1")
    private Long id;

    @Schema(description = "工序编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "OP001")
    @NotBlank(message = "工序编码不能为空")
    private String operationCode;

    @Schema(description = "工序名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "点焊")
    @NotBlank(message = "工序名称不能为空")
    private String operationName;

    @Schema(description = "工序顺序", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "工序顺序不能为空")
    private Integer sequence;

    @Schema(description = "默认工作站ID", example = "1")
    private Long workstationId;

    @Schema(description = "工作站名称", example = "焊接工位1")
    private String workstationName;

    @Schema(description = "标准工时(分钟)", example = "30.5")
    private BigDecimal standardTime;

    @Schema(description = "工序描述", example = "车身点焊工序")
    private String description;

    @Schema(description = "是否关键工序:0-否,1-是", example = "0")
    private Integer keyOperation;

    @Schema(description = "是否需要质检:0-否,1-是", example = "0")
    private Integer qualityCheck;

    @Schema(description = "作业指导内容", example = "按照标准操作流程进行焊接")
    private String instruction;

    @Schema(description = "作业指导文件URL", example = "http://xxx/file.pdf")
    private String instructionFile;

    @Schema(description = "物料列表")
    @Valid
    private List<MesOperationMaterialSaveReqVO> materials;

}