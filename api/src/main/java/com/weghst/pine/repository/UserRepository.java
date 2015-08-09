package com.weghst.pine.repository;

import com.weghst.pine.domain.User;

public interface UserRepository {

    void save(User user);

    void delete(int id);

    void update(User user);

    User get(int id);

    User get(String email);

}
