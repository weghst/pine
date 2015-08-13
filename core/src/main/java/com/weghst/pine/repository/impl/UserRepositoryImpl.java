package com.weghst.pine.repository.impl;

import com.weghst.pine.Pines;
import com.weghst.pine.domain.User;
import com.weghst.pine.repository.DeletedException;
import com.weghst.pine.repository.SavedException;
import com.weghst.pine.repository.UpdatedException;
import com.weghst.pine.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private static final String SAVE_SQL = "insert into t_user(email,password,emailValid,createdTime) values(?,?,?,?)";
    private static final String DELETE_BY_ID_SQL = "delete from t_user where id=?";
    private static final String UPDATE_BY_ID_SQL = "update t_user set email=?,password=?,emailValid=? where id=?";
    private static final String UPDATE_EMAIL_VALID_BY_EMAIL = "update t_user set emailValid=? where email=?";
    private static final String GET_BY_ID_SQL = "select * from t_user where id=";
    private static final String GET_BY_EMAIL_SQL = "select * from t_user where email=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        LOG.debug("Save user: {}", user);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int r = jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(SAVE_SQL);

            int i = 1;
            ps.setString(i++, user.getEmail());
            ps.setString(i++, user.getPassword());
            ps.setBoolean(i++, user.isEmailValid());
            ps.setLong(i++, Pines.unixTimestamp());
            return ps;
        }, keyHolder);

        if (r != 1) {
            throw new SavedException("保存用户失败. 预期影响[1]条记录, 实际影响[" + r + "]条记录");
        }

        user.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void delete(int id) {
        LOG.debug("Delete user by id [{}]", id);

        int r = jdbcTemplate.update(DELETE_BY_ID_SQL, id);
        if (r != 1) {
            throw new DeletedException("删除用户失败. 预期影响[1]条记录, 实际影响[" + r + "]条记录");
        }
    }

    @Override
    public void update(User user) {
        LOG.debug("Update user: {}", user);

        int r = jdbcTemplate.update(UPDATE_BY_ID_SQL, ps -> {
            int i = 1;
            ps.setString(i++, user.getEmail());
            ps.setString(i++, user.getPassword());
            ps.setBoolean(i++, user.isEmailValid());
            ps.setInt(i++, user.getId());
        });

        if (r != 1) {
            throw new UpdatedException("修改用户失败. 预期影响[1]条记录, 实际影响[" + r + "]条记录");
        }
    }

    @Override
    public void updateEmailValid(String email, boolean emailValid) {
        LOG.debug("Update user emailValid: [email={}, emailValid={}]", email, emailValid);

        int r = jdbcTemplate.update(UPDATE_EMAIL_VALID_BY_EMAIL, ps -> {
            int i = 1;
            ps.setBoolean(i++, emailValid);
            ps.setString(i++, email);
        });

        if (r != 1) {
            throw new UpdatedException("修改用户邮件验证失败. 预期影响[1]条记录, 实际影响[" + r + "]条记录");
        }
    }

    @Override
    public User get(int id) {
        PreparedStatementCreator psc = conn -> {
            PreparedStatement ps = conn.prepareStatement(GET_BY_ID_SQL);
            ps.setInt(1, id);
            return ps;
        };

        ResultSetExtractor<User> rse = rs -> {
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setId(id);
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setEmailValid(rs.getBoolean("emailValid"));
                user.setCreatedTime(rs.getLong("createdTime"));
            }
            return user;
        };

        return jdbcTemplate.query(psc, rse);
    }

    @Override
    public User get(String email) {
        PreparedStatementCreator psc = conn -> {
            PreparedStatement ps = conn.prepareStatement(GET_BY_EMAIL_SQL);
            ps.setString(1, email);
            return ps;
        };

        ResultSetExtractor<User> rse = rs -> {
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(email);
                user.setPassword(rs.getString("password"));
                user.setEmailValid(rs.getBoolean("emailValid"));
                user.setCreatedTime(rs.getLong("createdTime"));
            }
            return user;
        };

        return jdbcTemplate.query(psc, rse);
    }

}
