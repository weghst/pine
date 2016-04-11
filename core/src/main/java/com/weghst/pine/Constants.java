package com.weghst.pine;

/**
 * Pine 系统常量属性定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface Constants {

    /**
     * Pine 系统编码.
     */
    String ENCODING_PROP = "pine.encoding";

    /**
     * 自动升级数据库属性键。
     */
    String AUTO_UPGRADE_DATABASE = "pine.autoUpgradeDatabase";

    /**
     * Restful 资源路径前缀.
     */
    String RESTFUL_PATH_PREFIX_PROP = "pine.restful.path.prefix";

    /**
     * 从库数据源名称.
     */
    String SLAVE_DATA_SOURCE_TYPE = "salveDataSource";

    /**
     * 系统超级管理员邮箱。
     */
    String ROOT_EMAIL = "root@weghst.com";

}
