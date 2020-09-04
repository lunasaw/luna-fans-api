package com.luna.api.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @Package: com.luna.api.jsoup
 * @ClassName: JsoupUtil
 * @Author: luna
 * @CreateTime: 2020/9/4 14:03
 * @Description:
 */
public class JsoupUtil {

    /**
     * 对url进行连接尝试，如果连接成功则返回true，否则返回false
     *
     * @param url
     * @return
     */
    public static boolean attempConnect(String url) {
        try {
            Jsoup.connect(url)
                .timeout(3 * 1000)
                .get();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 下载指定url的文档对象
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static Document downloadDocument(String url) throws IOException {
        Document document = Jsoup.connect(url).timeout(5 * 1000).get();
        return document;
    }
}
