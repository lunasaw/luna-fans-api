package io.github.lunasaw.pay.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.lunasaw.pay.properties.TencentPayDelayMqConfigProperties;
import io.github.lunasaw.pay.properties.TencentPayMqConfigProperties;

/**
 * @author luna
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.wechat", name = "pay-mq.delay.enable", havingValue = "true")
public class TencentPayDelayQueueConfiguration {

    @Autowired
    private TencentPayMqConfigProperties      tencentPayMqConfigProperties;

    @Autowired
    private TencentPayDelayMqConfigProperties tencentPayDelayMqConfigProperties;

    /** 创建Queue1 延时队列 会过期,过期后数据发送给Queue2 */
    @Bean
    public Queue orderDelayQueue() {
        return QueueBuilder.durable("orderDelayQueue")
            .withArgument("x-dead-letter-exchange", "orderListenerExchange")
            // 死信交换机
            .withArgument("x-dead-letter-routing-key", "orderListenerQueue").build();

    }

    /** 创建delay Queue */
    @Bean(name = "${spring.wechat.pay-mq.delay.queue}")
    public Queue orderListenerQueue() {
        return new Queue(tencentPayDelayMqConfigProperties.getQueue(), true);
    }

    /** 创建交换机 */
    @Bean
    public Exchange orderListenerExchange() {
        return new DirectExchange(tencentPayMqConfigProperties.getExchange());
    }

    /** 队列绑定交换机 */
    @Bean
    public Binding orderListenerBinding(Queue orderListenerQueue, Exchange orderListenerExchange) {
        return BindingBuilder.bind(orderListenerQueue).to(orderListenerExchange).with("orderListenerQueue").noargs();
    }

    /***
     * 创建DirectExchange交换机
     *
     * @return
     */
    @Bean
    public DirectExchange basicExchange() {
        return new DirectExchange(tencentPayMqConfigProperties.getExchange(), true, false);
    }

    /***
     * 创建队列
     *
     * @return
     */
    @Bean(name = "${spring.wechat.pay-mq.order.queue}}")
    public Queue queueOrder() {
        return new Queue(tencentPayMqConfigProperties.getQueue(), true);
    }

    /****
     * 队列绑定到交换机上
     *
     * @return
     */
    @Bean
    public Binding basicBinding() {
        return BindingBuilder.bind(queueOrder()).to(basicExchange()).with(tencentPayMqConfigProperties.getRouting());
    }

}
