package com.luna.tencent.config;

import com.luna.tencent.properties.TencentApiConfigProperties;
import com.luna.tencent.properties.TencentPayConfigProperties;
import com.luna.tencent.properties.TencentPayMqConfigProperties;
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
@ConditionalOnProperty(prefix = "luna.ten", name = "secretId")
@EnableConfigurationProperties({TencentApiConfigProperties.class, TencentPayConfigProperties.class,
    TencentPayMqConfigProperties.class})
public class TencentApiAutoConfiguration {

    @Autowired
    private TencentApiConfigProperties   tencentApiConfigProperties;

    @Autowired
    private TencentPayConfigProperties   tencentPayConfigProperties;

    @Autowired
    private TencentPayMqConfigProperties tencentPayMqConfigProperties;

    public TencentApiAutoConfiguration(TencentApiConfigProperties tencentApiConfigProperties,
        TencentPayConfigProperties tencentPayConfigProperties,
        TencentPayMqConfigProperties tencentPayMqConfigProperties) {
        this.tencentApiConfigProperties = tencentApiConfigProperties;
        this.tencentPayConfigProperties = tencentPayConfigProperties;
        this.tencentPayMqConfigProperties = tencentPayMqConfigProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public TencentPayQueueConfiguration tencentPayQueueConfig() {
        return new TencentPayQueueConfiguration();
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
    @Bean(name = "queueOrder")
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
