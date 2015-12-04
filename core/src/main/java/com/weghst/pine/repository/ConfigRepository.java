package com.weghst.pine.repository;

import com.weghst.pine.domain.Config;

import java.util.List;

public interface ConfigRepository {

    int save(Config config);

    int delete(String key);

    int update(Config config);

    int updateValue(String key, String value);

    Config get(String key);

    List<Config> findAll();
}
