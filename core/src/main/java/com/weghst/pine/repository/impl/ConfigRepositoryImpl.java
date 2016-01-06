package com.weghst.pine.repository.impl;

import com.weghst.pine.domain.Config;
import com.weghst.pine.repository.ConfigRepository;
import com.weghst.pine.repository.support.SqlSessionDaoSupport;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Repository
public class ConfigRepositoryImpl extends SqlSessionDaoSupport implements ConfigRepository {

    @Override
    public int save(Config config) {
        return getSqlSession().insert("com.weghst.pine.domain.Config.save", config);
    }

    @Override
    public int delete(String key) {
        return getSqlSession().delete("com.weghst.pine.domain.Config.deleteByKey", key);
    }

    @Override
    public int update(Config config) {
        return getSqlSession().update("com.weghst.pine.domain.Config.updateByKey", config);
    }

    @Override
    public int updateValue(String key, String value) {
        Map<String, Object> params = new HashMap<>();
        params.put("key", key);
        params.put("value", value);

        return getSqlSession().update("com.weghst.pine.domain.Config.updateValueByKey", params);
    }

    @Override
    public Config get(String key) {
        return getSqlSession().selectOne("com.weghst.pine.domain.Config.getByKey", key);
    }

    @Override
    public List<Config> findAll() {
        return getSqlSession().selectList("com.weghst.pine.domain.Config.findAll");
    }
}
