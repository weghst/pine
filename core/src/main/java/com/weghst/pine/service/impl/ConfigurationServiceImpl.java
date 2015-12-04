package com.weghst.pine.service.impl;

import com.weghst.pine.ConfigUtils;
import com.weghst.pine.domain.Config;
import com.weghst.pine.repository.ConfigRepository;
import com.weghst.pine.service.ConfigurationService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 配置服务实现. 须通过XML配置初始化, 且需要保证该实现具有较高的执行优先级.
 */
public class ConfigurationServiceImpl implements InitializingBean, ConfigurationService {

    @Autowired
    private ConfigRepository configurationReposy;

    @Override
    public void afterPropertiesSet() throws Exception {
        for (Config config : findAll()) {
            ConfigUtils.setProperty(config.getKey(), config.getValue());
        }
    }

    @Transactional
    @Override
    public void saveOrUpdate(Config config) {
        if (get(config.getKey()) != null) {
            configurationReposy.update(config);
        } else {
            configurationReposy.save(config);
        }

        ConfigUtils.setProperty(config.getKey(), config.getValue());
    }

    @Transactional
    @Override
    public void delete(String key) {
        configurationReposy.delete(key);

        ConfigUtils.removeProperty(key);
    }

    @Transactional(readOnly = true)
    @Override
    public Config get(String key) {
        return configurationReposy.get(key);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Config> findAll() {
        return configurationReposy.findAll();
    }
}
