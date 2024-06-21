package com.luna.ali.oss;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.luna.ali.config.AliConfigProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author weidian
 * @description
 * @date 2023/4/16
 */
@Component
public class AliOssClientSupport implements InitializingBean {

    @Autowired
    private AliConfigProperties aliConfigProperties;

    /**
     * 创建OSSClient实例
     */
    private OSS                 client;

    public OSS getInstanceClient() {
        synchronized (AliOssClientSupport.class){
            if (aliConfigProperties.getEnableCname() && StringUtils.isNotEmpty(aliConfigProperties.getDomain())) {
                ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
                // 设置是否支持CNAME。CNAME是指将自定义域名绑定到存储空间上。
                conf.setSupportCname(true);
                return new OSSClientBuilder().build(aliConfigProperties.getDomain(), aliConfigProperties.getAccessKey(),
                        aliConfigProperties.getSecretKey(), conf);
            }
            if (client == null) {
                this.client = new OSSClientBuilder().build(aliConfigProperties.getEndpoint(), aliConfigProperties.getAccessKey(),
                        aliConfigProperties.getSecretKey());
            }
        }
        return client;
    }

    @Override
    public void afterPropertiesSet() {
        client = getInstanceClient();
    }

    @PreDestroy
    public void destroy() {
        this.client.shutdown();
    }
}
