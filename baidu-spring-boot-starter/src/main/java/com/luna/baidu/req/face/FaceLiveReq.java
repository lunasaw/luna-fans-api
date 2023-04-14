package com.luna.baidu.req.face;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author luna@mac
 * 2021年05月11日 11:07
 */
@Data
public class FaceLiveReq {

    /**
     * 图片信息(总数据大小应小于10M)，图片上传方式根据image_type来判断；
     * 可以上传同一个用户的1张、3张或8张图片来进行活体判断
     * 注：
     * * (1)后端会选择每组照片中的最高分数作为整体分数。 图片通过json格式上传，格式参考表格下方示例
     * * (2)支持1、3、8张图片输入进行计算，请求格式为数组格式
     */
    private String image;
    /**
     * 图片类型
     * BASE64:图片的base64值，base64编码后的图片数据，需urlEncode，编码后的图片大小不超过2M；
     * URL:图片的 URL地址( 可能由于网络等原因导致下载图片时间过长)；
     * FACE_TOKEN: 人脸图片的唯一标识，调用人脸检测接口时，会为每个人脸图片赋予一个唯一的FACE_TOKEN，同一张图片多次检测得到的FACE_TOKEN是同一个。
     */
    @JSONField(name = "image_type")
    private String imageType;
    /** 包括age,beauty,expression,face_shape,gender,glasses,landmark,quality,face_type,spoofing信息 */
    @JSONField(name = "face_field")
    private String faceField;
    /** 场景信息，程序会视不同的场景选用相对应的模型。当前支持的场景有COMMON(通用场景)，GATE(闸机场景)，默认使用COMMON */
    private String option = "COMMON";

    public FaceLiveReq() {}

    public FaceLiveReq(String image, String imageType, String faceField, String option) {
        this.image = image;
        this.imageType = imageType;
        this.faceField = faceField;
        this.option = option;
    }
}
