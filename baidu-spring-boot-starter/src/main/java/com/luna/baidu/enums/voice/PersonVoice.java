package com.luna.baidu.enums.voice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luna
 */

public enum PersonVoice {
    DU_XIAO_YU(1, "度小宇"),
    DU_XIAO_MEI(0, "度小美"),
    DU_XIAO_YAO_BASIC(3, "度逍遥（基础）"),
    DU_YA_YA(4, "度丫丫"),
    DU_XIAO_YAO_PREMIUM(5003, "度逍遥（精品）"),
    DU_XIAO_LU(5118, "度小鹿"),
    DU_BO_WEN(106, "度博文"),
    DU_XIAO_TONG(110, "度小童"),
    DU_XIAO_MENG(111, "度小萌"),
    DU_MI_DUO(103, "度米朵"),
    DU_XIAO_JIAO(5, "度小娇");

    private int code;
    private String name;
    private static Map<Integer, PersonVoice> map = new HashMap<>();

    static {
        for (PersonVoice personVoice : PersonVoice.values()) {
            map.put(personVoice.code, personVoice);
        }
    }

    PersonVoice(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static PersonVoice valueOf(int code) {
        return map.get(code);
    }
}