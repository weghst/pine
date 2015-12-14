package com.weghst.pine.service;

import com.weghst.pine.domain.Config;

import java.util.List;

/**
 * 配置逻辑接口定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface ConfigService {

    void saveOrUpdate(Config config);

    void delete(String key);

    Config get(String key);

    List<Config> findAll();

}
