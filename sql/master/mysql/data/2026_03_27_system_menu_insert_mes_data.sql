-- ============================================
-- 文件名：2026_03_27_system_menu_insert_mes_data.sql
-- 描述：MES 模块补充菜单数据（config, mobile, operation, stock, trace, workorder）
-- 作者：admin
-- 日期：2026-03-27
-- 注意：此文件可独立执行，会自动获取MES一级菜单ID
-- ============================================

-- ========== 获取MES一级菜单ID ==========
-- 如果MES管理菜单已存在，获取其ID；否则创建
SET @mesParentId = (SELECT id FROM `system_menu` WHERE `name` = 'MES管理' AND `parent_id` = 0 AND `deleted` = b'0' LIMIT 1);

-- 如果MES一级菜单不存在，先创建
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
SELECT 'MES管理', '', 1, 50, 0, '/mes', 'ep:setting', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'
FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM `system_menu` WHERE `name` = 'MES管理' AND `parent_id` = 0 AND `deleted` = b'0');

-- 重新获取MES一级菜单ID
SET @mesParentId = (SELECT id FROM `system_menu` WHERE `name` = 'MES管理' AND `parent_id` = 0 AND `deleted` = b'0' LIMIT 1);

-- ========== 配置管理子菜单 ==========
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('配置管理', '', 1, 2, @mesParentId, 'config', 'ep:setting', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

SET @configParentId = LAST_INSERT_ID();

-- ========== 工艺路线管理菜单 ==========
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('工艺路线', '', 2, 1, @configParentId, 'routing', 'ep:guide', 'mes/config/routing/index', 'MesRouting', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

SET @routingMenuId = LAST_INSERT_ID();

-- 工艺路线管理按钮权限
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('工艺路线查询', 'mes:routing:query', 3, 1, @routingMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工艺路线新增', 'mes:routing:create', 3, 2, @routingMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工艺路线修改', 'mes:routing:update', 3, 3, @routingMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工艺路线删除', 'mes:routing:delete', 3, 4, @routingMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工艺路线导出', 'mes:routing:export', 3, 5, @routingMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ========== 生产工单菜单 ==========
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('生产工单', '', 2, 3, @mesParentId, 'workorder', 'ep:document', 'mes/workorder/index', 'MesWorkOrder', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

SET @workorderMenuId = LAST_INSERT_ID();

-- 生产工单按钮权限
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('工单查询', 'mes:work-order:query', 3, 1, @workorderMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工单新增', 'mes:work-order:create', 3, 2, @workorderMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工单修改', 'mes:work-order:update', 3, 3, @workorderMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工单删除', 'mes:work-order:delete', 3, 4, @workorderMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工单导出', 'mes:work-order:export', 3, 5, @workorderMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工单下达', 'mes:work-order:release', 3, 6, @workorderMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工单开工', 'mes:work-order:start', 3, 7, @workorderMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工单完工', 'mes:work-order:complete', 3, 8, @workorderMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工单关闭', 'mes:work-order:close', 3, 9, @workorderMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ========== 生产作业菜单 ==========
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('生产作业', '', 2, 4, @mesParentId, 'operation', 'ep:cpu', 'mes/operation/index', 'MesOperation', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

SET @operationMenuId = LAST_INSERT_ID();

-- 生产作业按钮权限
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('作业查询', 'mes:operation:query', 3, 1, @operationMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('作业报工', 'mes:operation:report', 3, 2, @operationMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('关键件绑定', 'mes:operation:key-part', 3, 3, @operationMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ========== 库存管理菜单 ==========
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('库存管理', '', 2, 5, @mesParentId, 'stock', 'ep:box', 'mes/stock/index', 'MesStock', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

SET @stockMenuId = LAST_INSERT_ID();

-- 库存管理按钮权限
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('库存查询', 'mes:stock:query', 3, 1, @stockMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('库存入库', 'mes:stock:in', 3, 2, @stockMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('库存消耗', 'mes:stock:consume', 3, 3, @stockMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('库存预警', 'mes:stock:warning', 3, 4, @stockMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ========== 质量追溯菜单 ==========
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('质量追溯', '', 2, 6, @mesParentId, 'trace', 'ep:link', 'mes/trace/index', 'MesTrace', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

SET @traceMenuId = LAST_INSERT_ID();

-- 质量追溯按钮权限
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('追溯查询', 'mes:trace:query', 3, 1, @traceMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('VIN追溯', 'mes:trace:vin', 3, 2, @traceMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('关键件追溯', 'mes:trace:part', 3, 3, @traceMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('追溯导出', 'mes:trace:export', 3, 4, @traceMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ========== 移动端管理菜单 ==========
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('移动端管理', '', 2, 7, @mesParentId, 'mobile', 'ep:iphone', 'mes/mobile/index', 'MesMobile', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

SET @mobileMenuId = LAST_INSERT_ID();

-- 移动端管理按钮权限
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('移动端查询', 'mes:mobile:query', 3, 1, @mobileMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('PDA扫码', 'mes:mobile:scan', 3, 2, @mobileMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('关键件管理', 'mes:mobile:key-part', 3, 3, @mobileMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('异常处理', 'mes:mobile:exception', 3, 4, @mobileMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');