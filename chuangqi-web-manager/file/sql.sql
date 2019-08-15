CREATE TABLE `t_sys_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主见',
  `userName` varchar(50) NOT NULL COMMENT '用户名',
  `pwd` varchar(64) NOT NULL COMMENT '密码',
  `realName` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态：1启用，0禁用',
  `level` tinyint(5) DEFAULT '1' COMMENT '角色：1超级管理员，2普通管理员',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='系统账号';

DROP TABLE IF EXISTS `t_web_page` ;
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

INSERT INTO   t_web_page (name, number,title,keyword,description) values
    ("首页", "1000","创启官网", "创启；美容","创启首页"),
  ("微笑测试产品A", "5000","A产品测试", "微笑；A产品","A产品的测试页面"),
  ("微笑测试产品B", "5100","B产品测试", "微笑；B产品","B产品的测试页面");

DROP TABLE IF EXISTS `t_page_image`;
CREATE TABLE `t_page_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pageNum`  varchar(20)  NOT NULL COMMENT '页面编号',
  `number` varchar(64) NOT NULL COMMENT '图片编号',
   `url`  varchar(100) NOT NULL COMMENT '图片路径',
  `description` varchar(500)  DEFAULT NULL COMMENT '图片描述',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='网页图片管理';


INSERT INTO `t_page_image` (`pageNum`, `number`, `url`, `description`) values
("1000", "100", "", "主页创启团队图片"),
("1000", "101", "", "主页创启技术实力图片"),
("5000", "500", "", "测试案例A产品使用前图片"),
("5000", "501",  "", "测试案例A产品使用后图片"),
("5100", "600", "", "测试案例B产品使用前图片"),
("5100", "601", "", "测试案例B产品使用后图片");


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

CREATE TABLE `t_case_cate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '分类名称',
  `status` tinyint(5) NOT NULL DEFAULT '1' COMMENT '状态：1启用，0禁用',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `uq_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='病例分类';

CREATE TABLE `t_case_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cateId` bigint(20) DEFAULT NULL COMMENT '分类ID',
  `proCate` tinyint(5) DEFAULT '10' COMMENT '矫正产品：10舌侧，20隐形矫治器',
  `caseSpecial` varchar(128) DEFAULT NULL COMMENT '病例特点',
  `doctorName` varchar(64) DEFAULT NULL COMMENT '主治医生',
  `hospital` varchar(255) DEFAULT NULL COMMENT '治疗机构',
  `status` tinyint(5) DEFAULT '1' COMMENT '状态：1上架，0下架',
  `beforeImg` varchar(255) DEFAULT NULL COMMENT '整形前图片',
  `afterImg` varchar(255) DEFAULT NULL COMMENT '整形后图片',
  `experience` text COMMENT '矫治体会',
  `problem` text COMMENT '问题列表',
  `target` text COMMENT '治疗目标',
  `plan` text COMMENT '治疗计划',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_cateid` (`cateId`),
  KEY `idx_caseSpecial` (`caseSpecial`),
  KEY `idx_doctorName` (`doctorName`),
  KEY `idx_hospital` (`hospital`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='病例信息';

DROP TABLE IF EXISTS `t_zone`;
CREATE TABLE `t_zone` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `province` varchar(20) NOT NULL COMMENT '省份',
  `city`    varchar(20) NOT NULL COMMENT '城市',
  `county`  varchar(20) DEFAULT NULL COMMENT    '区、县',

  `zip`    varchar(10) DEFAULT NULL  COMMENT '区域代码',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='区域地址管理';

DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org` (
  `orgId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orgName`      varchar(20) NOT NULL COMMENT '机构名称',
  `agent`       varchar(20) NOT NULL COMMENT '机构联系人',
  `phone`       varchar(30) NOT NULL COMMENT '联系电话',
  `address`     bigint(20) NOT NULL COMMENT '机构地址ID',
  `code`        int(8)  DEFAULT NULL COMMENT '机构地址代码',
  `status` tinyint(5) NOT NULL DEFAULT '1' COMMENT '状态：1启用，0禁用',
  `street`      varchar(50) NOT NULL COMMENT	 '详细地址',
  `geoX`		decimal(15,10) NOT NULL  COMMENT    '坐标X',
 `geoY`       decimal(15,10)  NOT NULL COMMENT    '坐标Y',

  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`orgId`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='机构管理';

CREATE TABLE `t_key_val` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `key` varchar(64) DEFAULT NULL COMMENT '键',
  `keyDesc` varchar(128) DEFAULT NULL COMMENT '主键描述说明',
  `val` text COMMENT '值',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='键值配置';
