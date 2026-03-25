-- ============================================
-- 文件名：2026_03_25_mes_offline_cache_create.sql
-- 描述：MES 离线作业缓存表，用于移动端断网时的数据缓存
-- 作者：AI Assistant
-- 日期：2026-03-25
-- ============================================

CREATE TABLE `mes_offline_cache` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户编号',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `cache_type` TINYINT NOT NULL COMMENT '缓存类型:1-作业记录,2-关键件绑定,3-异常上报',
    `cache_data` JSON NOT NULL COMMENT '缓存数据',
    `sync_status` TINYINT NOT NULL DEFAULT 0 COMMENT '同步状态:0-待同步,1-已同步,2-同步失败',
    `sync_time` DATETIME DEFAULT NULL COMMENT '同步时间',
    `fail_reason` VARCHAR(255) DEFAULT NULL COMMENT '同步失败原因',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY `idx_tenant_user` (`tenant_id`, `user_id`),
    KEY `idx_tenant_sync_status` (`tenant_id`, `sync_status`),
    KEY `idx_tenant_create_time` (`tenant_id`, `create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='MES 离线作业缓存表';