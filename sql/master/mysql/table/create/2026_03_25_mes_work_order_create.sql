-- ============================================
-- 文件名：2026_03_25_mes_work_order_create.sql
-- 描述：生产工单表
-- 作者：AI Assistant
-- 日期：2026-03-25
-- ============================================

-- 生产工单表
CREATE TABLE `mes_work_order` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    `order_no` VARCHAR(64) NOT NULL COMMENT '工单编号(WO+年月日+4位流水)',
    `erp_order_no` VARCHAR(64) DEFAULT NULL COMMENT 'ERP订单编号',
    `product_id` BIGINT NOT NULL COMMENT '产品ID',
    `product_code` VARCHAR(64) NOT NULL COMMENT '产品编码',
    `product_name` VARCHAR(255) NOT NULL COMMENT '产品名称',
    `plan_qty` INT NOT NULL COMMENT '计划数量',
    `actual_qty` INT NOT NULL DEFAULT 0 COMMENT '实际数量',
    `routing_id` BIGINT NOT NULL COMMENT '工艺路线ID',
    `routing_name` VARCHAR(255) DEFAULT NULL COMMENT '工艺路线名称',
    `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态:0-待下发,1-已下发,2-生产中,3-已完成,4-已关闭',
    `priority` INT NOT NULL DEFAULT 5 COMMENT '优先级(1-10)',
    `plan_start_time` DATETIME NOT NULL COMMENT '计划开始时间',
    `plan_end_time` DATETIME NOT NULL COMMENT '计划结束时间',
    `actual_start_time` DATETIME DEFAULT NULL COMMENT '实际开始时间',
    `actual_end_time` DATETIME DEFAULT NULL COMMENT '实际结束时间',
    `workshop_id` BIGINT DEFAULT NULL COMMENT '车间ID',
    `workshop_name` VARCHAR(100) DEFAULT NULL COMMENT '车间名称',
    `line_id` BIGINT NOT NULL COMMENT '产线ID',
    `line_name` VARCHAR(100) DEFAULT NULL COMMENT '产线名称',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenant_order` (`tenant_id`, `order_no`, `deleted`),
    KEY `idx_tenant_status` (`tenant_id`, `status`),
    KEY `idx_tenant_line` (`tenant_id`, `line_id`),
    KEY `idx_tenant_time` (`tenant_id`, `plan_start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='生产工单表';