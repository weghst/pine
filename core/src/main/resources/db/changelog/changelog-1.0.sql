--liquibase formatted sql

--changeset kevinz@weghst.com:1439026570

/*Table structure for table `t_config` */

CREATE TABLE `t_config` (
  `key` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '配置属性键, 全局唯一',
  `value` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '配置属性值',
  `remarks` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '属性描述',
  `comments` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '额外的备注信息',
  `needReboot` tinyint(1) NOT NULL DEFAULT '0' COMMENT '修改属性是否需要重启应用生效',
  `lastUpdatedTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '属性最后修改时间',
  `lastBut2UpdatedTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '倒数第二次属性修改时间',
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统业务参数配置';

/*Data for the table `t_config` */

/*Table structure for table `t_user` */

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识, 自动增长',
  `email` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户邮箱, 唯一值',
  `password` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '用户密码',
  `emailValid` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户邮箱是否验证',
  `createdTime` bigint(20) NOT NULL COMMENT '用户创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `EMAIL(UNIQUE)` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统用户';

/*Data for the table `t_user` */

/*Table structure for table `t_user_temp_field` */

CREATE TABLE `t_user_temp_field` (
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `field` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '字段名称',
  `value` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '字段值',
  `createdTime` bigint(20) NOT NULL COMMENT '创建时间',
  `survivalMillis` int(20) NOT NULL COMMENT '字段存活有效时间(单位:毫秒)',
  PRIMARY KEY (`uid`,`field`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户临时字段存储表, uid与field为联合主键';

/*Data for the table `t_user_temp_field` */

--rollback DROP TABLE `t_config`;
--rollback DROP TABLE `t_user`;
--rollback DROP TABLE `t_user_temp_field`;
