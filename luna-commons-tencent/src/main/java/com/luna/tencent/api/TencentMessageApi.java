package com.luna.tencent.api;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.luna.common.http.HttpUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Luna@win10
 * @date 2020/5/14 20:46
 */
public class TencentMessageApi {

    private static final Logger log = LoggerFactory.getLogger(TencentMessageApi.class);

    /**
     * 发送短信
     * 
     * @param id 账号ID
     * @param key 账号KEY
     * @param phones +86 手机号 Array of String
     * @param appid 应用ID
     * @param sign 短信签名
     * @param templateId 模板ID
     * @param message 模板填充 Array of String
     * @return
     * @throws Exception
     */
    public static String sendMsgReq(String id, String key, List<String> phones, String appid, String sign,
        String templateId, List<String> message) throws Exception {
        log.info("sendMsgReq start id={}, key={}, phones={}, appid={}, sign={}, templateId={}, message={}", id, key,
            phones, appid, sign, templateId, message);
        Map<String, Object> map = Maps.newHashMap();
        map.put("PhoneNumberSet", phones);
        map.put("TemplateID", templateId);
        map.put("SmsSdkAppid", appid);
        map.put("Sign", sign);
        map.put("TemplateParamSet", message);
        String body = JSON.toJSONString(map);
        TreeMap postHeader = TencentCloudAPITC3.getPostHeader(id, key, "sms", TencentConstant.HOST_SMS, "", "SendSms",
            "2019-07-11", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.HOST_SMS, "/", postHeader, null, body);
        return HttpUtils.checkResponseAndGetResult(httpResponse, true);
    }

}
