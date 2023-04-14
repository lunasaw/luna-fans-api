package com.luna.api.email.constant;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Description 消息类型
 * @author Luna
 * @date 2019年10月4日 下午7:31:40
 */
public class MessageTypeConstant {
    /** 手机 */
    public static final String        MOBILE        = "mobile";
    /** 邮箱 */
    public static final String        EMAIL         = "email";

    /** 简单文本邮箱 */
    public static final String        EMAIL_SIMPLE  = "email-simple";
    /** 复杂HTML邮箱 */
    public static final String        COMPLEX_EMAIL = "complex-email";

    private static final List<String> ALL_TYPES     = ImmutableList.of(MOBILE, EMAIL_SIMPLE, EMAIL, COMPLEX_EMAIL);

    public static boolean isLegal(String userNoticeType) {
        if (StringUtils.isBlank(userNoticeType)) {
            return false;
        }

        return ALL_TYPES.contains(userNoticeType);
    }
}
