package com.luna.ali.face.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weidian
 */

public enum ExpressionEnum {
    NEUTRAL(0, "neutral", "中性"),
    HAPPINESS(1, "happiness", "高兴"),
    SURPRISE(2, "surprise", "惊讶"),
    SADNESS(3, "sadness", "伤心"),
    ANGER(4, "anger", "生气"),
    DISGUST(5, "disgust", "厌恶"),
    FEAR(6, "fear", "害怕");

    private int code;
    private String express;
    private String desc;

    private static Map<Integer, ExpressionEnum> map = new HashMap<>();

    static {
        for (ExpressionEnum expression : ExpressionEnum.values()) {
            map.put(expression.code, expression);
        }
    }

    ExpressionEnum(int code, String express, String desc) {
        this.code = code;
        this.express = express;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getExpress() {
        return express;
    }

    public String getDesc() {
        return desc;
    }

    public static ExpressionEnum valueOf(int code) {
        return map.get(code);
    }
}