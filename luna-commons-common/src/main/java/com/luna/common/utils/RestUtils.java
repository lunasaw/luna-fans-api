package com.luna.common.utils;

import com.google.common.collect.Maps;
import com.luna.common.http.HttpUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpResponse;

import java.util.Map;

/**
 * @author Tony
 */
public class RestUtils {
    private static final String ENCODE = "utf-8";

    public static String doGet(String host, String path, Map<String, String> headers,
        Map<String, String> queries) {
        HttpResponse httpResponse = HttpUtils.doGet(host, path, headers, queries);
        return HttpUtils.checkResponseAndGetResult(httpResponse, false);
    }

    public static String doPost(String host, String path, Map<String, String> headers,
        Map<String, String> queries, String body) {
        if (MapUtils.isEmpty(headers)) {
            headers = Maps.newHashMap();
        }
        headers.put("Content-Type", "application/json");
        HttpResponse httpResponse = HttpUtils.doPost(host, path, headers, queries, body);
        return HttpUtils.checkResponseAndGetResult(httpResponse, false);
    }

}
