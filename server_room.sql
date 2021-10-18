/*
 Navicat Premium Data Transfer

 Source Server         : MySQL8
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : server_room

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 18/10/2021 17:02:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for devices
-- ----------------------------
DROP TABLE IF EXISTS `devices`;
CREATE TABLE `devices`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type` int(0) NULL DEFAULT NULL COMMENT '设备类型',
  `dname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '设备名称',
  `status` int(0) NULL DEFAULT NULL COMMENT '设备状态,1是ok,0是error',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '设备地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '设备添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of devices
-- ----------------------------
INSERT INTO `devices` VALUES (3, 2, '湿度1', 0, '门口', '2021-10-15 21:32:55');
INSERT INTO `devices` VALUES (4, 3, 'UPS', 0, '门口', '2021-10-15 21:51:58');
INSERT INTO `devices` VALUES (5, 1, '温度1', 1, '青青大草原', '2021-10-16 01:08:56');
INSERT INTO `devices` VALUES (6, 1, '温度2', 1, '门口', '2021-10-16 20:19:38');

-- ----------------------------
-- Table structure for mois
-- ----------------------------
DROP TABLE IF EXISTS `mois`;
CREATE TABLE `mois`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `did` int(0) NULL DEFAULT NULL,
  `shi_data` double(255, 0) NULL DEFAULT NULL,
  `creat_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mois
-- ----------------------------
INSERT INTO `mois` VALUES (1, 3, 15, '2021-10-17 00:18:24');
INSERT INTO `mois` VALUES (2, 3, 54, '2021-10-17 00:18:53');
INSERT INTO `mois` VALUES (3, 3, 12, '2021-10-18 16:25:24');

-- ----------------------------
-- Table structure for temp
-- ----------------------------
DROP TABLE IF EXISTS `temp`;
CREATE TABLE `temp`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `did` int(0) NULL DEFAULT NULL,
  `wen_data` double(255, 0) NULL DEFAULT NULL,
  `creat_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of temp
-- ----------------------------
INSERT INTO `temp` VALUES (2, 5, 23, '2021-10-16 19:40:54');
INSERT INTO `temp` VALUES (3, 5, 11, '2021-10-16 20:18:59');
INSERT INTO `temp` VALUES (4, 6, 18, '2021-10-16 20:19:53');
INSERT INTO `temp` VALUES (5, 5, 32, '2021-10-16 21:38:22');
INSERT INTO `temp` VALUES (6, 5, 16, '2021-10-16 21:38:35');
INSERT INTO `temp` VALUES (11, 6, -5, '2021-10-17 00:02:21');

-- ----------------------------
-- Table structure for ups
-- ----------------------------
DROP TABLE IF EXISTS `ups`;
CREATE TABLE `ups`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `did` int(0) NULL DEFAULT NULL,
  `vol` double(255, 0) NULL DEFAULT NULL COMMENT '电压',
  `elecurrent` double(255, 0) NULL DEFAULT NULL COMMENT '电流',
  `creat_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ups
-- ----------------------------
INSERT INTO `ups` VALUES (2, 4, 110, 30, '2021-10-16 23:05:22');
INSERT INTO `ups` VALUES (3, 4, 180, 18, '2021-10-16 23:24:33');
INSERT INTO `ups` VALUES (4, 4, 232, 43, '2021-10-17 00:03:44');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `passwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` int(0) NULL DEFAULT NULL COMMENT '角色,1为管理员,2为普通用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '123', '男', 20, '翻斗小区', '17633885574', NULL, 1);
INSERT INTO `user` VALUES (6, '秦风戈', '123', '男', 21, 'king street', '8013827187', 'http://localhost:8181/file/download/a26f134b2d2d4b908ec01499cce50e9f', 1);
INSERT INTO `user` VALUES (7, '李四', '123', '男', 18, '青青大草原', '保密', 'http://localhost:8181/file/download/3d260b89edf041d0a967fb6beb7cb80c', 1);
INSERT INTO `user` VALUES (10, 'libai', '123', '男', 22, '22', '11', 'http://localhost:8181/file/download/ac7cf35f5d2a4a2ba196bae107c987eb', 2);
INSERT INTO `user` VALUES (11, '22', '22', '保密', 18, '青青大草原', '保密', 'http://localhost:8181/file/download/defaultPic.jpeg', 2);

SET FOREIGN_KEY_CHECKS = 1;
