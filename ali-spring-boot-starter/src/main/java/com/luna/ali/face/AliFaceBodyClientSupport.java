package com.luna.ali.face;

import com.aliyun.facebody20191230.Client;
import com.aliyun.teautil.models.RuntimeOptions;
import com.luna.ali.config.AliConfigProperties;
import com.luna.ali.constant.AliAccessConstant;
import com.luna.ali.face.api.AliFaceCheckApi;
import com.luna.ali.face.enums.FaceTypeEnum;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author luna
 * @description
 * @date 2023/4/15
 */
@Component
public class AliFaceBodyClientSupport implements InitializingBean {

    @Autowired
    private AliConfigProperties aliConfigProperties;

    private FaceClient          faceClient;

    private RuntimeOptions      runtimeOptions;


    public com.aliyun.teaopenapi.Client getClient(FaceTypeEnum faceType) {
        if (FaceTypeEnum.FACE_DETECTION.equals(faceType)) {
            return faceClient.getFaceBodyClient();
        } else if (FaceTypeEnum.FACE_VERIFICATION.equals(faceType)) {
            return faceClient.getFaceBodyClient();
        }
        throw new RuntimeException("getClient not found");
    }

    @Data
    public static class FaceClient {

        /**
         * 人脸检测
         */
        private com.aliyun.facebody20191230.Client faceCheckClient;

        /**
         * 人脸核身
         */
        private com.aliyun.facebody20200910.Client faceBodyClient;
    }

    public RuntimeOptions getRuntimeOptions() {
        return runtimeOptions;
    }

    public void createClient(String accessKeyId, String accessKeySecret) {
        try {
            // CLIENT 单例模式创建
            synchronized (AliFaceCheckApi.class) {

                if (faceClient == null) {
                    com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                        .setAccessKeyId(accessKeyId)
                        .setAccessKeySecret(accessKeySecret)
                        .setEndpoint(AliAccessConstant.FACE_BODY_HOST);
                    faceClient = new FaceClient();
                    faceClient.setFaceCheckClient(new com.aliyun.facebody20191230.Client(config));
                    faceClient.setFaceBodyClient(new com.aliyun.facebody20200910.Client(config));
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
