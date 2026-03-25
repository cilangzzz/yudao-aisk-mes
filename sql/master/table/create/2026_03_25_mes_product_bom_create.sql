-- ============================================
-- 文件名：2026_03_25_mes_product_bom_create.sql
-- 描述：MES产品BOM表建表脚本
-- 作者：admin
-- 日期：2026-03-25
-- ============================================

-- ----------------------------
-- 产品BOM表
-- ----------------------------
DROP TABLE IF EXISTS `mes_product_bom`;
CREATE TABLE `mes_product_bom` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户编号',
    `product_id` BIGINT NOT NULL COMMENT '产品ID',
    `product_code` VARCHAR(64) NOT NULL COMMENT '产品编码',
    `material_code` VARCHAR(64) NOT NULL COMMENT '物料编码',
    `material_name` VARCHAR(255) NOT NULL COMMENT '物料名称',
    `qty` DECIMAL(10,2) NOT NULL COMMENT '用量',
    `unit` VARCHAR(32) DEFAULT NULL COMMENT '单位',
    `key_part` TINYINT NOT NULL DEFAULT 0 COMMENT '是否关键件:0-否,1-是',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY `idx_tenant_product` (`tenant_id`, `product_id`),
    KEY `idx_tenant_material` (`tenant_id`, `material_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='MES产品BOM表';