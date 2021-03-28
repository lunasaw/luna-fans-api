package com.luna.baidu.api;

/**
 * @author Luna@win10
 * @date 2020/4/20 11:46
 */
public class BaiduApiConstant {
    /** AI */
    public static String HOST                 = "https://aip.baidubce.com";

    /** Map */
    public static String MAP_HOST             = "http://api.map.baidu.com";

    /** VOICE */
    public static String VOICE_HOST           = "http://vop.baidu.com";

    /** 语音合成 */
    public static String VOICE_SYNTHESIS      = "https://tsn.baidu.com";

    /** API_KEY */
    public static String API_KEY              = "/oauth/2.0/token";

    /** 通用OCR地址 */
    public static String OCR                  = "/rest/2.0/ocr/v1/general_basic";

    /** 高精度OCr识别带文字位置 */
    public static String OCR_ADDRESS          = "/rest/2.0/ocr/v1/accurate";

    /** 通用文字识别带位置 */
    public static String OCR_ADDRESS_NORMAL   = "/rest/2.0/ocr/v1/general";

    /** 身份证OCR地址 */
    public static String ID_OCR               = "/rest/2.0/ocr/v1/idcard";

    /** 人脸识别 */
    public static String FACE                 = "/rest/2.0/location/v3/detect";

    /** 身份证号与姓名 */
    public static String NAME_IDCARD          = "/rest/2.0/location/v3/person/idmatch";

    /** 人脸搜索 */
    public static String MATCH                = "/rest/2.0/face/v3/match";

    /** 单张活体检测 */
    public static String LIVE                 = "/rest/2.0/face/v3/faceverify";

    /** 通用物体识别 */
    public static String GOODS_IDENTIFY       = "/rest/2.0/image-classify/v2/advanced_general";

    /** 语音识别 */
    public static String VOICE_SPEECH         = "/server_api";

    /** 语音识别极速版 */
    public static String VOICE_SPEECH_FAST    = "/pro_api";

    /** 语音合成 */
    public static String VOICE_SYNTHESIS_PATH = "/text2audio";

    /** 文本纠错 */
    public static String LANGUAGE_PROCESSING  = "/rpc/2.0/nlp/v1/ecnet";

    /** 文本相似度 */
    public static String TEXT_SIMILARITY      = "/rpc/2.0/nlp/v2/simnet";

    /** 词语相似度 */
    public static String WOEDS_SIMILARITY     = "/rpc/2.0/nlp/v2/word_emb_sim";

    /** 获取热点事件 */
    public static String HOT_EVENT            = "/rpc/2.0/creation/v1/hot_list/domain";

    /** 获取事件脉络 */
    public static String EVENT_CONTEXT        = "/rpc/2.0/creation/v1/event/vein_list";

    /** 结构化写作 */
    public static String WRITING              = "/rest/2.0/nlp/v1/gen_article";

    /** 人体状态识别 */
    public static String BODIES               = "/rest/2.0/image-classify/v1/body_attr";

    /** TODO Key 30天更换一次 暂定固定值 */
    public static String BAIDU_KEY            =
        "24.879f8135d8bab56b057a73fe9c3d133b.2592000.1619419464.282335-19618961";

}
