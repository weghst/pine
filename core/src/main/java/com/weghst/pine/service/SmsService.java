package com.weghst.pine.service;

import com.weghst.pine.domain.Sms;

/**
 * 短信服务接口。
 *
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public interface SmsService {

    /**
     * 发送短信。
     *
     * @param sms 短信
     */
    void send(Sms sms);
}
