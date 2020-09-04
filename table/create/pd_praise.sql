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

 Date: 04/09/2020 16:28:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pd_praise
-- ----------------------------
DROP TABLE IF EXISTS `pd_praise`;
CREATE TABLE `pd_praise`  (
  `praise_id` int(10) NOT NULL COMMENT '点赞ID',
  `user_id` int(10) NOT NULL COMMENT '点赞用户ID',
  `blog_id` int(10) NOT NULL COMMENT '所属博客ID',
  `comment_id` int(10) NULL DEFAULT NULL COMMENT '所属评论ID',
  PRIMARY KEY (`praise_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
