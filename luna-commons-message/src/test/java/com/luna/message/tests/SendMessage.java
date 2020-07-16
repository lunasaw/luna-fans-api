package com.luna.message.tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.luna.message.MessageApplicationTest;
import com.luna.message.api.MessageSend;
import com.luna.message.config.TencentConfigValue;
import com.luna.message.config.TencentSmsConfigValue;

/**
 * @Package: com.luna.message.tests
 * @ClassName: SendMessage
 * @Author: luna
 * @CreateTime: 2020/7/16 19:05
 * @Description:
 */
public class SendMessage extends MessageApplicationTest {

    @Autowired
    private TencentSmsConfigValue tencentSmsConfigValue;

    @Autowired
    private TencentConfigValue    tencentCfigValue;

    @Autowired
    private MessageSend           messageSend;

    @Test
    public void atest() throws InterruptedException {
        System.out.println(tencentCfigValue.getSecretKey());
        System.out.println(tencentSmsConfigValue.getResetPassword());
        messageSend.authCode("15696756582@163.com");
        Thread.sleep(30000);
    }
}
