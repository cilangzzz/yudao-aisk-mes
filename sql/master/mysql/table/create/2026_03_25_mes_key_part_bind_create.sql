-- ============================================
-- 文件名：2026_03_25_mes_key_part_bind_create.sql
-- 描述：MES 关键件绑定表，用于关键件反向追溯
-- 作者：AI Assistant
-- 日期：2026-03-25
-- ============================================

CREATE TABLE `mes_key_part_bind` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户编号',
    `work_order_id` BIGINT NOT NULL COMMENT '工单ID',
    `operation_record_id` BIGINT DEFAULT NULL COMMENT '作业记录ID',
    `vin` VARCHAR(64) NOT NULL COMMENT '车辆VIN码',
    `part_code` VARCHAR(64) NOT NULL COMMENT '零部件编码',
    `part_name` VARCHAR(255) NOT NULL COMMENT '零部件名称',
    `part_sn` VARCHAR(128) NOT NULL COMMENT '零部件序列号',
    `supplier_code` VARCHAR(64) DEFAULT NULL COMMENT '供应商编码',
    `supplier_name` VARCHAR(255) DEFAULT NULL COMMENT '供应商名称',
    `bind_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '绑定时间',
    `workstation_id` BIGINT DEFAULT NULL COMMENT '绑定工位ID',
    `workstation_name` VARCHAR(255) DEFAULT NULL COMMENT '绑定工位名称',
    `operator_id` BIGINT DEFAULT NULL COMMENT '操作员ID',
    `operator_name` VARCHAR(64) DEFAULT NULL COMMENT '操作员姓名',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenant_part_sn` (`tenant_id`, `part_sn`, `deleted`),
    KEY `idx_tenant_vin` (`tenant_id`, `vin`),
    KEY `idx_tenant_part_code` (`tenant_id`, `part_code`),
    KEY `idx_tenant_order` (`tenant_id`, `work_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='MES 关键件绑定表';