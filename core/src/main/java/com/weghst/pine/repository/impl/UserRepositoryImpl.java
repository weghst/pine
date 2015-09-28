package com.weghst.pine.repository.impl;

import com.weghst.pine.Pines;
import com.weghst.pine.domain.User;
import com.weghst.pine.domain.UserTempField;
import com.weghst.pine.repository.DeletedException;
import com.weghst.pine.repository.SavedException;
import com.weghst.pine.repository.UpdatedException;
import com.weghst.pine.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private static final String SAVE_SQL = "insert into t_user(email,password,emailValid) values(:email,:password,:emailValid)";
    private static final String DELETE_BY_ID_SQL = "delete from t_user where id=:id";
    private static final String UPDATE_BY_ID_SQL = "update t_user set email=:email,password=:password,emailValid=:emailValid where id=:id";
    private static final String UPDATE_EMAIL_VALID_BY_EMAIL = "update t_user set emailValid=:emailValid where email=:email";
    private static final String GET_BY_ID_SQL = "select * from t_user where id=:id";
    private static final String GET_BY_EMAIL_SQL = "select * from t_user where email=:email";

    // ============================ UserTempField ================================//

    private static final String SAVE_OR_UPDATE_USER_TEMP_FIELD_SQL = "insert into t_user_temp_field(uid,field,value,createdTime,survivalMillis)" +
            " values(:uid,:field,:value,:createdTime,:survivalMillis)" +
            " on duplicate key update value=:value,createdTime=:createdTime,survivalMillis=:survivalMillis";
    private static final String DELETE_USER_TEMP_FIELD_SQL = "delete from t_user_temp_field where uid=:uid and field=:field";
    private static final String CLEAN_USER_TEMP_FIELD_SQL = "delete from t_user_temp_field where (createdTime+survivalMillis) < :expiredTime";
    private static final String GET_USER_TEMP_FIELD_SQL = "select value,createdTime,survivalMillis where uid=:uid and field=:field";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        LOG.debug("Save user: {}", user);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int r = jdbcTemplate.update(SAVE_SQL, new BeanPropertySqlParameterSource(user), keyHolder);
        if (r != 1) {
            throw new SavedException("保存用户失败. 预期影响[1]条记录, 实际影响[" + r + "]条记录");
        }

        user.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void delete(int id) {
        LOG.debug("Delete user by id [{}]", id);

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        int r = jdbcTemplate.update(DELETE_BY_ID_SQL, parameterSource);
        if (r != 1) {
            throw new DeletedException("删除用户失败. 预期影响[1]条记录, 实际影响[" + r + "]条记录");
        }
    }

    @Override
    public void update(User user) {
        LOG.debug("Update user: {}", user);

        int r = jdbcTemplate.update(UPDATE_BY_ID_SQL, new BeanPropertySqlParameterSource(user));
        if (r != 1) {
            throw new UpdatedException("修改用户失败. 预期影响[1]条记录, 实际影响[" + r + "]条记录");
        }
    }

    @Override
    public void updateEmailValid(String email, boolean emailValid) {
        LOG.debug("Update user emailValid: [email={}, emailValid={}]", email, emailValid);

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("email", email);
        parameterSource.addValue("emailValid", emailValid);
        int r = jdbcTemplate.update(UPDATE_EMAIL_VALID_BY_EMAIL, parameterSource);
        if (r != 1) {
            throw new UpdatedException("修改用户邮件验证失败. 预期影响[1]条记录, 实际影响[" + r + "]条记录");
        }
    }

    @Override
    public User get(int id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

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

        return jdbcTemplate.query(GET_BY_ID_SQL, parameterSource, rse);
    }

    @Override
    public User get(String email) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("email", email);

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

        return jdbcTemplate.query(GET_BY_EMAIL_SQL, parameterSource, rse);
    }

    @Override
    public void saveOrUpdate(UserTempField userTempField) {
        LOG.debug("SaveOrUpdate user temp field: {}", userTempField);

        int r = jdbcTemplate.update(SAVE_OR_UPDATE_USER_TEMP_FIELD_SQL,
                new BeanPropertySqlParameterSource(userTempField));
        if (r != 1) {
            throw new SavedException("保存用户临时字段失败. 预期影响[1]条记录, 实际影响[" + r + "]条记录");
        }
    }

    @Override
    public void deleteUserTempField(int uid, String field) {
        LOG.debug("Delete user temp field: [uid={}, field={}]", uid, field);

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("uid", uid);
        parameterSource.addValue("field", field);
        int r = jdbcTemplate.update(DELETE_USER_TEMP_FIELD_SQL, parameterSource);
        if (r != 1) {
            throw new DeletedException("删除用户临时字段失败. 预期影响[1]条记录, 实际影响[" + r + "]条记录");
        }
    }

    @Override
    public int cleanUserTempField() {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("expiredTime", Pines.currentTimeMillis());
        int r = jdbcTemplate.update(CLEAN_USER_TEMP_FIELD_SQL, parameterSource);
        LOG.info("清理过期用户临时字段. 影响[{}]条记录", r);

        return r;
    }

    @Override
    public UserTempField getUserTempField(int uid, String field) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("uid", uid);
        parameterSource.addValue("field", field);

        ResultSetExtractor<UserTempField> rse = rs -> {
            UserTempField userTempField = null;
            if (rs.next()) {
                userTempField = new UserTempField();
                userTempField.setUid(uid);
                userTempField.setField(field);
                userTempField.setValue(rs.getString("value"));
                userTempField.setCreatedTime(rs.getLong("createdTime"));
                userTempField.setSurvivalMillis(rs.getLong("survivalMillis"));
            }
            return userTempField;
        };
        return jdbcTemplate.query(GET_USER_TEMP_FIELD_SQL, parameterSource, rse);
    }

}
