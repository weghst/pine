package com.weghst.pine;

import com.weghst.pine.domain.Config;
import com.weghst.pine.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.testng.annotations.Test;

public class SpringCacheTest extends SpringTestSupport {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1() {
        Config config = new Config();
        config.setKey("hello.config");
        config.setValue("我是邹");
        config.setNeedReboot(true);
        config.setLastUpdatedTime(System.currentTimeMillis());
        config.setComments("这是注释");
        config.setRemarks("这是注释");

        Cache cache = cacheManager.getCache("test:cache");
        cache.put("test.config", config);
    }

    @Test
    public void test1Get() {
        Cache cache = cacheManager.getCache("test:cache");
        Cache.ValueWrapper value = cache.get("test.config");
        System.out.println(value.get());
    }

    @Test
    public void test2() {
        User user = new User();
        user.setEmail("kevinz@weghst.com");
        user.setPassword("[PASSWORD]");
        user.setCreatedTime(System.currentTimeMillis());

        Cache cache = cacheManager.getCache("test:cache");
        cache.put("test.user", user);
    }

    @Test
    public void test2Get() {
        Cache cache = cacheManager.getCache("test:cache");
        Cache.ValueWrapper value = cache.get("test.user");
        System.out.println(value.get());
    }

    @Test
    public void test3() {
        redisTemplate.boundHashOps("hello:hash").put("name", "kevin");

    }

}
