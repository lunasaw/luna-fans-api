package com.luna.ali.face.api;

import com.aliyun.facebody20191230.Client;
import com.aliyun.facebody20191230.models.*;
import com.luna.ali.face.AliFaceBodyClientSupport;
import com.luna.ali.face.enums.FaceTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author weidian
 * @description
 * @date 2023/4/15
 */
@Slf4j
@Component
public class AliFaceCheckApi {

    @Autowired
    private AliFaceBodyClientSupport aliFaceBodyClientSupport;


    private Client getClient() {
        return (Client) aliFaceBodyClientSupport.getClient(FaceTypeEnum.FACE_DETECTION);
    }

    public DetectCelebrityResponse detectCelebrityWithOptions(String url){
        return detectCelebrityWithOptions(new DetectCelebrityRequest().setImageURL(url));
    }

    /**
     * 明星识别
     * @param request
     * @return
     */
    public DetectCelebrityResponse detectCelebrityWithOptions(DetectCelebrityRequest request) {
        try {
            return getClient().detectCelebrityWithOptions(request, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception teaException) {
            throw new RuntimeException(teaException);
        }
    }


    /**
     * 图像Base64编码字符串。
     * @param listImage
     * @return
     */
    public RecognizePublicFaceResponse recognizePublicFaceWithOptionsWithData(List<String> listImage){
        List<RecognizePublicFaceRequest.RecognizePublicFaceRequestTask> taskList = listImage.stream().map(e -> new RecognizePublicFaceRequest.RecognizePublicFaceRequestTask().setImageData(e)).collect(Collectors.toList());
        return recognizePublicFaceWithOptions(taskList);
    }

    /**
     * oss 编码
     * @param listUrl
     * @return
     */
    public RecognizePublicFaceResponse recognizePublicFaceWithOptionsWithUrl(List<String> listUrl){
        List<RecognizePublicFaceRequest.RecognizePublicFaceRequestTask> taskList = listUrl.stream().map(e -> new RecognizePublicFaceRequest.RecognizePublicFaceRequestTask().setImageURL(e)).collect(Collectors.toList());
        return recognizePublicFaceWithOptions(taskList);
    }

    public RecognizePublicFaceResponse recognizePublicFaceWithOptions(List<RecognizePublicFaceRequest.RecognizePublicFaceRequestTask> list){
        RecognizePublicFaceRequest request = new RecognizePublicFaceRequest();
        request.setTask(list);
        return recognizePublicFaceWithOptions(request);
    }

    /**
     * 公众人物识别
     * 文件在本地或文件在同一地域OSS
     * <a href="https://help.aliyun.com/document_detail/478122.htm">表情识别</a>
     * @param request
     * @return
     */
    public RecognizePublicFaceResponse recognizePublicFaceWithOptions(RecognizePublicFaceRequest request) {
        try {
            return getClient().recognizePublicFaceWithOptions(request, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception teaException) {
            throw new RuntimeException(teaException);
        }
    }

    /**
     * 公众人物识别
     * 文件在本地或文件不在同一地域OSS
     * @param request
     * @return
     */
    public RecognizePublicFaceResponse recognizePublicFaceAdvance(RecognizePublicFaceAdvanceRequest request) {
        try {
            return getClient().recognizePublicFaceAdvance(request, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception teaException) {
            throw new RuntimeException(teaException);
        }
    }

    /**
     * 人脸表情识别
     * @param imageUrl
     * @return
     */
    public RecognizeExpressionResponse recognizeExpressionWithOptions(String imageUrl) {
        return recognizeExpressionWithOptions(new RecognizeExpressionRequest().setImageURL(imageUrl));
    }

    /**
     * 表情识别
     * <a href="https://help.aliyun.com/document_detail/155004.html">表情识别</a>
     * @param request
     * @return
     */
    public RecognizeExpressionResponse recognizeExpressionWithOptions(RecognizeExpressionRequest request) {
        try {
            return getClient().recognizeExpressionWithOptions(request, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception teaException) {
            throw new RuntimeException(teaException);
        }
    }

    /**
     * 人脸属性识别
     * <a href="https://help.aliyun.com/document_detail/151968.html">...</a>
     * @param request
     * @return
     */
    public RecognizeFaceResponse recognizeFaceWithOptions(RecognizeFaceRequest request) {
        try {
            return getClient().recognizeFaceWithOptions(request, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception teaException) {
            throw new RuntimeException(teaException);
        }
    }

    /**
     * 兼容老版本接口
     * @param imageUrl 图像URL地址。推荐使用上海地域的OSS链接，对于文件在本地或者非上海地域OSS链接的情况，请参见文件URL处理。
     * @param age 用来控制是否需要返回人脸年龄结果，取值true或false，默认false。
     * @param gender 用来控制是否需要返回人脸性别结果，取值true或false，默认false。
     * @param hat 用来控制是否需要返回人脸帽子结果，取值true或false，默认false。
     * @param glass 用来控制是否需要返回人脸眼镜结果，取值true或false，默认false。
     * @param beauty 用来控制是否需要返回人脸颜值结果，取值true或false，默认false。
     * @param expression 用来控制是否需要返回人脸表情结果，取值true或false，默认false。
     * @param mask 用来控制是否需要返回人脸口罩结果，取值true或false，默认false。
     * @param quality 用来控制是否需要返回人脸质量，取值true或false，默认false。
     * @param maxFaceNumber 用来设置图片中人脸的最大返回数量，取值范围1~10，默认为1。若想返回多个人脸检测结果，请正确设置。默认按返回参数FaceProbabilityList进行降序排列。
     */
    public RecognizeFaceResponse recognizeFaceWithOptions(String imageUrl, Boolean age, Boolean gender, Boolean hat, Boolean glass, Boolean beauty, Boolean expression, Boolean mask, Boolean quality, Long maxFaceNumber) {
        RecognizeFaceRequest recognizeFaceRequest = new RecognizeFaceRequest()
                .setImageURL(imageUrl)
                .setMaxFaceNumber(maxFaceNumber)
                .setQuality(quality)
                .setMask(mask)
                .setExpression(expression)
                .setBeauty(beauty)
                .setGlass(glass).setHat(hat).setGender(gender).setAge(age);
        return recognizeFaceWithOptions(recognizeFaceRequest);
    }

    public RecognizeFaceResponse recognizeFaceWithOptions(String imageUrl) {
        return recognizeFaceWithOptions(imageUrl, false, false, false, false, false, false, false, false, 1L);
    }

    /**
     * 人脸检测与五官定位
     * 
     * @param detectFaceRequest
     * @return
     */
    public DetectFaceResponse detectFaceWithOptions(DetectFaceRequest detectFaceRequest) {
        try {
            return getClient().detectFaceWithOptions(detectFaceRequest, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception teaException) {
            throw new RuntimeException(teaException);
        }
    }

    /**
     * <a href="https://help.aliyun.com/document_detail/151969.html">...</a>
     * 系统规定参数。取值：DetectFace。
     * 
     * @param imageUrl 图像URL地址。推荐使用上海地域的OSS链接，对于文件在本地或者非上海地域OSS链接的情况，请参见文件URL处理。
     * @param landmark 是否需要返回人脸的特征点定位，取值true或false。
     * @param maxFaceNumber 设置图片中人脸的最大返回数量，取值范围1~10。若想返回多个人脸检测结果，请正确设置。默认按返回参数FaceProbabilityList进行降序排列。
     * @param pose 是否需要返回人脸的姿态，取值true或false。
     * @param quality 是否需要返回人脸质量，取值true或false。
     */
    public DetectFaceResponse detectFaceWithOptions(String imageUrl, Boolean landmark, Long maxFaceNumber, Boolean pose, Boolean quality) {
        DetectFaceRequest detectFaceRequest = new DetectFaceRequest()
            .setImageURL(imageUrl)
            .setLandmark(landmark)
            .setMaxFaceNumber(maxFaceNumber)
            .setPose(pose)
            .setQuality(quality);
        return detectFaceWithOptions(detectFaceRequest);
    }

    public DetectFaceResponse detectFaceWithOptions(String imageURL, Boolean landmark, Long maxFaceNumber, Boolean pose) {
        return detectFaceWithOptions(imageURL, landmark, maxFaceNumber, pose, false);
    }

    public DetectFaceResponse detectFaceWithOptions(String imageURL, Boolean landmark, Long maxFaceNumber) {
        return detectFaceWithOptions(imageURL, landmark, maxFaceNumber, false, false);
    }

    public DetectFaceResponse detectFaceWithOptions(String imageURL, Boolean landmark) {
        return detectFaceWithOptions(imageURL, landmark, 1L, false, false);
    }

    public DetectFaceResponse detectFaceWithOptions(String imageURL) {
        return detectFaceWithOptions(imageURL, false, 1L, false, false);
    }
}
