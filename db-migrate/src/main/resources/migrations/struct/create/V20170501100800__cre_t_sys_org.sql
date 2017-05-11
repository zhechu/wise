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
-- Table structure for t_sys_org
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_org`;
CREATE TABLE `t_sys_org` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
  `parentId` int(11) NOT NULL COMMENT '父级编号',
  `parentIds` varchar(1000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `areaId` int(11) NOT NULL COMMENT '归属区域',
  `code` varchar(100) DEFAULT '' COMMENT '机构编码',
  `type` tinyint(4) DEFAULT '1' COMMENT '机构类型（1-公司，2-部门，3-小组，4-其它）',
  `grade`tinyint(4) DEFAULT '1' COMMENT '机构等级（1-一级，2-二级，3-三级，4-四级）',
  `address` varchar(255) DEFAULT '' COMMENT '联系地址',
  `zipCode` varchar(100) DEFAULT '' COMMENT '邮政编码',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态（0-禁用，1-启用）',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';
