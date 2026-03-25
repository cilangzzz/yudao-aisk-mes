-- ============================================
-- 文件名：2026_03_25_mes_operation_record_create.sql
-- 描述：MES 作业记录表，用于生产追溯
-- 作者：AI Assistant
-- 日期：2026-03-25
-- ============================================

CREATE TABLE `mes_operation_record` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户编号',
    `work_order_id` BIGINT NOT NULL COMMENT '工单ID',
    `work_order_no` VARCHAR(64) NOT NULL COMMENT '工单编号',
    `vin` VARCHAR(64) NOT NULL COMMENT 'VIN码',
    `operation_id` BIGINT NOT NULL COMMENT '工序ID',
    `operation_code` VARCHAR(64) NOT NULL COMMENT '工序编码',
    `operation_name` VARCHAR(255) NOT NULL COMMENT '工序名称',
    `operation_seq` INT DEFAULT NULL COMMENT '工序顺序',
    `workstation_id` BIGINT NOT NULL COMMENT '工作站ID',
    `workstation_code` VARCHAR(64) DEFAULT NULL COMMENT '工作站编码',
    `workstation_name` VARCHAR(255) DEFAULT NULL COMMENT '工作站名称',
    `operator_id` BIGINT DEFAULT NULL COMMENT '操作员ID',
    `operator_name` VARCHAR(64) DEFAULT NULL COMMENT '操作员姓名',
    `start_time` DATETIME DEFAULT NULL COMMENT '开始时间',
    `end_time` DATETIME DEFAULT NULL COMMENT '结束时间',
    `duration` INT DEFAULT NULL COMMENT '作业时长(秒)',
    `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态:0-进行中,1-已完成,2-异常',
    `result` TINYINT DEFAULT NULL COMMENT '结果:0-合格,1-不合格',
    `torque_value` DECIMAL(10,2) DEFAULT NULL COMMENT '扭矩值',
    `torque_result` TINYINT DEFAULT NULL COMMENT '扭矩判定:0-合格,1-不合格',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY `idx_tenant_vin` (`tenant_id`, `vin`),
    KEY `idx_tenant_order` (`tenant_id`, `work_order_id`),
    KEY `idx_tenant_operator` (`tenant_id`, `operator_id`),
    KEY `idx_tenant_time` (`tenant_id`, `start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='MES 作业记录表';