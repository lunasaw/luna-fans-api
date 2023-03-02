package com.luna.tencent.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.luna.common.encrypt.Base64Util;
import com.luna.common.file.FileTools;
import com.luna.common.net.HttpUtils;
import com.luna.tencent.response.group.AddFaceResultResponse;
import com.luna.tencent.response.group.CheckPersonInGroupResponse;
import com.luna.tencent.response.group.PersonBaseInfoResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.luna.tencent.dto.error.ErrorDTO;
import com.luna.tencent.dto.face.FaceInfosDTO;
import com.luna.tencent.dto.group.*;

/**
 * @author Luna@win10
 * @date 2020/5/26 20:18
 */
public class TencentGroupPersonApi {

    private static final Logger log = LoggerFactory.getLogger(TencentGroupPersonApi.class);

    /**
     * 人脸检测
     *
     * @param id
     * @param key
     * @param image 人脸照片 可为url base64 和图片地址
     * @param maxFaceNum 最多处理的人脸数目。默认值为1（仅检测图片中面积最大的那张人脸），最大值为120。
     * @param needFaceAttributes
     * @return
     * @throws Exception
     */
    public static List<FaceInfosDTO> detectFace(String id, String key, String image, Integer maxFaceNum,
        Integer needFaceAttributes) {
        HashMap<String, Object> map = Maps.newHashMap();
        if (HttpUtils.isUrl(image)) {
            map.put("Url", image);
        } else if (Base64Util.isBase64(image)) {
            map.put("Image", image);
        } else {
            map.put("Image", Base64Util.encodeBase64(FileTools.read(image)));
        }
        if (maxFaceNum != null) {
            map.put("MaxFaceNum", maxFaceNum);
        }
        if (needFaceAttributes != null) {
            map.put("NeedFaceAttributes", needFaceAttributes);
        }
        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai", TencentConstant.FACE_CHECK, "", "DetectFace", "2018-03-01",
                body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, false);
        List<FaceInfosDTO> list =
            JSON.parseArray(
                JSON.parseObject(JSON.parseObject(s, JSONObject.class).getString("Response")).getString("FaceInfos"),
                FaceInfosDTO.class);
        log.info("detectFace start id={}, key={}, response={}", id, key, JSON.toJSONString(list));
        return list;
    }

    /**
     * 创建人员库
     *
     * @param id
     * @param key
     * @param groupId 人员库 ID，不可修改，不可重复。支持英文、数字、-%@#&_，长度限制64B。
     * @param groupName 人员库名称，[1,60]个字符，可修改，不可重复。
     * @param tag 人员库信息备注，[0，40]个字符。
     * @param groupExDescriptions
     * 人员库自定义描述字段，用于描述人员库中人员属性，该人员库下所有人员将拥有此描述字段。
     * 最多可以创建5个。
     * 每个自定义描述字段支持[1,30]个字符。
     * 在同一人员库中自定义描述字段不可重复。
     * @return ErrorDTO 为null 则成功
     * @throws Exception
     */
    public static ErrorDTO createFaceDatabase(String id, String key, String groupId, String groupName, String tag,
        List<String> groupExDescriptions) {
        HashMap<String, Object> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(groupExDescriptions)) {
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
        ErrorDTO errorDTO = JSON.parseObject(s, ErrorDTO.class);
        log.info("createFaceDatabase success id={}, key={}, errorDTO={}", id, key, JSON.toJSONString(errorDTO));
        return errorDTO;
    }

    /**
     * 修改人员库
     *
     * @param id
     * @param key
     * @param groupId 人员库 ID，不可修改，不可重复。支持英文、数字、-%@#&_，长度限制64B。
     * @param groupName 人员库名称，[1,60]个字符，可修改，不可重复。
     * @param tag 人员库信息备注，[0，40]个字符。
     * @param groupExDescriptioninfo
     * 人员库自定义描述字段，用于描述人员库中人员属性，该人员库下所有人员将拥有此描述字段。
     * 最多可以创建5个。
     * 每个自定义描述字段支持[1,30]个字符。
     * 在同一人员库中自定义描述字段不可重复。
     * @return ErrorDTO 为null 则成功
     * @throws Exception
     */
    public static ErrorDTO modifyFaceDatabase(String id, String key, String groupId, String groupName, String tag,
        List<GroupExDescriptionInfoDTO> groupExDescriptioninfo) {
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
        ErrorDTO errorDTO = JSON.parseObject(s, ErrorDTO.class);
        log.info("modifyFaceDatabase success id={}, key={}, errorDTO={}", id, key, JSON.toJSONString(errorDTO));
        return errorDTO;
    }

    /**
     * 删除人员库
     * 
     * @param id
     * @param key
     * @param groupId 人员库Id
     * @return ErrorDTO 为空则成功
     * @throws Exception
     */
    public static ErrorDTO deleteFaceDatabase(String id, String key, String groupId) {
        String body = "{" + "\"GroupId\":\"" + groupId + "\"" + "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "DeleteGroup", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        ErrorDTO errorDTO = JSON.parseObject(s, ErrorDTO.class);
        log.info("deleteFaceDatabase success id={}, key={}, errorDTO={}", id, key, JSON.toJSONString(errorDTO));
        return errorDTO;
    }

    /**
     * 增加人员信息
     * 
     * @param id
     * @param key
     * @param groupId 待加入的人员库ID，取值为创建人员库接口中的GroupId
     * @param personId 人员ID，单个腾讯云账号下不可修改，不可重复。支持英文、数字、-%@#&_，长度限制64B。
     * @param personName 人员名称。[1，60]个字符，可修改，可重复。
     * @param gender 0代表未填写，1代表男性，2代表女性。
     * @param image
     * @param uniquePersonControl
     * 此参数用于控制判断 Image 或 Url 中图片包含的人脸，是否在人员库中已有疑似的同一人。
     * 如果判断为已有相同人在人员库中，则不会创建新的人员，返回疑似同一人的人员信息。
     * 如果判断没有，则完成创建人员。
     * 0: 不进行判断，无论是否有疑似同一人在库中均完成入库；
     * 1:较低的同一人判断要求（百一误识别率）；
     * 2: 一般的同一人判断要求（千一误识别率）；
     * 3: 较高的同一人判断要求（万一误识别率）；
     * 4: 很高的同一人判断要求（十万一误识别率）。
     * 默认 0。
     * @return
     * @throws Exception
     */
    public static AddFaceResultResponse addFace(String id, String key, String groupId, String personId,
        String personName,
        Integer gender, String image, Integer uniquePersonControl,
        List<PersonExDescriptionInfoDTO> personExDescriptionInfoDTOS) {
        HashMap<String, Object> map = Maps.newHashMap();
        if (Base64Util.isBase64(image)) {
            map.put("Image", image);
        } else if (HttpUtils.isUrl(image)) {
            map.put("Url", image);
        } else {
            map.put("Image", Base64Util.encodeBase64(FileTools.read(image)));
        }
        map.put("GroupId", groupId);
        map.put("Gender", gender);
        map.put("PersonName", personName);
        map.put("PersonId", personId);

        if (uniquePersonControl != null) {
            map.put("UniquePersonControl", uniquePersonControl);
        }

        if (CollectionUtils.isNotEmpty(personExDescriptionInfoDTOS)) {
            map.put("PersonExDescriptionInfos", personExDescriptionInfoDTOS);
        }

        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "CreatePerson", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        AddFaceResultResponse response =
            JSON.parseObject(JSON.parseObject(s).getString("Response"), AddFaceResultResponse.class);
        log.info("addFace success id={}, key={}, response={}", id, key, JSON.toJSONString(response));
        return response;
    }

    /**
     * 人员库中删除人员信息
     * 
     * @param id
     * @param key
     * @param personId
     * @param groupId
     * @return ErrorDTO 为null则成功
     * @throws Exception
     */
    public static ErrorDTO deleteFace2Group(String id, String key, String personId, String groupId) {
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
        ErrorDTO errorDTO = JSON.parseObject(s, ErrorDTO.class);
        log.info("deleteFace2Group success id={}, key={}, errorDTO={}", id, key, JSON.toJSONString(errorDTO));
        return errorDTO;
    }

    /**
     * 删除人员
     * 删除该人员信息，此操作会导致所有人员库均删除此人员。同时，该人员的所有人脸信息将被删除。
     * 
     * @param id
     * @param key
     * @param personId
     * @return ErrorDTO 为null则成功
     * @throws Exception
     */
    public static ErrorDTO deleteFace(String id, String key, String personId) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("PersonId", personId);
        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "DeletePerson", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        ErrorDTO errorDTO = JSON.parseObject(s, ErrorDTO.class);
        log.info("deleteFace2Group success id={}, key={}, errorDTO={}", id, key, JSON.toJSONString(errorDTO));
        return errorDTO;
    }

    /**
     * 获取人员基础信息
     * 
     * @param id
     * @param key
     * @param personId 人员id
     * @return ErrorDTO 为null则成功
     * @throws Exception
     */
    public static PersonBaseInfoResponse getFace(String id, String key, String personId) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("PersonId", personId);
        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "GetPersonBaseInfo", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        PersonBaseInfoResponse personBaseInfoResponse =
            JSON.parseObject(JSON.parseObject(s).getString("Response"), PersonBaseInfoResponse.class);
        log.info("deleteFace2Group success id={}, key={}, personBaseInfoResponse={}", id, key,
            JSON.toJSONString(personBaseInfoResponse));
        return personBaseInfoResponse;
    }

    /**
     * 返回算法模型认为最相像的人
     * 
     * @param id
     * @param key
     * @param image 人员图片
     * @param groupIds 人员组 array of String
     * @return
     * @throws Exception
     */
    public static CheckPersonInGroupResponse searchFaceByGroup(String id, String key, String image,
        List<String> groupIds) {
        Map<String, Object> map = Maps.newHashMap();
        if (Base64Util.isBase64(image)) {
            map.put("Image", image);
        } else if (HttpUtils.isUrl(image)) {
            map.put("Url", image);
        } else {
            map.put("Image", Base64Util.encodeBase64(FileTools.read(image)));
        }
        map.put("GroupIds", groupIds);
        String body = JSONArray.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "SearchFacesReturnsByGroup", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        CheckPersonInGroupResponse groupResultDTO =
            JSON.parseObject(JSON.parseObject(s).getString("Response"), CheckPersonInGroupResponse.class);
        log.info("searchFaceByGroup success id={}, key={}, groupResultDTO={}", id, key,
            JSON.toJSONString(groupResultDTO));
        return groupResultDTO;
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
    public static boolean getVerifyFaceByPersonId(String id, String key, String personId, String image) {
        Map<String, Object> map = Maps.newHashMap();
        if (Base64Util.isBase64(image)) {
            map.put("Image", image);
        } else if (HttpUtils.isUrl(image)) {
            map.put("Url", image);
        } else {
            map.put("Image", Base64Util.encodeBase64(FileTools.read(image)));
        }
        map.put("PersonId", personId);
        String body = JSONArray.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "VerifyFace", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        log.info("getVerifyFaceByPersonId success id={}, key={}, response={}", id, key, s);
        JSONObject response = JSON.parseObject(s);
        return JSON.parseObject(response.getString("Response")).getBooleanValue("IsMatch");
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
    public static boolean getVerifyPersonByPersonId(String id, String key, String personId, String image) {
        Map<String, Object> map = Maps.newHashMap();
        if (Base64Util.isBase64(image)) {
            map.put("Image", image);
        } else if (HttpUtils.isUrl(image)) {
            map.put("Url", image);
        } else {
            map.put("Image", Base64Util.encodeBase64(FileTools.read(image)));
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
        log.info("getVerifyPersonByPersonId success id={}, key={}, response={}", id, key, s);
        JSONObject response = JSON.parseObject(s);
        return JSON.parseObject(response.getString("Response")).getBooleanValue("IsMatch");
    }
}
