/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : yongle

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-09-27 09:52:05
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
INSERT INTO `t_base_customer` VALUES ('3', '华夏运输有限公司', '港口公司', '华夏', '经理', '13513644785', '0515-12457856', '0515-12457856', '124578996', '1245784@qq.com', '江苏省花市路360号', '华城运输有限公司', '32055214547745444', '招商银行', '6222024301071818379', '镇江市南京路33号', '12457899665', '半成品仓库导入 (8).xls', '测试文件2.xls', '测试文件1.xls', '粮食类', '百姓粮仓有限公司', '华耀食品加工有限公司', '200吨水位');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_goods
-- ----------------------------
INSERT INTO `t_base_goods` VALUES ('1', '玉米', '2.50', '2.5000', '玉米1', '2017-09-13', 'admin');
INSERT INTO `t_base_goods` VALUES ('2', '小麦', '1.50', '1.5000', '小麦', '2017-09-13', 'admin');
INSERT INTO `t_base_goods` VALUES ('3', '五眼沙', '0.22', '12.0000', '数字', '2017-09-14', 'admin');
INSERT INTO `t_base_goods` VALUES ('4', '稻', '1.50', '1.5000', '稻子', '2017-09-21', 'admin');

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_ship
-- ----------------------------
INSERT INTO `t_base_ship` VALUES ('2', '张大海1', '海航号1', '15605221211', '320981199211234775', '300', '内陆河流', '省内', '江苏银行', '张大海', '622022456774415455444', '2017-08-30', 'new 1.txt', 'new 1.txt', 'new 1.txt', 'new 1.txt');
INSERT INTO `t_base_ship` VALUES ('3', '张大海2', '海航号2', '15605221212', '320981199211234775', '300', '内陆河流', '省内', '江苏银行', '张大海', '622022456774415455444', '2017-08-30', '半成品仓库导入.xls', 'src', 'src', 'src');
INSERT INTO `t_base_ship` VALUES ('4', '张大海3', '海航号3', '15605221213', '320981199211234775', '300', '内陆河流', '省内', '江苏银行', '张大海', '622022456774415455444', '2017-08-30', 'src', 'src', 'src', 'src');
INSERT INTO `t_base_ship` VALUES ('6', '张大海', '蛟龙号', '15605122124', '320981199211234775', '3000', 'A级', '马六甲海峡', null, null, null, null, null, null, null, null);
INSERT INTO `t_base_ship` VALUES ('13', '张明', '明远号', '15605122124', '320981199211234775', '600', 'A级', '马六甲海峡', '招商银行', '张大海', '32095555225', '2017-09-01', null, null, null, null);
INSERT INTO `t_base_ship` VALUES ('14', '刘可', '大洋号', '15605122124', '320981199211234775', '1000', 'A级', 'C级', '招商银行', '张大海', '3205551212121', '2017-09-01', '半成品仓库导入 (10) (1).xls', '半成品仓库导入.xls', '半成品仓库导入 (10) (1).xls', '计划单.xlsx');
INSERT INTO `t_base_ship` VALUES ('15', '张明', '明远号1', '15605122124', '320981199211234775', '500', 'A级', '马六甲海峡', null, null, null, null, null, null, null, null);
INSERT INTO `t_base_ship` VALUES ('16', '胡一伟', '明远号2', '15605122124', '320981199211234775', '500', 'A级', '马六甲海峡', null, null, null, null, null, null, null, null);
INSERT INTO `t_base_ship` VALUES ('17', '李想', '明远号3', '15605122124', '320981199211234775', '500', 'A级', '马六甲海峡', null, null, null, null, null, null, null, null);
INSERT INTO `t_base_ship` VALUES ('18', '张三', '张珊号', '13011111111', '320619990101333', '900', 'A级', '马六甲', '中央银行', '张三', '222', '2017-09-05', '新建文本文档.txt', '新建文本文档.txt', '新建文本文档.txt', '新建文本文档.txt');

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_ship_crew
-- ----------------------------
INSERT INTO `t_base_ship_crew` VALUES ('4', '13', '周红', '半成品仓库导入 (10) (1).xls', '320981199211245775');
INSERT INTO `t_base_ship_crew` VALUES ('5', '14', '马云梅', '半成品仓库导入 (8).xls', '320981199211245775');
INSERT INTO `t_base_ship_crew` VALUES ('6', '2', '刘海明', '永乐运输管理系统方案.docx', '320981199211245775');
INSERT INTO `t_base_ship_crew` VALUES ('8', '17', '打', '半成品仓库导入 (10) (1).xls', '320981199211245668');
INSERT INTO `t_base_ship_crew` VALUES ('9', '18', '张三三', '新建文本文档.txt', '320622190001012222');

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
INSERT INTO `t_button` VALUES ('1', '81', '100', '新增', null, '2017-08-30 08:29:45', '2017-08-30 08:29:45');
INSERT INTO `t_button` VALUES ('2', '81', '101', '删除', null, '2017-08-30 08:30:07', '2017-08-30 08:30:33');
INSERT INTO `t_button` VALUES ('3', '81', '102', '编辑', null, '2017-08-30 08:30:51', '2017-08-30 08:30:51');
INSERT INTO `t_button` VALUES ('4', '81', '103', '查询', null, '2017-08-30 08:31:12', '2017-08-30 08:31:12');

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
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer_settle
-- ----------------------------
INSERT INTO `t_customer_settle` VALUES ('56', '154', '54.0000', '4.1350', '49.8650', null, '19.2000', '957.4080', null, null, null, null, null);
INSERT INTO `t_customer_settle` VALUES ('57', '155', '46.0000', '0.8650', '45.1350', null, '19.2000', '866.5920', null, null, null, null, null);

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
  `examine_state` int(1) DEFAULT '0' COMMENT '(计划号下发审核状态)审核状态 0:待审核 1:已审核 2:取消审核',
  `goods_name` varchar(20) DEFAULT NULL COMMENT '货物名称',
  `consignor` varchar(20) DEFAULT NULL COMMENT '托运人',
  `delivery_dock` varchar(20) DEFAULT NULL COMMENT '发货港',
  `seagoing_vessel_name` varchar(20) DEFAULT NULL COMMENT '海轮名称',
  `plan_state` tinyint(2) DEFAULT NULL COMMENT '计划状态(是否全部分配完）',
  `estimated_arrvial_date` date DEFAULT NULL COMMENT '预抵日期',
  `transport_consumption` decimal(15,4) DEFAULT NULL COMMENT '运输定耗',
  `discharge_period` int(3) DEFAULT NULL COMMENT '卸货期限',
  `Excess_deduction_price` decimal(16,4) DEFAULT NULL COMMENT '超耗扣价',
  `berthing_tonnage` decimal(16,4) DEFAULT NULL COMMENT '靠泊吨位',
  `total_quantity` decimal(16,4) DEFAULT NULL COMMENT '合计数量',
  `real_quantity` decimal(16,4) DEFAULT NULL COMMENT '实装吨位',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `entry_time` date DEFAULT NULL COMMENT '录入时间',
  `entry_man` varchar(255) DEFAULT NULL COMMENT '录入人',
  `billing` bit(1) DEFAULT b'0' COMMENT '开票',
  `port_fee` bit(1) DEFAULT b'0' COMMENT '港建费',
  `insurance` bit(1) DEFAULT b'0' COMMENT '保险',
  `left_quantity` decimal(15,4) DEFAULT NULL COMMENT '剩余吨数',
  `is_contant` tinyint(2) DEFAULT NULL COMMENT '是否包含装货时间',
  `site_dispatch` varchar(20) DEFAULT NULL COMMENT '现场调度',
  `dispatch_settle_state` int(1) DEFAULT '0' COMMENT '待审核结算表（调度）审核状态，0，待审核，1.已审核，2，取消审核',
  `site_settle_state` int(1) DEFAULT '0' COMMENT '审核结算表（现场）审核状态，0，待审核，1.已审核，2，取消审核',
  `bursar_settle_state` int(1) DEFAULT '0' COMMENT '审核结算表（复核）审核状态，0，待审核，1.已审核，2，取消审核',
  `manager_settle_state` int(1) DEFAULT '0' COMMENT '审核结算表（经理复核）审核状态，0，待审核，1.已审核，2，取消审核,3.驳回',
  `document_status` int(1) DEFAULT '0' COMMENT '单据状态 0：未配完 1：已配完',
  `isapplication` int(1) DEFAULT '0' COMMENT '该计划号是否存在结算批改申请,1存在，0不存在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dispatch
-- ----------------------------
INSERT INTO `t_dispatch` VALUES ('37', '20170922001', '1', '玉米', '江苏雨润集团', '扬州港', '星美', '1', '2017-09-22', '2.5000', '1', '60.0000', null, '500.0000', null, '预计今天离港。', '2017-09-22', 'admin', '\0', '\0', '\0', '500.0000', '1', '张三', '0', '0', '0', '0', '0', '0');
INSERT INTO `t_dispatch` VALUES ('38', '20170924001', '1', '玉米', '张明', '盐城', '平安号', '1', '2017-09-18', '2.5000', '2', '23.0000', null, '200.0000', '200.0000', null, '2017-09-24', 'admin', '', '\0', '\0', '200.0000', '1', '黄师傅', '0', '0', '0', '0', '1', '0');
INSERT INTO `t_dispatch` VALUES ('39', '20170925001', '1', '稻', '华夏运输有限公司', '洋口港', '大禹号', '1', '2017-09-26', '1.5000', '3', '2.2000', null, '800.0000', '800.0000', null, '2017-09-25', 'admin', '', '', '', '800.0000', '1', '辛勤', '0', '0', '1', '0', '1', '0');
INSERT INTO `t_dispatch` VALUES ('40', '20170925002', '1', '玉米', '张海', '盐城', '大海', '1', '2017-09-25', '2.5000', '6', '20.0000', null, '200.0000', null, null, '2017-09-25', 'admin', '\0', '', '', '200.0000', '1', null, '0', '0', '0', '0', '0', '0');
INSERT INTO `t_dispatch` VALUES ('41', '20170925003', '0', '小麦', '宜兴市新庄镇粮油管理所', '靖江青龙港', '安诚山', '1', '2017-10-01', '1.5000', '5', '3.5000', null, '4988.0000', '4988.0000', null, '2017-09-25', 'admin', '', '\0', '', '4988.0000', '1', '许彬', '0', '0', '0', '0', '1', '0');
INSERT INTO `t_dispatch` VALUES ('42', '20170925004', '0', '稻', '中粮米业有限公司', '江苏昆山国家粮食储备库新库', null, '1', '2017-09-29', '1.5000', '6', '3.5000', null, '1000.0000', '1000.0000', null, '2017-09-25', 'admin', '', '\0', '', '1000.0000', '1', '陈立', '0', '0', '1', '0', '1', '0');
INSERT INTO `t_dispatch` VALUES ('44', '20170926001', '0', '玉米', '马明亮', '盐城', '博远好', '1', '2017-09-26', '2.5000', '6', '2.5000', null, '2000.0000', '2000.0000', '一切正常', '2017-09-26', 'admin', '', '', '\0', '2000.0000', '0', null, '0', '0', '1', '0', '1', '0');
INSERT INTO `t_dispatch` VALUES ('45', '20170926002', '1', '玉米', '张明', '阿迪', '省道', '1', '2017-09-26', '2.5000', '6', '19.2000', null, '2000.0000', '2000.0000', null, '2017-09-26', 'admin', '\0', '\0', '\0', '2000.0000', '1', null, '0', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for t_dispatch_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_dispatch_detail`;
CREATE TABLE `t_dispatch_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_no_id` int(11) DEFAULT NULL COMMENT '计划号 id',
  `flow` varchar(20) DEFAULT NULL COMMENT '流向',
  `destination_port` varchar(20) DEFAULT NULL COMMENT '目的港',
  `consignee` varchar(20) DEFAULT NULL COMMENT '收货单位',
  `contants` varchar(20) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `planned_qty` decimal(10,4) DEFAULT NULL COMMENT '计划数量',
  `left_qty` decimal(10,4) DEFAULT NULL COMMENT '剩余数量',
  `freight_rates` decimal(10,4) DEFAULT NULL COMMENT '运价',
  `dispatcher_entry_time` date DEFAULT NULL,
  `dispatcher` varchar(20) DEFAULT NULL,
  `settle_price` decimal(15,4) DEFAULT NULL COMMENT '结算价',
  `guide_price` decimal(15,4) DEFAULT NULL COMMENT '指导价',
  `flow_state` int(11) DEFAULT '0' COMMENT '0：待配流向 1：已配流向',
  `remark` varchar(20) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dispatch_detail
-- ----------------------------
INSERT INTO `t_dispatch_detail` VALUES ('93', '37', '浙江温州', '温州27号港', '中粮集团', null, null, '300.0000', '0.0000', null, '2017-09-22', 'admin', '50.0000', '40.0000', '1', '加急');
INSERT INTO `t_dispatch_detail` VALUES ('94', '37', '上海', '浦东港', '中粮集团', null, null, '200.0000', '0.0000', null, '2017-09-27', 'admin', '50.0000', '40.0000', '0', '加急');
INSERT INTO `t_dispatch_detail` VALUES ('97', '38', '南京方向', '南京港', '南京嘉城有限公司', null, null, '150.0000', '0.0000', null, '2017-09-24', 'admin', '50.0000', '40.0000', '1', '无');
INSERT INTO `t_dispatch_detail` VALUES ('98', '38', '苏州港', '苏州港', '苏州昆泰有限公司', null, null, '50.0000', '0.0000', null, '2017-09-24', 'admin', '50.0000', '40.0000', '1', '无');
INSERT INTO `t_dispatch_detail` VALUES ('99', '39', '到福州', '福州', '华城运输有限公司', null, null, '300.0000', '0.0000', null, '2017-09-25', 'admin', '50.0000', '40.0000', '1', '急');
INSERT INTO `t_dispatch_detail` VALUES ('100', '39', '到海南', '海南', '华城运输有限公司', null, null, '500.0000', '0.0000', null, '2017-09-25', 'admin', '50.0000', '40.0000', '1', '急');
INSERT INTO `t_dispatch_detail` VALUES ('104', '40', '南京方向', '无锡', '天成有限公司', null, null, '100.0000', null, null, null, null, '30.2200', '20.2200', '0', '很好');
INSERT INTO `t_dispatch_detail` VALUES ('105', '40', '华纳方向', '合肥', '保航有限公司', null, null, '50.0000', null, null, null, null, '323.2200', '121.2200', '0', '很好');
INSERT INTO `t_dispatch_detail` VALUES ('106', '40', '话说方向', '江西', '江西赣州', null, null, '50.0000', null, null, null, null, '12.0000', '12.0000', '0', '');
INSERT INTO `t_dispatch_detail` VALUES ('107', '41', '1', '宜兴', '江苏南顺面粉有限公司', null, null, '3000.0000', '0.0000', null, '2017-09-25', 'admin', '25.0000', '18.0000', '1', '无');
INSERT INTO `t_dispatch_detail` VALUES ('108', '41', '2', '金坛', '江苏南顺食品有限公司', null, null, '1988.0000', '0.0000', null, '2017-09-25', 'admin', '26.0000', '19.0000', '1', '无');
INSERT INTO `t_dispatch_detail` VALUES ('109', '42', '张家港', '张家港', '东海粮油工业有限公司', null, null, '1000.0000', '0.0000', null, '2017-09-25', 'admin', '25.0000', '20.0000', '1', '全部发完');
INSERT INTO `t_dispatch_detail` VALUES ('121', '44', '南京流向', '南京港', '南京嘉城有限公司', null, null, '1000.0000', null, null, null, null, '60.0000', '59.0000', '0', '');
INSERT INTO `t_dispatch_detail` VALUES ('122', '44', '苏州流向', '苏州港', '苏州嘉城有限公司', null, null, '500.0000', null, null, null, null, '50.0000', '49.0000', '0', '');
INSERT INTO `t_dispatch_detail` VALUES ('132', '44', '常州方向', '常州港', '常州嘉城有限公司', null, null, '500.0000', null, null, null, null, '50.0000', '39.0000', '0', '');
INSERT INTO `t_dispatch_detail` VALUES ('133', '45', '华南', '南京', '南京', null, null, '1654.0000', '0.0000', null, '2017-09-26', 'admin', '20.0000', '20.0000', '1', '');
INSERT INTO `t_dispatch_detail` VALUES ('134', '45', '江西', '江西', '江西', null, null, '346.0000', '0.0000', null, '2017-09-26', 'admin', '20.0000', '20.0000', '1', '');

-- ----------------------------
-- Table structure for t_dispatch_ship
-- ----------------------------
DROP TABLE IF EXISTS `t_dispatch_ship`;
CREATE TABLE `t_dispatch_ship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dispatch_detail_id` int(11) DEFAULT NULL COMMENT '流向 id',
  `dispatch_id` int(11) DEFAULT NULL,
  `ship_name` varchar(20) DEFAULT NULL COMMENT '船名',
  `ship_owner_name` varchar(20) DEFAULT NULL COMMENT '船主姓名',
  `ship_owner_phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `id_card_no` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `loading_tonnage` decimal(10,4) DEFAULT NULL COMMENT '配载吨位',
  `available_tonnage` decimal(10,4) DEFAULT NULL COMMENT '可载吨位',
  `arrival_limit` int(11) DEFAULT NULL COMMENT '运到期限',
  `arrival_date` date DEFAULT NULL COMMENT '到港日期',
  `delivery_date` date DEFAULT NULL COMMENT '发货日期',
  `freight_price` decimal(10,4) DEFAULT NULL COMMENT '运价(单价)',
  `total_freight` decimal(15,4) DEFAULT NULL COMMENT '总价(运费)',
  `prepay` decimal(15,4) DEFAULT NULL COMMENT '预付款',
  `refuel_type` varchar(20) DEFAULT '' COMMENT '加油方式',
  `pre_refuel` decimal(15,4) DEFAULT NULL COMMENT '预加油',
  `site_refuel` decimal(15,4) DEFAULT NULL COMMENT '预加油(现场)',
  `site_pay` decimal(15,4) DEFAULT NULL COMMENT '预付款(现场)',
  `left_amount` decimal(15,4) DEFAULT NULL COMMENT '剩余费用',
  `specifications` varchar(20) DEFAULT NULL COMMENT '规格',
  `unit` varchar(10) DEFAULT NULL COMMENT '单位',
  `declare_date` date DEFAULT NULL COMMENT '报港日期--> 空船到港日期',
  `heavy_declare_date` date DEFAULT NULL COMMENT '重船报港日期',
  `delivery_quantity` decimal(15,4) DEFAULT NULL COMMENT '发货数量',
  `received_quantity` decimal(15,4) DEFAULT NULL COMMENT '收货数量',
  `unloaded_date` date DEFAULT NULL COMMENT '卸空日期',
  `rainy_days` int(11) DEFAULT NULL COMMENT '雨天数',
  `waterway_remark` varchar(100) DEFAULT NULL COMMENT '水路货运单备注',
  `dispatch_ship_remark` varchar(100) DEFAULT NULL,
  `over_guide_price` int(1) DEFAULT '0' COMMENT '1 运价超出指导价 0 未超过或审批通过',
  `ship_freight` decimal(15,4) DEFAULT NULL COMMENT '运费（船舶），收货数量*单价',
  `receipt_review` int(1) DEFAULT '0' COMMENT '运单审核 0：未审核 1：审核',
  `pay_status` int(1) DEFAULT '0' COMMENT '付款状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dispatch_ship
-- ----------------------------
INSERT INTO `t_dispatch_ship` VALUES ('113', '93', null, '球讯', '张三', '18903237688', '321286199001017567', '300.0000', '300.0000', null, '2017-09-25', null, '30.0000', '9000.0000', '8000.0000', '中石化', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '0', '0');
INSERT INTO `t_dispatch_ship` VALUES ('117', '97', null, '黎明号', '张大海', '15605122121', '320981199211254221', '150.0000', '100.0000', '24', '2017-09-24', null, '20.0000', '3000.0000', '200.0000', '中石化', '200.0000', '100.0000', '100.0000', null, '大号', '吨', '2017-09-24', null, '50.0000', '50.0000', '2017-09-18', '2', null, null, '0', null, '0', '0');
INSERT INTO `t_dispatch_ship` VALUES ('118', '98', null, '马林号', '马林', '15605122124', '320981199211254221', '50.0000', '50.0000', '12', '2017-09-24', null, '20.0000', '2000.0000', '600.0000', '200', '200.0000', '100.0000', '100.0000', null, '大号', '吨', '2017-09-18', null, '150.0000', '150.0000', '2017-09-18', '2', null, null, '0', null, '0', '0');
INSERT INTO `t_dispatch_ship` VALUES ('121', '99', null, '蛟龙号', '张大海', '15605122124', '320981199211234775', '150.0000', '3000.0000', '29', '2017-09-28', null, '50.0000', '7500.0000', '666.0000', '中石化', '200.0000', '333.0000', '777.0000', '666.0000', '包', '吨', '2017-09-30', null, '300.0000', '288.0000', '2017-09-30', '1', null, '666', '0', null, '0', '0');
INSERT INTO `t_dispatch_ship` VALUES ('123', '100', null, '海航号1', '张大海1', '15605221211', '320981199211234775', '300.0000', '300.0000', '28', '2017-09-28', null, '33.0000', '9900.0000', '2000.0000', '中石化', '200.0000', '1113.0000', '555.0000', '666.0000', '包', '吨', '2017-09-30', null, '150.0000', '144.0000', '2017-09-30', '0', null, '666', '0', null, '0', '0');
INSERT INTO `t_dispatch_ship` VALUES ('141', '108', null, '苏无锡货02390', '姚汉新', '13921338181', '320223196709086776', '500.0000', '520.0000', null, '2017-09-26', null, '19.0000', '9500.0000', '2000.0000', '江阴大桥', '3000.0000', null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '0', '0');
INSERT INTO `t_dispatch_ship` VALUES ('142', '108', null, '苏无锡货02391', '姚伟高', '13921339988', '320223197909076887', '1488.0000', '500.0000', '12', '2017-09-29', '2017-09-27', '18.0000', '26784.0000', '2000.0000', '南通天生港', '5000.0000', '111.0000', '555.0000', null, '散装', '吨', '2017-09-26', null, '200.0000', null, null, null, null, null, '0', null, '1', '1');
INSERT INTO `t_dispatch_ship` VALUES ('146', '107', null, '苏无锡货02001', '张永丰', '13706779899', '320282196609068998', '500.0000', '500.0000', '33', '2017-09-30', '2017-09-29', '18.0000', '9000.0000', null, null, null, '3000.0000', '2000.0000', null, '散装', '吨', '2017-09-28', null, '200.0000', null, null, null, null, null, '0', null, '0', '0');
INSERT INTO `t_dispatch_ship` VALUES ('147', '107', null, '苏无锡货023002', '张明明', '13706783389', '320121198807063267', '380.0000', '520.0000', null, '2017-09-28', null, '18.0000', '6840.0000', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '0', '0');
INSERT INTO `t_dispatch_ship` VALUES ('148', '107', null, '苏无锡货023003', '李明明', '13709897689', '330292196608090778', '2120.0000', '2500.0000', null, '2017-09-30', null, '18.0000', '38160.0000', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '0', '0');
INSERT INTO `t_dispatch_ship` VALUES ('149', '109', null, '中海999', '陈彪', '13809997893', '330292196707098998', '1000.0000', '1200.0000', '22', '2017-09-26', '2017-09-26', '20.0000', '20000.0000', '1000.0000', '南通天生港', '5000.0000', '88.0000', '88.0000', null, '散装', '吨', '2017-09-27', '2017-09-26', '999.9900', '999.0000', '2017-09-28', '4', null, null, '0', null, '1', '1');
INSERT INTO `t_dispatch_ship` VALUES ('152', '111', null, '明远号3', '李想', '15605122124', '320981199211234775', '1000.0000', '500.0000', '66', '2017-09-26', '2017-09-26', '50.0000', '50000.0000', '500.0000', '陆地加油', '600.0000', null, null, null, '大号', '吨', '2017-09-26', '2017-09-26', '1000.0000', '456.0000', '2017-09-26', '8', null, null, '0', '22800.0000', '0', '0');
INSERT INTO `t_dispatch_ship` VALUES ('153', '110', null, '张珊号', '张三', '13011111111', '320619990101333', '1000.0000', '1000.0000', '33', '2017-09-26', '2017-09-26', '60.0000', '60000.0000', '5000.0000', '陆地加油', '200.0000', null, null, '600.0000', '大', '吨', '2017-09-26', '2017-09-26', '1000.0000', '800.0000', '2017-09-26', '2', null, null, '0', '48000.0000', '0', '0');
INSERT INTO `t_dispatch_ship` VALUES ('154', '133', null, '阿迪', '爱迪生', '15651122212', '320981199211254221', '1654.0000', '2000.0000', '6', '2017-09-19', '2017-09-13', '20.0000', '33080.0000', '300.0000', '陆地加油', '600.0000', null, null, null, '大号', '吨', '2017-09-13', '2017-09-19', '1654.0000', '1600.0000', '2017-09-19', '2', null, null, '0', '32000.0000', '0', '0');
INSERT INTO `t_dispatch_ship` VALUES ('155', '134', null, '将现场', '阿萨德', '15605122124', '320981199211254221', '346.0000', '300.0000', '6', '2017-09-26', '2017-09-19', '20.0000', '6920.0000', '500.0000', '陆地加油', '600.0000', null, null, null, '大号', '吨', '2017-09-25', '2017-09-19', '346.0000', '300.0000', '2017-09-27', '2', null, null, '0', '6000.0000', '0', '0');
INSERT INTO `t_dispatch_ship` VALUES ('163', '94', null, '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', null, '2017-09-26', null, '22.0000', '4400.0000', '3000.0000', '南京上元门', '3000.0000', null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '0', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_insidejob_contract
-- ----------------------------
INSERT INTO `t_insidejob_contract` VALUES ('1', '华城运输有限公司', '123', '烟台', 'A公司', '22', '500.0000', '新建文本文档.txt');

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
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '计划管理', 'fa-clipboard', '', '0', '');
INSERT INTO `t_menu` VALUES ('2', '调度管理', 'fa-pencil-square-o', '', '0', '');
INSERT INTO `t_menu` VALUES ('3', '内勤管理', 'fa-comments-o', '', '0', '');
INSERT INTO `t_menu` VALUES ('4', '现场管理', 'fa-ship', '', '0', '');
INSERT INTO `t_menu` VALUES ('5', '运费结算', 'fa-pencil', '', '0', '');
INSERT INTO `t_menu` VALUES ('6', '结算批改申请', 'fa-table', '', '0', '');
INSERT INTO `t_menu` VALUES ('7', '结算审核管理', 'fa-check-square-o', '', '0', '');
INSERT INTO `t_menu` VALUES ('9', '创建计划', '', '/planManage/dispatch', '1', '');
INSERT INTO `t_menu` VALUES ('10', '待配流向', '', '/planManage/handover', '2', '');
INSERT INTO `t_menu` VALUES ('11', '已配流向', '', '/planManage/handoverfinish', '2', '');
INSERT INTO `t_menu` VALUES ('14', '计划调度信息', '', '/insidejob/plan', '3', '');
INSERT INTO `t_menu` VALUES ('15', '调度交接信息', '', '/site/handoversite', '4', '');
INSERT INTO `t_menu` VALUES ('19', '回单录入', '', '/site/receipt', '5', '');
INSERT INTO `t_menu` VALUES ('20', '待结算审核表', '', '/settle/managesettle', '7', '');
INSERT INTO `t_menu` VALUES ('26', '结算批改申请', '', '/settlecorrect/app', '6', '');
INSERT INTO `t_menu` VALUES ('28', '船舶信息管理', '', '/dataBase/ship', '42', '');
INSERT INTO `t_menu` VALUES ('29', '客户信息管理', '', '/dataBase/customer', '42', '');
INSERT INTO `t_menu` VALUES ('30', '港口信息管理', '', '/database/port', '42', '');
INSERT INTO `t_menu` VALUES ('31', '货物基础数据', '', '/database/goods', '42', '');
INSERT INTO `t_menu` VALUES ('32', '船舶结算管理', 'fa-anchor', '', '0', null);
INSERT INTO `t_menu` VALUES ('33', '待结算', '', '/settle/ship', '32', null);
INSERT INTO `t_menu` VALUES ('34', '已结算', '', '/settle/shipsettled', '32', '');
INSERT INTO `t_menu` VALUES ('35', '客户结算管理', 'fa-envelope-o', '', '0', '');
INSERT INTO `t_menu` VALUES ('36', '待结算', '', '/settle/customerfreight', '35', '');
INSERT INTO `t_menu` VALUES ('37', '已结算', '', '/settle/customersettled', '35', '');
INSERT INTO `t_menu` VALUES ('40', '统计管理', 'fa-bar-chart', '', '0', '');
INSERT INTO `t_menu` VALUES ('41', '合同管理', 'fa-file-text-o', '', '0', '');
INSERT INTO `t_menu` VALUES ('42', '基础数据', 'fa-database', '', '0', '');
INSERT INTO `t_menu` VALUES ('57', '结算审核表', '', '/planManage/waitsettle', '7', '');
INSERT INTO `t_menu` VALUES ('58', '现场信息录入', '', '/site/waterwayfreight', '4', '');
INSERT INTO `t_menu` VALUES ('61', '合同管理', '', '/insidejob/contract', '41', '');
INSERT INTO `t_menu` VALUES ('62', '销售统计', '', '/statistics/sales', '40', '');
INSERT INTO `t_menu` VALUES ('65', '已下发计划', '', '/planManage/issued', '1', '');
INSERT INTO `t_menu` VALUES ('80', '系统管理', 'fa-cogs', '', '0', '');
INSERT INTO `t_menu` VALUES ('81', '角色管理', '', '/system/role', '80', '');
INSERT INTO `t_menu` VALUES ('82', '账号管理', '', '/system/user', '80', '');
INSERT INTO `t_menu` VALUES ('83', '菜单管理', '', '/system/menu', '80', '');
INSERT INTO `t_menu` VALUES ('84', '按钮管理', '', '/system/button', '80', '');
INSERT INTO `t_menu` VALUES ('85', '权限管理', '', '/system/authority', '80', '');
INSERT INTO `t_menu` VALUES ('86', '基础数据', '', '/system/dictionary', '80', '');
INSERT INTO `t_menu` VALUES ('87', '通知公告', '', '/system/notice', '80', '');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL COMMENT '标题',
  `type` varchar(10) DEFAULT NULL COMMENT '类型',
  `content` varchar(420) DEFAULT NULL COMMENT '内容',
  `publisher` varchar(10) DEFAULT NULL COMMENT '发布者',
  `receiver` varchar(10) DEFAULT NULL COMMENT '接收者',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `url` varchar(255) DEFAULT NULL,
  `review` int(1) DEFAULT '0' COMMENT '1 已审核 0 未审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES ('1', '您有一条批改申请需要审核', '提醒', '', '', null, '2017-09-24 17:28:28', '/notice/ship/settle/10', '1');
INSERT INTO `t_notice` VALUES ('42', '您有一条批改申请需要审核', '提醒', null, null, null, '2017-09-24 17:36:19', '/notice/ship/settle/42', '1');
INSERT INTO `t_notice` VALUES ('43', '您有一条批改申请需要审核', '提醒', null, null, null, '2017-09-25 09:19:53', '/notice/ship/settle/13', '1');
INSERT INTO `t_notice` VALUES ('44', '永乐运输管理系统上线须知', '通知公告', '永乐运输管理系统上线须知', 'admin', null, '2017-09-25 13:05:37', null, '0');
INSERT INTO `t_notice` VALUES ('45', '国庆放假调休安排', '通知公告', '关于国庆放假调休安排', 'admin', null, '2017-09-25 13:04:57', null, '0');
INSERT INTO `t_notice` VALUES ('46', '您有一条批改申请需要审核', '提醒', null, null, null, '2017-09-25 10:59:52', '/notice/ship/settle/14', '1');
INSERT INTO `t_notice` VALUES ('48', '调度运价超出指导价，请确认。', '提醒', null, null, null, '2017-09-25 11:13:44', '/notice/ship/getRecord/48', '1');
INSERT INTO `t_notice` VALUES ('49', '调度运价超出指导价，请确认。', '提醒', null, null, null, '2017-09-25 14:07:14', '/notice/ship/getRecord/49', '1');
INSERT INTO `t_notice` VALUES ('50', '调度运价超出指导价，请确认。', '提醒', null, null, null, '2017-09-25 14:07:36', '/notice/ship/getRecord/50', '1');
INSERT INTO `t_notice` VALUES ('51', '调度运价超出指导价，请确认。', '提醒', null, null, null, '2017-09-25 14:07:50', '/notice/ship/getRecord/51', '1');
INSERT INTO `t_notice` VALUES ('52', '调度运价超出指导价，请确认。', '提醒', null, null, null, '2017-09-25 14:08:10', '/notice/ship/getRecord/52', '1');
INSERT INTO `t_notice` VALUES ('53', '调度运价超出指导价，请确认。', '提醒', null, null, null, '2017-09-25 14:08:28', '/notice/ship/getRecord/53', '1');
INSERT INTO `t_notice` VALUES ('54', '调度运价超出指导价，请确认。', '提醒', null, null, null, '2017-09-25 14:08:38', '/notice/ship/getRecord/54', '1');
INSERT INTO `t_notice` VALUES ('55', '您有一条批改申请需要审核', '提醒', null, null, null, '2017-09-25 14:08:35', '/notice/ship/settle/15', '1');
INSERT INTO `t_notice` VALUES ('56', '调度运价超出指导价，请确认。', '提醒', null, null, null, '2017-09-25 14:10:03', '/notice/ship/getRecord/56', '1');
INSERT INTO `t_notice` VALUES ('57', '调度运价超出指导价，请确认。', '提醒', null, null, null, '2017-09-25 14:10:19', '/notice/ship/getRecord/57', '1');
INSERT INTO `t_notice` VALUES ('58', '调度运价超出指导价，请确认。', '提醒', null, null, null, '2017-09-25 14:20:48', '/notice/ship/getRecord/58', '1');
INSERT INTO `t_notice` VALUES ('59', '调度运价超出指导价，请确认。', '提醒', null, null, null, '2017-09-25 15:28:26', '/notice/ship/getRecord/59', '1');
INSERT INTO `t_notice` VALUES ('60', '您有一条批改申请需要审核', '提醒', null, null, null, '2017-09-25 16:12:49', '/notice/ship/settle/16', '1');
INSERT INTO `t_notice` VALUES ('61', '您有一条批改申请需要审核', '提醒', null, null, null, '2017-09-25 16:14:59', '/notice/ship/settle/17', '1');
INSERT INTO `t_notice` VALUES ('62', '您有一条批改申请需要审核', '提醒', null, null, null, '2017-09-25 16:15:54', '/notice/ship/settle/18', '1');
INSERT INTO `t_notice` VALUES ('68', '您有一条批改申请需要审核', '提醒', null, 'admin', null, '2017-09-25 18:25:43', '/notice/ship/settle/24', '1');
INSERT INTO `t_notice` VALUES ('74', '运单需要预付款，请确认。', '提醒', null, null, null, '2017-09-26 17:25:42', '/notice/ship/getWaybill/74', '1');
INSERT INTO `t_notice` VALUES ('75', '运单需要预付款，请确认。', '提醒', null, null, null, '2017-09-26 17:52:07', '/notice/ship/getWaybill/75', '1');
INSERT INTO `t_notice` VALUES ('76', '运单需要预付款，请确认。', '提醒', null, null, null, '2017-09-26 17:52:51', '/notice/ship/getWaybill/76', '1');
INSERT INTO `t_notice` VALUES ('77', '运单需要预付款，请确认。', '提醒', null, null, null, '2017-09-26 17:56:56', '/notice/ship/getWaybill/77', '1');
INSERT INTO `t_notice` VALUES ('78', '运单需要预付款，请确认。', '提醒', null, null, null, '2017-09-27 08:08:21', '/notice/ship/getWaybill/78', '0');
INSERT INTO `t_notice` VALUES ('79', '运单需要预付款，请确认。', '提醒', null, null, null, '2017-09-27 08:10:14', '/notice/ship/getWaybill/79', '0');
INSERT INTO `t_notice` VALUES ('80', '运单需要预付款，请确认。', '提醒', null, null, null, '2017-09-27 08:13:40', '/notice/ship/getWaybill/80', '1');
INSERT INTO `t_notice` VALUES ('81', '运单需要预付款，请确认。', '提醒', null, null, null, '2017-09-27 08:43:46', '/notice/ship/getWaybill/81', '1');
INSERT INTO `t_notice` VALUES ('88', '调度运价超出指导价，请确认。', '提醒', null, null, null, '2017-09-27 09:03:05', '/notice/ship/getRecord/88', '0');

-- ----------------------------
-- Table structure for t_notice_ship
-- ----------------------------
DROP TABLE IF EXISTS `t_notice_ship`;
CREATE TABLE `t_notice_ship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_id` int(11) DEFAULT NULL,
  `dispatch_detail_id` int(11) DEFAULT NULL COMMENT '流向 id',
  `dispatch_ship_id` int(11) DEFAULT NULL,
  `ship_name` varchar(20) DEFAULT NULL COMMENT '船名',
  `ship_owner_name` varchar(20) DEFAULT NULL COMMENT '船主姓名',
  `ship_owner_phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `id_card_no` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `loading_tonnage` decimal(10,4) DEFAULT NULL COMMENT '配载吨位',
  `available_tonnage` decimal(10,4) DEFAULT NULL COMMENT '可载吨位',
  `arrival_date` date DEFAULT NULL COMMENT '到港日期',
  `freight_price` decimal(10,4) DEFAULT NULL COMMENT '运价',
  `total_freight` decimal(15,4) DEFAULT NULL COMMENT '总价',
  `prepay` decimal(15,4) DEFAULT NULL COMMENT '预付款',
  `refuel_type` varchar(20) DEFAULT '' COMMENT '加油方式',
  `pre_refuel` decimal(15,4) DEFAULT NULL COMMENT '预加油',
  `left_amount` decimal(15,4) DEFAULT NULL COMMENT '剩余费用',
  `dispatch_ship_remark` varchar(100) DEFAULT NULL,
  `over_guide_price` int(1) DEFAULT '0' COMMENT '1 运价超出指导价 0 未超过或审批通过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice_ship
-- ----------------------------
INSERT INTO `t_notice_ship` VALUES ('1', '47', '99', '119', '蛟龙号', '张大海', '15605122124', '320981199211234775', '150.0000', '3000.0000', '2017-09-28', '50.0000', '7500.0000', '666.0000', '中石化', '200.0000', '666.0000', '666', '1');
INSERT INTO `t_notice_ship` VALUES ('2', '48', '99', '121', '蛟龙号', '张大海', '15605122124', '320981199211234775', '150.0000', '3000.0000', '2017-09-28', '50.0000', '7500.0000', '666.0000', '中石化', '200.0000', '666.0000', '666', '1');
INSERT INTO `t_notice_ship` VALUES ('3', '49', '94', '125', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-25', '32.0000', '6400.0000', null, null, null, null, null, '1');
INSERT INTO `t_notice_ship` VALUES ('4', '50', '94', '126', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-25', '32.0000', '6400.0000', null, null, null, null, null, '1');
INSERT INTO `t_notice_ship` VALUES ('5', '51', '94', '127', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-25', '32.0000', '6400.0000', null, null, null, null, null, '1');
INSERT INTO `t_notice_ship` VALUES ('6', '52', '94', '128', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-25', '32.0000', '6400.0000', null, null, null, null, null, '1');
INSERT INTO `t_notice_ship` VALUES ('7', '53', '94', '129', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-25', '32.0000', '6400.0000', null, null, null, null, null, '1');
INSERT INTO `t_notice_ship` VALUES ('8', '54', '94', '130', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-25', '32.0000', '6400.0000', null, null, null, null, null, '1');
INSERT INTO `t_notice_ship` VALUES ('9', '56', '94', '131', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-25', '32.0000', '6400.0000', null, null, null, null, null, '1');
INSERT INTO `t_notice_ship` VALUES ('10', '57', '94', '132', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-25', '32.0000', '6400.0000', null, null, null, null, null, '1');
INSERT INTO `t_notice_ship` VALUES ('11', '58', '94', '133', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-25', '32.0000', '6400.0000', null, null, null, null, null, '1');
INSERT INTO `t_notice_ship` VALUES ('12', '59', '94', '136', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-26', '44.0000', '8800.0000', '3000.0000', '南京上元门', '3000.0000', null, null, '1');
INSERT INTO `t_notice_ship` VALUES ('13', '82', '94', '156', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-26', '44.0000', '8800.0000', '3000.0000', '南京上元门', '3000.0000', null, null, '1');
INSERT INTO `t_notice_ship` VALUES ('14', '83', '94', '157', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-26', '44.0000', '8800.0000', '3000.0000', '南京上元门', '3000.0000', null, null, '1');
INSERT INTO `t_notice_ship` VALUES ('15', '84', '94', '158', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-26', '44.0000', '8800.0000', '3000.0000', '南京上元门', '3000.0000', null, null, '1');
INSERT INTO `t_notice_ship` VALUES ('16', '85', '94', '159', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-26', '44.0000', '8800.0000', '3000.0000', '南京上元门', '3000.0000', null, null, '1');
INSERT INTO `t_notice_ship` VALUES ('17', '86', '94', '160', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-26', '44.0000', '8800.0000', '3000.0000', '南京上元门', '3000.0000', null, null, '1');
INSERT INTO `t_notice_ship` VALUES ('18', '87', '94', '161', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-26', '44.0000', '8800.0000', '3000.0000', '南京上元门', '3000.0000', null, null, '1');
INSERT INTO `t_notice_ship` VALUES ('19', '88', '94', '162', '海陵号', '张志勇', '18932475447', '321283198512044310', '200.0000', '200.0000', '2017-09-26', '44.0000', '8800.0000', '3000.0000', '南京上元门', '3000.0000', null, null, '1');

-- ----------------------------
-- Table structure for t_notice_waybill
-- ----------------------------
DROP TABLE IF EXISTS `t_notice_waybill`;
CREATE TABLE `t_notice_waybill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_id` int(11) DEFAULT NULL COMMENT '提醒 id',
  `dispatch_ship_id` int(11) DEFAULT NULL COMMENT '船 id',
  `plan_no` varchar(20) DEFAULT NULL COMMENT '计划号',
  `flow` varchar(20) DEFAULT NULL COMMENT '流向',
  `ship_name` varchar(20) DEFAULT NULL COMMENT '船名',
  `ship_owner_name` varchar(20) DEFAULT NULL COMMENT '船主姓名',
  `ship_owner_phone` varchar(20) DEFAULT NULL COMMENT '船主电话',
  `pre_refuel` decimal(15,4) DEFAULT NULL COMMENT '调度 预加油',
  `refuel_type` varchar(20) DEFAULT NULL COMMENT '加油方式',
  `prepay` decimal(15,4) DEFAULT NULL COMMENT '现场 预付款',
  `site_refuel` decimal(15,4) DEFAULT NULL COMMENT '现场 预加油',
  `site_pay` decimal(15,4) DEFAULT NULL COMMENT '现场 预付款',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice_waybill
-- ----------------------------
INSERT INTO `t_notice_waybill` VALUES ('1', '74', null, '20170925004', '张家港', '中海999', '陈彪', '13809997893', '5000.0000', '南通天生港', '1000.0000', '88.0000', '88.0000');
INSERT INTO `t_notice_waybill` VALUES ('2', '75', null, '20170925004', '张家港', '中海999', '陈彪', '13809997893', '5000.0000', '南通天生港', '1000.0000', '88.0000', '88.0000');
INSERT INTO `t_notice_waybill` VALUES ('3', '76', null, '20170925004', '张家港', '中海999', '陈彪', '13809997893', '5000.0000', '南通天生港', '1000.0000', '88.0000', '88.0000');
INSERT INTO `t_notice_waybill` VALUES ('4', '77', null, '20170925004', '张家港', '中海999', '陈彪', '13809997893', '5000.0000', '南通天生港', '1000.0000', '88.0000', '88.0000');
INSERT INTO `t_notice_waybill` VALUES ('5', '78', '149', '20170925004', '张家港', '中海999', '陈彪', '13809997893', '5000.0000', '南通天生港', '1000.0000', '88.0000', '88.0000');
INSERT INTO `t_notice_waybill` VALUES ('6', '79', '146', '20170925003', '1', '苏无锡货02001', '张永丰', '13706779899', null, null, null, '3000.0000', '2000.0000');
INSERT INTO `t_notice_waybill` VALUES ('7', '80', '142', '20170925003', '2', '苏无锡货02391', '姚伟高', '13921339988', '5000.0000', '南通天生港', '2000.0000', '111.0000', '555.0000');
INSERT INTO `t_notice_waybill` VALUES ('8', '81', '149', '20170925004', '张家港', '中海999', '陈彪', '13809997893', '5000.0000', '南通天生港', '1000.0000', '88.0000', '88.0000');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_type` varchar(20) NOT NULL COMMENT '角色',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '超级管理员', '超级管理员');
INSERT INTO `t_role` VALUES ('2', '财务', '');

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
INSERT INTO `t_role_menu` VALUES ('1', '1', '0,1,2,3,4,5,6,7,9,10,11,14,15,19,20,26,28,29,30,31,32,33,34,35,36,37,40,41,42,57,58,61,62,65,80,81,82,83,84,85,86,87');
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
  `next_adjust_freight` decimal(15,0) DEFAULT NULL COMMENT '调整前运费',
  `applicationerId` int(2) DEFAULT NULL COMMENT '申请人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_settle_apply
-- ----------------------------
INSERT INTO `t_settle_apply` VALUES ('10', '2017-09-24', 'admin', '20170924001', '张明', '马林号', '马林', '15605122124', '玉米', '平安号', '南京天安', '50.0000', '2000.0000', '订的', '1', 'deed', '1234', '2');
INSERT INTO `t_settle_apply` VALUES ('24', '2017-09-25', 'admin', '20170924001', '张明', '马林号', '马林', '15605122124', '玉米', '平安号', '南京', '50.0000', '1234.0000', '1324564', '1', null, '2000', '2');

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
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ship_settle
-- ----------------------------
INSERT INTO `t_ship_settle` VALUES ('57', '154', '54.0000', '4.1350', '49.8650', null, '957.4080', null, null, null, null, null);
INSERT INTO `t_ship_settle` VALUES ('58', '155', '46.0000', '0.8650', '45.1350', null, '866.5920', null, null, null, null, null);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_statement
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '1', 'admin', '6697BFAAD9A17D787670B699A12BC9DD3B8A544808CE6C73F1722693', 'admin', '1', '0');
INSERT INTO `t_user` VALUES ('2', '1', 'user', '6697BFAAD9A17D787670B699A12BC9DD3B8A544808CE6C73F1722693', 'user', '1', '0');
INSERT INTO `t_user` VALUES ('3', '2', 'accountant', '6ED478D83C30CE030445605F3E3A5FA45053B519B418A4D87B339661', 'accountant', '1', '0');

-- ----------------------------
-- View structure for v_customer_settle
-- ----------------------------
DROP VIEW IF EXISTS `v_customer_settle`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_customer_settle` AS select distinct `a`.`id` AS `ship_id`,`a`.`dispatch_detail_id` AS `dispatch_detail_id`,`a`.`dispatch_id` AS `dispatch_id`,`a`.`ship_name` AS `ship_name`,`a`.`ship_owner_name` AS `ship_owner_name`,`a`.`ship_owner_phone` AS `ship_owner_phone`,`a`.`id_card_no` AS `id_card_no`,`a`.`loading_tonnage` AS `loading_tonnage`,`a`.`available_tonnage` AS `available_tonnage`,`a`.`arrival_limit` AS `arrival_limit`,`a`.`arrival_date` AS `arrival_date`,`a`.`freight_price` AS `freight_price`,`a`.`total_freight` AS `total_freight`,`a`.`prepay` AS `prepay`,`a`.`refuel_type` AS `refuel_type`,`a`.`pre_refuel` AS `pre_refuel`,`a`.`site_refuel` AS `site_refuel`,`a`.`site_pay` AS `site_pay`,`a`.`left_amount` AS `left_amount`,`a`.`specifications` AS `specifications`,`a`.`unit` AS `unit`,`a`.`declare_date` AS `declare_date`,`a`.`delivery_quantity` AS `delivery_quantity`,`a`.`received_quantity` AS `received_quantity`,`a`.`unloaded_date` AS `unloaded_date`,`a`.`rainy_days` AS `rainy_days`,`a`.`waterway_remark` AS `waterway_remark`,`a`.`dispatch_ship_remark` AS `dispatch_ship_remark`,`a`.`over_guide_price` AS `over_guide_price`,`b`.`plan_no_id` AS `plan_no_id`,`b`.`flow` AS `flow`,`b`.`destination_port` AS `destination_port`,`b`.`consignee` AS `consignee`,`b`.`contants` AS `contants`,`b`.`phone` AS `phone`,`b`.`planned_qty` AS `planned_qty`,`b`.`left_qty` AS `left_qty`,`b`.`freight_rates` AS `freight_rates`,`b`.`dispatcher_entry_time` AS `dispatcher_entry_time`,`b`.`dispatcher` AS `dispatcher`,`b`.`flow_state` AS `flow_state`,`b`.`remark` AS `dispatch_detail_remark`,`c`.`plan_no` AS `plan_no`,`c`.`examine_state` AS `examine_state`,`c`.`goods_name` AS `goods_name`,`c`.`consignor` AS `consignor`,`c`.`delivery_dock` AS `delivery_dock`,`c`.`seagoing_vessel_name` AS `seagoing_vessel_name`,`c`.`plan_state` AS `plan_state`,`c`.`estimated_arrvial_date` AS `estimated_arrvial_date`,`c`.`transport_consumption` AS `transport_consumption`,`c`.`discharge_period` AS `discharge_period`,`c`.`Excess_deduction_price` AS `Excess_deduction_price`,`c`.`berthing_tonnage` AS `berthing_tonnage`,`c`.`total_quantity` AS `total_quantity`,`c`.`real_quantity` AS `real_quantity`,`c`.`remark` AS `dispatch_remark`,`c`.`entry_time` AS `entry_time`,`c`.`entry_man` AS `entry_man`,`c`.`billing` AS `billing`,`c`.`port_fee` AS `port_fee`,`c`.`insurance` AS `insurance`,`c`.`left_quantity` AS `left_quantity`,`c`.`site_dispatch` AS `site_dispatch`,`c`.`dispatch_settle_state` AS `dispatch_settle_state`,`c`.`site_settle_state` AS `site_settle_state`,`c`.`bursar_settle_state` AS `bursar_settle_state`,`c`.`manager_settle_state` AS `manager_settle_state`,`c`.`document_status` AS `document_status`,`d`.`dispatch_ship_id` AS `dispatch_ship_id`,`d`.`loss` AS `loss`,`d`.`fixed_loss` AS `fixed_loss`,`d`.`exceed_loss` AS `exceed_loss`,`d`.`port_construction_fee` AS `port_construction_fee`,`d`.`deduct_price` AS `deduct_price`,`d`.`deduct_money` AS `deduct_money`,`d`.`demurrage_days` AS `demurrage_days`,`d`.`extended_days` AS `extended_days`,`d`.`demurrage_charges` AS `demurrage_charges`,`d`.`other_charges` AS `other_charges`,`d`.`payable_amount` AS `payable_amount`,`a`.`id` AS `id`,`b`.`settle_price` AS `settle_price`,`b`.`guide_price` AS `guide_price` from (`t_customer_settle` `d` left join ((`t_dispatch_ship` `a` left join `t_dispatch_detail` `b` on((`a`.`dispatch_detail_id` = `b`.`id`))) left join `t_dispatch` `c` on((`b`.`plan_no_id` = `c`.`id`))) on((`d`.`dispatch_ship_id` = `a`.`id`))) ;

-- ----------------------------
-- View structure for v_ship_settle
-- ----------------------------
DROP VIEW IF EXISTS `v_ship_settle`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_ship_settle` AS select distinct `a`.`id` AS `ship_id`,`a`.`dispatch_detail_id` AS `dispatch_detail_id`,`a`.`dispatch_id` AS `dispatch_id`,`a`.`ship_name` AS `ship_name`,`a`.`ship_owner_name` AS `ship_owner_name`,`a`.`ship_owner_phone` AS `ship_owner_phone`,`a`.`id_card_no` AS `id_card_no`,`a`.`loading_tonnage` AS `loading_tonnage`,`a`.`available_tonnage` AS `available_tonnage`,`a`.`arrival_limit` AS `arrival_limit`,`a`.`arrival_date` AS `arrival_date`,`a`.`freight_price` AS `freight_price`,`a`.`total_freight` AS `total_freight`,`a`.`prepay` AS `prepay`,`a`.`refuel_type` AS `refuel_type`,`a`.`pre_refuel` AS `pre_refuel`,`a`.`site_refuel` AS `site_refuel`,`a`.`site_pay` AS `site_pay`,`a`.`left_amount` AS `left_amount`,`a`.`specifications` AS `specifications`,`a`.`unit` AS `unit`,`a`.`declare_date` AS `declare_date`,`a`.`delivery_quantity` AS `delivery_quantity`,`a`.`received_quantity` AS `received_quantity`,`a`.`unloaded_date` AS `unloaded_date`,`a`.`rainy_days` AS `rainy_days`,`a`.`waterway_remark` AS `waterway_remark`,`a`.`dispatch_ship_remark` AS `dispatch_ship_remark`,`a`.`over_guide_price` AS `over_guide_price`,`b`.`plan_no_id` AS `plan_no_id`,`b`.`flow` AS `flow`,`b`.`destination_port` AS `destination_port`,`b`.`consignee` AS `consignee`,`b`.`contants` AS `contants`,`b`.`phone` AS `phone`,`b`.`planned_qty` AS `planned_qty`,`b`.`left_qty` AS `left_qty`,`b`.`freight_rates` AS `freight_rates`,`b`.`dispatcher_entry_time` AS `dispatcher_entry_time`,`b`.`dispatcher` AS `dispatcher`,`b`.`flow_state` AS `flow_state`,`b`.`remark` AS `dispatch_detail_remark`,`c`.`plan_no` AS `plan_no`,`c`.`examine_state` AS `examine_state`,`c`.`goods_name` AS `goods_name`,`c`.`consignor` AS `consignor`,`c`.`delivery_dock` AS `delivery_dock`,`c`.`seagoing_vessel_name` AS `seagoing_vessel_name`,`c`.`plan_state` AS `plan_state`,`c`.`estimated_arrvial_date` AS `estimated_arrvial_date`,`c`.`transport_consumption` AS `transport_consumption`,`c`.`discharge_period` AS `discharge_period`,`c`.`Excess_deduction_price` AS `Excess_deduction_price`,`c`.`berthing_tonnage` AS `berthing_tonnage`,`c`.`total_quantity` AS `total_quantity`,`c`.`real_quantity` AS `real_quantity`,`c`.`remark` AS `dispatch_remark`,`c`.`entry_time` AS `entry_time`,`c`.`entry_man` AS `entry_man`,`c`.`billing` AS `billing`,`c`.`port_fee` AS `port_fee`,`c`.`insurance` AS `insurance`,`c`.`left_quantity` AS `left_quantity`,`c`.`site_dispatch` AS `site_dispatch`,`c`.`dispatch_settle_state` AS `dispatch_settle_state`,`c`.`site_settle_state` AS `site_settle_state`,`c`.`bursar_settle_state` AS `bursar_settle_state`,`c`.`manager_settle_state` AS `manager_settle_state`,`c`.`document_status` AS `document_status`,`d`.`dispatch_ship_id` AS `dispatch_ship_id`,`d`.`loss` AS `loss`,`d`.`fixed_loss` AS `fixed_loss`,`d`.`exceed_loss` AS `exceed_loss`,`d`.`port_construction_fee` AS `port_construction_fee`,`d`.`deduct_money` AS `deduct_money`,`d`.`demurrage_days` AS `demurrage_days`,`d`.`extended_days` AS `extended_days`,`d`.`demurrage_charges` AS `demurrage_charges`,`d`.`other_charges` AS `other_charges`,`d`.`payable_amount` AS `payable_amount`,`a`.`id` AS `id`,`b`.`settle_price` AS `settle_price`,`b`.`guide_price` AS `guide_price` from (`t_ship_settle` `d` left join ((`t_dispatch_ship` `a` left join `t_dispatch_detail` `b` on((`a`.`dispatch_detail_id` = `b`.`id`))) left join `t_dispatch` `c` on((`b`.`plan_no_id` = `c`.`id`))) on((('' = '') and (`d`.`dispatch_ship_id` = `a`.`id`)))) ;
