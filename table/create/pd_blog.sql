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

 Date: 04/09/2020 16:29:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pd_blog
-- ----------------------------
DROP TABLE IF EXISTS `pd_blog`;
CREATE TABLE `pd_blog`  (
  `blog_id` int(10) NOT NULL COMMENT '博客ID',
  `user_id` int(10) NOT NULL COMMENT '博客所属用户ID',
  `blog_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客类型（一句话/小故事）',
  `blog_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '博客图片',
  `blog_context` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客具体内容',
  `blog_label` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客标签',
  `blog_create_time` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客创建时间',
  `blog_update_time` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客更新时间',
  PRIMARY KEY (`blog_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
