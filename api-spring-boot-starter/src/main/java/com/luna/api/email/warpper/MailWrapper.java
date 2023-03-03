package com.luna.api.email.warpper;

import com.alibaba.fastjson.JSON;
import com.luna.api.email.dto.EmailSmallDTO;
import com.luna.common.text.CharsetUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description 邮件wrapper
 * @author Luna
 * @date 2019年10月4日 下午9:22:02
 */
@Component
public class MailWrapper {
    private final static Logger logger = LoggerFactory.getLogger(MailWrapper.class);

    @Autowired
    private JavaMailSender      javaMailSender;

    public void sendSimpleMessage(EmailSmallDTO emailSmallDTO) {
        SimpleMailMessage simpleMailMessage = convert(emailSmallDTO);
        try {
            logger.info("sendMessage::emailSmallDTO = {}", JSON.toJSONString(emailSmallDTO));
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            logger.error("sendMessage::emailSmallDTO = {} ", JSON.toJSONString(emailSmallDTO), e);
        }
    }

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

    /**
     * 发送复杂邮件
     * 
     * @param emailSmallDTO
     */
    public void sendMultiMessage(EmailSmallDTO emailSmallDTO) {
        Objects.requireNonNull(emailSmallDTO, "send email dto not allow null");
        Objects.requireNonNull(emailSmallDTO.getFromUser(), "send email from user not allow null");

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(personal2InternetAddress(emailSmallDTO.getFromUser()));
            helper.setSubject(emailSmallDTO.getSubject());
            helper.setText(emailSmallDTO.getContent().getTxt(), emailSmallDTO.getContent().getHtml());
            if (emailSmallDTO.getReplyTo() != null) {
                helper.setReplyTo(personal2InternetAddress(emailSmallDTO.getReplyTo()));
            }
            // 普通抄送,收件人互可见
            if (ObjectUtils.isNotEmpty(emailSmallDTO.getCc())) {
                helper.setCc(convertInternetAddress(emailSmallDTO.getCc()).toArray(new InternetAddress[0]));
            }
            // 加密抄送,收件人不可见
            if (ObjectUtils.isNotEmpty(emailSmallDTO.getBcc())) {
                helper.setBcc(convertInternetAddress(emailSmallDTO.getBcc()).toArray(new InternetAddress[0]));
            }

            // 默认使用配置发送人昵称
            helper.setFrom(personal2InternetAddress(emailSmallDTO.getFromUser()));

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
            javaMailSender.send(message);
            logger.info("javaMailSender.send success, emailSmallDTO={}", JSON.toJSONString(emailSmallDTO));
        } catch (Exception e) {
            logger.error("javaMailSender.send error, emailSmallDTO={}，e={}", JSON.toJSONString(emailSmallDTO), e);
        }
    }

    private SimpleMailMessage convert(EmailSmallDTO emailSmallDTO) {
        Objects.requireNonNull(emailSmallDTO, "send email dto not allow null");
        Objects.requireNonNull(emailSmallDTO.getFromUser(), "send email from user not allow null");
        Objects.requireNonNull(emailSmallDTO.getContent(), "send email content not allow null");
        Objects.requireNonNull(emailSmallDTO.getSubject(), "send email subject not allow null");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailSmallDTO.getFromUser().getAddress());
        if (emailSmallDTO.getReplyTo() != null) {
            simpleMailMessage.setReplyTo(emailSmallDTO.getReplyTo().getAddress());
        }
        simpleMailMessage.setTo(emailSmallDTO.getTargetList().toArray(new String[0]));
        simpleMailMessage.setCc(emailSmallDTO.getCc().stream().map(EmailSmallDTO.Personal::getAddress).toArray(String[]::new));
        simpleMailMessage.setBcc(emailSmallDTO.getBcc().stream().map(EmailSmallDTO.Personal::getAddress).toArray(String[]::new));
        simpleMailMessage.setSentDate(emailSmallDTO.getSentDate());
        simpleMailMessage.setSubject(emailSmallDTO.getSubject());
        simpleMailMessage.setText(emailSmallDTO.getContent().getTxt());
        return simpleMailMessage;
    }

    public static List<InternetAddress> convertInternetAddress(List<EmailSmallDTO.Personal> personals) {
        return personals.stream().map(MailWrapper::personal2InternetAddress).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static InternetAddress personal2InternetAddress(EmailSmallDTO.Personal personal) {
        if (personal == null) {
            return null;
        }
        InternetAddress internetAddress = new InternetAddress();
        internetAddress.setAddress(personal.getAddress());
        try {
            if (StringUtils.isNotBlank(personal.getNickName())) {
                internetAddress.setPersonal(personal.getNickName(), CharsetUtil.defaultCharsetName());
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("convertInternetAddress::personal = {}", personal);
        }
        return internetAddress;
    }

}
