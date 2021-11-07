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

 Date: 08/11/2021 00:10:20
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
  `onsw` int(0) NULL DEFAULT NULL COMMENT '设备开关,1是on,0是off',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of devices
-- ----------------------------
INSERT INTO `devices` VALUES (3, 2, '湿度1', 0, '门口', '2021-10-15 21:32:55', 1);
INSERT INTO `devices` VALUES (4, 3, 'UPS', 0, '门口', '2021-10-15 21:51:58', 1);
INSERT INTO `devices` VALUES (5, 1, '温度1', 1, '青青大草原', '2021-10-16 01:08:56', 1);
INSERT INTO `devices` VALUES (6, 1, '温度2', 1, '门口', '2021-10-16 20:19:38', 0);

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
-- Table structure for msg
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `data_time` datetime(0) NULL DEFAULT NULL,
  `is_show` int(0) NULL DEFAULT 0 COMMENT '判断此信息是否已弹过窗,1 已弹窗 0 未弹窗',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `u_msg`(`msg`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3866 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of msg
-- ----------------------------
INSERT INTO `msg` VALUES (1206, '55', '设备5\n超出预警!!!!!数据编号为2\n温度为23.0°C\n创建时间为Sat Oct 16 19:40:54 CST 2021', '2021-11-07 20:41:23', 1);
INSERT INTO `msg` VALUES (1207, '55', '设备5\n超出预警!!!!!数据编号为5\n温度为32.0°C\n创建时间为Sat Oct 16 21:38:22 CST 2021', '2021-11-07 20:41:23', 1);
INSERT INTO `msg` VALUES (1208, '55', '设备5\n超出预警!!!!!数据编号为6\n温度为16.0°C\n创建时间为Sat Oct 16 21:38:35 CST 2021', '2021-11-07 20:41:23', 1);
INSERT INTO `msg` VALUES (1209, '55', '设备5\n超出预警!!!!!数据编号为12\n温度为37.0°C\n创建时间为Wed Nov 03 21:47:46 CST 2021', '2021-11-07 20:41:23', 1);
INSERT INTO `msg` VALUES (1210, '55', '设备5\n超出预警!!!!!数据编号为13\n温度为28.0°C\n创建时间为Thu Nov 04 17:25:59 CST 2021', '2021-11-07 20:41:24', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of temp
-- ----------------------------
INSERT INTO `temp` VALUES (2, 5, 23, '2021-10-16 19:40:54');
INSERT INTO `temp` VALUES (4, 6, 18, '2021-10-16 20:19:53');
INSERT INTO `temp` VALUES (5, 5, 32, '2021-10-16 21:38:22');
INSERT INTO `temp` VALUES (6, 5, 16, '2021-10-16 21:38:35');
INSERT INTO `temp` VALUES (11, 6, -5, '2021-10-17 00:02:21');
INSERT INTO `temp` VALUES (12, 5, 37, '2021-11-03 21:47:46');
INSERT INTO `temp` VALUES (13, 5, 28, '2021-11-04 17:25:59');

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

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
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` int(0) NULL DEFAULT NULL COMMENT '角色,1为管理员,2为普通用户',
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '123', '男', 20, '翻斗小区', '17633885574', NULL, 1, NULL, NULL);
INSERT INTO `user` VALUES (7, '李四', '123', '男', 18, '青青大草原', '保密', 'http://q2.qlogo.cn/headimg_dl?dst_uin=718566891&spec=640', 1, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwidXNlcm5hbWUiOiLmnY7lm5siLCJwYXNzd29yZCI6IjEyMyIsImV4cCI6MTYzNjI5NzQzOCwianRpIjoiMDExMGRlYTYtNWRiMy00NThlLWJiMmQtOWJhYzE5ZGZjMTc1In0.flrSjnkaA00R95qsLkv-7MIFyE2E0r6Acc7Kwkihc-0', '718566891@qq.com');
INSERT INTO `user` VALUES (10, 'libai', '123', '男', 22, '22', '11', 'http://localhost:8181/file/download/ac7cf35f5d2a4a2ba196bae107c987eb', 2, NULL, NULL);
INSERT INTO `user` VALUES (11, '22', '22', '保密', 18, '青青大草原', '保密', 'http://localhost:8181/file/download/1a659ca09fc24d269282e868d88ad34f', 1, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwidXNlcm5hbWUiOiIyMiIsInBhc3N3b3JkIjoiMjIiLCJleHAiOjE2MzYzMDEzODAsImp0aSI6IjYxN2U2ZGM5LTg0ODMtNDFiYy04MjNlLWZlNGM5OWMwNmM4MSJ9.YaKYgKMOZWIuZd9qgGOHyLGROwd0S2mDsH724yCSDtQ', '718566891@qq.com');
INSERT INTO `user` VALUES (12, '55', '55', '保密', 18, '青青大草原', '保密', 'http://q2.qlogo.cn/headimg_dl?dst_uin=718566892&spec=640', 2, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwidXNlcm5hbWUiOiI1NSIsInBhc3N3b3JkIjoiNTUiLCJleHAiOjE2MzYyOTc3ODAsImp0aSI6IjhjOGYyZmJmLTUyODktNDkwMC05NTQwLWUzZWFhODQ3ZjNmZSJ9.alCtDL8BO1r7D3tOzH_qtFXx4l_OY6JHSpumKnEorPQ', '718566892@qq.com');
INSERT INTO `user` VALUES (15, '88', '88', '保密', 18, '青青大草原', '保密', 'http://q2.qlogo.cn/headimg_dl?dst_uin=718566895&spec=640', 2, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwidXNlcm5hbWUiOiI4OCIsInBhc3N3b3JkIjoiODgiLCJleHAiOjE2MzYyOTg2NTcsImp0aSI6ImRiZjIyMWNlLWRmZmQtNGRkOC1hODQ2LWZiMWMwNjBmYzI3MSJ9.uOfmxBb_xYrmwjCSFeZSiDxTKuh6H5P-hGFmSxSIyNA', '718566895@qq.com');
INSERT INTO `user` VALUES (16, 'rt', NULL, '男', 11, '11', '1111', 'http://localhost:8181/file/download/aa2dff1d636f479fa66d14d71b8d7529', 2, NULL, NULL);

-- ----------------------------
-- Table structure for warning
-- ----------------------------
DROP TABLE IF EXISTS `warning`;
CREATE TABLE `warning`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '预警ID',
  `did` int(0) NULL DEFAULT NULL COMMENT '设备ID',
  `min` int(0) NULL DEFAULT NULL COMMENT '取值范围最小值',
  `max` int(0) NULL DEFAULT NULL COMMENT '取值范围最大值',
  `type` int(0) NULL DEFAULT NULL COMMENT '设备类型',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warning
-- ----------------------------
INSERT INTO `warning` VALUES (10, 5, 10, 15, 1, '2021-11-04 17:25:13');

SET FOREIGN_KEY_CHECKS = 1;
