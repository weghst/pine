package com.weghst.pine.service.impl;

import com.weghst.pine.domain.User;
import com.weghst.pine.repository.UserRepository;
import com.weghst.pine.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    public static final String EMAIL_VALIDATING_CODE_CACHE_NAME = "pine.core.user.emailValidatingCode";

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private UserRepository userReposy;

    @Transactional
    @Override
    public void save(User user) {
        userReposy.save(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userReposy.delete(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        userReposy.update(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User get(int id) {
        return userReposy.get(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User get(String email) {
        return userReposy.get(email);
    }

    @Transactional
    @Override
    public void emailValidate(String email, String code) {
        Cache cache = cacheManager.getCache(EMAIL_VALIDATING_CODE_CACHE_NAME);
        String inteCode = cache.get(email, String.class);
        if (inteCode == null) {

        }
    }
}
