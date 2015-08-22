package com.weghst.pine.service;

import com.weghst.pine.ConfigUtils;
import com.weghst.pine.Constants;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 */
public class JavaMailSenderFactoryBean implements FactoryBean<JavaMailSender>, InitializingBean {

    private JavaMailSenderImpl javaMailSender;

    private String host;
    private int port;
    private String username;
    private String password;

    @Override
    public void afterPropertiesSet() throws Exception {
        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setDefaultEncoding(ConfigUtils.getString(Constants.ENCODING_PROP, "UTF-8"));
        javaMailSender.setHost(getHost());
        javaMailSender.setPort(getPort());
        javaMailSender.setUsername(getUsername());
        javaMailSender.setPassword(getPassword());
    }

    @Override
    public JavaMailSender getObject() throws Exception {
        return javaMailSender;
    }

    @Override
    public Class<?> getObjectType() {
        return JavaMailSender.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getHost() {
        String h = host;
        if (h == null) {
            h = ConfigUtils.getString(MailConstants.MAIL_SENDER_HOST_PROP);
        }
        return h;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        int p = port;
        if (p <= 0) {
            p = ConfigUtils.getInt(MailConstants.MAIL_SENDER_PORT_PROP);
        }
        return p;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        String u = username;
        if (u == null) {
            u = ConfigUtils.getString(MailConstants.MAIL_SENDER_USERNAME_PROP);
        }
        return u;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        String p = password;
        if (p == null) {
            p = ConfigUtils.getString(MailConstants.MAIL_SENDER_PASSWORD_PROP);
        }
        return p;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
