-- ============================================
-- 文件名：2026_03_25_mes_team_create.sql
-- 描述：MES班组表建表脚本
-- 作者：admin
-- 日期：2026-03-25
-- ============================================

-- ----------------------------
-- 班组表
-- ----------------------------
DROP TABLE IF EXISTS `mes_team`;
CREATE TABLE `mes_team` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户编号',
    `team_code` VARCHAR(64) NOT NULL COMMENT '班组编码',
    `team_name` VARCHAR(255) NOT NULL COMMENT '班组名称',
    `line_id` BIGINT NOT NULL COMMENT '产线ID',
    `line_name` VARCHAR(100) DEFAULT NULL COMMENT '产线名称（冗余字段）',
    `leader_id` BIGINT DEFAULT NULL COMMENT '班组长用户ID',
    `leader_name` VARCHAR(64) DEFAULT NULL COMMENT '班组长姓名',
    `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态:0-启用,1-停用',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenant_code` (`tenant_id`, `team_code`, `deleted`),
    KEY `idx_tenant_line` (`tenant_id`, `line_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='MES班组表';