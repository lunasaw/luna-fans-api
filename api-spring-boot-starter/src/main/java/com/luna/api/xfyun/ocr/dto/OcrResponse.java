package com.luna.api.xfyun.ocr.dto;

import com.alibaba.fastjson2.annotation.JSONField;

import lombok.Data;

@Data
public class OcrResponse {

    @JSONField(name = "payload")
    private Payload payload;

    @JSONField(name = "header")
    private Header  header;

    @Data
    public static class Header {

        @JSONField(name = "code")
        private int    code;

        @JSONField(name = "message")
        private String message;

        @JSONField(name = "sid")
        private String sid;
    }

    @Data
    public static class Payload {

        @JSONField(name = "result")
        private Result result;
    }

    @Data
    public static class Result {

        @JSONField(name = "compress")
        private String compress;

        @JSONField(name = "format")
        private String format;

        @JSONField(name = "text")
        private String text;

        @JSONField(name = "encoding")
        private String encoding;
    }
}