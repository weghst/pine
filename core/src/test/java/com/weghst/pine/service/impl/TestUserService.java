package com.weghst.pine.service.impl;

import com.weghst.pine.SpringTestSupport;
import com.weghst.pine.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class TestUserService extends SpringTestSupport {

    @Autowired
    private UserService userService;

    @Test
    public void testEmailValidate() {
//        userService.emailValidate("501276913@qq.com", "587697");
    }
}
