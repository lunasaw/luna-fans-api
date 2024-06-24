package com.luna.api.xfyun.ocr;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.core5.http.ClassicHttpResponse;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.luna.api.xfyun.constant.XfConstant;
import com.luna.api.xfyun.ocr.dto.OcrRequest;
import com.luna.api.xfyun.ocr.dto.OcrResponse;
import com.luna.api.xfyun.ocr.dto.OcrTextDTO;
import com.luna.common.check.Assert;
import com.luna.common.constant.StrPoolConstant;
import com.luna.common.encrypt.Base64Util;
import com.luna.common.file.FileTools;
import com.luna.common.net.HttpUtils;
import com.luna.common.net.HttpUtilsConstant;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author luna
 * @date 2024/6/20
 */
@Slf4j
public class UniversalCharacterRecognitionApi {

    private static final String IMAGE_PATH = "/Users/weidian/Downloads/universal_character_recognition_java_demo/example/1.jpg";

    @SneakyThrows
    public static void main(String[] args) {
        // java -jar  /Users/weidian/compose/git/bfg-1.13.0.jar --delete-files e76d7d8f .git
        // APPId = "e76d7d8f" # 控制台获取
        // APISecret = "Y2Y2ODc2OGQyOWFjMWZhY2JkOTllMDVl" # 控制台获取
        // APIKey = "990e2770b030441fbcc126c691daf5cd" # 控制台获取
        String s = getOcrText("e76d7d8f", "Y2Y2ODc2OGQyOWFjMWZhY2JkOTllMDVl", "990e2770b030441fbcc126c691daf5cd", IMAGE_PATH);
        System.out.println(s);
        OcrTextDTO parse = parse(s);
        System.out.println(JSON.toJSONString(getContent(parse)));
    }

    public static List<String> getContent(String filePath) {
        return getContent(XfConstant.APPID, XfConstant.API_SECRET, XfConstant.API_KEY, filePath);
    }

    public static List<String> getContent(String appId, String apiSecret, String apiKey, String filePath) {
        Assert.notNull(filePath, "文件路径不能为空");
        String ocrText = getOcrText(appId, apiSecret, apiKey, filePath);
        if (StringUtils.isBlank(ocrText)) {
            return Collections.emptyList();
        }
        return getContent(parse(ocrText));
    }

    public static List<String> getContent(OcrTextDTO ocrTextDTO) {
        if (ocrTextDTO == null) {
            return new ArrayList<>();
        }
        List<OcrTextDTO.PagesItem> pages = ocrTextDTO.getPages();
        if (CollectionUtils.isEmpty(pages)) {
            return new ArrayList<>();
        }

        List<List<OcrTextDTO.LinesItem>> lines = pages.stream().map(OcrTextDTO.PagesItem::getLines).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(lines)) {
            return new ArrayList<>();
        }

        ArrayList<String> words = Lists.newArrayList();
        for (List<OcrTextDTO.LinesItem> line : lines) {
            if (CollectionUtils.isEmpty(line)) {
                continue;
            }
            List<String> strings = line.stream().map(OcrTextDTO.LinesItem::getWords)
                .filter(CollectionUtils::isNotEmpty)
                .map(e -> e.stream().map(OcrTextDTO.WordsItem::getContent)
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.joining(StrPoolConstant.UNDERLINE)))
                .collect(Collectors.toList());
            if (CollectionUtils.isEmpty(strings)) {
                continue;
            }
            words.addAll(strings);
        }
        return words;
    }

    public static OcrTextDTO parse(String data) {
        OcrResponse ocrResponse = JSON.parseObject(data, OcrResponse.class);
        if (ocrResponse == null) {
            return null;
        }
        OcrResponse.Payload payload = ocrResponse.getPayload();
        if (payload == null) {
            return null;
        }
        String text = Optional.ofNullable(payload.getResult()).map(OcrResponse.Result::getText).orElse(StringUtils.EMPTY);
        String result = new String(Base64Util.decodeBase64(text), StandardCharsets.UTF_8);
        if (StringUtils.isEmpty(result)) {
            return null;
        }
        return JSON.parseObject(result, OcrTextDTO.class);
    }

    private static String getOcrText(String appId, String apiSecret, String apiKey, String filePath) {
        return getOcrText(XfConstant.REQUEST_URL, appId, apiSecret, apiKey, filePath);
    }

    private static String getOcrText(String requestUrl, String appId, String apiSecret, String apiKey, String filePath) {
        OcrRequest ocrRequest = new OcrRequest();
        ocrRequest.setHeader(new OcrRequest.Header(appId, 3));
        ocrRequest.setParameter(new OcrRequest.Parameter(OcrRequest.Sf8e6aca1.getInstance()));
        ocrRequest.setPayload(
            new OcrRequest.Payload(OcrRequest.Sf8e6aca1DataOne.getInstance(Base64.getEncoder().encodeToString(FileTools.read(filePath)))));
        Map<String, String> param = getUrlAuthPath(requestUrl, apiSecret, apiKey);

        ClassicHttpResponse classicHttpResponse =
            HttpUtils.doPost(XfConstant.HOST, XfConstant.PATH, ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), param,
                JSON.toJSONString(ocrRequest));
        String s = HttpUtils.checkResponseAndGetResult(classicHttpResponse, false);
        System.out.println(s);
        return s;
    }

    @SneakyThrows
    private static Map<String, String> getUrlAuthPath(String requestUrl, String apiSecret, String apiKey) {
        Assert.notNull(apiSecret, "apiSecret不能为空");
        Assert.notNull(apiKey, "apiKey不能为空");

        // 替换调schema前缀 ，原因是URL库不支持解析包含ws,wss schema的url
        String httpRequestUrl = requestUrl.replace("ws://", "http://").replace("wss://", "https://");
        URL url = new URL(httpRequestUrl);
        // 获取当前日期并格式化
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());
        String host = url.getHost();
        String builder = "host: " + host + "\n" +
            "date: " + date + "\n" +
            "POST " + url.getPath() + " HTTP/1.1";
        Charset charset = StandardCharsets.UTF_8;
        Mac mac = Mac.getInstance("hmacsha256");
        SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(charset), "hmacsha256");
        mac.init(spec);
        byte[] hexDigits = mac.doFinal(builder.getBytes(charset));
        String sha = Base64.getEncoder().encodeToString(hexDigits);
        String authorization =
            String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line", sha);
        String authBase = Base64.getEncoder().encodeToString(authorization.getBytes(charset));

        return ImmutableMap.of("authorization", authBase, "host", host, "date", date);
    }
}
