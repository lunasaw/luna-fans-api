package com.luna.tencent.api;

/**
 * @author Luna@win10
 * @date 2020/4/26 13:57
 */
public class TencentConstant {

    public static final String HOST_MAP                       = "https://apis.map.qq.com";

    public static final String HOST_OCR                       = "ocr.tencentcloudapi.com";

    public static final String FACE_CARD                      = "faceid.tencentcloudapi.com";

    public static final String FACE_CHECK                     = "iai.tencentcloudapi.com";

    public static final String VOICE_IDENTIFY                 = "asr.tencentcloudapi.com";

    public static final String HOST_SMS                       = "sms.tencentcloudapi.com";

    public static final String TENCENT_MARK_AUTHENTICATION    =
        "https://service-2n5qa8cl-1256140209.ap-shanghai.apigateway.myqcloud.com/release/eid/check";

    /** 经纬度转地址或者地址转坐标 */
    public static final String LONGITUDE_LATITUDE_FOR_ADDRESS = "/ws/geocoder/v1/";

    /** IP转地址 */
    public static final String IP_2_ADDRESS                   = "/ws/location/v1/ip";

    /** 关键词输入提示 */
    public static final String SUGGESTION                     = "/ws/place/v1/suggestion";

    /** 坐标转换 */
    public static final String TRANSLATE                      = "/ws/coord/v1/translate";
}
