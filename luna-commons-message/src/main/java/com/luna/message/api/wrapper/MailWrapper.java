package com.luna.message.api.wrapper;

import com.alibaba.fastjson.JSON;
import com.luna.common.utils.StringUtils;
import com.luna.common.utils.text.CharsetKit;
import com.luna.message.api.constant.MessageTypeConstant;
import com.luna.message.emailModel.EmailModelBuild;
import com.luna.message.entity.EmailDTO;
import com.luna.message.entity.EmailSmallDTO;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
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
import java.util.concurrent.TimeUnit;

/**
 * @author Luna@win10
 * @date 2020/5/16 14:07
 */
@Component
public class MailWrapper {
    private final static Logger log = LoggerFactory.getLogger(MailWrapper.class);

    @Autowired
    StringRedisTemplate         stringRedisTemplate;

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
     * @param to 接收者
     * @param subject 标题
     * @param content 内容
     * @param authCode 验证码 可选
     */
    public void sendEmail(String to, String subject, String content, String authCode)
        throws UnsupportedEncodingException, AddressException {
        if (authCode != null) {
            stringRedisTemplate.delete(to);
            stringRedisTemplate.opsForValue().append(to, authCode);
            stringRedisTemplate.expire(to, 300, TimeUnit.SECONDS);
        }
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 标题
        simpleMailMessage.setSubject(subject + DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
        // 内容
        simpleMailMessage.setText(content);
        // 发送人地址
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(username);
        // 设置自定义发件人昵称
        String nick = MimeUtility.encodeText(name);
        simpleMailMessage.setFrom(new InternetAddress(nick + " <" + username + ">").toString());
        log.info("simpleMailMessage={}", JSON.toJSONString(simpleMailMessage));
        javaMailSender.send(simpleMailMessage);
        log.info("javaMailSender.send success, simpleMailMessage={}", JSON.toJSONString(simpleMailMessage));
    }

    /**
     * 发送简单邮件
     *
     * @param to 接收者
     * @param subject 标题
     * @param content 内容
     * @param Cc 抄送者 可选
     * @param Bcc 密抄送 可选
     */
    public void sendEmail(String main, String to, String subject, String content, String[] Cc, String[] Bcc) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 邮件设置
        // 标题
        simpleMailMessage.setSubject(subject + DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
        // 内容
        simpleMailMessage.setText(content);
        // 发送人地址
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(username);
        try {
            // 设置自定义发件人昵称
            if (main == null || main == "") {
                main = name;
            }
            String nick = MimeUtility.encodeText(main);
            simpleMailMessage.setFrom(new InternetAddress(nick + " <" + username + ">").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 普通抄送,收件人互可见
        if (Cc != null) {
            simpleMailMessage.setCc(Cc);
        }
        // 加密抄送,收件人不可见
        if (Bcc != null) {
            simpleMailMessage.setBcc(Bcc);
        }
        log.info("simpleMailMessage={}", JSON.toJSONString(simpleMailMessage));
        javaMailSender.send(simpleMailMessage);
        log.info("javaMailSender.send success, simpleMailMessage={}", JSON.toJSONString(simpleMailMessage));
    }

    /**
     * 发送复杂邮件
     *
     * @param main 昵称
     * @param to 给谁
     * @param subject 标题
     * @param content 发送内容
     * @param pathMap 文件名 和文件地址的Map
     */
    public void sendEmail(String main, String to, String subject, String content, String[] Cc, String[] Bcc,
        Map<String, String> pathMap) throws MessagingException, UnsupportedEncodingException {
        // 创建复杂邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, CharsetKit.UTF_8);
        // 发送人地址
        mimeMessageHelper.setTo(to);
        // 标题
        mimeMessageHelper.setSubject(subject + DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
        // 内容
        mimeMessageHelper.setText("<b style='color:blue'>" + content + "</b>", true);
        // 普通抄送,收件人互可见
        if (Cc != null) {
            mimeMessageHelper.setCc(Cc);
        }
        // 加密抄送,收件人不可见
        if (Bcc != null) {
            mimeMessageHelper.setBcc(Bcc);
        }
        // 默认使用配置发送人昵称
        if (main == null || main.length() == 0) {
            main = name;
        }
        if (pathMap != null && pathMap.size() != 0) {
            // 上传文件
            // Iterating entries using a For Each loop
            Iterator<Map.Entry<String, String>> entries = pathMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                mimeMessageHelper.addAttachment(entry.getKey(), new File(entry.getValue()));
            }
        }
        // 设置自定义发件人昵称
        String nick = MimeUtility.encodeText(main);
        mimeMessage.setFrom(new InternetAddress(nick + " <" + username + ">"));
        log.info("simpleMailMessage={}", JSON.toJSONString(mimeMessage));
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

            if (MapUtils.isNotEmpty(emailDTO.getPathMap())) {
                // 上传文件
                Map<String, String> pathMap = emailDTO.getPathMap();
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
                helper.setSubject(smallDTO.getSubject() + DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
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
