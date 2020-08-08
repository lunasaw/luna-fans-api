package com.luna.message.tests;

import com.google.common.collect.Maps;
import com.luna.message.MessageApplicationTest;
import com.luna.message.api.MessageSend;
import com.luna.message.api.constant.EmailContentsConstant;
import com.luna.message.api.constant.MessageTypeConstant;
import com.luna.message.api.wrapper.MailWrapper;
import com.luna.message.entity.EmailDTO;
import com.luna.message.entity.EmailSmallDTO;
import com.luna.message.entity.ModelContentDTO;
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
    private MessageSend messageSend;

    @Autowired
    private MailWrapper mailWrapper;

    @Test
    public void atest() throws InterruptedException {
        EmailSmallDTO emailSmallDTO = new EmailSmallDTO("Luna", "15696756582@163.com", "", "");
        EmailDTO emailDTO = new EmailDTO();
        HashMap<String, String> map = Maps.newHashMap();
        map.put(EmailContentsConstant.COPY_RIGHT_NAME, "luna");
        map.put(EmailContentsConstant.EMAIL_CONTENT_BEFORE_OUTER_USER, "这是放在用户信息之前的介绍内容");
        map.put(EmailContentsConstant.EMAIL_CONTENT_AFTER_OUTER_USER, "这个是logo图片噢");
        map.put(EmailContentsConstant.EMAIL_CONTENT_AFTER_OUTER_USER_SRC,
            "https://gitee.com/Iszychen/luna/raw/master/img/20200808-162340-0536.png");
        map.put(EmailContentsConstant.EMAIL_HEAD_COLOR_VALUE, "blue");
        emailSmallDTO.setSubject("这是测试邮件");
        emailSmallDTO.setContent("这是测试邮件");
        emailDTO.setEmailSmallDTO(emailSmallDTO);
        ModelContentDTO modelContentDTO = new ModelContentDTO();
        modelContentDTO.setContents(map);
        modelContentDTO.setOuterName("陈章月");
        emailDTO.setModelContentDTO(modelContentDTO);
        emailDTO.setModelName(MessageTypeConstant.EMAIL_MODEL);
        mailWrapper.sendEmail(emailDTO);
    }
}
