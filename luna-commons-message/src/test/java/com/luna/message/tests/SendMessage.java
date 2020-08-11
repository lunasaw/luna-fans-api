package com.luna.message.tests;

import com.google.common.collect.Maps;
import com.luna.message.MessageApplicationTest;
import com.luna.message.api.MessageSend;
import com.luna.message.api.constant.EmailContentsConstant;
import com.luna.message.api.constant.MessageTypeConstant;
import com.luna.message.api.wrapper.MailWrapper;
import com.luna.message.config.JavaMailConfigValue;
import com.luna.message.dto.EmailDTO;
import com.luna.message.dto.EmailSmallDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * @Package: com.luna.message.tests
 * @ClassName: SendMessage
 * @Author: luna
 * @CreateTime: 2020/7/16 19:05
 * @Description:
 */
public class SendMessage extends MessageApplicationTest {
    @Autowired
    private MessageSend         messageSend;

    @Autowired
    private MailWrapper         mailWrapper;

    @Autowired
    private JavaMailConfigValue javaMailConfigValue;

    @Test
    public void atest() throws InterruptedException {
        EmailSmallDTO emailSmallDTO = new EmailSmallDTO("Luna", "15696756582@163.com", "这是测试邮件", "");
        EmailDTO emailDTO = new EmailDTO();
        HashMap<String, String> map = Maps.newHashMap();
        map.put(EmailContentsConstant.COPY_RIGHT_NAME, "luna");
        map.put(EmailContentsConstant.EMAIL_CONTENT_BEFORE_OUTER_USER, "这是放在用户信息之前的介绍内容");
        map.put(EmailContentsConstant.EMAIL_CONTENT_AFTER_OUTER_USER, "这个是logo图片噢");
        map.put(EmailContentsConstant.EMAIL_CONTENT_AFTER_OUTER_USER_SRC,
            "https://gitee.com/Iszychen/luna/raw/master/img/20200808-162340-0536.png");

        map.put(EmailContentsConstant.EMAIL_CONTENT_USER_NAME, "luna");
        emailSmallDTO.setContent("这是测试邮件");
        HashMap<String, String> pathMap = Maps.newHashMap();
        pathMap.put("luna.png", "C:\\Users\\improve\\Pictures\\Camera Roll\\luna-logo.png");
        emailSmallDTO.setPathMap(pathMap);
        emailDTO.setEmailSmallDTO(emailSmallDTO);
        emailDTO.setContents(map);
        emailDTO.setModelName(MessageTypeConstant.EMAIL_MODEL);
        // mailWrapper.sendEmail(emailDTO);
    }

    @Test
    public void btest() throws InterruptedException {
        messageSend.authCode("15696756582@163.com");
        Thread.sleep(30000);
    }

    @Test
    public void ctest() throws Exception {
        EmailSmallDTO emailSmallDTO = new EmailSmallDTO("Luna", "864636142@qq.com", "王者荣耀欢迎你", "上号!");
        HashMap<String, String> pathMap = Maps.newHashMap();
        // pathMap.put("luna-x.png", "C:\\Users\\improve\\Pictures\\Camera Roll\\luna-logo.png");
        emailSmallDTO.setPathMap(pathMap);
        // JavaMail.sendSimpleMail(emailSmallDTO, javaMailConfigValue);
        // JavaMail.sendMail(emailSmallDTO, javaMailConfigValue);
        HashMap<String, String> map = Maps.newHashMap();

        map.put(EmailContentsConstant.COPY_RIGHT_NAME, "luna");
        map.put(EmailContentsConstant.EMAIL_CONTENT_USER_NAME, "罗杰");
        map.put(EmailContentsConstant.EMAIL_CONTENT_BEFORE_OUTER_USER,
            "《王者荣耀》是腾讯第一5V5团队公平竞技手游，国民MOBA手游大作！5V5王者峡谷、公平对战、还原MOBA经典体验；契约之战、五军对决、边境突围等，带来花式作战乐趣！10秒实时跨区匹配，与好友开黑上分，向最强王者进击！多款英雄任凭选择，一血、五杀、超神，实力碾压，收割全场！敌军即将到达战场，王者召唤师快来集结好友，准备团战，就在《王者荣耀》！");
        map.put(EmailContentsConstant.EMAIL_CONTENT_AFTER_OUTER_USER, "王者荣耀欢迎你");
        map.put(EmailContentsConstant.EMAIL_CONTENT_AFTER_OUTER_USER_SRC,
            "https://pvp.qq.com/");
        map.put(EmailContentsConstant.EMAIL_HEAD_COLOR, "#7B68EE");
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmailSmallDTO(emailSmallDTO);
        emailDTO.setContents(map);
        // JavaMail.sendModelMail(emailDTO, javaMailConfigValue);
    }
}
