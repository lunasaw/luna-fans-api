package com.luna.message.api.wrapper;

import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.JSON;
import com.luna.common.utils.StringUtils;
import com.luna.common.utils.text.CharsetKit;
import com.luna.message.api.constant.MessageTypeConstant;
import com.luna.message.emailmodel.EmailModelBuild;
import com.luna.message.dto.EmailDTO;
import com.luna.message.dto.EmailSmallDTO;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Luna@win10
 * @date 2020/5/16 14:07
 */
@Component
public class MailWrapper {
    private final static Logger log = LoggerFactory.getLogger(MailWrapper.class);

    @Autowired
    JavaMailSenderImpl          javaMailSender;

    @Value("${spring.mail.username}")
    private String              username;

    @Value("${spring.mail.name}")
    private String              name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 简单发送文本邮件
     * 
     * @param emailSmallDTO
     * @throws UnsupportedEncodingException
     * @throws AddressException
     */
    public void sendSimpleEmail(EmailSmallDTO emailSmallDTO) {
        log.info("sendSimpleEmail start emailSmallDTO={}", JSON.toJSONString(emailSmallDTO));
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 标题
        simpleMailMessage.setSubject(
            emailSmallDTO.getSubject() + DateFormatUtils.format(new Date(), DatePattern.NORM_DATETIME_MINUTE_PATTERN));
        // 内容
        if (StringUtils.isNotEmpty(emailSmallDTO.getContent())) {
            simpleMailMessage.setText(emailSmallDTO.getContent());
        }
        // 发送人地址
        simpleMailMessage.setTo(emailSmallDTO.getTo());
        try {
            // 默认使用配置发送人昵称
            if (StringUtils.isNotEmpty(emailSmallDTO.getUserName())) {
                // 设置自定义发件人昵称
                String nick = MimeUtility.encodeText(emailSmallDTO.getUserName());
                simpleMailMessage.setFrom(new InternetAddress(nick + " <" + username + ">").toString());
            } else {
                simpleMailMessage.setFrom(new InternetAddress(name + " <" + username + ">").toString());
            }
        } catch (Exception e) {
            log.error("javaMailSender.send error, simpleMailMessage={}", JSON.toJSONString(simpleMailMessage));
            e.printStackTrace();
        }
        javaMailSender.send(simpleMailMessage);
        log.info("javaMailSender.send success, simpleMailMessage={}", JSON.toJSONString(simpleMailMessage));
    }

    /**
     * 发送复杂邮件
     * 
     * @param emailSmallDTO
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public void sendEmail(EmailSmallDTO emailSmallDTO) throws MessagingException, UnsupportedEncodingException {
        log.info("sendEmail start emailSmallDTO={}", JSON.toJSONString(emailSmallDTO));
        // 创建复杂邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, CharsetKit.UTF_8);
        // 发送人地址
        helper.setTo(emailSmallDTO.getTo());
        // 标题
        helper.setSubject(
            emailSmallDTO.getSubject() + DateFormatUtils.format(new Date(), DatePattern.NORM_DATETIME_MINUTE_PATTERN));

        // 内容
        if (StringUtils.isNotEmpty(emailSmallDTO.getContent())) {
            helper.setText("<b style='color:blue'>" + emailSmallDTO.getContent() + "</b>", true);
        }

        // 普通抄送,收件人互可见
        if (emailSmallDTO.getCc() != null && emailSmallDTO.getCc().length != 0) {
            helper.setCc(emailSmallDTO.getCc());
        }

        // 加密抄送,收件人不可见
        if (emailSmallDTO.getBcc() != null && emailSmallDTO.getBcc().length != 0) {
            helper.setBcc(emailSmallDTO.getBcc());
        }

        // 默认使用配置发送人昵称
        if (StringUtils.isNotEmpty(emailSmallDTO.getUserName())) {
            // 设置自定义发件人昵称
            String nick = MimeUtility.encodeText(emailSmallDTO.getUserName());
            helper.setFrom(new InternetAddress(nick + " <" + username + ">"));
        } else {
            helper.setFrom(new InternetAddress(name + " <" + username + ">"));
        }

        if (MapUtils.isNotEmpty(emailSmallDTO.getPathMap())) {
            // 上传文件
            Map<String, String> pathMap = emailSmallDTO.getPathMap();
            // Iterating entries using a For Each loop
            Iterator<Map.Entry<String, String>> entries = pathMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                helper.addAttachment(entry.getKey(), new File(entry.getValue()));
            }
        }

        javaMailSender.send(mimeMessage);
        log.info("javaMailSender.send success, simpleMailMessage={}", JSON.toJSONString(mimeMessage));
    }

    /**
     * HTML 模板发送
     * 
     * @param emailDTO
     * @throws IOException
     * @throws MessagingException
     */
    public void sendEmail(EmailDTO emailDTO) {
        log.info("sendEmail start emailDTO={}", JSON.toJSONString(emailDTO));
        EmailSmallDTO smallDTO = emailDTO.getEmailSmallDTO();
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(smallDTO.getTo());
            // 普通抄送,收件人互可见
            if (smallDTO.getCc() != null && smallDTO.getCc().length != 0) {
                helper.setCc(smallDTO.getCc());
            }

            // 加密抄送,收件人不可见
            if (smallDTO.getBcc() != null && smallDTO.getBcc().length != 0) {
                helper.setBcc(smallDTO.getBcc());
            }

            // 默认使用配置发送人昵称
            if (StringUtils.isNotEmpty(smallDTO.getUserName())) {
                // 设置自定义发件人昵称
                String nick = MimeUtility.encodeText(smallDTO.getUserName());
                helper.setFrom(new InternetAddress(nick + " <" + username + ">"));
            } else {
                helper.setFrom(new InternetAddress(name + " <" + username + ">"));
            }

            if (MapUtils.isNotEmpty(smallDTO.getPathMap())) {
                // 上传文件
                Map<String, String> pathMap = smallDTO.getPathMap();
                // Iterating entries using a For Each loop
                Iterator<Map.Entry<String, String>> entries = pathMap.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<String, String> entry = entries.next();
                    helper.addAttachment(entry.getKey(), new File(entry.getValue()));
                }
            }

            // 使用默认模板
            if (StringUtils.isEmpty(emailDTO.getModelName())) {
                emailDTO.setModelName(MessageTypeConstant.EMAIL_MODEL);
            }

            if (StringUtils.isNotEmpty(smallDTO.getSubject())) {
                helper.setSubject(smallDTO.getSubject()
                    + DateFormatUtils.format(new Date(), DatePattern.NORM_DATETIME_MINUTE_PATTERN));
            }

            if (emailDTO.getModelContentDTO() != null) {
                helper.setText(EmailModelBuild.buildContentOne(emailDTO.getModelName(), smallDTO.getContent(),
                    emailDTO.getModelContentDTO()), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
        log.info("sendEmail success emailDTO={}", JSON.toJSONString(emailDTO));
    }

}
