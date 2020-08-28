package com.luna.api.jsoup;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.luna.api.jd.dto.SearchJDResultDTO;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.base.BaseException;
import com.luna.common.utils.text.CharsetKit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.luna.api.jsoup
 * @ClassName: JsoupUtil
 * @Author: luna
 * @CreateTime: 2020/8/26 19:44
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

    /**
     * JD 搜索封装
     * 
     * @param keyword
     * @return
     */
    public static List<SearchJDResultDTO> parseJd(String keyword) {
        try {
            String url = "https://search.jd.com/Search?keyword=" + URLEncoder.encode(keyword, CharsetKit.UTF_8);
            Document document = Jsoup.connect(url).timeout(5 * 1000).get();
            Element elementById = document.getElementById("J_goodsList");
            Elements elements = elementById.getElementsByTag("li");

            ArrayList<SearchJDResultDTO> list = Lists.newArrayList();
            for (Element element : elements) {
                String img = element.getElementsByTag("img").eq(0).attr("src");
                if (img == null) {
                    img = element.getElementsByTag("img").eq(0).attr("source-data-lazy-img");
                }
                String price = element.getElementsByClass("p-price").eq(0).text();
                String title = element.getElementsByClass("p-name").eq(0).text();
                SearchJDResultDTO searchJDResultDTO = new SearchJDResultDTO(img, price, title);
                list.add(searchJDResultDTO);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
    }

    public static void main(String[] args) {
        List<SearchJDResultDTO> maps = parseJd("JAVA");
        System.out.println(JSON.toJSONString(maps));
    }
}
