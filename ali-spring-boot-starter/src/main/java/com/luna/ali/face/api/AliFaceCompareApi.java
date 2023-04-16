package com.luna.ali.face.api;

import com.aliyun.facebody20191230.models.CompareFaceRequest;
import com.aliyun.facebody20191230.models.CompareFaceResponse;
import com.aliyun.facebody20191230.models.CompareFaceWithMaskRequest;
import com.aliyun.facebody20191230.models.CompareFaceWithMaskResponse;
import com.luna.ali.face.AliFaceBodyClientSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author weidian
 * @description
 * @date 2023/4/16
 */
@Component
public class AliFaceCompareApi {


    @Autowired
    private AliFaceBodyClientSupport aliFaceBodyClientSupport;

    public CompareFaceResponse compareFaceWithOptionsData(String imageA, String imageB) {
        return compareFaceWithOptions(new CompareFaceRequest().setImageDataA(imageA).setImageDataB(imageB));
    }

    public CompareFaceResponse compareFaceWithOptions(String url1, String url2) {
        return compareFaceWithOptions(new CompareFaceRequest().setImageURLA(url1).setImageURLB(url2));
    }

    /**
     * 人脸1:1 对比
     * @param compareFaceRequest
     * @return
     */
    public CompareFaceResponse compareFaceWithOptions(CompareFaceRequest compareFaceRequest) {
        try {
            return aliFaceBodyClientSupport.getClient().compareFaceWithOptions(compareFaceRequest, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 人脸口罩识别 需要企业认证
     * @param url1 图片1
     * @param url2 图片2
     * @param qualityScoreThreshold 口罩质量分数阈值，取值范围[0,100]，默认值为97.0
     * @return
     */
    public CompareFaceWithMaskResponse compareFaceWithMaskWithOptions(String url1, String url2, Float qualityScoreThreshold) {
        return compareFaceWithMaskWithOptions(new CompareFaceWithMaskRequest().setImageURLA(url1).setImageURLB(url2));
    }


    public CompareFaceWithMaskResponse compareFaceWithMaskWithOptions(CompareFaceWithMaskRequest compareFaceRequest) {
        try {
            return aliFaceBodyClientSupport.getClient().compareFaceWithMaskWithOptions(compareFaceRequest, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
