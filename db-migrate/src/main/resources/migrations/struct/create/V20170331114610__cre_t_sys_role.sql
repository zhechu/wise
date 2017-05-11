/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : training

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-03-31 11:43:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '角色名称',
  `description` varchar(500) DEFAULT '' COMMENT '描述',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态（0-禁用，1-启用）',
  `creator` int(11) NOT NULL COMMENT '创建者',
  `createAt` datetime NOT NULL COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '修改者',
  `modifiedAt` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色';
