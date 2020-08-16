package com.luna.api.smms;

import com.alibaba.fastjson.JSONObject;
import com.luna.common.jsonfile.ConfInterface;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @author WangTingZheng
 * @date 2020-04-05 15:41
 * @features get ready from use conf information from file
 */
public class ConfParent {
    ConfInterface confInterface;

    public ConfParent(ConfInterface confInterface) {
        this.confInterface = confInterface;
    }


    /**
     * get smms information from json file and convert to HashMap
     * @param path the json file path
     * @return a HashMap, key is String, is item name, can be para or header, key is HashMap
     */
    public HashMap<String, HashMap<String, String>> getResponseHashMap(String path) {
        HashMap<String, HashMap<String,String>> response = new HashMap<>();
        HashMap<String, HashMap<String, String>> config = ConfFile.getConf(path);
        HashMap<String, String> para = null;
        HashMap<String, String> header = null;
        Set<String> set = config.keySet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String next;
            HashMap<String, String> hashMap;
            next = iterator.next();
            hashMap = config.get(next);
            if ("para".equals(next)) {
                para = hashMap;
            } else if ("header".equals(next)) {
                header = hashMap;
            }
        }
        response.put("para",para);
        response.put("header", header);
        return response;
    }

    /**
     * get response from ConfInterface dealHashMap()
     * @param path the json file path
     * @return the response json object
     */
    public JSONObject getResponse(String path) {
        HashMap<String, HashMap<String,String>> response = getResponseHashMap(path);
        HashMap<String,String> para = response.get("para");
        HashMap<String,String> header = response.get("header");
        return confInterface.dealHashMap(para, header);
    }
}
