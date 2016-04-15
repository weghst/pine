package com.weghst.pine.service.impl;

import com.weghst.pine.ConfigUtils;
import com.weghst.pine.domain.Sms;
import com.weghst.pine.service.SmsException;
import com.weghst.pine.service.SmsService;
import com.weghst.pine.util.JsonUtils;
import okhttp3.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.json.JsonObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
@Service
public class AliSmsServiceImpl implements SmsService {

    private static final Logger LOG = LoggerFactory.getLogger(AliSmsServiceImpl.class);
    private static final HttpUrl SMS_HTTP_URL = HttpUrl.parse("http://gw.api.taobao.com/router/rest");

    static final String ALI_SMS_APP_KEY = "pine.ali.sms.appKey";
    static final String ALI_SMS_APP_SECRET = "pine.ali.sms.appSecret";

    private OkHttpClient httpClient = new OkHttpClient();

    @Override
    public void send(Sms sms) {
        Map<String, String> params = new HashMap<>();
        params.put("method", "alibaba.aliqin.fc.sms.num.send");
        params.put("app_key", ConfigUtils.getString(ALI_SMS_APP_KEY));
        params.put("timestamp", getTimestamp());
        params.put("format", "json");
        params.put("v", "2.0");
        params.put("sign_method", "md5");
        params.put("sms_type", "normal");
        params.put("sms_free_sign_name", sms.getSignName());
        params.put("sms_param", JsonUtils.writeValueAsString(sms.getParams()));
        params.put("rec_num", sms.getMobile());
        params.put("sms_template_code", sms.getTemplateCode());
        params.put("sign", buildSign(params).toUpperCase());

        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            formBodyBuilder.add(entry.getKey(), entry.getValue());
        }

        Request request = new Request.Builder().url(SMS_HTTP_URL).post(formBodyBuilder.build()).build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                LOG.error("发送短信失败 {}-> {}", request, response);
                throw new SmsException("发送短信失败[httpStatus: " + response.code() + "]");
            }

            JsonObject result = JsonUtils.readValue(response.body().bytes(), JsonObject.class);
            if (result.containsKey("error_response")) {
                LOG.error("发送短信失败 {}-> {}", request, response.body().string());
                throw new SmsException("发送短信失败 -> " + response.body().string());
            }

            LOG.debug("短信[{}]发送成功", sms);
        } catch (IOException e) {
            throw new SmsException("发送短信失败[" + request.toString() + "]", e);
        }
    }

    private String buildSign(Map<String, String> params) {
        String[] keys = params.keySet().toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        Arrays.sort(keys);

        String appSecret = ConfigUtils.getString(ALI_SMS_APP_SECRET);
        StringBuilder sb = new StringBuilder();
        sb.append(appSecret);
        for (String key : keys) {
            String v = params.get(key);
            sb.append(key).append(v);
        }
        sb.append(appSecret);

        return DigestUtils.md5DigestAsHex(sb.toString().getBytes(StandardCharsets.UTF_8));
    }

    private String getTimestamp() {
        FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
        return fdf.format(Calendar.getInstance());
    }
}
