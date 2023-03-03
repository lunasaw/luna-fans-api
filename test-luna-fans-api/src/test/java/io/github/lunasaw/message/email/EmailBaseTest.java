package io.github.lunasaw.message.email;

import com.google.common.collect.Maps;
import com.luna.api.config.MailSendProperties;
import com.luna.api.email.constant.MessageTypeConstant;
import com.luna.api.email.dto.EmailSmallDTO;
import com.luna.api.email.service.MessageService;
import com.luna.common.date.DateUtils;
import io.github.lunasaw.BaseTest;
import org.assertj.core.util.Lists;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;

import java.util.HashMap;

/**
 * @author chenzhangyue
 * 2023/3/3
 */
public class EmailBaseTest extends BaseTest {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MailProperties mailProperties;

    @Test
    public void atest() {
        EmailSmallDTO emailSmallDTO = new EmailSmallDTO();
        emailSmallDTO.setTargetList(Lists.newArrayList("15696756582@163.com"));
        emailSmallDTO.setMessageType(MessageTypeConstant.COMPLEX_EMAIL);
        emailSmallDTO.setTemplateId(13L);
        emailSmallDTO.setFromUser(new EmailSmallDTO.Personal(mailProperties.getUsername(), "测试下昵称"));
        emailSmallDTO.setReplyTo(new EmailSmallDTO.Personal(mailProperties.getUsername()));
        emailSmallDTO.setSubject("标题");
        // emailSmallDTO.setSentDate(DateUtils.formatDate());
        emailSmallDTO.setContent(new EmailSmallDTO.Content("内容", true));
        emailSmallDTO.setBcc(Lists.newArrayList(new EmailSmallDTO.Personal("iszychen@gmail.com")));
        emailSmallDTO.setCc(Lists.newArrayList(new EmailSmallDTO.Personal("iszychen@gmail.com")));
        HashMap<@Nullable String, @Nullable String> hashMap = Maps.newHashMap();
        hashMap.put("newPassword", "123123");
        emailSmallDTO.setPlaceholderContent(hashMap);
        messageService.asyncSendMessage(emailSmallDTO);
        while (true) {

        }
    }

}
