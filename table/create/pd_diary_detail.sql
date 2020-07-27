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

 Date: 26/07/2020 15:27:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pd_diary_detail
-- ----------------------------
DROP TABLE IF EXISTS `pd_diary_detail`;
CREATE TABLE `pd_diary_detail`  (
  `diary_id` int(10) NOT NULL COMMENT '详情对应的日记的id',
  `detail_id` int(15) NOT NULL COMMENT '日志id',
  `detail_content` blob NULL COMMENT '日志详情',
  `detail_photo` blob NULL COMMENT '日志封面',
  PRIMARY KEY (`diary_id`, `detail_id`) USING BTREE,
  CONSTRAINT `detail_diary_id` FOREIGN KEY (`diary_id`) REFERENCES `pd_diary` (`diary_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
