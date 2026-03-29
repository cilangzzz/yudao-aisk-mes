-- =============================================
-- MES 工艺路线管理模块数据表
-- =============================================

-- =============================================
-- 工艺路线表（主表）
-- =============================================
CREATE TABLE `mes_routing` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    `routing_code` VARCHAR(64) NOT NULL COMMENT '工艺路线编码',
    `routing_name` VARCHAR(255) NOT NULL COMMENT '工艺路线名称',
    `product_id` BIGINT DEFAULT NULL COMMENT '关联产品ID',
    `product_code` VARCHAR(64) DEFAULT NULL COMMENT '产品编码',
    `product_name` VARCHAR(255) DEFAULT NULL COMMENT '产品名称',
    `version` VARCHAR(32) NOT NULL DEFAULT 'V1.0' COMMENT '版本号',
    `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态:0-草稿,1-生效,2-失效',
    `description` VARCHAR(500) DEFAULT NULL COMMENT '描述',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenant_code` (`tenant_id`, `routing_code`, `deleted`),
    KEY `idx_tenant_product` (`tenant_id`, `product_id`),
    KEY `idx_tenant_status` (`tenant_id`, `status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='MES工艺路线表';

-- =============================================
-- 工序定义表（子表）
-- =============================================
CREATE TABLE `mes_operation` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    `routing_id` BIGINT NOT NULL COMMENT '工艺路线ID',
    `operation_code` VARCHAR(64) NOT NULL COMMENT '工序编码',
    `operation_name` VARCHAR(255) NOT NULL COMMENT '工序名称',
    `sequence` INT NOT NULL COMMENT '工序顺序',
    `workstation_id` BIGINT DEFAULT NULL COMMENT '默认工作站ID',
    `workstation_name` VARCHAR(255) DEFAULT NULL COMMENT '工作站名称',
    `standard_time` DECIMAL(10,2) DEFAULT NULL COMMENT '标准工时(分钟)',
    `description` VARCHAR(500) DEFAULT NULL COMMENT '工序描述',
    `key_operation` TINYINT NOT NULL DEFAULT 0 COMMENT '是否关键工序:0-否,1-是',
    `quality_check` TINYINT NOT NULL DEFAULT 0 COMMENT '是否需要质检:0-否,1-是',
    `instruction` TEXT DEFAULT NULL COMMENT '作业指导内容',
    `instruction_file` VARCHAR(255) DEFAULT NULL COMMENT '作业指导文件URL',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY `idx_tenant_routing` (`tenant_id`, `routing_id`),
    KEY `idx_routing_sequence` (`routing_id`, `sequence`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='MES工序定义表';

-- =============================================
-- 工序物料表（子表的子表）
-- =============================================
CREATE TABLE `mes_operation_material` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    `operation_id` BIGINT NOT NULL COMMENT '工序ID',
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
    KEY `idx_tenant_operation` (`tenant_id`, `operation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='MES工序物料表';