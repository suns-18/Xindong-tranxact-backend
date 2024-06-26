/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : xindong_tranxact

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 26/04/2024 21:04:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bank
-- ----------------------------
DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `customer_id` int(0) NOT NULL,
  `bank_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1111889 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bank
-- ----------------------------
INSERT INTO `bank` VALUES (1, 33333313, 'string', 'string', 0);
INSERT INTO `bank` VALUES (3, 33333373, 'string', 'string', 0);
INSERT INTO `bank` VALUES (12, 33333773, 'string', 'string', 0);
INSERT INTO `bank` VALUES (17, 33337773, 'string', 'string', 0);
INSERT INTO `bank` VALUES (187, 33737773, '你好', '123334434', 0);
INSERT INTO `bank` VALUES (1187, 34737773, '你好', '123334434', 0);
INSERT INTO `bank` VALUES (11887, 34777773, '你好', '123334434', 0);
INSERT INTO `bank` VALUES (111887, 37777773, '你好', '123334434', 123456);
INSERT INTO `bank` VALUES (115887, 37767673, '你好', '123334434', 123456);
INSERT INTO `bank` VALUES (1111887, 31767673, '你好', '123334434', 123456);
INSERT INTO `bank` VALUES (1111888, 111, '111', '111', 1);

-- ----------------------------
-- Table structure for commission
-- ----------------------------
DROP TABLE IF EXISTS `commission`;
CREATE TABLE `commission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `cuacct_cls` int(0) NULL DEFAULT NULL,
  `market` int(0) NULL DEFAULT NULL,
  `stk_cls` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `rate` decimal(20, 4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commission
-- ----------------------------
INSERT INTO `commission` VALUES (1,  0, 0, 'S0', 0.0010);
INSERT INTO `commission` VALUES (2,  0, 1, 'S0', 0.0010);
INSERT INTO `commission` VALUES (3,  1, 0, 'S0', 0.0008);
INSERT INTO `commission` VALUES (4,  1, 1, 'S0', 0.0008);
INSERT INTO `commission` VALUES (5,  2, 0, 'S0', 0.0006);
INSERT INTO `commission` VALUES (6,  2, 1, 'S0', 0.0006);
INSERT INTO `commission` VALUES (7,  3, 0, 'S0', 0.0003);
INSERT INTO `commission` VALUES (8,  3, 1, 'S0', 0.0003);

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cuacct_cls` int(0) NULL DEFAULT NULL,
  `cuacct_status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51395745 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (11111111, 'wxr', '01', '130633200203275473', 1, 0);
INSERT INTO `customer` VALUES (13579966, 'string', '00', '130633200203275473', 1, 0);
INSERT INTO `customer` VALUES (22222222, 'wxr123', '02', '130633200203275473', 1, 0);
INSERT INTO `customer` VALUES (31355654, '测试', '00', '130633200203275473', 0, 0);
INSERT INTO `customer` VALUES (31767673, 'nhoooo', '00', '123456789987654321', 3, 0);
INSERT INTO `customer` VALUES (33333313, 'niaho', '00', '123456789987654321', 3, 0);
INSERT INTO `customer` VALUES (33333333, 'asdas', '00', '130633200203275473', 3, 0);
INSERT INTO `customer` VALUES (33333353, 'niaho', '00', '123456789987654321', 3, 0);
INSERT INTO `customer` VALUES (33333373, 'niaho', '00', '123456789987654321', 3, 0);
INSERT INTO `customer` VALUES (33333393, 'niaho', '00', '123456789987654321', 3, 0);
INSERT INTO `customer` VALUES (33333573, 'niaho', '00', '123456789987654321', 3, 0);
INSERT INTO `customer` VALUES (33333773, 'nho', '00', '123456789987654321', 3, 0);
INSERT INTO `customer` VALUES (33337773, 'nho', '00', '123456789987654321', 3, 0);
INSERT INTO `customer` VALUES (33737773, 'nhoooo', '00', '123456789987654321', 3, 0);
INSERT INTO `customer` VALUES (34737773, 'nhoooo', '00', '123456789987654321', 3, 0);
INSERT INTO `customer` VALUES (34777773, 'nhoooo', '00', '123456789987654321', 3, 0);
INSERT INTO `customer` VALUES (37767673, 'nhoooo', '00', '123456789987654321', 3, 0);
INSERT INTO `customer` VALUES (37767773, 'nhoooo', '00', '123456789987654321', 3, 0);
INSERT INTO `customer` VALUES (37777773, 'nhoooo', '00', '123456789987654321', 3, 0);
INSERT INTO `customer` VALUES (51395744, 'string', '00', '130633200203275473', 3, 0);

-- ----------------------------
-- Table structure for follow_account
-- ----------------------------
DROP TABLE IF EXISTS `follow_account`;
CREATE TABLE `follow_account`  (
  `id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `prime_account_id` int(0) NULL DEFAULT NULL,
  `market` int(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of follow_account
-- ----------------------------
INSERT INTO `follow_account` VALUES ('0000301051', 44381930, 0, '2024-04-20 17:39:28');
INSERT INTO `follow_account` VALUES ('0000457974', 13579966, 0, '2024-04-20 17:39:28');
INSERT INTO `follow_account` VALUES ('0000483059', 51395744, 0, '2024-04-20 17:39:28');
INSERT INTO `follow_account` VALUES ('0000608178', 32492884, 0, '2024-04-25 17:13:17');
INSERT INTO `follow_account` VALUES ('0111111111', 11111111, 0, '2024-04-18 18:42:10');
INSERT INTO `follow_account` VALUES ('0123456789', 31355654, 0, '2024-04-18 18:39:26');
INSERT INTO `follow_account` VALUES ('0222222222', 22222222, 0, '2024-04-18 18:42:34');
INSERT INTO `follow_account` VALUES ('0333333333', 33333333, 0, '2024-04-19 15:33:42');
INSERT INTO `follow_account` VALUES ('A111111111', 11111111, 1, '2024-04-19 15:50:42');
INSERT INTO `follow_account` VALUES ('A123456789', 31355654, 1, '2024-04-26 11:35:07');
INSERT INTO `follow_account` VALUES ('A222222222', 22222222, 1, '2024-04-26 11:35:09');
INSERT INTO `follow_account` VALUES ('B333333333', 33333333, 1, '2024-04-26 11:35:12');

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `unit` int(0) NULL DEFAULT NULL,
  `prime_account_id` int(0) NULL DEFAULT NULL,
  `follow_account_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stk_cls` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `rate` decimal(20, 4) NULL DEFAULT NULL,
  `trd_id` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stock_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_time` timestamp(0) NULL DEFAULT NULL,
  `order_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_amount` int(0) NULL DEFAULT NULL,
  `order_price` decimal(20, 4) NULL DEFAULT NULL,
  `deal_amount` int(0) NULL DEFAULT NULL,
  `deal_price` decimal(20, 4) NULL DEFAULT NULL,
  `is_withdraw` int(0) NULL DEFAULT NULL,
  `withdraw_amount` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES (1, 22222, 31355654, '0123456789', 'S0', 0.0010, 'B', '600446', '2024-04-19 19:19:43', '2', 100, 12.8600, 100, 12.8600, 0, 0);
INSERT INTO `order_info` VALUES (2, 33333, 11111111, 'A111111111', 'S0', 0.0010, 'S', '000858', '2024-04-19 14:57:38', '8', 1000, 136.1500, 500, 136.1600, 0, 0);

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `prime_account_id` int(0) NULL DEFAULT NULL,
  `follow_account_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stock_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `market` int(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  `share_total` int(0) NULL DEFAULT NULL,
  `share_usable` int(0) NULL DEFAULT NULL,
  `frozen_share_amount` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES (1, 31355654, '0123456789', '600446', 0, '2024-04-18 19:10:46', 1000, 1000, 0);
INSERT INTO `position` VALUES (2, 31355654, 'A123456789', '000001', 1, '2024-04-18 19:24:22', 500, 500, 0);
INSERT INTO `position` VALUES (3, 31355654, 'A123456789', '000858', 1, '2024-04-18 19:25:15', 200, 200, 0);
INSERT INTO `position` VALUES (4, 11111111, '0111111111', '600446', 0, '2024-04-18 19:32:44', 2000, 2000, 0);
INSERT INTO `position` VALUES (5, 11111111, '0111111111', '600900', 0, '2024-04-18 19:35:17', 5000, 5000, 0);
INSERT INTO `position` VALUES (6, 11111111, 'A111111111', '000858', 1, '2024-04-18 19:35:30', 2000, 2000, 0);
INSERT INTO `position` VALUES (7, 22222222, '0222222222', '600446', 0, '2024-04-18 19:35:44', 20000, 20000, 0);
INSERT INTO `position` VALUES (8, 22222222, '0222222222', '600900', 0, '2024-04-18 19:36:10', 10000, 10000, 0);
INSERT INTO `position` VALUES (9, 22222222, 'A222222222', '000858', 1, '2024-04-18 19:36:24', 10000, 10000, 0);
INSERT INTO `position` VALUES (10, 33333333, '0333333333', '600446', 0, '2024-04-18 19:36:42', 100000, 100000, 0);
INSERT INTO `position` VALUES (11, 33333333, '0333333333', '601857', 0, '2024-04-18 19:37:25', 100000, 100000, 0);
INSERT INTO `position` VALUES (12, 33333333, 'B333333333', '000651', 1, '2024-04-18 19:37:45', 100000, 100000, 0);

-- ----------------------------
-- Table structure for prime_account
-- ----------------------------
DROP TABLE IF EXISTS `prime_account`;
CREATE TABLE `prime_account`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `balance_usable` decimal(20, 4) NULL DEFAULT 100000.0000,
  `balance_total` decimal(20, 4) NULL DEFAULT 100000.0000,
  `cuacct_cls` int(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  `password` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51395746 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prime_account
-- ----------------------------
INSERT INTO `prime_account` VALUES (11111111, 10000000.0000, 10000000.0000, 1, '2024-04-26 11:29:09', 123456);
INSERT INTO `prime_account` VALUES (13579966, 10000000.0000, 10000000.0000, 1, '2024-04-26 11:29:11', 123456);
INSERT INTO `prime_account` VALUES (22222222, 10000000.0000, 10000000.0000, 2, '2024-04-26 11:29:12', 123456);
INSERT INTO `prime_account` VALUES (31355654, 10000000.0000, 10000000.0000, 3, '2024-04-26 11:29:13', 123456);
INSERT INTO `prime_account` VALUES (31767673, 2222220.0000, 2222220.0000, 3, '2024-04-25 16:53:13', 123456);
INSERT INTO `prime_account` VALUES (33333333, 10000000.0000, 10000000.0000, 3, '2024-04-26 11:28:18', 123456);
INSERT INTO `prime_account` VALUES (33337773, 2222220.0000, 2222220.0000, 3, '2024-04-25 16:53:13', 123456);
INSERT INTO `prime_account` VALUES (33737773, 2222220.0000, 2222220.0000, 3, '2024-04-25 16:53:13', 123456);
INSERT INTO `prime_account` VALUES (34737773, 2222220.0000, 2222220.0000, 3, '2024-04-25 16:53:13', 123456);
INSERT INTO `prime_account` VALUES (34777773, 2222220.0000, 2222220.0000, 3, '2024-04-25 16:53:13', 123456);
INSERT INTO `prime_account` VALUES (37767673, 2222220.0000, 2222220.0000, 3, '2024-04-25 16:53:13', 123456);
INSERT INTO `prime_account` VALUES (37777773, 2222220.0000, 2222220.0000, 3, '2024-04-25 16:53:13', 123456);
INSERT INTO `prime_account` VALUES (51395744, 100000.0000, 100000.0000, 3, '2024-04-26 11:29:24', 123456);
INSERT INTO `prime_account` VALUES (51395745, 100000.0000, 100000.0000, 3, '2024-04-26 11:29:25', 123456);

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`  (
  `id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stock_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `market` int(0) NULL DEFAULT NULL,
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
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin');

-- ----------------------------
-- Table structure for trade_unit
-- ----------------------------
DROP TABLE IF EXISTS `trade_unit`;
CREATE TABLE `trade_unit`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55556 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `prime_account_id` int(0) NULL DEFAULT NULL,
  `order_id` int(0) NULL DEFAULT NULL,
  `amount` int(0) NULL DEFAULT NULL,
  `price` decimal(20, 4) NULL DEFAULT NULL,
  `transact_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of transaction
-- ----------------------------
INSERT INTO `transaction` VALUES (1, 44444444,333333, 100, 1.5600, '2024-04-26 11:37:01');

-- ----------------------------
-- View structure for customer_bank
-- ----------------------------
DROP VIEW IF EXISTS `customer_bank`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `customer_bank` AS select `bank`.`account` AS `account`,`customer`.`id` AS `id`,`customer`.`customer_name` AS `customer_name`,`customer`.`id_type` AS `id_type`,`customer`.`id_number` AS `id_number`,`customer`.`cuacct_cls` AS `cuacct_cls`,`customer`.`cuacct_status` AS `cuacct_status`,`bank`.`bank_name` AS `bank_name` from (`bank` join `customer` on((`bank`.`customer_id` = `customer`.`id`)));

-- ----------------------------
-- View structure for customer_prime_account
-- ----------------------------
DROP VIEW IF EXISTS `customer_prime_account`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `customer_prime_account` AS select `customer`.`id` AS `id`,`customer`.`customer_name` AS `customer_name`,`customer`.`id_type` AS `id_type`,`customer`.`id_number` AS `id_number`,`customer`.`cuacct_cls` AS `cuacct_cls`,`customer`.`cuacct_status` AS `cuacct_status`,`prime_account`.`balance_usable` AS `balance_usable`,`prime_account`.`balance_total` AS `balance_total`,`prime_account`.`update_time` AS `update_time`,`prime_account`.`password` AS `password` from (`customer` join `prime_account` on(((`customer`.`id` = `prime_account`.`id`) and (`customer`.`cuacct_cls` = `prime_account`.`cuacct_cls`))));

-- ----------------------------
-- View structure for position_resp
-- ----------------------------
DROP VIEW IF EXISTS `position_resp`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `position_resp` AS select `position`.`id` AS `id`,`position`.`prime_account_id` AS `prime_account_id`,`position`.`follow_account_id` AS `follow_account_id`,`position`.`stock_id` AS `stock_id`,`position`.`market` AS `market`,`position`.`update_time` AS `update_time`,`position`.`share_total` AS `share_total`,`position`.`share_usable` AS `share_usable`,`order_info`.`order_amount` AS `frozen_share_amount`,(`order_info`.`order_amount` - `order_info`.`deal_amount`) AS `unfrozen_share_amount` from (`position` join `order_info` on(((`position`.`prime_account_id` = `order_info`.`prime_account_id`) and (`position`.`follow_account_id` = `order_info`.`follow_account_id`))));

SET FOREIGN_KEY_CHECKS = 1;
