package com.luna.ali.face.api;

import com.aliyun.facebody20191230.Client;
import com.aliyun.facebody20191230.models.*;
import com.aliyun.tea.TeaModel;
import com.luna.ali.face.AliFaceBodyClientSupport;
import com.luna.ali.face.enums.FaceTypeEnum;
import com.luna.ali.face.enums.LivenessTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luna
 * @description
 * @date 2023/4/16
 */
@Component
public class AliFaceCompareApi {

    @Autowired
    private AliFaceBodyClientSupport aliFaceBodyClientSupport;

    private Client getClient() {
        return (Client)aliFaceBodyClientSupport.getClient(FaceTypeEnum.FACE_DETECTION);
    }

    // 以下是人脸对比 ========================================

    public CompareFaceResponse compareFaceWithOptionsData(String imageA, String imageB) {
        return compareFaceWithOptions(new CompareFaceRequest().setImageDataA(imageA).setImageDataB(imageB));
    }

    public CompareFaceResponse compareFaceWithOptions(String url1, String url2) {
        return compareFaceWithOptions(new CompareFaceRequest().setImageURLA(url1).setImageURLB(url2));
    }

    /**
     * 人脸1:1 对比
     * 
     * @param compareFaceRequest
     * @return
     */
    public CompareFaceResponse compareFaceWithOptions(CompareFaceRequest compareFaceRequest) {
        try {
            return getClient().compareFaceWithOptions(compareFaceRequest, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 人脸口罩识别 需要企业认证
     * 
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
            return getClient().compareFaceWithMaskWithOptions(compareFaceRequest, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 以下是人脸库构造 ========================================

    // 以下是人脸活体检测 ========================================

    /**
     * 图像Base64编码字符串。
     * 
     * @param listImage
     * @return
     */
    public TeaModel detectLivingFaceWithOptionsBase64(List<String> listImage, LivenessTypeEnum livenessTypeEnum) {
        if (LivenessTypeEnum.NORMAL_LIVE_NESS.equals(livenessTypeEnum)) {
            List<DetectLivingFaceRequest.DetectLivingFaceRequestTasks> taskList =
                listImage.stream().map(e -> new DetectLivingFaceRequest.DetectLivingFaceRequestTasks().setImageData(e)).collect(Collectors.toList());
            return detectLivingFaceWithOptions(taskList);
        }

        throw new RuntimeException("livenessTypeEnum not found");
    }

    /**
     * oss 编码
     * 
     * @param listUrl
     * @return
     */
    public TeaModel detectLivingFaceWithOptionsUrl(List<String> listUrl, LivenessTypeEnum livenessTypeEnum) {

        if (LivenessTypeEnum.NORMAL_LIVE_NESS.equals(livenessTypeEnum)) {
            List<DetectLivingFaceRequest.DetectLivingFaceRequestTasks> taskList =
                listUrl.stream().map(e -> new DetectLivingFaceRequest.DetectLivingFaceRequestTasks().setImageURL(e)).collect(Collectors.toList());
            return detectLivingFaceWithOptions(taskList);
        } else if (LivenessTypeEnum.INFRARED_LIVE_NESS.equals(livenessTypeEnum)) {
            List<DetectInfraredLivingFaceRequest.DetectInfraredLivingFaceRequestTasks> taskList =
                listUrl.stream().map(e -> new DetectInfraredLivingFaceRequest.DetectInfraredLivingFaceRequestTasks().setImageURL(e))
                    .collect(Collectors.toList());
            return detectInfraredLivingFaceWithOptions(taskList);
        }

        throw new RuntimeException("livenessTypeEnum not found");
    }

    /**
     * 人脸活体检测 图片
     * 图像格式：PNG、JPG、JPEG、BMP、GIF、WEBP。
     * 图像大小：不超过10 MB。如您有大图需求，
     * 图像分辨率：建议大于256×256像素，像素过低可能会影响识别效果。
     * URL地址中不能包含中文字符。
     * @param tasks
     * @return
     */
    public DetectLivingFaceResponse detectLivingFaceWithOptions(List<DetectLivingFaceRequest.DetectLivingFaceRequestTasks> tasks) {
        try {
            return getClient().detectLivingFaceWithOptions(new DetectLivingFaceRequest().setTasks(tasks),
                aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <a href="https://help.aliyun.com/document_detail/1000225.html">...</a>
     * 红外人脸活体检测 图片 需要企业认证
     *
     * @param tasks
     * @return
     */
    public DetectInfraredLivingFaceResponse
        detectInfraredLivingFaceWithOptions(List<DetectInfraredLivingFaceRequest.DetectInfraredLivingFaceRequestTasks> tasks) {
        try {
            return getClient().detectInfraredLivingFaceWithOptions(new DetectInfraredLivingFaceRequest().setTasks(tasks),
                aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <a href="https://help.aliyun.com/document_detail/167847.html">...</a>
     * 视频活体验证
     * 视频格式：MP4、AVI。
     * 视频大小：不超过30 MB。
     * 视频分辨率：大于64×64像素以上，视频长度大于3秒。其中人脸尺寸建议大于64×64像素。
     * URL地址中不能包含中文字符
     * @param videoUrl
     * @return
     */
    public DetectVideoLivingFaceResponse detectVideoLivingFaceWithOptions(String videoUrl) {
        try {
            return getClient().detectVideoLivingFaceWithOptions(new DetectVideoLivingFaceRequest().setVideoUrl(videoUrl),
                aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
