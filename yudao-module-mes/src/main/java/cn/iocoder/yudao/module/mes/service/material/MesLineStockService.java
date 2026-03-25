package cn.iocoder.yudao.module.mes.service.material;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mes.controller.admin.material.vo.MesLineStockInReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.material.vo.MesLineStockPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.material.vo.MesMaterialConsumePageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.material.vo.MesMaterialConsumeReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.material.MesLineStockDO;

import javax.validation.Valid;
import java.util.List;

/**
 * MES 线边库存 Service 接口
 *
 * @author 芋道源码
 */
public interface MesLineStockService {

    /**
     * 获得线边库存分页
     *
     * @param pageReqVO 分页查询
     * @return 线边库存分页
     */
    PageResult<MesLineStockDO> getLineStockPage(MesLineStockPageReqVO pageReqVO);

    /**
     * 获得线边库存列表（按工位）
     *
     * @param workstationId 工位ID
     * @return 线边库存列表
     */
    List<MesLineStockDO> getLineStockListByWorkstation(Long workstationId);

    /**
     * 获得缺料预警列表
     *
     * @return 缺料预警列表
     */
    List<MesLineStockDO> getWarningList();

    /**
     * 物料消耗（扣减库存）
     *
     * @param reqVO 消耗信息
     */
    void consumeMaterial(@Valid MesMaterialConsumeReqVO reqVO);

    /**
     * 线边物料入库
     *
     * @param reqVO 入库信息
     */
    void stockIn(@Valid MesLineStockInReqVO reqVO);

    /**
     * 获得线边库存
     *
     * @param id 编号
     * @return 线边库存
     */
    MesLineStockDO getLineStock(Long id);

}