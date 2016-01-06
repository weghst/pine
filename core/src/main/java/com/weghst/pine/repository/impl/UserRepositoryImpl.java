package com.weghst.pine.repository.impl;

import com.weghst.pine.Pines;
import com.weghst.pine.domain.User;
import com.weghst.pine.domain.UserTempField;
import com.weghst.pine.repository.UserRepository;
import com.weghst.pine.repository.support.SqlSessionDaoSupport;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Repository
public class UserRepositoryImpl extends SqlSessionDaoSupport implements UserRepository {

    @Override
    public int save(User user) {
        user.setCreatedTime(Pines.unixTimestamp());

        return getSqlSession().insert("com.weghst.pine.domain.User.save", user);
    }

    @Override
    public int delete(int id) {
        int r = getSqlSession().delete("com.weghst.pine.domain.User.deleteById", id);

        deleteUserTempField(id);
        return r;
    }

    @Override
    public int update(User user) {
        return getSqlSession().update("com.weghst.pine.domain.User.updateById", user);
    }

    @Override
    public int updateEmailValid(String email, boolean emailValid) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("emailValid", emailValid);

        return getSqlSession().update("com.weghst.pine.domain.User.updateEmailValidByEmail", params);
    }

    @Override
    public User get(int id) {
        return getSqlSession().selectOne("com.weghst.pine.domain.User.getById", id);
    }

    @Override
    public User get(String email) {
        return getSqlSession().selectOne("com.weghst.pine.domain.User.getByEmail", email);
    }

    @Override
    public int saveOrUpdate(UserTempField userTempField) {
        return getSqlSession().insert("com.weghst.pine.domain.UserTempField.saveOrUpdate", userTempField);
    }

    @Override
    public int deleteUserTempField(int uid) {
        return getSqlSession().insert("com.weghst.pine.domain.UserTempField.deleteByUid", uid);
    }

    @Override
    public int deleteUserTempField(int uid, String field) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
        params.put("field", field);

        return getSqlSession().insert("com.weghst.pine.domain.UserTempField.deleteByUidAndField", params);
    }

    @Override
    public int cleanUserTempField() {
        return getSqlSession().insert("com.weghst.pine.domain.UserTempField.cleanByExpiredTime", Pines.unixTimestamp());
    }

    @Override
    public UserTempField getUserTempField(int uid, String field) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
        params.put("field", field);

        return getSqlSession().selectOne("com.weghst.pine.domain.UserTempField.getByUidAndField", params);
    }
}
