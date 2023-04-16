package com.luna.ali.oss;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.google.common.collect.ImmutableMap;
import com.luna.ali.config.AliConfigProperties;
import com.luna.ali.oss.dto.OssSignDTO;
import com.luna.common.constant.StrPoolConstant;
import com.luna.common.date.DateUtils;
import com.luna.common.text.RandomStrUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author luna
 * 2022/5/22
 */
public class AliOssWebApi {

    private static final Long      MAX = 1048576000L;
    @Autowired
    private AliConfigProperties aliConfigProperties;

    public AliOssWebApi(OSS ossClient) {
        this.ossClient = ossClient;
    }

    private OSS                 ossClient;

    private static final Logger log = LoggerFactory.getLogger(AliOssUploadApi.class);

    /**
     * 获取签名
     *
     * @param expireTime 过期时间
     * @param dir 目录
     * @param objectName 文件名
     * @param callback 回调路径
     * @return
     */
    public OssSignDTO getPolicy(Long expireTime, String dir, String objectName, String callback) {
        OssSignDTO ossSignDTO = getPolicy(expireTime, dir, objectName, callback, MAX);
        log.info("getPolicy::expireTime = {}, dir = {}, ossSignDTO = {}", expireTime, dir, JSON.toJSONString(ossSignDTO));
        return ossSignDTO;
    }

    /**
     * 获取签名
     *
     * @param expireTime 过期时间
     * @param dir 目录
     * @param objectName 文件名
     * @param callback 回调数据 默认返回文件路径
     * @param max 最大大小
     * @return
     */
    public OssSignDTO getPolicy(Long expireTime, String dir, String objectName, String callback, Long max) {
        Map<String, Object> policy = getPolicy(expireTime, TimeUnit.SECONDS, dir, objectName, callback, max);
        OssSignDTO ossSignDTO = JSON.parseObject(JSON.toJSONString(policy), OssSignDTO.class);
        log.info("getPolicy::expireTime = {}, dir = {}, ossSignDTO = {}", expireTime, dir, JSON.toJSONString(objectName));
        return ossSignDTO;
    }

    /**
     * 获取签名
     *
     * @param expireTime 过期时间
     * @param dir 目录
     * @param objectName 文件名
     * @return
     */
    public OssSignDTO getPolicy(Long expireTime, String dir, String objectName) {
        OssSignDTO ossSignDTO = getPolicy(expireTime, dir, objectName, StringUtils.EMPTY, MAX);
        log.info("getPolicy::expireTime = {}, dir = {}, ossSignDTO = {}", expireTime, dir, JSON.toJSONString(ossSignDTO));
        return ossSignDTO;
    }

    /**
     * 获取签名
     *
     * @param expireTime 过期时间
     * @param timeUnit 单位
     * @param dir 目录
     * @param objectName 文件名
     * @param callback 回调数据 默认返回文件路径
     * @param max 最大大小
     * @return
     */
    public Map<String, Object> getPolicy(Long expireTime, TimeUnit timeUnit, String dir, String objectName, String callback, Long max) {
        log.info("getPolicy::expireTime = {}, dir = {}, objectName = {}", expireTime, dir, objectName);
        if (StringUtils.isEmpty(dir)) {
            dir += DateUtils.datePath() + "/";
        }

        if (!dir.endsWith(StrPoolConstant.SLASH)) {
            dir += "/";
        }

        objectName = RandomStrUtil.generateNonceStrWithUUID() + "_" + objectName;
        // https://bucketname.endpoint
        String host = "https://" + aliConfigProperties.getBucketName() + StrPoolConstant.DOT + aliConfigProperties.getEndpoint();
        if (StringUtils.isEmpty(callback)) {
            callback = JSON.toJSONString(ImmutableMap.of("url", host + "/" + dir + objectName));
        }

        if (Objects.isNull(max)) {
            max = MAX;
        }

        Map<String, Object> policy = getPolicy(expireTime, timeUnit, dir, max);

        policy.put("host", host);
        policy.put("objectName", objectName);
        policy.put("dir", dir);
        policy.put("accessKey", aliConfigProperties.getAccessKey());
        policy.put("callbackUrl", aliConfigProperties.getCallbackUrl());
        policy.put("enableCname", aliConfigProperties.getEnableCname());
        policy.put("domain", aliConfigProperties.getDomain());

        if (StringUtils.isNotEmpty(callback)) {
            String base64CallbackBody = BinaryUtil.toBase64String(callback.getBytes());
            policy.put("callback", base64CallbackBody);
        }

        log.info("getPolicy::expireTime = {}, dir = {}, policy = {}", expireTime, dir, JSON.toJSONString(policy));
        return policy;
    }

    /**
     * 签名
     *
     * @param expireTime 时间
     * @param timeUnit 单位
     * @param dir 目录
     * @param maxSize
     * @return
     */
    public Map<String, Object> getPolicy(Long expireTime, TimeUnit timeUnit, String dir, Long maxSize) {
        long expireEndTime = System.currentTimeMillis() + timeUnit.toMillis(expireTime);
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, MAX);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);

        Map<String, Object> respMap = new LinkedHashMap<>();
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));
        return respMap;
    }
}
