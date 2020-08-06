package com.luna.message.api.wrapper;

import com.google.common.collect.Lists;
import com.luna.message.config.TencentConfigValue;
import com.luna.message.config.TencentSmsConfigValue;
import com.luna.tencent.api.TencentMessage;
import com.luna.tencent.dto.SendStatusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author Luna@win10
 * @date 2020/5/16 10:12
 */
@Component
public class SmsWrapper {

    @Autowired
    StringRedisTemplate         stringRedisTemplate;

    @Autowired
    TencentSmsConfigValue       tencentSmsConfigValue;

    @Autowired
    TencentConfigValue          tencentConfigValue;

    private static final Logger log = LoggerFactory.getLogger(SmsWrapper.class);

    /**
     * +86 国内单个发送验证码短信
     * 
     * @param phone
     */
    public boolean sendAuthCode(String phone, String code) throws Exception {
        log.info("sendAuthCode start phone={}, code={}", phone, code);
        if (!phone.startsWith("+86")) {
            phone = "+86" + phone;
        }
        ArrayList<String> phones = Lists.newArrayList();
        phones.add(phone);
        ArrayList<String> codes = Lists.newArrayList();
        codes.add(code);
        stringRedisTemplate.delete(phone);
        stringRedisTemplate.opsForValue().append(phone, code);
        stringRedisTemplate.expire(phone, 3000, TimeUnit.SECONDS);
        ArrayList<SendStatusDTO> sendStatusDTOS =
            TencentMessage.sendMsg(tencentConfigValue.getSecretid(), tencentConfigValue.getSecretKey(), phones,
                tencentSmsConfigValue.getAuthCode(), codes, tencentSmsConfigValue.getAppId(),
                tencentSmsConfigValue.getSign());
        log.info("sendAuthCode start phone={}, code={}, sendStatusDTOS={}", phone, code, sendStatusDTOS);
        return sendStatusDTOS.get(0).getCode() == "Ok";
    }

    /**
     * 重置密码
     * 
     * @param phone
     * @return
     */
    public boolean resetPassword(String phone, String password) throws Exception {
        log.info("resetPassword start phone={}, password={}", phone, password);
        if (!phone.startsWith("+86")) {
            phone = "+86" + phone;
        }
        ArrayList<String> phones = Lists.newArrayList();
        phones.add(phone);
        ArrayList<String> passwords = Lists.newArrayList();
        passwords.add(password);
        ArrayList<SendStatusDTO> sendStatusDTOS =
            TencentMessage.sendMsg(tencentConfigValue.getSecretid(), tencentConfigValue.getSecretKey(), phones,
                tencentSmsConfigValue.getResetPassword(), passwords,
                tencentSmsConfigValue.getAppId(), tencentSmsConfigValue.getSign());
        log.info("resetPassword start phone={}, password={}, sendStatusDTOS={}", phone, password, sendStatusDTOS);
        return sendStatusDTOS.get(0).getCode() == "Ok";
    }
}
