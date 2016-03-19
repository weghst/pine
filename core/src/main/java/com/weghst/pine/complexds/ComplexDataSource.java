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
 * @author zouyong (zouyong@mychebao.com)
 */
public class ComplexDataSource implements DataSource {

    private final DataSource masterDataSource;
    private final Map<String, DataSource> slaveDataSources;

    /**
     * @param masterDataSource
     * @param slaveDataSources
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
        DataSource dataSource = slaveDataSources.get(DataSourceTypeValue.get());
        if (dataSource == null) {
            dataSource = masterDataSource;
        }
        return dataSource;
    }
}
