package com.weghst.pine.service.impl;

import com.weghst.pine.Pines;
import com.weghst.pine.UserTempFields;
import com.weghst.pine.domain.User;
import com.weghst.pine.domain.UserTempField;
import com.weghst.pine.repository.UserRepository;
import com.weghst.pine.service.UserService;
import com.weghst.pine.template.TemplateEngine;
import com.weghst.pine.util.RandomUtils;
import com.weghst.pine.util.RedisUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Service
public class UserServiceImpl implements UserService {

    public static final String EMAIL_VALIDATING_CODE_CACHE_NS = "pine.core.user.emailValidatingCode";

    @Autowired
    private UserRepository userReposy;
//    @Autowired
    private Jedis jedis;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender mailSender;

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
        String cacheName = RedisUtils.getCacheName(EMAIL_VALIDATING_CODE_CACHE_NS, user.getEmail());
        jedis.set(cacheName, inteCode, "NX", "PX", userTempField.getSurvivalMillis());

        // 处理邮件模板
        Map<String, Object> model = new HashMap<>();
        model.put("code", inteCode);

        StringWriter writer = new StringWriter();
        templateEngine.execute("com/weghst/pine/service/user-email-validating.ftl",
                template -> template.process(model, writer));

        // send mail
        mailSender.send(mimeMessage -> {

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setSubject("注册邮箱验证");
            helper.setFrom("noreplay@weghst.com");
            helper.setTo(user.getEmail());
            helper.setText(writer.toString(), true);
        });
    }

    @Transactional
    @Override
    public boolean emailValidate(String email, String code) {
        String cacheName = RedisUtils.getCacheName(EMAIL_VALIDATING_CODE_CACHE_NS, email);
        String inteCode = jedis.get(cacheName);
        if (inteCode == null) {
            User user = get(email);
            UserTempField userTempField = getUserTempField0(user.getId(),
                    UserTempFields.USER_EMAIL_VALIDATING_CODE_FIELD);
            if (userTempField != null) {
                inteCode = userTempField.getValue();
            }
        }

        if (code.equals(inteCode)) {
            jedis.del(cacheName); // 删除缓存

            userReposy.updateEmailValid(email, true);
            return true;
        }
        return false;
    }

    private UserTempField getUserTempField0(int uid, String field) {
        UserTempField userTempField = userReposy.getUserTempField(uid, field);
        long expiredTime = userTempField.getCreatedTime() + userTempField.getSurvivalMillis();
        if (userTempField != null && expiredTime > Pines.unixTimestamp()) {
            return userTempField;
        }
        return null;
    }
}
