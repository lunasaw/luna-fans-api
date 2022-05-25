package com.luna.baidu.dto.location;

import lombok.Data;

/**
 * @author Luna@win10
 * @date 2020/4/29 14:37
 */
@Data
public class LocationDO {

    private Double  top;

    private Double  left;

    private Double  width;

    private Double  height;

    /**
     * ++rotation 是 int64 人脸框相对于竖直方向的顺时针旋转角，[-180,180]
     */
    private Integer rotation;

    @Override
    public String toString() {
        return "Body{" +
            "top=" + top +
            ", left=" + left +
            ", width=" + width +
            ", height=" + height +
            '}';
    }
}
