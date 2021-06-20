package com.luna.baidu.api;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.luna.common.net.HttpUtils;
import com.luna.common.net.HttpUtilsConstant;
import org.apache.http.HttpResponse;

/**
 * @author luna
 * 2021/6/20
 */
public class BaiduAuthApi {

    /**
     * 身份证审核
     * 
     * @param key
     * @param name 名字
     * @param idCardNum 身份证号码
     */
    public static boolean idMatch(String key, String name, String idCardNum) {
        HttpResponse response = HttpUtils.doPost(BaiduApiConstant.HOST, BaiduApiConstant.NAME_ID_CARD,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON),
            ImmutableMap.of("access_token", key),
            JSON.toJSONString(ImmutableMap.of("name", name, "id_card_number", idCardNum)));
        String s = HttpUtils.checkResponseAndGetResult(response, true);
        return JSON.parseObject(s).getInteger("error_code") == 0;
    }

    /**
     * 身份证照片和名字对比
     * 
     * @param key
     * @param name 姓名（注：需要是UTF-8编码的中文）
     * @param image 图片信息(总数据大小应小于10M)，图片上传方式根据image_type来判断
     * @param imageType 图片类型
     * BASE64:图片的base64值，base64编码后的图片数据，编码后的图片大小不超过2M；图片尺寸不超过1920*1080
     * URL:图片的 URL地址( 可能由于网络等原因导致下载图片时间过长)；
     * FACE_TOKEN: 人脸图片的唯一标识，调用人脸检测接口时，会为每个人脸图片赋予一个唯一的FACE_TOKEN，同一张图片多次检测得到的FACE_TOKEN是同一个。
     * @param idCardNumber 身份证号
     * @param qualityControl 图片质量控制
     * NONE: 不进行控制
     * LOW:较低的质量要求
     * NORMAL: 一般的质量要求
     * HIGH: 较高的质量要求
     * 默认 NONE
     * @param livenessControl 活体检测控制
     * NONE: 不进行控制
     * LOW:较低的活体要求(高通过率 低攻击拒绝率)
     * NORMAL: 一般的活体要求(平衡的攻击拒绝率, 通过率)
     * HIGH: 较高的活体要求(高攻击拒绝率 低通过率)
     * 默认NONE
     * @param spoofingControl 合成图控制
     * NONE: 不进行控制
     * LOW:较低的合成图检测要求(高通过率 低攻击拒绝率)
     * NORMAL: 一般的合成图检测要求(平衡的攻击拒绝率, 通过率)
     * HIGH: 较高的活体要求(高攻击拒绝率 低通过率)
     * 默认NONE
     * @return
     */
    public static Double personVerify(String key, String name, String image, String imageType, String idCardNumber,
        String qualityControl, String livenessControl, String spoofingControl) {
        ImmutableMap<String, String> map = ImmutableMap.<String, String>builder()
            .put("image_type", imageType)
            .put("name", name)
            .put("id_card_number", idCardNumber)
            .put("quality_control", qualityControl)
            .put("liveness_control", livenessControl)
            .put("spoofing_control", spoofingControl)
            .build();

        HttpResponse response = HttpUtils.doPost(BaiduApiConstant.HOST, BaiduApiConstant.NAME_ID_CARD,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON),
            ImmutableMap.of("access_token", key),
            JSON.toJSONString(map));
        String s = HttpUtils.checkResponseAndGetResult(response, true);
        String result = JSON.parseObject(s).getString("result");
        return JSON.parseObject(result).getDouble("score");
    }

    /**
     * 身份证照片和名字对比
     *
     * @param image URL
     * @return
     */
    public static Double personVerifyWithUrl(String key, String name, String image,
        String idCardNumber, String qualityControl, String livenessControl, String spoofingControl) {
        return personVerify(key, name, image, "URL", idCardNumber, qualityControl, livenessControl, spoofingControl);
    }

    public static Double personVerifyWithUrl(String key, String name, String image, String idCardNumber) {
        return personVerify(key, name, image, "URL", idCardNumber, "NONE", "NONE", "NONE");
    }

    /**
     * 身份证照片和名字对比
     *
     * @param image BASE64
     * @return
     */
    public static Double personVerifyWithBASE64(String key, String name, String image,
        String idCardNumber, String qualityControl, String livenessControl, String spoofingControl) {
        return personVerify(key, name, image, "BASE64", idCardNumber, qualityControl, livenessControl, spoofingControl);
    }

    public static Double personVerifyWithBASE64(String key, String name, String image, String idCardNumber) {
        return personVerify(key, name, image, "BASE64", idCardNumber, "NONE", "NONE", "NONE");
    }

    /**
     * 身份证照片和名字对比
     *
     * @param image BASE64
     * @return
     */
    public static Double personVerifyWithFaceToken(String key, String name, String image,
        String idCardNumber, String qualityControl, String livenessControl, String spoofingControl) {
        return personVerify(key, name, image, "FACE_TOKEN", idCardNumber, qualityControl, livenessControl,
            spoofingControl);
    }

    public static Double personVerifyWithFaceToken(String key, String name, String image, String idCardNumber) {
        return personVerify(key, name, image, "FACE_TOKEN", idCardNumber, "NONE", "NONE", "NONE");
    }
}
