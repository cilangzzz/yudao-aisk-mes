-- ============================================
-- 文件名：2026_03_25_mes_device_data_create.sql
-- 描述：MES 设备采集数据表，用于追溯设备采集的扭矩等数据
-- 作者：AI Assistant
-- 日期：2026-03-25
-- ============================================

CREATE TABLE `mes_device_data` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户编号',
    `device_code` VARCHAR(64) NOT NULL COMMENT '设备编码',
    `device_name` VARCHAR(255) DEFAULT NULL COMMENT '设备名称',
    `device_type` VARCHAR(64) DEFAULT NULL COMMENT '设备类型',
    `vin` VARCHAR(64) DEFAULT NULL COMMENT '关联VIN',
    `operation_record_id` BIGINT DEFAULT NULL COMMENT '作业记录ID',
    `workstation_id` BIGINT DEFAULT NULL COMMENT '工位ID',
    `data_type` VARCHAR(64) NOT NULL COMMENT '数据类型:TORQUE/ANGLE/TEMPERATURE等',
    `data_value` DECIMAL(15,4) NOT NULL COMMENT '数据值',
    `data_unit` VARCHAR(32) DEFAULT NULL COMMENT '数据单位',
    `result` TINYINT DEFAULT NULL COMMENT '判定结果:0-合格,1-不合格',
    `collect_time` DATETIME NOT NULL COMMENT '采集时间',
    `raw_data` TEXT DEFAULT NULL COMMENT '原始数据',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY `idx_tenant_device` (`tenant_id`, `device_code`),
    KEY `idx_tenant_vin` (`tenant_id`, `vin`),
    KEY `idx_tenant_collect_time` (`tenant_id`, `collect_time`),
    KEY `idx_tenant_operation` (`tenant_id`, `operation_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='MES 设备采集数据表';