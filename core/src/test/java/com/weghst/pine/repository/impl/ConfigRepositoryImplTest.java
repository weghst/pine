package com.weghst.pine.repository.impl;

import com.weghst.pine.SpringTestSupport;
import com.weghst.pine.domain.Config;
import com.weghst.pine.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * @author Kevin Zou <kevinz@weghst.com>
 */
public class ConfigRepositoryImplTest extends SpringTestSupport {

    @Autowired
    private ConfigRepository configRepository;

    @Test
    public void testSave() throws Exception {
        Config config = newConfig();
        int r = configRepository.save(config);
        assertEquals(r, 1);
    }

    @Test
    public void testDelete() throws Exception {
        Config config = newConfig();
        configRepository.save(config);

        int r = configRepository.delete(config.getKey());
        assertEquals(r, 1);
    }

    @Test
    public void testUpdate() throws Exception {
        Config config = newConfig();
        configRepository.save(config);

        config.setComments("UPDATE-COMMENTS");
        int r = configRepository.update(config);
        assertEquals(r, 1);
    }

    @Test
    public void testUpdateValue() throws Exception {
        Config config = newConfig();
        configRepository.save(config);

        int r = configRepository.updateValue(config.getKey(), "UPDATE-VALUE");
        assertEquals(r, 1);
    }

    @Test
    public void testGet() throws Exception {
        Config config = newConfig();
        configRepository.save(config);

        Config dbConfig = configRepository.get(config.getKey());
        assertNotNull(dbConfig);
    }

    @Test
    public void testFindAll() throws Exception {
        Config config = newConfig();
        configRepository.save(config);

        List<Config> list = configRepository.findAll();
        assertTrue(list.size() > 0);
    }

    private Config newConfig() {
        Config config = new Config();
        config.setKey("hello.test.config");
        config.setValue("Test Value");
        config.setRemarks("REMARKS");
        config.setComments("COMMENTS");
        config.setNeedReboot(false);
        return config;
    }
}