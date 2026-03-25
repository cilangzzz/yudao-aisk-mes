package cn.iocoder.yudao.module.mes.controller.admin.material;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.material.vo.MesLineStockInReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.material.vo.MesLineStockPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.material.vo.MesLineStockRespVO;
import cn.iocoder.yudao.module.mes.controller.admin.material.vo.MesMaterialConsumeReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.material.MesLineStockDO;
import cn.iocoder.yudao.module.mes.service.material.MesLineStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - MES线边库存")
@RestController
@RequestMapping("/mes/stock")
@Validated
public class MesLineStockController {

    @Resource
    private MesLineStockService lineStockService;

    @GetMapping("/page")
    @Operation(summary = "获得线边库存分页")
    @PreAuthorize("@ss.hasPermission('mes:stock:query')")
    public CommonResult<PageResult<MesLineStockRespVO>> getLineStockPage(@Valid MesLineStockPageReqVO pageReqVO) {
        PageResult<MesLineStockDO> pageResult = lineStockService.getLineStockPage(pageReqVO);
        return success(convertToRespVOPage(pageResult));
    }

    @GetMapping("/list-by-workstation")
    @Operation(summary = "按工位查询库存")
    @Parameter(name = "workstationId", description = "工位ID", required = true)
    @PreAuthorize("@ss.hasPermission('mes:stock:query')")
    public CommonResult<List<MesLineStockRespVO>> getLineStockListByWorkstation(@RequestParam("workstationId") Long workstationId) {
        List<MesLineStockDO> list = lineStockService.getLineStockListByWorkstation(workstationId);
        return success(convertToRespVOList(list));
    }

    @GetMapping("/warning-list")
    @Operation(summary = "获得缺料预警列表")
    @PreAuthorize("@ss.hasPermission('mes:stock:query')")
    public CommonResult<List<MesLineStockRespVO>> getWarningList() {
        List<MesLineStockDO> list = lineStockService.getWarningList();
        return success(convertToRespVOList(list));
    }

    @PostMapping("/consume")
    @Operation(summary = "物料消耗")
    @PreAuthorize("@ss.hasPermission('mes:stock:update')")
    public CommonResult<Boolean> consumeMaterial(@Valid @RequestBody MesMaterialConsumeReqVO reqVO) {
        lineStockService.consumeMaterial(reqVO);
        return success(true);
    }

    @PostMapping("/in")
    @Operation(summary = "线边物料入库")
    @PreAuthorize("@ss.hasPermission('mes:stock:update')")
    public CommonResult<Boolean> stockIn(@Valid @RequestBody MesLineStockInReqVO reqVO) {
        lineStockService.stockIn(reqVO);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得线边库存")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:stock:query')")
    public CommonResult<MesLineStockRespVO> getLineStock(@RequestParam("id") Long id) {
        MesLineStockDO stock = lineStockService.getLineStock(id);
        return success(convertToRespVO(stock));
    }

    /**
     * 转换为响应VO
     */
    private MesLineStockRespVO convertToRespVO(MesLineStockDO stock) {
        if (stock == null) {
            return null;
        }
        MesLineStockRespVO respVO = BeanUtils.toBean(stock, MesLineStockRespVO.class);
        // 计算是否缺料预警
        if (stock.getQty() != null && stock.getSafetyQty() != null) {
            respVO.setWarning(stock.getQty().compareTo(stock.getSafetyQty()) < 0);
        }
        return respVO;
    }

    /**
     * 转换为响应VO列表
     */
    private List<MesLineStockRespVO> convertToRespVOList(List<MesLineStockDO> list) {
        List<MesLineStockRespVO> result = new java.util.ArrayList<>();
        for (MesLineStockDO stock : list) {
            result.add(convertToRespVO(stock));
        }
        return result;
    }

    /**
     * 转换为分页响应VO
     */
    private PageResult<MesLineStockRespVO> convertToRespVOPage(PageResult<MesLineStockDO> pageResult) {
        List<MesLineStockRespVO> list = convertToRespVOList(pageResult.getList());
        return new PageResult<>(list, pageResult.getTotal());
    }

}