/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : 127.0.0.1:3306
 Source Schema         : private_diary

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 26/07/2020 15:27:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pd_user
-- ----------------------------
DROP TABLE IF EXISTS `pd_user`;
CREATE TABLE `pd_user`  (
  `user_id` int(10) NOT NULL COMMENT '用户唯一标识',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `user_password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录密码',
  `user_state` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '3' COMMENT '用户状态',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
