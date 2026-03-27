-- ============================================
-- 文件名：2026_03_27_system_menu_fix_insert_data.sql
-- 描述：系统菜单补充数据
-- 作者：admin
-- 日期：2026-03-27
-- 注意：
-- ============================================

INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (2526, '产品管理', '', 2, 80, 2397, 'product', 'fa:product-hunt', 'crm/product/index', 'CrmProduct', 0, b'1', b'1', b'1', '1', '2023-12-05 22:45:26', '1', '2024-02-20 20:36:20', b'0');

INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (4002, '产品管理', '', 2, 1, 4001, 'product', 'fa-solid:tools', 'iot/product/product/index', 'IoTProduct', 0, b'1', b'1', b'1', '', '2024-08-10 02:38:02', '1', '2025-06-15 20:56:06', b'0');
