package com.weghst.pine.repository.support;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class SqlSessionDaoSupport {

    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    public final void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     *
     * @return
     */
    protected SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
