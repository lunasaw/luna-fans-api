package com.luna.tencent.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @Package: com.luna.tencent.config
 * @ClassName: TencentPayMqConfigValue
 * @Author: luna
 * @CreateTime: 2020/8/17 14:19
 * @Description:
 */
@Configuration
public class TencentPayMqConfig {

    @Autowired
    private Environment env;

    /***
     * 创建DirectExchange交换机
     * 
     * @return
     */
    @Bean
    public DirectExchange basicExchange() {
        return new DirectExchange(env.getProperty("mq.pay.exchange.order"), true, false);
    }

    /***
     * 创建队列
     * 
     * @return
     */
    @Bean(name = "queueOrder")
    public Queue queueOrder() {
        return new Queue(env.getProperty("mq.pay.queue.order"), true);
    }

    /****
     * 队列绑定到交换机上
     * 
     * @return
     */
    @Bean
    public Binding basicBinding() {
        return BindingBuilder.bind(queueOrder()).to(basicExchange()).with(env.getProperty("mq.pay.routing.key"));
    }

}
