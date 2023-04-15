package com.luna.ali.config;

import com.aliyun.facebody20191230.Client;
import com.aliyun.teautil.models.RuntimeOptions;
import com.luna.ali.constant.AliAccessConstant;
import com.luna.ali.face.AliFaceCheckApi;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author weidian
 * @description
 * @date 2023/4/15
 */
@Component
public class AliClientSupport implements InitializingBean {

    @Autowired
    private AliConfigProperties aliConfigProperties;

    private Client              client         = null;

    private RuntimeOptions      runtimeOptions = null;

    public Client getClient() {
        return client;
    }

    public RuntimeOptions getRuntimeOptions() {
        return runtimeOptions;
    }

    public void createClient(String accessKeyId, String accessKeySecret) {
        try {
            // CLIENT 单例模式创建
            synchronized (AliFaceCheckApi.class) {
                if (client == null) {
                    com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                        .setAccessKeyId(accessKeyId)
                        .setAccessKeySecret(accessKeySecret)
                        .setEndpoint(AliAccessConstant.FACE_BODY_HOST);
                    client = new Client(config);
                }

                if (runtimeOptions == null) {
                    runtimeOptions = new RuntimeOptions();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        createClient(aliConfigProperties.getAccessKey(), aliConfigProperties.getSecretKey());
    }
}
