package com.weghst.pine.repository.impl;

import com.weghst.pine.domain.User;
import com.weghst.pine.domain.UserTempField;
import com.weghst.pine.repository.UserRepository;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class LdapUserRepositoryImpl implements UserRepository {

    @Override
    public int save(User user) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public int updateEmailValid(String email, boolean emailValid) {
        return 0;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User get(String email) {
        return null;
    }

    @Override
    public int saveOrUpdate(UserTempField userTempField) {
        return 0;
    }

    @Override
    public int deleteUserTempField(int uid) {
        return 0;
    }

    @Override
    public int deleteUserTempField(int uid, String field) {
        return 0;
    }

    @Override
    public int cleanUserTempField() {
        return 0;
    }

    @Override
    public UserTempField getUserTempField(int uid, String field) {
        return null;
    }
}
