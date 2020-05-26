package com.luna.common.xuexiaoyi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.luna.common.utils.HttpUtilsConstant;
import org.apache.http.HttpResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.luna.common.utils.HttpUtils;

/**
 * @author Luna@win10
 * @date 2020/4/28 11:30
 */
public class XueXiaoYi {

    /**
     * 学小易
     *
     * @param key
     * @param language
     * @return
     * @throws IOException
     */
    public static JSONObject getAnswer(String key, String language) throws IOException {

        String s = "{\"keyword\":\"" + key + "\"}";
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", HttpUtilsConstant.JSON);
        map.put("Accept-Language", language);
        map.put("token", XueXiaoYiConstant.TOKEN);
        map.put("t", "1589445251850");
        map.put("app-version", "null");
        map.put("device", "26908752fd5dfa3a5444c8d7fa2e351cac25f32838cf2f3c008f0a4926c2a844");
        map.put("s", "2a8b885209846ee116eb4d7fcd989235");
        HttpResponse httpResponse =
            HttpUtils.doPost(XueXiaoYiConstant.HOST, XueXiaoYiConstant.PATH, map, null, s);
        return HttpUtils.getResponse(httpResponse);
    }

    /**
     * 学小易答案解析
     *
     * @param response
     */
    public static String getWord(JSONObject response) {
        List<JSONObject> datas = JSON.parseArray(response.get("data").toString(), JSONObject.class);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < datas.size(); i++) {
            String a = datas.get(i).get("a").toString();
            String q = datas.get(i).get("q").toString();
            buffer.append(q + "\n");
            buffer.append("答案===>" + a + "\n");
            buffer.append("==================================\n");
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String word = scanner.nextLine();
            JSONObject answer1 = getAnswer(word, XueXiaoYiConstant.EN_US);
            String answer = getWord(answer1);
            System.out.println(answer);
        }
    }

}
