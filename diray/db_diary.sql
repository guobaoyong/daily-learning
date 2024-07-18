/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : db_diary

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 04/11/2018 15:31:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_diary
-- ----------------------------
DROP TABLE IF EXISTS `t_diary`;
CREATE TABLE `t_diary`  (
  `diaryId` int(11) NOT NULL AUTO_INCREMENT COMMENT '日记ID',
  `title` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日记标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '日记内容',
  `typeId` int(11) NULL DEFAULT NULL COMMENT '类别ID',
  `releaseDate` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`diaryId`) USING BTREE,
  INDEX `FK_t_diary`(`typeId`) USING BTREE,
  CONSTRAINT `FK_t_diary` FOREIGN KEY (`typeId`) REFERENCES `t_diarytype` (`diaryTypeId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_diary
-- ----------------------------
INSERT INTO `t_diary` VALUES (3, '第一条日志', '<p>测试</p>\r\n', 4, '2018-11-04 14:39:29');
INSERT INTO `t_diary` VALUES (4, '第二条日志', '<p>测</p>\r\n', 4, '2018-11-04 14:40:11');

-- ----------------------------
-- Table structure for t_diarytype
-- ----------------------------
DROP TABLE IF EXISTS `t_diarytype`;
CREATE TABLE `t_diarytype`  (
  `diaryTypeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别ID',
  `typeName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别名称',
  PRIMARY KEY (`diaryTypeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_diarytype
-- ----------------------------
INSERT INTO `t_diarytype` VALUES (4, '测试类别');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `imageName` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `mood` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '心情',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '0', 'z80ghJXVZe9m59/5+Ydk2g==', '菜鸟小生', '20181104015747.jpg', '单次测试				\r\n									\r\n									\r\n									\r\n							');

SET FOREIGN_KEY_CHECKS = 1;
