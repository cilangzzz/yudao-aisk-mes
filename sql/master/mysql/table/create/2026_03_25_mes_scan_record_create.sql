-- ============================================
-- 文件名：2026_03_25_mes_scan_record_create.sql
-- 描述：MES 扫码记录表，记录移动端扫码操作
-- 作者：AI Assistant
-- 日期：2026-03-25
-- ============================================

CREATE TABLE `mes_scan_record` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户编号',
    `scan_code` VARCHAR(128) NOT NULL COMMENT '扫码内容',
    `scan_type` TINYINT NOT NULL COMMENT '扫码类型:1-VIN,2-物料码,3-工单码,4-关键件SN',
    `workstation_id` BIGINT DEFAULT NULL COMMENT '工位ID',
    `operator_id` BIGINT DEFAULT NULL COMMENT '操作员ID',
    `operation_id` BIGINT DEFAULT NULL COMMENT '关联工序ID',
    `vin` VARCHAR(64) DEFAULT NULL COMMENT '关联VIN',
    `work_order_id` BIGINT DEFAULT NULL COMMENT '关联工单ID',
    `result` TINYINT NOT NULL DEFAULT 0 COMMENT '扫码结果:0-成功,1-失败',
    `fail_reason` VARCHAR(255) DEFAULT NULL COMMENT '失败原因',
    `scan_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '扫码时间',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY `idx_tenant_scan_code` (`tenant_id`, `scan_code`),
    KEY `idx_tenant_vin` (`tenant_id`, `vin`),
    KEY `idx_tenant_scan_time` (`tenant_id`, `scan_time`),
    KEY `idx_tenant_operator` (`tenant_id`, `operator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='MES 扫码记录表';