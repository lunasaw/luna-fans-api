DROP TABLE IF EXISTS `orm_user`;
CREATE TABLE `orm_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  `name` VARCHAR(32) NOT NULL UNIQUE COMMENT '用户名',
  `password` VARCHAR(32) NOT NULL COMMENT '加密后的密码',
  `salt` VARCHAR(32) NOT NULL COMMENT '加密使用的盐',
  `email` VARCHAR(32) NOT NULL UNIQUE COMMENT '邮箱',
  `phone_number` VARCHAR(15) NOT NULL UNIQUE COMMENT '手机号码',
  `status` INT(2) NOT NULL DEFAULT 1 COMMENT '状态，-1：逻辑删除，0：禁用，1：启用',
  `create_time` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `last_login_time` DATETIME DEFAULT NULL COMMENT '上次登录时间',
  `last_update_time` DATETIME NOT NULL DEFAULT NOW() COMMENT '上次更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Spring Boot Demo Orm 系列示例表';


DROP TABLE IF EXISTS `orm_department`;
CREATE TABLE `orm_department` (
  `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  `name` VARCHAR(32) NOT NULL COMMENT '部门名称',
  `superior` INT(11)  COMMENT '上级id',
  `levels` INT(11) NOT NULL COMMENT '层级',
  `order_no` INT(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `create_time` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `last_update_time` DATETIME NOT NULL DEFAULT NOW() COMMENT '上次更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Spring Boot Demo Orm 系列示例表';

DROP TABLE IF EXISTS `orm_user_dept`;
CREATE TABLE `orm_user_dept` (
  `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  `user_id` INT(11) NOT NULL COMMENT '用户id',
  `dept_id` INT(11) NOT NULL COMMENT '部门id',
  `create_time` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `last_update_time` DATETIME NOT NULL DEFAULT NOW() COMMENT '上次更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Spring Boot Demo Orm 系列示例表';


DROP TABLE IF EXISTS `orm_contacts`;
/*创建联系人表*/
CREATE TABLE orm_contacts (
  id bigint(32) NOT NULL AUTO_INCREMENT COMMENT '联系人编号(主键)',
  name varchar(16) DEFAULT NULL COMMENT '联系人姓名',
  gender char(1) DEFAULT NULL COMMENT '联系人性别',
  phone varchar(16) DEFAULT NULL COMMENT '联系人办公电话',
  mobile varchar(16) DEFAULT NULL COMMENT '联系人手机',
  email varchar(64) DEFAULT NULL COMMENT '联系人邮箱',
  position varchar(16) DEFAULT NULL COMMENT '联系人职位',
  memo varchar(512) DEFAULT NULL COMMENT '联系人备注',
  user_id INT(11) DEFAULT NULL COMMENT '客户id(外键)',
   `create_time` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `last_update_time` DATETIME NOT NULL DEFAULT NOW() COMMENT '上次更新时间',
  PRIMARY KEY (`id`),
  KEY `FK_cst_linkman_cust_id` (`user_id`),
  CONSTRAINT `FK_cst_linkman_cust_id` FOREIGN KEY (`user_id`) REFERENCES `orm_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

