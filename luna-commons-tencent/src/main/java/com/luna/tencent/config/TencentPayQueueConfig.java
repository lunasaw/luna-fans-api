package com.luna.tencent.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.luna.tencent.config
 * @ClassName: TencentPayQueenConfig
 * @Author: luna
 * @CreateTime: 2020/8/17 16:22
 * @Description:
 */
@Configuration
public class TencentPayQueueConfig {

    /** 创建Queue1 延时队列 会过期,过期后数据发送给Queue2 */
    @Bean
    public Queue orderDelayQueue() {
        return QueueBuilder.durable("orderDelayQueue")
            .withArgument("x-dead-letter-exchange", "orderListenerExchange")
            // 死信交换机
            .withArgument("x-dead-letter-routing-key", "orderListenerQueue").build();

    }

    /** 创建Queue2 */
    @Bean
    public Queue orderListenerQueue() {
        return new Queue("orderListenerQueue", true);
    }

    /** 创建交换机 */
    @Bean
    public Exchange orderListenerExchange() {
        return new DirectExchange("orderListenerExchange");
    }

    /** 队列绑定交换机 */
    @Bean
    public Binding orderListenerBinding(Queue orderListenerQueue, Exchange orderListenerExchange) {
        return BindingBuilder.bind(orderListenerQueue).to(orderListenerExchange).with("orderListenerQueue").noargs();
    }
}
