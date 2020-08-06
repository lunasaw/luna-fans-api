package com.luna.api.smMs;

import com.alibaba.fastjson.JSONObject;
import com.luna.api.smMs.constant.SmMsConstant;
import com.luna.common.http.HttpUtils;
import com.luna.common.http.HttpUtilsConstant;
import org.apache.http.HttpResponse;

import java.util.HashMap;

/**
 * @Package: com.luna.api.smMs
 * @ClassName: ImageApiFromString
 * @Author: luna
 * @CreateTime: 2020/7/27 12:23
 * @Description:
 */
public class ImageApiFromString {

    /**
     * 上传文件
     * 
     * @param token
     * @param path
     * @return
     */
    public static JSONObject upload(String token, String path) {
        HashMap<String, String> header = new HashMap<>();
        header.put("Authorization", token);
        HashMap<String, String> bodies = new HashMap<>();
        bodies.put("smfile", path);
        return HttpUtils.getResponse(HttpUtils.doPost(SmMsConstant.HOST, "/upload", header, null, bodies));
    }

    /**
     * 获取最近上传历史
     * 
     * @param token
     * @return
     */
    public static JSONObject getHistory(String token) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Content-Type", HttpUtilsConstant.FORM_DATA);
        map.put("Authorization", token);
        HttpResponse smfile =
            HttpUtils.doGet(SmMsConstant.HOST, "/history", map, null);
        return HttpUtils.getResponse(smfile);
    }

    /**
     * 获取所有上传记录
     * 
     * @param token
     * @return
     */
    public static JSONObject getAllHistory(String token) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Content-Type", HttpUtilsConstant.FORM_DATA);
        map.put("Authorization", token);
        HttpResponse smfile =
            HttpUtils.doGet(SmMsConstant.HOST, "/upload_history", map, null);
        return HttpUtils.getResponse(smfile);
    }
}
