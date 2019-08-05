CREATE TABLE `t_sys_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主见',
  `userName` varchar(50) NOT NULL COMMENT '用户名',
  `pwd` varchar(64) NOT NULL COMMENT '密码',
  `realName` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态：1启用，0禁用',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统账号';


CREATE TABLE `t_web_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '页面名称',
  `number` varchar(64) NOT NULL COMMENT '编号',
  `title` varchar(64) NOT NULL COMMENT '标题',
  `keyword` VARCHAR(100) NOT NULL COMMENT '页面关键词',
  `description` varchar(500)  DEFAULT NULL COMMENT '页面简介',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='网页管理';


CREATE TABLE `t_page_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `page`bigint(20)  NOT NULL COMMENT '页面类型',
  `number` varchar(64) NOT NULL COMMENT '编号',
   `url`  varchar(100) NOT NULL COMMENT '图片路径',
  `description` varchar(500)  DEFAULT NULL COMMENT '页面简介',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='网页图片管理';