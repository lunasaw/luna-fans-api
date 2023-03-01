package com.luna.api.email.warpper;

import com.alibaba.fastjson.JSON;
import com.luna.api.email.dto.EmailSmallDTO;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.Optional;

/**
 * @Description 邮件wrapper
 * @author Luna
 * @date 2019年10月4日 下午9:22:02
 */
@Component
public class MailWrapper {
    private final static Logger logger         = LoggerFactory.getLogger(MailWrapper.class);

    @Autowired
    private JavaMailSender      javaMailSender;

    /**
     * 发送文本邮件
     *
     * @param toEmail
     * @param subject
     * @param content
     */
    public void sendSimpleMessage(String fromMail, String toEmail, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        logger.info("simpleMailMessage={}", JSON.toJSONString(simpleMailMessage));
        javaMailSender.send(simpleMailMessage);
        logger.info("javaMailSender.send success, simpleMailMessage={}", JSON.toJSONString(simpleMailMessage));
    }

    /**
     * HTML 模板发送
     *
     * @param toEmail
     * @param subject
     * @param content
     */
    public void send(String fromMail, String fromMallNick, String toEmail, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.setFrom(new InternetAddress(fromMallNick + " <" + fromMail + ">"));
            javaMailSender.send(message);
            logger.info("javaMailSender.send success, message={}", message);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void send(EmailSmallDTO emailSmallDTO) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo((String []) emailSmallDTO.getTargetList().toArray());
            helper.setSubject(emailSmallDTO.getSubject());
            helper.setText(emailSmallDTO.getContent(), true);
            // 普通抄送,收件人互可见
            if (ObjectUtils.isNotEmpty(emailSmallDTO.getCc())) {
                helper.setCc(emailSmallDTO.getCc());
            }
            // 加密抄送,收件人不可见
            if (ObjectUtils.isNotEmpty(emailSmallDTO.getBcc())) {
                helper.setBcc(emailSmallDTO.getBcc());
            }

            // 默认使用配置发送人昵称
            String nickName = emailSmallDTO.getNickName();
            // 设置自定义发件人昵称
            String nick = Optional.ofNullable(nickName).orElse(StringUtils.EMPTY);
            nickName = MimeUtility.encodeText(nick);
            helper.setFrom(new InternetAddress(nickName + " <" + emailSmallDTO.getFromMail() + ">"));

            if (MapUtils.isNotEmpty(emailSmallDTO.getPathMap())) {
                // 上传文件
                emailSmallDTO.getPathMap().forEach((key, value) -> {
                    try {
                        helper.addAttachment(key, new File(value));
                    } catch (MessagingException e) {
                        logger.warn("send email add file error k={}, v={}, e={}", key, value, e);
                    }
                });
            }

            String content = emailSmallDTO.getContent();
            if (StringUtils.isNotEmpty(content)) {
                helper.setText(content, true);
            }
            javaMailSender.send(message);
            logger.info("javaMailSender.send success, emailSmallDTO={}", JSON.toJSONString(emailSmallDTO));
        } catch (Exception e) {
            logger.error("javaMailSender.send error, emailSmallDTO={}，e={}", JSON.toJSONString(emailSmallDTO), e);
        }
    }
}
