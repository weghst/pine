package com.weghst.pine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weghst.pine.domain.Config;
import com.weghst.pine.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;

import redis.clients.jedis.Jedis;

public class SpringCacheTest extends SpringTestSupport {


    @Autowired
    private Jedis jedis;

    @Test
    public void test1() throws JsonProcessingException {
        Config config = new Config();
        config.setKey("hello.config");
        config.setValue("我是邹");
        config.setNeedReboot(true);
        config.setLastUpdatedTime(System.currentTimeMillis());
        config.setComments("这是注释");
        config.setRemarks("这是注释");

        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
        jedis.set("test.cache:config", objectMapper.writeValueAsString(config));
    }

    @Test
    public void test1Get() throws IOException {
        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();

        String val = jedis.get("test.cache:config");
        Config config = objectMapper.readValue(val, Config.class);
        System.out.println(config);
    }

    @Test
    public void test2() throws JsonProcessingException {
        User user = new User();
        user.setEmail("kevinz@weghst.com");
        user.setPassword("[PASSWORD]");

        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
        jedis.set("test.cache:user", objectMapper.writeValueAsString(user));
    }

    @Test
    public void test2Get() throws IOException {
        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();

        String val = jedis.get("test.cache:user");
        User user = objectMapper.readValue(val, User.class);
        System.out.println(user);
    }

}
