package com.weghst.pine.repository.impl;

import com.weghst.pine.SpringTestSupport;
import com.weghst.pine.domain.User;
import com.weghst.pine.repository.UserRepository;

import org.testng.annotations.Test;

import javax.inject.Inject;

public class TestUserRepositoryImpl extends SpringTestSupport {

    @Inject
    private UserRepository userReposy;

    @Test
    public void testSave() {
        User user = new User();
        user.setEmail("kevinz@weghst.com");
        user.setPassword("[PASSWORD]");

        userReposy.save(user);
    }
}
