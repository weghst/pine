package com.weghst.pine.repository.impl;

import com.weghst.pine.SpringTestSupport;
import com.weghst.pine.domain.User;
import com.weghst.pine.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class TestUserRepositoryImpl extends SpringTestSupport {

    @Autowired
    private UserRepository userReposy;

    @Test
    public void testSave() {
        User user = new User();
        user.setEmail("kevinz@weghst.com");
        user.setPassword("[PASSWORD]");

        userReposy.save(user);
    }
}
