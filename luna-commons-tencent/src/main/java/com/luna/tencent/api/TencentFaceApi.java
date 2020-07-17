package com.luna.tencent.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.entity.Face;
import com.luna.common.exception.base.BaseException;
import com.luna.common.http.HttpUtils;
import org.apache.http.HttpResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Luna@win10
 * @date 2020/5/26 20:18
 */
public class TencentFaceApi {

    /**
     * 人脸检测
     * 
     * @param id
     * @param key
     * @param base64Str
     * @return
     * @throws Exception
     */
    public static List<Face> FaceCheck(String id, String key, String base64Str) throws Exception {
        String body = "{\n" +
            "    \"Image\":\"" + base64Str + "\",\n" +
            "    \"MaxFaceNum\":20\n" +
            "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "DetectFace",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        List<JSONObject> response1 =
            JSON.parseArray(JSON.parseObject(response.getString("Response")).getString("FaceInfos"), JSONObject.class);
        List<Face> list = new ArrayList<>();
        for (int i = 0; i < response1.size(); i++) {
            Face face = new Face();
            face.setLeft(Double.parseDouble(response1.get(i).getString("X")));
            face.setTop(Double.parseDouble(response1.get(i).getString("Y")));
            face.setWidth(Double.parseDouble(response1.get(i).getString("Width")));
            face.setHeight(Double.parseDouble(response1.get(i).getString("Height")));
            list.add(face);
        }
        return list;
    }

    /**
     * 创建人员库
     * 
     * @param id
     * @param key
     * @param groupId
     * @param groupName
     * @param tag
     * @return
     * @throws Exception
     */
    public static boolean createFaceDatabase(String id, String key, String groupId, String groupName, String tag)
        throws Exception {
        String body = "{\n" +
            "    \"GroupName\":\"" + groupName + "\",\n" +
            "    \"GroupId\":\"" + groupId + "\",\n" +
            "    \"Tag\":\"" + tag + "\",\n" +
            "    \"FaceModelVersion\":\"3.0\"\n" +
            "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "CreateGroup",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        System.out.println(response);
        boolean equals = "3.0".equals(JSON.parseObject(response.getString("Response")).getString("FaceModelVersion"));
        return equals;
    }

    /**
     * 修改人员库信息
     * 
     * @param id
     * @param key
     * @param groupId 人员库ID
     * @param groupName new人员库名称
     * @param tag new备注
     * @return
     * @throws Exception
     */
    public static boolean modifyFaceDatabase(String id, String key, String groupId, String groupName, String tag)
        throws Exception {
        String body = "{\n" +
            "    \"GroupName\":\"" + groupName + "\",\n" +
            "    \"GroupId\":\"" + groupId + "\",\n" +
            "    \"Tag\":\"" + tag + "\"\n" +
            "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "ModifyGroup",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        String string = JSON.parseObject(response.getString("Response")).getString("Error");
        if ("".equals(string)) {
            return true;
        } else {
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, string);
        }
    }

    /**
     * 删除人员库
     * 
     * @param id
     * @param key
     * @param groupId
     * @return
     * @throws Exception
     */
    public static boolean deleteFaceDatabase(String id, String key, String groupId)
        throws Exception {
        String body = "{\n" +
            "    \"GroupId\":\"" + groupId + "\"\n" +
            "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "DeleteGroup",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        System.out.println(response);
        return JSON.parseObject(response.getString("Response")).getString("Error") == null;
    }

    /**
     * 增加人员信息
     * 
     * @param id
     * @param key
     * @param groupId
     * @param personId
     * @param personName
     * @param gender
     * @param Image64
     * @return
     * @throws Exception
     */
    public static boolean addFace(String id, String key, String groupId, String personId, String personName,
        Integer gender, String Image64)
        throws Exception {
        String body = "{\n" +
            "    \"Image\":\"" + Image64 + "\",\n" +
            "    \"GroupId\":\"" + groupId + "\",\n" +
            "    \"Gender\":" + gender + ",\n" +
            "    \"PersonName\":\"" + personName + "\",\n" +
            "    \"PersonId\":\"" + personId + "\"\n" +
            "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "CreatePerson",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        return JSON.parseObject(response.getString("Response")).getString("Error") == null;
    }

    /**
     * 人员库中删除人员信息
     *
     * @param id
     * @param key
     * @param personId
     * @param groupId
     * @return
     * @throws Exception
     */
    public static boolean deleteFace2Group(String id, String key, String personId, String groupId)
        throws Exception {
        String body = "{\n" +
            "    \"PersonId\":\"" + personId + "\",\n" +
            "    \"GroupId\":\"" + groupId + "\"\n" +
            "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "DeletePersonFromGroup",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        return JSON.parseObject(response.getString("Response")).getString("Error") == null;
    }

    /**
     * 删除人员
     * 删除该人员信息，此操作会导致所有人员库均删除此人员。同时，该人员的所有人脸信息将被删除。
     * 
     * @param id
     * @param key
     * @param personId
     * @return
     * @throws Exception
     */
    public static boolean deleteFace(String id, String key, String personId)
        throws Exception {
        String body = "{\n" +
            "    \"PersonId\":\"" + personId + "\"\n" +
            "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "DeletePerson",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        return JSON.parseObject(response.getString("Response")).getString("Error") == null;
    }

    /**
     * 获取人员基础信息
     * 
     * @param id
     * @param key
     * @param personId 人员id
     * @return
     * @throws Exception
     */
    public static boolean getFace(String id, String key, String personId)
        throws Exception {
        String body = "{\n" +
            "    \"PersonId\":\"" + personId + "\"\n" +
            "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "GetPersonBaseInfo",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        return JSON.parseObject(response.getString("Response")).getString("Error") == null;
    }

    /**
     * 返回算法模型认为最相像的人
     * 
     * @param id
     * @param key
     * @param image64 人员图片
     * @param groupIds 人员组 array of String
     * @return
     * @throws Exception
     */
    public static String searchFaceByGroup(String id, String key, String image64, String[] groupIds)
        throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("Image", image64);
        map.put("GroupIds", groupIds);
        String body = JSONArray.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "SearchFacesReturnsByGroup",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        List<JSONObject> jsonArrays = JSON.parseArray(
            JSON.parseObject(response.getString("Response")).getString("ResultsReturnsByGroup"), JSONObject.class);
        String PersonId = "";
        for (int i = 0; i < jsonArrays.size(); i++) {
            List<JSONObject> groupCandidates = JSON.parseArray(
                JSON.parseObject(jsonArrays.get(i).toJSONString()).getString("GroupCandidates"), JSONObject.class);
            Double maxScore = 0.0;
            for (int j = 0; j < groupCandidates.size(); j++) {
                List<JSONObject> candidates =
                    JSON.parseArray(groupCandidates.get(j).get("Candidates").toString(), JSONObject.class);
                for (int i1 = 0; i1 < candidates.size(); i1++) {
                    String score = candidates.get(i1).getString("Score");
                    Double max = Math.max(maxScore, Double.parseDouble(score));
                    if (max > maxScore) {
                        PersonId = candidates.get(i1).getString("PersonId");
                        maxScore = max;
                    }
                }

            }
        }
        return PersonId;
    }

    /**
     * 检测该图像是否与该Id是否匹配
     * 人脸验证将该人员（Person）下的每个人脸（Face）都作为单独个体进行验证
     * 
     * @param id
     * @param key
     * @param personId 人员id
     * @param image64 人员照片
     * @return
     * @throws Exception
     */
    public static boolean getVerifyFaceByPersonId(String id, String key, String personId, String image64)
        throws Exception {
        String body = "{\n" +
            "    \"PersonId\":\"" + personId + "\",\n" +
            "    \"Image\":\"" + image64 + "\"\n" +
            "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "VerifyFace",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        return JSON.parseObject(response.getString("Response")).getBoolean("IsMatch");
    }

    /**
     * 人员验证
     * 会将4张 Face 的特征进行融合处理，生成对应这个 Person 的特征，使人员验证（确定待识别的人脸图片是某人员）更加准确。
     * 
     * @param id
     * @param key
     * @param personId
     * @param image64
     * @return
     * @throws Exception
     */
    public static boolean getVerifyPersonByPersonId(String id, String key, String personId, String image64)
        throws Exception {
        String body = "{\n" +
            "    \"PersonId\":\"" + personId + "\",\n" +
            "    \"Image\":\"" + image64 + "\"\n" +
            "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "VerifyPerson",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        return JSON.parseObject(response.getString("Response")).getBoolean("IsMatch");
    }

    /**
     * 人脸对比
     *
     * @param id
     * @param key
     * @param image641 生活照1
     * @param image642 生活照2
     * @return
     * @throws Exception
     */
    public static Double faceCheck(String id, String key, String image641, String image642)
        throws Exception {
        String body = "{\n" +
            "    \"ImageA\":\"" + image641 + "\",\n" +
            "    \"ImageB\":\"" + image642 + "\"\n" +
            "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "CompareFace",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        String string = JSON.parseObject(response.getString("Response")).getString("Error");
        if ("".equals(string)) {
            return Double.parseDouble(JSON.parseObject(response.getString("Response")).getString("Score"));
        } else {
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, string);
        }
    }

    /**
     * 活体检测
     * 
     * @param id
     * @param key
     * @param image64
     * @return
     * @throws Exception
     */
    public static boolean faceLiveCheck(String id, String key, String image64)
        throws Exception {
        String body = "{\n\"Image\":\"" + image64 + "\"}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "DetectLiveFace",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        return JSON.parseObject(response.getString("Response")).getBoolean("IsLiveness");
    }

}
