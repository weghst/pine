package com.weghst.pine.service;

import com.weghst.pine.domain.User;

public interface UserService {

    void save(User user);

    void delete(int id);

    void update(User user);

    User get(int id);

    User get(String email);
}
