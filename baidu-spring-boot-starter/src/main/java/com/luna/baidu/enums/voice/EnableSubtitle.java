package com.luna.baidu.enums.voice;

import java.util.HashMap;
import java.util.Map;

public enum EnableSubtitle {
    NO_SUBTITLE(0, "不开启字幕"),
    SENTENCE_LEVEL_SUBTITLE(1, "开启句级别字幕"),
    WORD_LEVEL_SUBTITLE(2, "开启词级别字幕");

    private final int code;
    private final String desc;
    private static final Map<Integer, EnableSubtitle> map = new HashMap<>();

    static {
        for (EnableSubtitle enableSubtitle : EnableSubtitle.values()) {
            map.put(enableSubtitle.code, enableSubtitle);
        }
    }

    EnableSubtitle(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static EnableSubtitle getByCode(int code) {
        return map.get(code);
    }
}