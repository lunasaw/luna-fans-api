package com.luna.common.jsonfile;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * @author WangTingZheng
 * @date 2020-04-05 15:40
 * @features configure interface class
 */
public abstract class ConfInterface {

    /**
     * A intface function to deal different post/ get result
     * @param para the parameter hashMap
     * @param header the header parameter hashMap
     * @return the json response
     */
    public abstract JSONObject dealHashMap(HashMap<String, String> para, HashMap<String, String> header);

}
