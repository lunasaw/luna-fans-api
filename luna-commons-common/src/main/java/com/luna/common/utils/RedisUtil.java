package com.luna.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.base.BaseException;

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
        } else {
            throw new BaseException(ResultCode.PARAMETER_INVALID, "不是一个合法的手机号或者邮箱地址");
        }
        return s;
    }
}
