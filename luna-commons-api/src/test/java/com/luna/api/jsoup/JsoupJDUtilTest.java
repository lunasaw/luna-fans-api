package com.luna.api.jsoup;

import com.luna.api.ApiApplicationTest;
import com.luna.api.jd.service.SearchJDService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;

/**
 * @Package: com.luna.elasticsearch.jd
 * @ClassName: JsoupUtil
 * @Author: luna
 * @CreateTime: 2020/8/26 19:50
 * @Description:
 */
public class JsoupJDUtilTest extends ApiApplicationTest {

    @Autowired
    private SearchJDService searchJDService;

    public static void main(String[] args) throws IOException {
        // 获取请求 https://search.jd.com/Search?keyword=java
        String url = "https://search.jd.com/Search?keyword=java";

        // 解析网页 jsoup 返回document就是浏览器的document对象
        Document document = Jsoup.parse(new URL(url), 30000);

        // 所有js 方法皆可使用
        Element elementById = document.getElementById("J_goodsList");

        // 获取所有的li元素
        Elements elements = elementById.getElementsByTag("li");
        for (Element element : elements) {
            String img = element.getElementsByTag("img").eq(0).attr("src");
            String price = element.getElementsByClass("p-price").eq(0).text();
            String title = element.getElementsByClass("p-name").eq(0).text();
            System.out.println("===================================================");
            System.out.println(img);
            System.out.println(price);
            System.out.println(title);
        }
    }

    @Test
    public void parseJD() {
        boolean java = searchJDService.parseJD("docker");
        System.out.println(java);
    }

}
