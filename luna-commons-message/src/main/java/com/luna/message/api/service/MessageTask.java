package com.luna.message.api.service;

import com.alibaba.fastjson.JSON;
import com.luna.message.api.constant.MessageTypeConstant;
import com.luna.message.api.constant.TargetTypeConstant;
import com.luna.message.api.dao.TemplateDAO;
import com.luna.message.api.entity.MessageDO;
import com.luna.message.api.entity.TemplateDO;
import com.luna.message.api.wrapper.MailWrapper;
import com.luna.message.api.wrapper.SmsWrapper;
import com.luna.message.dto.EmailSmallDTO;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public MessageTask(MessageDO messageDO, TemplateDAO templateDAO, MailWrapper mailWrapper, String toMail) {
        this.messageDO = messageDO;
        this.templateDAO = templateDAO;
        this.mailWrapper = mailWrapper;
        this.toMail = toMail;
    }

    public MessageTask(MessageDO messageDO, TemplateDAO templateDAO, SmsWrapper smsWrapper, String toSms) {
        this.messageDO = messageDO;
        this.templateDAO = templateDAO;
        this.smsWrapper = smsWrapper;
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

        // 内容占位符替换
        String content = templateDO.getContent();
        if (MapUtils.isNotEmpty(messageDO.getPlaceholderContent())) {
            content = new StringSubstitutor(messageDO.getPlaceholderContent()).replace(content);
        }

        // 标题占位符替换
        String subject = templateDO.getSubject();
        if (MapUtils.isNotEmpty(messageDO.getPlaceholderContent())) {
            subject = new StringSubstitutor(messageDO.getPlaceholderContent()).replace(subject);
        }

        if (StringUtils.equals(MessageTypeConstant.AUTH_OCDE, messageDO.getMessageType())) {
            // 发送验证码
            if (StringUtils.equals(TargetTypeConstant.EMAIL, messageDO.getTargetType())) {
                // 邮件发送
                mailWrapper.sendSimpleEmail(new EmailSmallDTO(toMail, subject, content));
            } else if (StringUtils.equals(TargetTypeConstant.MOBILE, messageDO.getTargetType())) {
                // TODO 短信发送暂不打开，发送要钱
                // smsWrapper.sendAuthCode(toSms, messageDO.getPlaceholderContent().get(MessageTypeConstant.AUTH_OCDE));

            }
        } else if (StringUtils.equals(MessageTypeConstant.RESET_PASSWORD, messageDO.getMessageType())) {
            // 发送重置密码
            if (StringUtils.equals(TargetTypeConstant.EMAIL, messageDO.getTargetType())) {
                mailWrapper.sendSimpleEmail(new EmailSmallDTO(toMail, subject, content));
            } else if (StringUtils.equals(TargetTypeConstant.MOBILE, messageDO.getTargetType())) {
                // TODO 暂不打开，发送要钱
                // smsWrapper.sendAuthCode(toSms,
                // messageDO.getPlaceholderContent().get(MessageTypeConstant.RESET_PASSWORD));
            }
        }

    }
}
