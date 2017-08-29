/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : yongle

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-29 08:47:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_button
-- ----------------------------
DROP TABLE IF EXISTS `t_button`;
CREATE TABLE `t_button` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) NOT NULL COMMENT '菜单 id',
  `button_id` int(11) NOT NULL COMMENT '按钮 id',
  `button_name` varchar(50) NOT NULL COMMENT '按钮名称',
  `permision` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_button
-- ----------------------------

-- ----------------------------
-- Table structure for t_contract
-- ----------------------------
DROP TABLE IF EXISTS `t_contract`;
CREATE TABLE `t_contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(20) NOT NULL,
  `goods_name` varchar(20) DEFAULT NULL,
  `delivery_dock` varchar(20) DEFAULT NULL,
  `recipient` varchar(255) DEFAULT NULL,
  `freight_price` decimal(15,4) DEFAULT NULL,
  `delivery_quantity` decimal(15,4) DEFAULT NULL,
  `attachment` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_contract
-- ----------------------------

-- ----------------------------
-- Table structure for t_dispatch
-- ----------------------------
DROP TABLE IF EXISTS `t_dispatch`;
CREATE TABLE `t_dispatch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_no` varchar(20) DEFAULT NULL COMMENT '计划号',
  `examine_state` tinyint(1) DEFAULT '0' COMMENT '审核状态',
  `goods_name` varchar(20) DEFAULT NULL COMMENT '货物名称',
  `consignor` varchar(20) DEFAULT NULL COMMENT '托运人',
  `delivery_dock` varchar(20) DEFAULT NULL COMMENT '发货港',
  `destination_port` varchar(20) DEFAULT NULL COMMENT '目的港',
  `seagoing_vessel_name` varchar(20) DEFAULT NULL COMMENT '海轮名称',
  `settle_price` varchar(20) DEFAULT NULL COMMENT '结算价',
  `estimated_arrvial_date` date DEFAULT NULL COMMENT '预抵日期',
  `transport_consumption` varchar(20) DEFAULT NULL COMMENT '运输定耗',
  `discharge_period` date DEFAULT NULL COMMENT '卸货期限',
  `Excess_deduction_price` varchar(16) DEFAULT NULL COMMENT '超耗扣价',
  `berthing_tonnage` decimal(16,4) DEFAULT NULL COMMENT '靠泊吨位',
  `total_quantity` decimal(16,4) DEFAULT NULL COMMENT '合计数量',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `entry_time` date DEFAULT NULL COMMENT '录入时间',
  `entry_man` varchar(255) DEFAULT NULL COMMENT '录入人',
  `billing` bit(1) DEFAULT b'0' COMMENT '开票',
  `port_fee` bit(1) DEFAULT b'0' COMMENT '港建费',
  `insurance` bit(1) DEFAULT b'0' COMMENT '保险',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dispatch
-- ----------------------------
INSERT INTO `t_dispatch` VALUES ('1', 'P20170822001', '0', '玉米', '海轮1', '港口A', '港口B', '测试号海轮', '52.33', '2017-08-25', '0.002', '2017-08-25', '2.5元/斤', '25.0000', '2500.0000', '情况良好', '2017-08-25', '仓库1', '', '\0', '');
INSERT INTO `t_dispatch` VALUES ('2', 'P20170822001', '0', '玉米', '海轮1', '港口A', '港口B', '测试号海轮', '52.33', '2017-08-25', '0.002', '2017-08-25', '2.5元/斤', '25.0000', '2500.0000', '情况良好', '2017-08-25', '仓库1', '', '', '\0');
INSERT INTO `t_dispatch` VALUES ('3', 'P20170822001', '1', '玉米', '海轮1', '港口A', '港口B', '测试号海轮', '52.33', '2017-08-25', '0.002', '2017-08-25', '2.5元/斤', '25.0000', '2500.0000', '情况良好', '2017-08-25', '仓库1', '\0', '', '');
INSERT INTO `t_dispatch` VALUES ('4', 'P20170822001', '1', '玉米', '海轮1', '港口A', '港口B', '测试号海轮', '52.33', '2017-08-25', '0.002', '2017-08-25', '2.5元/斤', '25.0000', '2500.0000', '情况良好', '2017-08-25', '仓库1', '', '', '');
INSERT INTO `t_dispatch` VALUES ('5', 'Plan123456', '1', '1', '1', '1', '盐城', '1', '1', '1899-12-19', '1', '1899-12-12', '1', '1.0000', '1.0000', '1', null, null, '\0', '\0', '\0');
INSERT INTO `t_dispatch` VALUES ('6', 'Plan123456', '1', '1', '1', '1', '盐城', '1', '1', '1899-12-31', '1', '1899-12-06', '1', '1.0000', '1.0000', '1', null, null, '', '', '');
INSERT INTO `t_dispatch` VALUES ('7', 'Plan123456', '0', 'q', 'w', 'q', '淮安', '1', '1', '2017-08-08', '1', '2017-08-16', '1', '1.0000', '1.0000', '1', null, null, '\0', '', '');
INSERT INTO `t_dispatch` VALUES ('8', 'Plan123456', '0', 'q', 'w', 'q', '淮安', '1', '1', '2017-08-08', '1', '2017-08-16', '1', '1.0000', '1.0000', '1', null, null, '', '\0', '');
INSERT INTO `t_dispatch` VALUES ('9', 'Plan123456', '0', '1', '1', '1', '大丰', '1', '1', '1899-12-08', '1', '1899-12-13', '1', '1.0000', '1.0000', '1', null, null, '\0', '\0', '\0');
INSERT INTO `t_dispatch` VALUES ('10', 'Plan123456', '0', '1', '1', '1', '盐城', '1', '20', '1899-12-08', '600', '1899-12-13', '20.66', '200.0000', '1.0000', '1', null, null, '', '', '\0');
INSERT INTO `t_dispatch` VALUES ('16', 'Plan123456', '0', '1', '1', '1', '蚌埠', '1', '1', '1899-11-30', '1', '1899-12-12', '1', '1.0000', '1.0000', '1', '2017-08-28', null, '', '\0', '\0');

-- ----------------------------
-- Table structure for t_dispatch_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_dispatch_detail`;
CREATE TABLE `t_dispatch_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_no_id` int(11) DEFAULT NULL,
  `consignee` varchar(20) DEFAULT NULL COMMENT '收货单位',
  `contants` varchar(20) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) DEFAULT NULL,
  `planned_qty` decimal(10,4) DEFAULT NULL,
  `freight_rates` decimal(10,4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dispatch_detail
-- ----------------------------
INSERT INTO `t_dispatch_detail` VALUES ('1', '1', '希望饲料', '张强', '15605112545', '600.0000', '636.6000');
INSERT INTO `t_dispatch_detail` VALUES ('2', '2', '华康饲料', '刘能', '15600551244', '500.0000', '5668.5500');
INSERT INTO `t_dispatch_detail` VALUES ('3', '3', '金华饲料厂', '马鑫', '13545844552', '600.7000', '625.1100');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_name` varchar(20) NOT NULL COMMENT '菜单名',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `url` varchar(255) DEFAULT NULL COMMENT '路径',
  `pid` int(11) DEFAULT NULL COMMENT '父级菜单 id',
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '调度管理', '', '', '0', '');
INSERT INTO `t_menu` VALUES ('2', '内勤管理', '', '', '0', '');
INSERT INTO `t_menu` VALUES ('3', '现场管理', '', '', '0', '');
INSERT INTO `t_menu` VALUES ('4', '结算审核表', '', '', '0', '');
INSERT INTO `t_menu` VALUES ('5', '结算批改申请', '', '', '0', '');
INSERT INTO `t_menu` VALUES ('6', '基础数据', '', '', '0', '');
INSERT INTO `t_menu` VALUES ('7', '统计管理', '', '', '0', '');
INSERT INTO `t_menu` VALUES ('8', '计划管理', '', '/planManage/dispatch', '1', '');
INSERT INTO `t_menu` VALUES ('9', '调度交接表', '', '/planManage/handover', '1', '');
INSERT INTO `t_menu` VALUES ('10', '待结算审核表', '', '', '1', '');
INSERT INTO `t_menu` VALUES ('11', '结算审核表', '', '', '1', '');
INSERT INTO `t_menu` VALUES ('12', '计划调度表', '', '/insidejob/plandispatch', '2', '');
INSERT INTO `t_menu` VALUES ('13', '合同管理', '', '/insidejob/contract', '2', '');
INSERT INTO `t_menu` VALUES ('14', '水路货物运单', '', '', '3', '');
INSERT INTO `t_menu` VALUES ('15', '待结算审核表', '', '', '3', '');
INSERT INTO `t_menu` VALUES ('16', '结算审核表', '', '', '3', '');
INSERT INTO `t_menu` VALUES ('17', '结算审核表（经理）', '', '', '4', '');
INSERT INTO `t_menu` VALUES ('18', '结算审核表（财务）', '', '', '4', '');
INSERT INTO `t_menu` VALUES ('19', '船舶运费结算清单', '', '', '4', '');
INSERT INTO `t_menu` VALUES ('20', '客户运费结算清单', '', '', '4', '');
INSERT INTO `t_menu` VALUES ('21', '结算复核', '', '', '4', '');
INSERT INTO `t_menu` VALUES ('22', '结算审批', '', '', '4', '');
INSERT INTO `t_menu` VALUES ('23', '出纳', '', '', '4', '');
INSERT INTO `t_menu` VALUES ('24', '结算批改申请表', '', '', '5', '');
INSERT INTO `t_menu` VALUES ('25', '结算批改申请表审批', '', '', '5', '');
INSERT INTO `t_menu` VALUES ('26', '全局审批', '', '', '5', '');
INSERT INTO `t_menu` VALUES ('27', '船舶信息管理', '', '', '6', '');
INSERT INTO `t_menu` VALUES ('28', '客户信息管理', '', '', '6', '');
INSERT INTO `t_menu` VALUES ('29', '员工信息管理', '', '', '6', '');
INSERT INTO `t_menu` VALUES ('50', '系统管理', 'fa-cogs', '', '0', '');
INSERT INTO `t_menu` VALUES ('51', '角色管理', '', '/system/role', '50', '');
INSERT INTO `t_menu` VALUES ('52', '账号管理', '', '/system/user', '50', '');
INSERT INTO `t_menu` VALUES ('53', '菜单管理', '', '/system/menu', '50', '');
INSERT INTO `t_menu` VALUES ('54', '权限管理', '', '/system/authority', '50', '');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_type` varchar(20) NOT NULL COMMENT '角色',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '超级管理员', '超级管理员');

-- ----------------------------
-- Table structure for t_role_button
-- ----------------------------
DROP TABLE IF EXISTS `t_role_button`;
CREATE TABLE `t_role_button` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色 id',
  `button_ids` varchar(1024) DEFAULT NULL COMMENT '按钮 ids',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_button
-- ----------------------------
INSERT INTO `t_role_button` VALUES ('1', '1', '');
INSERT INTO `t_role_button` VALUES ('2', '2', '');
INSERT INTO `t_role_button` VALUES ('3', '3', '');
INSERT INTO `t_role_button` VALUES ('4', '4', '');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色 id',
  `menu_ids` varchar(512) NOT NULL COMMENT '菜单 ids',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('1', '1', '0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,50,51,52,53,54,60,61');
INSERT INTO `t_role_menu` VALUES ('2', '2', '0,2,22,23,24,25,26,27,3,11,14,15');
INSERT INTO `t_role_menu` VALUES ('3', '3', '0,4,19,20,21');
INSERT INTO `t_role_menu` VALUES ('4', '4', '0,1,12,13,16,17,18');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色 id',
  `account` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `user_name` varchar(20) NOT NULL COMMENT '姓名',
  `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1：启用 0：冻结',
  `type` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '1', 'admin', '6697BFAAD9A17D787670B699A12BC9DD3B8A544808CE6C73F1722693', 'admin', '1', '0');
