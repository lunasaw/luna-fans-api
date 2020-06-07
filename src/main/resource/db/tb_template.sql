/*
 Navicat Premium Data Transfer

 Source Server         : luna-tencent
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 111.229.114.126:3307
 Source Schema         : luna-message

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 07/06/2020 16:29:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_template
-- ----------------------------
DROP TABLE IF EXISTS `tb_template`;
CREATE TABLE `tb_template`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL,
  `modified_time` datetime(0) NOT NULL,
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_template
-- ----------------------------
INSERT INTO `tb_template` VALUES (13, '2020-02-05 20:41:33', '2020-02-05 20:41:31', '您已重置密码', '您已重置密码，新密码为：${newPassword}，请妥善保存。');
INSERT INTO `tb_template` VALUES (14, '2020-02-05 20:41:33', '2020-02-05 20:41:31', '您的验证码', '您的验证码是：${authCode}，5分钟有效，打死也不要告诉别人哦。');

SET FOREIGN_KEY_CHECKS = 1;
