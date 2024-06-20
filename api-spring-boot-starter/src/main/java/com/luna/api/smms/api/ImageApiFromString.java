package com.luna.api.smms.api;

import java.util.List;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.luna.api.smms.dto.UploadResultDTO;
import com.luna.common.net.HttpUtils;
import com.luna.common.net.UserAgentConstant;

import org.apache.hc.core5.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.luna.api.smms.constant.SmMsConstant;

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
    public static UploadResultDTO upload(String token, String path) {
        log.info("smms upload start token={}, path={}", token, path);
        String s = HttpUtils.checkResponseAndGetResult(HttpUtils.doPost(SmMsConstant.HOST, "/upload",
            ImmutableMap.of("Authorization", token, "User-Agent", UserAgentConstant.CHROME_WIN_10), null,
            ImmutableMap.of("smfile", path)),
            false);
        JSONObject jsonObject = JSON.parseObject(s);
        String data = jsonObject.getString("data");
        String images = jsonObject.getString("images");
        if (data != null) {
            return JSON.parseObject(data, UploadResultDTO.class);
        }
        if (images != null) {
            UploadResultDTO uploadResultDTO = new UploadResultDTO();
            uploadResultDTO.setUrl(images);
            return uploadResultDTO;
        }
        return null;
    }

    /**
     * 删除文件
     * @param token
     * @param hash
     * @return
     */
    public static boolean deleteFile(String token, String hash) {
        HttpResponse httpResponse = HttpUtils.doGet(SmMsConstant.HOST, "/delete" + "/" + hash,
            ImmutableMap.of("Authorization", token, "User-Agent", UserAgentConstant.CHROME_WIN_10), null);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse);
        return JSON.parseObject(s).getBooleanValue("success");
    }

    /**
     * 获取最近上传历史
     * 
     * @param token
     * @return
     */
    public static List<UploadResultDTO> getHistory(String token) {
        HttpResponse httpResponse =
            HttpUtils.doGet(SmMsConstant.HOST, "/history",
                ImmutableMap.of("Authorization", token, "User-Agent", UserAgentConstant.CHROME_WIN_10), null);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        String data = JSONObject.parseObject(s).getString("data");
        return JSONArray.parseArray(data, UploadResultDTO.class);
    }

    /**
     * 获取所有上传记录
     * 
     * @param token
     * @return
     */
    public static List<UploadResultDTO> getAllHistory(String token) {
        HttpResponse response =
            HttpUtils.doGet(SmMsConstant.HOST, "/upload_history",
                ImmutableMap.of("Authorization", token, "User-Agent", UserAgentConstant.CHROME_WIN_10), null);
        String s = HttpUtils.checkResponseAndGetResult(response, true);
        String data = JSONObject.parseObject(s).getString("data");
        return JSONArray.parseArray(data, UploadResultDTO.class);
    }

    /**
     * 清理基于IP上传的临时记录
     * @param token
     * @return
     */
    public static boolean clearTempHistory(String token){
        HttpResponse httpResponse = HttpUtils.doGet(SmMsConstant.HOST, "/clear",
                ImmutableMap.of("Authorization", token, "User-Agent", UserAgentConstant.CHROME_WIN_10), null);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse);
        return JSON.parseObject(s).getBooleanValue("success");
    }
}
