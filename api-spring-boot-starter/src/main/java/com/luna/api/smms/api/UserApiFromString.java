package com.luna.api.smms.api;

import com.luna.api.smms.dto.UserProfileDTO;
import com.luna.common.net.HttpUtils;
import com.luna.common.net.UserAgentConstant;
import org.apache.http.HttpResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.luna.api.smms.constant.SmMsConstant;

/**
 * @Package: com.luna.api.smMs
 * @ClassName: SmMs
 * @Author: luna
 * @CreateTime: 2020/7/24 23:09
 * @Description:
 */
public class UserApiFromString {

    /**
     * 获取用户TOKEN
     * 
     * @param username
     * @param password
     * @return
     */
    public static String getAuthToken(String username, String password) {
        HttpResponse httpResponse =
            HttpUtils.doPost(SmMsConstant.HOST, "/token",
                ImmutableMap.of("User-Agent", UserAgentConstant.CHROME_WIN_10),
                ImmutableMap.of("username", username, "password", password), "");
        JSONObject response = JSON.parseObject(HttpUtils.checkResponseAndGetResult(httpResponse, true));
        return JSON.parseObject(response.getString("data")).getString("token");
    }

    /**
     * 获取用户信息
     * 
     * @param token
     * @return
     */
    public static UserProfileDTO getUserProfile(String token) {
        HttpResponse httpResponse =
            HttpUtils.doPost(SmMsConstant.HOST, "/profile",
                ImmutableMap.of("Authorization", token, "User-Agent", UserAgentConstant.CHROME_WIN_10),
                null, "");
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        return JSON.parseObject(JSON.parseObject(s).getString("data"), UserProfileDTO.class);
    }

}
