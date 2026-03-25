-- ============================================
-- 文件名：2026_03_25_mes_production_line_create.sql
-- 描述：MES产线表建表脚本
-- 作者：admin
-- 日期：2026-03-25
-- ============================================

-- ----------------------------
-- 产线表
-- ----------------------------
DROP TABLE IF EXISTS `mes_production_line`;
CREATE TABLE `mes_production_line` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户编号',
    `line_code` VARCHAR(64) NOT NULL COMMENT '产线编码',
    `line_name` VARCHAR(255) NOT NULL COMMENT '产线名称',
    `workshop_id` BIGINT DEFAULT NULL COMMENT '车间ID',
    `workshop_name` VARCHAR(100) DEFAULT NULL COMMENT '车间名称（冗余字段）',
    `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态:0-启用,1-停用',
    `description` VARCHAR(500) DEFAULT NULL COMMENT '描述',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenant_code` (`tenant_id`, `line_code`, `deleted`),
    KEY `idx_tenant_workshop` (`tenant_id`, `workshop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='MES产线表';