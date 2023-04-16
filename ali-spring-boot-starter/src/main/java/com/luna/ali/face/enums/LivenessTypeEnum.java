package com.luna.ali.face.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luna
 */

public enum LivenessTypeEnum {
    INFRARED_LIVE_NESS(1, "Infrared Liveness", "红外活体检测"),
    NORMAL_LIVE_NESS(2, "Normal Liveness", "普通活体检测"),
    VIDEO_LIVE_NESS(3, "Video Liveness", "视频活体检测"),
    ;

    private static final Map<Integer, LivenessTypeEnum> BY_ID = new HashMap<>();

    static {
        for (LivenessTypeEnum e : values()) {
            BY_ID.put(e.id, e);
        }
    }

    public final int    id;
    public final String name;
    public final String description;

    LivenessTypeEnum(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static LivenessTypeEnum valueOfId(int id) {
        return BY_ID.get(id);
    }
}