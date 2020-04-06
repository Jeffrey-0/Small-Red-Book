/*
Navicat MySQL Data Transfer

Source Server         : SRB
Source Server Version : 50539
Source Host           : localhost:3306
Source Database       : srb

Target Server Type    : MYSQL
Target Server Version : 50539
File Encoding         : 65001

Date: 2020-01-09 23:16:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
  `sender_id` int(10) DEFAULT NULL,
  `recerive_id` int(10) DEFAULT NULL,
  `chat_content` varchar(256) DEFAULT NULL,
  `chat_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES ('111', '112', '聊天记录', '2019-11-15 13:50:32');
INSERT INTO `chat` VALUES ('112', '111', '你真无聊', '2019-11-15 13:50:52');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `user_id` int(10) DEFAULT NULL,
  `collect_note` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('112', '11');
INSERT INTO `collect` VALUES ('111', '12');
INSERT INTO `collect` VALUES ('111', '11');
INSERT INTO `collect` VALUES ('113', '14');
INSERT INTO `collect` VALUES ('113', '11');
INSERT INTO `collect` VALUES ('112', '19');
INSERT INTO `collect` VALUES ('120', '16');
INSERT INTO `collect` VALUES ('111', '19');
INSERT INTO `collect` VALUES ('112', '18');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `note_id` int(10) DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL,
  `comment_content` varchar(256) DEFAULT NULL,
  `comment_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('11', '111', '你真帅', '2019-11-15 13:52:22');
INSERT INTO `comment` VALUES ('12', '112', '你真幽默', '2019-11-15 13:52:43');
INSERT INTO `comment` VALUES ('11', '113', '超级燃，看哭了', '2019-11-18 23:49:03');
INSERT INTO `comment` VALUES ('13', '113', '111', '2019-11-19 00:00:00');
INSERT INTO `comment` VALUES ('12', '113', '1212341', '2019-11-19 00:00:00');
INSERT INTO `comment` VALUES ('11', '111', '傻了', '2019-10-10 11:30:00');
INSERT INTO `comment` VALUES ('14', '113', '我觉得可以', '2019-11-19 00:00:00');
INSERT INTO `comment` VALUES ('14', '111', '有道理', '2019-11-20 00:00:00');
INSERT INTO `comment` VALUES ('19', '112', '超级帅', '2019-11-20 00:00:00');
INSERT INTO `comment` VALUES ('19', '111', '666', '2019-11-20 00:00:00');

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `user_id` int(20) DEFAULT NULL,
  `concern_id` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of follow
-- ----------------------------
INSERT INTO `follow` VALUES ('111', '112');
INSERT INTO `follow` VALUES ('112', '111');
INSERT INTO `follow` VALUES ('112', '115');
INSERT INTO `follow` VALUES ('112', '113');
INSERT INTO `follow` VALUES ('120', '112');
INSERT INTO `follow` VALUES ('111', '111');
INSERT INTO `follow` VALUES ('111', '113');
INSERT INTO `follow` VALUES ('113', '111');
INSERT INTO `follow` VALUES ('113', '115');
INSERT INTO `follow` VALUES ('113', '112');

-- ----------------------------
-- Table structure for love
-- ----------------------------
DROP TABLE IF EXISTS `love`;
CREATE TABLE `love` (
  `user_id` int(10) DEFAULT NULL,
  `love_note` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of love
-- ----------------------------
INSERT INTO `love` VALUES ('112', '11');
INSERT INTO `love` VALUES ('113', '13');
INSERT INTO `love` VALUES ('111', '14');
INSERT INTO `love` VALUES ('111', '15');
INSERT INTO `love` VALUES ('111', '12');
INSERT INTO `love` VALUES ('112', '18');
INSERT INTO `love` VALUES ('113', '18');
INSERT INTO `love` VALUES ('113', '19');
INSERT INTO `love` VALUES ('111', '18');

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `note_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `note_time` datetime DEFAULT NULL,
  `title` varchar(30) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `text` varchar(256) DEFAULT NULL,
  `note_context` varchar(512) DEFAULT NULL,
  `num_love` int(10) DEFAULT NULL,
  `num_collect` int(10) DEFAULT NULL,
  `num_comment` int(10) DEFAULT NULL,
  PRIMARY KEY (`note_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES ('11', '111', '2019-11-15 13:53:45', '詹姆斯', '运动', '我觉得这球可以', 'NBA.mp4', '111', '111', '111');
INSERT INTO `note` VALUES ('12', '112', '2019-11-15 13:56:10', '9年RM，帅炸', '娱乐', '', 'RM.mp4', '111', '111', '11');
INSERT INTO `note` VALUES ('14', '115', '2019-11-16 12:15:10', '习大大经典语录', '搞笑', '遇到了困难，就不做了，睡大觉！！！', '习大大.mp4', '1', '1', '1');
INSERT INTO `note` VALUES ('15', '114', '2019-11-17 13:27:37', '穿搭推荐', '穿搭', '', '穿搭.mp4', '1', '1', '1');
INSERT INTO `note` VALUES ('16', '112', '2019-11-19 09:37:18', '仿佛看到几年后的自己了', '其他', '', '秃顶.jpg', '1', '1', '1');
INSERT INTO `note` VALUES ('17', '114', '2019-11-19 09:38:34', '搞笑电影', '搞笑', null, '搞笑电影.mp4', '1', '1', '1');
INSERT INTO `note` VALUES ('18', '113', '2019-11-19 09:39:25', '部门小伙伴', '其他', '一年的陪伴', '2.jpg,2_2.jpg,2_3.jpg,2_4.jpg', '1', null, null);
INSERT INTO `note` VALUES ('19', '113', '2019-11-20 09:40:17', '我的英雄学院超帅', '动漫', '', '我的英雄学院.mp4', '1', '1', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `head_portrait` varchar(50) DEFAULT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `describe` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('111', '111', '111', '13433894951', '111.jpg', '男', '江门', '2019-11-15', '什么都没写');
INSERT INTO `user` VALUES ('112', '小疯子', '123', '13433894951', '112.jpg', '男', '江门', '2019-11-15', '没个性');
INSERT INTO `user` VALUES ('113', 'xfz', '111', '13222222222', '113.jpg', '男', '广州', '2019-11-14', '无');
INSERT INTO `user` VALUES ('114', '鹿', '111', '12344133444', '114.jpg', '女', '广州', '2019-11-20', '哈哈哈哈');
INSERT INTO `user` VALUES ('115', '梦想做个死肥宅', '111', '13222222222', '115.jpg', '男', '肇庆', '2019-11-14', '死肥宅一个');
INSERT INTO `user` VALUES ('118', '222222', '2222', '2222', null, '2222', null, null, null);
INSERT INTO `user` VALUES ('119', 'wewwe', 'werw', '12312', null, 'sdfas', null, null, null);
INSERT INTO `user` VALUES ('120', '222', '222', '12323423423', null, 'nan', null, null, null);
INSERT INTO `user` VALUES ('121', '333', '3333', '333', null, '', null, null, null);
INSERT INTO `user` VALUES ('122', '333', '3333', '333', null, '333', null, null, null);
INSERT INTO `user` VALUES ('123', 'rrr', 'rrr', '777', null, 'rrr', null, null, null);
INSERT INTO `user` VALUES ('124', '123123', '1231231231', '12332131213', null, '', null, null, null);

-- ----------------------------
-- View structure for note_view
-- ----------------------------
DROP VIEW IF EXISTS `note_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`srb_user`@`%` SQL SECURITY DEFINER VIEW `note_view` AS select `note`.`note_id` AS `note_id`,`note`.`user_id` AS `user_id`,`note`.`note_time` AS `note_time`,`note`.`title` AS `title`,`note`.`type` AS `type`,`note`.`text` AS `text`,`note`.`note_context` AS `note_context`,(select count(0) from `love` where (`love`.`love_note` = `note`.`note_id`)) AS `num_love`,(select count(0) from `collect` where (`collect`.`collect_note` = `note`.`note_id`)) AS `num_collect`,(select count(0) from `comment` where (`comment`.`note_id` = `note`.`note_id`)) AS `num_comment` from `note` ;
