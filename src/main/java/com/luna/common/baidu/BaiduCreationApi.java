package com.luna.common.baidu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.luna.common.http.HttpUtils;
import com.luna.common.http.HttpUtilsConstant;
import com.luna.common.utils.StringUtils;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author Luna@win10
 * @date 2020/5/25 15:37
 */
public class BaiduCreationApi {

    /**
     * @param domain 合法domain=“娱乐”格式，如国际、国内、军事、财经、科技、房产、娱乐、教育、社会、旅游、体育、汽车、游戏，通过接口获取
     * @return
     * @throws IOException
     */
    public static List<JSONObject> hotEvent(String domain) throws IOException {
        String body = "{\"domain\":\"" + domain + "\"}";
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.HOT_EVENT,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON),
            ImmutableMap.of("access_token", BaiduApiContent.BAIDU_KEY),
            body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        List<JSONObject> content = JSON.parseArray(response.getString("content"), JSONObject.class);
        return content;
    }

    /**
     * 自动返回最近一周的最新脉络
     * 
     * @return
     * @throws IOException
     */
    public static Map<String, List<JSONObject>> eventContext() throws IOException {
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.EVENT_CONTEXT,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON),
            ImmutableMap.of("access_token", BaiduApiContent.BAIDU_KEY),
            "");
        JSONObject response = HttpUtils.getResponse(httpResponse);
        List<JSONObject> content = JSON.parseArray(response.getString("content"), JSONObject.class);
        Map<String, List<JSONObject>> map = Maps.newHashMap();
        for (int i = 0; i < content.size(); i++) {
            String event_name = content.get(i).getString("event_name");
            List<JSONObject> vein = JSON.parseArray(content.get(i).getString("vein"), JSONObject.class);
            map.put(event_name, vein);
        }
        return map;
    }

    /** 匹配中文正则表达式 */
    private final static String PATTERN = "[\\u4e00-\\u9fa5]+";

    /**
     * 文本匹配 判断toMatch里是否存在prepare
     *
     * @param prepare 判断字符
     * @param toMatch 原始字符
     * @return
     */
    public static boolean checkKnowledge(String prepare, String toMatch) {
        if (StringUtils.isEmpty(prepare) || StringUtils.isEmpty(toMatch)) {
            return false;
        }
        Pattern pattern = Pattern.compile(PATTERN);
        // OCR识别出的文字用换行符分隔
        String[] split = toMatch.split("\n");
        for (String str : split) {
            if (pattern.matcher(str).find()) {
                // 匹配到中文
                // 判断是否是知识点
                if (str.replaceAll(" ", "").contains(prepare.replaceAll(" ", ""))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取城市天气情况
     * 
     * @param city
     * @return
     * @throws IOException
     */
    public static Map<String, String> writing(String city) throws IOException {
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.WRITING,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED),
            ImmutableMap.of("access_token", BaiduApiContent.BAIDU_KEY, "project_id", "41168", "city", city),
            ImmutableMap.of());
        JSONObject response = HttpUtils.getResponse(httpResponse);
        JSONObject jsonObject = JSON.parseObject(response.get("result").toString());
        Map<String, String> map = new HashMap<>();
        String summary = jsonObject.getString("summary");
        String texts = jsonObject.getString("texts");
        String title = jsonObject.getString("title");
        map.put("summary", summary);
        map.put("texts", texts);
        map.put("title", title);
        return map;
    }

}
