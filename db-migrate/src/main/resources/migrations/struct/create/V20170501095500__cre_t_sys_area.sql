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
-- Table structure for t_sys_area
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_area`;
CREATE TABLE `t_sys_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
  `parentId` int(11) NOT NULL COMMENT '父级编号',
  `parentIds` varchar(1000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `code` varchar(100) DEFAULT '' COMMENT '区域编码',
  `type` tinyint(4) DEFAULT '1' COMMENT '区域类型（1-国家，2-省份、直辖市，3-地市，4-区县）',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域表';
