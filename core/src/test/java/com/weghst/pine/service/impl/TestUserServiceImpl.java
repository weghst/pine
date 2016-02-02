package com.weghst.pine.service.impl;

import com.weghst.pine.SpringTestSupport;
import com.weghst.pine.TestConstants;
import com.weghst.pine.domain.User;
import com.weghst.pine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class TestUserServiceImpl extends SpringTestSupport {

    @Autowired
    private UserService userService;

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

    private User newUser() {
        User user = new User();
        user.setEmail(TestConstants.EMAIL);
        user.setPassword(TestConstants.PASSWORD);
        return user;
    }
}
