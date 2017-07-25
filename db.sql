/*
Navicat MySQL Data Transfer

Source Server         : 192.168.18.40_3306
Source Server Version : 50530
Source Host           : 192.168.18.40:3306
Source Database       : farmerhome_oa

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2017-07-25 18:10:55
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `sys_config`
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `config_name` varchar(1024) DEFAULT '' COMMENT '配置名称',
  `group_code` varchar(20) DEFAULT '' COMMENT '分组CODE',
  `ckey` varchar(128) NOT NULL COMMENT '配置英文标识',
  `cvalue` varchar(2048) DEFAULT '' COMMENT '配置的值',
  `remark` varchar(512) DEFAULT '' COMMENT '备注说明',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ckey` (`ckey`)
) ENGINE=MyISAM AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_config
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_config_group`
-- ----------------------------
DROP TABLE IF EXISTS `sys_config_group`;
CREATE TABLE `sys_config_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_code` varchar(20) NOT NULL,
  `app_code` varchar(30) DEFAULT '',
  `group_name` varchar(50) DEFAULT '' COMMENT '配置分组名称',
  `sort` int(11) DEFAULT NULL COMMENT '配置分组排序',
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_code` (`app_code`,`group_code`),
  KEY `app_code` (`app_code`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_config_group
-- ----------------------------
INSERT INTO `sys_config_group` VALUES ('1', 'XITONGPEIZHI', 'DCF_APP', '系统配置', '1');
INSERT INTO `sys_config_group` VALUES ('2', 'ALIPAYWAP', 'DCF_APP', '支付宝wap', '100');
INSERT INTO `sys_config_group` VALUES ('3', 'XITONGYOUJIAN', 'DCF_APP', '系统邮件', '60');
INSERT INTO `sys_config_group` VALUES ('4', 'TUPIANYASUO', 'DCF_APP', '图片压缩', '50');
INSERT INTO `sys_config_group` VALUES ('5', 'DINGDANPEIZHIZU', 'DCF_APP', '订单配置组', '70');
INSERT INTO `sys_config_group` VALUES ('6', 'XIAOXITUISONG', 'DCF_APP', '消息推送', '80');
INSERT INTO `sys_config_group` VALUES ('7', 'SMSSEND', 'DCF_APP', '短信发送', '90');
INSERT INTO `sys_config_group` VALUES ('8', 'KUAIDIPEIZHI', 'DCF_APP', '快递配置', '20');
INSERT INTO `sys_config_group` VALUES ('9', 'JIFENPEIZHI', 'DCF_APP', '积分配置', '30');
INSERT INTO `sys_config_group` VALUES ('10', 'pingxxZHIFUPINGTAI', 'DCF_APP', 'pingxx支付平台', '99');
INSERT INTO `sys_config_group` VALUES ('11', 'WEIXINWAP', 'DCF_APP', '微信wap', '101');
INSERT INTO `sys_config_group` VALUES ('12', 'WEIXIN', 'DCF_APP', '微信', '102');
INSERT INTO `sys_config_group` VALUES ('13', 'KUAJINGMAOYIJIEKOU', 'DCF_APP', '跨境贸易接口', '110');
INSERT INTO `sys_config_group` VALUES ('14', 'KUAIDI', 'DCF_APP', '快递', '0');

-- ----------------------------
-- Table structure for `t_consult`
-- ----------------------------
DROP TABLE IF EXISTS `t_consult`;
CREATE TABLE `t_consult` (
  `id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '查询号（生成规则yyyymmdd+4位有序数）',
  `consultant_name` varchar(125) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '咨询人姓名',
  `consultant_phone` varchar(125) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '咨询人手机号',
  `consultant_address` varchar(125) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '咨询人地址',
  `consultant_mail` varchar(125) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '咨询人邮箱',
  `consult_question` tinytext CHARACTER SET utf8mb4 COMMENT '咨询问题',
  `handle_user_id` bigint(20) DEFAULT NULL COMMENT '交办对象ID',
  `handle_status` int(2) DEFAULT '1' COMMENT '办理状态（1已受理，2正在办理，3已办理）',
  `handle_result` tinytext CHARACTER SET utf8mb4 COMMENT '处理结果',
  `handle_feedback` int(2) DEFAULT NULL COMMENT '咨询者反馈（1满意，2基本满意，3不满意）',
  `is_public` int(2) DEFAULT '0' COMMENT '是否公开（0公开，1不公开）',
  `current_user_id` bigint(20) DEFAULT NULL COMMENT '当前处理人ID',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除（0未删除，1已删除）',
  `by1` varchar(255) DEFAULT NULL,
  `by2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_consult
-- ----------------------------
INSERT INTO `t_consult` VALUES ('1', '李程', '18271624120', '特色', '840446169@qq.com', '快来救我', '22', '2', null, null, '0', null, '2017-07-08', '2017-07-08', '0', null, null);
INSERT INTO `t_consult` VALUES ('2', '李程', '18271624120', '色色色', '840446169@qq.com', '快来救我', '22', '3', '1111', null, '0', null, '2017-07-08', '2017-07-15', '0', null, null);
INSERT INTO `t_consult` VALUES ('201707141', 'yuyu', '15757166090', '123', '2636074560@qq.com', '这里我不清楚', '22', '3', 'yibanli', '1', '0', null, '2017-07-14', '2017-07-17', '0', null, null);
INSERT INTO `t_consult` VALUES ('201707151', '66666', '15757166090', '', '', '今天好热', '22', '3', 'yibanli', null, '0', null, '2017-07-15', '2017-07-15', '0', null, null);
INSERT INTO `t_consult` VALUES ('201707171', '李程', '18271624120', '', '', '哪里有玉堂金马', '22', '3', 'yibanli', '1', '0', null, '2017-07-17', '2017-07-17', '0', null, null);
INSERT INTO `t_consult` VALUES ('201707181', '879', '15757166090', '', '', '5555555', '22', '3', '111111111111', '1', '1', null, '2017-07-18', '2017-07-18', '0', null, null);
INSERT INTO `t_consult` VALUES ('201707182', '333333333', '15757166090', '', '', '11111111111', null, '1', null, null, '0', null, '2017-07-18', '2017-07-18', '0', null, null);
INSERT INTO `t_consult` VALUES ('3', 'yu', '15757166090', '111', '819391791@qq.com', '不清楚', '26', '3', 'yibanli', '1', '0', null, '2017-07-10', '2017-07-10', '0', null, null);

-- ----------------------------
-- Table structure for `t_department`
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `depart_name` varchar(125) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '部门名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除（0不删除，1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES ('2', '宣传部', '2017-05-24 16:36:40', '2017-05-24 16:36:40', '1');
INSERT INTO `t_department` VALUES ('3', '组织部', '2017-05-24 16:36:45', '2017-05-24 16:36:45', '0');
INSERT INTO `t_department` VALUES ('4', '后勤部', '2017-05-24 16:36:51', '2017-05-24 16:36:51', '0');
INSERT INTO `t_department` VALUES ('5', '保卫部', '2017-05-24 16:36:59', '2017-05-24 16:36:59', '0');
INSERT INTO `t_department` VALUES ('6', '情报部', '2017-05-24 16:37:04', '2017-05-24 16:37:04', '0');
INSERT INTO `t_department` VALUES ('7', '检查部', '2017-05-24 16:37:21', '2017-05-24 16:37:21', '0');
INSERT INTO `t_department` VALUES ('8', '卫生部', '2017-05-24 16:37:25', '2017-05-24 16:37:25', '0');
INSERT INTO `t_department` VALUES ('9', '文艺部', '2017-05-24 16:37:31', '2017-05-24 16:37:31', '0');
INSERT INTO `t_department` VALUES ('10', '文化部', '2017-05-24 16:37:38', '2017-05-24 16:37:38', '0');
INSERT INTO `t_department` VALUES ('11', '学习部', '2017-05-24 16:37:44', '2017-05-24 16:37:44', '0');
INSERT INTO `t_department` VALUES ('12', '老司机部', '2017-05-24 17:15:30', '2017-05-24 17:15:30', '0');
INSERT INTO `t_department` VALUES ('13', '120', '2017-06-26 09:38:17', '2017-06-26 09:38:23', '1');
INSERT INTO `t_department` VALUES ('14', '测试1', '2017-07-03 13:52:53', '2017-07-03 13:53:09', '1');
INSERT INTO `t_department` VALUES ('15', '测试123', '2017-07-03 14:49:32', '2017-07-03 14:49:40', '1');
INSERT INTO `t_department` VALUES ('16', '测试', '2017-07-14 10:59:39', '2017-07-14 10:59:55', '1');
INSERT INTO `t_department` VALUES ('17', '测试', '2017-07-14 11:00:05', '2017-07-14 11:00:05', '1');
INSERT INTO `t_department` VALUES ('18', '老司机部', '2017-07-14 11:00:15', '2017-07-14 11:00:24', '1');
INSERT INTO `t_department` VALUES ('19', 'test', '2017-07-17 09:56:56', '2017-07-17 09:56:56', '1');
INSERT INTO `t_department` VALUES ('20', 'test', '2017-07-17 09:57:09', '2017-07-17 09:57:09', '1');

-- ----------------------------
-- Table structure for `t_report_eaxm`
-- ----------------------------
DROP TABLE IF EXISTS `t_report_eaxm`;
CREATE TABLE `t_report_eaxm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '报表上传ID',
  `report_id` int(11) NOT NULL COMMENT '报表ID',
  `report_name` varchar(125) CHARACTER SET utf8mb4 NOT NULL COMMENT '报表名称',
  `report_type` int(11) NOT NULL COMMENT '报表类型（1财务报表，2服务报表）',
  `status` int(2) NOT NULL COMMENT '状态0未提交，1审核中（乡），2审核中（县），3成功，4未成功',
  `xiang_name` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '乡名称',
  `xiang_id` int(11) NOT NULL COMMENT '乡ID',
  `cun_name` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '村名称',
  `cun_id` int(11) NOT NULL COMMENT '村ID',
  `user_id` int(11) DEFAULT NULL COMMENT '上传用户ID',
  `username` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '上传用户姓名',
  `update_request` tinytext CHARACTER SET utf8mb4 COMMENT '修改要求',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_report_eaxm
-- ----------------------------
INSERT INTO `t_report_eaxm` VALUES ('8', '23', 'test', '1', '4', '莲花乡', '19', '二狗村', '20', '23', null, '12113', '2017-07-05', '2017-07-08');
INSERT INTO `t_report_eaxm` VALUES ('9', '24', '210', '1', '3', '莲花乡', '19', '二狗村', '20', '23', null, '11111', '2017-07-06', '2017-07-19');
INSERT INTO `t_report_eaxm` VALUES ('10', '21', '服务表', '2', '3', '莲花乡', '19', '二狗村', '20', '23', null, null, '2017-07-06', '2017-07-10');
INSERT INTO `t_report_eaxm` VALUES ('11', '25', '2', '1', '3', '莲花乡', '19', '二狗村', '20', '23', null, '111', '2017-07-06', '2017-07-10');
INSERT INTO `t_report_eaxm` VALUES ('12', '26', '2222', '1', '3', '莲花乡', '19', '二狗村', '20', '23', null, '111222', '2017-07-06', '2017-07-14');
INSERT INTO `t_report_eaxm` VALUES ('13', '22', '122', '2', '4', '莲花乡', '19', '二狗村', '20', '23', null, 'test', '2017-07-06', '2017-07-11');
INSERT INTO `t_report_eaxm` VALUES ('14', '27', '22222', '1', '4', '莲花乡', '19', '二狗村', '20', '23', null, '重审', '2017-07-06', '2017-07-10');
INSERT INTO `t_report_eaxm` VALUES ('15', '28', 'haole', '1', '4', '莲花乡', '19', '二狗村', '20', '23', null, 'haole1', '2017-07-10', '2017-07-10');
INSERT INTO `t_report_eaxm` VALUES ('16', '23', 'laolao', '2', '3', '莲花乡', '19', '二狗村', '20', '23', null, null, '2017-07-10', '2017-07-10');
INSERT INTO `t_report_eaxm` VALUES ('17', '24', '210', '2', '0', '莲花乡', '19', '二狗村', '20', '23', null, null, '2017-07-10', '2017-07-17');
INSERT INTO `t_report_eaxm` VALUES ('18', '29', '标题', '1', '4', '莲花乡', '19', '二狗村', '20', '23', null, '错误', '2017-07-14', '2017-07-17');
INSERT INTO `t_report_eaxm` VALUES ('19', '25', '11', '2', '4', '莲花乡', '19', '二狗村', '20', '23', null, 'gaiyigai1', '2017-07-14', '2017-07-14');

-- ----------------------------
-- Table structure for `t_report_finance`
-- ----------------------------
DROP TABLE IF EXISTS `t_report_finance`;
CREATE TABLE `t_report_finance` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '财务报表ID',
  `title` varchar(125) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '报表名',
  `hbzj_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '货币资金年初数',
  `hbzj_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '货币资金期末数',
  `dqtz_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '短期投资年初数',
  `dqtz_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '短期投资期末数',
  `yskx_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '应收款项年初数',
  `yskx_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '应收款项期末数',
  `yfzk_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '预付账款年初数',
  `yfzk_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '预付账款期末数',
  `ch_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '存货年初数',
  `ch_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '存货期末数',
  `dtfy_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '待摊费用年初数',
  `dtfy_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '待摊费用期末数',
  `ynndqdcqzqtz_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '一年内到期的长期债权投资年初数',
  `ynndqdcqzqtz_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '一年内到期的长期债权投资期末数',
  `qtldzc_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '其他流动资产年初数',
  `qtldzc_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '其他流动资产期末数',
  `ldzchj_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '流动资产合计年初数',
  `ldzchj_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '流动资产合计期末数',
  `cqgqtz_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '长期股权投资年初数',
  `cqgqtz_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '长期股权投资期末数',
  `cqzqtz_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '长期债权投资年初数',
  `cqzqtz_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '长期债权投资期末数',
  `cqtzhj_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '长期投资合计年初数',
  `cqtzhj_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '长期投资合计期末数',
  `gdzcyj_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '固定资产原价年初数',
  `gdzcyj_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '固定资产原件期末数',
  `ljzj_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '减：累积折旧年初数',
  `ljzj_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '减：累积折旧期末数',
  `gdzcjz_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '固定资产净值年初数',
  `gdzcjz_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '固定资产净值期末数',
  `zjgc_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '在建工程年初数',
  `zjgc_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '在建工程期末数',
  `wwwhzc_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '文物文化资产年初数',
  `wwwhzc_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '文物文化资产期末数',
  `gdzcql_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '固定资产清理年初数',
  `gdzcql_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '固定资产清理期末数',
  `gdzchj_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '固定资产合计年初数',
  `gdzchj_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '固定资产合计期末数',
  `wxzc_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '无形资产年初数',
  `wxzc_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '无形资产期末数',
  `stdlzc_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '受托代理资产年初数',
  `stdlzc_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '受托代理资产期末数',
  `zczj_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '资产总计年初数',
  `zczj_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '资产总计期末数',
  `dqjd_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '短期借款年初数',
  `dqjd_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '短期借款期末数',
  `yfkx_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '应付款项年初数',
  `yfkx_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '应付款项期末数',
  `yfgz_begin` varchar(30) DEFAULT '' COMMENT '应付工资年初数',
  `yfgz_end` varchar(30) DEFAULT '' COMMENT '应付工资年末数',
  `yjsj_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '应交税金年初数',
  `yjsj_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '应交税金期末数',
  `yszk_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '预收账款年初数',
  `yszk_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '预收账款期末数',
  `ytfy_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '预提费用年初数',
  `ytfy_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '预提费用期末数',
  `yjfz_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '预计负债年初数',
  `yjfz_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '预计负债期末数',
  `ynndqdcqfz_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '一年内到期的长期负债年初数',
  `ynndqdcqfz_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '一年内到期的长期负债期末数',
  `qtldfz_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '其他流动负债年初数',
  `qtldfz_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '其他流动负债期末数',
  `ldfzhj_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '流动负债合计年初数',
  `ldfzhj_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '流动负债合计期末数',
  `cqjd_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '长期借款年初数',
  `cqjd_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '长期借款期末数',
  `cqyfk_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '长期应付款年初数',
  `cqyfk_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '长期应付款期末数',
  `qtcqfz_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '其他长期负债年初数',
  `qtcqfz_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '其他长期负债期末数',
  `cqfzhj_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '长期负债合计年初数',
  `cqfzhj_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '长期负债合计期末数',
  `stdlfz_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '受托代理负债年初数',
  `stdlfz_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '受托代理负债期末数',
  `fzhj_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '负债合计年初数',
  `fzhj_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '负债合计期末数',
  `fxdxjzc_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '非限定性净资产年初数',
  `fxdxjzc_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '非限定性净资产期末数',
  `xdxjzc_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '限定性净资产年初数',
  `xdxjzc_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '限定性净资产期末数',
  `jzchj_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '净资产合计年初数',
  `jzchj_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '净资产合计期末数',
  `fzhjzchj_begin` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '负债和净资产合计年初数',
  `fzhjzchj_end` varchar(30) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '负债和净资产合计期末数',
  `create_time` date DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_report_finance
-- ----------------------------
INSERT INTO `t_report_finance` VALUES ('18', '财务', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '2017-07-04');
INSERT INTO `t_report_finance` VALUES ('19', '财务1', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '2017-07-04');
INSERT INTO `t_report_finance` VALUES ('20', '财务1', '1000', '100', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '100', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '2017-07-04');
INSERT INTO `t_report_finance` VALUES ('21', '财务1', '1000', '100', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '100', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '2017-07-04');
INSERT INTO `t_report_finance` VALUES ('22', '财务1', '1000', '100', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '100', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '1000', '2017-07-04');
INSERT INTO `t_report_finance` VALUES ('23', 'test', '22', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '11', '1', '11', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '79', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2017-07-05');
INSERT INTO `t_report_finance` VALUES ('24', '210', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '121', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2017-07-06');
INSERT INTO `t_report_finance` VALUES ('25', '2', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '79', '11', '11', '11', '11', '11', '11', '11', '11', '11', '11', '2017-07-06');
INSERT INTO `t_report_finance` VALUES ('26', '2222', '1', '11', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '11', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '11', '1', '1', '1', '1', '1', '1', '79', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2017-07-06');
INSERT INTO `t_report_finance` VALUES ('27', '22222', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '79', '22', '22', '22', '22', '22', '22', '22', '22', '22', '22', '2017-07-06');
INSERT INTO `t_report_finance` VALUES ('28', 'haole', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2017-07-10');
INSERT INTO `t_report_finance` VALUES ('29', '标题', '2', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2017-07-14');

-- ----------------------------
-- Table structure for `t_report_manage`
-- ----------------------------
DROP TABLE IF EXISTS `t_report_manage`;
CREATE TABLE `t_report_manage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `xiang_id` int(11) DEFAULT NULL COMMENT '乡ID',
  `cun_id` int(11) DEFAULT NULL COMMENT '村ID',
  `username` varchar(255) DEFAULT '' COMMENT '用户名',
  `xiang_name` varchar(255) DEFAULT '' COMMENT '乡名',
  `cun_name` varchar(255) DEFAULT '' COMMENT '村名',
  `phone` varchar(255) DEFAULT '' COMMENT '手机号',
  `report_type` int(11) DEFAULT NULL COMMENT '报表类型（1财务报表，2服务报表）',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `is_upload` int(11) DEFAULT '1' COMMENT '是否上传（0已上传，1未上传，2逾期上传）',
  `upload_time` date DEFAULT NULL COMMENT '上传时间 ',
  `by1` varchar(255) DEFAULT NULL COMMENT '补充字段1',
  `by2` varchar(255) DEFAULT NULL COMMENT '补充字段2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=369 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_report_manage
-- ----------------------------

-- ----------------------------
-- Table structure for `t_report_server`
-- ----------------------------
DROP TABLE IF EXISTS `t_report_server`;
CREATE TABLE `t_report_server` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '服务报表ID',
  `title` varchar(120) DEFAULT NULL COMMENT '服务报表标题',
  `employment_consult_accpet` int(11) unsigned DEFAULT '0' COMMENT '就业服务咨询受理',
  `employment_consult_deal` int(11) DEFAULT '0' COMMENT '就业服务咨询办结',
  `employment_agent_accpet` int(11) DEFAULT '0' COMMENT '就业服务代办受理',
  `employment_agent_deal` int(11) DEFAULT '0' COMMENT '就业服务代办办结',
  `employment_others_accpet` int(11) DEFAULT '0' COMMENT '就业服务其他受理',
  `employment_others_deal` int(11) DEFAULT '0' COMMENT '就业服务其他办结',
  `employment_numbers` int(11) DEFAULT '0' COMMENT '就业服务服务人次',
  `employment_remark` varchar(255) DEFAULT '' COMMENT '就业服务备注',
  `business_consult_accpet` int(11) DEFAULT '0' COMMENT '创业融资咨询受理',
  `business_consult_deal` int(11) DEFAULT '0' COMMENT '创业融资咨询办结',
  `business_agent_accpet` int(11) DEFAULT '0' COMMENT '创业融资代办受理',
  `business_agent_deal` int(11) DEFAULT '0' COMMENT '创业融资代办办结',
  `business_others_accpet` int(11) DEFAULT '0' COMMENT '创业融资其他受理',
  `business_others_deal` int(11) DEFAULT '0' COMMENT '创业融资其他办结',
  `business_numbers` int(11) DEFAULT '0' COMMENT '创业融资服务人次',
  `business_remark` varchar(250) DEFAULT '' COMMENT '创业融资备注',
  `law_consult_accpet` int(11) DEFAULT '0' COMMENT '法律服务咨询受理',
  `law_consult_deal` int(11) DEFAULT '0' COMMENT '法律服务咨询办结',
  `law_agent_accpet` int(11) DEFAULT '0' COMMENT '法律服务代办受理',
  `law_agent_deal` int(11) DEFAULT '0' COMMENT '法律服务代办办结',
  `law_others_accpet` int(11) DEFAULT '0' COMMENT '法律服务其他受理',
  `law_others_deal` int(11) DEFAULT '0' COMMENT '法律服务其他办结',
  `law_numbers` int(11) DEFAULT '0' COMMENT '法律服务服务人次',
  `law_remark` varchar(250) DEFAULT '' COMMENT '法律服务备注',
  `policy_consult_accpet` int(11) DEFAULT '0' COMMENT '政策服务咨询受理',
  `policy_consult_deal` int(11) DEFAULT '0' COMMENT '政策服务咨询办结',
  `policy_agent_accpet` int(11) DEFAULT '0' COMMENT '政策服务代办受理',
  `policy_agent_deal` int(11) DEFAULT '0' COMMENT '政策服务代办办结',
  `policy_others_accpet` int(11) DEFAULT '0' COMMENT '政策服务其他受理',
  `policy_others_deal` int(11) DEFAULT '0' COMMENT '政策服务其他办结',
  `policy_numbers` int(11) DEFAULT '0' COMMENT '政策服务服务人次',
  `policy_remark` varchar(250) DEFAULT '' COMMENT '政策服务备注',
  `project_consult_accpet` int(11) DEFAULT '0' COMMENT '项目申办咨询受理',
  `project_consult_deal` int(11) DEFAULT '0' COMMENT '项目申办咨询办结',
  `project_agent_accpet` int(11) DEFAULT '0' COMMENT '项目申办代办受理',
  `project_agent_deal` int(11) DEFAULT '0' COMMENT '项目申办代办办结',
  `project_others_accpet` int(11) DEFAULT '0' COMMENT '项目申办其他受理',
  `project_others_deal` int(11) DEFAULT '0' COMMENT '项目申办其他办结',
  `project_numbers` int(11) DEFAULT '0' COMMENT '项目申办服务人次',
  `project_remark` varchar(250) DEFAULT '' COMMENT '项目申办备注',
  `professor_consult_accpet` int(11) DEFAULT '0' COMMENT '专家指导咨询受理',
  `professor_consult_deal` int(11) DEFAULT '0' COMMENT '专家指导咨询办结',
  `professor_agent_accpet` int(11) DEFAULT '0' COMMENT '专家指导代办受理',
  `professor_agent_deal` int(11) DEFAULT '0' COMMENT '专家指导代办办结',
  `professor_others_accpet` int(11) DEFAULT '0' COMMENT '专家指导其他受理',
  `professor_others_deal` int(11) DEFAULT '0' COMMENT '专家指导其他办结',
  `professor_numbers` int(11) DEFAULT '0' COMMENT '专家指导人次',
  `professor_remark` varchar(250) DEFAULT '' COMMENT '专家指导备注',
  `train_consult_accpet` int(11) DEFAULT '0' COMMENT '技能培训咨询受理',
  `train_consult_deal` int(11) DEFAULT '0' COMMENT '技能培训咨询办结',
  `train_agent_accpet` int(11) DEFAULT '0' COMMENT '技能培训代办受理',
  `train_agent_deal` int(11) DEFAULT '0' COMMENT '技能培训代办办结',
  `train_others_accpet` int(11) DEFAULT '0' COMMENT '技能培训其他受理',
  `train_others_deal` int(11) DEFAULT '0' COMMENT '技能培训其他办结',
  `train_numbers` int(11) DEFAULT '0' COMMENT '技能培训服务人次',
  `train_remark` varchar(250) DEFAULT '' COMMENT '技能培训备注',
  `insurance_consult_accpet` int(11) DEFAULT '0' COMMENT '保险业务咨询受理',
  `insurance_consult_deal` int(11) DEFAULT '0' COMMENT '保险业务咨询办结',
  `insurance_agent_accpet` int(11) DEFAULT '0' COMMENT '保险业务代办受理',
  `insurance_agent_deal` int(11) DEFAULT '0' COMMENT '保险业务代办办结',
  `insurance_others_accpet` int(11) DEFAULT '0' COMMENT '保险业务其他受理',
  `insurance_others_deal` int(11) DEFAULT '0' COMMENT '保险业务其他办结',
  `insurance_numbers` int(11) DEFAULT '0' COMMENT '保险业务服务人次',
  `insurance_remark` varchar(205) DEFAULT '' COMMENT '保险业务备注',
  `weather_consult_accpet` int(11) DEFAULT '0' COMMENT '气象信息咨询受理',
  `weather_consult_deal` int(11) DEFAULT '0' COMMENT '气象信息咨询办结',
  `weather_agent_accpet` int(11) DEFAULT '0' COMMENT '气象信息代办受理',
  `weather_agent_deal` int(11) DEFAULT '0' COMMENT '气象信息代办办结',
  `weather_others_accpet` int(11) DEFAULT '0' COMMENT '气象信息其他受理',
  `weather_others_deal` int(11) DEFAULT '0' COMMENT '气象信息其他办结',
  `weather_numbers` int(11) DEFAULT '0' COMMENT '气象信息服务人次',
  `weather_remark` varchar(250) DEFAULT '' COMMENT '气象信息备注',
  `supply_consult_accpet` int(11) DEFAULT '0' COMMENT '供需信息咨询受理',
  `supply_consult_deal` int(11) DEFAULT '0' COMMENT '供需信息咨询办结',
  `supply_agent_accpet` int(11) DEFAULT '0' COMMENT '供需信息代办受理',
  `supply_agent_deal` int(11) DEFAULT '0' COMMENT '供需信息代办办结',
  `supply_others_accpet` int(11) DEFAULT '0' COMMENT '供需信息其他受理',
  `supply_others_deal` int(11) DEFAULT '0' COMMENT '供需信息其他办结',
  `supply_numbers` int(11) DEFAULT '0' COMMENT '供需信息服务人次',
  `supply_remark` varchar(250) DEFAULT '' COMMENT '供需信息备注',
  `acount_consult_accpet` int(11) DEFAULT '0' COMMENT '累积咨询受理',
  `acount_consult_deal` int(11) DEFAULT '0' COMMENT '累积咨询办结',
  `acount_agent_accpet` int(11) DEFAULT '0' COMMENT '累积代办受理',
  `acount_agent_deal` int(11) DEFAULT '0' COMMENT '累积代办办结',
  `acount_others_accpet` int(11) DEFAULT '0' COMMENT '累积其他受理',
  `acount_others_deal` int(11) DEFAULT '0' COMMENT '累积其他办结',
  `acount_numbers` int(11) DEFAULT '0' COMMENT '累积服务人次',
  `acount_remark` varchar(250) DEFAULT '' COMMENT '累积备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_report_server
-- ----------------------------
INSERT INTO `t_report_server` VALUES ('20', '服务', '1', '1', '1', '1', '1', '1', '1', '完结', '1', '1', '1', '1', '1', '1', '1', '完结', '1', '1', '1', '1', '1', '1', '1', '完结', '1', '1', '1', '1', '1', '1', '1', '完结', '1', '1', '1', '1', '1', '1', '1', '完结', '1', '1', '1', '1', '1', '1', '1', '完结', '1', '1', '1', '1', '1', '1', '1', '完结', '1', '1', '1', '1', '1', '1', '1', '完结', '1', '1', '1', '1', '1', '1', '1', '完结', '1', '1', '1', '1', '1', '1', '1', '完结', '1', '1', '1', '1', '1', '1', '1', '完结');
INSERT INTO `t_report_server` VALUES ('21', '服务表', '1', '1', '1', '1', '1', '1', '1', '好', '1', '1', '1', '1', '1', '1', '1', '好', '1', '1', '1', '1', '1', '1', '1', '好', '1', '1', '1', '1', '1', '1', '1', '好', '1', '1', '1', '1', '1', '1', '1', '好', '1', '1', '1', '1', '1', '1', '1', '好', '1', '1', '1', '1', '1', '1', '1', '好', '1', '1', '1', '1', '1', '1', '1', '好', '1', '1', '1', '1', '1', '1', '1', '好', '1', '1', '1', '1', '1', '1', '1', '好', '1', '1', '1', '1', '1', '1', '1', '好');
INSERT INTO `t_report_server` VALUES ('22', '122', '20', '2', '2', '2', '2', '2', '2', '无', '2', '2', '2', '2', '2', '2', '2', '无', '220', '2', '2', '2', '2', '2', '222', '无', '22', '22', '22', '22', '22', '22', '123456', 'haha', '10', '20', '2', '2', '2', '2', '10', '无', '2', '2', '2', '2', '2', '2', '2', '无', '2', '2', '2', '2', '2', '2', '2', '无', '2', '2', '2', '2', '2', '2', '2', '无', '2', '2', '2', '2', '2', '2', '2', '无', '2', '2', '2', '2', '2', '2', '2', '无', '2', '2', '2', '2', '2', '2', '2', '无');
INSERT INTO `t_report_server` VALUES ('23', 'laolao', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `t_report_server` VALUES ('24', '210', '20', '5', '10', '5', '10', '5', '2', '无', '2', '2', '2', '2', '2', '2', '2', '无', '2', '2', '2', '2', '2', '2', '1', '无', '2', '2', '2', '2', '2', '2', '1', '2', '2', '20', '2', '2', '2', '2', '1', '无', '2', '2', '2', '2', '2', '2', '1', '无', '2', '2', '2', '2', '2', '2', '3', '无', '2', '2', '2', '2', '2', '2', '2', '无', '2', '2', '2', '2', '2', '2', '2', '无', '2', '2', '2', '2', '2', '2', '2', '无', '2', '2', '2', '2', '2', '2', '2', '无');
INSERT INTO `t_report_server` VALUES ('25', '11', '1', '1', '1', '1', '1', '1', '1', '无', '1', '1', '1', '1', '1', '1', '1', '无', '1', '1', '1', '1', '1', '1', '1', '无', '1', '1', '1', '1', '1', '1', '1', '无', '1', '1', '1', '1', '1', '1', '1', '无', '1', '1', '1', '1', '1', '1', '1', '无', '1', '1', '1', '1', '1', '1', '1', '无', '1', '1', '1', '1', '1', '1', '1', '无', '1', '1', '1', '1', '1', '1', '1', '无', '1', '1', '1', '1', '1', '1', '1', '无', '1', '1', '1', '1', '1', '1', '1', '无');

-- ----------------------------
-- Table structure for `t_report_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_report_user`;
CREATE TABLE `t_report_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '上传报表用户ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `username` varchar(255) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '用户名称',
  `user_phone` varchar(255) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '用户手机号',
  `village_id` bigint(20) DEFAULT NULL COMMENT '乡村ID',
  `village_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '乡村名',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除（0未删除，1已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_report_user
-- ----------------------------

-- ----------------------------
-- Table structure for `t_res`
-- ----------------------------
DROP TABLE IF EXISTS `t_res`;
CREATE TABLE `t_res` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) DEFAULT '' COMMENT '资源名称',
  `symbol` varchar(64) DEFAULT '' COMMENT '资源标识',
  `url` varchar(512) DEFAULT '' COMMENT '菜单(资源)路径',
  `icon` varchar(512) DEFAULT '' COMMENT '菜单小图标地址',
  `parent_id` int(20) NOT NULL DEFAULT '0',
  `menu_order` int(20) DEFAULT NULL,
  `is_usable` varchar(10) DEFAULT '1' COMMENT '是否可用,1：可用，0不可用',
  `remark` varchar(512) DEFAULT '' COMMENT '资源备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=84243 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_res
-- ----------------------------
INSERT INTO `t_res` VALUES ('1', '系统管理', 'system', '', '&#xe632;', '0', null, '1', '', '2017-03-31 17:33:00');
INSERT INTO `t_res` VALUES ('2', '用户管理', 'jiao', '../system/roleManage.html', '&#xe699;', '1', null, '1', '', null);
INSERT INTO `t_res` VALUES ('3', '部门管理', 'zhao', '../system/department.html', '&#xe63d;', '1', null, '1', '', null);
INSERT INTO `t_res` VALUES ('4', '乡村管理', 'xiang', '../system/countyManage.html', '&#xe653;', '1', null, '1', '', null);
INSERT INTO `t_res` VALUES ('5', '档案管理', 'dang', '', '&#xe61c;', '0', null, '1', '', null);
INSERT INTO `t_res` VALUES ('6', '农户列表', 'lie', '../record/farmerList.html', '&#xe6db;', '5', null, '1', '', null);
INSERT INTO `t_res` VALUES ('7', '农户统计', 'tong', '../record/farmerStastistics.html', '&#xe6c9;', '5', null, '1', '', null);
INSERT INTO `t_res` VALUES ('8', '事项', 'chan ', '', '&#xe608;', '0', null, '1', '', null);
INSERT INTO `t_res` VALUES ('9', '事项列表', 'shi', '../matter/matterList.html', '&#xe617;', '8', null, '1', '', null);
INSERT INTO `t_res` VALUES ('10', '文件', 'wen', '', '&#xe61f;', '0', null, '1', '', null);
INSERT INTO `t_res` VALUES ('13', '报表', 'bao', '', '&#xe7d5;', '0', null, '1', '', null);
INSERT INTO `t_res` VALUES ('11', '已收文件', 'yi', '../documents/receivedFile.html', '&#xe806;', '10', null, '1', '', null);
INSERT INTO `t_res` VALUES ('12', '已发文件', 'fa', '../documents/sendFile.html', '&#xe655;', '10', null, '1', '', null);
INSERT INTO `t_res` VALUES ('14', '报表管理', 'baoManage', '../statement/statementManage.html', '&#xe61d;', '13', null, '1', '', null);
INSERT INTO `t_res` VALUES ('15', '报表审核', 'shen', '../statement/statementCheck.html', '&#xe639;', '13', null, '1', '', null);
INSERT INTO `t_res` VALUES ('16', '报表存档', 'cun', '../statement/statementKeep.html', '&#xe656;', '13', null, '1', '', null);
INSERT INTO `t_res` VALUES ('17', '服务报表统计', 'futong', '../statement/serviceStatistic.html', '&#xe8b3;', '13', null, '1', '', null);
INSERT INTO `t_res` VALUES ('19', '接受用户短信列表', 'jie', '../statement/noteList.html', '&#xe647;', '13', null, '1', '', null);
INSERT INTO `t_res` VALUES ('20', '未上报管理', 'wei', '../statement/disBaoManage.html', '&#xe679;', '13', null, '1', '', null);

-- ----------------------------
-- Table structure for `t_resident`
-- ----------------------------
DROP TABLE IF EXISTS `t_resident`;
CREATE TABLE `t_resident` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '农户ID',
  `resident_booklet_id` int(20) DEFAULT NULL COMMENT '所属户口簿ID',
  `name` varchar(255) DEFAULT '' COMMENT '农户姓名',
  `relation` varchar(20) DEFAULT '' COMMENT '与户主关系',
  `sex` int(11) DEFAULT NULL COMMENT '农户性别（1男，2女）',
  `native_place` varchar(20) DEFAULT '' COMMENT '籍贯',
  `nation` varchar(20) DEFAULT '' COMMENT '民族',
  `id_card` varchar(20) DEFAULT '' COMMENT '身份证号',
  `contact` varchar(20) DEFAULT '' COMMENT '联系方式',
  `marriage` int(11) DEFAULT '2' COMMENT '婚姻状态（1已婚，2未婚，默认2）',
  `education` int(11) DEFAULT NULL COMMENT '教育程度（1无文凭，2小学，3初中，4高中，5大专/高职，6本科，7研究生及以上）',
  `politics` int(11) DEFAULT NULL COMMENT '政治面貌（1群众，2共青团员，3预备党员，4党员）',
  `religion` varchar(20) DEFAULT '' COMMENT '宗教信仰',
  `speciality` varchar(50) DEFAULT '' COMMENT '专业特长',
  `certificate` varchar(125) DEFAULT '' COMMENT '持证情况',
  `slack_season` varchar(125) DEFAULT '' COMMENT '农闲时间节点',
  `disadvantaged` int(11) DEFAULT '2' COMMENT '是否弱势群体（1是，2不是，默认2）',
  `special` int(11) DEFAULT '2' COMMENT '是否特殊人群（1是，2不是，默认2）',
  `job_condition` varchar(255) DEFAULT '' COMMENT '就业状况（在何地，干什么）',
  `job_intention` varchar(125) DEFAULT NULL COMMENT '创业就业意向',
  `population_property` varchar(125) DEFAULT '常住人口' COMMENT '人口属性（按常住人口统计）',
  `xiang_id` bigint(20) DEFAULT NULL COMMENT '乡ID',
  `cun_id` bigint(20) DEFAULT NULL COMMENT '村ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除（0未删除，1已删除）',
  `by1` varchar(255) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '补充字段1',
  `by2` varchar(255) CHARACTER SET utf8mb4 DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resident
-- ----------------------------
INSERT INTO `t_resident` VALUES ('10', '1', '赵', '', '1', null, null, null, null, '2', '4', '1', null, null, null, null, '2', '2', null, null, null, '19', '20', '2017-07-03 15:53:39', '2017-07-03 15:53:39', '0', '', '');
INSERT INTO `t_resident` VALUES ('11', '1', '王', '', '2', null, null, null, null, '2', '3', '4', null, null, null, null, '2', '2', null, null, null, '19', '20', '2017-07-03 15:53:56', '2017-07-03 15:53:56', '0', '', '');
INSERT INTO `t_resident` VALUES ('12', '1', '李', '', '1', null, null, null, null, '2', '1', '3', null, null, null, null, '2', '2', null, null, null, '19', '21', '2017-07-03 16:07:08', '2017-07-04 11:31:32', '0', '', '');
INSERT INTO `t_resident` VALUES ('13', '2', '冬冬', '', '1', null, null, null, null, '2', '5', '3', null, null, null, null, '2', '2', null, null, null, '19', '21', '2017-07-03 16:07:21', '2017-07-03 16:07:21', '0', '', '');
INSERT INTO `t_resident` VALUES ('14', '1', 'li', '', '1', null, null, null, null, '2', '1', '3', null, null, null, null, '2', '2', null, null, null, '19', '21', '2017-07-04 11:32:01', '2017-07-04 11:32:01', '0', '', '');
INSERT INTO `t_resident` VALUES ('15', '1', '李程', '', null, '', '', '', '', '2', null, null, '', '', '', '', '2', '2', '', null, '常住人口', '19', '20', '2017-07-25 16:18:42', '2017-07-25 16:18:42', '0', '', '');

-- ----------------------------
-- Table structure for `t_resident_booklet`
-- ----------------------------
DROP TABLE IF EXISTS `t_resident_booklet`;
CREATE TABLE `t_resident_booklet` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `xiang_id` int(11) NOT NULL COMMENT '乡ID',
  `cun_id` int(11) NOT NULL COMMENT '村id',
  `house_holder` varchar(50) NOT NULL DEFAULT '' COMMENT '户主姓名',
  `village_group` varchar(50) DEFAULT '' COMMENT '村民小组组别',
  `contact_address` varchar(255) DEFAULT '' COMMENT '联系地址',
  `breed_category` varchar(255) DEFAULT '' COMMENT '家庭主要经营种养植类别',
  `breed_scale` varchar(255) DEFAULT '' COMMENT '家庭主要经营种养规模（亩，羽，只，群）',
  `bank_loan` varchar(10) DEFAULT NULL COMMENT '银行贷款总额（单位万元）',
  `private_lending` varchar(10) DEFAULT '' COMMENT '民间借贷总额（单位万元）',
  `loan_condition` varchar(255) DEFAULT '' COMMENT '贷款需求情况',
  `house_number` varchar(255) DEFAULT '' COMMENT '房产证编号',
  `house_space` varchar(11) DEFAULT '' COMMENT '房屋住房面积',
  `land_out` int(2) DEFAULT '2' COMMENT '土地、林地农业产业经营流出流入意向（1有，2无）',
  `house_out` int(2) DEFAULT '2' COMMENT '空闲房屋流出意向（1有，2无）',
  `land_out_space` varchar(11) DEFAULT '' COMMENT '愿意流出地块和面积（亩）',
  `location` varchar(255) DEFAULT '' COMMENT '所在位置',
  `land_in_space` varchar(255) DEFAULT '' COMMENT '需要流入面积（亩）',
  `house_floor_space` varchar(255) DEFAULT '' COMMENT '建筑层数和面积（平方米）',
  `nature` int(11) DEFAULT '1' COMMENT '性质（1耕地，2林地）',
  `house_out_year` int(11) DEFAULT '0' COMMENT '愿意流出使用年限意向',
  `house_run` int(11) DEFAULT '2' COMMENT '空闲房屋租赁经营意向（1有，2无）',
  `best_area` varchar(255) DEFAULT '' COMMENT '最佳地域',
  `build_space` varchar(10) DEFAULT '0' COMMENT '建筑面积（平方米）',
  `rent_year` int(11) DEFAULT '0' COMMENT '租赁经营年限意向（年）',
  `train_hope` int(11) DEFAULT '2' COMMENT '希望何种创业培训（1有，2无）',
  `agricultural_technique` varchar(125) DEFAULT '' COMMENT '农业生产技术',
  `home_manage` varchar(125) DEFAULT '' COMMENT '民宿经营管理',
  `e_commerce` varchar(125) DEFAULT '' COMMENT '电子商务',
  `employability` varchar(125) DEFAULT '' COMMENT '就业技能',
  `others` varchar(125) DEFAULT '' COMMENT '其他',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除（0正常，1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_resident_booklet
-- ----------------------------
INSERT INTO `t_resident_booklet` VALUES ('1', '19', '20', '李程', '1组', '', '', '', null, '', '', '', '', '2', '2', '', '', '', '', '1', '0', '2', '', '0', '0', '2', '', '', '', '', '', '2017-07-24 11:37:26', '2017-07-24 14:09:28', '0');
INSERT INTO `t_resident_booklet` VALUES ('2', '19', '20', '12', '12', '12', '12', '12', '12', '12', '12', '12', '12', '2', '1', '12', '2342', '12', '', '1', '0', '1', '', '', '0', '2', '', '', '', '', '', '2017-07-25 17:41:46', '2017-07-25 17:41:46', '0');

-- ----------------------------
-- Table structure for `t_resource_file`
-- ----------------------------
DROP TABLE IF EXISTS `t_resource_file`;
CREATE TABLE `t_resource_file` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `source_url` varchar(255) DEFAULT '' COMMENT '文件url',
  `source_suffix` varchar(50) DEFAULT '' COMMENT '文件后缀名',
  `source_size` decimal(12,2) DEFAULT '1.00' COMMENT '文件大小',
  `source_old_name` varchar(200) DEFAULT '' COMMENT '文件原名称',
  `source_name` varchar(200) DEFAULT '' COMMENT '文件名称uuid',
  `source_createtime` datetime DEFAULT NULL COMMENT '文件创建时间',
  `source_userid` int(20) DEFAULT NULL COMMENT '文件上传者userid',
  `source_duration` varchar(50) DEFAULT '' COMMENT '文件时长(视频和音频)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2464 DEFAULT CHARSET=utf8mb4 COMMENT='资源文件基础信息存储';

-- ----------------------------
-- Records of t_resource_file
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role_res`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_res`;
CREATE TABLE `t_role_res` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `resid` int(11) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=328 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_role_res
-- ----------------------------
INSERT INTO `t_role_res` VALUES ('282', '0', '1', '2017-07-03 09:31:37');
INSERT INTO `t_role_res` VALUES ('283', '0', '2', '2017-07-03 09:32:02');
INSERT INTO `t_role_res` VALUES ('284', '0', '3', '2017-07-03 09:32:14');
INSERT INTO `t_role_res` VALUES ('285', '0', '4', '2017-07-03 09:32:25');
INSERT INTO `t_role_res` VALUES ('286', '0', '5', '2017-07-03 09:32:48');
INSERT INTO `t_role_res` VALUES ('287', '0', '6', '2017-07-03 09:33:02');
INSERT INTO `t_role_res` VALUES ('288', '0', '7', '2017-07-03 09:33:15');
INSERT INTO `t_role_res` VALUES ('289', '0', '8', '2017-07-03 09:33:30');
INSERT INTO `t_role_res` VALUES ('290', '0', '9', '2017-07-03 09:33:51');
INSERT INTO `t_role_res` VALUES ('291', '0', '10', '2017-07-03 09:34:11');
INSERT INTO `t_role_res` VALUES ('292', '0', '12', '2017-07-03 09:34:27');
INSERT INTO `t_role_res` VALUES ('293', '0', '13', '2017-07-03 09:36:39');
INSERT INTO `t_role_res` VALUES ('294', '0', '15', '2017-07-03 09:36:58');
INSERT INTO `t_role_res` VALUES ('295', '0', '16', '2017-07-03 09:37:14');
INSERT INTO `t_role_res` VALUES ('296', '0', '17', '2017-07-03 09:37:30');
INSERT INTO `t_role_res` VALUES ('297', '0', '19', '2017-07-03 09:37:46');
INSERT INTO `t_role_res` VALUES ('298', '0', '20', '2017-07-03 09:37:58');
INSERT INTO `t_role_res` VALUES ('299', '1', '5', '2017-07-03 09:38:29');
INSERT INTO `t_role_res` VALUES ('300', '1', '6', '2017-07-03 09:39:06');
INSERT INTO `t_role_res` VALUES ('301', '1', '7', '2017-07-03 09:39:19');
INSERT INTO `t_role_res` VALUES ('302', '1', '8', '2017-07-03 09:39:31');
INSERT INTO `t_role_res` VALUES ('303', '1', '9', '2017-07-03 09:39:45');
INSERT INTO `t_role_res` VALUES ('304', '1', '10', '2017-07-03 09:39:54');
INSERT INTO `t_role_res` VALUES ('305', '1', '11', '2017-07-03 09:40:16');
INSERT INTO `t_role_res` VALUES ('306', '1', '12', '2017-07-03 09:40:32');
INSERT INTO `t_role_res` VALUES ('307', '1', '13', '2017-07-03 09:40:48');
INSERT INTO `t_role_res` VALUES ('308', '1', '15', '2017-07-03 09:41:10');
INSERT INTO `t_role_res` VALUES ('309', '1', '16', '2017-07-03 09:41:21');
INSERT INTO `t_role_res` VALUES ('310', '1', '17', '2017-07-03 09:41:32');
INSERT INTO `t_role_res` VALUES ('311', '1', '20', '2017-07-03 09:42:19');
INSERT INTO `t_role_res` VALUES ('312', '2', '5', '2017-07-03 09:42:57');
INSERT INTO `t_role_res` VALUES ('313', '2', '6', '2017-07-03 09:43:19');
INSERT INTO `t_role_res` VALUES ('314', '2', '7', '2017-07-03 09:43:43');
INSERT INTO `t_role_res` VALUES ('315', '2', '8', '2017-07-03 09:43:59');
INSERT INTO `t_role_res` VALUES ('316', '2', '9', '2017-07-03 09:44:07');
INSERT INTO `t_role_res` VALUES ('317', '2', '10', '2017-07-03 09:44:46');
INSERT INTO `t_role_res` VALUES ('318', '2', '11', '2017-07-03 09:47:06');
INSERT INTO `t_role_res` VALUES ('319', '2', '13', '2017-07-03 09:55:04');
INSERT INTO `t_role_res` VALUES ('320', '2', '14', '2017-07-03 09:55:43');
INSERT INTO `t_role_res` VALUES ('321', '2', '16', '2017-07-03 09:57:10');
INSERT INTO `t_role_res` VALUES ('322', '2', '17', '2017-07-03 09:57:24');
INSERT INTO `t_role_res` VALUES ('323', '2', '20', '2017-07-03 09:59:15');
INSERT INTO `t_role_res` VALUES ('324', '3', '8', '2017-07-03 10:06:10');
INSERT INTO `t_role_res` VALUES ('325', '3', '9', '2017-07-03 10:06:50');
INSERT INTO `t_role_res` VALUES ('326', '3', '10', '2017-07-03 10:07:02');
INSERT INTO `t_role_res` VALUES ('327', '3', '11', '2017-07-03 10:07:22');

-- ----------------------------
-- Table structure for `t_transmit`
-- ----------------------------
DROP TABLE IF EXISTS `t_transmit`;
CREATE TABLE `t_transmit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '传达ID',
  `receiver_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收件人姓名集合，以;分隔',
  `receiver` varchar(125) DEFAULT NULL COMMENT '收件人ID集合，以;分隔',
  `title` varchar(125) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '标题',
  `content` tinytext CHARACTER SET utf8mb4 COMMENT '内容',
  `sender` bigint(20) DEFAULT NULL COMMENT '收件人ID',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `by1` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '补充字段1',
  `by2` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '补充字段2',
  `by3` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '补充字段3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_transmit
-- ----------------------------
INSERT INTO `t_transmit` VALUES ('64', 'lao,hao,yuaofeng,yu,zhao', '22,32,31,29,11', 'haha', 'gfds', '1', '2017-07-24 15:33:00', null, '', null);
INSERT INTO `t_transmit` VALUES ('65', 'lao,hao,yuaofeng,yu,zhao', '22,32,31,29,11', 'haha', 'gfds', '1', '2017-07-24 15:33:03', null, '', null);

-- ----------------------------
-- Table structure for `t_transmit_receiver`
-- ----------------------------
DROP TABLE IF EXISTS `t_transmit_receiver`;
CREATE TABLE `t_transmit_receiver` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '阅件ID',
  `transmit_id` bigint(20) NOT NULL COMMENT '文件传达ID',
  `receiver_id` bigint(20) DEFAULT NULL COMMENT '收件人ID',
  `receiver_name` varchar(125) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '收件人姓名',
  `receiver_phone` varchar(125) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '收件人手机号',
  `is_read` int(2) DEFAULT '0' COMMENT '是否已阅读（0未读，1已读）',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `receive_time` datetime DEFAULT NULL COMMENT '收件时间',
  `by1` varchar(255) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '补充字段1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_transmit_receiver
-- ----------------------------
INSERT INTO `t_transmit_receiver` VALUES ('19', '22', '22', 'lao', '', '4', '2017-07-06 10:43:13', '2017-07-06 10:18:56', '');
INSERT INTO `t_transmit_receiver` VALUES ('20', '24', '22', 'lao', '', '12', '2017-07-06 10:43:16', '2017-07-06 10:31:36', '');
INSERT INTO `t_transmit_receiver` VALUES ('21', '24', '11', 'zhao', '', '0', null, '2017-07-06 10:31:36', '');
INSERT INTO `t_transmit_receiver` VALUES ('22', '25', '22', 'lao', '', '2', '2017-07-06 13:39:03', '2017-07-06 13:37:44', '');
INSERT INTO `t_transmit_receiver` VALUES ('23', '25', '11', 'zhao', '', '2', '2017-07-06 15:56:15', '2017-07-06 13:37:44', '');
INSERT INTO `t_transmit_receiver` VALUES ('24', '26', '23', 'wang', '', '1', '2017-07-06 13:51:28', '2017-07-06 13:48:59', '');
INSERT INTO `t_transmit_receiver` VALUES ('25', '27', '23', 'wang', '', '2', '2017-07-06 13:51:27', '2017-07-06 13:50:40', '');
INSERT INTO `t_transmit_receiver` VALUES ('26', '28', '22', 'lao', '', '2', '2017-07-06 16:29:08', '2017-07-06 13:55:43', '');
INSERT INTO `t_transmit_receiver` VALUES ('27', '29', '22', 'lao', '', '2', '2017-07-06 16:29:58', '2017-07-06 13:58:43', '');
INSERT INTO `t_transmit_receiver` VALUES ('28', '29', '11', 'zhao', '', '0', null, '2017-07-06 13:58:43', '');
INSERT INTO `t_transmit_receiver` VALUES ('29', '29', '26', '123123', '', '3', '2017-07-06 16:23:35', '2017-07-06 13:58:43', '');
INSERT INTO `t_transmit_receiver` VALUES ('30', '29', '25', 'test', '', '0', null, '2017-07-06 13:58:43', '');
INSERT INTO `t_transmit_receiver` VALUES ('31', '48', '22', 'lao', '', '0', null, '2017-07-08 16:20:37', '');
INSERT INTO `t_transmit_receiver` VALUES ('32', '48', '11', 'zhao', '', '0', null, '2017-07-08 16:20:37', '');
INSERT INTO `t_transmit_receiver` VALUES ('33', '49', '23', 'wang', '', '0', null, '2017-07-08 16:22:37', '');
INSERT INTO `t_transmit_receiver` VALUES ('34', '49', '24', '测试', '', '0', null, '2017-07-08 16:22:37', '');
INSERT INTO `t_transmit_receiver` VALUES ('35', '50', '26', '123123', '', '0', null, '2017-07-08 17:28:45', '');
INSERT INTO `t_transmit_receiver` VALUES ('36', '51', '22', 'lao', '', '1', '2017-07-10 10:19:23', '2017-07-10 10:02:31', '');
INSERT INTO `t_transmit_receiver` VALUES ('37', '51', '11', 'zhao', '', '0', null, '2017-07-10 10:02:31', '');
INSERT INTO `t_transmit_receiver` VALUES ('38', '51', '29', 'yu', '', '5', '2017-07-10 10:32:32', '2017-07-10 10:02:31', '');
INSERT INTO `t_transmit_receiver` VALUES ('39', '52', '23', 'wang', '', '0', null, '2017-07-10 10:18:57', '');
INSERT INTO `t_transmit_receiver` VALUES ('40', '53', '26', '123123', '', '1', '2017-07-10 10:34:52', '2017-07-10 10:33:51', '');
INSERT INTO `t_transmit_receiver` VALUES ('41', '54', '22', 'lao', '', '1', '2017-07-14 16:24:36', '2017-07-14 16:24:06', '');
INSERT INTO `t_transmit_receiver` VALUES ('42', '54', '11', 'zhao', '', '0', null, '2017-07-14 16:24:06', '');
INSERT INTO `t_transmit_receiver` VALUES ('43', '54', '26', '123123', '', '2', '2017-07-14 16:25:51', '2017-07-14 16:24:06', '');
INSERT INTO `t_transmit_receiver` VALUES ('44', '54', '25', 'test', '', '0', null, '2017-07-14 16:24:06', '');
INSERT INTO `t_transmit_receiver` VALUES ('45', '55', '23', 'wang', '', '2', '2017-07-19 14:35:46', '2017-07-14 16:29:02', '');
INSERT INTO `t_transmit_receiver` VALUES ('46', '56', '22', 'lao', '', '1', '2017-07-14 16:39:41', '2017-07-14 16:39:31', '');
INSERT INTO `t_transmit_receiver` VALUES ('47', '57', '28', 'pwdtest', '', '0', null, '2017-07-17 10:05:38', '');
INSERT INTO `t_transmit_receiver` VALUES ('48', '57', '32', 'hao', '', '0', null, '2017-07-17 10:05:38', '');
INSERT INTO `t_transmit_receiver` VALUES ('49', '58', '28', 'pwdtest', '', '0', null, '2017-07-17 10:20:55', '');
INSERT INTO `t_transmit_receiver` VALUES ('50', '60', '28', 'pwdtest', '18271624120', '0', null, '2017-07-17 10:24:52', '');
INSERT INTO `t_transmit_receiver` VALUES ('51', '60', '32', 'hao', '15757166090', '0', null, '2017-07-17 10:25:03', '');
INSERT INTO `t_transmit_receiver` VALUES ('52', '61', '22', 'lao', '15757166090', '1', '2017-07-18 09:55:37', '2017-07-18 09:55:22', '');
INSERT INTO `t_transmit_receiver` VALUES ('53', '62', '22', 'lao', '15757166090', '1', '2017-07-19 14:06:23', '2017-07-19 14:06:15', '');
INSERT INTO `t_transmit_receiver` VALUES ('54', '63', '22', 'lao', '15757166090', '3', '2017-07-19 14:23:37', '2017-07-19 14:18:56', '');
INSERT INTO `t_transmit_receiver` VALUES ('55', '64', '22', 'lao', '15757166090', '0', null, '2017-07-24 15:33:00', '');
INSERT INTO `t_transmit_receiver` VALUES ('56', '65', '22', 'lao', '15757166090', '0', null, '2017-07-24 15:33:03', '');
INSERT INTO `t_transmit_receiver` VALUES ('57', '64', '32', 'hao', '15757166090', '0', null, '2017-07-24 15:33:04', '');
INSERT INTO `t_transmit_receiver` VALUES ('58', '65', '32', 'hao', '15757166090', '0', null, '2017-07-24 15:33:04', '');
INSERT INTO `t_transmit_receiver` VALUES ('59', '65', '31', 'yuaofeng', '15757166090', '0', null, '2017-07-24 15:33:04', '');
INSERT INTO `t_transmit_receiver` VALUES ('60', '64', '31', 'yuaofeng', '15757166090', '0', null, '2017-07-24 15:33:04', '');
INSERT INTO `t_transmit_receiver` VALUES ('61', '65', '29', 'yu', '15757166090', '0', null, '2017-07-24 15:33:04', '');
INSERT INTO `t_transmit_receiver` VALUES ('62', '64', '29', 'yu', '15757166090', '0', null, '2017-07-24 15:33:04', '');
INSERT INTO `t_transmit_receiver` VALUES ('63', '65', '11', 'zhao', '15757166090', '0', null, '2017-07-24 15:33:05', '');
INSERT INTO `t_transmit_receiver` VALUES ('64', '64', '11', 'zhao', '15757166090', '0', null, '2017-07-24 15:33:05', '');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(125) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '用户姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '密码（md5加密）',
  `user_phone` varchar(125) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '手机号',
  `xiang_id` bigint(20) DEFAULT NULL COMMENT '乡id',
  `cun_id` bigint(20) DEFAULT NULL COMMENT '村ID',
  `depart_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `level` int(2) DEFAULT '0' COMMENT '0县级，1乡级，2村级,3部级',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除（0未删除，1已删除）',
  `is_report` int(2) DEFAULT '1' COMMENT '是否上传报表用户（1不是，0是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '18271624120', null, null, null, '0', '2017-05-26 10:39:53', '2017-07-11 10:18:35', '0', '1');
INSERT INTO `t_user` VALUES ('10', 'li', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', null, null, null, '0', '2017-07-03 14:35:03', '2017-07-03 14:35:03', '1', '1');
INSERT INTO `t_user` VALUES ('11', 'zhao', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', '24', null, null, '1', '2017-07-03 14:36:17', '2017-07-14 15:52:33', '0', '1');
INSERT INTO `t_user` VALUES ('13', 'sun', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', '18', null, null, '2', '2017-07-03 14:37:34', '2017-07-03 14:37:34', '1', '1');
INSERT INTO `t_user` VALUES ('15', 'lao', '13bbf54a6850c393fb8d1b2b3bba997b', '15757166090', '18', null, '12', '0', '2017-07-03 14:41:41', '2017-07-03 15:17:40', '1', '1');
INSERT INTO `t_user` VALUES ('16', '12', '13bbf54a6850c393fb8d1b2b3bba997b', '15757166090', '19', null, '7', '3', '2017-07-03 14:42:17', '2017-07-03 14:48:48', '1', '1');
INSERT INTO `t_user` VALUES ('17', 'wang', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', '19', '20', null, '2', '2017-07-03 15:04:07', '2017-07-03 15:04:07', '1', '1');
INSERT INTO `t_user` VALUES ('20', '李程', 'e10adc3949ba59abbe56e057f20f883e', '18271624120', null, null, null, '0', '2017-07-03 15:43:37', '2017-07-03 15:43:37', '1', '1');
INSERT INTO `t_user` VALUES ('21', '李程', 'e10adc3949ba59abbe56e057f20f883e', '18271624120', null, null, null, '0', '2017-07-03 15:44:05', '2017-07-03 15:44:05', '0', '1');
INSERT INTO `t_user` VALUES ('22', 'lao', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', '19', null, null, '1', '2017-07-03 15:45:46', '2017-07-04 11:30:19', '0', '1');
INSERT INTO `t_user` VALUES ('23', 'wang', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', '19', '20', null, '2', '2017-07-03 15:52:50', '2017-07-06 15:11:59', '0', '0');
INSERT INTO `t_user` VALUES ('24', '测试', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', '19', '21', null, '2', '2017-07-03 16:05:56', '2017-07-11 10:18:47', '0', '0');
INSERT INTO `t_user` VALUES ('25', 'test', '202cb962ac59075b964b07152d234b70', '15757166090', '18', null, '3', '3', '2017-07-03 16:12:18', '2017-07-03 16:12:18', '0', '1');
INSERT INTO `t_user` VALUES ('26', '123123', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', '18', null, '3', '3', '2017-07-03 16:12:42', '2017-07-06 15:55:15', '0', '1');
INSERT INTO `t_user` VALUES ('27', '2352', '13bbf54a6850c393fb8d1b2b3bba997b', '15757166090', null, null, null, '2', '2017-07-03 16:20:00', '2017-07-06 14:32:17', '0', '1');
INSERT INTO `t_user` VALUES ('28', 'pwdtest', '13bbf54a6850c393fb8d1b2b3bba997b', '18271624120', '18', null, null, '1', '2017-07-04 11:34:27', '2017-07-17 10:02:16', '0', '1');
INSERT INTO `t_user` VALUES ('29', 'yu', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', '19', null, '3', '3', '2017-07-06 14:30:51', '2017-07-06 14:30:51', '0', '1');
INSERT INTO `t_user` VALUES ('30', 'licheng', 'e739f571a686204163285b2317dff8e5', '18271624120', null, null, null, '0', '2017-07-08 16:30:05', '2017-07-08 16:30:05', '0', '1');
INSERT INTO `t_user` VALUES ('31', 'yuaofeng', '13bbf54a6850c393fb8d1b2b3bba997b', '15757166090', '19', null, '3', '3', '2017-07-14 10:08:57', '2017-07-18 09:58:41', '0', '1');
INSERT INTO `t_user` VALUES ('32', 'hao', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', '19', null, '3', '3', '2017-07-14 10:15:12', '2017-07-14 10:15:12', '0', '1');
INSERT INTO `t_user` VALUES ('33', 'aaa', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', '19', null, '3', '3', '2017-07-14 10:52:55', '2017-07-14 10:52:55', '1', '1');
INSERT INTO `t_user` VALUES ('34', 'bbb', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', '19', null, null, '1', '2017-07-14 10:56:50', '2017-07-14 10:56:50', '1', '1');
INSERT INTO `t_user` VALUES ('35', 'ccc', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', '19', '20', null, '2', '2017-07-14 10:57:06', '2017-07-14 17:33:12', '0', '1');
INSERT INTO `t_user` VALUES ('36', '111', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', '24', '26', null, '2', '2017-07-14 11:21:20', '2017-07-14 11:21:20', '0', '1');
INSERT INTO `t_user` VALUES ('37', '222', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', '18', null, '3', '3', '2017-07-14 15:54:20', '2017-07-14 15:54:20', '1', '1');
INSERT INTO `t_user` VALUES ('38', 'li', 'e10adc3949ba59abbe56e057f20f883e', '18271624120', null, null, null, '0', '2017-07-17 09:34:25', '2017-07-17 09:34:25', '1', '1');
INSERT INTO `t_user` VALUES ('39', 'li', 'e10adc3949ba59abbe56e057f20f883e', '18271624120', null, null, null, '0', '2017-07-17 09:34:41', '2017-07-17 09:34:41', '0', '1');
INSERT INTO `t_user` VALUES ('40', 'bbb', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', '19', null, null, '1', '2017-07-18 09:57:08', '2017-07-18 09:57:08', '1', '1');
INSERT INTO `t_user` VALUES ('41', 'bbb', 'e10adc3949ba59abbe56e057f20f883e', '15757166090', null, null, null, '0', '2017-07-18 09:57:36', '2017-07-18 09:57:36', '0', '1');

-- ----------------------------
-- Table structure for `t_village`
-- ----------------------------
DROP TABLE IF EXISTS `t_village`;
CREATE TABLE `t_village` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '乡村ID',
  `level` int(11) DEFAULT NULL COMMENT '级别（0为乡级，1为村级）',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `village_name` varchar(125) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '乡村名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除（0不删除，1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_village
-- ----------------------------
INSERT INTO `t_village` VALUES ('18', '0', '0', '测试', '2017-07-03 13:52:29', '2017-07-03 13:52:29', '0');
INSERT INTO `t_village` VALUES ('19', '0', '0', '莲花乡', '2017-07-03 14:47:42', '2017-07-03 14:47:42', '0');
INSERT INTO `t_village` VALUES ('20', '1', '19', '二狗村', '2017-07-03 14:48:04', '2017-07-03 14:48:04', '0');
INSERT INTO `t_village` VALUES ('21', '1', '19', '南山', '2017-07-03 14:48:20', '2017-07-03 14:48:20', '0');
INSERT INTO `t_village` VALUES ('22', '0', '0', '北门乡1', '2017-07-14 11:07:22', '2017-07-14 11:07:34', '1');
INSERT INTO `t_village` VALUES ('23', '1', '22', '1111', '2017-07-14 11:07:41', '2017-07-14 11:07:41', '0');
INSERT INTO `t_village` VALUES ('24', '0', '0', '北门乡', '2017-07-14 11:07:57', '2017-07-14 11:07:57', '0');
INSERT INTO `t_village` VALUES ('25', '1', '24', '泉门村', '2017-07-14 11:08:30', '2017-07-14 11:08:30', '0');
INSERT INTO `t_village` VALUES ('26', '1', '24', '赵家村', '2017-07-14 11:08:39', '2017-07-14 11:20:34', '0');
