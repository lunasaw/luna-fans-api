package com.luna.message.api.wrapper;

import com.google.common.collect.Lists;
import com.luna.message.config.TencentConfigValue;
import com.luna.message.config.TencentSmsConfigValue;
import com.luna.message.dto.SmsDTO;
import com.luna.message.sms.TencentSmsMessage;
import com.luna.tencent.dto.SendStatusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author Luna@win10
 * @date 2020/5/16 10:12
 */
@Component
public class SmsWrapper {
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
    public boolean sendAuthCode(String phone, String code) {
        log.info("sendAuthCode start phone={}, code={}", phone, code);
        if (!phone.startsWith("+86")) {
            phone = "+86" + phone;
        }

        ArrayList<String> phones = Lists.newArrayList();
        phones.add(phone);
        ArrayList<String> codes = Lists.newArrayList();
        codes.add(code);

        SmsDTO smsDTO = new SmsDTO(phones, tencentSmsConfigValue.getAppId(), tencentSmsConfigValue.getSign(),
            tencentSmsConfigValue.getAuthCode(), codes);
        ArrayList<SendStatusDTO> sendStatusDTOS =
            TencentSmsMessage.sendMsg(tencentConfigValue.getSecretid(), tencentConfigValue.getSecretKey(), smsDTO);
        log.info("sendAuthCode ends msDTO={}, sendStatusDTOS={}", smsDTO, sendStatusDTOS);
        return "Ok".equals(sendStatusDTOS.get(0).getCode());
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

        SmsDTO smsDTO = new SmsDTO(phones, tencentSmsConfigValue.getAppId(), tencentSmsConfigValue.getSign(),
            tencentSmsConfigValue.getResetPassword(), passwords);
        ArrayList<SendStatusDTO> sendStatusDTOS =
            TencentSmsMessage.sendMsg(tencentConfigValue.getSecretid(), tencentConfigValue.getSecretKey(), smsDTO);
        log.info("resetPassword end smsDTO={}, sendStatusDTOS={}", smsDTO, sendStatusDTOS);
        return "Ok".equals(sendStatusDTOS.get(0).getCode());
    }
}
