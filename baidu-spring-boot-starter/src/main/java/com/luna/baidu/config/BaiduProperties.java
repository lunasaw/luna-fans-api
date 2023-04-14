package com.luna.baidu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author luna@mac
 * @className BaiduProperties.java
 * @description TODO 参数设置
 * @createTime 2021年03月27日 13:02:00
 */
@ConfigurationProperties(prefix = "spring.baidu")
@Data
public class BaiduProperties {

    private Boolean enable;

    private String  appKey;

    private String  secretKey;

    private String  appId;

    private String  baiduKey;

    private String  mapAk;
}
