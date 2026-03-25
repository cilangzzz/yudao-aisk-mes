package cn.iocoder.yudao.module.mes.service.quality;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesDefectHandleCreateReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesDefectHandlePageReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesDefectHandleReqVO;
import cn.iocoder.yudao.module.mes.controller.admin.quality.vo.MesDefectHandleVerifyReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.quality.MesDefectHandleDO;
import cn.iocoder.yudao.module.mes.dal.dataobject.quality.MesQualityRecordDO;
import cn.iocoder.yudao.module.mes.dal.mysql.quality.MesDefectHandleMapper;
import cn.iocoder.yudao.module.mes.dal.mysql.quality.MesQualityRecordMapper;
import cn.iocoder.yudao.module.mes.enums.DefectHandleStatusEnum;
import cn.iocoder.yudao.module.mes.enums.QualityResultEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mes.enums.ErrorCodeConstants.*;

/**
 * 不合格处理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MesDefectHandleServiceImpl implements MesDefectHandleService {

    @Resource
    private MesDefectHandleMapper defectHandleMapper;

    @Resource
    private MesQualityRecordMapper qualityRecordMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDefectHandle(MesDefectHandleCreateReqVO createReqVO) {
        // 1. 校验质量记录存在
        MesQualityRecordDO qualityRecord = qualityRecordMapper.selectById(createReqVO.getQualityRecordId());
        if (qualityRecord == null) {
            throw exception(QUALITY_RECORD_NOT_EXISTS);
        }
        // 2. 校验质量记录结果为不合格
        if (!QualityResultEnum.FAIL.getResult().equals(qualityRecord.getResult())) {
            throw exception(DEFECT_HANDLE_STATUS_INVALID);
        }
        // 3. 校验是否已登记不合格处理
        MesDefectHandleDO existHandle = defectHandleMapper.selectByQualityRecordId(createReqVO.getQualityRecordId());
        if (existHandle != null) {
            throw exception(QUALITY_RECORD_ALREADY_DEFECT);
        }
        // 4. 创建不合格处理记录
        MesDefectHandleDO defectHandle = BeanUtils.toBean(createReqVO, MesDefectHandleDO.class);
        defectHandle.setHandleStatus(DefectHandleStatusEnum.PENDING.getStatus());
        defectHandleMapper.insert(defectHandle);
        return defectHandle.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleDefect(MesDefectHandleReqVO handleReqVO) {
        // 1. 校验存在
        MesDefectHandleDO defectHandle = validateDefectHandleExists(handleReqVO.getId());
        // 2. 校验状态：只有待处理状态才能处理
        if (!DefectHandleStatusEnum.PENDING.getStatus().equals(defectHandle.getHandleStatus())) {
            throw exception(DEFECT_HANDLE_STATUS_INVALID);
        }
        // 3. 更新处理信息
        MesDefectHandleDO updateObj = new MesDefectHandleDO();
        updateObj.setId(handleReqVO.getId());
        updateObj.setHandleType(handleReqVO.getHandleType());
        updateObj.setHandleStatus(DefectHandleStatusEnum.PROCESSING.getStatus());
        updateObj.setHandlerId(handleReqVO.getHandlerId());
        updateObj.setHandlerName(handleReqVO.getHandlerName());
        updateObj.setHandleTime(LocalDateTime.now());
        updateObj.setHandleResult(handleReqVO.getHandleResult());
        defectHandleMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void verifyDefect(MesDefectHandleVerifyReqVO verifyReqVO) {
        // 1. 校验存在
        MesDefectHandleDO defectHandle = validateDefectHandleExists(verifyReqVO.getId());
        // 2. 校验状态：只有处理中状态才能验证
        if (!DefectHandleStatusEnum.PROCESSING.getStatus().equals(defectHandle.getHandleStatus())) {
            throw exception(DEFECT_HANDLE_STATUS_INVALID);
        }
        // 3. 更新验证信息，状态改为已闭环
        MesDefectHandleDO updateObj = new MesDefectHandleDO();
        updateObj.setId(verifyReqVO.getId());
        updateObj.setHandleStatus(DefectHandleStatusEnum.CLOSED.getStatus());
        updateObj.setVerifierId(verifyReqVO.getVerifierId());
        updateObj.setVerifierName(verifyReqVO.getVerifierName());
        updateObj.setVerifyTime(LocalDateTime.now());
        defectHandleMapper.updateById(updateObj);
    }

    @Override
    public MesDefectHandleDO getDefectHandle(Long id) {
        return defectHandleMapper.selectById(id);
    }

    @Override
    public PageResult<MesDefectHandleDO> getDefectHandlePage(MesDefectHandlePageReqVO pageReqVO) {
        return defectHandleMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MesDefectHandleDO> getDefectHandleListByVin(String vin) {
        return defectHandleMapper.selectListByVin(vin);
    }

    @Override
    public MesDefectHandleDO validateDefectHandleExists(Long id) {
        if (id == null) {
            return null;
        }
        MesDefectHandleDO defectHandle = defectHandleMapper.selectById(id);
        if (defectHandle == null) {
            throw exception(DEFECT_HANDLE_NOT_EXISTS);
        }
        return defectHandle;
    }

}