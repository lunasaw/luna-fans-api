package com.luna.baidu.api;

/**
 * @author Luna@win10
 * @date 2020/4/20 11:46
 */
public class BaiduApiContent {
    /** AI */
    public static String HOST                = "https://aip.baidubce.com";

    /** Map */
    public static String MAP_HOST            = "http://api.map.baidu.com";

    /** VOICE */
    public static String VOICE_HOST          = "http://vop.baidu.com";

    /** 通用OCR地址 */
    public static String OCR                 = "/rest/2.0/ocr/v1/general_basic";

    /** 高精度OCr识别带文字位置 */
    public static String OCR_ADDRESS         = "/rest/2.0/ocr/v1/accurate";

    /** 通用文字识别带位置 */
    public static String OCR_ADDRESS_NORMAL  = "/rest/2.0/ocr/v1/general";

    /** 身份证OCR地址 */
    public static String ID_OCR              = "/rest/2.0/ocr/v1/idcard";

    /** 人脸识别 */
    public static String FACE                = "/rest/2.0/face/v3/detect";

    /** 身份证号与姓名 */
    public static String NAME_IDCARD         = "/rest/2.0/face/v3/person/idmatch";

    /** 人脸搜索 */
    public static String MATCH               = "/rest/2.0/face/v3/match";

    /** 单张活体检测 */
    public static String LIVE                = "/rest/2.0/face/v3/faceverify";

    /** 通用物体识别 */
    public static String GOODS_IDENTIFY      = "/rest/2.0/image-classify/v2/advanced_general";

    /** 语音识别 */
    public static String VOICE_SPEECH        = "/server_api";

    /** 文本纠错 */
    public static String LANGUAGE_PROCESSING = "/rpc/2.0/nlp/v1/ecnet";

    /** 文本相似度 */
    public static String TEXT_SIMILARITY     = "/rpc/2.0/nlp/v2/simnet";

    /** 词语相似度 */
    public static String WOEDS_SIMILARITY    = "/rpc/2.0/nlp/v2/word_emb_sim";

    /** 获取热点事件 */
    public static String HOT_EVENT           = "/rpc/2.0/creation/v1/hot_list/domain";

    /** 获取事件脉络 */
    public static String EVENT_CONTEXT       = "/rpc/2.0/creation/v1/event/vein_list";

    /** 结构化写作 */
    public static String WRITING             = "/rest/2.0/nlp/v1/gen_article";

    /** TODO Key 30天更换一次 暂定固定值 */
    public static String BAIDU_KEY           =
        "24.a6610a2137c77fc9db29c29d64f8d8f0.2592000.1592999017.282335-19618961";

}
