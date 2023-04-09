package io.github.lunasaw.pay.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @Package: com.luna.tencent.config
 * @ClassName: TencentPayMqConfigValue
 * @Author: luna
 * @CreateTime: 2020/8/17 14:19
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "spring.wechat.pay-mq.delay")
@Data
public class TencentPayDelayMqConfigProperties {

    private Boolean enable;

    private String  exchange;

    private String  listenerExchange;

    private String  queue;

    private String  listenerQueue;

    private String  routing;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getRouting() {
        return routing;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }
}
