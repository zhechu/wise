

-- 系统管理
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('1', '系统管理', '', 'glyphicon glyphicon-cog', '', '1', '0', '', '0', '10');
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('2', '资源管理', '/sysResource/list.do', '', '', '1', '0', '', '1', '10');
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('3', '角色管理', '/sysRole/list.do', '', '', '1', '0', '', '1', '20');
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('4', '用户管理', '/sysManager/list.do', '', '', '1', '0', '', '1', '30');
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('5', '字典管理', '/sysDict/list.do', '', '', '1', '0', '', '1', '40');

-- 资源管理操作
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('6', '添加', '', '', '', '1', '2', 'sys:resource:add', '2', '0');
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('7', '删除', '', '', '', '1', '2', 'sys:resource:delete', '2', '0');
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('8', '修改', '', '', '', '1', '2', 'sys:resource:update', '2', '0');
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('9', '查询', '', '', '', '1', '2', 'sys:resource:view', '2', '0');

-- 角色管理操作
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('10', '添加', '', '', '', '1', '2', 'sys:role:add', '3', '0');
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('11', '删除', '', '', '', '1', '2', 'sys:role:delete', '3', '10');
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('12', '修改', '', '', '', '1', '2', 'sys:role:update', '3', '20');
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('13', '查询', '', '', '', '1', '2', 'sys:role:view', '3', '30');

--  用户管理操作
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('14', '添加', '', '', '', '1', '2', 'sys:manager:add', '4', '0');
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('15', '删除', '', '', '', '1', '2', 'sys:manager:delete', '4', '10');
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('16', '修改', '', '', '', '1', '2', 'sys:manager:update', '4', '20');
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('17', '查询', '', '', '', '1', '2', 'sys:manager:view', '4', '30');

-- 字典管理操作
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('18', '添加', '', '', '', '1', '2', 'sys:dict:add', '5', '0');
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('19', '删除', '', '', '', '1', '2', 'sys:dict:delete', '5', '10');
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('20', '修改', '', '', '', '1', '2', 'sys:dict:update', '5', '20');
INSERT INTO `t_sys_resource` (`id`, `name`, `url`, `icon`, `description`, `status`, `type`, `permission`, `parentId`, `sort`) VALUES ('21', '查询', '', '', '', '1', '2', 'sys:dict:view', '5', '30');
