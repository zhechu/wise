/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : training

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-03-31 11:43:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_resource`;
CREATE TABLE `t_sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
  `name` varchar(255) NOT NULL COMMENT '资源名称',
  `url` varchar(255) DEFAULT '' COMMENT '链接地址',
  `icon` varchar(100) DEFAULT '' COMMENT '图标',
  `description` varchar(500) DEFAULT '' COMMENT '描述',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '显示状态（0-隐藏，1-显示）',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '资源类型（0-菜单，1-页面，2-操作）',
  `permission` varchar(255) DEFAULT '' COMMENT '权限字符串',
  `parentId` int(11) NOT NULL DEFAULT '0' COMMENT '父资源id（不同类型的资源，请勿设置上下级关系）',
  `sort` int(11) DEFAULT '0' COMMENT '资源排列顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统资源';
