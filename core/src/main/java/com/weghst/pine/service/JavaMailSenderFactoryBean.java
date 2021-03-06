package com.weghst.pine.service;

import com.weghst.pine.ConfigUtils;
import com.weghst.pine.Constants;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 创建 <code>JavaMailSender</code> 对象工厂类。
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class JavaMailSenderFactoryBean implements FactoryBean<JavaMailSender>, InitializingBean {

    private JavaMailSenderImpl javaMailSender;

    /**
     * 邮箱主机地址。
     */
    private String host;
    /**
     * 邮箱主机端口。
     */
    private int port;
    /**
     * 邮箱用户名。
     */
    private String username;
    /**
     * 邮箱用户密码。
     */
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
