/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : localhost:3306
 Source Schema         : private_diary

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 03/08/2020 18:24:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pd_diary
-- ----------------------------
DROP TABLE IF EXISTS `pd_diary`;
CREATE TABLE `pd_diary`  (
  `user_id` int(10) NOT NULL COMMENT '日记所属用户id',
  `diary_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '日记唯一标识',
  `diary_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日记标题',
  `diary_weather` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '当天天气',
  `diary_mood` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '当天心情',
  `diary_event` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '当天主要事件',
  `diary_location` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当天日记所在位置',
  `diary_create_time` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日记创建时间',
  `diary_update_time` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日记最后更新时间',
  `detail_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日记详情',
  `detail_photo` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日记封面',
  PRIMARY KEY (`diary_id`) USING BTREE,
  INDEX `diary_user_id`(`user_id`) USING BTREE,
  INDEX `diary_id`(`diary_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
