package com.luna.tencent.api;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson2.JSON;
import com.luna.common.net.HttpUtils;
import org.apache.hc.core5.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson2.JSONObject;

/**
 * @author Luna@win10
 * @date 2020/4/28 17:04
 */
public class TencentMarketApi {

    private static final Logger log = LoggerFactory.getLogger(TencentMarketApi.class);

    /**
     * 腾讯云市场身份证审核
     *
     * @param name 姓名
     * @param id 身份证号
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static JSONObject checkIdByLuna(String secretId, String secretKey, String name, String id) {
        log.info("checkIdByLuna start secretId={}, secretKey={}, name={}, id={}", secretId, secretKey, name, id);
        String source = "market";
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String datetime = sdf.format(cd.getTime());
        // 签名
        String auth = TencentCloudAPITC3.calcAuthorization(source, secretId, secretKey, datetime);

        // 请求头
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("X-Source", source);
        headers.put("X-Date", datetime);
        headers.put("Authorization", auth);

        // 查询参数
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("idcard", id);
        queryParams.put("name", name);

        // url参数拼接
        String url = TencentConstant.TENCENT_MARK_AUTHENTICATION;

        HttpResponse httpResponse = HttpUtils.doGet(url, "", headers, queryParams);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, false);
        JSONObject response = JSON.parseObject(s);
        log.info("checkIdByLuna end secretId={}, secretKey={}, name={}, id={},response={}", secretId, secretKey, name,
            id, response);
        return response;
    }

}
