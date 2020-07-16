package com.luna.common.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.base.BaseException;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tony
 */
public class HttpUtils {
    /** urlEncode编码 */
    private static final String        ENCODE            = "utf-8";

    private static final String        FORM_CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";

    private static CloseableHttpClient httpClient;

    static {
        SSLConnectionSocketFactory socketFactory = null;
        try {
            // 信任所有
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null,
                (TrustStrategy)(chain, authType) -> true).build();
            socketFactory = new SSLConnectionSocketFactory(sslContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
            .register("http", PlainConnectionSocketFactory.getSocketFactory())
            .register("https", socketFactory != null ? socketFactory : PlainConnectionSocketFactory.getSocketFactory())
            .build();

        // for proxy debug
        // HttpHost proxy = new HttpHost("localhost", 8888);
        // RequestConfig defaultRequestConfig =
        // RequestConfig.custom().setProxy(proxy).setSocketTimeout(5000).setConnectTimeout(5000)
        // .setConnectionRequestTimeout(5000).build();

        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000)
            .setConnectionRequestTimeout(5000).build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(200);
        httpClient =
            HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(defaultRequestConfig).build();
    }

    /**
     * doURL
     * 
     * @param url url路径
     * @param method 方法
     * @param headers 请求头
     * @param queryParams 请求参数
     * @return
     * @throws IOException
     */
    public static JSONObject doURL(String url, String method, Map<String, String> headers,
        Map<String, String> queryParams) throws IOException {
        // url参数拼接
        if (!queryParams.isEmpty()) {
            url += "?" + HttpUtils.urlencode(queryParams);
        }
        URL realUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestMethod(method);

        // request headers
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            conn.setRequestProperty(entry.getKey(), entry.getValue());
        }

        // request body
        Map<String, Boolean> methods = new HashMap<>();
        methods.put("POST", true);
        methods.put("PUT", true);
        methods.put("PATCH", true);
        Boolean hasBody = methods.get(method);
        if (hasBody != null) {
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            conn.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(urlencode(queryParams));
            out.flush();
            out.close();
        }

        // 定义 BufferedReader输入流来读取URL的响应
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        String result = "";
        while ((line = in.readLine()) != null) {
            result += line;
        }
        return JSONObject.parseObject(result);
    }

    public static String urlencode(Map<?, ?> map) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(String.format("%s=%s",
                URLEncoder.encode(entry.getKey().toString(), "UTF-8"),
                URLEncoder.encode(entry.getValue().toString(), "UTF-8")));
        }
        return sb.toString();
    }

    /**
     * get
     *
     * @param host 主机
     * @param path 路径
     * @param headers 请求头
     * @param queries 请求参数
     * @return
     * @throws Exception
     */
    public static HttpResponse doGet(String host, String path, Map<String, String> headers,
        Map<String, String> queries) {
        HttpGet request = new HttpGet(buildUrl(host, path, queries));
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }
        try {
            return httpClient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Post form
     *
     * @param host 主机
     * @param path 路径
     * @param headers 请求头
     * @param queries 请求参数
     * @param bodies 请求体
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path, Map<String, String> headers,
        Map<String, String> queries, Map<String, String> bodies) {
        HttpPost request = new HttpPost(buildUrl(host, path, queries));
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }
        if (MapUtils.isNotEmpty(bodies)) {
            List<NameValuePair> nameValuePairList = Lists.newArrayList();
            for (String key : bodies.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, bodies.get(key)));
            }
            UrlEncodedFormEntity formEntity = null;
            try {
                formEntity = new UrlEncodedFormEntity(nameValuePairList, ENCODE);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            formEntity.setContentType(FORM_CONTENT_TYPE);
            request.setEntity(formEntity);
        }
        try {
            return httpClient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Post String
     *
     * @param host
     * @param path
     * @param headers
     * @param queries
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path, Map<String, String> headers,
        Map<String, String> queries, String body) {
        HttpPost request = new HttpPost(buildUrl(host, path, queries));
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }
        if (StringUtils.isNotBlank(body)) {
            request.setEntity(new StringEntity(body, ENCODE));

        }
        try {
            return httpClient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Post stream
     *
     * @param host
     * @param path
     * @param headers
     * @param queries
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path, Map<String, String> headers,
        Map<String, String> queries, byte[] body) {
        HttpPost request = new HttpPost(buildUrl(host, path, queries));
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }
        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }
        try {
            return httpClient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String buildUrl(String host, String path, Map<String, String> queries) {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);

        if (StringUtils.isNotBlank(path)) {
            sbUrl.append(path);
        }

        if (MapUtils.isNotEmpty(queries)) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : queries.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (StringUtils.isBlank(query.getKey()) && StringUtils.isNotBlank(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (StringUtils.isNotBlank(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (StringUtils.isNotBlank(query.getValue())) {
                        sbQuery.append("=");
                        try {
                            sbQuery.append(URLEncoder.encode(query.getValue(), ENCODE));
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }

        return sbUrl.toString();
    }

    /**
     * 检测响应体
     * 
     * @param httpResponse
     * @return
     */
    public static String checkResponseAndGetResult(HttpResponse httpResponse) {
        if (httpResponse == null) {
            throw new RuntimeException();
        }
        if (httpResponse.getStatusLine() == null) {
            throw new RuntimeException();
        }
        if (HttpStatus.SC_OK != httpResponse.getStatusLine().getStatusCode()) {
            throw new RuntimeException();
        }

        HttpEntity entity = httpResponse.getEntity();
        try {
            return EntityUtils.toString(entity, ENCODE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析响应体
     * 
     * @param httpResponse
     * @return
     * @throws IOException
     */
    public static JSONObject getResponse(HttpResponse httpResponse) {
        try {
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
            String jsonText = readAll(reader);
            return JSONObject.parseObject(jsonText);
        } catch (IOException e) {
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, "读取失败,请检查网络连接后重试" + e);
        }
    }

    /**
     * 解析返回JSON数组
     * 
     * @param httpResponse
     * @return
     */
    public static List<JSONObject> getResponseToArray(HttpResponse httpResponse) {
        try {
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
            String jsonText = readAll(reader);
            List<JSONObject> datas = JSON.parseArray(jsonText, JSONObject.class);
            return datas;
        } catch (IOException e) {
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, "读取失败,请检查网络连接后重试" + e);
        }
    }

    /**
     * 读取
     *
     * @param rd
     * @return
     * @throws IOException
     */
    private static String readAll(Reader rd) {
        try {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char)cp);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, "读取失败,请检查网络连接后重试" + e);
        }
    }

    /**
     * 创建链接
     *
     * @param url
     * @return
     * @throws IOException
     * @throws JSONException
     */
    private static JSONObject readJsonFromUrl(String url) throws Exception {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = JSONObject.parseObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    /**
     * 检查是不是网络路径
     * 
     * @param url
     * @return
     */
    public static boolean isNetUrl(String url) {
        boolean reault = false;
        if (url != null) {
            if (url.toLowerCase().startsWith("http") || url.toLowerCase().startsWith("rtsp")
                || url.toLowerCase().startsWith("mms")) {
                reault = true;
            }
        }
        return reault;
    }
}
