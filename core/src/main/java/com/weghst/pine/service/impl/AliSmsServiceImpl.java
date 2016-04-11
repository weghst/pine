package com.weghst.pine.service.impl;

import com.weghst.pine.ConfigUtils;
import com.weghst.pine.domain.Sms;
import com.weghst.pine.service.SmsService;
import com.weghst.pine.util.JsonUtils;
import okhttp3.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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

    static final String ALI_SMS_API_URL = "pine.ali.sms.apiUrl";
    static final String ALI_SMS_APP_KEY = "pine.ali.sms.appKey";
    static final String ALI_SMS_APP_SECRET = "pine.ali.sms.appSecret";

    private OkHttpClient httpClient = new OkHttpClient();

    @Override
    public void send(Sms sms) {
        // 23342731
        // b324481b5e0f7a2438c7bb7c4a9f8629

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

//        HttpUrl httpUrl = HttpUrl.parse(ConfigUtils.getString(ALI_SMS_API_URL));
//        HttpUrl.Builder httpUrlBuilder = httpUrl.newBuilder();
//        for (Map.Entry<String, String> entry : params.entrySet()) {
//            httpUrlBuilder.setQueryParameter(entry.getKey(), entry.getValue());
//        }

        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }

        Request request = new Request.Builder()
                .url(ConfigUtils.getString(ALI_SMS_API_URL))
                .post(builder.build())
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        .enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println(response.body());
//            }
//        });
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
