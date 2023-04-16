package com.luna.ali.oss;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.internal.OSSUtils;
import com.aliyun.oss.model.Callback;
import com.google.common.collect.Lists;
import com.luna.ali.oss.constant.OssConstants;
import com.luna.common.encrypt.Base64Util;
import com.luna.common.encrypt.HashUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author luna
 */
@Slf4j
public class PostObjectSample {
    /**
     * 表单上传
     * 
     * @param localFilePath 上传文件
     * @param bucketName
     * @param objectName 设置文件名称
     * @throws Exception
     */
    public static void postObjectByForm(String localFilePath, String bucketName, String objectName, String serverUrl, String accessKey,
        String secretKey,
        String endpoint) throws Exception {
        // 在URL中添加存储空间名称，添加后URL如下：http://yourBucketName.oss-cn-hangzhou.aliyuncs.com。
        if (StringUtils.isEmpty(endpoint)) {
            endpoint = OssConstants.HOST;
        }
        String host = endpoint.replace("yourBucketName", bucketName);
        // 设置表单Map。
        Map<String, String> formFields = new LinkedHashMap<String, String>();
        // 设置文件名称。
        formFields.put("objectName", objectName);
        // 设置Content-Disposition。
        formFields.put("Content-Disposition", "attachment;filename=" + localFilePath);
        // 设置回调参数。
        if (StringUtils.isNotEmpty(serverUrl)) {
            Callback callback = AliOssUtil.getCallback(serverUrl);
            // 在表单Map中设置回调参数。
            setCallBack(formFields, callback);
        }
        // 设置OSSAccessKeyId。
        formFields.put("OSSAccessKeyId", accessKey);
        String policy = JSON.toJSONString(Policy.getInstance());
        String encodePolicy = Base64Util.encodeBase64(policy.getBytes());
        // 设置policy。
        formFields.put("policy", encodePolicy);
        // 生成签名。
        String signaturecom =
            com.aliyun.oss.common.auth.ServiceSignature.create().computeSignature(secretKey,
                encodePolicy);
        // 设置签名。
        formFields.put("Signature", signaturecom);
        String result = formUpload(host, formFields, localFilePath);
        log.info("postObjectByForm::localFilePath = {}, bucketName = {}, objectName = {}, serverUrl = {}, accessKey = {}, result = {}, endpoint = {}",
            localFilePath, bucketName, objectName, serverUrl, accessKey, result, endpoint);
    }

    /**
     * @throws Exception
     */
    private static String formUpload(String host, Map<String, String> formFields, String localFile)
        throws Exception {
        String res = "";
        HttpURLConnection conn = null;
        String boundary = "9431149156168";
        try {
            URL url = new URL(host);
            conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            // 设置MD5值。MD5值由整个body计算得出。
            conn.setRequestProperty("Content-MD5", HashUtils.md5WithFile(localFile));
            conn.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + boundary);
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // 遍历读取表单Map中的数据，将数据写入到输出流中。
            if (formFields != null) {
                StringBuffer strBuf = new StringBuffer();
                Iterator<Map.Entry<String, String>> iter = formFields.entrySet().iterator();
                int i = 0;
                while (iter.hasNext()) {
                    Map.Entry<String, String> entry = iter.next();
                    String inputName = entry.getKey();
                    String inputValue = entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    if (i == 0) {
                        strBuf.append("--").append(boundary).append("\r\n");
                        strBuf.append("Content-Disposition: form-data; name=\""
                            + inputName + "\"\r\n\r\n");
                        strBuf.append(inputValue);
                    } else {
                        strBuf.append("\r\n").append("--").append(boundary).append("\r\n");
                        strBuf.append("Content-Disposition: form-data; name=\""
                            + inputName + "\"\r\n\r\n");
                        strBuf.append(inputValue);
                    }
                    i++;
                }
                out.write(strBuf.toString().getBytes());
            }
            // 读取文件信息，将要上传的文件写入到输出流中。
            File file = new File(localFile);
            String filename = file.getName();
            String contentType = new MimetypesFileTypeMap().getContentType(file);
            if (contentType == null || contentType.equals("")) {
                contentType = "application/octet-stream";
            }
            StringBuffer strBuf = new StringBuffer();
            strBuf.append("\r\n").append("--").append(boundary)
                .append("\r\n");
            strBuf.append("Content-Disposition: form-data; name=\"file\"; "
                + "filename=\"" + filename + "\"\r\n");
            strBuf.append("Content-Type: " + contentType + "\r\n\r\n");
            out.write(strBuf.toString().getBytes());
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();
            byte[] endData = ("\r\n--" + boundary + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();
            // 读取返回数据。
            strBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            res = strBuf.toString();
            reader.close();
            reader = null;
        } catch (Exception e) {
            System.err.println("Send post request exception: " + e);
            throw e;
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return res;
    }

    private static void setCallBack(Map<String, String> formFields, Callback callback) {
        if (callback != null) {
            String jsonCb = OSSUtils.jsonizeCallback(callback);
            String base64Cb = BinaryUtil.toBase64String(jsonCb.getBytes());
            formFields.put("callback", base64Cb);
            if (callback.hasCallbackVar()) {
                Map<String, String> varMap = callback.getCallbackVar();
                for (Map.Entry<String, String> entry : varMap.entrySet()) {
                    formFields.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Policy {

        private String       expiration;

        private List<Object> conditions;

        String               policy =
            "{\"expiration\": \"2120-01-01T12:00:00.000Z\",\"conditions\": [[\"content-length-range\", 0, 104857600]]}";

        public static Policy getInstance() {
            return Policy.builder().expiration("2120-01-01T12:00:00.000Z")
                .conditions(Lists.newArrayList(Lists.newArrayList("content-length-range", 0, 104857600))).build();
        }

    }
}
