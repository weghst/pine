package com.weghst.pine.service;

import com.weghst.pine.SpringTestSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.testng.annotations.Test;

public class JavaMailSenderTest extends SpringTestSupport {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void testSenderMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Pine Test Unit Mail");
        message.setFrom("kevinz@weghst.com");
        message.setTo("501276913@qq.com");
        message.setText("Test. NOREPLAY.");
        mailSender.send(message);
    }
}
