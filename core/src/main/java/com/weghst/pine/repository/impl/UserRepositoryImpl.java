package com.weghst.pine.repository.impl;

import com.weghst.pine.Pines;
import com.weghst.pine.domain.User;
import com.weghst.pine.domain.UserTempField;
import com.weghst.pine.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin Zou <kevinz@weghst.com>
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public int save(User user) {
        user.setCreatedTime(Pines.unixTimestamp());

        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.insert("com.weghst.pine.domain.User.save", user);
    }

    @Override
    public int delete(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.delete("com.weghst.pine.domain.User.deleteById", id);
    }

    @Override
    public int update(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.update("com.weghst.pine.domain.User.updateById", user);
    }

    @Override
    public int updateEmailValid(String email, boolean emailValid) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("emailValid", emailValid);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.update("com.weghst.pine.domain.User.updateEmailValidByEmail", params);
    }

    @Override
    public User get(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectOne("com.weghst.pine.domain.User.getById", id);
    }

    @Override
    public User get(String email) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectOne("com.weghst.pine.domain.User.getByEmail", email);
    }

    @Override
    public int saveOrUpdate(UserTempField userTempField) {
        userTempField.setCreatedTime(Pines.unixTimestamp());

        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.insert("com.weghst.pine.domain.UserTempField.saveOrUpdate", userTempField);
    }

    @Override
    public int deleteUserTempField(int uid, String field) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
        params.put("field", field);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.insert("com.weghst.pine.domain.UserTempField.deleteByUidAndField", params);
    }

    @Override
    public int cleanUserTempField() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.insert("com.weghst.pine.domain.UserTempField.cleanByExpiredTime", Pines.unixTimestamp());
    }

    @Override
    public UserTempField getUserTempField(int uid, String field) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
        params.put("field", field);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectOne("com.weghst.pine.domain.UserTempField.getByUidAndField", params);
    }
}
