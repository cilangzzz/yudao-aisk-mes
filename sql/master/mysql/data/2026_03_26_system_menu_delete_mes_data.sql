-- ============================================
-- 文件名：2026_03_26_system_menu_delete_mes_data.sql
-- 描述：删除 MES 模块菜单权限数据
-- 作者：admin
-- 日期：2026-03-26
-- ============================================

-- ========== 删除按钮权限（type=3）==========
-- 按照从子到父的顺序删除

-- 删除工作站管理按钮权限
DELETE FROM `system_menu` WHERE `permission` IN (
    'mes:workstation:query', 'mes:workstation:create', 'mes:workstation:update',
    'mes:workstation:delete', 'mes:workstation:export', 'mes:workstation:enable',
    'mes:workstation:disable', 'mes:workstation:bind-equipment'
);

-- 删除产品BOM按钮权限
DELETE FROM `system_menu` WHERE `permission` IN (
    'mes:product-bom:query', 'mes:product-bom:create', 'mes:product-bom:update', 'mes:product-bom:delete'
);

-- 删除产品管理按钮权限
DELETE FROM `system_menu` WHERE `permission` IN (
    'mes:product:query', 'mes:product:create', 'mes:product:update', 'mes:product:delete', 'mes:product:export'
);

-- 删除班次管理按钮权限
DELETE FROM `system_menu` WHERE `permission` IN (
    'mes:shift:query', 'mes:shift:create', 'mes:shift:update', 'mes:shift:delete', 'mes:shift:export'
);

-- 删除班组管理按钮权限
DELETE FROM `system_menu` WHERE `permission` IN (
    'mes:team:query', 'mes:team:create', 'mes:team:update', 'mes:team:delete'
);

-- 删除产线管理按钮权限
DELETE FROM `system_menu` WHERE `permission` IN (
    'mes:production-line:query', 'mes:production-line:create', 'mes:production-line:update',
    'mes:production-line:delete', 'mes:production-line:export'
);

-- 删除车间管理按钮权限
DELETE FROM `system_menu` WHERE `permission` IN (
    'mes:workshop:query', 'mes:workshop:create', 'mes:workshop:update',
    'mes:workshop:delete', 'mes:workshop:export'
);

-- ========== 删除菜单（type=1或2）==========
-- 删除二级菜单（基础数据下的子菜单）
DELETE FROM `system_menu` WHERE `name` = '工作站管理' AND `path` = 'workstation';
DELETE FROM `system_menu` WHERE `name` = '产品BOM' AND `path` = 'product-bom';
DELETE FROM `system_menu` WHERE `name` = '产品管理' AND `path` = 'product';
DELETE FROM `system_menu` WHERE `name` = '班次管理' AND `path` = 'shift';
DELETE FROM `system_menu` WHERE `name` = '班组管理' AND `path` = 'team';
DELETE FROM `system_menu` WHERE `name` = '产线管理' AND `path` = 'production-line';
DELETE FROM `system_menu` WHERE `name` = '车间管理' AND `path` = 'workshop';

-- 删除一级菜单（基础数据）- 通过path匹配
DELETE FROM `system_menu` WHERE `name` = '基础数据' AND `path` = 'base';

-- 删除MES一级菜单
DELETE FROM `system_menu` WHERE `name` = 'MES管理' AND `path` = '/mes';