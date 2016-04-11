package com.weghst.pine;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Component(DatabaseInitializingBean.BEAN_NAME)
public class DatabaseInitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseInitializingBean.class);
    /**
     * DatabaseProcessor 被 Ioc 容器所管理的 ID。
     */
    public static final String BEAN_NAME = "com.weghst.pine.DatabaseInitializingBean";

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        LOG.debug("Pine database init");

        boolean autoUpgrade = ConfigUtils.getBoolean(Constants.AUTO_UPGRADE_DATABASE);
        if (!autoUpgrade) {
            LOG.info("自动升级数据库脚本被关闭");
            return;
        }

        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();
    }

}
