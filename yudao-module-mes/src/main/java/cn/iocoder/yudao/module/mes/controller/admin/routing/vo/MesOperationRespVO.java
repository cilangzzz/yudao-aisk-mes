package cn.iocoder.yudao.module.mes.controller.admin.routing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - MES工序 Response VO")
@Data
public class MesOperationRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "工序编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "OP001")
    private String operationCode;

    @Schema(description = "工序名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "点焊")
    private String operationName;

    @Schema(description = "工序顺序", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
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

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "物料列表")
    private List<MesOperationMaterialRespVO> materials;

}