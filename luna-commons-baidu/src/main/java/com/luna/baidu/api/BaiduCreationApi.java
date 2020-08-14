package com.luna.baidu.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.luna.baidu.dto.write.EventContextDTO;
import com.luna.baidu.dto.write.HotEventDTO;
import com.luna.baidu.dto.write.WriterResultCheckDTO;
import com.luna.common.http.HttpUtils;
import com.luna.common.http.HttpUtilsConstant;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.List;

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
    public static List<HotEventDTO> hotEvent(String key, String domain) {
        String body = "{\"domain\":\"" + domain + "\"}";
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.HOT_EVENT,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON),
            ImmutableMap.of("access_token", key),
            body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        JSONObject response = JSON.parseObject(s);
        return JSON.parseArray(response.getString("content"), HotEventDTO.class);
    }

    /**
     * 自动返回最近一周的最新脉络
     * 
     * @param key
     * @return
     */
    public static List<EventContextDTO> eventContext(String key) {
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.EVENT_CONTEXT,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON),
            ImmutableMap.of("access_token", key),
            "");
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        JSONObject response = JSON.parseObject(s);
        return JSON.parseArray(response.getString("content"), EventContextDTO.class);
    }

    /**
     * 获取城市天气情况
     * 
     * @param key
     * @param projectId 项目ID
     * @param city 城市代码 @https://ai.baidu.com/file/3DD79E311FEE4988AC1246FB28FBB7BC
     * @return
     */
    public static WriterResultCheckDTO weather(String key, String projectId, String city) {
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.WRITING,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED),
            ImmutableMap.of("access_token", key, "project_id", projectId, "city", city),
            ImmutableMap.of());
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        WriterResultCheckDTO writerResultCheckDTO = JSON.parseObject(s, WriterResultCheckDTO.class);
        System.out.println(JSON.toJSONString(writerResultCheckDTO));
        return writerResultCheckDTO;
    }
}
