package io.github.lunasaw.pay.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.lunasaw.pay.properties.TencentPayDelayMqConfigProperties;

/**
 * @author luna
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.wechat", name = "pay-mq.delay.enable", havingValue = "true")
public class TencentPayDelayQueueConfiguration {


    @Autowired
    private TencentPayDelayMqConfigProperties delayMqConfigProperties;

    /** 创建Queue1 延时队列 会过期,过期后数据发送给Queue2 */
    @Bean
    public Queue orderDelayQueue() {
        return QueueBuilder.durable(delayMqConfigProperties.getQueue())
            .withArgument("x-dead-letter-exchange", delayMqConfigProperties.getListenerExchange())
            // 死信交换机
            .withArgument("x-dead-letter-routing-key", delayMqConfigProperties.getListenerQueue()).build();

    }

    /** 创建Queue2 */
    @Bean
    @Qualifier("${spring.wechat.pay-mq.delay.listenerQueue}}")
    public Queue orderListenerQueue() {
        return new Queue(delayMqConfigProperties.getListenerQueue(), true);
    }

    /** 创建交换机 */
    @Bean
    @Qualifier("${spring.wechat.pay-mq.delay.listenerExchange}}")
    public Exchange orderListenerExchange() {
        return new DirectExchange(delayMqConfigProperties.getListenerExchange());
    }

    /** 队列绑定交换机 */
    @Bean
    public Binding orderListenerBinding(Queue orderListenerQueue, Exchange orderListenerExchange) {
        return BindingBuilder.bind(orderListenerQueue).to(orderListenerExchange).with(delayMqConfigProperties.getRouting()).noargs();
    }

}
