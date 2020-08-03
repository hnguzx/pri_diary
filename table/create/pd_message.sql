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

 Date: 03/08/2020 18:23:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pd_message
-- ----------------------------
DROP TABLE IF EXISTS `pd_message`;
CREATE TABLE `pd_message`  (
  `msg_send_user` int(10) NOT NULL COMMENT '发送用户',
  `msg_receive` int(10) NOT NULL COMMENT '接收用户',
  `msg_create_time` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发送时间',
  `msg_is_readed` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接收者是否查看',
  `msg_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息内容（图片，音频，文件2.0处理）'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
