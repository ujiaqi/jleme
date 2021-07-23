/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : jleme

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 23/07/2021 21:37:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '菜谱ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '种类名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜谱表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '家常');
INSERT INTO `category` VALUES (2, '火锅');
INSERT INTO `category` VALUES (3, '小吃');
INSERT INTO `category` VALUES (4, '酒水');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `price` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品价格',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名字',
  `description` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `sales` int(0) NULL DEFAULT 0 COMMENT '商品销量',
  `pic` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `category_id` int(0) NOT NULL DEFAULT 0 COMMENT '归属菜谱',
  `tags_id` int(0) NOT NULL DEFAULT 0 COMMENT '菜品标签',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '商品状态，1为正常，0为下架',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  INDEX `tags_id`(`tags_id`) USING BTREE,
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `goods_ibfk_2` FOREIGN KEY (`tags_id`) REFERENCES `tags` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '单个商品，也就是菜单中的菜品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '120', '番茄炒蛋', '番茄炒蛋不一定要用牛番茄，小番茄炒出来也很好吃哦', 66, 'http://localhost:8081/static/goods/fanqiechaodan.jpg', 1, 5, 1);
INSERT INTO `goods` VALUES (2, '32', '鱼香肉丝', '此菜主料是猪肉，要选用三成肥、七成瘦的猪肉切丝滑炒，方能使肉丝质地鲜嫩。成菜色红润、肉嫩、质鲜、富鱼香味。', 35, 'http://localhost:8081/static/goods/yuxiangrousi.jpg', 1, 7, 1);
INSERT INTO `goods` VALUES (3, '55', '糖醋排骨', '酸甜适中，不油不腻，口感丰富细腻，不会觉得任何一种调料的突兀。 颜色呈糖稀色，不浓不淡,男女老少皆宜。', 88, 'http://localhost:8081/static/goods/tangcupaigu.jpg', 1, 8, 1);
INSERT INTO `goods` VALUES (4, '15', '香菇青菜', '香菇青菜是一道菜品，制作原料有青菜、香菇、油、盐等。香菇盛出后，用筷子整齐地摆放在青菜上，最后将锅内的汁淋在菜上。', 36, 'http://localhost:8081/static/goods/xiangguqingcai.jpg', 1, 5, 1);
INSERT INTO `goods` VALUES (5, '88', '红烧鲫鱼', '红烧鲫鱼是以鲫鱼为主要食材，配以香菜、红辣椒一起烧制的美味私房菜，口味香辣可口，美容抗皱，营养价值丰富。', 121, 'http://localhost:8081/static/goods/hongshaojiyu.jpg', 1, 9, 1);
INSERT INTO `goods` VALUES (6, '128', '牛肉小火锅', '牛肉是补气血的佳品，可治疗由气血虚弱引起的脾胃虚弱都，健脾养胃，对面黄肌瘦以及虚胖的人群都有好处。', 26, 'http://localhost:8081/static/goods/niurouhuoguo.jpg', 2, 9, 1);

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '菜品ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品标签',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜品标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tags
-- ----------------------------
INSERT INTO `tags` VALUES (1, '酸');
INSERT INTO `tags` VALUES (2, '甜');
INSERT INTO `tags` VALUES (3, '辣');
INSERT INTO `tags` VALUES (4, '清淡');
INSERT INTO `tags` VALUES (5, '正常');
INSERT INTO `tags` VALUES (6, '重口');
INSERT INTO `tags` VALUES (7, '偏咸');
INSERT INTO `tags` VALUES (8, '偏甜');
INSERT INTO `tags` VALUES (9, '微辣');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加盐',
  `user_photo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `state` tinyint(0) NULL DEFAULT 1 COMMENT '用户状态',
  `join_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `last_login` datetime(0) NULL DEFAULT NULL COMMENT '最近登录',
  `tag` int(0) NULL DEFAULT 1 COMMENT '身份标签',
  `token` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录凭证',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '地址表主键',
  `user_id` int(0) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `latitude` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `longitude` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `province` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `district` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域',
  `street` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '街道',
  `detal_addr` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `default_addr` tinyint(0) NULL DEFAULT 0 COMMENT '是否为默认地址',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '状态',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `user_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
