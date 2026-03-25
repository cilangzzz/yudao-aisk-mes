package cn.iocoder.yudao.module.mes.controller.admin.quality;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesQualityRecordPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesQualityRecordRespVO;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesQualityRecordSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.quality.MesQualityRecordDO;
import cn.iocoder.yudao.module.mes.service.quality.MesQualityRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 质量检验记录")
@RestController
@RequestMapping("/mes/quality")
@Validated
public class MesQualityRecordController {

    @Resource
    private MesQualityRecordService qualityRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建质量检验记录")
    @PreAuthorize("@ss.hasPermission('mes:quality:create')")
    public CommonResult<Long> createQualityRecord(@Valid @RequestBody MesQualityRecordSaveReqVO createReqVO) {
        return success(qualityRecordService.createQualityRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新质量检验记录")
    @PreAuthorize("@ss.hasPermission('mes:quality:update')")
    public CommonResult<Boolean> updateQualityRecord(@Valid @RequestBody MesQualityRecordSaveReqVO updateReqVO) {
        qualityRecordService.updateQualityRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除质量检验记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:quality:delete')")
    public CommonResult<Boolean> deleteQualityRecord(@RequestParam("id") Long id) {
        qualityRecordService.deleteQualityRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得质量检验记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:quality:query')")
    public CommonResult<MesQualityRecordRespVO> getQualityRecord(@RequestParam("id") Long id) {
        MesQualityRecordDO qualityRecord = qualityRecordService.getQualityRecord(id);
        return success(BeanUtils.toBean(qualityRecord, MesQualityRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得质量检验记录分页")
    @PreAuthorize("@ss.hasPermission('mes:quality:query')")
    public CommonResult<PageResult<MesQualityRecordRespVO>> getQualityRecordPage(@Valid MesQualityRecordPageReqVO pageReqVO) {
        PageResult<MesQualityRecordDO> pageResult = qualityRecordService.getQualityRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MesQualityRecordRespVO.class));
    }

    @GetMapping("/list-by-vin")
    @Operation(summary = "根据VIN获得质量检验记录列表")
    @Parameter(name = "vin", description = "车辆VIN", required = true)
    @PreAuthorize("@ss.hasPermission('mes:quality:query')")
    public CommonResult<List<MesQualityRecordRespVO>> getQualityRecordListByVin(@RequestParam("vin") String vin) {
        List<MesQualityRecordDO> list = qualityRecordService.getQualityRecordListByVin(vin);
        return success(BeanUtils.toBean(list, MesQualityRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出质量检验记录 Excel")
    @PreAuthorize("@ss.hasPermission('mes:quality:export')")
    public void exportQualityRecordExcel(@Valid MesQualityRecordPageReqVO pageReqVO,
                                         HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<MesQualityRecordDO> pageResult = qualityRecordService.getQualityRecordPage(pageReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "质量检验记录.xls", "数据", MesQualityRecordRespVO.class,
                BeanUtils.toBean(pageResult.getList(), MesQualityRecordRespVO.class));
    }

}