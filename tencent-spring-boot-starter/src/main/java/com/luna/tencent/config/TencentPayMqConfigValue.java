package com.luna.tencent.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Package: com.luna.tencent.config
 * @ClassName: TencentPayMqConfigValue
 * @Author: luna
 * @CreateTime: 2020/8/17 14:19
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "mq.pay")
public class TencentPayMqConfigValue {

    private String exchange;

    private String queue;

    private String routing;

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
