package com.luna.baidu.constant;

import java.util.Arrays;
import java.util.Objects;

public enum ImageConstant {

    /** URL */
    URL("url", 1),

    /** BASE64 */
    BASE64("base64", 2),

    /** BASE64 */
    IMAGE("image", 3),

    /** BASE64 */
    IMAGE_BASE("BASE64", 4),

    /** URL */
    IMAGE_URL("URL", 5),

    /** FACE_TOKEN */
    FACE_TOKEN("FACE_TOKEN", 6),
    ;

    private String  imageStr;

    private Integer imageType;

    public String getImageStr() {
        return imageStr;
    }

    public void setImageStr(String imageStr) {
        this.imageStr = imageStr;
    }

    public Integer getImageType() {
        return imageType;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    ImageConstant(String imageStr, Integer imageType) {
        this.imageStr = imageStr;
        this.imageType = imageType;
    }

    public static String getTypeStr(Integer orderType) {
        return Arrays.stream(ImageConstant.values()).filter(type -> Objects.equals(type.getImageType(), orderType))
            .findFirst().get()
            .getImageStr();
    }
}
