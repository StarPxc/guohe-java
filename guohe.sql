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

 Date: 01/11/2018 12:51:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(8) COLLATE utf8mb4_bin DEFAULT NULL,
  `course_num` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '课程号',
  `course_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `weeks` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '这门课要上的周次\n',
  `week_num` int(2) DEFAULT NULL COMMENT '星期几的课\n',
  `semester` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '学年',
  `student_id` int(11) DEFAULT NULL COMMENT '学生id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` int(11) NOT NULL,
  `course_name` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '课程名',
  `score` int(8) DEFAULT NULL,
  `credit` int(2) DEFAULT NULL COMMENT '学分',
  `course_num` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '课程号',
  `course_attribute` varchar(4) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '课程属性',
  `course_nature` varchar(4) COLLATE utf8mb4_bin DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL COMMENT '序号',
  `start_semester` varchar(8) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '学年',
  `total_hours` varchar(0) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '总学时',
  `examination_method` varchar(8) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '考核方式',
  `student_id` int(11) DEFAULT NULL,
  `mark_of_score` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL,
  `alternative_course_name` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL,
  `alternative_course_number` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `birthday` varchar(36) DEFAULT NULL,
  `major` varchar(100) DEFAULT NULL COMMENT '专业',
  `academy` varchar(30) DEFAULT NULL COMMENT '学院',
  `class_num` varchar(255) DEFAULT NULL COMMENT '班级号',
  `organization` varchar(255) DEFAULT NULL COMMENT '组织',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (17, '152210702119', '935377012pxc', ' 浦希成', ' 19970412', '软件工程(嵌入式培养)(卓越)', '计算机学院', '1522107021', NULL, '2018-11-01 10:55:26', '2018-11-01 10:55:26');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `nickname` varchar(32) COLLATE utf8mb4_bin DEFAULT '大学僧',
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (13, '152210702119', '935377012pxc', '大学僧', NULL, NULL, NULL, NULL, 'student', 'guest', 17, NULL, NULL, NULL, '2018-11-01T10:55:26.200', '2018-11-01 10:55:26', '2018-11-01 10:55:26');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
