# trashCan
垃圾箱管理系统

垃圾箱显示界面（广告招商）-未完成
微信号自动登陆-未完成

创建数据库：
CREATE DATABASE trashcan;
sql脚本：
/*
 Navicat Premium Data Transfer

 Source Server         : localhost-root
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : trashcan

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 06/09/2018 00:25:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `logId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Type` int(2) NULL DEFAULT NULL,
  `RequestIp` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ExceptionCode` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ExceptionDetail` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Params` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT NULL,
  `crtOptr` int(11) NULL DEFAULT NULL COMMENT '新增用户',
  `crtTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增日期',
  PRIMARY KEY (`logId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1519 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menuId` int(11) NOT NULL AUTO_INCREMENT,
  `pmenuId` int(11) NULL DEFAULT NULL,
  `menuName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menuDesc` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menuIcon` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menuAction` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT NULL,
  `crtOptr` int(11) NULL DEFAULT NULL COMMENT '新增用户',
  `crtTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增日期',
  `modOptr` int(11) NULL DEFAULT NULL COMMENT '修改用户',
  `modTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menuId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', '系统管理', 'icon-settings', '', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (2, 1, '用户管理', '用户管理', 'icon-users', '/sysUserController/userIndex', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (3, 2, '用户管理-新增', '用户管理-新增', 'insertBtn', '/sysUserController/userAdd', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (4, 2, '用户管理-修改', '用户管理-修改', 'updateBtn', '/sysUserController/userAmd', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (5, 2, '用户管理-删除', '用户管理-删除', 'deleteBtn', '/sysUserController/userDel', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (6, 2, '用户管理-初始化', '用户管理-初始化', 'initializeBtn', '/sysUserController/userPas', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (7, 1, '角色管理', '角色管理', 'icon-grid', '/sysRoleController/roleIndex', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (8, 7, '角色管理-新增', '角色管理-新增', 'insertBtn', '/sysRoleController/roleAdd', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (9, 7, '角色管理-修改', '角色管理-修改', 'updateBtn', '/sysRoleController/roleAmd', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (10, 7, '角色管理-删除', '角色管理-删除', 'deleteBtn', '/sysRoleController/roleDel', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11, 1, '机构管理', '机构管理', 'icon-share', '/sysOrgController/orgIndex', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (12, 11, '机构管理-新增', '机构管理-新增', 'insertBtn', '/sysOrgController/orgAdd', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (13, 11, '机构管理-修改', '机构管理-修改', 'updateBtn', '/sysOrgController/orgAmd', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (14, 11, '机构管理-删除', '机构管理-删除', 'deleteBtn', '/sysOrgController/orgDel', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (15, 11, '机构管理-成员查询', '机构管理-成员查询', 'searchBtn', '/sysOrgController/userSearch', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (16, 11, '机构管理-成员移入', '机构管理-成员移入', 'inBtn', '/sysOrgController/userIn', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (17, 11, '机构管理-成员移出', '机构管理-成员移出', 'outBtn', '/sysOrgController/userOut', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (18, 1, '日志管理', '日志管理', 'icon-calendar', '/sysLogController/logIndex', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (19, 0, '运维管理', '运维管理', 'icon-trash', ' ', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (20, 19, '类型管理', '类型管理', 'icon-wrench', '/trashcantypeController/typeIndex', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (21, 20, '类型管理-新增', '类型管理-新增', 'insertBtn', '/trashcantypeController/typeAdd', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (22, 20, '类型管理-修改', '类型管理-修改', 'updateBtn', '/trashcantypeController/typeAmd', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (23, 20, '类型管理-删除', '类型管理-删除', 'deleteBtn', '/trashcantypeController/typeDel', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (24, 19, '垃圾桶管理', '垃圾桶管理', 'icon-pointer', '/trashcanmasterController/trashCanIndex', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (25, 24, '垃圾桶管理-新增', '垃圾桶管理-新增', 'insertBtn', '/trashcanmasterController/trashCanAdd', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (26, 24, '垃圾桶管理-修改', '垃圾桶管理-修改', 'updateBtn', '/trashcanmasterController/trashCanAmd', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (27, 24, '垃圾桶管理-删除', '垃圾桶管理-删除', 'deleteBtn', '/trashcanmasterController/trashCanDel', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `orgId` int(11) NOT NULL AUTO_INCREMENT,
  `porgId` int(11) NULL DEFAULT NULL,
  `orgCode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgName` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgDesc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT NULL,
  `crtOptr` int(11) NULL DEFAULT NULL COMMENT '新增用户',
  `crtTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增日期',
  `modOptr` int(11) NULL DEFAULT NULL COMMENT '修改用户',
  `modTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`orgId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES (1, 0, '914403003600521031', '忻铭信息科技(上海)有限公司', '忻铭信息科技(上海)有限公司', 1, 1, '2017-06-25 13:22:51', 1, '2018-08-21 15:57:12');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `roleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `roleName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `roleDesc` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `crtOptr` int(11) NULL DEFAULT NULL COMMENT '新增用户',
  `crtTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增日期',
  `modOptr` int(11) NULL DEFAULT NULL COMMENT '修改用户',
  `modTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', '系统管理员', 1, NULL, NULL, 1, '2018-08-22 20:18:15');
INSERT INTO `sys_role` VALUES (2, '运维管理员', '运维管理员', 1, 1, '2017-06-02 22:32:29', 1, '2018-08-22 20:18:31');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `roleMenuId` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NULL DEFAULT NULL,
  `menuId` int(11) NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT NULL,
  `crtOptr` int(11) NULL DEFAULT NULL COMMENT '新增用户',
  `crtTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增日期',
  `modOptr` int(11) NULL DEFAULT NULL COMMENT '修改用户',
  `modTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`roleMenuId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1, 1, 1, 1, '2018-08-22 20:18:15', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (2, 1, 2, 1, 1, '2018-08-22 20:18:15', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (3, 1, 3, 1, 1, '2018-08-22 20:18:15', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (4, 1, 4, 1, 1, '2018-08-22 20:18:15', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (5, 1, 5, 1, 1, '2018-08-22 20:18:15', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (6, 1, 6, 1, 1, '2018-08-22 20:18:15', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (7, 1, 7, 1, 1, '2018-08-22 20:18:15', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (8, 1, 8, 1, 1, '2018-08-22 20:18:15', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (9, 1, 9, 1, 1, '2018-08-22 20:18:15', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (10, 1, 10, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (11, 1, 11, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (12, 1, 12, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (13, 1, 13, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (14, 1, 14, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (15, 1, 15, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (16, 1, 16, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (17, 1, 17, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (18, 1, 18, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (19, 1, 19, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (20, 1, 20, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (21, 1, 21, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (22, 1, 22, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (23, 1, 23, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (24, 1, 24, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (25, 1, 25, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (26, 1, 26, 1, 1, '2018-08-22 20:18:16', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (27, 1, 27, 1, 1, '2018-08-22 20:18:16', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `loginName` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `passwd` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `userCode` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工号',
  `userName` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `mobileNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `eMail` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `picPath` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `roleId` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `crtOptr` int(11) NULL DEFAULT NULL COMMENT '新增用户',
  `crtTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增日期',
  `modOptr` int(11) NULL DEFAULT NULL COMMENT '修改用户',
  `modTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '96e79218965eb72c92a549dd5a330112', '0001', '陈印', '男', '18202128391', 'chenyin@sfinace.com.cn', '上海市浦东新区', 'Upload\\userImg\\1\\userPic.jpeg', 1, 1, NULL, NULL, 1, '2018-08-22 21:13:54');
INSERT INTO `sys_user` VALUES (2, 'demo', '96e79218965eb72c92a549dd5a330112', '0002', 'demo', '男', '18000000001', 'demo@sfinace.com.cn', '上海市浦东新区', NULL, 2, 1, 1, '2018-08-21 15:55:28', 2, '2018-08-22 21:13:33');

-- ----------------------------
-- Table structure for sys_user_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_org`;
CREATE TABLE `sys_user_org`  (
  `userOrgId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL,
  `orgId` int(11) NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT NULL,
  `crtOptr` int(11) NULL DEFAULT NULL COMMENT '新增用户',
  `crtTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增日期',
  `modOptr` int(11) NULL DEFAULT NULL COMMENT '修改用户',
  `modTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`userOrgId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_org
-- ----------------------------
INSERT INTO `sys_user_org` VALUES (1, 1, 1, 1, 1, '2018-08-21 15:56:56', NULL, NULL);
INSERT INTO `sys_user_org` VALUES (2, 2, 1, 0, 1, '2018-08-21 16:00:17', NULL, NULL);

-- ----------------------------
-- Table structure for trashcanmaster
-- ----------------------------
DROP TABLE IF EXISTS `trashcanmaster`;
CREATE TABLE `trashcanmaster`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型',
  `lat` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `lng` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `useDate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用日期',
  `pic1` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片1',
  `pic2` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片2',
  `pic3` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片3',
  `pic4` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片4',
  `pic5` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片5',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `crtOptr` int(11) NULL DEFAULT NULL COMMENT '新增用户',
  `crtTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增日期',
  `modOptr` int(11) NULL DEFAULT NULL COMMENT '修改用户',
  `modTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '垃圾箱主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of trashcanmaster
-- ----------------------------
INSERT INTO `trashcanmaster` VALUES (1, '222', 1, '31.222211711942542', '121.54548168182373', '中国上海市上海市浦东新区锦带路22号', '2018-08-22', '2018-08-27-23-42-58_480_0104.jpg', '2018-08-27-23-43-01_480_0105.jpg', '2018-08-27-23-43-05_480_0106.jpg', '2018-08-27-23-43-05_480_0107.jpg', '2018-08-27-23-43-08_480_0108.jpg', 1, 1, '2018-08-27 23:43:09', 1, '2018-08-22 20:15:31');
INSERT INTO `trashcanmaster` VALUES (2, '22222', 2, '31.222619994450767', '121.54478967189789', '中国上海市上海市浦东新区锦带路18号', '', '2018-08-24-10-46-35_480_0109.jpg', '2018-08-24-10-46-37_480_0110.jpg', '2018-08-24-10-46-39_480_0111.jpg', '2018-08-24-10-46-42_480_dreamer.jpg', '2018-08-24-10-46-44_480_guess.jpg', 1, 1, '2018-08-24 11:01:18', 1, '2018-08-24 10:48:48');
INSERT INTO `trashcanmaster` VALUES (3, '333', 1, '31.220604254414287', '121.53280019760132', '中国上海市上海市浦东新区浦电路399号', '', '', '', '', '', '', 1, 1, '2018-08-26 22:42:41', NULL, NULL);
INSERT INTO `trashcanmaster` VALUES (4, '44', 1, '31.22675501747227', '121.53548240661621', '中国上海市上海市浦东新区源深路836号', '', '', '', '', '', '', 0, 1, '2018-08-26 22:42:56', 1, '2018-08-27 10:44:47');
INSERT INTO `trashcanmaster` VALUES (5, '55555', 2, '31.22179333524228', '121.5440708398819', '中国上海市上海市浦东新区世纪大道2001号', '2018-08-30', '2018-08-27-18-23-55_480_0104.jpg', '2018-08-27-18-23-57_480_0105.jpg', '2018-08-27-18-23-59_480_0106.jpg', '2018-08-27-18-24-01_480_0107.jpg', '2018-08-27-18-24-04_480_0108.jpg', 1, 1, '2018-08-27 18:26:10', NULL, NULL);
INSERT INTO `trashcanmaster` VALUES (6, '3123213', 1, '31.222089685634163', '121.54354512691498', '中国上海市上海市浦东新区', '', NULL, NULL, NULL, NULL, NULL, 1, 1, '2018-09-03 21:33:45', NULL, NULL);
INSERT INTO `trashcanmaster` VALUES (7, '1312323', 1, '31.222663116362565', '121.54399037361145', '中国上海市上海市浦东新区锦带路15号', '', 'undefined', '2018-09-03-21-50-44-572_480_0105.jpg', '2018-09-03-21-50-44-572_480_0104.jpg', '2018-09-03-21-50-44-614_480_0107.jpg', '2018-09-03-21-50-44-624_480_0106.jpg', 1, 1, '2018-09-03 21:50:54', NULL, NULL);

-- ----------------------------
-- Table structure for trashcantype
-- ----------------------------
DROP TABLE IF EXISTS `trashcantype`;
CREATE TABLE `trashcantype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `size` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '尺寸',
  `material` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '材质',
  `power` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电源',
  `lifetime` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '寿命',
  `light` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '灯光',
  `color` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '颜色',
  `status` int(2) NULL DEFAULT NULL,
  `crtOptr` int(11) NULL DEFAULT NULL COMMENT '新增用户',
  `crtTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增日期',
  `modOptr` int(11) NULL DEFAULT NULL COMMENT '修改用户',
  `modTime` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '垃圾箱类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of trashcantype
-- ----------------------------
INSERT INTO `trashcantype` VALUES (1, '22', '22', '22', '22', '22', '22', '/trashCan/assets/users/img/transhCan-27408B.png', 1, 1, '2018-08-23 19:23:55', 1, '2018-08-23 19:26:54');
INSERT INTO `trashcantype` VALUES (2, '333', '333', '333', '333', '333', '333', '/trashCan/assets/users/img/transhCan-CD3333.png', 1, 1, '2018-08-24 11:00:53', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

项目发布：
修改数据库连接
dataSource.properties
修改用户上传路径配置（FOR LINUX）
users.properties
USER-IMG-PATH=/etc/apache-tomcat-8.0.53/webapps/trashCanUpload/

增加TOMCAT映射路径配置
<Context docBase="trashCanUpload" path="/trashCanUpload" reloadable="true" crossContext = "ture"/>
