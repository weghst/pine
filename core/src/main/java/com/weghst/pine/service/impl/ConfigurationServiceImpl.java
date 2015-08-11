package com.weghst.pine.service.impl;

import com.weghst.pine.ConfigUtils;
import com.weghst.pine.domain.Config;
import com.weghst.pine.repository.ConfigurationRepository;
import com.weghst.pine.service.ConfigurationService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ConfigurationServiceImpl implements InitializingBean, ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationReposy;

    @Override
    public void afterPropertiesSet() throws Exception {
        for (Config config : findAll()) {
            ConfigUtils.setProperty(config.getKey(), config.getValue());
        }
    }

    @Override
    public void saveOrUpdate(Config config) {
        if (get(config.getKey()) != null) {
            configurationReposy.update(config);
        } else {
            configurationReposy.save(config);
        }

        ConfigUtils.setProperty(config.getKey(), config.getValue());
    }

    @Override
    public void delete(String key) {
        configurationReposy.delete(key);

        ConfigUtils.removeProperty(key);
    }

    @Override
    public Config get(String key) {
        return configurationReposy.get(key);
    }

    @Override
    public List<Config> findAll() {
        return configurationReposy.findAll();
    }

}
