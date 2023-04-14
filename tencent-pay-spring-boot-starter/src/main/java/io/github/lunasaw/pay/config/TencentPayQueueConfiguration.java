package io.github.lunasaw.pay.config;

import io.github.lunasaw.pay.properties.TencentPayMqConfigProperties;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luna
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.wechat", name = "pay-mq.order.enable", havingValue = "true")
public class TencentPayQueueConfiguration {

    @Autowired
    private TencentPayMqConfigProperties tencentPayMqConfigProperties;


    /***
     * 创建DirectExchange交换机
     *
     * @return
     */
    @Qualifier("${spring.wechat.pay-mq.order.exchange}")
    @Bean
    public DirectExchange basicExchange() {
        return new DirectExchange(tencentPayMqConfigProperties.getExchange(), true, false);
    }

    /***
     * 创建队列
     *
     * @return
     */
    @Qualifier("${spring.wechat.pay-mq.order.queue}")
    @Bean
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