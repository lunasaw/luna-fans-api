package com.luna.api.email.service;

import com.luna.api.email.constant.MessageTypeConstant;
import com.luna.api.email.dto.EmailSmallDTO;
import com.luna.api.email.dto.MessageDTO;
import com.luna.api.email.entity.TemplateDO;
import com.luna.api.email.warpper.MailWrapper;
import com.luna.common.text.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luna
 */
@Slf4j
public class MessageTask implements Runnable {

    /** 模板 */
    private TemplateDO    templateDO;

    /** 发送人包装 */
    private EmailSmallDTO emailSmallDTO;

    /** 邮件wrapper */
    private MailWrapper   mailWrapper;
    public MessageTask(EmailSmallDTO emailSmallDTO, TemplateDO templateDO, MailWrapper mailWrapper) {
        this.templateDO = templateDO;
        this.emailSmallDTO = emailSmallDTO;
        this.mailWrapper = mailWrapper;
    }

    @Override
    public void run() {
        // 填充内容
        String content = templateDO.getContent();
        if (MapUtils.isNotEmpty(emailSmallDTO.getPlaceholderContent())) {
            content = StringTools.format(content, emailSmallDTO.getPlaceholderContent());
        }
        // 填充标题
        String subject = templateDO.getSubject();
        if (MapUtils.isNotEmpty(emailSmallDTO.getPlaceholderContent())) {
            subject = StringTools.format(subject, emailSmallDTO.getPlaceholderContent());
        }

        if (StringUtils.equals(MessageTypeConstant.MOBILE, emailSmallDTO.getMessageType())) {
            for (String mobile : emailSmallDTO.getTargetList()) {
                // TODO 暂不打开
            }
        } else if (StringUtils.equals(MessageTypeConstant.EMAIL_SIMPLE, emailSmallDTO.getMessageType())) {
            for (String email : emailSmallDTO.getTargetList()) {
                mailWrapper.sendSimpleMessage(emailSmallDTO.getFromMail(), email, subject, content);
            }
        } else if (StringUtils.equals(MessageTypeConstant.COMPLEX_EMAIL, emailSmallDTO.getMessageType())) {
            mailWrapper.send(emailSmallDTO);
        }
    }
}