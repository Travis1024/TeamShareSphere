/*
 Navicat Premium Data Transfer

 Source Server         : Lab-Mysql
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : 120.27.245.254:17007
 Source Schema         : tss_team

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 30/04/2024 17:52:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for team_department
-- ----------------------------
DROP TABLE IF EXISTS `team_department`;
CREATE TABLE `team_department` (
  `id` bigint unsigned NOT NULL COMMENT 'ID',
  `name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `description` varchar(1024) COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门简述',
  `manager_id` bigint unsigned NOT NULL COMMENT '部门管理者ID',
  `manager_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门管理者名字',
  `manager_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门管理者手机号码',
  `manager_email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门管理者邮箱',
  `enterprise_id` bigint unsigned NOT NULL COMMENT '所属企业ID',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `updater` bigint unsigned DEFAULT NULL COMMENT '更新者',
  `creator` bigint unsigned DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of team_department
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for team_enterprise
-- ----------------------------
DROP TABLE IF EXISTS `team_enterprise`;
CREATE TABLE `team_enterprise` (
  `id` bigint unsigned NOT NULL COMMENT 'ID',
  `name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '企业名称',
  `description` varchar(1024) COLLATE utf8mb4_general_ci NOT NULL COMMENT '企业简述',
  `manager_id` bigint unsigned NOT NULL COMMENT '企业管理者ID',
  `manager_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '企业管理者名字',
  `manager_phone` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '企业管理者联系电话',
  `manager_email` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '企业管理者邮箱地址',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `updater` bigint unsigned DEFAULT NULL COMMENT '更新者',
  `creator` bigint unsigned DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of team_enterprise
-- ----------------------------
BEGIN;
INSERT INTO `team_enterprise` (`id`, `name`, `description`, `manager_id`, `manager_name`, `manager_phone`, `manager_email`, `is_deleted`, `updater`, `creator`, `update_time`, `create_time`) VALUES (12412412, '111', '11', 1111, '1111', '1111', '1111', 0, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for team_group
-- ----------------------------
DROP TABLE IF EXISTS `team_group`;
CREATE TABLE `team_group` (
  `id` bigint unsigned NOT NULL COMMENT 'ID',
  `name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '团队名称',
  `description` varchar(1024) COLLATE utf8mb4_general_ci NOT NULL COMMENT '团队简述',
  `manager_id` bigint unsigned NOT NULL COMMENT '团队管理者ID',
  `manager_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '团队管理者名字',
  `manager_phone` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '团队管理者手机号码',
  `manager_email` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '团队管理者邮箱',
  `type` tinyint unsigned NOT NULL COMMENT '团队类型（0-部门内部、1-外部）',
  `enterprise_id` bigint unsigned NOT NULL COMMENT '团队所属企业ID',
  `department_id` bigint unsigned DEFAULT NULL COMMENT '团队所属部门ID（内部必填、外部为空）',
  `is_deleted` tinyint unsigned NOT NULL COMMENT '逻辑删除',
  `updater` bigint unsigned DEFAULT NULL COMMENT '更新者',
  `creator` bigint unsigned DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of team_group
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for team_user
-- ----------------------------
DROP TABLE IF EXISTS `team_user`;
CREATE TABLE `team_user` (
  `id` bigint unsigned NOT NULL COMMENT 'ID',
  `username` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录用户名',
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码',
  `phone` varchar(16) COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号码',
  `email` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `birthday` datetime NOT NULL COMMENT '出生日期',
  `real_name` varchar(16) COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名',
  `icon` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像URL',
  `gender` tinyint unsigned NOT NULL COMMENT '性别：0-男性，1-女性',
  `province` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '通讯地址：省份',
  `city` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '通讯地址：城市',
  `district` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '通讯地址：区/县',
  `emergency_contact_name` varchar(16) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '紧急联系人名字',
  `emergency_contact_phone` varchar(16) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '紧急联系人手机号码',
  `is_deleted` bigint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `updater` bigint unsigned DEFAULT NULL COMMENT '更新者',
  `creator` bigint unsigned DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of team_user
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for team_user_department
-- ----------------------------
DROP TABLE IF EXISTS `team_user_department`;
CREATE TABLE `team_user_department` (
  `id` bigint unsigned NOT NULL COMMENT 'ID',
  `user_id` bigint unsigned NOT NULL COMMENT '用户ID',
  `department_id` bigint unsigned NOT NULL COMMENT '部门ID',
  `role` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '角色（0-普通员工、1-部门管理员）',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `updater` bigint unsigned DEFAULT NULL COMMENT '更新者',
  `creator` bigint unsigned DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of team_user_department
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for team_user_enterprise
-- ----------------------------
DROP TABLE IF EXISTS `team_user_enterprise`;
CREATE TABLE `team_user_enterprise` (
  `id` bigint unsigned NOT NULL COMMENT 'ID',
  `user_id` bigint unsigned NOT NULL COMMENT '用户ID',
  `enterprise_id` bigint unsigned NOT NULL COMMENT '企业ID',
  `employee_number` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '员工号',
  `role` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '角色（0-普通员工、1-企业管理员）',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `updater` bigint unsigned DEFAULT NULL COMMENT '更新者',
  `creator` bigint unsigned DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of team_user_enterprise
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for team_user_group
-- ----------------------------
DROP TABLE IF EXISTS `team_user_group`;
CREATE TABLE `team_user_group` (
  `id` bigint unsigned NOT NULL COMMENT 'ID',
  `user_id` bigint unsigned NOT NULL COMMENT '用户ID',
  `group_id` bigint unsigned NOT NULL COMMENT '团队ID',
  `role` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '角色（0-普通员工、1-团队管理员）',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `updater` bigint unsigned DEFAULT NULL COMMENT '更新者',
  `creator` bigint unsigned DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of team_user_group
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(128) NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`),
  KEY `ix_log_created` (`log_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AT transaction mode undo table';

-- ----------------------------
-- Records of undo_log
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
