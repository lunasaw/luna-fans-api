package com.luna.commons.message.constant;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableSet;

/**
 * @author Tony
 */
public class TargetTypeConstant {
    /** 手机 */
    public static final String       MOBILE    = "mobile";
    /** 邮箱 */
    public static final String       EMAIL     = "email";

    private static final Set<String> ALL_TYPES = ImmutableSet.of(MOBILE, EMAIL);

    public static boolean isLegal(String site) {
        if (StringUtils.isBlank(site)) {
            return false;
        }

        return ALL_TYPES.contains(site);
    }
}
