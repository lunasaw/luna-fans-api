package com.luna.api.xuexiaoyi.api;

import com.alibaba.fastjson.JSON;
import com.luna.api.xuexiaoyi.constant.XueXiaoYiConstant;
import com.luna.api.xuexiaoyi.dto.XueXiaoYiResultDTO;
import com.luna.common.http.HttpUtils;
import com.luna.common.http.HttpUtilsConstant;
import com.luna.common.utils.text.StringUtils;
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
    public static List<XueXiaoYiResultDTO> getAnswer(String key, String language) {

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
        String response = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        return JSON.parseArray(JSON.parseObject(response).get("data").toString(), XueXiaoYiResultDTO.class);
    }

    /**
     * 学小易答案解析
     *
     * @param datas
     */
    public static void getWord(List<XueXiaoYiResultDTO> datas) {
        for (XueXiaoYiResultDTO data : datas) {
            System.out.println(JSON.toJSONString(data.getQuestion()));
            System.out.println("答案===>");
            System.out.println(JSON.toJSONString(data.getAnswer()));
        }
    }

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String word = scanner.nextLine();
            if (StringUtils.isNotEmpty(word)) {
                List<XueXiaoYiResultDTO> answer = getAnswer(word, XueXiaoYiConstant.EN_US);
                getWord(answer);
            }
        }
    }

}
