package com.weghst.pine.service;

import com.weghst.pine.domain.Config;

import java.util.List;

/**
 * 配置逻辑接口定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface ConfigService {

    /**
     *
     * @param config
     */
    void saveOrUpdate(Config config);

    /**
     *
     * @param key
     */
    void delete(String key);

    /**
     *
     * @param key
     * @return
     */
    Config get(String key);

    /**
     *
     * @return
     */
    List<Config> findAll();

}
