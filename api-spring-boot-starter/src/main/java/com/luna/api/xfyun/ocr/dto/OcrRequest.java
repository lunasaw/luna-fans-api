package com.luna.api.xfyun.ocr.dto;

import com.alibaba.fastjson2.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author luna
 * @date 2024/6/20
 */
@Data
public class OcrRequest {

    @JSONField(name = "payload")
    private Payload   payload;

    @JSONField(name = "parameter")
    private Parameter parameter;

    @JSONField(name = "header")
    private Header    header;

    @Data
    @AllArgsConstructor
    public static class Header {
        @JSONField(name = "app_id")
        private String appId;

        @JSONField(name = "status")
        private int    status;
    }

    @Data
    @AllArgsConstructor
    public static class Parameter {

        @JSONField(name = "sf8e6aca1")
        private Sf8e6aca1 sf8e6aca1;
    }

    @Data
    @AllArgsConstructor
    public static class Payload {

        @JSONField(name = "sf8e6aca1_data_1")
        private Sf8e6aca1DataOne sf8e6aca1DataOne;
    }

    @Data
    public static class ResultDTO {

        @JSONField(name = "compress")
        private String compress;

        @JSONField(name = "format")
        private String format;

        @JSONField(name = "encoding")
        private String encoding;

    }

    @Data
    public static class Sf8e6aca1 {

        @JSONField(name = "result")
        private ResultDTO result;

        @JSONField(name = "category")
        private String    category;

        public static Sf8e6aca1 getInstance() {
            ResultDTO result = new ResultDTO();
            result.setEncoding("utf8");
            result.setCompress("raw");
            result.setFormat("json");
            Sf8e6aca1 sf8e6aca1 = new Sf8e6aca1();
            sf8e6aca1.setCategory("ch_en_public_cloud");
            sf8e6aca1.setResult(result);
            return sf8e6aca1;
        }
    }

    @Data
    public static class Sf8e6aca1DataOne {

        @JSONField(name = "image")
        private String image;

        @JSONField(name = "encoding")
        private String encoding;

        @JSONField(name = "status")
        private int    status;

        public static Sf8e6aca1DataOne getInstance(String base64) {
            Sf8e6aca1DataOne sf8e6aca1DataOne = new Sf8e6aca1DataOne();
            sf8e6aca1DataOne.setEncoding("jpg");
            sf8e6aca1DataOne.setStatus(3);
            sf8e6aca1DataOne.setImage(base64);
            return sf8e6aca1DataOne;
        }
    }
}
