package io.github.lunasaw.pay.config;

import io.github.lunasaw.pay.properties.TencentPayConfigProperties;
import io.github.lunasaw.pay.properties.TencentPayMqConfigProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author luna@mac
 * @date 2021年03月27日 17:06:00
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.wechat", name = "enable", havingValue = "true")
@EnableConfigurationProperties({TencentPayConfigProperties.class, TencentPayMqConfigProperties.class})
@ComponentScan(basePackages = {"io.github.lunasaw.pay"})
public class TencentPayAutoConfiguration {

    private TencentPayConfigProperties   tencentPayConfigProperties;

    private TencentPayMqConfigProperties tencentPayMqConfigProperties;

    public TencentPayAutoConfiguration(TencentPayConfigProperties tencentPayConfigProperties,
        TencentPayMqConfigProperties tencentPayMqConfigProperties) {
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
