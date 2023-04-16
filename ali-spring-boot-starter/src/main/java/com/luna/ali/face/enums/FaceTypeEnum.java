package com.luna.ali.face.enums;

import java.util.HashMap;
import java.util.Map;

public enum FaceTypeEnum {
    FACE_DETECTION(1, "Face Detection", "人脸检测"),
    FACE_VERIFICATION(2, "Face Verification", "人脸核身");

    private static final Map<Integer, FaceTypeEnum> BY_ID = new HashMap<>();

    static {
        for (FaceTypeEnum e : values()) {
            BY_ID.put(e.id, e);
        }
    }

    public final int id;
    public final String name;
    public final String description;

    FaceTypeEnum(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static FaceTypeEnum valueOfId(int id) {
        return BY_ID.get(id);
    }
}