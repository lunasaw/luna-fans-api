package io.github.lunasaw.ali;

import com.alibaba.fastjson.JSON;
import com.aliyun.facebody20191230.models.DetectFaceResponse;
import com.aliyun.facebody20191230.models.RecognizeExpressionResponse;
import com.aliyun.facebody20191230.models.RecognizeFaceResponse;
import com.luna.ali.face.AliFaceCheckApi;
import io.github.lunasaw.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author weidian
 * @description
 * @date 2023/4/15
 */
public class AliApiTest extends BaseTest {

    @Autowired
    private AliFaceCheckApi     aliFaceCheckApi;

    private static final String URL_GIRL = "https://viapi-test.oss-cn-shanghai.aliyuncs.com/viapi-3.0domepic/facebody/DetectFace/DetectFace1.png";

    private static final String URL_GLASS_MAIN = "http://viapi-test.oss-cn-shanghai.aliyuncs.com/viapi-3.0domepic/facebody/RecognizeFace/RecognizeFace1.png";

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
}
