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

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.luna.api.xfyun.constant.XfConstant;
import com.luna.api.xfyun.ocr.dto.OcrRequest;
import com.luna.api.xfyun.ocr.dto.OcrResponse;
import com.luna.api.xfyun.ocr.dto.OcrTextDTO;
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
        String s = getOcrText(IMAGE_PATH);

        System.out.println(s);

        OcrTextDTO parse = parse(s);
        System.out.println(JSON.toJSONString(getContent(parse)));
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

    private static String getOcrText(String filePath) {
        OcrRequest ocrRequest = new OcrRequest();
        ocrRequest.setHeader(new OcrRequest.Header(XfConstant.APPID, 3));
        ocrRequest.setParameter(new OcrRequest.Parameter(OcrRequest.Sf8e6aca1.getInstance()));
        ocrRequest.setPayload(
            new OcrRequest.Payload(OcrRequest.Sf8e6aca1DataOne.getInstance(Base64.getEncoder().encodeToString(FileTools.read(filePath)))));
        Map<String, String> param = getUrlAuthPath(XfConstant.REQUEST_URL, XfConstant.API_SECRET, XfConstant.API_KEY);


        return HttpUtils.doPostHander(XfConstant.HOST, XfConstant.PATH, ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), param,
            JSON.toJSONString(ocrRequest));
    }

    @SneakyThrows
    private static Map<String, String> getUrlAuthPath(String requestUrl, String apiSecret, String apiKey) {
        // 替换调schema前缀 ，原因是URL库不支持解析包含ws,wss schema的url
        String httpRequestUrl = requestUrl.replace("ws://", "http://").replace("wss://", "https://");
        URL url = new URL(httpRequestUrl);
        // 获取当前日期并格式化
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());
        String host = url.getHost();
        StringBuilder builder = new StringBuilder("host: ").append(host).append("\n").//
            append("date: ").append(date).append("\n").//
            append("POST ").append(url.getPath()).append(" HTTP/1.1");
        Charset charset = StandardCharsets.UTF_8;
        Mac mac = Mac.getInstance("hmacsha256");
        SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(charset), "hmacsha256");
        mac.init(spec);
        byte[] hexDigits = mac.doFinal(builder.toString().getBytes(charset));
        String sha = Base64.getEncoder().encodeToString(hexDigits);
        String authorization =
            String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line", sha);
        String authBase = Base64.getEncoder().encodeToString(authorization.getBytes(charset));

        return ImmutableMap.of("authorization", authBase, "host", host, "date", date);
    }
}
