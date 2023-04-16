package com.luna.ali.face.api;

import com.aliyun.facebody20200910.Client;
import com.aliyun.facebody20200910.models.ExecuteServerSideVerificationRequest;
import com.aliyun.facebody20200910.models.ExecuteServerSideVerificationResponse;
import com.google.common.collect.Maps;
import com.luna.ali.face.enums.FaceTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import com.luna.ali.face.AliFaceBodyClientSupport;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luna
 * @description
 * @date 2023/4/16
 */
@Component
public class AliFaceAuditApi {

    @Autowired
    private AliFaceBodyClientSupport aliFaceBodyClientSupport;

    private Client getClient() {
        return (Client)aliFaceBodyClientSupport.getClient(FaceTypeEnum.FACE_VERIFICATION);
    }

    /**
     * 人脸核身服务端。
     *
     * @param certificateName 真实姓名。
     * @param certificateNumber 身份证号。
     * @param facialPictureUrl 待比对的图像URL地址，只支持JPG格式图片。推荐使用上海地域的OSS链接，对于文件在本地或者非上海地域OSS链接的情况，请参见文件URL处理。
     */
    public ExecuteServerSideVerificationResponse ExecuteServerSideVerificationResponse(String certificateName, String certificateNumber,
        String facialPictureUrl) {
        return executeServerSideVerificationWithOptions(certificateName, certificateNumber, null, facialPictureUrl, "server");
    }

    /**
     * 人脸核身服务端。
     *
     * @param certificateName 真实姓名。
     * @param certificateNumber 身份证号。
     * @param facialPictureData
     * 待比对的图像，Base64格式，只支持JPG格式图片。必须是"/9j/"开头，如非"/9j/"开头请检查是否为JPG格式图片。文件的Base64编码处理操作，请参见文件Base64处理。
     * @param facialPictureUrl 待比对的图像URL地址，只支持JPG格式图片。推荐使用上海地域的OSS链接，对于文件在本地或者非上海地域OSS链接的情况，请参见文件URL处理。
     * @param sceneType 场景类型，默认为server。目前只支持server。
     */
    public ExecuteServerSideVerificationResponse executeServerSideVerificationWithOptions(String certificateName, String certificateNumber,
        String facialPictureData, String facialPictureUrl, String sceneType) {
        ExecuteServerSideVerificationRequest request = new ExecuteServerSideVerificationRequest();
        request.setCertificateName(certificateName);
        request.setCertificateNumber(certificateNumber);
        request.setFacialPictureData(facialPictureData);
        request.setFacialPictureUrl(facialPictureUrl);
        request.setSceneType(sceneType);
        return executeServerSideVerificationWithOptions(request, Maps.newHashMap());
    }

    public ExecuteServerSideVerificationResponse executeServerSideVerificationWithOptions(ExecuteServerSideVerificationRequest request,
        Map<String, String> headers) {
        try {
            return getClient().executeServerSideVerificationWithOptions(request, headers, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
