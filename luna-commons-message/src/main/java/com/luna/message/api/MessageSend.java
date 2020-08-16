package com.luna.message.api;

import com.google.common.collect.ImmutableMap;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.base.BaseException;
import com.luna.common.utils.mask.MaskUtils;
import com.luna.common.utils.md5.HashUtils;
import com.luna.common.utils.md5.Md5Utils;
import com.luna.message.api.constant.EmailContentsConstant;
import com.luna.message.api.constant.MessageTypeConstant;
import com.luna.message.api.constant.TargetTypeConstant;
import com.luna.message.api.entity.MessageDO;
import com.luna.message.api.service.MessageEmailService;
import com.luna.message.api.service.MessageMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Luna@win10
 * @date 2020/5/16 4:50
 */
@Component
public class MessageSend {

    @Autowired
    private StringRedisTemplate  stringRedisTemplate;

    @Autowired
    private MessageMobileService messageMobileService;

    @Autowired
    private MessageEmailService  messageEmailService;

    /**
     * 重置密码
     *
     * @param userMark
     * @return
     */
    public void resetPassword(String userMark) {
        MessageDO messageDO = new MessageDO();
        String randomPassword = HashUtils.randomHex32();
        messageDO.setPlaceholderContent(ImmutableMap.of(MessageTypeConstant.RESET_PASSWORD, randomPassword));
        if (MaskUtils.isEmailAddress(userMark)) {
            messageDO.setTargetMap(ImmutableMap.of(TargetTypeConstant.EMAIL, userMark));
            messageDO.setMessageType(MessageTypeConstant.RESET_PASSWORD);
            messageDO.setTargetType(TargetTypeConstant.EMAIL);
            messageDO.setTemplateId(EmailContentsConstant.RESET_PASSWORD);
            messageEmailService.asyncSendMessage(messageDO, userMark, "");
        } else if (MaskUtils.isMobilePhoneNumber(userMark)) {
            messageDO.setTargetMap(ImmutableMap.of(TargetTypeConstant.MOBILE, userMark));
            messageDO.setMessageType(MessageTypeConstant.RESET_PASSWORD);
            messageDO.setTargetType(TargetTypeConstant.MOBILE);
            messageDO.setTemplateId(EmailContentsConstant.RESET_PASSWORD);
            messageMobileService.asyncSendMessage(messageDO, "", userMark);
        } else {
            throw new BaseException(ResultCode.PARAMETER_INVALID, "不是一个合法的手机号或者邮箱地址");
        }

    }

    /**
     * 发送验证吗
     *
     * @param userMark
     * @return
     */
    public void authCode(String userMark) {
        MessageDO messageDO = new MessageDO();
        String validationCode = Md5Utils.getValidationCode();
        messageDO.setPlaceholderContent(ImmutableMap.of(MessageTypeConstant.AUTH_OCDE, validationCode));
        if (MaskUtils.isEmailAddress(userMark)) {
            messageDO.setTargetMap(ImmutableMap.of(TargetTypeConstant.EMAIL, userMark));
            messageDO.setMessageType(MessageTypeConstant.AUTH_OCDE);
            messageDO.setTargetType(TargetTypeConstant.EMAIL);
            messageDO.setTemplateId(EmailContentsConstant.AUTH_CODE);
            stringRedisTemplate.delete(userMark);
            stringRedisTemplate.opsForValue().append(userMark, validationCode);
            stringRedisTemplate.expire(userMark, 300, TimeUnit.SECONDS);
            messageEmailService.asyncSendMessage(messageDO, userMark, null);
        } else if (MaskUtils.isMobilePhoneNumber(userMark)) {
            messageDO.setTargetMap(ImmutableMap.of(TargetTypeConstant.MOBILE, userMark));
            messageDO.setMessageType(MessageTypeConstant.AUTH_OCDE);
            messageDO.setTargetType(TargetTypeConstant.MOBILE);
            messageDO.setTemplateId(EmailContentsConstant.AUTH_CODE);
            stringRedisTemplate.delete(userMark);
            stringRedisTemplate.opsForValue().append(userMark, validationCode);
            stringRedisTemplate.expire(userMark, 300, TimeUnit.SECONDS);
            messageMobileService.asyncSendMessage(messageDO, null, userMark);
        } else {
            throw new BaseException(ResultCode.PARAMETER_INVALID, "不是一个合法的手机号或者邮箱地址");
        }

    }

}
