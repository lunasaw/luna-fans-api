package com.luna.common.tencent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luna.common.http.HttpUtils;

/**
 * @author Luna@win10
 * @date 2020/5/14 20:46
 */
public class TencentMessage {

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
    public static JSONObject sendMsgReq(String id, String key, String[] phones, String appid, String sign,
        String templateId, String[] message)
        throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("PhoneNumberSet", phones);
        map.put("TemplateID", templateId);
        map.put("SmsSdkAppid", appid);
        map.put("Sign", sign);
        map.put("TemplateParamSet", message);
        String body = JSONArray.toJSONString(map);
        TreeMap postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "sms", TencentConstant.HOST_SMS, "", "SendSms", "2019-07-11",
                body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.HOST_SMS, "/", postHeader, null, body);
        return HttpUtils.getResponse(httpResponse);
    }

    /**
     * 发送短信结果解析
     *
     * @param phones +86手机 Arrays
     * @param templateId 模板编号
     * @param param 参数
     * @return 包含对应手机号的发送信息结果集 1 成功 0 失败
     */
    public static Map sendMsg(String id, String key, String[] phones, String templateId, String[] param, String appId,
        String sign)
        throws Exception {
        JSONObject jsonObject = TencentMessage.sendMsgReq(id, key, phones, appId, sign, templateId, param);
        String state = JSON.parseObject(jsonObject.get("Response").toString()).get("SendStatusSet").toString();
        List<JSONObject> datas = JSON.parseArray(state, JSONObject.class);
        Map<String, String> map = new HashMap<>(phones.length);
        for (int i = 0; i < datas.size(); i++) {
            map.put(datas.get(i).get("PhoneNumber").toString(), datas.get(i).get("Fee").toString());
        }
        return map;
    }
}
