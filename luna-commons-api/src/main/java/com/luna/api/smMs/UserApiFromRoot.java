package com.luna.api.smMs;

import com.alibaba.fastjson.JSONObject;
import com.luna.api.smMs.constant.SmMsConstant;
import com.luna.common.http.HttpUtils;
import com.luna.common.http.HttpUtilsConstant;
import com.luna.common.jsonfile.ConfInterface;
import com.luna.common.jsonfile.ConfParent;

import java.util.HashMap;

/**
 * @Package: com.luna.api.smMs
 * @ClassName: UserApiFromRoot
 * @Author: luna
 * @CreateTime: 2020/7/27 10:37
 * @Description:
 */
public class UserApiFromRoot {
    // static String JSONPath = System.getProperty("user.home") + "/smms.json";

    /**
     * get user profile information
     * 
     * @param header header hashMap, should put Authorization
     * @return json response object contains user profile information
     */
    public static JSONObject getProfile(HashMap<String, String> header) {
        header.put("Content-Type", HttpUtilsConstant.FORM_DATA);
        return HttpUtils.getResponse(HttpUtils.doPost(SmMsConstant.HOST, "/profile", header, null, ""));
    }

    /**
     * get user token information
     * 
     * @param para parameter hashMap, should put Authorization
     * @param header header hashMap, should put Authorization
     * @return json response object contains user token information
     */
    public static JSONObject getToken(HashMap<String, String> para, HashMap<String, String> header) {
        header.put("Content-Type", HttpUtilsConstant.FORM_DATA);
        return HttpUtils.getResponse(HttpUtils.doPost(SmMsConstant.HOST, "/token", header, para, ""));
    }

    /**
     * access smms user key value from user home and get user profile
     * 
     * @return profile response json object
     */
    public static JSONObject getProfile(String JSONPath) {
        ConfParent confParent = new ConfParent(new ConfInterface() {
            @Override
            public JSONObject dealHashMap(HashMap<String, String> para, HashMap<String, String> header) {
                return getProfile(header);
            }
        });
        return confParent.getResponse(JSONPath);
    }

    /**
     * access smms user key value from user home and get user token
     * 
     * @return token response json object
     */
    public static JSONObject getToken(String JSONPath) {
        ConfParent confParent = new ConfParent(new ConfInterface() {
            @Override
            public JSONObject dealHashMap(HashMap<String, String> para, HashMap<String, String> header) {
                return getToken(para, header);
            }
        });
        return confParent.getResponse(JSONPath);
    }
}
