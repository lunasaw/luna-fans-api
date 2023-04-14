package io.github.lunasaw.pay.mq;

import io.github.lunasaw.pay.properties.TencentPayConfigProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import io.github.lunasaw.pay.api.TencentPayApi;

/**
 * @Package: io.github.lunasaw.pay.mq
 * @ClassName: DelayMessageListener
 * @Author: luna
 * @CreateTime: 2020/8/17 17:01
 * @Description:
 */
@Component
@ConditionalOnProperty(prefix = "spring.wechat", name = "pay-mq.queue.delay.enable", havingValue = "true")
@RabbitListener(queues = "${spring.wechat.pay-mq.order.queue}")
public class DelayMessageListener {

    @Autowired
    private TencentPayConfigProperties configValue;

    /**
     * 延时队列监听
     */
    @RabbitHandler
    public void delayMessage(String msg) {
        TencentPayApi.closeOrder(configValue, msg);
    }
}
