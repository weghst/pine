package com.weghst.pine.repository.impl;

import com.weghst.pine.domain.Config;
import com.weghst.pine.repository.ConfigRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kevin Zou <kevinz@weghst.com>
 */
@Repository
public class ConfigRepositoryImpl implements ConfigRepository {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public int save(Config config) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.insert("com.weghst.pine.domain.Config.save", config);
    }

    @Override
    public int delete(String key) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.delete("com.weghst.pine.domain.Config.deleteByKey", key);
    }

    @Override
    public int update(Config config) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.update("com.weghst.pine.domain.Config.updateByKey", config);
    }

    @Override
    public int updateValue(String key, String value) {
        Map<String, Object> params = new HashMap<>();
        params.put("key", key);
        params.put("value", value);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.update("com.weghst.pine.domain.Config.updateValueByKey", params);
    }

    @Override
    public Config get(String key) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectOne("com.weghst.pine.domain.Config.getByKey", key);
    }

    @Override
    public List<Config> findAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectList("com.weghst.pine.domain.Config.findAll");
    }
}
