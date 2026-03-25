package cn.iocoder.yudao.module.mes.service.quality;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesQualityRecordPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesQualityRecordSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.quality.MesQualityRecordDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 质量检验记录 Service 接口
 *
 * @author 芋道源码
 */
public interface MesQualityRecordService {

    /**
     * 创建质量检验记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createQualityRecord(@Valid MesQualityRecordSaveReqVO createReqVO);

    /**
     * 更新质量检验记录
     *
     * @param updateReqVO 更新信息
     */
    void updateQualityRecord(@Valid MesQualityRecordSaveReqVO updateReqVO);

    /**
     * 删除质量检验记录
     *
     * @param id 编号
     */
    void deleteQualityRecord(Long id);

    /**
     * 获得质量检验记录
     *
     * @param id 编号
     * @return 质量检验记录
     */
    MesQualityRecordDO getQualityRecord(Long id);

    /**
     * 获得质量检验记录分页
     *
     * @param pageReqVO 分页查询
     * @return 质量检验记录分页
     */
    PageResult<MesQualityRecordDO> getQualityRecordPage(MesQualityRecordPageReqVO pageReqVO);

    /**
     * 根据VIN获取质量检验记录列表
     *
     * @param vin 车辆VIN
     * @return 检验记录列表
     */
    List<MesQualityRecordDO> getQualityRecordListByVin(String vin);

    /**
     * 校验质量检验记录是否存在
     *
     * @param id 编号
     * @return 检验记录
     */
    MesQualityRecordDO validateQualityRecordExists(Long id);

}