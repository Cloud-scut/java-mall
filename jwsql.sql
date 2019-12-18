/*
Navicat MySQL Data Transfer

Source Server         : javaweb
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : jwsql

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2019-12-17 20:52:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`password`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`username`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3

;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
BEGIN;
INSERT INTO `admin_user` VALUES ('1', 'admin', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for classification
-- ----------------------------
DROP TABLE IF EXISTS `classification`;
CREATE TABLE `classification` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`cname`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`parent_id`  int(11) NULL DEFAULT NULL ,
`type`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=12

;

-- ----------------------------
-- Records of classification
-- ----------------------------
BEGIN;
INSERT INTO `classification` VALUES ('2', '电脑', null, '1'), ('3', '手机', null, '1'), ('4', '食品', null, '1'), ('5', '笔记本', '2', '2'), ('6', '平板', '2', '2'), ('7', '台式机', '2', '2'), ('10', '饮料', '4', '2'), ('11', '智能手机', '3', '2');
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`addr`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`order_time`  datetime NULL DEFAULT NULL ,
`phone`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`state`  int(11) NULL DEFAULT NULL ,
`total`  double NULL DEFAULT NULL ,
`user_id`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=6

;

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`count`  int(11) NULL DEFAULT NULL ,
`order_id`  int(11) NULL DEFAULT NULL ,
`product_id`  int(11) NULL DEFAULT NULL ,
`sub_total`  double NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=7

;

-- ----------------------------
-- Records of order_item
-- ----------------------------
BEGIN;
INSERT INTO `order_item` VALUES ('1', '1', '1', '10', '8888'), ('2', '2', '2', '9', '17998'), ('3', '2', '3', '11', '78'), ('4', '1', '3', '13', '5999'), ('5', '1', '4', '9', '8999'), ('6', '1', '5', '13', '5999');
COMMIT;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`csid`  int(11) NULL DEFAULT NULL ,
`desc`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`image`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`is_hot`  int(11) NULL DEFAULT NULL ,
`market_price`  double NULL DEFAULT NULL ,
`pdate`  datetime NULL DEFAULT NULL ,
`shop_price`  bigint(20) NULL DEFAULT NULL ,
`title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=18

;

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES ('9', '5', '外星人Alienware m15 15.6英寸英特尔酷睿i7眼球追踪轻薄游戏笔记本电脑(i7-9750H16G 512G GTX1660Ti 144Hz)', '/mall/admin/product/img/alien.jpg', '1', '19999', '2017-11-25 00:37:57', '15999', '外星人Alienware m15 15.6英寸英特尔酷睿i7眼球追踪轻薄游戏笔记本电脑'), ('10', '11', 'Apple iPhone XR (A2108) 128GB 黑色 移动联通电信4G手机 双卡双待', '/mall/admin/product/img/E98ECEAC9E68BE31BB623419FD0C9E.png', '1', '9999', '2017-11-24 22:17:54', '8888', 'Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机'), ('14', '1', 'air max跑鞋，真的很不错', '/mall/admin/product/img/airmax.jpg', '1', '1999', null, '1899', 'AIR MAX97'), ('15', '11', '华为 HUAWEI P30 超感光徕卡三摄麒麟980AI智能芯片全面屏屏内指纹版手机', '/mall/admin/product/img/P30.jfif', '1', '3888', null, '3388', '华为P30'), ('16', '10', '可口可乐（Coca-Cola）【日本直邮】2019新款Coca-Cola Energy 限定可乐 可口可乐 160ml×30瓶/箱', '/mall/admin/product/img/coke.jpg', '1', '888', null, '588', '可口可乐'), ('17', '9', 'JBL L830音响 音箱 家庭影院 电视音响 落地影院 组合音响 客厅影院 音响套装 HIFI', '/mall/admin/product/img/jbl.jpg', '1', '4988', null, '4888', 'JBL音响');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`addr`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`email`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`password`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`phone`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`username`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=6

;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('4', 'china', 'lch@163.com', 'lch', '123456', '12345678910', 'lch'), ('5', 'China', '765339408@qq.com', 'licenhao', '123456', '12345678910', '201730613359');
COMMIT;

-- ----------------------------
-- Auto increment value for admin_user
-- ----------------------------
ALTER TABLE `admin_user` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for classification
-- ----------------------------
ALTER TABLE `classification` AUTO_INCREMENT=12;

-- ----------------------------
-- Auto increment value for order
-- ----------------------------
ALTER TABLE `order` AUTO_INCREMENT=6;

-- ----------------------------
-- Auto increment value for order_item
-- ----------------------------
ALTER TABLE `order_item` AUTO_INCREMENT=7;

-- ----------------------------
-- Auto increment value for product
-- ----------------------------
ALTER TABLE `product` AUTO_INCREMENT=18;

-- ----------------------------
-- Auto increment value for user
-- ----------------------------
ALTER TABLE `user` AUTO_INCREMENT=6;
