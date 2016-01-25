package com.weghst.pine.repository.impl;

import com.weghst.pine.domain.Config;
import com.weghst.pine.repository.ConfigRepository;
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
public class ConfigRepositoryImpl implements ConfigRepository {

    @Autowired
    private SqlSession sqlSessionTemplate;

    @Override
    public int save(Config config) {
        return sqlSessionTemplate.insert("com.weghst.pine.domain.Config.save", config);
    }

    @Override
    public int delete(String key) {
        return sqlSessionTemplate.delete("com.weghst.pine.domain.Config.deleteByKey", key);
    }

    @Override
    public int update(Config config) {
        return sqlSessionTemplate.update("com.weghst.pine.domain.Config.updateByKey", config);
    }

    @Override
    public int updateValue(String key, String value) {
        Map<String, Object> params = new HashMap<>();
        params.put("key", key);
        params.put("value", value);

        return sqlSessionTemplate.update("com.weghst.pine.domain.Config.updateValueByKey", params);
    }

    @Override
    public Config get(String key) {
        return sqlSessionTemplate.selectOne("com.weghst.pine.domain.Config.getByKey", key);
    }

    @Override
    public List<Config> findAll() {
        return sqlSessionTemplate.selectList("com.weghst.pine.domain.Config.findAll");
    }
}
