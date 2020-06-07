package com.luna.commons.message.wrapper;

import com.alibaba.fastjson.JSON;
import com.luna.commons.dto.constant.ResultCode;
import com.luna.commons.exception.FileException;
import com.luna.commons.message.constant.MessageTypeConstant;
import com.luna.commons.utils.text.CharsetKit;
import org.apache.commons.io.FileExistsException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
import java.io.*;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Luna@win10
 * @date 2020/5/16 14:07
 */
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class MailWrapper {
    private final static Logger logger = LoggerFactory.getLogger(MailWrapper.class);

    @Autowired
    StringRedisTemplate         stringRedisTemplate;

    @Autowired
    JavaMailSenderImpl          javaMailSender;

    private String              username;

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
        logger.info("simpleMailMessage={}", JSON.toJSONString(simpleMailMessage));
        javaMailSender.send(simpleMailMessage);
        logger.info("javaMailSender.send success, simpleMailMessage={}", JSON.toJSONString(simpleMailMessage));
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
        logger.info("simpleMailMessage={}", JSON.toJSONString(simpleMailMessage));
        javaMailSender.send(simpleMailMessage);
        logger.info("javaMailSender.send success, simpleMailMessage={}", JSON.toJSONString(simpleMailMessage));
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
        logger.info("simpleMailMessage={}", JSON.toJSONString(mimeMessage));
        javaMailSender.send(mimeMessage);
        logger.info("javaMailSender.send success, simpleMailMessage={}", JSON.toJSONString(mimeMessage));
    }

    /**
     * HTML 模板发送
     * 
     * @param main 昵称
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param Cc 抄送人
     * @param Bcc 加密抄送
     * @param pathMap 附件地址<名称,路径>
     * @param src 超链接
     * @param model 模板名称
     */
    public void sendEmail(String main, String[] to, String subject, String content, String[] Cc, String[] Bcc,
        Map<String, String> pathMap, String src, String model) throws IOException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        // 普通抄送,收件人互可见
        if (Cc != null) {
            helper.setCc(Cc);
        }

        // 加密抄送,收件人不可见
        if (Bcc != null) {
            helper.setBcc(Bcc);
        }

        // 默认使用配置发送人昵称
        if (main == null || main == "") {
            main = name;
        }

        if (pathMap != null && pathMap.size() != 0) {
            // 上传文件
            // Iterating entries using a For Each loop
            Iterator<Map.Entry<String, String>> entries = pathMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                helper.addAttachment(entry.getKey(), new File(entry.getValue()));
            }
        }
        // 使用默认模板
        if (model == null) {
            model = MessageTypeConstant.EMAIL_MODEL;
        }
        // 设置自定义发件人昵称
        String nick = MimeUtility.encodeText(main);
        helper.setFrom(new InternetAddress(nick + " <" + username + ">"));
        helper.setSubject(subject + DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
        helper.setText(buildContent(model, content, src), true);
        javaMailSender.send(message);
    }

    /**
     * 模板加载
     * 
     * @param model 模板
     * @param content 内容
     * @param src 超链接
     * @return
     * @throws IOException
     */
    private static String buildContent(String model, String content, String src) throws IOException {
        // 加载邮件html模板
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(model)));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            logger.error("读取文件失败，fileName:{}", model, e);
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION);
        } finally {
            fileReader.close();
        }
        String contentText =
            "你好.<br> <b style='color:blue'> " + content + "</b><a/>";
        // 邮件表格header
        String header = "luna - message © cpoyRight";
        String path = "<a href='" + src + "'>luna-message</a>";

        // 绿色
        String emailHeadColor = "#10fa81";
        String date = DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss");
        // 填充html模板中的五个参数
        String htmlText =
            MessageFormat.format(buffer.toString(), emailHeadColor, contentText, path, date, header);
        return htmlText;
    }
}
