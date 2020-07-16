package com.luna.file.httpd;

import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.FileException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Package: com.luna.file.httpd
 * @ClassName: HttpdFileUtil
 * @Author: luna
 * @CreateTime: 2020/7/16 15:17
 * @Description:
 */
public class HttpdFileUtil {
    /**
     * 判断http文件是否存在
     *
     * @param httpPath 网路路径
     * @return
     */
    public static void existHttpPath(String httpPath) {
        try {
            URL httpurl = new URL(new URI(httpPath).toASCIIString());
            URLConnection urlConnection = httpurl.openConnection();
            String headerField = urlConnection.getHeaderField(0);
            if (headerField.startsWith("HTTP/1.1 404")) {
                throw new FileException(ResultCode.PARAMETER_INVALID, "未读取文件,请检查路径");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileException(ResultCode.PARAMETER_INVALID, "未读取文件,请检查路径");
        }
    }

    /**
     * 下载文件
     * <p>
     * 若文件已存在，覆盖
     * </p>
     * <p>
     * 有异常时抛出异常
     * </p>
     *
     * @param url 网络路径
     * @param file 本地路径
     */
    public static void downloadFile(String url, String file) {
        try {
            org.apache.commons.io.FileUtils.copyURLToFile(new URL(url), new File(file), 5000, 5000);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 下载文件，失败在优先次数内重试
     *
     * @param url
     * @param file
     * @param maxRetry
     */
    public static void downloadFileWithRetry(String url, String file, int maxRetry) {
        for (int i = 0; i < maxRetry - 1; i++) {
            try {
                downloadFile(url, file);
                return;
            } catch (Exception e) {
                // do nothing
            }
        }
        downloadFile(url, file);
    }

    /**
     * 上传到文件服务器
     *
     * @param file 文件字节
     * @param filePath 网络路径
     * @return
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String filePath) {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        Client client = new Client();
        WebResource resource = client.resource(filePath);
        resource.put(String.class, file);
    }

    /**
     * 删除httpd服务器文件
     *
     * @param filePath 网络路径
     * @throws Exception
     */
    public static void delete(String filePath) {
        Client client = new Client();
        WebResource resource = client.resource(filePath);
        resource.delete();
    }
}
