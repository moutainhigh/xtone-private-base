/*
Navicat MySQL Data Transfer

Source Server         : 121.40.138.91
Source Server Version : 50624
Source Host           : 121.40.138.91:3306
Source Database       : hl_push

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-10-27 17:51:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '名字',
  `url` varchar(512) DEFAULT NULL COMMENT '地址',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '父节点',
  `status` tinyint(4) NOT NULL COMMENT '状态1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COMMENT='用户菜单表';

-- ----------------------------
-- Table structure for admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_menu`;
CREATE TABLE `admin_role_menu` (
  `roleid` int(11) NOT NULL,
  `menuid` int(11) NOT NULL,
  PRIMARY KEY (`roleid`,`menuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `password` varchar(40) NOT NULL,
  `role` int(11) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for apk
-- ----------------------------
DROP TABLE IF EXISTS `apk`;
CREATE TABLE `apk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `pkgname` varchar(50) DEFAULT NULL,
  `sign` varchar(128) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `version` varchar(30) DEFAULT NULL,
  `versioncode` int(11) DEFAULT NULL,
  `channel` varchar(30) DEFAULT NULL,
  `phonetype` varchar(30) DEFAULT NULL,
  `url` varchar(128) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for app_switch
-- ----------------------------
DROP TABLE IF EXISTS `app_switch`;
CREATE TABLE `app_switch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(30) NOT NULL,
  `appname` varchar(50) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(64) NOT NULL,
  `province` varchar(64) DEFAULT NULL,
  `country` varchar(64) DEFAULT NULL,
  `area` varchar(64) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `createdate` datetime DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for channel_area
-- ----------------------------
DROP TABLE IF EXISTS `channel_area`;
CREATE TABLE `channel_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel` varchar(30) NOT NULL,
  `area` varchar(256) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0',
  `createdate` datetime DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel` varchar(30) DEFAULT NULL,
  `appid` varchar(100) DEFAULT NULL,
  `client_id` varchar(40) DEFAULT NULL,
  `area` varchar(256) DEFAULT NULL,
  `imei` varchar(50) DEFAULT NULL,
  `imsi` varchar(50) DEFAULT NULL,
  `wifi` varchar(10) DEFAULT NULL,
  `model` varchar(100) DEFAULT NULL,
  `phone_num` varchar(20) DEFAULT NULL,
  `desity` decimal(18,0) DEFAULT NULL,
  `width` varchar(10) DEFAULT NULL,
  `height` varchar(10) DEFAULT NULL,
  `scaled_density` varchar(20) DEFAULT NULL,
  `xdpi` varchar(20) DEFAULT NULL,
  `ydpi` varchar(20) DEFAULT NULL,
  `ramsize` varchar(20) DEFAULT NULL,
  `leftramsize` varchar(20) DEFAULT NULL,
  `romsize` varchar(20) DEFAULT NULL,
  `leftromsize` varchar(20) DEFAULT NULL,
  `sd1size` varchar(20) DEFAULT NULL,
  `leftsd1size` varchar(20) DEFAULT NULL,
  `sd2size` varchar(20) DEFAULT NULL,
  `leftsd2size` varchar(20) DEFAULT NULL,
  `age` varchar(10) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `client_id` (`client_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9338330 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subname` varchar(30) DEFAULT NULL,
  `channel` varchar(30) DEFAULT NULL,
  `belong` varchar(30) DEFAULT NULL,
  `zhname` varchar(255) DEFAULT '',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for country
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mcc` varchar(10) NOT NULL,
  `mnc` varchar(10) NOT NULL,
  `country` varchar(10) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `unitname` varchar(20) DEFAULT NULL,
  `timezone` varchar(20) DEFAULT NULL,
  `ccode` varchar(10) DEFAULT NULL,
  `language` varchar(10) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `ename` varchar(50) DEFAULT NULL,
  `localname` varchar(40) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=156 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for googleplayapk
-- ----------------------------
DROP TABLE IF EXISTS `googleplayapk`;
CREATE TABLE `googleplayapk` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `country` varchar(10) DEFAULT NULL,
  `pkg` varchar(128) DEFAULT NULL,
  `apk` varchar(256) DEFAULT NULL,
  `icon` varchar(256) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `md5` varchar(40) DEFAULT NULL,
  `price` float DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for iptable
-- ----------------------------
DROP TABLE IF EXISTS `iptable`;
CREATE TABLE `iptable` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `StartIPNum` bigint(10) NOT NULL,
  `StartIPText` varchar(30) DEFAULT NULL,
  `EndIPNum` bigint(10) NOT NULL,
  `EndIPText` varchar(30) DEFAULT NULL,
  `Country` varchar(510) DEFAULT NULL,
  `Local` varchar(510) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=444461 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ldg_push_test
-- ----------------------------
DROP TABLE IF EXISTS `ldg_push_test`;
CREATE TABLE `ldg_push_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `IMSI` varchar(50) DEFAULT NULL,
  `IMEI` varchar(50) DEFAULT NULL,
  `User_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=835 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for libs
-- ----------------------------
DROP TABLE IF EXISTS `libs`;
CREATE TABLE `libs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `version` varchar(20) NOT NULL,
  `versioncode` int(11) NOT NULL,
  `crc32` varchar(50) DEFAULT NULL,
  `url` varchar(128) NOT NULL,
  `channel` varchar(30) DEFAULT NULL,
  `phonetype` varchar(30) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for o_channelorg
-- ----------------------------
DROP TABLE IF EXISTS `o_channelorg`;
CREATE TABLE `o_channelorg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orgid` int(20) DEFAULT NULL,
  `channel` varchar(30) NOT NULL,
  `channelname` varchar(30) NOT NULL,
  `key` varchar(40) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0',
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `channel_index` (`channel`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `channel` varchar(30) DEFAULT NULL,
  `channelname` varchar(30) DEFAULT NULL,
  `belong` varchar(30) DEFAULT NULL,
  `appid` varchar(30) DEFAULT NULL,
  `appname` varchar(30) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for push_area
-- ----------------------------
DROP TABLE IF EXISTS `push_area`;
CREATE TABLE `push_area` (
  `AREA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AREA_Name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`AREA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for push_filterid
-- ----------------------------
DROP TABLE IF EXISTS `push_filterid`;
CREATE TABLE `push_filterid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imei` varchar(50) DEFAULT NULL,
  `imsi` varchar(50) DEFAULT NULL,
  `user_c_day` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for push_history
-- ----------------------------
DROP TABLE IF EXISTS `push_history`;
CREATE TABLE `push_history` (
  `PUSH_HISTORY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `IMEI` varchar(100) DEFAULT NULL,
  `IMSI` varchar(100) DEFAULT NULL,
  `client_id` varchar(40) DEFAULT NULL,
  `cmd_id` varchar(256) DEFAULT NULL,
  `push_id` varchar(256) DEFAULT NULL,
  `area` varchar(300) DEFAULT NULL,
  `PHONETYPE_NAME` varchar(60) DEFAULT NULL,
  `PUSH_HISTORY_CREATEDATE` datetime DEFAULT NULL,
  `PUSH_POLICY_ID` int(11) DEFAULT NULL,
  `push_record_ID` bigint(20) DEFAULT NULL,
  `User_id` varchar(40) DEFAULT NULL,
  `apk_type` varchar(50) DEFAULT NULL,
  `kou_money` decimal(19,4) DEFAULT '0.0000',
  `baike_money` decimal(19,4) DEFAULT '0.0000',
  `RES_sp_money` decimal(19,4) DEFAULT '0.0000',
  `sp_id` int(11) DEFAULT NULL,
  `RES_ID` int(11) DEFAULT NULL,
  `download_ok` tinyint(1) DEFAULT '0',
  `history_type` int(11) DEFAULT NULL,
  `download` int(11) DEFAULT NULL,
  `mount` int(11) DEFAULT NULL,
  `activate` int(11) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`PUSH_HISTORY_ID`),
  KEY `client_id` (`client_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2517860 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for push_policy
-- ----------------------------
DROP TABLE IF EXISTS `push_policy`;
CREATE TABLE `push_policy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `subtype` int(11) DEFAULT '0',
  `res_id` int(11) DEFAULT NULL,
  `content` longtext,
  `updatedate` datetime DEFAULT NULL,
  `begintime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `model` longtext,
  `country` varchar(256) DEFAULT NULL,
  `push_status` int(11) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `link` longtext,
  `all_push` tinyint(1) DEFAULT NULL,
  `network` int(10) DEFAULT NULL,
  `channel` varchar(30) DEFAULT NULL,
  `open` varchar(250) DEFAULT NULL,
  `closeh_nam` varchar(250) DEFAULT NULL,
  `cesu` varchar(250) DEFAULT NULL,
  `state` varchar(250) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `age` longtext,
  `size` varchar(200) DEFAULT NULL,
  `area` longtext,
  `img_link` longtext,
  `downcount` int(11) DEFAULT NULL,
  `close` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `res_loc` int(11) DEFAULT NULL,
  `rom` varchar(1000) DEFAULT NULL,
  `down_direct` int(11) DEFAULT NULL,
  `channelname` longtext,
  `canal_switch` int(11) DEFAULT NULL,
  `push_count` int(11) NOT NULL DEFAULT '10000',
  `weight` int(11) DEFAULT '100',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qihu
-- ----------------------------
DROP TABLE IF EXISTS `qihu`;
CREATE TABLE `qihu` (
  `id` bigint(20) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `phonenum` varchar(255) DEFAULT NULL,
  `releasever` varchar(255) DEFAULT NULL,
  `sdkvername` varchar(255) DEFAULT NULL,
  `sdkver` varchar(255) DEFAULT NULL,
  `sdkverint` varchar(255) DEFAULT NULL,
  `andver` varchar(255) DEFAULT NULL,
  `imsi` varchar(255) DEFAULT NULL,
  `imei` varchar(255) DEFAULT NULL,
  `mac` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `net` varchar(255) DEFAULT NULL,
  `lang` varchar(255) DEFAULT NULL,
  `channel` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for smscenter
-- ----------------------------
DROP TABLE IF EXISTS `smscenter`;
CREATE TABLE `smscenter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `smscenter` varchar(20) NOT NULL,
  `province` varchar(64) DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0',
  `createdate` datetime DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for statis_client
-- ----------------------------
DROP TABLE IF EXISTS `statis_client`;
CREATE TABLE `statis_client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel` varchar(30) NOT NULL,
  `model` varchar(80) DEFAULT NULL,
  `area` varchar(20) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  `createdate` datetime DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=353 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for statis_msg
-- ----------------------------
DROP TABLE IF EXISTS `statis_msg`;
CREATE TABLE `statis_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `msgid` bigint(20) NOT NULL,
  `msgname` varchar(50) DEFAULT NULL,
  `channel` varchar(20) DEFAULT NULL,
  `model` varchar(80) DEFAULT NULL,
  `area` varchar(20) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  `arrive` int(11) DEFAULT '0',
  `active` int(11) DEFAULT '0',
  `money` int(11) DEFAULT '0',
  `createdate` datetime DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20649 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uninstallation_log
-- ----------------------------
DROP TABLE IF EXISTS `uninstallation_log`;
CREATE TABLE `uninstallation_log` (
  `PUSH_HISTORY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `push_record_ID` int(11) DEFAULT NULL,
  `Apply_ID` int(11) DEFAULT NULL,
  `app_name` varchar(200) DEFAULT NULL,
  `package_name` varchar(100) DEFAULT NULL,
  `version` varchar(100) DEFAULT NULL,
  `User_id` int(11) DEFAULT NULL,
  `BeginningDate` datetime DEFAULT NULL,
  `FinishDate` datetime DEFAULT NULL,
  `PUSH_POLICY_STATUS` int(11) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PUSH_HISTORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for white_channel
-- ----------------------------
DROP TABLE IF EXISTS `white_channel`;
CREATE TABLE `white_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel` varchar(30) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `channel` (`channel`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
