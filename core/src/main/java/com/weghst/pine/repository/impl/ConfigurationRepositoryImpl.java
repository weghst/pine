package com.weghst.pine.repository.impl;

import com.weghst.pine.domain.Config;
import com.weghst.pine.repository.ConfigurationRepository;
import com.weghst.pine.repository.DeletedException;
import com.weghst.pine.repository.FindException;
import com.weghst.pine.repository.SavedException;
import com.weghst.pine.repository.UpdatedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ConfigurationRepositoryImpl implements ConfigurationRepository {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigurationRepositoryImpl.class);

    private static final String SAVE_SQL = "insert into t_config(key,value,remarks,comments,needReboot)" +
            " values(:key,:value,:remarks,:comments,:needReboot)";
    private static final String DELETE_BY_KEY_SQL = "delete from t_config where key=:key";
    private static final String UPDATE_SQL = "update t_config set value=:value,remarks=:remarks,comments=:comments,needReboot=:needReboot," +
            "lastUpdatedTime=UNIX_TIMESTAMP(),lastBut2UpdatedTime=lastUpdatedTime where key=:key";
    private static final String UPDATE_VALUE_SQL = "update t_config set value=:value, where key=:key";
    private static final String GET_SQL = "select `key`,`value`,`remarks`,`comments`,`needReboot`," +
            "`lastUpdatedTime`,`lastBut2UpdatedTime` from t_config where key=?";
    private static final String FIND_ALL_SQL = "select `key`,`value`,`remarks`,`comments`,`needReboot`," +
            "`lastUpdatedTime`,`lastBut2UpdatedTime` from t_config";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void save(Config config) {
        LOG.debug("Save config: {}", config);

        int r = jdbcTemplate.update(SAVE_SQL, new BeanPropertySqlParameterSource(config));
        if (r != 1) {
            throw new SavedException("保存配置失败. 预期影响[1]条记录, 实际影响[" + r + "]条记录");
        }
    }

    @Override
    public void delete(String key) {
        LOG.debug("Delete config by key: {}", key);

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("key", key);
        int r = jdbcTemplate.update(DELETE_BY_KEY_SQL, parameterSource);
        if (r != 1) {
            throw new DeletedException("删除配置失败. 预期影响[1]条记录, 实际影响[" + r + "]条记录");
        }
    }

    @Override
    public void update(Config config) {
        LOG.debug("Update config: {}", config);

        int r = jdbcTemplate.update(UPDATE_SQL, new BeanPropertySqlParameterSource(config));
        if (r != 1) {
            throw new UpdatedException("修改配置失败. 预期影响[1]条记录, 实际影响[" + r + "]条记录");
        }
    }

    @Override
    public void updateValue(String key, String value) {
        LOG.debug("Update config value: {key={}, value={}}", key, value);

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("key", key);
        parameterSource.addValue("value", value);
        int r = jdbcTemplate.update(UPDATE_VALUE_SQL, parameterSource);
        if (r != 1) {
            throw new UpdatedException("修改配置失败. 预期影响[1]条记录, 实际影响[" + r + "]条记录");
        }
    }

    @Override
    public Config get(String key) {
        List<Config> result = jdbcTemplate.query(GET_SQL, ConfigRowMapper.INSTANCE);
        if (result.size() != 1) {
            throw new FindException("查询配置失败. 预期返回[1]条记录, 实际返回[" + result.size()
                    + "]条记录");
        }
        return result.get(0);
    }

    @Override
    public List<Config> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, ConfigRowMapper.INSTANCE);
    }

    private static final class ConfigRowMapper implements RowMapper<Config> {

        static final ConfigRowMapper INSTANCE = new ConfigRowMapper();

        @Override
        public Config mapRow(ResultSet rs, int rowNum) throws SQLException {
            Config config = new Config();
            config.setKey(rs.getString("key"));
            config.setValue(rs.getString("value"));
            config.setRemarks(rs.getString("remarks"));
            config.setComments(rs.getString("comments"));
            config.setNeedReboot(rs.getBoolean("needReboot"));
            config.setLastUpdatedTime(rs.getLong("lastUpdatedTime"));
            config.setLastBut2UpdatedTime(rs.getLong("lastBut2UpdatedTime"));
            return config;
        }
    }
}
