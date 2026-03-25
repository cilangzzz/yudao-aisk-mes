package cn.iocoder.yudao.module.mes.dal.mysql.material;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mes.controller.admin.material.vo.MesLineStockPageReqVO;
import cn.iocoder.yudao.module.mes.dal.dataobject.material.MesLineStockDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * MES 线边库存 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MesLineStockMapper extends BaseMapperX<MesLineStockDO> {

    default PageResult<MesLineStockDO> selectPage(MesLineStockPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MesLineStockDO>()
                .likeIfPresent(MesLineStockDO::getMaterialCode, reqVO.getMaterialCode())
                .likeIfPresent(MesLineStockDO::getMaterialName, reqVO.getMaterialName())
                .eqIfPresent(MesLineStockDO::getWorkstationId, reqVO.getWorkstationId())
                .orderByDesc(MesLineStockDO::getId));
    }

    default MesLineStockDO selectByMaterialCodeAndWorkstationId(String materialCode, Long workstationId) {
        return selectOne(new LambdaQueryWrapperX<MesLineStockDO>()
                .eq(MesLineStockDO::getMaterialCode, materialCode)
                .eq(MesLineStockDO::getWorkstationId, workstationId));
    }

    default List<MesLineStockDO> selectListByWorkstationId(Long workstationId) {
        return selectList(MesLineStockDO::getWorkstationId, workstationId);
    }

    default List<MesLineStockDO> selectWarningList() {
        return selectList(new LambdaQueryWrapperX<MesLineStockDO>()
                .apply("qty < safety_qty"));
    }

}