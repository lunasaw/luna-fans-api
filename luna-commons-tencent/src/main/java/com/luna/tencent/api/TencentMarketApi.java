package com.luna.tencent.api;

import com.alibaba.fastjson.JSONObject;
import com.luna.common.http.HttpUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Luna@win10
 * @date 2020/4/28 17:04
 */
public class TencentMarketApi {

    /**
     * 身份证审核
     *
     * @param name 姓名
     * @param id 身份证号
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     */
    public static JSONObject checkIdByLuna(String secretId, String secretKey, String name, String id) throws Exception {
        String source = "market";
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String datetime = sdf.format(cd.getTime());
        // 签名
        String auth = TencentCloudAPITC3.calcAuthorization(source, secretId, secretKey, datetime);
        // 请求方法
        String method = "GET";

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

        JSONObject response = HttpUtils.doURL(url, method, headers, queryParams);
        return response;
    }

}
