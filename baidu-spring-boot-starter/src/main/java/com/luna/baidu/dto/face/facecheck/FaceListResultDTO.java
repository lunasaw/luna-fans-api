package com.luna.baidu.dto.face.facecheck;

import com.luna.baidu.dto.location.LocationDO;
import lombok.Data;

import java.util.List;

/**
 * @author luna
 * 2022/5/3
 */
@Data
public class FaceListResultDTO {
    /**
     * 人脸在图片中的位置
     */
    private LocationDO      location;

    /**
     * 人脸置信度，范围【0~1】，代表这是一张人脸的概率，0最小、1最大。其中返回0或1时，数据类型为Integer
     */
    private Double          faceProbability;

    /**
     * 人脸旋转角度参数
     */
    private List<Angle>     angle;

    /**
     * 年龄 ，当face_field包含age时返回
     */
    private Double          age;

    /**
     * +spoofing 否 double 判断图片是否为合成图
     */
    private Double          spoofing;

    /**
     * 表情，当 face_field包含expression时返回
     */
    private Expression      expression;

    /**
     * +face_shape 否 array 脸型，当face_field包含face_shape时返回
     */
    private List<FaceShape> faceShape;

    /**
     * +gender 否 array 性别，face_field包含gender时返回
     */
    private List<Gender>    gender;

    /**
     * +glasses 否 array 是否带眼镜，face_field包含glasses时返回
     */
    private List<Glasses>   glasses;

    /**
     * +eye_status 否 array 双眼状态（睁开/闭合） face_field包含eye_status时返回
     */
    private List<EyeStatus> eyeStatus;

    /**
     * +emotion 否 array 情绪 face_field包含emotion时返回
     */
    private List<Emotion>   emotion;

    /**
     * +face_type 否 array 真实人脸/卡通人脸 face_field包含face_type时返回
     */
    private List<FaceType>  faceType;

    /**
     * +mask 否 array 口罩识别 face_field包含mask时返回
     */
    private List<Mask>      mask;

    /**
     * +landmark 否 array 4个关键点位置，左眼中心、右眼中心、鼻尖、嘴中心。face_field包含landmark时返回
     */
    private List<String>    landmark;
    /**
     * +landmark72 否 array 72个特征点位置 face_field包含landmark时返回
     */
    private List<String>    landmark72;
    /**
     * +landmark150 否 array 150个特征点位置 face_field包含landmark150时返回
     */
    private List<String>    landmark150;

    /**
     * +quality 否 array 人脸质量信息。face_field包含quality时返回
     */
    private List<Quality>   quality;

    @Data
    public static class Quality {
        /**
         * ++occlusion 否 array 人脸各部分遮挡的概率，范围[0~1]，0表示完整，1表示不完整
         * ++blur 否 double 人脸模糊程度，范围[0~1]，0表示清晰，1表示模糊
         * ++illumination 否 double 取值范围在[0~255], 表示脸部区域的光照程度 越大表示光照越好
         * ++completeness 否 int64 人脸完整度，0或1, 0为人脸溢出图像边界，1为人脸都在图像边界内
         */
        private List<Occlusion> occlusion;

        private Double          blur;

        private Double          illumination;

        private Long            completeness;

        @Data
        public static class Occlusion {
            /**
             * +++left_eye 否 double 左眼遮挡比例，[0-1] ，1表示完全遮挡
             * +++right_eye 否 double 右眼遮挡比例，[0-1] ， 1表示完全遮挡
             * +++nose 否 double 鼻子遮挡比例，[0-1] ， 1表示完全遮挡
             * +++mouth 否 double 嘴巴遮挡比例，[0-1] ， 1表示完全遮挡
             * +++left_cheek 否 double 左脸颊遮挡比例，[0-1] ， 1表示完全遮挡
             * +++right_cheek 否 double 右脸颊遮挡比例，[0-1] ， 1表示完全遮挡
             * +++chin 否 double 下巴遮挡比例，，[0-1] ， 1表示完全遮挡
             */
            private Double leftEye;
            private Double rightEye;
            private Double nose;
            private Double mouth;
            private Double leftCheek;
            private Double rightCheek;
            private Double chin;
        }
    }

    @Data
    public static class Mask {
        /**
         * ++type 否 int 没戴口罩/戴口罩 取值0或1 0代表没戴口罩 1 代表戴口罩
         * ++probability 否 double 置信度，范围0~1
         */
        private Integer type;

        private Double  probability;
    }

    @Data
    public static class EyeStatus {
        /**
         * ++left_eye 否 double 左眼状态 [0,1]取值，越接近0闭合的可能性越大
         * ++right_eye 否 double 右眼状态 [0,1]取值，越接近0闭合的可能性越大
         */
        private Double leftEye;

        private Double rightEye;
    }

    @Data
    public static class Emotion {
        /**
         * ++type 否 string angry:愤怒 disgust:厌恶 fear:恐惧 happy:高兴 sad:伤心 surprise:惊讶 neutral:无表情 pouty: 撅嘴 grimace:鬼脸
         * ++probability 否 double 情绪置信度，范围0~1
         */
        private String type;

        private Double probability;
    }

    @Data
    public static class FaceType {
        /**
         * ++type 否 string human: 真实人脸 cartoon: 卡通人脸
         * ++probability 否 double 人脸类型判断正确的置信度，范围【0~1】，0代表概率最小、1代表最大。
         */
        private String type;

        private Double probability;
    }

    @Data
    public static class Glasses {
        /**
         * ++type 否 string male:男性 female:女性
         * ++probability 否 double 性别置信度，范围【0~1】，0代表概率最小、1代表最大。
         */
        private String type;

        private Double probability;
    }

    @Data
    public static class Gender {
        /**
         * ++type 否 string male:男性 female:女性
         * ++probability 否 double 性别置信度，范围【0~1】，0代表概率最小、1代表最大。
         */
        private String type;

        private Double probability;
    }

    @Data
    public static class FaceShape {
        /**
         * ++type 否 double square: 正方形 triangle:三角形 oval: 椭圆 heart: 心形 round: 圆形
         * ++probability 否 double 置信度，范围【0~1】，代表这是人脸形状判断正确的概率，0最小、1最大。
         */
        private String type;

        private Double probability;
    }

    @Data
    public static class Expression {
        /**
         * ++type 否 string none:不笑；smile:微笑；laugh:大笑
         * ++probability 否 double 表情置信度，范围【0~1】，0最小、1最大。
         */
        private String type;

        private Double probability;
    }

    @Data
    public static class Angle {
        /**
         * ++yaw 是 double 三维旋转之左右旋转角[-90(左), 90(右)]
         * ++pitch 是 double 三维旋转之俯仰角度[-90(上), 90(下)]
         * ++roll 是 double 平面内旋转角[-180(逆时针), 180(顺时针)]
         */
        private Double yaw;
        private Double pitch;
        private Double roll;
    }
}
