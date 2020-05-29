package com.luna.commons.message.constant;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableList;

/**
 * @Description TODO
 * @author Tony
 * @date 2019年10月4日 下午7:31:40
 */
public class MessageTypeConstant {

    /** 发送模板 */
    public static final String        EMAIL_MODEL    = "luna-message.html";
    /** 重置密码 */
    public static final String        RESET_PASSWORD = "newPassword";
    /** 验证码 */
    public static final String        AUTH_OCDE      = "authCode";

    private static final List<String> ALL_TYPES      = ImmutableList.of(RESET_PASSWORD, AUTH_OCDE);

    public static boolean isLegal(String userNoticeType) {
        if (StringUtils.isBlank(userNoticeType)) {
            return false;
        }

        return ALL_TYPES.contains(userNoticeType);
    }
}
