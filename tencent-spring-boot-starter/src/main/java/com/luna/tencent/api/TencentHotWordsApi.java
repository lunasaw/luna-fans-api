package com.luna.tencent.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.luna.common.file.FileTools;
import com.luna.common.net.HttpUtils;
import com.luna.common.text.ChainTreeMap;
import com.luna.tencent.dto.hotword.HotWordDTO;
import com.luna.tencent.dto.hotword.VocabDTO;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author luna
 * 2021/6/14
 */
public class TencentHotWordsApi {

    private static final Logger log = LoggerFactory.getLogger(TencentHotWordsApi.class);

    /**
     * 用户通过本接口进行热词表的创建。
     * • 默认最多可创建30个热词表。
     * • 每个热词表最多可添加128个词，每个词最长10个字，不能超出限制。
     * • 热词表可以通过数组或者本地文件形式上传。
     * • 本地文件必须为UTF-8编码格式，每行仅添加一个热词且不能包含标点和特殊字符。
     * • 热词权重取值范围为[1,10]之间的整数，权重越大代表该词被识别出来的概率越大。
     * 
     * @param secretid
     * @param key
     * @param name 热词表名称，长度在1-255之间
     * @param description 热词表描述，长度在0-1000之间
     * @param wordWeights 词权重数组，包含全部的热词和对应的权重。每个热词的长度不大于10，权重为[1,10]之间整数，数组长度不大于128
     * @param wordWeightStr 词权重文件（纯文本文件）的二进制base64编码，以行分隔，每行的格式为word|weight，即以英文符号|为分割，左边为词，右边为权重，如：你好|5。
     * 当用户传此参数（参数长度大于0），即以此参数解析词权重，WordWeights会被忽略
     */
    public static String createHotWords(String secretid, String key, String name, String description,
        List<HotWordDTO> wordWeights, String wordWeightStr) {

        ChainTreeMap<String, Object> map = ChainTreeMap.newChainMap();
        map.putIfNotEmpty("Name", name);
        map.putIfNotEmpty("Description", description);
        map.putIfNotEmpty("WordWeights", wordWeights);
        map.putIfNotEmpty("WordWeightStr", wordWeightStr);

        String body = JSONArray.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(secretid, key, "asr",
                TencentConstant.VOICE_IDENTIFY, "", "CreateAsrVocab", "2019-06-14", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.VOICE_IDENTIFY, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        String response = JSON.parseObject(s).getString("Response");
        String vocabId = JSON.parseObject(response).getString("VocabId");
        log.info("createHotWords start secretid={}, key={}, vocabId={}", secretid, key,
            JSON.toJSONString(vocabId));
        return vocabId;
    }

    public static String createHotWordsWithWordWeights(String secretid, String key, String name, String description,
        List<HotWordDTO> wordWeights) {
        return createHotWords(secretid, key, name, description,
            wordWeights, null);
    }

    public static String createHotWordsWithWordWeightStr(String secretid, String key, String name, String description,
        String wordWeightStr) {
        return createHotWords(secretid, key, name, description,
            null, wordWeightStr);
    }

    public static String createHotWordsWithWordWeights(String secretid, String key, String name, String description,
        Map<String, Integer> wordWeights) {

        ArrayList<HotWordDTO> list = Lists.newArrayList();
        wordWeights.forEach((k, v) -> list.add(new HotWordDTO(k, v)));
        return createHotWords(secretid, key, name, description,
            list, null);
    }

    /**
     * 下载热词表
     * 用户通过本接口进行热词表的下载，获得词表权重文件形式的 base64 值，文件形式为通过 “|” 分割的词和权重，即 word|weight 的形式。
     * 
     * @param secretid
     * @param key
     * @param vocabId
     */
    public static byte[] downloadHotWords(String secretid, String key, String vocabId) {
        ImmutableMap<String, String> map = ImmutableMap.of("VocabId", vocabId);
        String body = JSONArray.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(secretid, key, "asr",
                TencentConstant.VOICE_IDENTIFY, "", "DownloadAsrVocab", "2019-06-14", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.VOICE_IDENTIFY, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        String response = JSON.parseObject(s).getString("Response");
        String wordWeightStr = JSON.parseObject(response).getString("WordWeightStr");
        log.info("getHotWordsList start secretid={}, key={}, wordWeightStr={}", secretid, key,
            JSON.toJSONString(wordWeightStr));
        return Base64.getDecoder().decode(wordWeightStr);
    }

    public static List<HotWordDTO> downloadHotWordsWithHotWords(String secretid, String key, String vocabId) {
        byte[] bytes = downloadHotWords(secretid, key, vocabId);
        String hotwords = new String(bytes, StandardCharsets.UTF_8);
        String[] split = hotwords.split("\r\n");
        List<HotWordDTO> collect = Arrays.stream(split).map(s -> {
            String[] strings = s.split("\\|");
            return new HotWordDTO(strings[0], Integer.parseInt(strings[1]));
        }).collect(Collectors.toList());
        return collect;
    }

    public static void downloadHotWordsWithFileName(String secretid, String key, String vocabId,
        String fileName) {
        byte[] bytes = downloadHotWords(secretid, key, vocabId);
        FileTools.createDirectory(fileName);
        FileTools.write(bytes, fileName);
    }

    /**
     * 用户通过该接口，可获得所有的热词表及其信息。
     * 
     * @param secretid
     * @param key
     * @param tagInfos 标签信息，格式为“$TagKey : $TagValue ”，中间分隔符为“空格”+“:”+“空格”
     * @param offset 分页Offset
     * @param limit 分页Limit
     */
    public static List<VocabDTO> getHotWordsList(String secretid, String key, List<String> tagInfos,
        String offset, String limit) {
        ImmutableMap<String, Object> map = ImmutableMap.of("TagInfos", tagInfos, "Offset", offset, "Limit", limit);
        String body = JSONArray.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(secretid, key, "asr",
                TencentConstant.VOICE_IDENTIFY, "", "GetAsrVocabList", "2019-06-14", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.VOICE_IDENTIFY, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        String response = JSON.parseObject(s).getString("Response");
        String vocab = JSON.parseObject(response).getString("VocabList");
        List<VocabDTO> vocabDTOS = JSON.parseArray(vocab, VocabDTO.class);
        log.info("getHotWordsList start secretid={}, key={}, vocabDTOS={}", secretid, key,
            JSON.toJSONString(vocabDTOS));
        return vocabDTOS;
    }

    public static List<VocabDTO> getHotWordsListWithTag(String secretid, String key, Map<String, String> tagInfos,
        String offset, String limit) {

        ArrayList<String> list = Lists.newArrayList();
        tagInfos.forEach((k, v) -> {
            list.add("$" + k + " : $" + v);
        });
        return getHotWordsList(secretid, key, list, offset, limit);
    }

    public static List<VocabDTO> getHotWordsList(String secretid, String key) {
        return getHotWordsList(secretid, key, null, null, null);
    }

    /**
     * 用户通过本接口进行热词表的更新。
     * 
     * @param secretid
     * @param key
     * @param vocabId 热词表ID
     * @param name 热词表名称，长度在1-255之间
     * @param description 热词表描述，长度在0-1000之间
     * @param wordWeights 词权重数组，包含全部的热词和对应的权重。每个热词的长度不大于10，权重为[1,10]之间整数，数组长度不大于128
     * @param wordWeightStr 词权重文件（纯文本文件）的二进制base64编码，以行分隔，每行的格式为word|weight，即以英文符号|为分割，左边为词，右边为权重，如：你好|5。
     * 当用户传此参数（参数长度大于0），即以此参数解析词权重，WordWeights会被忽略
     */
    public static String updateHotWords(String secretid, String key, String vocabId, String name, String description,
        List<HotWordDTO> wordWeights, String wordWeightStr) {
        ImmutableMap<String, Object> map = ImmutableMap.of("Name", name, "VocabId", vocabId, "Description", description,
            "WordWeights", wordWeights, "WordWeightStr", wordWeightStr);

        String body = JSONArray.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(secretid, key, "asr",
                TencentConstant.VOICE_IDENTIFY, "", "UpdateAsrVocab", "2019-06-14", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.VOICE_IDENTIFY, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        String response = JSON.parseObject(s).getString("Response");
        vocabId = JSON.parseObject(response).getString("VocabId");
        log.info("updateHotWords start secretid={}, key={}, vocabId={}", secretid, key,
            JSON.toJSONString(vocabId));
        return vocabId;
    }

    public static String updateHotWordsWithWordWeights(String secretid, String key, String vocabId, String name,
        String description,
        List<HotWordDTO> wordWeights) {
        return updateHotWords(secretid, key, vocabId, name, description,
            wordWeights, null);
    }

    public static String updateHotWordsWithWordWeightStr(String secretid, String key, String vocabId, String name,
        String description,
        String wordWeightStr) {
        return updateHotWords(secretid, key, vocabId, name, description,
            null, wordWeightStr);
    }

    public static String updateHotWordsWithWordWeights(String secretid, String key, String vocabId, String name,
        String description,
        Map<String, Integer> wordWeights) {

        ArrayList<HotWordDTO> list = Lists.newArrayList();
        wordWeights.forEach((k, v) -> list.add(new HotWordDTO(k, v)));
        return updateHotWords(secretid, key, vocabId, name, description,
            list, null);
    }

    /**
     * 用户通过该接口可以设置热词表的默认状态。初始状态为0，用户可设置状态为1，即为默认状态。默认状态表示用户在请求识别时，如不设置热词表ID，则默认使用状态为1的热词表。
     * 
     * @param secretid
     * @param key
     * @param vocabId
     * @param state 热词表状态，1：设为默认状态；0：设为非默认状态。
     */
    public static String setHotWordsStatus(String secretid, String key, String vocabId, Integer state) {
        ImmutableMap<String, Object> map = ImmutableMap.of("VocabId", vocabId, "State", state);
        String body = JSONArray.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(secretid, key, "asr",
                TencentConstant.VOICE_IDENTIFY, "", "DeleteAsrVocab", "2019-06-14", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.VOICE_IDENTIFY, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        String response = JSON.parseObject(s).getString("Response");
        vocabId = JSON.parseObject(response).getString("VocabId");
        log.info("setHotWordsStatus start secretid={}, key={}, vocabId={}", secretid, key,
            JSON.toJSONString(vocabId));
        return vocabId;
    }

    /**
     * 用户通过本接口进行热词表的删除。
     * 
     * @param secretid
     * @param key
     * @param vocabId 热词表Id
     */
    public static void deleteHotWordsStatus(String secretid, String key, String vocabId) {
        HashMap<String, Object> map = Maps.newHashMap();

        String body = JSONArray.toJSONString(ImmutableMap.of("VocabId", vocabId));
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(secretid, key, "asr",
                TencentConstant.VOICE_IDENTIFY, "", "SetVocabState", "2019-06-14", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.VOICE_IDENTIFY, "/", postHeader, null, body);
        HttpUtils.checkResponseAndGetResult(httpResponse, true);
    }

    /**
     * 用户根据词表的ID可以获取对应的热词表信息
     * 
     * @param secretid
     * @param key
     * @param vocabId
     */
    public static VocabDTO getHotWordsListWithVocabId(String secretid, String key, String vocabId) {
        HashMap<String, Object> map = Maps.newHashMap();
        String body = JSONArray.toJSONString(ImmutableMap.of("VocabId", vocabId));
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(secretid, key, "asr",
                TencentConstant.VOICE_IDENTIFY, "", "GetAsrVocab", "2019-06-14", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.VOICE_IDENTIFY, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        String response = JSON.parseObject(s).getString("Response");
        VocabDTO vocabDTO = JSON.parseObject(response, VocabDTO.class);
        log.info("getHotWordsListWithVocabId start secretid={}, key={}, vocabDTO={}", secretid, key,
            JSON.toJSONString(vocabDTO));
        return vocabDTO;
    }
}
