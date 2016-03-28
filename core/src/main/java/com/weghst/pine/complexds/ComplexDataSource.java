package com.weghst.pine.complexds;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;
import javax.sql.DataSource;

import org.springframework.util.Assert;

/**
 * JDBC 复合数据源定义。
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ComplexDataSource implements DataSource {

    private final DataSource masterDataSource;
    private final Map<String, DataSource> slaveDataSources;

    /**
     * 通过 Master/Slave 构建复合数据源。
     *
     * @param masterDataSource Master 数据源（必须）
     * @param slaveDataSources Slaves 数据库源集（至少一个 Slave 节点）
     */
    public ComplexDataSource(DataSource masterDataSource, Map<String, DataSource> slaveDataSources) {
        Assert.notNull(masterDataSource);
        Assert.notEmpty(slaveDataSources);

        this.masterDataSource = masterDataSource;
        this.slaveDataSources = Collections.unmodifiableMap(slaveDataSources);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getDataSource().getConnection(username, password);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet");
    }

    private DataSource getDataSource() {
        String contextName = NamedDSUtils.get();
        DataSource dataSource = slaveDataSources.get(contextName);
        if (dataSource == null) {
            dataSource = masterDataSource;
        }
        return dataSource;
    }
}
