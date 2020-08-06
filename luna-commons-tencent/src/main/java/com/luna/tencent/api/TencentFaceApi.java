package com.luna.tencent.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.luna.common.http.HttpUtils;
import com.luna.common.utils.Base64Util;
import com.luna.common.utils.StringUtils;
import com.luna.common.utils.img.ImageUtils;
import com.luna.tencent.dto.ErrorDTO;
import com.luna.tencent.dto.FaceInfosDTO;
import com.luna.tencent.dto.GroupExDescriptionInfoDTO;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Luna@win10
 * @date 2020/5/26 20:18
 */
public class TencentFaceApi {

    private static final Logger log = LoggerFactory.getLogger(TencentFaceApi.class);

    /**
     * 人脸检测
     * 
     * @param id
     * @param key
     * @param image
     * @return
     * @throws Exception
     */
    public static List<FaceInfosDTO> detectFace(String id, String key, String image, Integer maxFaceNum)
        throws Exception {
        HashMap<String, Object> map = Maps.newHashMap();
        if (HttpUtils.isNetUrl(image)) {
            map.put("Url", image);
        } else if (Base64Util.isBase64(image)) {
            map.put("Image", image);
        } else {
            map.put("Image", Base64Util.encodeBase64String(ImageUtils.getBytes(image)));
        }
        if (maxFaceNum != null) {
            map.put("MaxFaceNum", maxFaceNum);
        }
        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai", TencentConstant.FACE_CHECK, "", "DetectFace", "2018-03-01",
                body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, false);
        log.info("detectFace start id={}, key={}, response={}", id, key, s);
        List<JSONObject> response =
            JSON.parseArray(
                JSON.parseObject(JSON.parseObject(s, JSONObject.class).getString("Response")).getString("FaceInfos"),
                JSONObject.class);
        List<FaceInfosDTO> list = new ArrayList<>();
        for (JSONObject jsonObject : response) {
            FaceInfosDTO faceInfosDTO = jsonObject.toJavaObject(FaceInfosDTO.class);
            list.add(faceInfosDTO);
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
    public static void createFaceDatabase(String id, String key, String groupId, String groupName, String tag,
        List<String> groupExDescriptions)
        throws Exception {
        HashMap<String, Object> map = Maps.newHashMap();
        if (!groupExDescriptions.isEmpty()) {
            map.put("GroupExDescriptions", groupExDescriptions);
        }
        map.put("GroupName", groupName);
        map.put("GroupId", groupId);
        map.put("Tag", tag);
        map.put("FaceModelVersion", "3.0");
        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "CreateGroup", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        log.info("createFaceDatabase start id={}, key={}, response={}", id, key, s);
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
    public static void modifyFaceDatabase(String id, String key, String groupId, String groupName, String tag,
        List<GroupExDescriptionInfoDTO> groupExDescriptioninfo)
        throws Exception {
        HashMap<String, Object> map = Maps.newHashMap();
        if (!groupExDescriptioninfo.isEmpty()) {
            map.put("GroupExDescriptionInfos", groupExDescriptioninfo);
        }
        map.put("GroupName", groupName);
        map.put("GroupId", groupId);
        map.put("Tag", tag);
        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "ModifyGroup", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        log.info("modifyFaceDatabase start id={}, key={}, response={}", id, key, s);
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
    public static ErrorDTO deleteFaceDatabase(String id, String key, String groupId)
        throws Exception {
        String body = "{" + "\"GroupId\":\"" + groupId + "\"" + "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "DeleteGroup", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        log.info("deleteFaceDatabase start id={}, key={}, response={}", id, key, s);
        return JSON.parseObject(JSON.parseObject(s).getString("Response"), ErrorDTO.class);
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
     * @param image
     * @return
     * @throws Exception
     */
    public static ErrorDTO addFace(String id, String key, String groupId, String personId, String personName,
        Integer gender, String image)
        throws Exception {
        HashMap<String, Object> map = Maps.newHashMap();
        if (Base64Util.isBase64(image)) {
            map.put("Image", image);
        } else {
            map.put("Image", Base64Util.encodeBase64String(ImageUtils.getBytes(image)));
        }
        map.put("GroupId", groupId);
        map.put("Gender", gender);
        map.put("PersonName", personName);
        map.put("PersonId", personId);
        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "CreatePerson", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        log.info("addFace start id={}, key={}, response={}", id, key, s);
        return JSON.parseObject(JSON.parseObject(s).getString("Response"), ErrorDTO.class);
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
    public static ErrorDTO deleteFace2Group(String id, String key, String personId, String groupId)
        throws Exception {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("GroupId", groupId);
        map.put("PersonId", personId);
        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "DeletePersonFromGroup", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        log.info("deleteFace2Group start id={}, key={}, response={}", id, key, s);
        return JSON.parseObject(JSON.parseObject(s).getString("Response"), ErrorDTO.class);
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
    public static ErrorDTO deleteFace(String id, String key, String personId)
        throws Exception {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("PersonId", personId);
        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "DeletePerson", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        log.info("deleteFace start id={}, key={}, response={}", id, key, s);
        return JSON.parseObject(JSON.parseObject(s).getString("Response"), ErrorDTO.class);
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
    public static ErrorDTO getFace(String id, String key, String personId)
        throws Exception {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("PersonId", personId);
        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "GetPersonBaseInfo",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        log.info("getFace start id={}, key={}, response={}", id, key, s);
        return JSON.parseObject(JSON.parseObject(s).getString("Response"), ErrorDTO.class);
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
        Map<String, Object> map = Maps.newHashMap();
        map.put("Image", image64);
        map.put("GroupIds", groupIds);
        String body = JSONArray.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "SearchFacesReturnsByGroup",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        log.info("searchFaceByGroup start id={}, key={}, response={}", id, key, s);
        JSONObject response = JSON.parseObject(s);
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
     * @param image 人员照片
     * @return
     * @throws Exception
     */
    public static boolean getVerifyFaceByPersonId(String id, String key, String personId, String image)
        throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        if (Base64Util.isBase64(image)) {
            map.put("Image", image);
        } else {
            map.put("Image", Base64Util.encodeBase64String(ImageUtils.getBytes(image)));
        }
        map.put("PersonId", personId);
        String body = JSONArray.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "VerifyFace",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        log.info("getVerifyFaceByPersonId start id={}, key={}, response={}", id, key, s);
        JSONObject response = JSON.parseObject(s);
        return JSON.parseObject(response.getString("Response")).getBoolean("IsMatch");
    }

    /**
     * 人员验证
     * 会将4张 Face 的特征进行融合处理，生成对应这个 Person 的特征，使人员验证（确定待识别的人脸图片是某人员）更加准确。
     * 
     * @param id
     * @param key
     * @param personId
     * @param image
     * @return
     * @throws Exception
     */
    public static boolean getVerifyPersonByPersonId(String id, String key, String personId, String image)
        throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        if (Base64Util.isBase64(image)) {
            map.put("Image", image);
        } else {
            map.put("Image", Base64Util.encodeBase64String(ImageUtils.getBytes(image)));
        }
        map.put("PersonId", personId);
        String body = JSONArray.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "VerifyPerson",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        log.info("getVerifyPersonByPersonId start id={}, key={}, response={}", id, key, s);
        JSONObject response = JSON.parseObject(s);
        return JSON.parseObject(response.getString("Response")).getBoolean("IsMatch");
    }

    /**
     * 人脸对比
     *
     * @param id
     * @param key
     * @param imageA 生活照1
     * @param imageB 生活照2
     * @return
     * @throws Exception
     */
    public static String faceCheck(String id, String key, String imageA, String imageB)
        throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        if (Base64Util.isBase64(imageA)) {
            map.put("ImageA", imageA);
        } else if (HttpUtils.isNetUrl(imageA)) {
            map.put("UrlA", imageA);
        } else {
            map.put("ImageA", Base64Util.encodeBase64String(ImageUtils.getBytes(imageA)));
        }
        if (Base64Util.isBase64(imageB)) {
            map.put("ImageB", imageB);
        } else if (HttpUtils.isNetUrl(imageB)) {
            map.put("UrlB", imageB);
        } else {
            map.put("ImageB", Base64Util.encodeBase64String(ImageUtils.getBytes(imageB)));
        }
        String body = JSONArray.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "CompareFace", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);

        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        log.info("idNameCheck start id={}, key={}, response={}", id, key, s);
        String response = JSON.parseObject(s).getString("Response");
        ErrorDTO errorDTO = JSON.parseObject(JSON.parseObject(s).getString("Response"), ErrorDTO.class);
        if (StringUtils.isEmpty(errorDTO.getMessage())) {
            return JSON.parseObject(response).getString("Score");
        }
        return null;
    }

    /**
     * 活体检测
     * 
     * @param id
     * @param key
     * @param image
     * @return
     * @throws Exception
     */
    public static boolean faceLiveCheck(String id, String key, String image)
        throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        if (Base64Util.isBase64(image)) {
            map.put("Image", image);
        } else if (HttpUtils.isNetUrl(image)) {
            map.put("Url", image);
        } else {
            map.put("Image", Base64Util.encodeBase64String(ImageUtils.getBytes(image)));
        }
        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "DetectLiveFace", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        log.info("faceLiveCheck start id={}, key={}, response={}", id, key, s);
        JSONObject response = JSON.parseObject(s);
        return JSON.parseObject(response.getString("Response")).getBoolean("IsLiveness");
    }

}
