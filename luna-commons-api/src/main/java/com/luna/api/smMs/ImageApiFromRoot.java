package com.luna.api.smMs;

import com.alibaba.fastjson.JSONObject;
import com.luna.api.smMs.constant.SmMsConstant;
import com.luna.common.http.HttpUtils;
import com.luna.common.http.HttpUtilsConstant;
import com.luna.common.jsonfile.ConfInterface;
import com.luna.common.jsonfile.InformationConverter;
import com.luna.common.okHttp.HttpPost;

import java.util.HashMap;

/**
 * @author WangTingZheng
 * @date 2020-04-05 15:39
 * @features based action about image
 */
public class ImageApiFromRoot {
    /**
     * access smms user key value from user home and upload image
     * 
     * @param path the file you want to upload
     * @return the upload response json object
     */
    public static JSONObject uploadFromRoot(String JSONPath, String path) {
        ConfParent confParent = new ConfParent(new ConfInterface() {
            @Override
            public JSONObject dealHashMap(HashMap<String, String> para, HashMap<String, String> header) {
                return upload(header, InformationConverter.pathToHashMap(path));
            }
        });
        return confParent.getResponse(JSONPath);
    }

    /**
     * access smms user key value from user home and get temporary History
     * 
     * @return the temporary History response json object
     */
    public static JSONObject temporaryHistoryFromRoot(String JSONPath) {
        ConfParent confParent = new ConfParent(new ConfInterface() {
            @Override
            public JSONObject dealHashMap(HashMap<String, String> para, HashMap<String, String> header) {
                return temporaryHistory(header);
            }
        });
        return confParent.getResponse(JSONPath);
    }

    /**
     * access smms user key value from user home and get upload History
     * 
     * @return the tupload History response json object
     */
    public static JSONObject uploadHistoryFromRoot(String JSONPath) {
        ConfParent confParent = new ConfParent(new ConfInterface() {
            @Override
            public JSONObject dealHashMap(HashMap<String, String> para, HashMap<String, String> header) {
                return uploadHistory(header);
            }
        });
        return confParent.getResponse(JSONPath);
    }

    /**
     * upload image file to sm.ms
     * 
     * @param header put authorization to header hashMap
     * @param bodyForma put key, and value(image file path)
     * @return response json object
     */
    public static JSONObject upload(HashMap<String, String> header, HashMap<String, String> bodyForma) {
        header.put("Content-Type", HttpUtilsConstant.FORM_DATA);
        HashMap<String, String> para = new HashMap<>();
        return HttpPost.post(SmMsConstant.HOST + "/upload", para, header, bodyForma);
    }

    /**
     * base ip and get temporaryHistory
     * 
     * @param header header hashMap, should put Authorization
     * @return json response object contains temporaryHistory information
     */
    public static JSONObject temporaryHistory(HashMap<String, String> header) {
        header.put("Content-Type", HttpUtilsConstant.FORM_DATA);
        return HttpUtils.getResponse(HttpUtils.doGet(SmMsConstant.HOST, "/history", header, null));
    }

    /**
     * get all upload History
     * 
     * @param header header hashMap, should put Authorization
     * @return json response object contains upload history information
     */
    public static JSONObject uploadHistory(HashMap<String, String> header) {
        header.put("Content-Type", HttpUtilsConstant.FORM_DATA);
        return HttpUtils.getResponse(HttpUtils.doGet(SmMsConstant.HOST, "/upload_history", header, null));
    }

}
