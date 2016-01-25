package com.weghst.pine.service.impl;

import com.weghst.pine.ConfigUtils;
import com.weghst.pine.domain.Config;
import com.weghst.pine.repository.ConfigRepository;
import com.weghst.pine.service.ConfigService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 配置服务实现. 须通过XML配置初始化, 且需要保证该实现具有较高的执行优先级.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ConfigServiceImpl implements InitializingBean, ConfigService {

    @Autowired
    private ConfigRepository configRepository;

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
            configRepository.update(config);
        } else {
            configRepository.save(config);
        }

        ConfigUtils.setProperty(config.getKey(), config.getValue());
    }

    @Transactional
    @Override
    public void delete(String key) {
        configRepository.delete(key);

        ConfigUtils.removeProperty(key);
    }

    @Transactional(readOnly = true)
    @Override
    public Config get(String key) {
        return configRepository.get(key);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Config> findAll() {
        return configRepository.findAll();
    }
}
