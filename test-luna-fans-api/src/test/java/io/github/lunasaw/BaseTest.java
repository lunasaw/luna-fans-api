package io.github.lunasaw;

import com.alibaba.fastjson.JSON;
import com.apple.eawt.Application;
import com.google.common.net.MediaType;
import com.luna.api.email.dto.EmailSmallDTO;
import com.luna.api.email.service.MessageService;
import com.luna.api.email.service.TemplateService;
import io.github.lunasaw.listener.ApiMethodListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
// @Import(BaseTest.TemplateServiceContextConfiguration.class)
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
    public void btest() {
        messageService.sendSimpleMessage("主题", "内容");
    }

    // @TestConfiguration
    // static class TemplateServiceContextConfiguration {
    // @Bean
    // public TemplateService templateService() {
    // return new TemplateService();
    // }
    // }

}
