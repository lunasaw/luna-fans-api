package com.luna.common.jsonfile;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @date  2020-3-35
 * @author 14037
 * @features convert file JSONObject String
 */
public class InformationConverter {
    /**
     * 读取json文件并转换为JSONObject
     * 
     * @param path JSON文件路径
     * @return a fastjson JSONObject
     */
    public static JSONObject fileToJson(String path)
    {
        String text = null;
        String str;
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            while ((str = in.readLine()) != null) {
                text +=str;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(text != null)
        {
            text = text.replace("null", "");
        }
        return JSONObject.parseObject(text);
    }

    /**
     * write json String to a json file
     * @param text the json text needs to write
     * @param path the json file path needs to save
     */
    public static void stringToFile(String text, String path)
    {
        File file = new File(path);
        try(FileWriter writer = new FileWriter(file);
            BufferedWriter out =  new BufferedWriter(writer)
        ){
            out.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * get item from json text file
     * @param path the json file path
     * @param item the item you want to get, can be para, header
     * @return a Map, key is String, value is Object
     */
    public static Map<String, Object> getItem(String path, String item)
    {
        JSONObject object = fileToJson(path);
        JSONObject nodeOject = object.getJSONObject(item);
        return nodeOject.getInnerMap();
    }

    /**
     * Convert jsonobject to hashMap
     * @param object the jsonObject need to convert
     * @return the hashMap(String, String)
     */
    public static HashMap<String, String> jsonToHasMap(JSONObject object)
    {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        Set<String> set = object.keySet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext())
        {
            String next = iterator.next();
            hashMap.put(next, object.getString(next));
        }
        return hashMap;
    }

    public static JSONObject StringToJson(String msg)
    {
        return JSONObject.parseObject(msg);
    }

    public static String JsonToString(JSONObject jsonObject)
    {
        return jsonObject.toJSONString();
    }

    public static String getValue(String path, String item, String value)
    {
        JSONObject object = fileToJson(path);
        JSONObject nodeOject = object.getJSONObject(item);
        return nodeOject.getString(value);
    }

    /**
     * get a certain item json value from file path and convert it to hashMap
     * @param path the json file path
     * @param item the item of json
     * @return the hashMap(String, String)
     */
    public static HashMap<String, String> getItemHasMap(String path, String item)
    {
        JSONObject object = fileToJson(path);
        object = object.getJSONObject(item);
        return jsonToHasMap(object);
    }

    /**
     * convert upload file path to body hashMap
     * @param path the file path
     * @return hasHMap which can be add into post/get function
     */
    public static HashMap<String, String> pathToHashMap(String path)
    {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("smfile", path);
        return hashMap;
    }

    /**
     * converter account information to HashMap
     * @param username the smms username
     * @param password the smms password
     * @param authorization the smms key
     * @return a hashMap, Key is String(username, password, authorization), Value is HashMap(para, header)
     */
    public static  HashMap<String, HashMap<String,String>> StringToHashMap(String username, String password, String authorization)
    {
        HashMap<String, HashMap<String,String>> confList = new HashMap<String, HashMap<String, String>>();
        HashMap<String,String> para = new HashMap<String, String>();
        para.put("username" , username);
        para.put("password", password);
        HashMap<String,String> header = new HashMap<String, String>();
        header.put("Authorization", authorization);
        confList.put("para", para);
        confList.put("header", header);
        return confList;
    }
}
