/*
 Navicat Premium Data Transfer

 Source Server         : wangwenqing
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : tranxact

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 18/04/2024 19:42:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bank
-- ----------------------------
DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank`  (
  `id` int NOT NULL,
  `customer_id` int NOT NULL,
  `bank_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bank
-- ----------------------------

-- ----------------------------
-- Table structure for commission
-- ----------------------------
DROP TABLE IF EXISTS `commission`;
CREATE TABLE `commission`  (
  `id` int NOT NULL,
  `prime_account_id` int NULL DEFAULT NULL,
  `cuacct_cls` int NULL DEFAULT NULL,
  `market` int NULL DEFAULT NULL,
  `stk_cls` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `rate` decimal(20, 4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commission
-- ----------------------------
INSERT INTO `commission` VALUES (1, 31355654, 0, 0, 'S0', 0.0010);
INSERT INTO `commission` VALUES (2, 31355654, 0, 1, 'S0', 0.0010);
INSERT INTO `commission` VALUES (3, NULL, 1, 0, 'S0', 0.0008);
INSERT INTO `commission` VALUES (4, NULL, 1, 1, 'S0', 0.0008);
INSERT INTO `commission` VALUES (5, NULL, 2, 0, 'S0', 0.0006);
INSERT INTO `commission` VALUES (6, NULL, 2, 1, 'S0', 0.0006);
INSERT INTO `commission` VALUES (7, NULL, 3, 0, 'S0', 0.0003);
INSERT INTO `commission` VALUES (8, NULL, 3, 1, 'S0', 0.0003);

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int NOT NULL,
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cuacct_cls` int NULL DEFAULT NULL,
  `cuacct_status` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (11111111, NULL, '1', NULL, NULL, NULL);
INSERT INTO `customer` VALUES (22222222, NULL, '2', NULL, NULL, NULL);
INSERT INTO `customer` VALUES (31355654, '测试', '0', '130633200203275473', 0, 0);
INSERT INTO `customer` VALUES (33333333, NULL, '3', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for follow_account
-- ----------------------------
DROP TABLE IF EXISTS `follow_account`;
CREATE TABLE `follow_account`  (
  `id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `prime_account_id` int NULL DEFAULT NULL,
  `balance` decimal(20, 4) NULL DEFAULT NULL,
  `market` int NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  ` balance_usable` decimal(20, 4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of follow_account
-- ----------------------------
INSERT INTO `follow_account` VALUES ('0111111111', 11111111, 100000.0000, 1, '2024-04-18 18:42:10', NULL);
INSERT INTO `follow_account` VALUES ('0123456789', 31355654, 50000.0000, 1, '2024-04-18 18:39:26', NULL);
INSERT INTO `follow_account` VALUES ('0222222222', 22222222, 500000.0000, 1, '2024-04-18 18:42:34', NULL);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` int NOT NULL,
  `unit` int NULL DEFAULT NULL,
  `prime_account_id` int NULL DEFAULT NULL,
  `follow_account_id` int NULL DEFAULT NULL,
  `stk_cls` int NULL DEFAULT NULL,
  `rate` decimal(20, 4) NULL DEFAULT NULL,
  `trd_id` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stock_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_time` timestamp NULL DEFAULT NULL,
  `order_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_amount` int NULL DEFAULT NULL,
  `order_price` decimal(20, 4) NULL DEFAULT NULL,
  `deal_amount` int NULL DEFAULT NULL,
  `deal_price` decimal(20, 4) NULL DEFAULT NULL,
  `is_withdraw` int NULL DEFAULT NULL,
  `withdraw_amount` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `prime_account_id` int NULL DEFAULT NULL,
  `follow_account_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stock_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `market` int NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `share_total` int NULL DEFAULT NULL,
  `share_usable` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES (1, 31355654, '0123456789', '600446', 1, '2024-04-18 19:10:46', 1000, NULL);
INSERT INTO `position` VALUES (2, 31355654, '0123456789', '000001', 0, '2024-04-18 19:24:22', 500, NULL);
INSERT INTO `position` VALUES (3, 31355654, '0123456789', '000858', 0, '2024-04-18 19:25:15', 200, NULL);
INSERT INTO `position` VALUES (4, 11111111, '0111111111', '600446', 1, '2024-04-18 19:32:44', 2000, NULL);
INSERT INTO `position` VALUES (5, 11111111, '0111111111', '600900', 1, '2024-04-18 19:35:17', 5000, NULL);
INSERT INTO `position` VALUES (6, 11111111, '0111111111', '000858', 0, '2024-04-18 19:35:30', 2000, NULL);
INSERT INTO `position` VALUES (7, 22222222, '0222222222', '600446', 1, '2024-04-18 19:35:44', 20000, NULL);
INSERT INTO `position` VALUES (8, 22222222, '0222222222', '600900', 1, '2024-04-18 19:36:10', 10000, NULL);
INSERT INTO `position` VALUES (9, 22222222, '0222222222', '000858', 0, '2024-04-18 19:36:24', 10000, NULL);
INSERT INTO `position` VALUES (10, 33333333, '0333333333', '600446', 1, '2024-04-18 19:36:42', 100000, NULL);
INSERT INTO `position` VALUES (11, 33333333, '0333333333', '601857', 1, '2024-04-18 19:37:25', 100000, NULL);
INSERT INTO `position` VALUES (12, 33333333, '0333333333', '000651', 0, '2024-04-18 19:37:45', 100000, NULL);

-- ----------------------------
-- Table structure for prime_account
-- ----------------------------
DROP TABLE IF EXISTS `prime_account`;
CREATE TABLE `prime_account`  (
  `id` int NOT NULL,
  `balance_usable` decimal(20, 4) NULL DEFAULT NULL,
  `balance_total` decimal(20, 4) NULL DEFAULT NULL,
  `cuacct_cls` int NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `password` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prime_account
-- ----------------------------
INSERT INTO `prime_account` VALUES (11111111, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `prime_account` VALUES (22222222, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `prime_account` VALUES (31355654, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `prime_account` VALUES (33333333, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`  (
  `id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stock_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `market` int NULL DEFAULT NULL,
  `stk_cls` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stamp` decimal(20, 3) NULL DEFAULT NULL,
  `currency` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES ('000001', '平安银行', 0, 'S0', 0.001, '0');
INSERT INTO `stock` VALUES ('000651', '格力电器', 0, 'S0', 0.001, '0');
INSERT INTO `stock` VALUES ('000858', '五粮液', 0, 'S0', 0.001, '0');
INSERT INTO `stock` VALUES ('600446', '金证股份', 1, 'S0', 0.001, '0');
INSERT INTO `stock` VALUES ('600900', '长江电力', 1, 'S0', 0.001, '0');
INSERT INTO `stock` VALUES ('601857', '中国石油', 1, 'S0', 0.001, '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin');

-- ----------------------------
-- Table structure for trade_unit
-- ----------------------------
DROP TABLE IF EXISTS `trade_unit`;
CREATE TABLE `trade_unit`  (
  `id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of trade_unit
-- ----------------------------
INSERT INTO `trade_unit` VALUES (22222);
INSERT INTO `trade_unit` VALUES (23296);
INSERT INTO `trade_unit` VALUES (33333);
INSERT INTO `trade_unit` VALUES (44444);
INSERT INTO `trade_unit` VALUES (55555);

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction`  (
  `id` int NOT NULL,
  `order_id` int NULL DEFAULT NULL,
  `unit` int NULL DEFAULT NULL,
  `prime_account_id` int NULL DEFAULT NULL,
  `follow_account_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `trd_id` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `amount` int NULL DEFAULT NULL,
  `price` decimal(20, 4) NULL DEFAULT NULL,
  `transact_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of transaction
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;