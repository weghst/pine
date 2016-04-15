package com.weghst.pine.domain;

import com.google.common.base.MoreObjects;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信实体对象。
 *
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class Sms {

    /**
     * 短信目标手机号码。
     */
    private String mobile;
    /**
     * 短信签名名称。
     */
    private String signName;
    /**
     * 短信参数。
     */
    private Map<String, String> params;
    /**
     * 短信模板ID。
     */
    private String templateCode;

    public String getMobile() {
        return mobile;
    }

    public Sms setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getSignName() {
        return signName;
    }

    public Sms setSignName(String signName) {
        this.signName = signName;
        return this;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public Sms setParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public Sms addParam(String name, String value) {
        if (params == null) {
            params = new HashMap<>();
        }
        params.put(name, value);
        return this;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public Sms setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("mobile", mobile)
                .add("signName", signName)
                .add("params", params)
                .add("templateCode", templateCode)
                .toString();
    }
}
