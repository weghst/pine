--liquibase formatted sql

--changeset kevinz@weghst.com:1439026570
CREATE TABLE `t_config`(
  `key` VARCHAR(50) NOT NULL COMMENT '配置属性键, 全局唯一',
  `value` VARCHAR(256) COMMENT '配置属性值',
  `remarks` VARCHAR(512) COMMENT '属性描述',
  `comments` VARCHAR(512) COMMENT '额外的备注信息',
  `needReboot` BOOLEAN COMMENT '修改属性是否需要重启应用生效',
  `lastUpdatedTime` BIGINT COMMENT '属性最后修改时间',
  `lastBut2UpdatedTime` BIGINT COMMENT '倒数第二次属性修改时间',
  PRIMARY KEY (`key`)
);

INSERT INTO `t_config`(`key`, `value`, `remarks`, `lastUpdatedTime`) VALUES('hello.test', 'HELLO', 'TestData', UNIX_TIMESTAMP());
--rollback DROP TABLE `t_config`;

--changeset kevinz@weghst.com:1439026604
CREATE TABLE `t_user`(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识, 自动增长',
  `email` VARCHAR (32) COMMENT '用户邮箱, 唯一值',
  `password` VARCHAR (128) COMMENT '用户密码',
  `emailValid` BOOLEAN NULL COMMENT '用户邮箱是否验证',
  `createdTime` BIGINT COMMENT '用户创建时间',
  PRIMARY KEY (`id`)
);

ALTER TABLE `t_user`
  ADD UNIQUE INDEX `EMAIL(UNIQUE)` (`email`);
--rollback DROP TABLE `t_user`;

--changeset kevinz@weghst.com:1440394618
CREATE TABLE `t_user_temp_field`(
  `uid` BIGINT NOT NULL COMMENT '用户ID',
  `field` VARCHAR(128) NOT NULL COMMENT '字段名称',
  `value` VARCHAR(1024) COMMENT '字段值',
  `createdTime` BIGINT COMMENT '创建时间',
  `survivalMillis` BIGINT COMMENT '字段存活有效时间(单位:毫秒)',
  PRIMARY KEY (`uid`, `field`)
) COMMENT='用户临时字段存储表, uid与field为联合主键';
--rollback DROP TABLE `t_user_temp_field`;
