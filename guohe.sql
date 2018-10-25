/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : guohe

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 25/10/2018 21:12:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `major` varchar(100) DEFAULT NULL COMMENT '专业',
  `academy` varchar(30) DEFAULT NULL COMMENT '学院',
  `class_num` varchar(255) DEFAULT NULL COMMENT '班级号',
  `organization` varchar(255) DEFAULT NULL COMMENT '组织',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `nickname` varchar(32) COLLATE utf8mb4_bin DEFAULT '大学僧',
  `birthday` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'yyyy-mm-dd',
  `open_id_qq` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL,
  `open_id_wx` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL,
  `head_pic_qq` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `head_pic_wx` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `role` varchar(16) COLLATE utf8mb4_bin DEFAULT 'student' COMMENT '角色',
  `authority` varchar(32) COLLATE utf8mb4_bin DEFAULT 'guest' COMMENT '权限',
  `detail_info_id` int(255) DEFAULT NULL COMMENT '关联用户详情',
  `origin` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录来源',
  `native_place` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '籍贯',
  `phone` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL,
  `last_login` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '最后一次登录时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;
