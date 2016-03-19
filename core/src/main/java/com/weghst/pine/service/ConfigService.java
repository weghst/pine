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
     * ConfigService 被 IoC 容器所管理的 ID.
     */
    String BEAN_NAME = "com.weghst.pine.service.ConfigService";

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
