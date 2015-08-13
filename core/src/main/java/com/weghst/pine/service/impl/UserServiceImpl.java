package com.weghst.pine.service.impl;

import com.weghst.pine.domain.User;
import com.weghst.pine.repository.UserRepository;
import com.weghst.pine.service.UserService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Configuration configuration;
    @Autowired
    private JavaMailSender mailSender;

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

    @Transactional
    @Override
    public void emailValidate(String email, String code) {
        // FIXME 待完善
        try {
            Template template = configuration.getTemplate("com/weghst/pine/service/user-email-validating.ftl");
            Map<String, String> modal = new HashMap<>();
            modal.put("code", code);

            StringWriter writer = new StringWriter();
            template.process(modal, writer);

            MimeMessagePreparator preparator = mimeMessage -> {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                helper.setTo(email);
                helper.setFrom("kevinz@weghst.com");

                helper.setText(writer.toString(), true);
            };

            mailSender.send(preparator);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
