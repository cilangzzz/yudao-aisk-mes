-- ============================================
-- 文件名：2026_03_25_mes_product_create.sql
-- 描述：MES产品表建表脚本
-- 作者：admin
-- 日期：2026-03-25
-- ============================================

-- ----------------------------
-- 产品表
-- ----------------------------
DROP TABLE IF EXISTS `mes_product`;
CREATE TABLE `mes_product` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户编号',
    `product_code` VARCHAR(64) NOT NULL COMMENT '产品编码',
    `product_name` VARCHAR(255) NOT NULL COMMENT '产品名称',
    `product_type` VARCHAR(64) DEFAULT NULL COMMENT '产品类型',
    `specification` VARCHAR(255) DEFAULT NULL COMMENT '规格',
    `model` VARCHAR(128) DEFAULT NULL COMMENT '型号',
    `unit` VARCHAR(32) DEFAULT NULL COMMENT '单位',
    `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态:0-启用,1-停用',
    `description` VARCHAR(500) DEFAULT NULL COMMENT '描述',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenant_code` (`tenant_id`, `product_code`, `deleted`),
    KEY `idx_tenant_status` (`tenant_id`, `status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='MES产品表';