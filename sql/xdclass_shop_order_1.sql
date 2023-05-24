/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80020 (8.0.20)
 Source Host           : localhost:3306
 Source Schema         : xdclass_shop_order_1

 Target Server Type    : MySQL
 Target Server Version : 80020 (8.0.20)
 File Encoding         : 65001

 Date: 24/05/2023 16:45:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ad_config
-- ----------------------------
DROP TABLE IF EXISTS `ad_config`;
CREATE TABLE `ad_config`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '主键id',
  `config_key` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '配置key',
  `config_value` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '配置value',
  `type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product_order_0
-- ----------------------------
DROP TABLE IF EXISTS `product_order_0`;
CREATE TABLE `product_order_0`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `out_trade_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '订单唯一标识',
  `state` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'NEW 未支付订单,PAY已经支付订单,CANCEL超时取消订单',
  `create_time` datetime NULL DEFAULT NULL COMMENT '订单生成时间',
  `pay_amount` decimal(16, 2) NULL DEFAULT NULL COMMENT '订单实际支付价格',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '昵称',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1661275008572030979 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product_order_1
-- ----------------------------
DROP TABLE IF EXISTS `product_order_1`;
CREATE TABLE `product_order_1`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `out_trade_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '订单唯一标识',
  `state` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'NEW 未支付订单,PAY已经支付订单,CANCEL超时取消订单',
  `create_time` datetime NULL DEFAULT NULL COMMENT '订单生成时间',
  `pay_amount` decimal(16, 2) NULL DEFAULT NULL COMMENT '订单实际支付价格',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '昵称',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1661275008693665796 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product_order_item_0
-- ----------------------------
DROP TABLE IF EXISTS `product_order_item_0`;
CREATE TABLE `product_order_item_0`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_order_id` bigint NULL DEFAULT NULL COMMENT '订单号',
  `product_id` bigint NULL DEFAULT NULL COMMENT '产品id',
  `product_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '商品名称',
  `buy_num` int NULL DEFAULT NULL COMMENT '购买数量',
  `user_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product_order_item_1
-- ----------------------------
DROP TABLE IF EXISTS `product_order_item_1`;
CREATE TABLE `product_order_item_1`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_order_id` bigint NULL DEFAULT NULL COMMENT '订单号',
  `product_id` bigint NULL DEFAULT NULL COMMENT '产品id',
  `product_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '商品名称',
  `buy_num` int NULL DEFAULT NULL COMMENT '购买数量',
  `user_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
