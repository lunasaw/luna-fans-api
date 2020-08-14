package com.luna.api.xuexiaoyi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luna.common.http.HttpUtils;
import com.luna.common.http.HttpUtilsConstant;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


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
    public static JSONObject getAnswer(String key, String language) {

        String s = "{\"keyword\":\"" + key + "\"}";
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", HttpUtilsConstant.JSON);
        map.put("Accept-Language", language);
        map.put("token", XueXiaoYiConstant.NEW_TOKEN);
        map.put("t", "1592575114331");
        map.put("app-version", "null");
        map.put("device", "c5a0c5e3e113aac2583a203f696a63a16faf1d7b254db7d8d1914faccfc8a36b");
        map.put("s", "c88602c7632a9cfcb499ca506fd48f00");
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
            System.out.println(answer1);
            String answer = getWord(answer1);
            System.out.println(answer);
        }
    }

}
