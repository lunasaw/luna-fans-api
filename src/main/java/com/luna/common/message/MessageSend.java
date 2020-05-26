package com.luna.common.message;

import com.luna.common.exception.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.message.entity.MessageDO;
import com.luna.common.message.service.MessageService;
import com.luna.common.utils.CommonUtils;
import com.luna.common.utils.HashUtils;
import com.luna.common.utils.Md5Utils;

/**
 * @author Luna@win10
 * @date 2020/5/16 4:50
 */
@Component
public class MessageSend {

    @Autowired
    MessageService messageService;

    /**
     * 重置密码
     *
     * @param userMark
     * @return
     */
    public void resetPassword(String userMark) {
        MessageDO messageDO = new MessageDO();
        String randomPassword = HashUtils.randomHex32();
        messageDO.setPlaceholderContent(ImmutableMap.of("newPassword", randomPassword));
        if (CommonUtils.isEmailAddress(userMark)) {
            messageDO.setTargetMap(ImmutableMap.of("email", userMark));
            messageDO.setMessageType("newPassword");
            messageDO.setTargetType("email");
            messageDO.setTemplateId(13);
            messageService.asyncSendMessage(messageDO, userMark, "");
        } else if (CommonUtils.isMobilePhoneNumber(userMark)) {
            messageDO.setTargetMap(ImmutableMap.of("mobile", userMark));
            messageDO.setMessageType("newPassword");
            messageDO.setTargetType("mobile");
            messageDO.setTemplateId(13);
            messageService.asyncSendMessage(messageDO, "", userMark);
        } else {
            throw new MessageException(ResultCode.PARAMETER_INVALID, "不是一个合法的手机号或者邮箱地址");
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
        messageDO.setPlaceholderContent(ImmutableMap.of("authCode", validationCode));
        if (CommonUtils.isEmailAddress(userMark)) {
            messageDO.setTargetMap(ImmutableMap.of("email", userMark));
            messageDO.setMessageType("authCode");
            messageDO.setTargetType("email");
            messageDO.setTemplateId(14);
            messageService.asyncSendMessage(messageDO, userMark, "");
        } else if (CommonUtils.isMobilePhoneNumber(userMark)) {
            messageDO.setTargetMap(ImmutableMap.of("mobile", userMark));
            messageDO.setMessageType("authCode");
            messageDO.setTargetType("mobile");
            messageDO.setTemplateId(14);
            messageService.asyncSendMessage(messageDO, "", userMark);
        } else {
            throw new MessageException(ResultCode.PARAMETER_INVALID, "不是一个合法的手机号或者邮箱地址");
        }

    }

}
