package com.luna.common.dto.constant;

import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 返回参数
 * 
 * @author 15272
 *
 */
public interface ResultCode {


    /** 成功 */
    int                              SUCCESS                    = 1;
    String                           MSG_SUCCESS                = "success";

    // 一些可能共性的异常code 9000~9999
    /** 接口已下线 */
    int                              INTERFACE_OFFLINE          = 9000;
    String                           MSG_INTERFACE_OFFLINE      = "interface is offline";

    /** 参数非法 */
    int                              PARAMETER_INVALID          = 9002;
    String                           MSG_PARAMETER_INVALID      = "parameter invalid";

    /** 依赖错误 */
    int                              DEPENDENCY_ERROR           = 9003;
    String                           MSG_DEPENDENCY_ERROR       = "dependency error";

    /** 系统错误 */
    int                              ERROR_SYSTEM_EXCEPTION     = 9999;
    String                           MSG_ERROR_SYSTEM_EXCEPTION = "system error";

    Map<Locale, Map<String, String>> TRANSLATION_MAP            =
            ImmutableMap.<Locale, Map<String, String>>builder()
                    .put(Locale.SIMPLIFIED_CHINESE,
                            ImmutableMap.<String, String>builder()
                                    .put(MSG_INTERFACE_OFFLINE, "接口已下线")
                                    .put(MSG_PARAMETER_INVALID, "参数非法")
                                    .put(MSG_DEPENDENCY_ERROR, "依赖错误")
                                    .put(MSG_ERROR_SYSTEM_EXCEPTION, "系统异常")
                                    .build())
                    .build();

    public static String translateMessage(String message, List<Map<Locale, Map<String, String>>> translationMapList) {
        for (Map<Locale, Map<String, String>> translationMap : translationMapList) {
            if (translationMap.get(Locale.CHINA).containsKey(message)) {
                return translationMap.get(Locale.CHINA).get(message);
            }
        }
        return message;
    }
}
