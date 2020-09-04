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

 Date: 04/09/2020 16:29:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pd_friend
-- ----------------------------
DROP TABLE IF EXISTS `pd_friend`;
CREATE TABLE `pd_friend`  (
  `my_user_id` int(10) NOT NULL COMMENT '我的用户ID',
  `my_email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '我的邮箱',
  `my_phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '我的手机号码',
  `friend_user_id` int(10) NOT NULL COMMENT '好友的用户ID',
  `friend_email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '好友的邮箱',
  `friend_phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '好友的手机号码',
  `friend_remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '好友的备注',
  PRIMARY KEY (`my_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
