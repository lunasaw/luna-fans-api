package com.luna.api.email.service;

import com.google.common.collect.Maps;
import com.luna.api.email.constant.MessageTypeConstant;
import com.luna.api.email.dto.EmailSmallDTO;
import com.luna.api.email.dto.TemplateDTO;
import com.luna.api.email.warpper.MailWrapper;
import com.luna.common.text.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Luna
 */
@Slf4j
public class MessageTask implements Runnable {

    /** 模板 */
    private TemplateDTO   templateDTO;

    /** 发送人包装 */
    private EmailSmallDTO emailSmallDTO;

    /** 邮件wrapper */
    private MailWrapper   mailWrapper;

    public MessageTask(EmailSmallDTO emailSmallDTO, TemplateDTO templateDTO, MailWrapper mailWrapper) {
        this.templateDTO = templateDTO;
        this.emailSmallDTO = emailSmallDTO;
        this.mailWrapper = mailWrapper;
    }

    @Override
    public void run() {
        // 填充内容
        String content = templateDTO.getContent();
        if (MapUtils.isNotEmpty(emailSmallDTO.getPlaceholderContent())) {
            content = replaceHolder(content, emailSmallDTO.getPlaceholderContent());
            emailSmallDTO.setContent(new EmailSmallDTO.Content(content));
        }
        // 填充标题
        String subject = templateDTO.getSubject();
        if (MapUtils.isNotEmpty(emailSmallDTO.getPlaceholderContent())) {
            subject = replaceHolder(subject, emailSmallDTO.getPlaceholderContent());
            emailSmallDTO.setSubject(subject);
        }

        if (StringUtils.equals(MessageTypeConstant.MOBILE, emailSmallDTO.getMessageType())) {
            for (String mobile : emailSmallDTO.getTargetList()) {
                // TODO 暂不打开
            }
        } else if (StringUtils.equals(MessageTypeConstant.EMAIL_SIMPLE, emailSmallDTO.getMessageType())) {
            mailWrapper.sendSimpleMessage(emailSmallDTO);
        } else if (StringUtils.equals(MessageTypeConstant.COMPLEX_EMAIL, emailSmallDTO.getMessageType())) {
            mailWrapper.sendMultiMessage(emailSmallDTO);
        }
    }

    public String replaceHolder(String content, Map<String, String> placeholderContent) {
        return StringTools.format(content, "$", placeholderContent, true);
    }

}
