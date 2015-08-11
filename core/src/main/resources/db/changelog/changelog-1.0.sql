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
  `createdTime` BIGINT COMMENT '用户创建时间',
  PRIMARY KEY (`id`)
);

ALTER TABLE `t_user`
  ADD UNIQUE INDEX `EMAIL(UNIQUE)` (`email`);
--rollback DROP TABLE `t_user`;
