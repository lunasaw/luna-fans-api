package com.luna.tencent.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luna@mac
 * @className TencentConfiguration.java
 * @description TODO
 * @createTime 2021年03月27日 17:06:00
 */
@Configuration
@ConditionalOnProperty(prefix = "luna.ten", name = "enable", havingValue = "true")
@EnableConfigurationProperties({TencentConfigValue.class, TencentPayConfigValue.class, TencentPayMqConfigValue.class})
public class TencentConfiguration {

    @Autowired
    private TencentConfigValue      tencentConfigValue;

    @Autowired
    private TencentPayConfigValue   tencentPayConfigValue;

    @Autowired
    private TencentPayMqConfigValue tencentPayMqConfigValue;

    public TencentConfiguration(TencentConfigValue tencentConfigValue, TencentPayConfigValue tencentPayConfigValue,
        TencentPayMqConfigValue tencentPayMqConfigValue) {
        this.tencentConfigValue = tencentConfigValue;
        this.tencentPayConfigValue = tencentPayConfigValue;
        this.tencentPayMqConfigValue = tencentPayMqConfigValue;
    }

    @Bean
    @ConditionalOnMissingBean
    public TencentPayQueueConfig tencentPayQueueConfig() {
        return new TencentPayQueueConfig();
    }

    /***
     * 创建DirectExchange交换机
     *
     * @return
     */
    @Bean
    public DirectExchange basicExchange() {
        return new DirectExchange(tencentPayMqConfigValue.getExchange(), true, false);
    }

    /***
     * 创建队列
     *
     * @return
     */
    @Bean(name = "queueOrder")
    public Queue queueOrder() {
        return new Queue(tencentPayMqConfigValue.getQueue(), true);
    }

    /****
     * 队列绑定到交换机上
     *
     * @return
     */
    @Bean
    public Binding basicBinding() {
        return BindingBuilder.bind(queueOrder()).to(basicExchange()).with(tencentPayMqConfigValue.getRouting());
    }

}
