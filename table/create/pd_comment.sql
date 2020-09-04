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

 Date: 04/09/2020 16:29:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pd_comment
-- ----------------------------
DROP TABLE IF EXISTS `pd_comment`;
CREATE TABLE `pd_comment`  (
  `comment_id` int(10) NOT NULL COMMENT '评论ID',
  `blog_id` int(10) NOT NULL COMMENT '评论所属博客ID',
  `comment_user_id` int(10) NOT NULL COMMENT '评论人ID',
  `blog_owner_id` int(10) NOT NULL COMMENT '博客所属人ID',
  `comment_create_time` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论时间',
  `commented_user_id` int(10) NOT NULL COMMENT '被评论者ID',
  `comment_context` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
