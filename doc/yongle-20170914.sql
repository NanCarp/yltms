/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : yongle

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-09-14 10:25:32
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
INSERT INTO `t_base_customer` VALUES ('3', '华夏运输有限公司', '港口公司', '华夏', '经理', '13513644785', '0515-12457856', '0515-12457856', '124578996', '1245784@qq.com', '江苏省花市路360号', '华城运输有限公司', '32055214547745444', '招商银行', '6222024301071818379', '镇江市南京路33号', '12457899665', '上传测试1.txt', '测试文件2.xls', '测试文件1.xls', '粮食类', '百姓粮仓有限公司', '华耀食品加工有限公司', '200吨水位');

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
INSERT INTO `t_base_goods` VALUES ('1', '玉米', '2.50', '2.5000', '玉米1', '2017-09-13', 'admin');
INSERT INTO `t_base_goods` VALUES ('2', '小麦', '1.50', '1.5000', '小麦', '2017-09-13', 'admin');

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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dispatch_ship_id` int(11) DEFAULT NULL,
  `loss` decimal(15,4) DEFAULT NULL COMMENT '损耗',
  `fixed_loss` decimal(15,4) DEFAULT NULL COMMENT '定耗',
  `exceed_loss` decimal(15,4) DEFAULT NULL COMMENT '超耗',
  `port_construction_fee` decimal(15,4) DEFAULT NULL COMMENT '港建费',
  `deduct_price` decimal(15,4) DEFAULT NULL COMMENT '扣价',
  `deduct_money` decimal(15,4) DEFAULT NULL COMMENT '扣款',
  `demurrage_days` int(11) DEFAULT NULL COMMENT '滞期天数',
  `extended_days` int(11) DEFAULT NULL COMMENT '超期天数',
  `demurrage_charges` decimal(15,4) DEFAULT NULL COMMENT '滞期费',
  `other_charges` varchar(100) DEFAULT NULL COMMENT '其他费用',
  `payable_amount` decimal(15,4) DEFAULT NULL COMMENT '应付金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer_settle
-- ----------------------------
INSERT INTO `t_customer_settle` VALUES ('1', '33', '2.0000', '1.5000', '0.5000', null, '2.0000', '1.0000', '11', null, null, null, null);
INSERT INTO `t_customer_settle` VALUES ('2', '34', '3.0000', '2.1000', '0.9000', '222.0000', '3.0000', '2.7000', '22', '22', '2222.0000', '11.0000', '1111.0000');
INSERT INTO `t_customer_settle` VALUES ('11', '37', '6.0000', '5.0000', '1.0000', null, '2.5000', '2.5000', null, null, null, null, null);
INSERT INTO `t_customer_settle` VALUES ('12', '38', '0.8000', '5.0000', null, null, '2.5000', null, null, null, null, null, null);

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
  `dispatch_settle_state` int(1) DEFAULT '0' COMMENT '待审核结算表（调度）审核状态，0，待审核，1.已审核，2，取消审核',
  `site_settle_state` int(1) DEFAULT '0' COMMENT '审核结算表（现场）审核状态，0，待审核，1.已审核，2，取消审核',
  `bursar_settle_state` int(1) DEFAULT '0' COMMENT '审核结算表（复核）审核状态，0，待审核，1.已审核，2，取消审核',
  `manager_settle_state` int(1) DEFAULT '0' COMMENT '审核结算表（经理复核）审核状态，0，待审核，1.已审核，2，取消审核,3.驳回',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dispatch
-- ----------------------------
INSERT INTO `t_dispatch` VALUES ('24', '20170905001', '1', '玉米', '无锡海力士', '青岛', '泰州', null, '23', '2017-09-05', '0.012', '2017-09-14', '30', '300.0000', '5000.0000', null, '2017-09-05', 'admin', '', '\0', '\0', '4000.0000', null, '小赵', '1', '1', '0', '0', '0', '0');
INSERT INTO `t_dispatch` VALUES ('25', '20170907001', '1', '小麦', '华夏运输有限公司', '青岛港', '大丰', '鲸鱼号', '15', '2017-09-07', '1.5', '2017-09-08', '1.5', '5000.0000', '3000.0000', null, '2017-09-07', 'admin', '', '\0', '', '0.0000', 'admin', '小钱', '1', '1', '1', '1', '1', '0');
INSERT INTO `t_dispatch` VALUES ('26', '20170907002', '1', '小麦', '1', '1', '盐城', '1', '1', '2017-09-11', '1', '1899-12-13', '1', '1.0000', '1.0000', '1', '2017-09-07', 'admin', '\0', '', '\0', '1.0000', null, '小孙', '0', '0', '0', '0', '0', '0');
INSERT INTO `t_dispatch` VALUES ('27', '20170908001', '1', '小麦', '1', '1', '淮安', '1', '1', '2017-09-11', '1', '1899-12-27', '1', '1.0000', '1.0000', '11', '2017-09-08', 'admin', '\0', '', '\0', '1.0000', '老李', '老赵', '2', '0', '1', '1', '1', '3');
INSERT INTO `t_dispatch` VALUES ('28', '20170913001', '1', '玉米', '张家辉', '盐城', '扬州', '家辉号', '2000', '2017-09-13', '0.02', '2017-09-16', '11', '200.0000', '2000.2000', null, '2017-09-13', 'admin', '', '\0', '\0', '1600.2000', 'admin', '张晓明', '1', '1', '1', '1', '1', '1');
INSERT INTO `t_dispatch` VALUES ('29', '20170913002', '2', '11', '11', '11', '扬州', '11', '11', '2017-09-07', '11', '2017-09-13', '11', '11.0000', '11.0000', null, '2017-09-13', 'admin', '\0', '\0', '\0', '0.0000', 'admin', '', '0', '0', '0', '0', '0', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dispatch_detail
-- ----------------------------
INSERT INTO `t_dispatch_detail` VALUES ('14', '24', '江苏华康食品有限公司', '张强', '1560511212', '8000.0000', '600.0000');
INSERT INTO `t_dispatch_detail` VALUES ('15', '25', '华美有限公司', '刘总', '13022222222', '3000.0000', '20.0000');
INSERT INTO `t_dispatch_detail` VALUES ('16', '28', '温氏集团', '张海', '1560855124', '800.0000', '20.0000');
INSERT INTO `t_dispatch_detail` VALUES ('17', '28', '张氏集团', '刘能', '15605221585', '900.0000', '20.0000');
INSERT INTO `t_dispatch_detail` VALUES ('18', '29', '11', '11', '13022222222', '11.0000', '11.0000');
INSERT INTO `t_dispatch_detail` VALUES ('19', '29', '22', '22', '13022222222', '22.0000', '22.0000');

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
  `refuel_type` varchar(20) DEFAULT '' COMMENT '加油方式',
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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dispatch_ship
-- ----------------------------
INSERT INTO `t_dispatch_ship` VALUES ('13', '24', '明远号1', '李想', '17089789521', '321283199001015610', '500.0000', '500.0000', null, '2017-09-06', '30.0000', '15200.0000', '90.0000', '中石油', '80.0000', null, null, null, null, null, null, null, null, '14');
INSERT INTO `t_dispatch_ship` VALUES ('14', '24', '明远号', '李想', '17089789521', '321283199001015610', '500.0000', '500.0000', '2017-09-07', '2017-09-06', '30.0000', '15200.0000', '90.0000', '中石化', '80.0000', null, '包', '吨', '2017-09-22', '499.0000', '2017-09-26', '1', null, '15');
INSERT INTO `t_dispatch_ship` VALUES ('15', '27', '智博号', '王智', '1560522121', '32056612345461545', '500.0000', '500.0000', '2017-09-08', '2017-09-20', '30.0000', '15200.2220', '80.0000', '自加油', '80.0000', null, null, null, '2017-09-01', '200.0000', '2017-09-13', '1', null, '12');
INSERT INTO `t_dispatch_ship` VALUES ('33', '25', '蛟龙号', '张大海', '15605122124', '320981199211234775', '1585.0000', '3000.0000', null, '2017-09-08', '15.5500', '24646.7500', null, '中石化', null, null, null, null, '2017-09-11', '1500.0000', '2017-09-11', '2', null, '15');
INSERT INTO `t_dispatch_ship` VALUES ('34', '25', '大洋号', '刘可', '15605122124', '320981199211234775', '1333.0000', '1000.0000', null, '2017-09-15', '22.6000', '30125.8000', null, '中石化', null, null, null, null, '2017-09-11', '1300.0000', '2017-09-11', '3', null, '15');
INSERT INTO `t_dispatch_ship` VALUES ('35', '24', '明远号1', '张明', '15605122124', '320981199211234775', '82.0000', '100.0000', null, '2017-09-15', null, null, null, '中石油', null, null, null, null, null, null, null, null, null, '15');
INSERT INTO `t_dispatch_ship` VALUES ('37', '28', '小李号', '李小龙', '15605122124', '320981199211234668', '200.0000', '200.0000', '2017-09-13', '2017-09-13', '20.0000', '4000.0000', '200.0000', '中石化', '200.0000', '200.0000', '精致玉米', '吨', '2017-09-13', '194.0000', '2017-09-13', '2', null, '16');
INSERT INTO `t_dispatch_ship` VALUES ('38', '28', '戴莫号', '李代沫', '15605122124', '320981199211234668', '200.0000', '200.0000', '2017-09-13', '2017-09-13', '20.0000', '4000.0000', '3000.0000', '中石化', '200.0000', '200.0000', '精致玉米', '吨', '2017-09-20', '199.2000', '2017-09-13', '3', null, '16');
INSERT INTO `t_dispatch_ship` VALUES ('42', '29', '11', '11', '15605122124', '320981199211234775', '5.0000', '11.0000', null, '1899-12-31', null, null, null, null, null, null, null, null, null, null, null, null, null, '18');
INSERT INTO `t_dispatch_ship` VALUES ('43', '29', '22', '22', '13000000000', '320981199211234775', '6.0000', '11.0000', null, '2017-09-13', null, null, null, null, null, null, null, null, null, null, null, null, null, '19');

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
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '计划管理', 'fa-clipboard', '', '0', '');
INSERT INTO `t_menu` VALUES ('2', '调度管理', 'fa-pencil-square-o', '', '0', '');
INSERT INTO `t_menu` VALUES ('3', '内勤管理', 'fa-comments-o', '', '0', '');
INSERT INTO `t_menu` VALUES ('4', '现场管理', 'fa-ship', '', '0', '');
INSERT INTO `t_menu` VALUES ('5', '结算批改申请', 'fa-table', '', '0', '');
INSERT INTO `t_menu` VALUES ('6', '结算审核', 'fa-check-square-o', '', '0', '');
INSERT INTO `t_menu` VALUES ('7', '基础数据', 'fa-database', '', '0', '');
INSERT INTO `t_menu` VALUES ('8', '统计管理', 'fa-bar-chart', '', '0', '');
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
INSERT INTO `t_menu` VALUES ('62', '销售统计', '', '/statistics/sales', '8', '');

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
INSERT INTO `t_role_menu` VALUES ('1', '1', '0,1,2,3,4,5,6,7,8,9,10,13,14,15,20,25,26,28,29,30,31,50,51,52,53,54,55,56,57,58,59,60,61,62');
INSERT INTO `t_role_menu` VALUES ('2', '2', '0,2,22,23,24,25,26,27,3,11,14,15');
INSERT INTO `t_role_menu` VALUES ('3', '3', '0,4,19,20,21');
INSERT INTO `t_role_menu` VALUES ('4', '4', '0,1,12,13,16,17,18');

-- ----------------------------
-- Table structure for t_settle_apply
-- ----------------------------
DROP TABLE IF EXISTS `t_settle_apply`;
CREATE TABLE `t_settle_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apply_date` date DEFAULT NULL COMMENT '申请日期',
  `dispatcher` varchar(20) DEFAULT NULL COMMENT '调度员',
  `plan_no` varchar(20) DEFAULT NULL COMMENT '计划号',
  `consignor` varchar(20) DEFAULT NULL COMMENT '托运人',
  `ship_name` varchar(20) DEFAULT NULL COMMENT '船名',
  `ship_owner_name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `ship_owner_phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `goods_name` varchar(20) DEFAULT NULL COMMENT '货物名称',
  `seagoing_vessel_name` varchar(20) DEFAULT NULL COMMENT '海轮名',
  `freight_begin_end` varchar(50) DEFAULT NULL COMMENT '运输起讫',
  `loading_tonnage` decimal(15,4) DEFAULT NULL COMMENT '实装吨位',
  `adjust_freight` decimal(15,4) DEFAULT NULL COMMENT '调整运费',
  `reason` varchar(100) DEFAULT NULL COMMENT '申请调整船户运费事由',
  `manager_review` int(1) DEFAULT '0' COMMENT '经理审批',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_settle_apply
-- ----------------------------
INSERT INTO `t_settle_apply` VALUES ('1', '2017-09-13', 'admin', '20170905001', '无锡海力士', '明远号', '李想', '17089789521', '高粱', null, '南京到苏州', '500.0000', '500.0000', '台风1', '2', '同意');

-- ----------------------------
-- Table structure for t_ship_settle
-- ----------------------------
DROP TABLE IF EXISTS `t_ship_settle`;
CREATE TABLE `t_ship_settle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dispatch_ship_id` int(11) DEFAULT NULL,
  `loss` decimal(15,4) DEFAULT NULL COMMENT '损耗',
  `fixed_loss` decimal(15,4) DEFAULT NULL COMMENT '定耗',
  `exceed_loss` decimal(15,4) DEFAULT NULL COMMENT '超耗',
  `port_construction_fee` decimal(15,4) DEFAULT NULL COMMENT '港建费',
  `deduct_money` decimal(15,4) DEFAULT NULL COMMENT '扣款',
  `demurrage_days` varchar(40) DEFAULT NULL COMMENT '滞期天数',
  `extended_days` int(11) DEFAULT NULL COMMENT '超期天数',
  `demurrage_charges` decimal(15,4) DEFAULT NULL COMMENT '滞港费',
  `other_charges` varchar(40) DEFAULT NULL COMMENT '其他费用',
  `payable_amount` decimal(15,4) DEFAULT NULL COMMENT '应收金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ship_settle
-- ----------------------------
INSERT INTO `t_ship_settle` VALUES ('1', '33', '2.0000', '1.5000', '0.5000', '1.0000', '1.0000', '5.11-5.14共13天2天雨天', '3', '4.0000', '过闸提档500', '6.0000');
INSERT INTO `t_ship_settle` VALUES ('2', '34', '3.0000', '2.1000', '0.9000', '260.0000', '2.7000', '6.4-6.15共11天', '2', '200.0000', '计划南通后转张家港等12天，补贴6元/吨', '333.0000');
INSERT INTO `t_ship_settle` VALUES ('12', '37', '6.0000', '5.0000', '1.0000', null, '2.5000', null, null, null, null, null);
INSERT INTO `t_ship_settle` VALUES ('13', '38', '0.8000', '5.0000', null, null, null, null, null, null, null, null);

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

-- ----------------------------
-- View structure for v_sales
-- ----------------------------
DROP VIEW IF EXISTS `v_sales`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_sales` AS select sum(`a`.`payable_amount`) AS `customer_total_payable_amount`,sum(`b`.`payable_amount`) AS `ship_total_payable_amount`,sum((`a`.`payable_amount` - `b`.`payable_amount`)) AS `total_profit`,`a`.`dispatch_ship_id` AS `dispatch_ship_id`,`a`.`loss` AS `customer_loss`,`a`.`fixed_loss` AS `customer_fixed_loss`,`a`.`exceed_loss` AS `customer_exceed_loss`,`a`.`port_construction_fee` AS `customer_port_construction_fee`,`a`.`deduct_price` AS `customer_deduct_price`,`a`.`deduct_money` AS `customer_deduct_money`,`a`.`demurrage_days` AS `customer_demurrage_days`,`a`.`extended_days` AS `customer_extended_days`,`a`.`demurrage_charges` AS `customer_demurrage_charges`,`a`.`other_charges` AS `customer_other_charges`,`a`.`payable_amount` AS `customer_payable_amount`,`b`.`loss` AS `ship_loss`,`b`.`fixed_loss` AS `ship_fixed_loss`,`b`.`exceed_loss` AS `ship_exceed_loss`,`b`.`port_construction_fee` AS `ship_port_construction_fee`,`b`.`deduct_money` AS `ship_deduct_money`,`b`.`demurrage_days` AS `ship_demurrage_days`,`b`.`extended_days` AS `ship_extended_days`,`b`.`demurrage_charges` AS `ship_demurrage_charges`,`b`.`other_charges` AS `ship_other_charges`,`b`.`payable_amount` AS `ship_payable_amount`,`c`.`dispatch_id` AS `dispatch_id`,`c`.`ship_name` AS `ship_name`,`c`.`ship_owner_name` AS `ship_owner_name`,`c`.`ship_owner_phone` AS `ship_owner_phone`,`c`.`id_card_no` AS `id_card_no`,`c`.`loading_tonnage` AS `loading_tonnage`,`c`.`available_tonnage` AS `available_tonnage`,`c`.`arrival_limit` AS `arrival_limit`,`c`.`arrival_date` AS `arrival_date`,`c`.`freight_price` AS `freight_price`,`c`.`total_freight` AS `total_freight`,`c`.`prepay` AS `prepay`,`c`.`refuel_type` AS `refuel_type`,`c`.`pre_refuel` AS `pre_refuel`,`c`.`left_amount` AS `left_amount`,`c`.`specifications` AS `specifications`,`c`.`unit` AS `unit`,`c`.`declare_date` AS `declare_date`,`c`.`received_quantity` AS `received_quantity`,`c`.`unloaded_date` AS `unloaded_date`,`c`.`rainy_days` AS `rainy_days`,`c`.`waterway_remark` AS `waterway_remark`,`c`.`dispatch_detail_id` AS `dispatch_detail_id`,`d`.`id` AS `id`,`d`.`plan_no` AS `plan_no`,`d`.`examine_state` AS `examine_state`,`d`.`goods_name` AS `goods_name`,`d`.`consignor` AS `consignor`,`d`.`delivery_dock` AS `delivery_dock`,`d`.`destination_port` AS `destination_port`,`d`.`seagoing_vessel_name` AS `seagoing_vessel_name`,`d`.`settle_price` AS `settle_price`,`d`.`estimated_arrvial_date` AS `estimated_arrvial_date`,`d`.`transport_consumption` AS `transport_consumption`,`d`.`discharge_period` AS `discharge_period`,`d`.`Excess_deduction_price` AS `Excess_deduction_price`,`d`.`berthing_tonnage` AS `berthing_tonnage`,`d`.`total_quantity` AS `total_quantity`,`d`.`remark` AS `remark`,`d`.`entry_time` AS `entry_time`,`d`.`entry_man` AS `entry_man`,`d`.`billing` AS `billing`,`d`.`port_fee` AS `port_fee`,`d`.`insurance` AS `insurance`,`d`.`left_quantity` AS `left_quantity`,`d`.`dispatcher` AS `dispatcher`,`d`.`site_dispatch` AS `site_dispatch`,`d`.`dispatch_review` AS `dispatch_review`,`d`.`dispatch_issue` AS `dispatch_issue`,`d`.`dispatch_settle_state` AS `dispatch_settle_state`,`d`.`site_settle_state` AS `site_settle_state`,`d`.`bursar_settle_state` AS `bursar_settle_state`,`d`.`manager_settle_state` AS `manager_settle_state` from (((`t_customer_settle` `a` left join `t_ship_settle` `b` on((`a`.`dispatch_ship_id` = `b`.`dispatch_ship_id`))) left join `t_dispatch_ship` `c` on((`a`.`dispatch_ship_id` = `c`.`id`))) left join `t_dispatch` `d` on((`c`.`dispatch_id` = `d`.`id`))) group by `d`.`id` ;
