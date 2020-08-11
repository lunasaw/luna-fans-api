package com.luna.message.mail;

import cn.hutool.extra.mail.MailUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.luna.common.utils.StringUtils;
import com.luna.message.config.JavaMailConfigValue;
import com.luna.message.dto.EmailSmallDTO;
import com.luna.message.util.MailUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/**
 * @Package: com.luna.message.mail
 * @ClassName: JavaMail
 * @Author: luna
 * @CreateTime: 2020/8/11 12:36
 * @Description:
 */
public class JavaMail {

    private static final Logger log = LoggerFactory.getLogger(JavaMail.class);

    /**
     * 发送邮件
     * 
     * @param emailSmallDTO
     * @param javaMailConfigValue
     * @throws Exception
     */
    public static void sendSimpleMail(EmailSmallDTO emailSmallDTO,
        JavaMailConfigValue javaMailConfigValue) throws Exception {
        log.info("sendSimpleMail start emailSmallDTO={},javaMailConfigValue={}", JSON.toJSONString(emailSmallDTO),
            JSON.toJSONString(javaMailConfigValue));
        Properties props = new Properties();
        props.put("mail.smtp.host", javaMailConfigValue.getHost());
        props.put("mail.smtp.auth", javaMailConfigValue.getAuth());
        props.put("mail.transport.protocol", javaMailConfigValue.getProtocol());
        props.put("mail.smtp.port", javaMailConfigValue.getPort());
        props.put("mail.debug", javaMailConfigValue.getMailDebug());
        Session mailSession = Session.getInstance(props);

        // 设置session,和邮件服务器进行通讯。
        MimeMessage message = new MimeMessage(mailSession);
        // 设置邮件主题
        message.setSubject(emailSmallDTO.getSubject());
        // 设置邮件正文
        message.setContent(emailSmallDTO.getContent(), javaMailConfigValue.getContentType());
        // 设置邮件发送日期
        message.setSentDate(new Date());

        InternetAddress address = null;
        // 设置发件人信息
        if (StringUtils.isEmpty(javaMailConfigValue.getUsername())) {
            address = new InternetAddress(javaMailConfigValue.getMailFrom(), emailSmallDTO.getUserName());
        } else {
            address = new InternetAddress(javaMailConfigValue.getMailFrom(), javaMailConfigValue.getUsername());
        }

        // 设置邮件发送者的地址
        message.setFrom(address);
        // 设置邮件接收方的地址
        message.setRecipients(Message.RecipientType.TO, MailUtils.getAddresses(emailSmallDTO.getTo()));

        // 普通抄送,收件人互可见
        if (emailSmallDTO.getCc() != null && emailSmallDTO.getCc().length != 0) {
            message.setRecipients(Message.RecipientType.CC, MailUtils.getAddresses(emailSmallDTO.getCc()));
        }

        // 加密抄送,收件人不可见
        if (emailSmallDTO.getBcc() != null && emailSmallDTO.getBcc().length != 0) {
            message.setRecipients(Message.RecipientType.BCC, MailUtils.getAddresses(emailSmallDTO.getBcc()));
        }

        Transport transport = mailSession.getTransport();
        message.saveChanges();
        transport.connect(javaMailConfigValue.getHost(), javaMailConfigValue.getMailFrom(),
            javaMailConfigValue.getPassword());
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    /**
     * 带附件发送 Hutools工具类
     * 
     * @param emailSmallDTO
     * @param javaMailConfigValue
     * @throws Exception
     */
    public static void sendMail(EmailSmallDTO emailSmallDTO,
        JavaMailConfigValue javaMailConfigValue) throws FileNotFoundException {
        log.info("sendSimpleMail start emailSmallDTO={},javaMailConfigValue={}", JSON.toJSONString(emailSmallDTO),
            JSON.toJSONString(javaMailConfigValue));
        HashMap<String, InputStream> maps = Maps.newHashMap();
        if (MapUtils.isNotEmpty(emailSmallDTO.getPathMap())) {
            // 上传文件
            Map<String, String> pathMap = emailSmallDTO.getPathMap();
            // Iterating entries using a For Each loop
            Iterator<Map.Entry<String, String>> entries = pathMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                maps.put(entry.getKey(), new FileInputStream(entry.getValue()));
            }
        }

        MailUtil.send(MailUtils.javaMailConfigValue2MailAccount(javaMailConfigValue), emailSmallDTO.getTo(),
            emailSmallDTO.getSubject(), emailSmallDTO.getContent(), maps, false);
    }

}
