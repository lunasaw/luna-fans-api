package com.luna.baidu.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.base.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Luna@win10
 * @date 2020/4/26 21:44
 */

@EnableScheduling
@Component
public class GetBaiduKey {
    private static final Logger log = LoggerFactory.getLogger(GetBaiduKey.class);

    @Autowired
    private BaiduConfigValue    configValue;

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * 
     * @return assess_token 示例：
     * "25.2634aa914e737196878361a42128d998.315360000.1910247307.282335-19618961"
     */
    @Scheduled(cron = "0 0 0 1,15 * ? ")
    public String getAuth() {
        log.info("执行定时任务获取百度Key");
        String ak = configValue.getAppKey();
        String sk = configValue.getSecretKey();
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
            // 1. grant_type为固定参数
            + "grant_type=client_credentials"
            // 2. 官网获取的 API Key
            + "&client_id=" + ak
            // 3. 官网获取的 Secret Key
            + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            JSONObject jsonObject = JSON.parseObject(result);
            configValue.setBaiduKey(jsonObject.get("access_token").toString());
            log.info("get token success！", jsonObject.toString());
            return jsonObject.get("access_token").toString();
        } catch (Exception e) {
            log.info("get token failed！");
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, e.getMessage());
        }
    }

}
