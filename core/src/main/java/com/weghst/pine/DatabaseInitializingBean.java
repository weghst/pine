package com.weghst.pine;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class DatabaseInitializingBean implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseInitializingBean.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public void afterPropertiesSet() throws Exception {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();
    }
}
