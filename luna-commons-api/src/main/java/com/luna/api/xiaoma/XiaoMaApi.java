package com.luna.api.xiaoma;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.luna.api.xiaoma.dto.CreateResultDTO;
import com.luna.api.xiaoma.dto.GroupListDTO;
import com.luna.common.http.HttpUtils;
import com.luna.common.http.HttpUtilsConstant;
import org.apache.http.HttpResponse;

import java.util.HashMap;

/**
 * @Package: com.luna.api.xiaoma
 * @ClassName: XiaoMaApi
 * @Author: luna
 * @CreateTime: 2020/9/28 10:30
 * @Description: https://xiaomark.com/dashboard/a-domain/setting
 */
public class XiaoMaApi {

    /**
     * 获取链接分组接口
     * 
     * @param str
     * @return
     */
    public static GroupListDTO shortLink(String str) {
        String key = "{\"apikey\": \"" + str + "\"}";
        HttpResponse httpResponse = HttpUtils.doPost(XiaoMaConstant.HOST, "/v1/group/get",
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), null, key);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, false);
        return JSON.parseObject(JSON.parseObject(s).getString("data"), GroupListDTO.class);
    }

    /**
     * 生成短链接接口
     *
     * @param str 密钥
     * @param link 需生成的短链接
     * @param groupId 组
     * @param domain 不填则默认使用sourl.cn；使用自定义域名可以生成任意域名下的短链接
     * @return
     */
    public static CreateResultDTO createLink(String str, String link, String groupId, String domain) {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("apikey", str);
        map.put("origin_url", link);
        map.put("group_sid", groupId);
        map.put("domain", domain);
        HttpResponse httpResponse = HttpUtils.doPost(XiaoMaConstant.HOST, "/v1/link/create",
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), null, JSON.toJSONString(map));
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, false);
        System.out.println(s);
        return JSON.parseObject(JSON.parseObject(s).getString("data"), CreateResultDTO.class);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // shortLink(XiaoMaConstant.KEY);
        CreateResultDTO link = createLink(XiaoMaConstant.KEY, "http://luna_nov.gitee.io/blog/", "", "");
        System.out.println(JSON.toJSONString(link));
    }
}
