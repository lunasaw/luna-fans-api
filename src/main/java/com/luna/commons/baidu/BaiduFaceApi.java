package com.luna.commons.baidu;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luna.commons.baidu.entity.Face;
import com.luna.commons.http.HttpUtils;
import com.luna.commons.http.HttpUtilsConstant;
import com.luna.commons.utils.text.CharsetKit;
import org.apache.http.HttpResponse;

import com.google.common.collect.ImmutableMap;

/**
 * @author Luna@win10
 * @date 2020/4/20 11:46
 */
public class BaiduFaceApi {

    /**
     * 人脸识别Api 返回face_token
     * 
     * @param base64Str
     * @return List<Face>
     * @throws IOException
     */
    public static List<Face> faceDetect(String base64Str) {
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.FACE,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), null,
            ImmutableMap.of("access_token", BaiduApiContent.BAIDU_KEY, "image_type", "BASE64", "max_face_num", "10",
                "image", base64Str));
        JSONObject response = HttpUtils.getResponse(httpResponse);
        JSONObject jsonObject1 = JSON.parseObject(response.get("result").toString());
        List<JSONObject> datas = JSON.parseArray(jsonObject1.get("face_list").toString(), JSONObject.class);
        List<Face> faces = new ArrayList<>();
        JSONObject jsonObject2 = null;
        for (int i = 0; i < datas.size(); i++) {
            Face face = new Face();
            if (datas.get(i).get("face_token") != null) {
                face.setFaceToken(datas.get(i).get("face_token").toString());
                jsonObject2 = JSON.parseObject(datas.get(i).get("location").toString());
                face.setLeft(Double.parseDouble(jsonObject2.get("left").toString()));
                face.setHeight(Double.parseDouble(jsonObject2.get("height").toString()));
                face.setTop(Double.parseDouble(jsonObject2.get("top").toString()));
                face.setWidth(Double.parseDouble(jsonObject2.get("width").toString()));
                faces.add(face);
            }
        }
        return faces;
    }

    /**
     * 人脸对比
     * 
     * @param base64Str1 脸部生活照
     * @param base64Str2 身份证照片
     * @return 比较数值
     * @throws IOException
     */
    public static Double faceMathch(String base64Str1, String base64Str2) {
        String s = "[\n" +
            "  {\n" +
            "\t\t\"image\": \"" + base64Str1 + "\",\n" +
            "\t\t\"image_type\": \"BASE64\",\n" +
            "\t\t\"face_type\": \"LIVE\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"image\": \"" + base64Str2 + "\",\n" +
            "\t\t\"image_type\": \"BASE64\",\n" +
            "\t\t\"face_type\": \"IDCARD\"\n" +
            "\t}\n" +
            "]";
        HttpResponse httpResponse =
            HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.MATCH + "?access_token=" + BaiduApiContent.BAIDU_KEY,
                ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), null,
                s);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        JSONObject jsonObject1 = JSON.parseObject(response.get("result").toString());
        Double score = Double.parseDouble(jsonObject1.get("score").toString());
        return score;
    }

    /**
     * 单张活体检测
     * 
     * @param base64Str
     * @return
     * @throws IOException
     */
    public static boolean checkLive(String base64Str) {
        String s = "[{\n" +
            "\t\"image\": \"" + base64Str + "\",\n" +
            "\t\"image_type\": \"BASE64\",\n" +
            "\t\"face_field\": \"age,beauty\",\n" +
            "\t\"option\": \"COMMON\"\n" +
            "}]";
        HttpResponse httpResponse =
            HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.LIVE + "?access_token=" + BaiduApiContent.BAIDU_KEY,
                ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), null,
                s);
        JSONObject response = HttpUtils.getResponse(httpResponse);

        JSONObject jsonObject1 = JSON.parseObject(response.get("result").toString());
        return Double.parseDouble(jsonObject1.get("face_liveness").toString()) > 0.995;
    }

    /**
     * 人证审核
     * 
     * @param base64Str
     * @return
     * 用于校验身份证号码、性别、出生是否一致，输出结果及其对应关系如下：
     * -1: 身份证正面所有字段全为空
     * 0: 身份证证号识别错误
     * 1: 身份证证号和性别、出生信息一致
     * 2: 身份证证号和性别、出生信息都不一致
     * 3: 身份证证号和出生信息不一致
     * 4: 身份证证号和性别信息不一致
     * @throws IOException
     */
    public static Map<String, String> checIdCard(String base64Str) throws UnsupportedEncodingException {
        String param = "id_card_side=" + "front" + "&image=" + URLEncoder.encode(base64Str, CharsetKit.UTF_8);
        HttpResponse httpResponse =
            HttpUtils.doPost(BaiduApiContent.HOST,
                BaiduApiContent.ID_OCR + "?access_token=" + BaiduApiContent.BAIDU_KEY,
                ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED), null,
                param);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        Map<String, String> map = null;
        JSONObject jsonObject = JSON.parseObject(response.get("words_result").toString());
        map = new HashMap<>();
        String address = JSON.parseObject(jsonObject.get("住址").toString()).get("words").toString();
        map.put("address", address);
        String idCard = JSON.parseObject(jsonObject.get("公民身份号码").toString()).get("words").toString();
        map.put("idCard", idCard);
        String name = JSON.parseObject(jsonObject.get("姓名").toString()).get("words").toString();
        map.put("name", name);
        String nation = JSON.parseObject(jsonObject.get("民族").toString()).get("words").toString();
        map.put("nation", nation);
        String numberType = response.get("idcard_number_type").toString();
        map.put("numberType", numberType);
        return map;
    }
}
