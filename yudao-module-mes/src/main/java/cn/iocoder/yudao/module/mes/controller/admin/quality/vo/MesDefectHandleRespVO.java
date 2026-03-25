package cn.iocoder.yudao.module.mes.controller.admin.quality.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 不合格处理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MesDefectHandleRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "质量记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long qualityRecordId;

    @Schema(description = "车辆VIN", requiredMode = Schema.RequiredMode.REQUIRED, example = "LSVXXXXXXXXXXXXXXX")
    @ExcelProperty("车辆VIN")
    private String vin;

    @Schema(description = "缺陷类型", example = "外观缺陷")
    @ExcelProperty("缺陷类型")
    private String defectType;

    @Schema(description = "缺陷描述", requiredMode = Schema.RequiredMode.REQUIRED, example = "外观划伤")
    @ExcelProperty("缺陷描述")
    private String defectDesc;

    @Schema(description = "处理方式", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @ExcelProperty("处理方式")
    private Integer handleType;

    @Schema(description = "处理状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @ExcelProperty("处理状态")
    private Integer handleStatus;

    @Schema(description = "处理人ID", example = "1")
    private Long handlerId;

    @Schema(description = "处理人姓名", example = "李四")
    @ExcelProperty("处理人姓名")
    private String handlerName;

    @Schema(description = "处理时间")
    @ExcelProperty("处理时间")
    private LocalDateTime handleTime;

    @Schema(description = "处理结果", example = "已返修完成")
    @ExcelProperty("处理结果")
    private String handleResult;

    @Schema(description = "验证人ID", example = "1")
    private Long verifierId;

    @Schema(description = "验证人姓名", example = "王五")
    @ExcelProperty("验证人姓名")
    private String verifierName;

    @Schema(description = "验证时间")
    @ExcelProperty("验证时间")
    private LocalDateTime verifyTime;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}