package cn.iocoder.yudao.module.mes.service.material;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.material.vo.MesLineStockInReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.material.vo.MesLineStockPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.material.vo.MesMaterialConsumeReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.material.MesLineStockDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.material.MesMaterialConsumeDO;
import cn.iocoder.yudao.module.mes.dal.mysql.material.MesLineStockMapper;
import cn.iocoder.yudao.module.mes.dal.mysql.material.MesMaterialConsumeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.*;

/**
 * MES 线边库存 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MesLineStockServiceImpl implements MesLineStockService {

    @Resource
    private MesLineStockMapper lineStockMapper;

    @Resource
    private MesMaterialConsumeMapper materialConsumeMapper;

    @Override
    public PageResult<MesLineStockDO> getLineStockPage(MesLineStockPageReqVO pageReqVO) {
        return lineStockMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MesLineStockDO> getLineStockListByWorkstation(Long workstationId) {
        return lineStockMapper.selectListByWorkstationId(workstationId);
    }

    @Override
    public List<MesLineStockDO> getWarningList() {
        return lineStockMapper.selectWarningList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void consumeMaterial(MesMaterialConsumeReqVO reqVO) {
        // 1. 查询线边库存
        MesLineStockDO stock = lineStockMapper.selectByMaterialCodeAndWorkstationId(
                reqVO.getMaterialCode(), reqVO.getWorkstationId());
        if (stock == null) {
            throw exception(STOCK_NOT_EXISTS);
        }

        // 2. 校验库存是否充足（防止负库存）
        if (stock.getQty().compareTo(reqVO.getQty()) < 0) {
            throw exception(STOCK_NOT_ENOUGH, stock.getQty(), reqVO.getQty());
        }

        // 3. 扣减库存
        BigDecimal newQty = stock.getQty().subtract(reqVO.getQty());
        stock.setQty(newQty);
        stock.setLastUpdateTime(LocalDateTime.now());
        lineStockMapper.updateById(stock);

        // 4. 创建消耗记录
        MesMaterialConsumeDO consume = BeanUtils.toBean(reqVO, MesMaterialConsumeDO.class);
        consume.setConsumeTime(LocalDateTime.now());
        materialConsumeMapper.insert(consume);

        // 5. 检查是否触发缺料预警
        if (newQty.compareTo(stock.getSafetyQty()) < 0) {
            // TODO: 触发预警（可扩展为发送消息、通知等）
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stockIn(MesLineStockInReqVO reqVO) {
        // 1. 查询是否已存在库存记录
        MesLineStockDO stock = lineStockMapper.selectByMaterialCodeAndWorkstationId(
                reqVO.getMaterialCode(), reqVO.getWorkstationId());

        if (stock == null) {
            // 2. 不存在则创建新记录
            stock = BeanUtils.toBean(reqVO, MesLineStockDO.class);
            stock.setLastUpdateTime(LocalDateTime.now());
            lineStockMapper.insert(stock);
        } else {
            // 3. 存在则增加库存
            stock.setQty(stock.getQty().add(reqVO.getQty()));
            stock.setLastUpdateTime(LocalDateTime.now());
            // 更新安全库存（如果传入了新值）
            if (reqVO.getSafetyQty() != null) {
                stock.setSafetyQty(reqVO.getSafetyQty());
            }
            lineStockMapper.updateById(stock);
        }
    }

    @Override
    public MesLineStockDO getLineStock(Long id) {
        return lineStockMapper.selectById(id);
    }

}