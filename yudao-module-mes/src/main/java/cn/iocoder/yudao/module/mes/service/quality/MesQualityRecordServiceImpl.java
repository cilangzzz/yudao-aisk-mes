package cn.iocoder.yudao.module.mes.service.quality;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesQualityRecordPageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesQualityRecordSaveReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.quality.MesQualityRecordDO;
import cn.iocoder.yudao.module.mes.dal.mysql.quality.MesQualityRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.QUALITY_RECORD_NOT_EXISTS;

/**
 * 质量检验记录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MesQualityRecordServiceImpl implements MesQualityRecordService {

    @Resource
    private MesQualityRecordMapper qualityRecordMapper;

    @Override
    public Long createQualityRecord(MesQualityRecordSaveReqVO createReqVO) {
        MesQualityRecordDO qualityRecord = BeanUtils.toBean(createReqVO, MesQualityRecordDO.class);
        qualityRecordMapper.insert(qualityRecord);
        return qualityRecord.getId();
    }

    @Override
    public void updateQualityRecord(MesQualityRecordSaveReqVO updateReqVO) {
        // 1. 校验存在
        validateQualityRecordExists(updateReqVO.getId());
        // 2. 更新数据
        MesQualityRecordDO updateObj = BeanUtils.toBean(updateReqVO, MesQualityRecordDO.class);
        qualityRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteQualityRecord(Long id) {
        // 1. 校验存在
        validateQualityRecordExists(id);
        // 2. 删除
        qualityRecordMapper.deleteById(id);
    }

    @Override
    public MesQualityRecordDO getQualityRecord(Long id) {
        return qualityRecordMapper.selectById(id);
    }

    @Override
    public PageResult<MesQualityRecordDO> getQualityRecordPage(MesQualityRecordPageReqVO pageReqVO) {
        return qualityRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MesQualityRecordDO> getQualityRecordListByVin(String vin) {
        return qualityRecordMapper.selectListByVin(vin);
    }

    @Override
    public MesQualityRecordDO validateQualityRecordExists(Long id) {
        if (id == null) {
            return null;
        }
        MesQualityRecordDO qualityRecord = qualityRecordMapper.selectById(id);
        if (qualityRecord == null) {
            throw exception(QUALITY_RECORD_NOT_EXISTS);
        }
        return qualityRecord;
    }

}