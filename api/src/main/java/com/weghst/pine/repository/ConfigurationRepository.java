package com.weghst.pine.repository;

import com.weghst.pine.domain.Config;

import java.util.List;

public interface ConfigurationRepository {

    void save(Config config);

    void delete(String key);

    void update(Config config);

    void updateValue(String key, String value);

    Config get(String key);

    List<Config> findAll();
}
