/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : training

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-03-31 11:44:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_role_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_manager`;
CREATE TABLE `t_sys_role_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `managerId` int(11) NOT NULL COMMENT '管理员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员角色关联';
