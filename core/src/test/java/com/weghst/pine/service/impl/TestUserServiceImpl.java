package com.weghst.pine.service.impl;

import com.weghst.pine.SpringTestSupport;
import com.weghst.pine.TestConstants;
import com.weghst.pine.domain.User;
import com.weghst.pine.service.UserService;
import com.weghst.pine.util.RedisUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import redis.clients.jedis.Jedis;

public class TestUserServiceImpl extends SpringTestSupport {

    @Autowired
    private UserService userService;
    @Autowired
    private Jedis jedis;

    @Test
    public void testSave() {
        User user = newUser();
        userService.save(user);
    }

    @Test
    public void testRegister() {
        User user = newUser();
        userService.register(user);
    }

    @Test
    public void testEmailValidate() {
        User user = newUser();
        userService.register(user);

        String inteCode = jedis.get(RedisUtils.getCacheName(
                UserServiceImpl.EMAIL_VALIDATING_CODE_CACHE_NS, TestConstants.EMAIL));
        boolean r = userService.emailValidate(TestConstants.EMAIL, inteCode);

        User user2 = userService.get(user.getId());
        Assert.assertTrue(r && user2.isEmailValid());
    }

    private User newUser() {
        User user = new User();
        user.setEmail(TestConstants.EMAIL);
        user.setPassword(TestConstants.PASSWORD);
        return user;
    }
}
