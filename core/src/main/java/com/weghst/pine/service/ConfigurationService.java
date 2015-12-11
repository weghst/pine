package com.weghst.pine.service;

import com.weghst.pine.domain.Config;

import java.util.List;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface ConfigurationService {

    void saveOrUpdate(Config config);

    void delete(String key);

    Config get(String key);

    List<Config> findAll();

}
