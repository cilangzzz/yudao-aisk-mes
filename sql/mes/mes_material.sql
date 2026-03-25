-- =============================================
-- MES 物料管理模块数据表
-- =============================================

-- =============================================
-- 线边库存表
-- =============================================
CREATE TABLE `mes_line_stock` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    `material_code` VARCHAR(64) NOT NULL COMMENT '物料编码',
    `material_name` VARCHAR(255) NOT NULL COMMENT '物料名称',
    `workstation_id` BIGINT NOT NULL COMMENT '工位ID',
    `workstation_name` VARCHAR(255) DEFAULT NULL COMMENT '工位名称',
    `qty` DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '库存数量',
    `safety_qty` DECIMAL(10,2) NOT NULL COMMENT '安全库存',
    `unit` VARCHAR(32) DEFAULT NULL COMMENT '单位',
    `last_update_time` DATETIME DEFAULT NULL COMMENT '最后更新时间',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenant_material_workstation` (`tenant_id`, `material_code`, `workstation_id`, `deleted`),
    KEY `idx_tenant_workstation` (`tenant_id`, `workstation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='MES线边库存表';

-- =============================================
-- 物料消耗记录表
-- =============================================
CREATE TABLE `mes_material_consume` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    `vin` VARCHAR(64) DEFAULT NULL COMMENT '车辆VIN',
    `work_order_id` BIGINT DEFAULT NULL COMMENT '工单ID',
    `material_code` VARCHAR(64) NOT NULL COMMENT '物料编码',
    `material_name` VARCHAR(255) NOT NULL COMMENT '物料名称',
    `qty` DECIMAL(10,2) NOT NULL COMMENT '消耗数量',
    `unit` VARCHAR(32) DEFAULT NULL COMMENT '单位',
    `workstation_id` BIGINT NOT NULL COMMENT '消耗工位ID',
    `operator_id` BIGINT DEFAULT NULL COMMENT '操作员ID',
    `operator_name` VARCHAR(64) DEFAULT NULL COMMENT '操作员姓名',
    `consume_time` DATETIME NOT NULL COMMENT '消耗时间',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY `idx_tenant_vin` (`tenant_id`, `vin`),
    KEY `idx_tenant_work_order` (`tenant_id`, `work_order_id`),
    KEY `idx_tenant_workstation` (`tenant_id`, `workstation_id`),
    KEY `idx_consume_time` (`consume_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='MES物料消耗记录表';