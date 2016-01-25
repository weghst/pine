package com.weghst.pine.repository.impl;

import com.weghst.pine.domain.Config;
import com.weghst.pine.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;

import java.util.List;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class LdapConfigRepositoryImpl implements ConfigRepository {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Override
    public int save(Config config) {
        return 0;
    }

    @Override
    public int delete(String key) {
        return 0;
    }

    @Override
    public int update(Config config) {
        return 0;
    }

    @Override
    public int updateValue(String key, String value) {
        return 0;
    }

    @Override
    public Config get(String key) {
        return null;
    }

    @Override
    public List<Config> findAll() {
        return null;
    }
}
