package com.weghst.pine.repository.impl;

import com.weghst.pine.domain.User;
import com.weghst.pine.domain.UserTempField;
import com.weghst.pine.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Zou Yong(zouyong@mychebao.com)
 * @date 2015-11-28 14:11
 */
@Repository
public class MyBatisUserRepositoryImpl implements UserRepository {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void save(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("com.weghst.pine.domain.User.save", user);
    }

    @Override
    public void delete(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("com.weghst.pine.domain.User.deleteById", id);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void updateEmailValid(String email, boolean emailValid) {

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
    public void saveOrUpdate(UserTempField userTempField) {

    }

    @Override
    public void deleteUserTempField(int uid, String field) {

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
