/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : training

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-03-31 11:42:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dict`;
CREATE TABLE `t_sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
  `type` varchar(255) NOT NULL COMMENT '类型',
  `value` varchar(255) NOT NULL COMMENT '字典项值',
  `label` varchar(255) NOT NULL COMMENT '字典项名称',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态值（0-禁用，1-启用）',
  `description` varchar(500) DEFAULT '' COMMENT '字典项描述',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `parentId` int(11) NOT NULL DEFAULT '0' COMMENT '父级字典项',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';
