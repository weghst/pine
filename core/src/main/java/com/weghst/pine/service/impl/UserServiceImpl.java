package com.weghst.pine.service.impl;

import com.weghst.pine.ConfigUtils;
import com.weghst.pine.Pines;
import com.weghst.pine.UserTempFields;
import com.weghst.pine.domain.Sms;
import com.weghst.pine.domain.User;
import com.weghst.pine.domain.UserTempField;
import com.weghst.pine.repository.UserRepository;
import com.weghst.pine.repository.UserTempFieldRepository;
import com.weghst.pine.service.*;
import com.weghst.pine.template.TemplateEngine;
import com.weghst.pine.util.RandomUtils;
import com.weghst.pine.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     *
     */
    public static final String MOBILE_VALIDATING_CODE_CACHE_NS = "pine:user:mobileValidatingCode";
    /**
     *
     */
    public static final String EMAIL_VALIDATING_CODE_CACHE_NS = "pine:user:emailValidatingCode";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTempFieldRepository userTempFieldRepository;
    @Autowired
    @Qualifier("pine.core.templateEngine")
    private TemplateEngine templateEngine;
    @Autowired
    @Qualifier("pine.core.redisTemplate")
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SmsService smsService;

    @Transactional
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User get(int id) {
        return userRepository.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User get(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public void registerForMobile(User user, String code) throws CodeIncorrectException {
        if (!mobileValidate(user.getMobile(), code)) {
            LOG.info("用户[{}]注册输入的验证码[{}]错误", user, code);
            throw new CodeIncorrectException("错误码的验证码[" + code + "]");
        }

        user.setMobileValid(true);
        userRepository.save(user);
    }

    @Override
    public String sendMobileValidate(String mobile) {
        String inteCode = String.valueOf(RandomUtils.nextCode6());

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String cacheName = RedisUtils.getCacheName(MOBILE_VALIDATING_CODE_CACHE_NS, mobile);
        valueOperations.set(cacheName, inteCode, ConfigUtils.getInt("pine.user.mobileRegister.code.expiredTime"),
                TimeUnit.MILLISECONDS);

        Sms sms = new Sms();
        sms.setMobile(mobile)
                .setSignName("注册验证")
                .setTemplateCode(ConfigUtils.getString("pine.user.mobileRegister.sms.templateCode"))
                .addParam("code", inteCode)
                .addParam("product", ConfigUtils.getString("pine.vendor", "Weghst Pine"));
        smsService.send(sms);

        return inteCode;
    }

    @Override
    public boolean mobileValidate(String mobile, String code) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String cacheName = RedisUtils.getCacheName(MOBILE_VALIDATING_CODE_CACHE_NS, mobile);

        String inteCode = valueOperations.get(cacheName);
        return code.equals(inteCode);
    }

    @Override
    public void registerForEmail(User user) {
        save(user);

        sendEmailValidate(user);
    }

    @Override
    public User loginForEmail(String email, String password) {
        User user = get(email);
        if (user == null) {
            throw new UserNotFoundException("未发现邮箱为[" + email + "]的用户");
        }

        if (!Objects.equals(password, user.getPassword())) {
            throw new PasswordNotMatchedException("用户[" + email + "]输入密码不匹配");
        }

        return user;
    }

    @Override
    public User loginForMobile(String mobile, String password) throws UserNotFoundException, PasswordNotMatchedException {
        User user = userRepository.getByMobile(mobile);
        if (user == null) {
            throw new UserNotFoundException("未发现手机为[" + mobile + "]的用户");
        }

        if (!Objects.equals(password, user.getPassword())) {
            throw new PasswordNotMatchedException("用户[" + mobile + "]输入密码不匹配");
        }
        return user;
    }

    @Transactional
    @Override
    public void sendEmailValidate(User user) {
        String inteCode = String.valueOf(RandomUtils.nextCode6());

        // 将验证码放入缓存
        int expiredTime = ConfigUtils.getInt("pine.user.emailRegister.code.expiredTime", 24 * 60 * 60 * 1000);
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String cacheName = RedisUtils.getCacheName(EMAIL_VALIDATING_CODE_CACHE_NS, user.getEmail());
        valueOperations.set(cacheName, inteCode, expiredTime, TimeUnit.MINUTES);

        // 处理邮件模板
        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getEmail());
        model.put("vendor", ConfigUtils.getString("pine.vendor", "Weghst Pine"));
        model.put("activationUrl", ConfigUtils.getString("pine.user.register.activationUrl"));
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
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String inteCode = valueOperations.get(cacheName);
        if (inteCode == null) {
            User user = get(email);
            UserTempField userTempField = getUserTempField0(user.getId(),
                    UserTempFields.USER_EMAIL_VALIDATING_CODE_FIELD);
            if (userTempField != null) {
                inteCode = userTempField.getValue();
            }
        }

        if (code.equals(inteCode)) {
            redisTemplate.delete(cacheName);// 删除缓存

            userRepository.updateEmailValid(email, true);
            return true;
        }
        return false;
    }

    private UserTempField getUserTempField0(long uid, String field) {
        UserTempField userTempField = userTempFieldRepository.getUserTempField(uid, field);
        long expiredTime = userTempField.getCreatedTime() + userTempField.getSurvivalMillis();
        if (userTempField != null && expiredTime > Pines.unixTimestamp()) {
            return userTempField;
        }
        return null;
    }
}
