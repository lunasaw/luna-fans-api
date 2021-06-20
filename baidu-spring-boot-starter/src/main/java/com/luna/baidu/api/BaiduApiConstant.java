package com.luna.baidu.api;

/**
 * @author Luna@win10
 * @date 2020/4/20 11:46
 */
public class BaiduApiConstant {
    /** AI */
    public static final String HOST                   = "https://aip.baidubce.com";

    /** Map */
    public static final String MAP_HOST               = "http://api.map.baidu.com";

    /** VOICE */
    public static final String VOICE_HOST             = "http://vop.baidu.com";

    /** 语音合成 */
    public static final String VOICE_SYNTHESIS        = "https://tsn.baidu.com";

    /** API_KEY */
    public static final String API_KEY                = "/oauth/2.0/token";

    /** 通用OCR地址 */
    public static final String OCR                    = "/rest/2.0/ocr/v1/general_basic";

    /** 高精度OCr识别带文字位置 */
    public static final String OCR_ADDRESS            = "/rest/2.0/ocr/v1/accurate";

    /** 通用文字识别带位置 */
    public static final String OCR_ADDRESS_NORMAL     = "/rest/2.0/ocr/v1/general";

    /** 身份证OCR地址 */
    public static final String ID_OCR                 = "/rest/2.0/ocr/v1/idcard";

    /** 人脸识别 */
    public static final String FACE                   = "/rest/2.0/location/v3/detect";

    /** 人脸注册 */
    public static final String FACE_USER_ADD          = "/rest/2.0/face/v3/faceset/user/add";

    /** 人脸更新 */
    public static final String FACE_USER_UPDATE       = "/rest/2.0/face/v3/faceset/user/update";

    /** 人脸删除 */
    public static final String FACE_USER_FACE_DELETE  = "/rest/2.0/face/v3/faceset/face/delete";

    /** 人员信息 */
    public static final String FACE_USER_INFO         = "/rest/2.0/face/v3/faceset/user/get";

    /** 人员人脸列表 */
    public static final String FACE_USER_FACE_LIST    = "/rest/2.0/face/v3/faceset/face/getlist";

    /** 删除用户 */
    public static final String FACE_USER_DELETE       = "/rest/2.0/face/v3/faceset/user/delete";

    /** 人员复制 */
    public static final String FACE_USER_COPY         = "/rest/2.0/face/v3/faceset/user/copy";

    /** 添加用户组 */
    public static final String FACE_USER_CREATE_GROUP = "/rest/2.0/face/v3/faceset/group/add";

    /** 查询用户组 */
    public static final String FACE_USER_GROUP_LIST   = "/rest/2.0/face/v3/faceset/group/getlist";

    /** 删除用户组 */
    public static final String FACE_USER_GROUP_DELETE = "/rest/2.0/face/v3/faceset/group/delete";

    /** 身份证号与姓名 */
    public static final String NAME_ID_CARD           = "/rest/2.0/face/v3/person/idmatch";

    /** 人脸对比 */
    public static final String MATCH                  = "/rest/2.0/face/v3/match";

    /** 人脸搜索 */
    public static final String SEARCH                 = "/rest/2.0/face/v3/search";

    /** 单张活体检测 */
    public static final String LIVE                   = "/rest/2.0/face/v3/faceverify";

    /** 通用物体识别 */
    public static final String GOODS_IDENTIFY         = "/rest/2.0/image-classify/v2/advanced_general";

    /** 语音识别 */
    public static final String VOICE_SPEECH           = "/server_api";

    /** 语音识别极速版 */
    public static final String VOICE_SPEECH_FAST      = "/pro_api";

    /** 语音合成 */
    public static final String VOICE_SYNTHESIS_PATH   = "/text2audio";

    /** 音频文件转写 */
    public static final String VOICE_TO_WRITE         = "/rpc/2.0/aasr/v1/create";

    /** 音频文件转写结果查询 */
    public static final String VOICE_TO_QUERY         = "/rpc/2.0/aasr/v1/query";

    /** 文本纠错 */
    public static final String LANGUAGE_PROCESSING    = "/rpc/2.0/nlp/v1/ecnet";

    /** 文本相似度 */
    public static final String TEXT_SIMILARITY        = "/rpc/2.0/nlp/v2/simnet";

    /** 词语相似度 */
    public static final String WOEDS_SIMILARITY       = "/rpc/2.0/nlp/v2/word_emb_sim";

    /** 获取热点事件 */
    public static final String HOT_EVENT              = "/rpc/2.0/creation/v1/hot_list/domain";

    /** 获取事件脉络 */
    public static final String EVENT_CONTEXT          = "/rpc/2.0/creation/v1/event/vein_list";

    /** 结构化写作 */
    public static final String WRITING                = "/rest/2.0/nlp/v1/gen_article";

    /** 人体状态识别 */
    public static final String BODIES                 = "/rest/2.0/image-classify/v1/body_attr";

    /** Ip 转 物理地址 */
    public static final String IP_TO_ADDRESS          = "/location/ip";

    /** 地址查询天气 */
    public static final String FIND_WEARHER           = "/weather/v1/";

    /** TODO Key 30天更换一次 暂定固定值 */
    public static final String BAIDU_KEY              =
        "24.879f8135d8bab56b057a73fe9c3d133b.2592000.1619419464.282335-19618961";

}
