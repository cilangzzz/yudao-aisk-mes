package cn.iocoder.yudao.module.mes.service.productionline;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.productionline.vo.ProductionLinePageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.productionline.vo.ProductionLineSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.productionline.ProductionLineDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.workshop.WorkshopDO;
import cn.iocoder.yudao.module.mes.dal.mysql.productionline.ProductionLineMapper;
import cn.iocoder.yudao.module.mes.service.workshop.WorkshopService;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.*;

/**
 * 产线 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProductionLineServiceImpl implements ProductionLineService {

    @Resource
    private ProductionLineMapper productionLineMapper;

    @Resource
    private WorkshopService workshopService;

    @Override
    public Long createProductionLine(ProductionLineSaveReqVO createReqVO) {
        // 1. 校验编码唯一
        validateLineCodeUnique(null, createReqVO.getLineCode());
        // 2. 校验名称唯一
        validateLineNameUnique(null, createReqVO.getLineName());
        // 3. 校验车间存在
        WorkshopDO workshop = validateWorkshop(createReqVO.getWorkshopId());
        // 4. 插入数据
        ProductionLineDO productionLine = BeanUtils.toBean(createReqVO, ProductionLineDO.class);
        // 设置默认状态
        if (productionLine.getStatus() == null) {
            productionLine.setStatus(CommonStatusEnum.ENABLE.getStatus());
        }
        // 冗余车间名称
        if (workshop != null) {
            productionLine.setWorkshopName(workshop.getWorkshopName());
        }
        productionLineMapper.insert(productionLine);
        return productionLine.getId();
    }

    @Override
    public void updateProductionLine(ProductionLineSaveReqVO updateReqVO) {
        // 1. 校验存在
        validateProductionLineExists(updateReqVO.getId());
        // 2. 校验编码唯一
        validateLineCodeUnique(updateReqVO.getId(), updateReqVO.getLineCode());
        // 3. 校验名称唯一
        validateLineNameUnique(updateReqVO.getId(), updateReqVO.getLineName());
        // 4. 校验车间存在
        WorkshopDO workshop = validateWorkshop(updateReqVO.getWorkshopId());
        // 5. 更新数据
        ProductionLineDO updateObj = BeanUtils.toBean(updateReqVO, ProductionLineDO.class);
        // 冗余车间名称
        if (workshop != null) {
            updateObj.setWorkshopName(workshop.getWorkshopName());
        }
        productionLineMapper.updateById(updateObj);
    }

    @Override
    public void deleteProductionLine(Long id) {
        // 1. 校验存在
        validateProductionLineExists(id);
        // 2. 删除
        productionLineMapper.deleteById(id);
    }

    @Override
    public ProductionLineDO getProductionLine(Long id) {
        return productionLineMapper.selectById(id);
    }

    @Override
    public List<ProductionLineDO> getProductionLineList(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return productionLineMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProductionLineDO> getProductionLinePage(ProductionLinePageReqVO pageReqVO) {
        return productionLineMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductionLineDO> getProductionLineList(ProductionLinePageReqVO pageReqVO) {
        return productionLineMapper.selectList(pageReqVO);
    }

    @Override
    public List<ProductionLineDO> getProductionLineListByWorkshopId(Long workshopId) {
        return productionLineMapper.selectListByWorkshopId(workshopId);
    }

    @Override
    public ProductionLineDO validateProductionLineExists(Long id) {
        if (id == null) {
            return null;
        }
        ProductionLineDO productionLine = productionLineMapper.selectById(id);
        if (productionLine == null) {
            throw exception(PRODUCTION_LINE_NOT_EXISTS);
        }
        return productionLine;
    }

    @Override
    public void validateProductionLineList(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        List<ProductionLineDO> list = productionLineMapper.selectBatchIds(ids);
        for (Long id : ids) {
            ProductionLineDO productionLine = list.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
            if (productionLine == null) {
                throw exception(PRODUCTION_LINE_NOT_EXISTS);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(productionLine.getStatus())) {
                throw exception(PRODUCTION_LINE_DISABLED);
            }
        }
    }

    private WorkshopDO validateWorkshop(Long workshopId) {
        if (workshopId == null) {
            return null;
        }
        WorkshopDO workshop = workshopService.getWorkshop(workshopId);
        if (workshop == null) {
            throw exception(WORKSHOP_NOT_EXISTS);
        }
        if (!CommonStatusEnum.ENABLE.getStatus().equals(workshop.getStatus())) {
            throw exception(PRODUCTION_LINE_WORKSHOP_DISABLED);
        }
        return workshop;
    }

    @VisibleForTesting
    void validateLineCodeUnique(Long id, String lineCode) {
        if (lineCode == null) {
            return;
        }
        ProductionLineDO productionLine = productionLineMapper.selectByLineCode(lineCode);
        if (productionLine == null) {
            return;
        }
        if (id == null) {
            throw exception(PRODUCTION_LINE_CODE_DUPLICATE);
        }
        if (!productionLine.getId().equals(id)) {
            throw exception(PRODUCTION_LINE_CODE_DUPLICATE);
        }
    }

    @VisibleForTesting
    void validateLineNameUnique(Long id, String lineName) {
        if (lineName == null) {
            return;
        }
        ProductionLineDO productionLine = productionLineMapper.selectByLineName(lineName);
        if (productionLine == null) {
            return;
        }
        if (id == null) {
            throw exception(PRODUCTION_LINE_NAME_DUPLICATE);
        }
        if (!productionLine.getId().equals(id)) {
            throw exception(PRODUCTION_LINE_NAME_DUPLICATE);
        }
    }

}