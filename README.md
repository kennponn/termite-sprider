# termite-sprider
:ant:采集京东，代码未完成只留做仓库方便多地开发:lemon::lemon:

[code]
/*
Navicat MySQL Data Transfer

Source Server         : 123
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : termite_sprider

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2019-03-27 16:45:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for jdlink
-- ----------------------------
DROP TABLE IF EXISTS `jdlink`;
CREATE TABLE `jdlink` (
  `linkid` varchar(60) NOT NULL,
  PRIMARY KEY (`linkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `productId` varchar(255) NOT NULL,
  `productName` varchar(255) DEFAULT NULL,
  `productSummary` varchar(2048) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `price` varchar(20) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

[/code]
