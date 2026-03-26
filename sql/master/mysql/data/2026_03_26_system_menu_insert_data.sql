-- ============================================
-- 文件名：2026_03_26_system_menu_insert_data.sql
-- 描述：MES 模块初始数据（菜单权限）
-- 作者：admin
-- 日期：2026-03-25
-- ============================================

-- ========== MES 一级菜单 ==========
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('MES管理', '', 1, 50, 0, '/mes', 'ep:setting', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

SET @mesParentId = LAST_INSERT_ID();

-- ========== 基础数据子菜单 ==========
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('基础数据', '', 1, 1, @mesParentId, 'base', 'ep:document', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

SET @baseParentId = LAST_INSERT_ID();

-- ========== 车间管理菜单 ==========
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('车间管理', '', 2, 1, @baseParentId, 'workshop', 'ep:office-building', 'mes/basic/workshop/index', 'MesWorkshop', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

SET @workshopMenuId = LAST_INSERT_ID();

-- 车间管理按钮权限
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('车间查询', 'mes:workshop:query', 3, 1, @workshopMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('车间新增', 'mes:workshop:create', 3, 2, @workshopMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('车间修改', 'mes:workshop:update', 3, 3, @workshopMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('车间删除', 'mes:workshop:delete', 3, 4, @workshopMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('车间导出', 'mes:workshop:export', 3, 5, @workshopMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ========== 产线管理菜单 ==========
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('产线管理', '', 2, 2, @baseParentId, 'production-line', 'ep:data-line', 'mes/basic/prdline/index', 'MesProductionLine', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

SET @lineMenuId = LAST_INSERT_ID();

-- 产线管理按钮权限
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('产线查询', 'mes:production-line:query', 3, 1, @lineMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('产线新增', 'mes:production-line:create', 3, 2, @lineMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('产线修改', 'mes:production-line:update', 3, 3, @lineMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('产线删除', 'mes:production-line:delete', 3, 4, @lineMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('产线导出', 'mes:production-line:export', 3, 5, @lineMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ========== 班组管理菜单 ==========
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('班组管理', '', 2, 3, @baseParentId, 'team', 'ep:avatar', 'mes/basic/team/index', 'MesTeam', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

SET @teamMenuId = LAST_INSERT_ID();

-- 班组管理按钮权限
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('班组查询', 'mes:team:query', 3, 1, @teamMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('班组新增', 'mes:team:create', 3, 2, @teamMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('班组修改', 'mes:team:update', 3, 3, @teamMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('班组删除', 'mes:team:delete', 3, 4, @teamMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ========== 班次管理菜单 ==========
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('班次管理', '', 2, 4, @baseParentId, 'shift', 'ep:timer', 'mes/basic/shift/index', 'MesShift', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

SET @shiftMenuId = LAST_INSERT_ID();

-- 班次管理按钮权限
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('班次查询', 'mes:shift:query', 3, 1, @shiftMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('班次新增', 'mes:shift:create', 3, 2, @shiftMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('班次修改', 'mes:shift:update', 3, 3, @shiftMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('班次删除', 'mes:shift:delete', 3, 4, @shiftMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('班次导出', 'mes:shift:export', 3, 5, @shiftMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ========== 产品管理菜单 ==========
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('产品管理', '', 2, 5, @baseParentId, 'product', 'ep:goods', 'mes/basic/product/index', 'MesProduct', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

SET @productMenuId = LAST_INSERT_ID();

-- 产品管理按钮权限
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('产品查询', 'mes:product:query', 3, 1, @productMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('产品新增', 'mes:product:create', 3, 2, @productMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('产品修改', 'mes:product:update', 3, 3, @productMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('产品删除', 'mes:product:delete', 3, 4, @productMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('产品导出', 'mes:product:export', 3, 5, @productMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ========== 产品BOM管理菜单 ==========
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('产品BOM', '', 2, 6, @baseParentId, 'product-bom', 'ep:list', 'mes/basic/bom/index', 'MesProductBom', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

SET @productBomMenuId = LAST_INSERT_ID();

-- 产品BOM管理按钮权限
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('产品BOM查询', 'mes:product-bom:query', 3, 1, @productBomMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('产品BOM新增', 'mes:product-bom:create', 3, 2, @productBomMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('产品BOM修改', 'mes:product-bom:update', 3, 3, @productBomMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('产品BOM删除', 'mes:product-bom:delete', 3, 4, @productBomMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ========== 工作站管理菜单 ==========
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('工作站管理', '', 2, 7, @baseParentId, 'workstation', 'ep:monitor', 'mes/config/workstation/index', 'MesWorkstation', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

SET @workstationMenuId = LAST_INSERT_ID();

-- 工作站管理按钮权限
INSERT INTO `system_menu` (`name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('工作站查询', 'mes:workstation:query', 3, 1, @workstationMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工作站新增', 'mes:workstation:create', 3, 2, @workstationMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工作站修改', 'mes:workstation:update', 3, 3, @workstationMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工作站删除', 'mes:workstation:delete', 3, 4, @workstationMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工作站导出', 'mes:workstation:export', 3, 5, @workstationMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工作站启用', 'mes:workstation:enable', 3, 6, @workstationMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('工作站停用', 'mes:workstation:disable', 3, 7, @workstationMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
('设备绑定', 'mes:workstation:bind-equipment', 3, 8, @workstationMenuId, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');