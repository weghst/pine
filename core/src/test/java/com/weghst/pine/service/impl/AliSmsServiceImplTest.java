package com.weghst.pine.service.impl;

import com.weghst.pine.ConfigUtils;
import com.weghst.pine.SpringTestSupport;
import com.weghst.pine.domain.Sms;
import com.weghst.pine.service.SmsService;
import com.weghst.pine.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class AliSmsServiceImplTest extends SpringTestSupport {

    @Autowired
    private SmsService smsService;

    @Test
    public void testSend() throws Exception {
        ConfigUtils.setProperty(AliSmsServiceImpl.ALI_SMS_APP_KEY, "23342731");
        ConfigUtils.setProperty(AliSmsServiceImpl.ALI_SMS_APP_SECRET, "b324481b5e0f7a2438c7bb7c4a9f8629");

        Sms sms = new Sms();
        sms.setMobile("13085162323")
                .setSignName("注册验证")
                .setTemplateCode("SMS_7425734")
                .addParam("code", String.valueOf(RandomUtils.nextCode6()))
                .addParam("product", "Weghst");
        smsService.send(sms);
    }
}