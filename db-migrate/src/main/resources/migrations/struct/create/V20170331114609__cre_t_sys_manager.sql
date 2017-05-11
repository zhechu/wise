/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : training

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-03-31 11:42:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_manager`;
CREATE TABLE `t_sys_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
  `userName` varchar(255) NOT NULL COMMENT '用户名',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `sex` tinyint(4) NOT NULL DEFAULT '0' COMMENT '性别（0-女，1-男）',
  `phone` varchar(255) NOT NULL COMMENT '电话',
  `salt` varchar(255) NOT NULL COMMENT '加密盐',
  `pwd` varchar(255) NOT NULL COMMENT '用户密码',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '账号状态（0-禁用，1-启用）',
  `email` varchar(255) NOT NULL DEFAULT '' COMMENT '账号邮箱',
  `registIp` varchar(100) NOT NULL COMMENT '注册ip',
  `portraitPic` varchar(255) DEFAULT '' COMMENT '头像',
  `creator` int(11) NOT NULL COMMENT '创建者',
  `createdAt` datetime NOT NULL COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '修改者',
  `modifiedAt` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理员表';
