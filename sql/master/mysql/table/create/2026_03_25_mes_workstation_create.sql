-- ============================================
-- 文件名：2026_03_25_mes_workstation_create.sql
-- 描述：MES工作站表建表脚本
-- 作者：admin
-- 日期：2026-03-25
-- ============================================

-- ----------------------------
-- 工作站表
-- ----------------------------
DROP TABLE IF EXISTS `mes_workstation`;
CREATE TABLE `mes_workstation` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户编号',
    `workstation_code` VARCHAR(64) NOT NULL COMMENT '工作站编码',
    `workstation_name` VARCHAR(255) NOT NULL COMMENT '工作站名称',
    `workshop_id` BIGINT DEFAULT NULL COMMENT '车间ID',
    `workshop_name` VARCHAR(100) DEFAULT NULL COMMENT '车间名称（冗余字段）',
    `line_id` BIGINT NOT NULL COMMENT '产线ID',
    `line_name` VARCHAR(100) DEFAULT NULL COMMENT '产线名称（冗余字段）',
    `workstation_type` TINYINT NOT NULL DEFAULT 0 COMMENT '类型:0-普通,1-关键,2-检验',
    `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态:0-启用,1-停用',
    `equipment_ids` VARCHAR(500) DEFAULT NULL COMMENT '关联设备ID列表（JSON格式）',
    `description` VARCHAR(500) DEFAULT NULL COMMENT '描述',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenant_code` (`tenant_id`, `workstation_code`, `deleted`),
    KEY `idx_tenant_line` (`tenant_id`, `line_id`),
    KEY `idx_tenant_workshop` (`tenant_id`, `workshop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='MES工作站表';