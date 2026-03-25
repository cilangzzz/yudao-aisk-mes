package cn.iocoder.yudao.module.mes.controller.admin.routing;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.routing.vo.MesOperationMaterialRespVO;
import cn.iocoder.yudao.module.mes.controller.admin.routing.vo.MesOperationRespVO;
import cn.iocoder.yudao.module.mes.controller.admin.routing.vo.MesRoutingPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.routing.vo.MesRoutingRespVO;
import cn.iocoder.yudao.module.mes.controller.admin.routing.vo.MesRoutingSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.routing.MesOperationDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.routing.MesOperationMaterialDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.routing.MesRoutingDO;
import cn.iocoder.yudao.module.mes.service.routing.MesRoutingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - MES工艺路线")
@RestController
@RequestMapping("/mes/routing")
@Validated
public class MesRoutingController {

    @Resource
    private MesRoutingService routingService;

    @PostMapping("/create")
    @Operation(summary = "创建工艺路线")
    @PreAuthorize("@ss.hasPermission('mes:routing:create')")
    public CommonResult<Long> createRouting(@Valid @RequestBody MesRoutingSaveReqVO createReqVO) {
        return success(routingService.createRouting(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工艺路线")
    @PreAuthorize("@ss.hasPermission('mes:routing:update')")
    public CommonResult<Boolean> updateRouting(@Valid @RequestBody MesRoutingSaveReqVO updateReqVO) {
        routingService.updateRouting(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工艺路线")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:routing:delete')")
    public CommonResult<Boolean> deleteRouting(@RequestParam("id") Long id) {
        routingService.deleteRouting(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工艺路线")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:routing:query')")
    public CommonResult<MesRoutingRespVO> getRouting(@RequestParam("id") Long id) {
        MesRoutingDO routing = routingService.getRouting(id);
        return success(convertToRespVO(routing));
    }

    @GetMapping("/page")
    @Operation(summary = "获得工艺路线分页")
    @PreAuthorize("@ss.hasPermission('mes:routing:query')")
    public CommonResult<PageResult<MesRoutingRespVO>> getRoutingPage(@Valid MesRoutingPageReqVO pageReqVO) {
        PageResult<MesRoutingDO> pageResult = routingService.getRoutingPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MesRoutingRespVO.class));
    }

    @PutMapping("/activate")
    @Operation(summary = "生效工艺路线")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:routing:update')")
    public CommonResult<Boolean> activateRouting(@RequestParam("id") Long id) {
        routingService.activateRouting(id);
        return success(true);
    }

    @PutMapping("/deactivate")
    @Operation(summary = "失效工艺路线")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:routing:update')")
    public CommonResult<Boolean> deactivateRouting(@RequestParam("id") Long id) {
        routingService.deactivateRouting(id);
        return success(true);
    }

    @PostMapping("/copy")
    @Operation(summary = "复制工艺路线")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:routing:create')")
    public CommonResult<Long> copyRouting(@RequestParam("id") Long id) {
        return success(routingService.copyRouting(id));
    }

    /**
     * 转换为响应VO（包含工序列表和物料列表）
     */
    private MesRoutingRespVO convertToRespVO(MesRoutingDO routing) {
        if (routing == null) {
            return null;
        }
        MesRoutingRespVO respVO = BeanUtils.toBean(routing, MesRoutingRespVO.class);
        // 转换工序列表
        if (routing.getOperations() != null && !routing.getOperations().isEmpty()) {
            List<MesOperationRespVO> operations = new ArrayList<>();
            for (MesOperationDO operation : routing.getOperations()) {
                MesOperationRespVO operationVO = BeanUtils.toBean(operation, MesOperationRespVO.class);
                // 转换物料列表
                if (operation.getMaterials() != null && !operation.getMaterials().isEmpty()) {
                    List<MesOperationMaterialRespVO> materials = new ArrayList<>();
                    for (MesOperationMaterialDO material : operation.getMaterials()) {
                        materials.add(BeanUtils.toBean(material, MesOperationMaterialRespVO.class));
                    }
                    operationVO.setMaterials(materials);
                }
                operations.add(operationVO);
            }
            respVO.setOperations(operations);
        }
        return respVO;
    }

}