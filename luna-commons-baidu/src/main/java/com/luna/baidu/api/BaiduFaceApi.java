package com.luna.baidu.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.luna.baidu.entity.Face;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.base.BaseException;
import com.luna.common.http.HttpUtils;
import com.luna.common.http.HttpUtilsConstant;
import com.luna.common.utils.text.CharsetKit;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static List<Face> faceDetect(String key, String base64Str) {
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.FACE,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), ImmutableMap.of("access_token", key),
            JSON.toJSONString(ImmutableMap.of("image_type", "BASE64", "max_face_num", "10",
                "image", base64Str)));
        JSONObject response = HttpUtils.getResponse(httpResponse);
        List<JSONObject> datas = null;
        try {
            JSONObject jsonObject1 = JSON.parseObject(response.get("result").toString());
            datas = JSON.parseArray(jsonObject1.get("face_list").toString(), JSONObject.class);
        } catch (Exception e) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, response.toString());
        }
        List<Face> faces = new ArrayList<>();
        JSONObject jsonObject2 = null;
        for (JSONObject data : datas) {
            if (data.get("face_token") != null) {
                Face face = JSON.parseObject(data.get("location").toString(), Face.class);
                face.setFaceToken(data.get("face_token").toString());
                faces.add(face);
            }
        }
        return faces;
    }

    /**
     * 人脸对比
     * 
     * @param live 脸部生活照
     * @param idCard 身份证照片
     * @return 比较数值
     * @throws IOException
     */
    public static Double faceMathch(String key, String live, String idCard) {
        HashMap<String, String> liveParam = new HashMap<>();
        liveParam.put("image", live);
        liveParam.put("image_type", "BASE64");
        liveParam.put("face_type", "LIVE");

        HashMap<String, String> cardParam = new HashMap<>();
        cardParam.put("image", idCard);
        cardParam.put("image_type", "BASE64");
        cardParam.put("face_type", "LIVE");

        String str = "[" + JSON.toJSONString(liveParam) + "," + JSON.toJSONString(cardParam) + "]";
        HttpResponse httpResponse =
            HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.MATCH + "?access_token=" + key,
                ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), null,
                str);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        Double score = null;
        try {
            JSONObject jsonObject1 = JSON.parseObject(response.get("result").toString());
            score = Double.parseDouble(jsonObject1.get("score").toString());
        } catch (NumberFormatException e) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, response.toString());
        }
        return score;
    }

    /**
     * 单张活体检测
     * 
     * @param live
     * @return
     * @throws IOException
     */
    public static boolean checkLive(String key, String live) {
        HashMap<String, String> liveParam = new HashMap<>();
        liveParam.put("image", live);
        liveParam.put("image_type", "BASE64");
        liveParam.put("face_field", "age,beauty");
        liveParam.put("option", "COMMON");
        HttpResponse httpResponse =
            HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.LIVE + "?access_token=" + key,
                ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), null,
                "[" + JSON.toJSONString(liveParam) + "]");
        JSONObject response = HttpUtils.getResponse(httpResponse);

        boolean liveness = false;
        try {
            JSONObject jsonObject1 = JSON.parseObject(response.get("result").toString());
            liveness = Double.parseDouble(jsonObject1.get("face_liveness").toString()) > 0.995;
        } catch (NumberFormatException e) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, response.toString());
        }
        return liveness;
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
    public static Map<String, String> checIdCard(String key, String base64Str) throws UnsupportedEncodingException {
        String param = "id_card_side=" + "front" + "&image=" + URLEncoder.encode(base64Str, CharsetKit.UTF_8);
        HttpResponse httpResponse =
            HttpUtils.doPost(BaiduApiContent.HOST,
                BaiduApiContent.ID_OCR + "?access_token=" + key,
                ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED), null,
                param);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        Map<String, String> map = new HashMap<>();
        JSONObject jsonObject = JSON.parseObject(response.get("words_result").toString());
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
