package com.weghst.pine.service;

import com.weghst.pine.domain.User;
import com.weghst.pine.domain.UserTempField;

public interface UserService {

    void save(User user);

    void delete(int id);

    void update(User user);

    User get(int id);

    User get(String email);

    void register(User user);

    void sendEmailValidate(User user);

    boolean emailValidate(String email, String code);

}
