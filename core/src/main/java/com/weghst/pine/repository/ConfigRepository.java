package com.weghst.pine.repository;

import com.weghst.pine.domain.Config;

import java.util.List;

/**
 * 配置数据接口定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface ConfigRepository {

    /**
     * 保存配置并返回成功的记录数.
     *
     * @param config 配置对象
     * @return 成功的记录数
     */
    int save(Config config);

    /**
     * 根据属性名称删除配置并返回成功的记录数.
     *
     * @param key 属性名称
     * @return 成功的记录数
     */
    int delete(String key);

    /**
     * 更新配置并返回成功的记录数.
     *
     * @param config 配置对象
     * @return 成功的记录数
     */
    int update(Config config);

    /**
     * 更新指定属性的值并返回成功的记录数.
     *
     * @param key   属性名称
     * @param value 新的属性值
     * @return 成功的记录数
     */
    int updateValue(String key, String value);

    /**
     * 根据指定的属性名称返回配置信息.
     *
     * @param key 属性名称
     * @return 配置信息
     */
    Config get(String key);

    /**
     * 返回所有的配置信息.
     *
     * @return 配置信息集
     */
    List<Config> findAll();
}
