package com.weghst.pine.service.impl;

import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weghst.pine.ConfigUtils;
import com.weghst.pine.domain.Config;
import com.weghst.pine.repository.ConfigRepository;
import com.weghst.pine.service.ConfigService;

/**
 * 配置服务实现. 须通过XML配置初始化, 且需要保证该实现具有较高的执行优先级.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Service(ConfigService.BEAN_NAME)
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigRepository configRepository;
    @Value("${pine.timeZone}")
    public String testKey;

    @PostConstruct
    public void init() throws Exception {
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
