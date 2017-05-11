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
-- Table structure for t_sys_dict_meta
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dict_meta`;
CREATE TABLE `t_sys_dict_meta` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `code` varchar(255) NOT NULL COMMENT '编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典元数据表';
