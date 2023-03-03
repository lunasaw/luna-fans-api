package io.github.lunasaw;

import com.alibaba.fastjson.JSON;
import com.apple.eawt.Application;
import com.google.common.net.MediaType;
import com.luna.api.email.dto.EmailSmallDTO;
import com.luna.api.email.service.MessageService;
import com.luna.api.email.service.TemplateService;
import io.github.lunasaw.listener.ApiMethodListener;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author chenzhangyue
 * 2023/3/3
 */
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = FansApi.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestExecutionListeners(value = {
    ApiMethodListener.class,
    DependencyInjectionTestExecutionListener.class
}, mergeMode = TestExecutionListeners.MergeMode.REPLACE_DEFAULTS)
// MergeMode: 自定义的监听器是否与父类监听器合并输出 MERGE_WITH_DEFAULTS 合并，REPLACE_DEFAULTS 替换默认
@TestPropertySource(
    locations = "classpath:application-dev.properties")
public class BaseTest {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private MessageService  messageService;

    @Test
    public void atest() {
        System.out.println(JSON.toJSONString(templateService.listTemplate()));
    }

    @Test
    @SneakyThrows
    public void btest() {
        messageService.sendSimpleMessage("15696756582@163.com", "主题", "内容");
        Thread.sleep(20L);
        while (true) {

        }
    }
}
