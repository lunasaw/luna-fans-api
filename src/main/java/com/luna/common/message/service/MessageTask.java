package com.luna.common.message.service;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.luna.common.message.constant.MessageTypeConstant;
import com.luna.common.message.constant.TargetTypeConstant;
import com.luna.common.message.dao.TemplateDAO;
import com.luna.common.message.entity.MessageDO;
import com.luna.common.message.entity.TemplateDO;
import com.luna.common.message.wrapper.MailWrapper;
import com.luna.common.message.wrapper.SmsWrapper;

/**
 * @author Tony
 */
public class MessageTask implements Runnable {

    private final static Logger logger = LoggerFactory.getLogger(MessageTask.class);

    private MessageDO           messageDO;
    private TemplateDAO         templateDAO;
    private MailWrapper         mailWrapper;
    private SmsWrapper          smsWrapper;
    private String              toMail;
    private String              toSms;

    public MessageTask(MessageDO messageDO, TemplateDAO templateDAO,
        MailWrapper mailWrapper, SmsWrapper smsWrapper, String toMail, String toSms) {
        this.messageDO = messageDO;
        this.templateDAO = templateDAO;
        this.mailWrapper = mailWrapper;
        this.smsWrapper = smsWrapper;
        this.toMail = toMail;
        this.toSms = toSms;
    }

    @Override
    public void run() {
        // 确定消息标题和内容
        TemplateDO templateDO = templateDAO.get(messageDO.getTemplateId());
        if (templateDO == null) {
            logger.error("templateDO is null, messageDO={}", JSON.toJSONString(messageDO));
            return;
        }
        String content = templateDO.getContent();
        if (MapUtils.isNotEmpty(messageDO.getPlaceholderContent())) {
            content = new StringSubstitutor(messageDO.getPlaceholderContent()).replace(content);
        }
        String subject = templateDO.getSubject();
        if (MapUtils.isNotEmpty(messageDO.getPlaceholderContent())) {
            subject = new StringSubstitutor(messageDO.getPlaceholderContent()).replace(subject);
        }

        if (StringUtils.equals(MessageTypeConstant.AUTH_OCDE, messageDO.getMessageType())) {
            // 发送
            if (StringUtils.equals(TargetTypeConstant.EMAIL, messageDO.getTargetType())) {
                mailWrapper.sendEmail(toMail, subject, content,
                    messageDO.getPlaceholderContent().get(MessageTypeConstant.AUTH_OCDE));
            } else if (StringUtils.equals(TargetTypeConstant.MOBILE, messageDO.getTargetType())) {
                // TODO 暂不打开，发送要钱
                // smsWrapper.sendAuthCode(toSms, messageDO.getPlaceholderContent().get(MessageTypeConstant.AUTH_OCDE));
            }
        } else if (StringUtils.equals(MessageTypeConstant.RESET_PASSWORD, messageDO.getMessageType())) {
            // 发送
            if (StringUtils.equals(TargetTypeConstant.EMAIL, messageDO.getTargetType())) {
                mailWrapper.sendEmail(toMail, subject, content, null);
            } else if (StringUtils.equals(TargetTypeConstant.MOBILE, messageDO.getTargetType())) {
                // TODO 暂不打开，发送要钱
                // smsWrapper.sendAuthCode(toSms,
                // messageDO.getPlaceholderContent().get(MessageTypeConstant.RESET_PASSWORD));
            }
        }

    }
}
