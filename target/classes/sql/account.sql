/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.17.14_3306
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 192.168.17.14:3306
 Source Schema         : demo_db

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 30/09/2020 13:00:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `account_num` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账单编号',
  `account_type` tinyint(4) NOT NULL COMMENT '收支类型（1收入，2支出）',
  `quota` float NULL DEFAULT NULL COMMENT '额度',
  `cost_type` tinyint(4) NULL DEFAULT NULL COMMENT '费用类型（1转账，2红包，3工资...）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
