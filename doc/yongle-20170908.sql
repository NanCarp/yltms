/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : yongle

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-09-08 16:58:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_base_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_base_customer`;
CREATE TABLE `t_base_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(20) DEFAULT NULL COMMENT '客户名称',
  `customer_type` varchar(20) DEFAULT NULL COMMENT '客户类型',
  `name_content` varchar(20) DEFAULT NULL COMMENT '姓名(联系人)',
  `post_contant` varchar(20) DEFAULT NULL COMMENT '职务(联系人)',
  `phone_contant` varchar(20) DEFAULT NULL COMMENT '手机号码(联系人)',
  `fixed_phone_contant` varchar(20) DEFAULT NULL COMMENT '固话号码(联系人)',
  `fax_contant` varchar(20) DEFAULT NULL COMMENT '传真号码',
  `qq_content` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `e_mail_content` varchar(20) DEFAULT NULL COMMENT 'e_mail',
  `address_contant` varchar(40) DEFAULT NULL COMMENT '地址(联系人)',
  `company_information` varchar(20) DEFAULT NULL COMMENT '单位名称（开票信息）',
  `taxpayer_distinguish_information` varchar(20) DEFAULT NULL COMMENT '纳税人识别号（开票信息）',
  `blankname_information` varchar(20) DEFAULT NULL COMMENT '开户银行（开票信息）',
  `blankaccount_infomation` varchar(20) DEFAULT NULL COMMENT '开户银行账号（开票信息）',
  `address_information` varchar(40) DEFAULT NULL COMMENT '地址（开票信息）',
  `phone_information` varchar(20) DEFAULT NULL COMMENT '电话（开票信息）',
  `license_copy_commerce` varchar(40) DEFAULT NULL COMMENT '企业营业执照副本（工商资料）',
  `registration_commerce` varchar(40) DEFAULT NULL COMMENT '税务登记证（工商资料）',
  `organization_commerce` varchar(40) DEFAULT NULL COMMENT '组织机构代码证（工商资料）',
  `variety_goods` varchar(20) DEFAULT NULL COMMENT '货物品种',
  `common_delivery_company` varchar(20) DEFAULT NULL COMMENT '常用发货单位',
  `common_receiving_company` varchar(20) DEFAULT NULL COMMENT '常用收货单位',
  `wharf_berth` varchar(20) DEFAULT NULL COMMENT '码头泊位情况',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_customer
-- ----------------------------
INSERT INTO `t_base_customer` VALUES ('2', '华城运输有限公司', '港口公司', '陈年', '经理', '13513644785', '0515-12457856', '0515-12457856', '124578996', '1245784@qq.com', '江苏省花市路360号', '华城运输有限公司', '32055214547745444', '招商银行', '62220245456625545588', '镇江市南京路33号', '12457899665', '半成品仓库导入.xls', '半成品仓库导入.xls', '半成品仓库导入.xls', '粮食类', '百姓粮仓有限公司', '华耀食品加工有限公司', '200吨水位');
INSERT INTO `t_base_customer` VALUES ('3', '华夏运输有限公司', '港口公司', '华夏', '经理', '13513644785', '0515-12457856', '0515-12457856', '124578996', '1245784@qq.com', '江苏省花市路360号', '华城运输有限公司', '32055214547745444', '招商银行', '6222024301071818379', '镇江市南京路33号', '12457899665', 'new 1.txt', 'new 1.txt', 'new 1.txt', '粮食类', '百姓粮仓有限公司', '华耀食品加工有限公司', '200吨水位');

-- ----------------------------
-- Table structure for t_base_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_base_goods`;
CREATE TABLE `t_base_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(20) NOT NULL,
  `fixed_loss` decimal(4,2) DEFAULT NULL COMMENT '定耗(%)',
  `deduct_price` decimal(10,4) DEFAULT NULL COMMENT '扣价',
  `remark` varchar(100) DEFAULT NULL,
  `entry_time` date DEFAULT NULL COMMENT '录入时间',
  `entry_man` varchar(20) DEFAULT NULL COMMENT '录入人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_goods
-- ----------------------------
INSERT INTO `t_base_goods` VALUES ('1', '玉米', '2.50', '2.5000', '玉米1', '2017-08-29', '小张');
INSERT INTO `t_base_goods` VALUES ('2', '小麦', '1.50', '1.5000', '小麦', '2017-08-29', '小张');

-- ----------------------------
-- Table structure for t_base_port
-- ----------------------------
DROP TABLE IF EXISTS `t_base_port`;
CREATE TABLE `t_base_port` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `port` varchar(20) NOT NULL COMMENT '港口',
  `port_code` varchar(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `entry_time` date DEFAULT NULL,
  `entry_man` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_port
-- ----------------------------
INSERT INTO `t_base_port` VALUES ('1', '上海港', 'CNSH', '上海港1', '2017-09-05', 'admin');
INSERT INTO `t_base_port` VALUES ('2', '苏州港', 'CNSZ', '', '2017-09-05', 'admin');
INSERT INTO `t_base_port` VALUES ('3', '青岛港', 'CNQD', '', '2017-09-05', 'admin');

-- ----------------------------
-- Table structure for t_base_ship
-- ----------------------------
DROP TABLE IF EXISTS `t_base_ship`;
CREATE TABLE `t_base_ship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shipowname` varchar(20) DEFAULT NULL COMMENT '船主姓名',
  `ship_name` varchar(20) DEFAULT NULL COMMENT '船舶名称',
  `phone_num` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `ID_number` varchar(20) DEFAULT NULL,
  `ship_ton` varchar(20) DEFAULT NULL COMMENT '吨位',
  `grade_sail` varchar(30) DEFAULT NULL COMMENT '可航行航区等级',
  `business_route` varchar(30) DEFAULT NULL COMMENT '业务航线',
  `open_bank` varchar(30) DEFAULT NULL COMMENT '开户银行（银行账户信息）',
  `name_bank` varchar(30) DEFAULT NULL COMMENT '姓名(银行账户信息)',
  `card_bank` varchar(30) DEFAULT NULL COMMENT '卡号（银行账户信息）',
  `updatetime_bank` date DEFAULT NULL COMMENT '更新日期(银行账户信息)',
  `visa_sheet` varchar(20) DEFAULT NULL COMMENT '签证薄（船舶船员证件信息）',
  `ship_inspection` varchar(20) DEFAULT NULL COMMENT '船检证书（船舶船员证件信息）',
  `loading` varchar(20) DEFAULT NULL COMMENT '载重证书（船舶船员证件信息）',
  `operation` varchar(20) DEFAULT NULL COMMENT '营运证书（船舶船员证件信息）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_ship
-- ----------------------------
INSERT INTO `t_base_ship` VALUES ('2', '张大海', '海航号1', '1560522121', '320981199211234775', '300', '内陆河流', '省内', '江苏银行', '张大海', '622022456774415455444', '2017-08-30', 'new 1.txt', 'new 1.txt', 'new 1.txt', 'new 1.txt');
INSERT INTO `t_base_ship` VALUES ('3', '张大海', '海航号2', '1560522121', '320981199211234775', '300', '内陆河流', '省内', '江苏银行', '张大海', '622022456774415455444', '2017-08-30', '半成品仓库导入.xls', 'src', 'src', 'src');
INSERT INTO `t_base_ship` VALUES ('4', '张大海', '海航号3', '1560522121', '320981199211234775', '300', '内陆河流', '省内', '江苏银行', '张大海', '622022456774415455444', '2017-08-30', 'src', 'src', 'src', 'src');
INSERT INTO `t_base_ship` VALUES ('6', '张大海', '蛟龙号', '15605122124', '320981199211234775', '3000', 'A级', '马六甲海峡', null, null, null, null, null, null, null, null);
INSERT INTO `t_base_ship` VALUES ('13', '张明', '明远号', '15605122124', '320981199211234775', '100', 'A级', '马六甲海峡', '招商银行', '张大海', '32095555225', '2017-09-01', null, null, null, null);
INSERT INTO `t_base_ship` VALUES ('14', '刘可', '大洋号', '15605122124', '320981199211234775', '1000', 'A级', 'C级', '招商银行', '张大海', '3205551212121', '2017-09-01', '半成品仓库导入 (10) (1).xls', '半成品仓库导入.xls', '半成品仓库导入 (10) (1).xls', '计划单.xlsx');
INSERT INTO `t_base_ship` VALUES ('15', '张明', '明远号1', '15605122124', '320981199211234775', '100', 'A级', '马六甲海峡', null, null, null, null, null, null, null, null);
INSERT INTO `t_base_ship` VALUES ('16', '胡一伟', '明远号2', '15605122124', '320981199211234775', '100', 'A级', '马六甲海峡', null, null, null, null, null, null, null, null);
INSERT INTO `t_base_ship` VALUES ('17', '李想', '明远号3', '15605122124', '320981199211234775', '100', 'A级', '马六甲海峡', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for t_base_ship_crew
-- ----------------------------
DROP TABLE IF EXISTS `t_base_ship_crew`;
CREATE TABLE `t_base_ship_crew` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t_base_ship_id` int(11) DEFAULT NULL COMMENT '船舶基础信息id',
  `crew_name` varchar(20) DEFAULT NULL COMMENT '船员姓名',
  `crew_certificate` varchar(20) DEFAULT NULL COMMENT '船员证书（船舶船员证件信息）',
  `ID_card_certificate` varchar(20) DEFAULT NULL COMMENT '身份证（船舶船员证件信息）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_ship_crew
-- ----------------------------
INSERT INTO `t_base_ship_crew` VALUES ('4', '13', '周红', '半成品仓库导入 (10) (1).xls', '320981199211245775');
INSERT INTO `t_base_ship_crew` VALUES ('5', '14', '马云梅', '半成品仓库导入 (8).xls', '320981199211245775');
INSERT INTO `t_base_ship_crew` VALUES ('6', '2', '刘海明', '永乐运输管理系统方案.docx', '320981199211245775');
INSERT INTO `t_base_ship_crew` VALUES ('8', '17', '打', '半成品仓库导入 (10) (1).xls', '320981199211245668');

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
  `create_time` datetime DEFAULT NULL,
  `review_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_button
-- ----------------------------
INSERT INTO `t_button` VALUES ('1', '51', '100', '新增', null, '2017-08-30 08:29:45', '2017-08-30 08:29:45');
INSERT INTO `t_button` VALUES ('2', '51', '101', '删除', null, '2017-08-30 08:30:07', '2017-08-30 08:30:33');
INSERT INTO `t_button` VALUES ('3', '51', '102', '编辑', null, '2017-08-30 08:30:51', '2017-08-30 08:30:51');
INSERT INTO `t_button` VALUES ('4', '51', '103', '查询', null, '2017-08-30 08:31:12', '2017-08-30 08:31:12');

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
-- Table structure for t_customer_settle
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_settle`;
CREATE TABLE `t_customer_settle` (
  `id` int(11) NOT NULL,
  `dispatch_ship_id` int(11) DEFAULT NULL,
  `port_construction_fee` decimal(15,4) DEFAULT NULL COMMENT '港建费',
  `demurrage_days` int(11) DEFAULT NULL,
  `extended_days` int(11) DEFAULT NULL COMMENT '超期天数',
  `demurrage_charges` decimal(15,4) DEFAULT NULL COMMENT '滞期费',
  `other_charges` decimal(15,4) DEFAULT NULL COMMENT '其他费用',
  `payable_amount` decimal(15,4) DEFAULT NULL COMMENT '应付金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer_settle
-- ----------------------------
INSERT INTO `t_customer_settle` VALUES ('1', '11', '111.0000', '11', '1', '1111.0000', '22.0000', '222.0000');
INSERT INTO `t_customer_settle` VALUES ('2', '12', '222.0000', '22', '22', '2222.0000', '11.0000', '111.0000');

-- ----------------------------
-- Table structure for t_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionary`;
CREATE TABLE `t_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(50) NOT NULL,
  `key` varchar(50) NOT NULL,
  `value` int(11) NOT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dictionary
-- ----------------------------
INSERT INTO `t_dictionary` VALUES ('1', '男', 'gender', '1', '男性');
INSERT INTO `t_dictionary` VALUES ('2', '女', 'gender', '0', '女性');

-- ----------------------------
-- Table structure for t_dispatch
-- ----------------------------
DROP TABLE IF EXISTS `t_dispatch`;
CREATE TABLE `t_dispatch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_no` varchar(20) DEFAULT NULL COMMENT '计划号',
  `examine_state` int(1) DEFAULT '0' COMMENT '(计划号审核状态)审核状态 0:待审核 1:已审核 2:取消审核',
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
  `left_quantity` decimal(15,4) DEFAULT NULL COMMENT '剩余吨数',
  `dispatcher` varchar(15) DEFAULT NULL COMMENT '调度员',
  `site_dispatch` varchar(20) DEFAULT NULL COMMENT '现场调度',
  `dispatch_review` int(1) DEFAULT '0' COMMENT '调度管理审核 0:待审核 1:已审核 2:取消审核',
  `dispatch_issue` int(1) DEFAULT '0' COMMENT '调度管理下发',
  `site_delivery` varchar(20) DEFAULT NULL COMMENT '现场发货负责人',
  `dispatch_settle_state` int(1) DEFAULT '0' COMMENT '待审核结算表（调度）审核状态，0，待审核，1.已审核，2，取消审核',
  `site_settle_state` int(1) DEFAULT '0' COMMENT '审核结算表（现场）审核状态，0，待审核，1.已审核，2，取消审核',
  `bursar_settle_state` int(1) DEFAULT '0' COMMENT '审核结算表（复核）审核状态，0，待审核，1.已审核，2，取消审核',
  `manager_settle_state` int(1) DEFAULT '0' COMMENT '审核结算表（复核）审核状态，0，待审核，1.已审核，2，取消审核,3.驳回',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dispatch
-- ----------------------------
INSERT INTO `t_dispatch` VALUES ('24', '20170905001', '1', '玉米', '无锡海力士', '青岛', '泰州', null, '23', '2017-09-05', '0.012', '2017-09-14', '30', '300.0000', '5000.0000', null, '2017-09-05', 'admin', '', '\0', '\0', '4000.0000', null, '小赵', '1', '1', null, '0', '0', '0', '0');
INSERT INTO `t_dispatch` VALUES ('25', '20170907001', '1', '小麦', '华夏运输有限公司', '青岛港', '大丰', '鲸鱼号', '15', '2017-09-07', '1.5', '2017-09-08', '1.5', '5000.0000', '3000.0000', null, '2017-09-07', 'admin', '', '\0', '', '0.0000', 'admin', '小钱', '0', '0', null, '0', '0', '0', '0');
INSERT INTO `t_dispatch` VALUES ('26', '20170907002', '1', '1', '1', '1', '盐城', '1', '1', '1899-12-06', '1', '1899-12-13', '1', '1.0000', '1.0000', '1', '2017-09-07', 'admin', '\0', '', '\0', '1.0000', null, '小孙', '0', '0', null, '0', '0', '0', '0');
INSERT INTO `t_dispatch` VALUES ('27', '20170908001', '1', '1', '1', '1', '淮安', '1', '1', '1899-11-28', '1', '1899-12-27', '1', '1.0000', '1.0000', '11', '2017-09-08', 'admin', '\0', '', '\0', '1.0000', '老李', '老赵', '0', '0', null, '0', '0', '0', '0');

-- ----------------------------
-- Table structure for t_dispatch_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_dispatch_detail`;
CREATE TABLE `t_dispatch_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_no_id` varchar(20) DEFAULT NULL COMMENT '计划号',
  `consignee` varchar(20) DEFAULT NULL COMMENT '收货单位',
  `contants` varchar(20) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `planned_qty` decimal(10,4) DEFAULT NULL COMMENT '计划数量',
  `freight_rates` decimal(10,4) DEFAULT NULL COMMENT '运价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dispatch_detail
-- ----------------------------
INSERT INTO `t_dispatch_detail` VALUES ('14', '24', '江苏华康食品有限公司', '张强', '1560511212', '8000.0000', '600.0000');
INSERT INTO `t_dispatch_detail` VALUES ('15', '25', '华美有限公司', '刘总', '13022222222', '3000.0000', '20.0000');

-- ----------------------------
-- Table structure for t_dispatch_ship
-- ----------------------------
DROP TABLE IF EXISTS `t_dispatch_ship`;
CREATE TABLE `t_dispatch_ship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dispatch_id` int(11) NOT NULL,
  `ship_name` varchar(20) DEFAULT NULL COMMENT '船名',
  `ship_owner_name` varchar(20) DEFAULT NULL COMMENT '船主姓名',
  `ship_owner_phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `id_card_no` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `loading_tonnage` decimal(10,4) DEFAULT NULL COMMENT '配载吨位',
  `available_tonnage` decimal(10,4) DEFAULT NULL COMMENT '可载吨位',
  `arrival_limit` date DEFAULT NULL COMMENT '运到期限',
  `arrival_date` date DEFAULT NULL COMMENT '到港日期',
  `freight_price` decimal(10,4) DEFAULT NULL COMMENT '运价',
  `total_freight` decimal(15,4) DEFAULT NULL COMMENT '总价',
  `prepay` decimal(15,4) DEFAULT NULL COMMENT '预付款',
  `refuel_type` varchar(20) DEFAULT NULL COMMENT '加油方式',
  `pre_refuel` decimal(15,4) DEFAULT NULL COMMENT '预加油',
  `left_amount` decimal(15,4) DEFAULT NULL COMMENT '剩余费用',
  `specifications` varchar(20) DEFAULT NULL COMMENT '规格',
  `unit` varchar(10) DEFAULT NULL COMMENT '单位',
  `declare_date` date DEFAULT NULL COMMENT '报港日期',
  `received_quantity` decimal(15,4) DEFAULT NULL COMMENT '收货数量',
  `unloaded_date` date DEFAULT NULL COMMENT '卸空日期',
  `rainy_days` int(11) DEFAULT NULL COMMENT '雨天数',
  `waterway_remark` varchar(100) DEFAULT NULL COMMENT '水路货运单备注',
  `dispatch_detail_id` int(11) DEFAULT NULL COMMENT '收货单位 id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dispatch_ship
-- ----------------------------
INSERT INTO `t_dispatch_ship` VALUES ('13', '24', '明远号1', '李想', '17089789521', '321283199001015610', '500.0000', '500.0000', null, '2017-09-06', '30.0000', '15200.0000', '90.0000', null, '80.0000', null, null, null, null, null, null, null, null, '14');
INSERT INTO `t_dispatch_ship` VALUES ('14', '24', '明远号', '李想', '17089789521', '321283199001015610', '500.0000', '500.0000', '2017-09-30', '2017-09-06', '30.0000', '15200.0000', '90.0000', null, '80.0000', null, '包', '吨', '2017-09-22', '499.0000', '2017-09-26', '1', null, '15');
INSERT INTO `t_dispatch_ship` VALUES ('15', '27', '智博号', '王智', '1560522121', '32056612345461545', '500.0000', '500.0000', '2017-09-08', '2017-09-20', '30.0000', '15200.2220', '80.0000', null, '80.0000', null, null, null, '2017-09-01', '200.0000', '2017-09-13', '1', null, '12');
INSERT INTO `t_dispatch_ship` VALUES ('33', '25', '蛟龙号', '张大海', '15605122124', '320981199211234775', '1585.0000', '3000.0000', null, '2017-09-08', '15.5500', '24646.7500', null, '中石化', null, null, null, null, null, null, null, null, null, '15');
INSERT INTO `t_dispatch_ship` VALUES ('34', '25', '大洋号', '刘可', '15605122124', '320981199211234775', '1333.0000', '1000.0000', null, '2017-09-15', '22.6000', '30125.8000', null, '中石化', null, null, null, null, null, null, null, null, null, '15');
INSERT INTO `t_dispatch_ship` VALUES ('35', '25', '明远号1', '张明', '15605122124', '320981199211234775', '82.0000', '100.0000', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '15');

-- ----------------------------
-- Table structure for t_insidejob_contract
-- ----------------------------
DROP TABLE IF EXISTS `t_insidejob_contract`;
CREATE TABLE `t_insidejob_contract` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `customer` varchar(20) DEFAULT NULL COMMENT '客户名称',
  `art_no` varchar(20) DEFAULT NULL COMMENT '货号',
  `dispatch_port` varchar(20) DEFAULT NULL COMMENT '发货港',
  `consignee` varchar(20) DEFAULT NULL COMMENT '收货单位',
  `freight` varchar(20) DEFAULT NULL COMMENT '运价',
  `quantity` decimal(12,4) DEFAULT NULL COMMENT '发货数量',
  `attachment` varchar(60) DEFAULT NULL COMMENT '附件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insidejob_contract
-- ----------------------------
INSERT INTO `t_insidejob_contract` VALUES ('2', '王晴', 'BP335022', '盐城港', '淮安麻城有限公司', '44', '5000.0000', '新建文本文档.txt');
INSERT INTO `t_insidejob_contract` VALUES ('3', '王晴', 'BP335022', '盐城港', '淮安麻城有限公司', '222', '5000.0000', '新建文本文档.txt');
INSERT INTO `t_insidejob_contract` VALUES ('4', '王晴', 'BP335022', '盐城港', '淮安麻城有限公司', '500', '5000.0000', '新建文本文档.txt');
INSERT INTO `t_insidejob_contract` VALUES ('5', '王晴', 'BP335022', '盐城港', '淮安麻城有限公司', '6000', '5000.0000', '新建文本文档.txt');
INSERT INTO `t_insidejob_contract` VALUES ('15', '王晴', 'BP335022', '盐城港', '淮安麻城有限公司', '6566', '5000.0000', '半成品仓库导入 (8).xls');

-- ----------------------------
-- Table structure for t_insidejob_contract_copy
-- ----------------------------
DROP TABLE IF EXISTS `t_insidejob_contract_copy`;
CREATE TABLE `t_insidejob_contract_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer` varchar(20) DEFAULT NULL COMMENT '客户名称',
  `art_no` varchar(20) DEFAULT NULL COMMENT '货号',
  `dispatch_port` varchar(20) DEFAULT NULL COMMENT '发货港',
  `consignee` varchar(20) DEFAULT NULL COMMENT '收货单位',
  `freight` varchar(20) DEFAULT NULL COMMENT '运价',
  `quantity` decimal(12,4) DEFAULT NULL COMMENT '发货数量',
  `attachment` varchar(20) DEFAULT NULL COMMENT '附件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insidejob_contract_copy
-- ----------------------------
INSERT INTO `t_insidejob_contract_copy` VALUES ('2', '王晴', 'BP335022', '盐城港', '淮安麻城有限公司', '60元/斤', '5000.0000', 'src');
INSERT INTO `t_insidejob_contract_copy` VALUES ('3', '王晴', 'BP335022', '盐城港', '淮安麻城有限公司', '60元/斤', '5000.0000', 'src');
INSERT INTO `t_insidejob_contract_copy` VALUES ('4', '王晴', 'BP335022', '盐城港', '淮安麻城有限公司', '60元/斤', '5000.0000', 'src');
INSERT INTO `t_insidejob_contract_copy` VALUES ('5', '王晴', 'BP335022', '盐城港', '淮安麻城有限公司', '60元/斤', '5000.0000', 'src');

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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '计划管理', '', '', '0', '');
INSERT INTO `t_menu` VALUES ('2', '调度管理', '', '', '0', '');
INSERT INTO `t_menu` VALUES ('3', '内勤管理', '', '', '0', '');
INSERT INTO `t_menu` VALUES ('4', '现场管理', '', '', '0', '');
INSERT INTO `t_menu` VALUES ('5', '结算批改申请', '', '', '0', '');
INSERT INTO `t_menu` VALUES ('6', '结算审核', '', '', '0', '');
INSERT INTO `t_menu` VALUES ('7', '基础数据', '', '', '0', '');
INSERT INTO `t_menu` VALUES ('8', '统计管理', '', '', '0', '');
INSERT INTO `t_menu` VALUES ('9', '计划下达', '', '/planManage/dispatch', '1', '');
INSERT INTO `t_menu` VALUES ('10', '调度交接表', '', '/planManage/handover', '2', '');
INSERT INTO `t_menu` VALUES ('13', '计划调度表', '', '/insidejob/plandispatch', '99', '');
INSERT INTO `t_menu` VALUES ('14', '计划调度表', '', '/insidejob/plan', '3', '');
INSERT INTO `t_menu` VALUES ('15', '调度交接表(现场)', '', '/site/handoversite', '4', '');
INSERT INTO `t_menu` VALUES ('20', '结算审核表', '', '/settle/managesettle', '6', '');
INSERT INTO `t_menu` VALUES ('25', '船舶运费结算清单', '', '/settle/ship', '6', '');
INSERT INTO `t_menu` VALUES ('26', '结算批改申请表', '', '/settlecorrect/app', '5', '');
INSERT INTO `t_menu` VALUES ('28', '船舶信息管理', '', '/dataBase/ship', '7', '');
INSERT INTO `t_menu` VALUES ('29', '客户信息管理', '', '/dataBase/customer', '7', '');
INSERT INTO `t_menu` VALUES ('30', '港口信息管理', '', '/database/port', '7', '');
INSERT INTO `t_menu` VALUES ('31', '货物基础数据', '', '/database/goods', '7', '');
INSERT INTO `t_menu` VALUES ('50', '系统管理', 'fa-cogs', '', '0', '');
INSERT INTO `t_menu` VALUES ('51', '角色管理', '', '/system/role', '50', '');
INSERT INTO `t_menu` VALUES ('52', '账号管理', '', '/system/user', '50', '');
INSERT INTO `t_menu` VALUES ('53', '菜单管理', '', '/system/menu', '50', '');
INSERT INTO `t_menu` VALUES ('54', '按钮管理', '', '/system/button', '50', '');
INSERT INTO `t_menu` VALUES ('55', '权限管理', '', '/system/authority', '50', '');
INSERT INTO `t_menu` VALUES ('56', '基础数据', '', '/system/dictionary', '50', '');
INSERT INTO `t_menu` VALUES ('57', '待结算审核表', '', '/planManage/waitsettle', '2', '');
INSERT INTO `t_menu` VALUES ('58', '水路货物运单', '', '/site/waterwayfreight', '4', '');
INSERT INTO `t_menu` VALUES ('59', '结算审核表', '', '/site/settle', '4', '');
INSERT INTO `t_menu` VALUES ('60', '客户运费结算清单', '', '/settle/customerfreight', '6', '');
INSERT INTO `t_menu` VALUES ('61', '合同管理', '', '/insidejob/contract', '3', '');

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
INSERT INTO `t_role_button` VALUES ('1', '1', '100,101,102,103');
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
INSERT INTO `t_role_menu` VALUES ('1', '1', '0,1,2,3,4,5,6,7,8,9,10,13,14,15,20,25,26,28,29,30,31,50,51,52,53,54,55,56,57,58,59,60,61');
INSERT INTO `t_role_menu` VALUES ('2', '2', '0,2,22,23,24,25,26,27,3,11,14,15');
INSERT INTO `t_role_menu` VALUES ('3', '3', '0,4,19,20,21');
INSERT INTO `t_role_menu` VALUES ('4', '4', '0,1,12,13,16,17,18');

-- ----------------------------
-- Table structure for t_statement
-- ----------------------------
DROP TABLE IF EXISTS `t_statement`;
CREATE TABLE `t_statement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shipper` varchar(20) DEFAULT NULL COMMENT '托运人',
  `goods_name` varchar(20) DEFAULT NULL COMMENT '货物名称',
  `seagoing_vessel_name` varchar(20) DEFAULT NULL COMMENT '海轮名称',
  `delivery_terminal` varchar(20) DEFAULT NULL COMMENT '发货港',
  `plan_no` varchar(20) DEFAULT NULL COMMENT '计划号s',
  `plan_date` date DEFAULT NULL COMMENT '计划日期',
  `flow` varchar(20) DEFAULT NULL COMMENT '流向',
  `ship_name` varchar(20) DEFAULT NULL COMMENT '船名',
  `plan_quantity` decimal(12,4) DEFAULT NULL,
  `loss` decimal(12,4) DEFAULT NULL,
  `discharging_period` date DEFAULT NULL COMMENT '卸货日期',
  `freight` decimal(12,4) DEFAULT NULL COMMENT '运价',
  `refueling_mode` varchar(20) DEFAULT NULL COMMENT '加油方式',
  `amount_money` decimal(12,4) DEFAULT NULL COMMENT '金额',
  `date_transhipment` date DEFAULT NULL COMMENT '装船日期',
  `actual_tonnage` decimal(12,4) DEFAULT NULL COMMENT '实际吨位',
  `advance_charge` decimal(12,4) DEFAULT NULL COMMENT '预付款',
  `remark` varchar(20) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_statement
-- ----------------------------
INSERT INTO `t_statement` VALUES ('1', '张大海', '玉米', '龙腾号', '江苏盐城', '201700021', '2017-08-22', '北流', '小石号', '6000.0000', '2.2200', '2017-08-28', '20.0000', '加油站加油', '2360.6600', '2017-08-28', '562.3600', '600.0000', '号');
INSERT INTO `t_statement` VALUES ('2', '张大海', '玉米', '龙腾号', '江苏盐城', '201700021', '2017-08-22', '北流', '小石号', '6000.0000', '2.2200', '2017-08-28', '20.0000', '加油站加油', '2360.6600', '2017-08-28', '562.3600', '600.0000', '号');
INSERT INTO `t_statement` VALUES ('3', '张大海', '玉米', '龙腾号', '江苏盐城', '201700021', '2017-08-22', '北流', '小石号', '6000.0000', '2.2200', '2017-08-28', '20.0000', '加油站加油', '2360.6600', '2017-08-28', '562.3600', '600.0000', '号');
INSERT INTO `t_statement` VALUES ('4', '张大海', '玉米', '龙腾号', '江苏盐城', '201700021', '2017-08-22', '北流', '小石号', '6000.0000', '2.2200', '2017-08-28', '20.0000', '加油站加油', '2360.6600', '2017-08-28', '562.3600', '600.0000', '号');
INSERT INTO `t_statement` VALUES ('5', '张大海', '玉米', '龙腾号', '江苏盐城', '201700021', '2017-08-22', '北流', '小石号', '6000.0000', '2.2200', '2017-08-28', '20.0000', '加油站加油', '2360.6600', '2017-08-28', '562.3600', '600.0000', '号');
INSERT INTO `t_statement` VALUES ('6', '张大海', '玉米', '龙腾号', '江苏盐城', '201700021', '2017-08-22', '北流', '小石号', '6000.0000', '2.2200', '2017-08-28', '20.0000', '加油站加油', '2360.6600', '2017-08-28', '562.3600', '600.0000', '号');

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
