package com.luna.baidu.enums;

import java.util.Arrays;

/**
 * 人脸的类型
 * 
 * @author facemask
 * face_type 否 string 人脸的类型
 * LIVE 表示生活照：通常为手机、相机拍摄的人像图片、或从网络获取的人像图片等
 * IDCARD 表示身份证芯片照：二代身份证内置芯片中的人像照片
 * WATERMARK 表示带水印证件照：一般为带水印的小图，如公安网小图
 * CERT 表示证件照片：如拍摄的身份证、工卡、护照、学生证等证件图片
 */
public enum FaceTypeEnum {
    LIVE(0, "LIVE"),
    IDCARD(1, "IDCARD"),
    WATERMARK(2, "WATERMARK"),
    CERT(3, "CERT");

    private final Integer code;
    private final String  info;

    public static Boolean isVaild(String faceType) {
        return Arrays.stream(values()).anyMatch(e -> e.getInfo().equals(faceType));
    }

    FaceTypeEnum(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
