package io.github.lunasaw.ali;

import com.alibaba.fastjson2.JSON;
import com.aliyun.facebody20191230.models.DetectFaceResponse;
import com.aliyun.facebody20191230.models.RecognizeExpressionResponse;
import com.aliyun.facebody20191230.models.RecognizeFaceResponse;
import com.aliyun.facebody20191230.models.RecognizePublicFaceResponse;
import com.google.common.collect.Lists;
import com.luna.ali.face.api.AliFaceCheckApi;
import com.luna.ali.face.OssFileTools;
import io.github.lunasaw.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author luna
 * @description
 * @date 2023/4/15
 */
public class AliFaceApiTest extends BaseTest {

    @Autowired
    private AliFaceCheckApi     aliFaceCheckApi;

    @Autowired
    private OssFileTools        ossFileUtils;

    private static final String URL_GIRL       =
        "https://viapi-test.oss-cn-shanghai.aliyuncs.com/viapi-3.0domepic/facebody/DetectFace/DetectFace1.png";

    private static final String URL_GLASS_MAIN =
        "http://viapi-test.oss-cn-shanghai.aliyuncs.com/viapi-3.0domepic/facebody/RecognizeFace/RecognizeFace1.png";

    private static final String URL_TRUMP      =
        "https://viapi-test.oss-cn-shanghai.aliyuncs.com/test/facebody/RecognizePublicFace/u%3D2802364678%2C591996814%26fm%3D26%26gp%3D0.jpg";

    @Test
    public void test_local_file_check() {
        String upload = ossFileUtils.upload(URL_GLASS_MAIN);
        System.out.println(upload);
        String upload2 = ossFileUtils.upload(URL_GLASS_MAIN);
        System.out.println(upload2);
    }

    @Test
    public void test_check_face() {
        DetectFaceResponse detectFaceResponse = aliFaceCheckApi.detectFaceWithOptions(URL_GIRL);
        System.out.println(JSON.toJSONString(detectFaceResponse));
    }

    @Test
    public void test_check_face_attribute() {
        RecognizeFaceResponse recognizeFaceResponse = aliFaceCheckApi.recognizeFaceWithOptions(URL_GLASS_MAIN);
        System.out.println(JSON.toJSONString(recognizeFaceResponse));
    }

    @Test
    public void test_check_face_expression() {
        RecognizeExpressionResponse expressionResponse = aliFaceCheckApi.recognizeExpressionWithOptions(URL_GLASS_MAIN);
        System.out.println(JSON.toJSONString(expressionResponse));
    }

    @Test
    public void public_face_test() {
        RecognizePublicFaceResponse recognizePublicFaceResponse =
            aliFaceCheckApi.recognizePublicFaceWithOptionsWithData(Lists.newArrayList(URL_TRUMP));
        System.out.println(JSON.toJSONString(recognizePublicFaceResponse));
    }
}
