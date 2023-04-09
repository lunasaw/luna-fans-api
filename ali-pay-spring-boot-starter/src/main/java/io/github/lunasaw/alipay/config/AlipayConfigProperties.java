package io.github.lunasaw.alipay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 应用启动加载文件
 *
 * @author Louis
 * @date Dec 12, 2018
 */
@Component
@ConfigurationProperties(prefix = "spring.alipay")
@Data
public class AlipayConfigProperties {

    private boolean enable;

    private String  appId;

    private String  privateKey;

    private String  gatewayUrl;

    private String  publicKey;

    private String  notifyUrl;

    private String  returnUrl;
}
