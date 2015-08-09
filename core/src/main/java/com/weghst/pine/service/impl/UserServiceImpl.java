package com.weghst.pine.service.impl;

import com.weghst.pine.domain.User;
import com.weghst.pine.repository.UserRepository;
import com.weghst.pine.service.UserService;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userReposy;

    @Override
    public void save(User user) {
        userReposy.save(user);
    }

    @Override
    public void delete(int id) {
        userReposy.delete(id);
    }

    @Override
    public void update(User user) {
        userReposy.update(user);
    }

    @Override
    public User get(int id) {
        return userReposy.get(id);
    }

    @Override
    public User get(String email) {
        return userReposy.get(email);
    }
}
