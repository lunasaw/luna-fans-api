package com.luna.api.xfyun.ocr.dto;

import java.util.List;

import com.alibaba.fastjson2.annotation.JSONField;

import lombok.Data;

@Data
public class OcrTextDTO {

    @JSONField(name = "pages")
    private List<PagesItem> pages;

    @JSONField(name = "category")
    private String          category;

    @JSONField(name = "version")
    private String          version;

    @Data
    public static class PagesItem {

        @JSONField(name = "exception")
        private int             exception;

        @JSONField(name = "width")
        private int             width;

        @JSONField(name = "angle")
        private int             angle;

        @JSONField(name = "lines")
        private List<LinesItem> lines;

        @JSONField(name = "height")
        private int             height;
    }

    @Data
    public static class CenterPoint {

        @JSONField(name = "x")
        private int X;

        @JSONField(name = "y")
        private int Y;
    }

    @Data
    public static class LinesItem {

        @JSONField(name = "exception")
        private int                 exception;

        @JSONField(name = "coord")
        private List<CoordItem>     coord;

        @JSONField(name = "words")
        private List<WordsItem>     words;

        @JSONField(name = "angle")
        private int                 angle;

        @JSONField(name = "conf")
        private double              conf;

        @JSONField(name = "word_units")
        private List<WordUnitsItem> wordUnits;
    }

    @Data
    public static class CoordItem {

        @JSONField(name = "x")
        private int X;

        @JSONField(name = "y")
        private int Y;
    }

    @Data
    public static class WordsItem {

        @JSONField(name = "coord")
        private List<CoordItem> coord;

        @JSONField(name = "conf")
        private double          conf;

        @JSONField(name = "content")
        private String          content;
    }

    @Data
    public static class WordUnitsItem {

        @JSONField(name = "center_point")
        private CenterPoint     centerPoint;

        @JSONField(name = "coord")
        private List<CoordItem> coord;

        @JSONField(name = "conf")
        private double          conf;

        @JSONField(name = "content")
        private String          content;
    }
}