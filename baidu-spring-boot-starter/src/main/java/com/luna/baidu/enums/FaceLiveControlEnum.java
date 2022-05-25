package com.luna.baidu.enums;

import java.util.Arrays;

/**
 * 人脸的类型
 * 
 * @author facemask
 * liveness_control 否 string 活体控制 检测结果中不符合要求的人脸会被过滤
 * NONE: 不进行控制
 * LOW:较低的活体要求(高通过率 低攻击拒绝率)
 * NORMAL: 一般的活体要求(平衡的攻击拒绝率, 通过率)
 * HIGH: 较高的活体要求(高攻击拒绝率 低通过率)
 * 默认NONE
 */
public enum FaceLiveControlEnum {
    NONE(0, "NONE"),
    LOW(1, "LOW"),
    NORMAL(2, "NORMAL"),
    HIGH(3, "HIGH");

    private final Integer code;
    private final String  desc;

    public static Boolean isVaild(String faceType) {
        return Arrays.stream(values()).anyMatch(e -> e.getDesc().equals(faceType));
    }

    FaceLiveControlEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
