/*
 Navicat Premium Data Transfer

 Source Server         : 百度云数据库
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 180.76.58.205:3306
 Source Schema         : private_diary

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 04/09/2020 16:28:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pd_user
-- ----------------------------
DROP TABLE IF EXISTS `pd_user`;
CREATE TABLE `pd_user`  (
  `user_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `user_birthday` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户出生日期',
  `user_sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户性别',
  `user_password` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录密码',
  `user_state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户状态',
  `user_phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `user_email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `user_head` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像图片存储地址',
  `user_create_time` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户创建时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
