package com.luna.common.message;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.luna.common.config.TencentConfigValue;
import com.luna.common.config.TencentSmsConfigValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.luna.common.tencent.TencentConstant;
import com.luna.common.tencent.TencentMessage;
import com.luna.common.utils.CommonUtils;
import com.luna.common.utils.Md5Utils;

/**
 * @author Luna@win10
 * @date 2020/5/16 10:12
 */
@Component
public class RedisUtil {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 获取Redis缓存验证码内容
     *
     * @param mark
     * @return
     */
    public String getAutchCode(String mark) {
        String s = "";
        if (CommonUtils.isMobilePhoneNumber(mark)) {
            mark = "+86" + mark;
            s = stringRedisTemplate.opsForValue().get(mark);
        } else if (CommonUtils.isEmailAddress(mark)) {
            s = stringRedisTemplate.opsForValue().get(mark);
        }
        return s;
    }
}
