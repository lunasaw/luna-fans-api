package com.luna.baidu.enums.voice;

import java.util.HashMap;
import java.util.Map;

/**
 * Audio format enum class
 */
public enum AudioFormat {
    PCM("pcm", 0),
    WAV("wav", 1),
    AMR("amr", 2),
    M4A("m4a", 3);

    private static final Map<String, AudioFormat> map = new HashMap<>();

    static {
        for (AudioFormat format : AudioFormat.values()) {
            map.put(format.name, format);
        }
    }

    private final String name;
    private final int code;

    AudioFormat(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static AudioFormat fromName(String name) {
        return map.get(name);
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}