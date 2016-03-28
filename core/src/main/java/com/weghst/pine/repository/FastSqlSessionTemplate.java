package com.weghst.pine.repository;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.*;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.util.Assert;

/**
 * @author zouyong (zouyong@mychebao.com)
 */
public class FastSqlSessionTemplate implements SqlSession {

    private final SqlSessionFactory sqlSessionFactory;
    private final ExecutorType executorType;
    private final PersistenceExceptionTranslator exceptionTranslator;

    public FastSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        this(sqlSessionFactory, sqlSessionFactory.getConfiguration().getDefaultExecutorType());
    }

    public FastSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType) {
        this(sqlSessionFactory, executorType, new MyBatisExceptionTranslator(
                sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(), true));
    }

    public FastSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType,
                                  PersistenceExceptionTranslator exceptionTranslator) {
        Assert.notNull(sqlSessionFactory, "Property 'sqlSessionFactory' is required");
        Assert.notNull(executorType, "Property 'executorType' is required");

        this.sqlSessionFactory = sqlSessionFactory;
        this.executorType = executorType;
        this.exceptionTranslator = exceptionTranslator;
    }

    @Override
    public <T> T selectOne(String statement) {
        return getSqlSession().selectOne(statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return getSqlSession().selectOne(statement, parameter);
    }

    @Override
    public <E> List<E> selectList(String statement) {
        return getSqlSession().selectList(statement);
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        return getSqlSession().selectList(statement, parameter);
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        return getSqlSession().selectList(statement, parameter, rowBounds);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
        return getSqlSession().selectMap(statement, mapKey);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
        return getSqlSession().selectMap(statement, parameter, mapKey);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
        return getSqlSession().selectMap(statement, parameter, mapKey, rowBounds);
    }

    @Override
    public void select(String statement, Object parameter, ResultHandler handler) {
        getSqlSession().select(statement, parameter, handler);
    }

    @Override
    public void select(String statement, ResultHandler handler) {
        getSqlSession().select(statement, handler);
    }

    @Override
    public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
        getSqlSession().select(statement, parameter, rowBounds, handler);
    }

    @Override
    public int insert(String statement) {
        return getSqlSession().insert(statement);
    }

    @Override
    public int insert(String statement, Object parameter) {
        return getSqlSession().insert(statement, parameter);
    }

    @Override
    public int update(String statement) {
        return getSqlSession().update(statement);
    }

    @Override
    public int update(String statement, Object parameter) {
        return getSqlSession().update(statement, parameter);
    }

    @Override
    public int delete(String statement) {
        return getSqlSession().delete(statement);
    }

    @Override
    public int delete(String statement, Object parameter) {
        return getSqlSession().delete(statement, parameter);
    }

    @Override
    public void commit() {
        // ignore
    }

    @Override
    public void commit(boolean force) {
        // ignore
    }

    @Override
    public void rollback() {
        // ignore
    }

    @Override
    public void rollback(boolean force) {
        // ignore
    }

    @Override
    public List<BatchResult> flushStatements() {
        return getSqlSession().flushStatements();
    }

    @Override
    public void close() {
        // ignore
    }

    @Override
    public void clearCache() {
        getSqlSession().clearCache();
    }

    @Override
    public Configuration getConfiguration() {
        return sqlSessionFactory.getConfiguration();
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return getConfiguration().getMapper(type, this);
    }

    @Override
    public Connection getConnection() {
        return getSqlSession().getConnection();
    }

    private SqlSession getSqlSession() {
        return SqlSessionUtils.getSqlSession(sqlSessionFactory, executorType, exceptionTranslator);
    }
}
