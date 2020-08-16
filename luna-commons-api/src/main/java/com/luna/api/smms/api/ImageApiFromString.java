package com.luna.api.smms.api;

import com.luna.api.smms.constant.SmMsConstant;
import com.luna.common.http.HttpUtils;
import com.luna.common.http.HttpUtilsConstant;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * @Package: com.luna.api.smMs
 * @ClassName: ImageApiFromString
 * @Author: luna
 * @CreateTime: 2020/7/27 12:23
 * @Description:
 */
public class ImageApiFromString {

    private static final Logger log = LoggerFactory.getLogger(ImageApiFromString.class);

    /**
     * 上传文件
     * 
     * @param token
     * @param path
     * @return
     */
    public static String upload(String token, String path) {
        log.info("smms upload start token={},path={}", token, path);
        HashMap<String, String> header = new HashMap<>();
        header.put("Authorization", token);
        HashMap<String, String> bodies = new HashMap<>();
        bodies.put("smfile", path);
        return HttpUtils.checkResponseAndGetResult(HttpUtils.doPost(SmMsConstant.HOST, "/upload", header, null, bodies),
            true);
    }

    /**
     * 获取最近上传历史
     * 
     * @param token
     * @return
     */
    public static String getHistory(String token) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Content-Type", HttpUtilsConstant.FORM_DATA);
        map.put("Authorization", token);
        HttpResponse smfile =
            HttpUtils.doGet(SmMsConstant.HOST, "/history", map, null);
        return HttpUtils.checkResponseAndGetResult(smfile, true);
    }

    /**
     * 获取所有上传记录
     * 
     * @param token
     * @return
     */
    public static String getAllHistory(String token) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Content-Type", HttpUtilsConstant.FORM_DATA);
        map.put("Authorization", token);
        HttpResponse smfile =
            HttpUtils.doGet(SmMsConstant.HOST, "/upload_history", map, null);
        return HttpUtils.checkResponseAndGetResult(smfile, true);
    }
}
