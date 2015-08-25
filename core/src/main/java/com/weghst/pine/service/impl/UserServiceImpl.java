package com.weghst.pine.service.impl;

import com.weghst.pine.UserTempFields;
import com.weghst.pine.domain.User;
import com.weghst.pine.domain.UserTempField;
import com.weghst.pine.repository.UserRepository;
import com.weghst.pine.service.UserService;
import com.weghst.pine.util.RandomUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    public static final String EMAIL_VALIDATING_CODE_CACHE_NAME = "pine.core.user.emailValidatingCode";

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private UserRepository userReposy;

    @Transactional
    @Override
    public void save(User user) {
        userReposy.save(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userReposy.delete(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        userReposy.update(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User get(int id) {
        return userReposy.get(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User get(String email) {
        return userReposy.get(email);
    }

    @Override
    public void register(User user) {
        save(user);

        sendEmailValidate(user);
    }

    @Transactional
    @Override
    public void sendEmailValidate(User user) {
        String inteCode = String.valueOf(RandomUtils.nextCode6());

        UserTempField userTempField = new UserTempField();
        userTempField.setUid(user.getId());
        userTempField.setField(UserTempFields.USER_EMAIL_VALIDATING_CODE_FIELD);
        userTempField.setValue(inteCode);
        userTempField.setSurvivalMillis(24 * 60 * 60 * 1000);
        userReposy.saveOrUpdate(userTempField);

        // 将验证码放入缓存
        Cache cache = cacheManager.getCache(EMAIL_VALIDATING_CODE_CACHE_NAME);
        cache.put(user.getEmail(), inteCode);

        // send mail
    }

    @Transactional
    @Override
    public boolean emailValidate(String email, String code) {
        Cache cache = cacheManager.getCache(EMAIL_VALIDATING_CODE_CACHE_NAME);
        String inteCode = cache.get(email, String.class);
        if (inteCode == null) {
            User user = get(email);
            UserTempField userTempField = userReposy.getUserTempField(user.getId(),
                    UserTempFields.USER_EMAIL_VALIDATING_CODE_FIELD);
            if(userTempField!=null) {
                inteCode = userTempField.getValue();
            }
        }

        if(code.equals(inteCode)) {
            userReposy.updateEmailValid(email, true);
            return true;
        }
        return false;
    }
}
