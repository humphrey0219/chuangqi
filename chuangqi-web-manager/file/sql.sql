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
  `number` varchar(64) NOT NULL COMMENT '页面编号',
  `title` varchar(64) NOT NULL COMMENT '标题',
  `keyword` VARCHAR(100) NOT NULL COMMENT '页面关键词',
  `description` varchar(500)  DEFAULT NULL COMMENT '页面简介',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='网页分类管理';


CREATE TABLE `t_page_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `page`bigint(20)  NOT NULL COMMENT '页面类型',
  `number` varchar(64) NOT NULL COMMENT '图片编号',
   `url`  varchar(100) NOT NULL COMMENT '图片路径',
  `description` varchar(500)  DEFAULT NULL COMMENT '图片描述',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='网页图片管理';

CREATE TABLE `t_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `serviceType` tinyint(10) NOT NULL DEFAULT '10' COMMENT '业务类型：10公司新闻，20行业新闻',
  `title` varchar(255) NOT NULL COMMENT '新闻标题',
  `resource` varchar(128) DEFAULT NULL COMMENT '新闻来源',
  `resourceUrl` varchar(255) DEFAULT NULL COMMENT '来源URL',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键词',
  `description` varchar(1024) DEFAULT NULL COMMENT '关键字描述',
  `imgUrl` varchar(255) DEFAULT NULL COMMENT '新闻图片',
  `newDesc` varchar(1024) DEFAULT NULL COMMENT '新闻简介',
  `status` tinyint(5) NOT NULL DEFAULT '0' COMMENT '状态（0：草稿，1上架，2下架）',
  `pubTime` datetime DEFAULT NULL COMMENT '发布时间',
  `details` text COMMENT '新闻详情',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uq` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='新闻管理';

CREATE TABLE `t_smile_test` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `smileTestImg` varchar(255) DEFAULT NULL COMMENT '微笑测试图片',
  `applyTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `status` tinyint(5) NOT NULL DEFAULT '0' COMMENT '状态（0申请中，1测试中，2已反馈，3作废）',
  `backTime` datetime DEFAULT NULL COMMENT '反馈时间',
  `testReport` text COMMENT '测试报告',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_phone` (`phone`),
  KEY `idx_applyTime` (`applyTime`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='微笑测试';

