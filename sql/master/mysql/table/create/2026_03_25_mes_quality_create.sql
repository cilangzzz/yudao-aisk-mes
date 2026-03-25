-- =============================================
-- MES 质量管理模块建表脚本
-- 创建时间：2026-03-25
-- =============================================

-- 1. 质量检验记录表
CREATE TABLE IF NOT EXISTS `mes_quality_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户ID',
  `vin` varchar(64) NOT NULL COMMENT '车辆VIN',
  `work_order_id` bigint DEFAULT NULL COMMENT '工单ID',
  `check_type` tinyint NOT NULL COMMENT '检验类型:0-过程检,1-终检',
  `check_item_code` varchar(64) NOT NULL COMMENT '检验项目编码',
  `check_item_name` varchar(255) NOT NULL COMMENT '检验项目名称',
  `operation_id` bigint DEFAULT NULL COMMENT '关联工序ID',
  `operation_name` varchar(255) DEFAULT NULL COMMENT '工序名称',
  `workstation_id` bigint DEFAULT NULL COMMENT '检验工位ID',
  `result` tinyint NOT NULL COMMENT '结果:0-合格,1-不合格',
  `check_value` varchar(255) DEFAULT NULL COMMENT '实际检验值',
  `standard_value` varchar(255) DEFAULT NULL COMMENT '标准值',
  `inspector_id` bigint NOT NULL COMMENT '检验员ID',
  `inspector_name` varchar(64) NOT NULL COMMENT '检验员姓名',
  `check_time` datetime NOT NULL COMMENT '检验时间',
  `defect_reason` varchar(500) DEFAULT NULL COMMENT '不合格原因',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_tenant_vin` (`tenant_id`, `vin`),
  KEY `idx_tenant_work_order` (`tenant_id`, `work_order_id`),
  KEY `idx_tenant_check_time` (`tenant_id`, `check_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='质量检验记录表';

-- 2. 不合格处理表
CREATE TABLE IF NOT EXISTS `mes_defect_handle` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户ID',
  `quality_record_id` bigint NOT NULL COMMENT '质量记录ID',
  `vin` varchar(64) NOT NULL COMMENT '车辆VIN',
  `defect_type` varchar(64) DEFAULT NULL COMMENT '缺陷类型',
  `defect_desc` varchar(500) NOT NULL COMMENT '缺陷描述',
  `handle_type` tinyint NOT NULL COMMENT '处理方式:0-返修,1-报废,2-让步接收',
  `handle_status` tinyint NOT NULL DEFAULT 0 COMMENT '处理状态:0-待处理,1-处理中,2-已闭环',
  `handler_id` bigint DEFAULT NULL COMMENT '处理人ID',
  `handler_name` varchar(64) DEFAULT NULL COMMENT '处理人姓名',
  `handle_time` datetime DEFAULT NULL COMMENT '处理时间',
  `handle_result` varchar(500) DEFAULT NULL COMMENT '处理结果',
  `verifier_id` bigint DEFAULT NULL COMMENT '验证人ID',
  `verifier_name` varchar(64) DEFAULT NULL COMMENT '验证人姓名',
  `verify_time` datetime DEFAULT NULL COMMENT '验证时间',
  `creator` varchar(64) DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_tenant_vin` (`tenant_id`, `vin`),
  KEY `idx_tenant_quality_record` (`tenant_id`, `quality_record_id`),
  KEY `idx_tenant_status` (`tenant_id`, `handle_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='不合格处理表';