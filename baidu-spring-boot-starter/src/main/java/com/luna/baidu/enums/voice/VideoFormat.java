package com.luna.baidu.enums.voice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weidian
 */

public enum VideoFormat {
    MP3_16K(1, "mp3-16k"),
    MP3_48K(2, "mp3-48k"),
    WAV(3, "wav"),
    PCM_8K(4, "pcm-8k"),
    PCM_16K(5, "pcm-16k");

    private int                              code;
    private String                           name;
    private static Map<Integer, VideoFormat> map = new HashMap<>();

    static {
        for (VideoFormat videoFormat : VideoFormat.values()) {
            map.put(videoFormat.code, videoFormat);
        }
    }

    VideoFormat(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static VideoFormat valueOf(int code) {
        return map.get(code);
    }
}