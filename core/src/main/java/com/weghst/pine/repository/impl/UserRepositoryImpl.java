package com.weghst.pine.repository.impl;

import com.weghst.pine.Pines;
import com.weghst.pine.domain.User;
import com.weghst.pine.domain.UserTempField;
import com.weghst.pine.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SqlSession sqlSessionTemplate;

    @Override
    public int save(User user) {
        user.setCreatedTime(Pines.unixTimestamp());

        return sqlSessionTemplate.insert("com.weghst.pine.domain.User.save", user);
    }

    @Override
    public int delete(int id) {
        int r = sqlSessionTemplate.delete("com.weghst.pine.domain.User.deleteById", id);

        deleteUserTempField(id);
        return r;
    }

    @Override
    public int update(User user) {
        return sqlSessionTemplate.update("com.weghst.pine.domain.User.updateById", user);
    }

    @Override
    public int updateEmailValid(String email, boolean emailValid) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("emailValid", emailValid);

        return sqlSessionTemplate.update("com.weghst.pine.domain.User.updateEmailValidByEmail", params);
    }

    @Override
    public User get(int id) {
        return sqlSessionTemplate.selectOne("com.weghst.pine.domain.User.getById", id);
    }

    @Override
    public User get(String email) {
        return sqlSessionTemplate.selectOne("com.weghst.pine.domain.User.getByEmail", email);
    }

    @Override
    public int saveOrUpdate(UserTempField userTempField) {
        return sqlSessionTemplate.insert("com.weghst.pine.domain.UserTempField.saveOrUpdate", userTempField);
    }

    @Override
    public int deleteUserTempField(int uid) {
        return sqlSessionTemplate.insert("com.weghst.pine.domain.UserTempField.deleteByUid", uid);
    }

    @Override
    public int deleteUserTempField(int uid, String field) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
        params.put("field", field);

        return sqlSessionTemplate.insert("com.weghst.pine.domain.UserTempField.deleteByUidAndField", params);
    }

    @Override
    public int cleanUserTempField() {
        return sqlSessionTemplate.insert("com.weghst.pine.domain.UserTempField.cleanByExpiredTime", Pines.unixTimestamp());
    }

    @Override
    public UserTempField getUserTempField(int uid, String field) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
        params.put("field", field);

        return sqlSessionTemplate.selectOne("com.weghst.pine.domain.UserTempField.getByUidAndField", params);
    }
}
