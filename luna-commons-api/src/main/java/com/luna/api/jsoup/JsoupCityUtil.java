package com.luna.api.jsoup;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.luna.api.city.CityCard;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.base.BaseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.luna.api.jsoup
 * @ClassName: CityUtil
 * @Author: luna
 * @CreateTime: 2020/9/4 14:02
 * @Description:
 */
public class JsoupCityUtil {

    public static List<CityCard> getCityCode() {
        try {
            String url = "http://www.lhsxlxx.com/xjgl/admin/xzqhdm.asp";
            Document document = Jsoup.connect(url).timeout(5 * 1000).get();
            Element element = document.getElementsByClass("content").get(0);
            Elements tbody = element.getElementsByTag("tbody");
            Elements tds = tbody.get(0).getElementsByTag("td");
            int i = 0;
            int k = 0;
            CityCard cityCard = new CityCard();
            ArrayList<CityCard> list = Lists.newArrayList();
            for (int j = 2; j < tds.size(); j++) {
                String text = tds.get(j).text();
                String[] split = text.split("\\(");
                if (k == 0) {
                    cityCard.setSort(text);
                    k++;
                } else if (k == 1) {
                    cityCard.setCity(split[0]);
                    String[] split1 = split[1].split("\\)");
                    cityCard.setCode(split1[0]);
                    k = 0;
                    list.add(cityCard);
                    cityCard = new CityCard();
                }
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
    }

    public static void main(String[] args) {
        List<CityCard> cityCode = getCityCode();
        System.out.println(JSON.toJSONString(cityCode));
    }
}
