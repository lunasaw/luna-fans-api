package com.luna.baidu.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.luna.baidu.dto.text.TextSimilarResultDTO;
import com.luna.baidu.dto.text.TextSimnetResultDTO;
import com.luna.common.http.HttpUtils;
import com.luna.common.http.HttpUtilsConstant;
import com.luna.common.utils.StringUtils;
import com.luna.common.utils.text.CharsetKit;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Luna@win10
 * @date 2020/5/24 21:10
 */
public class BaiduTextApi {

    private static final Logger log = LoggerFactory.getLogger(BaiduTextApi.class);

    /**
     * 百度文本纠错
     * 
     * @param text
     * @throws IOException
     */
    public static String correction(String key, String text) {
        log.info("correction start key={}, text={}", key, text);
        String body = "{\"text\": \"" + text + "\"" + "}";
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.LANGUAGE_PROCESSING,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON),
            ImmutableMap.of("access_token", key, "charset", CharsetKit.UTF_8),
            body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        JSONObject jsonObject = JSON.parseObject(JSON.parseObject(s).get("item").toString());
        String query = jsonObject.get("correct_query").toString();
        log.info("correction success query={}, text={}", query, text);
        return query;
    }

    /**
     * 文本相似度比较 短文本相似度接口用来判断两个文本的相似度得分。
     *
     * BOW（词包）模型=>基于bag of words的BOW模型，特点是泛化性强，效率高，比较轻量级，适合任务：输入序列的 term “确切匹配”、不关心序列的词序关系，对计算效率有很高要求；
     *
     * GRNN（循环神经网络）模型=>基于recurrent，擅长捕捉短文本“跨片段”的序列片段关系，适合任务：对语义泛化要求很高，对输入语序比较敏感的任务；
     *
     * CNN（卷积神经网络）模型=>模型语义泛化能力介于 BOW/RNN 之间，对序列输入敏感，相较于 GRNN 模型的一个显著优点是计算效率会更高些。
     * 
     * @param key
     * @param model
     * @return
     */
    public static TextSimnetResultDTO similarityText(String key, String text1, String text2, String model) {
        log.info("similarityText start key={},text1={},text2={},model={}", key, text1, text2, model);
        if (StringUtils.isEmpty(model)) {
            model = "BOW";
        }
        HashMap<String, String> textParam = Maps.newHashMap();
        textParam.put("text_1", text1);
        textParam.put("text_2", text2);
        textParam.put("model", model);
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.TEXT_SIMILARITY,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON),
            ImmutableMap.of("access_token", key, "charset", CharsetKit.UTF_8),
            JSON.toJSONString(textParam));
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        TextSimnetResultDTO textSimnetResultDTO = JSON.parseObject(s, TextSimnetResultDTO.class);
        log.info("similarityText success key={},textSimnetResultDTO={}", key, JSON.toJSONString(textSimnetResultDTO));
        return textSimnetResultDTO;
    }

    public static void main(String[] args) {
        TextSimilarResultDTO textSimilarResultDTO =
            similarityWords("24.f4b0da25ae8e4925fc157a757d3035ff.2592000.1598949848.282335-19618961", "喜欢", "相爱");
        System.out.println(JSON.toJSONString(textSimilarResultDTO));
    }

    /**
     * 词语比较 输入两个词，得到两个词的相似度结果。
     * 
     * @param word1
     * @param word2
     * @return
     * @throws IOException
     */
    public static TextSimilarResultDTO similarityWords(String key, String word1, String word2) {
        log.info("similarityWords start key={},text1={},text2={}", key, word1, word2);
        HashMap<String, String> wordParam = Maps.newHashMap();
        wordParam.put("word_1", word1);
        wordParam.put("word_2", word2);
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.WOEDS_SIMILARITY,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON),
            ImmutableMap.of("access_token", key, "charset", CharsetKit.UTF_8),
            JSON.toJSONString(wordParam));
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        TextSimilarResultDTO similarResultDTO = JSON.parseObject(s, TextSimilarResultDTO.class);
        log.info("similarityWords success key={},similarResultDTO={}", key, JSON.toJSONString(similarResultDTO));
        return similarResultDTO;
    }

}
