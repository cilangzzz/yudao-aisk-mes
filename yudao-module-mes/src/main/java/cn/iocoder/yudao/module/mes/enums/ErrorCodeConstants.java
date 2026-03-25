package cn.iocoder.yudao.module.mes.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * MES 错误码枚举类
 * <p>
 * mes 系统，使用 1-020-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 车间管理 1-020-001-xxx ==========
    ErrorCode WORKSHOP_NOT_EXISTS = new ErrorCode(1_020_001_000, "车间不存在");
    ErrorCode WORKSHOP_CODE_DUPLICATE = new ErrorCode(1_020_001_001, "已存在该编码的车间");
    ErrorCode WORKSHOP_NAME_DUPLICATE = new ErrorCode(1_020_001_002, "已存在该名称的车间");
    ErrorCode WORKSHOP_HAS_CHILDREN = new ErrorCode(1_020_001_003, "存在子车间，无法删除");
    ErrorCode WORKSHOP_HAS_PRODUCTION_LINES = new ErrorCode(1_020_001_004, "车间下存在产线，无法删除");
    ErrorCode WORKSHOP_DISABLED = new ErrorCode(1_020_001_005, "车间已停用");
    ErrorCode WORKSHOP_PARENT_NOT_EXISTS = new ErrorCode(1_020_001_006, "父车间不存在");
    ErrorCode WORKSHOP_PARENT_ERROR = new ErrorCode(1_020_001_007, "不能设置自己为父车间");
    ErrorCode WORKSHOP_PARENT_IS_CHILD = new ErrorCode(1_020_001_008, "不能设置子车间为父车间");

    // ========== 产线管理 1-020-002-xxx ==========
    ErrorCode PRODUCTION_LINE_NOT_EXISTS = new ErrorCode(1_020_002_000, "产线不存在");
    ErrorCode PRODUCTION_LINE_CODE_DUPLICATE = new ErrorCode(1_020_002_001, "已存在该编码的产线");
    ErrorCode PRODUCTION_LINE_NAME_DUPLICATE = new ErrorCode(1_020_002_002, "已存在该名称的产线");
    ErrorCode PRODUCTION_LINE_HAS_WORKSTATIONS = new ErrorCode(1_020_002_003, "产线下存在工作站，无法删除");
    ErrorCode PRODUCTION_LINE_HAS_WORK_ORDERS = new ErrorCode(1_020_002_004, "产线下存在进行中的工单，无法停用");
    ErrorCode PRODUCTION_LINE_DISABLED = new ErrorCode(1_020_002_005, "产线已停用");
    ErrorCode PRODUCTION_LINE_WORKSHOP_DISABLED = new ErrorCode(1_020_002_006, "所属车间已停用");

    // ========== 班组管理 1-020-003-xxx ==========
    ErrorCode TEAM_NOT_EXISTS = new ErrorCode(1_020_003_000, "班组不存在");
    ErrorCode TEAM_CODE_DUPLICATE = new ErrorCode(1_020_003_001, "已存在该编码的班组");
    ErrorCode TEAM_NAME_DUPLICATE = new ErrorCode(1_020_003_002, "已存在该名称的班组");
    ErrorCode TEAM_HAS_MEMBERS = new ErrorCode(1_020_003_003, "班组下存在成员，无法删除");
    ErrorCode TEAM_LINE_DISABLED = new ErrorCode(1_020_003_004, "所属产线已停用");

    // ========== 班次管理 1-020-004-xxx ==========
    ErrorCode SHIFT_NOT_EXISTS = new ErrorCode(1_020_004_000, "班次不存在");
    ErrorCode SHIFT_CODE_DUPLICATE = new ErrorCode(1_020_004_001, "已存在该编码的班次");
    ErrorCode SHIFT_NAME_DUPLICATE = new ErrorCode(1_020_004_002, "已存在该名称的班次");
    ErrorCode SHIFT_TIME_CONFLICT = new ErrorCode(1_020_004_003, "班次时间存在冲突");
    ErrorCode SHIFT_TIME_ERROR = new ErrorCode(1_020_004_004, "开始时间不能大于结束时间");

    // ========== 产品管理 1-020-005-xxx ==========
    ErrorCode PRODUCT_NOT_EXISTS = new ErrorCode(1_020_005_000, "产品不存在");
    ErrorCode PRODUCT_CODE_DUPLICATE = new ErrorCode(1_020_005_001, "已存在该编码的产品");
    ErrorCode PRODUCT_NAME_DUPLICATE = new ErrorCode(1_020_005_002, "已存在该名称的产品");
    ErrorCode PRODUCT_HAS_BOM = new ErrorCode(1_020_005_003, "产品下存在BOM，无法删除");
    ErrorCode PRODUCT_HAS_WORK_ORDERS = new ErrorCode(1_020_005_004, "产品下存在工单，无法删除");
    ErrorCode PRODUCT_DISABLED = new ErrorCode(1_020_005_005, "产品已停用");

    // ========== 产品BOM 1-020-006-xxx ==========
    ErrorCode PRODUCT_BOM_NOT_EXISTS = new ErrorCode(1_020_006_000, "产品BOM不存在");
    ErrorCode PRODUCT_BOM_MATERIAL_DUPLICATE = new ErrorCode(1_020_006_001, "该产品已存在此物料的BOM记录");
    ErrorCode PRODUCT_BOM_PRODUCT_NOT_EXISTS = new ErrorCode(1_020_006_002, "关联产品不存在");
    ErrorCode PRODUCT_BOM_QTY_ERROR = new ErrorCode(1_020_006_003, "用量必须大于0");

    // ========== 工作站管理 1-020-007-xxx ==========
    ErrorCode WORKSTATION_NOT_EXISTS = new ErrorCode(1_020_007_000, "工作站不存在");
    ErrorCode WORKSTATION_CODE_DUPLICATE = new ErrorCode(1_020_007_001, "已存在该编码的工作站");
    ErrorCode WORKSTATION_NAME_DUPLICATE = new ErrorCode(1_020_007_002, "已存在该名称的工作站");
    ErrorCode WORKSTATION_HAS_OPERATIONS = new ErrorCode(1_020_007_003, "工作站存在进行中的作业，无法停用");
    ErrorCode WORKSTATION_DISABLED = new ErrorCode(1_020_007_004, "工作站已停用");
    ErrorCode WORKSTATION_LINE_DISABLED = new ErrorCode(1_020_007_005, "所属产线已停用");
    ErrorCode WORKSTATION_WORKSHOP_DISABLED = new ErrorCode(1_020_007_006, "所属车间已停用");
    ErrorCode WORKSTATION_EQUIPMENT_BIND_FAILED = new ErrorCode(1_020_007_007, "设备绑定失败");
    ErrorCode WORKSTATION_HAS_ROUTING = new ErrorCode(1_020_007_008, "工作站被工艺路线引用，无法删除");

    // ========== 工单管理 1-020-008-xxx ==========
    ErrorCode WORK_ORDER_NOT_EXISTS = new ErrorCode(1_020_008_000, "生产工单不存在");
    ErrorCode WORK_ORDER_NO_DUPLICATE = new ErrorCode(1_020_008_001, "工单编号已存在");
    ErrorCode WORK_ORDER_STATUS_ERROR = new ErrorCode(1_020_008_002, "工单状态不正确");
    ErrorCode WORK_ORDER_ROUTING_NOT_ACTIVE = new ErrorCode(1_020_008_003, "工艺路线未生效");
    ErrorCode WORK_ORDER_CANNOT_RELEASE = new ErrorCode(1_020_008_004, "工单无法下发，当前状态不允许");
    ErrorCode WORK_ORDER_CANNOT_START = new ErrorCode(1_020_008_005, "工单无法开始，当前状态不允许");
    ErrorCode WORK_ORDER_CANNOT_COMPLETE = new ErrorCode(1_020_008_006, "工单无法完成，当前状态不允许");
    ErrorCode WORK_ORDER_CANNOT_CLOSE = new ErrorCode(1_020_008_007, "工单无法关闭，当前状态不允许");

    // ========== 工艺路线 1-020-009-xxx ==========
    ErrorCode ROUTING_NOT_EXISTS = new ErrorCode(1_020_009_000, "工艺路线不存在");
    ErrorCode ROUTING_CODE_DUPLICATE = new ErrorCode(1_020_009_001, "已存在该编码的工艺路线");
    ErrorCode ROUTING_HAS_WORK_ORDERS = new ErrorCode(1_020_009_002, "工艺路线被工单引用，无法删除");
    ErrorCode ROUTING_STATUS_ERROR = new ErrorCode(1_020_009_003, "工艺路线状态不正确");

    // ========== 工序管理 1-020-010-xxx ==========
    ErrorCode OPERATION_NOT_EXISTS = new ErrorCode(1_020_010_000, "工序不存在");
    ErrorCode OPERATION_CODE_DUPLICATE = new ErrorCode(1_020_010_001, "已存在该编码的工序");
    ErrorCode OPERATION_SEQUENCE_ERROR = new ErrorCode(1_020_010_002, "工序顺序号重复");

}