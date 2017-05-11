

-- 系统管理模块字典
INSERT INTO `t_sys_dict` (`type`, `value`, `label`, `status`, `description`, `sort`, `parentId`) VALUES ('status', '0', '禁用', '1', '状态', '1', '0');
INSERT INTO `t_sys_dict` (`type`, `value`, `label`, `status`, `description`, `sort`, `parentId`) VALUES ('status', '1', '启用', '1', '状态', '2', '0');
INSERT INTO `t_sys_dict` (`type`, `value`, `label`, `status`, `description`, `sort`, `parentId`) VALUES ('sys_dict_status', '0', '禁用', '1', '字典状态', '1', '0');
INSERT INTO `t_sys_dict` (`type`, `value`, `label`, `status`, `description`, `sort`, `parentId`) VALUES ('sys_dict_status', '1', '启用', '1', '字典状态', '2', '0');
INSERT INTO `t_sys_dict` (`type`, `value`, `label`, `status`, `description`, `sort`, `parentId`) VALUES ('sys_manager_status', '0', '禁用', '1', '管理员状态', '0', '0');
INSERT INTO `t_sys_dict` (`type`, `value`, `label`, `status`, `description`, `sort`, `parentId`) VALUES ('sys_manager_status', '1', '启用', '1', '管理员状态', '10', '0');
INSERT INTO `t_sys_dict` (`type`, `value`, `label`, `status`, `description`, `sort`, `parentId`) VALUES ('sex', '0', '女', '1', '性别', '0', '0');
INSERT INTO `t_sys_dict` (`type`, `value`, `label`, `status`, `description`, `sort`, `parentId`) VALUES ('sex', '1', '男', '1', '性别', '10', '0');
INSERT INTO `t_sys_dict` (`type`, `value`, `label`, `status`, `description`, `sort`, `parentId`) VALUES ('sex', '2', '其它', '1', '性别', '20', '0');
INSERT INTO `t_sys_dict` (`type`, `value`, `label`, `status`, `description`, `sort`, `parentId`) VALUES ('sys_role_status', '0', '禁用', '1', '角色状态', '0', '0');
INSERT INTO `t_sys_dict` (`type`, `value`, `label`, `status`, `description`, `sort`, `parentId`) VALUES ('sys_role_status', '1', '启用', '1', '角色状态', '10', '0');
INSERT INTO `t_sys_dict` (`type`, `value`, `label`, `status`, `description`, `sort`, `parentId`) VALUES ('sys_resource_status', '0', '禁用', '1', '资源状态', '0', '0');
INSERT INTO `t_sys_dict` (`type`, `value`, `label`, `status`, `description`, `sort`, `parentId`) VALUES ('sys_resource_status', '1', '启用', '1', '资源状态', '10', '0');
INSERT INTO `t_sys_dict` (`type`, `value`, `label`, `status`, `description`, `sort`, `parentId`) VALUES ('sys_resource_type', '0', '菜单', '1', '资源类型', '0', '0');
INSERT INTO `t_sys_dict` (`type`, `value`, `label`, `status`, `description`, `sort`, `parentId`) VALUES ('sys_resource_type', '1', '页面', '1', '资源类型', '10', '0');
INSERT INTO `t_sys_dict` (`type`, `value`, `label`, `status`, `description`, `sort`, `parentId`) VALUES ('sys_resource_type', '2', '操作', '1', '资源类型', '20', '0');
