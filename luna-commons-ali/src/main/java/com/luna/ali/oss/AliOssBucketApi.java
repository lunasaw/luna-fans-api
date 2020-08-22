package com.luna.ali.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import com.luna.ali.config.AliConfigValue;
import com.luna.common.utils.text.StringUtils;

import java.util.*;

/**
 * @Package: com.luna.ali.oss
 * @ClassName: AliOssBucketApi
 * @Author: luna
 * @CreateTime: 2020/8/21 21:54
 * @Description:
 */
public class AliOssBucketApi {

    /**
     * 创建对象存储空间
     *
     * @param bucketName
     * 只能包括小写字母、数字和短划线（-）。
     * 必须以小写字母或者数字开头和结尾。
     * 长度必须在3~63字节之间。
     * @param aliConfigValue
     */
    public void createBucket(String bucketName, String accress, String type, AliConfigValue aliConfigValue) {
        // 创建OSSClient实例。
        OSS ossClient = aliConfigValue.getOssClient(false);

        // 创建CreateBucketRequest对象。
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);

        // 设置存储空间的权限为公共读，默认是私有。
        if (StringUtils.isNotEmpty(accress)) {
            createBucketRequest.setCannedACL(CannedAccessControlList.parse(accress));
        }
        // 设置存储空间的存储类型为低频访问类型，默认是标准类型。
        if (StringUtils.isNotEmpty(type)) {
            createBucketRequest.setStorageClass(StorageClass.parse(type));
        }

        // 创建存储空间。
        ossClient.createBucket(createBucketRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 列举所有的存储空间
     * 
     * @param aliConfigValue
     * @return
     */
    public List<Bucket> listBuckets(String prefix, Integer maxKeys, String marker, AliConfigValue aliConfigValue) {
        // 创建OSSClient实例。
        OSS ossClient = aliConfigValue.getOssClient(false);

        // 列举存储空间。
        ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
        // 列举指定前缀的存储空间。
        if (StringUtils.isNotEmpty(prefix)) {
            listBucketsRequest.setPrefix(prefix);
        }
        // 限定此次列举存储空间的个数为500。默认值为100，最大值为1000。
        if (maxKeys != null) {
            listBucketsRequest.setMaxKeys(maxKeys);
        }
        // 列举指定marker之后的存储空间。
        if (StringUtils.isNotEmpty(marker)) {
            listBucketsRequest.setMarker(marker);
        }
        BucketList bucketList = ossClient.listBuckets(listBucketsRequest);
        List<Bucket> buckets = bucketList.getBucketList();

        // 关闭OSSClient。
        ossClient.shutdown();
        return buckets;
    }

    /**
     * 判断存储空间是否存在
     * 
     * @param bucketName
     * @param configValue
     * @return
     */
    public boolean isBucket(String bucketName, AliConfigValue configValue) {

        // 创建OSSClient实例。
        OSS ossClient = configValue.getOssClient(false);

        boolean exists = ossClient.doesBucketExist(bucketName);

        // 关闭OSSClient。
        ossClient.shutdown();
        return exists;
    }

    /**
     * 获取存储空间的地域
     * 
     * @param bucketName
     * @param configValue
     * @return
     */
    public String getBucketRegion(String bucketName, AliConfigValue configValue) {
        // 创建OSSClient实例。
        OSS ossClient = configValue.getOssClient(false);
        String location = ossClient.getBucketLocation(bucketName);

        // 关闭OSSClient。
        ossClient.shutdown();
        return location;
    }

    /**
     * 获取存储空间的信息
     * 
     * @param bucketName
     * @param configValue
     * @return
     */
    public BucketInfo getBucketInfo(String bucketName, AliConfigValue configValue) {
        // 创建OSSClient实例。
        OSS ossClient = configValue.getOssClient(false);

        // 存储空间的信息包括地域（Region或Location）、创建日期（CreationDate）、拥有者（Owner）、权限（Grants）等。
        BucketInfo info = ossClient.getBucketInfo(bucketName);
        // 获取地域。
        String location = info.getBucket().getLocation();
        // 获取创建日期。
        Date creationDate = info.getBucket().getCreationDate();
        // 获取拥有者信息。
        Owner owner = info.getBucket().getOwner();
        // 获取权限信息。
        Set<Grant> grants = info.getGrants();
        // 获取容灾类型。
        DataRedundancyType dataRedundancyType = info.getDataRedundancyType();

        // 关闭OSSClient。
        ossClient.shutdown();
        return info;
    }

    /**
     * 设置存储空间访问权限
     * 
     * @param bucketName
     * @param configValue
     * 
     * 私有 存储空间的拥有者和授权用户有该存储空间内的文件的读写权限，其他用户没有权限操作该存储空间内的文件。 CannedAccessControlList.Private
     * 公共读 存储空间的拥有者和授权用户有该存储空间内的文件的读写权限，其他用户只有该存储空间内的文件的读权限。请谨慎使用该权限。 CannedAccessControlList.PublicRead
     * 公共读写 所有用户都有该存储空间内的文件的读写权限。请谨慎使用该权限。 CannedAccessControlList.PublicReadWrite
     */
    public void setBucketAccess(String bucketName, String access, AliConfigValue configValue) {

        // 创建OSSClient实例。
        OSS ossClient = configValue.getOssClient(false);

        // 设置存储空间的访问权限为私有。
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.parse(access));

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 获取存储空间的访问权限
     *
     * @param bucketName
     * @param configValue
     * @return
     */
    public String getBucketAccess(String bucketName, AliConfigValue configValue) {
        // 创建OSSClient实例。
        OSS ossClient = configValue.getOssClient(false);
        // 获取存储空间的访问权限。
        AccessControlList acl = ossClient.getBucketAcl(bucketName);

        // 关闭OSSClient。
        ossClient.shutdown();
        return acl.toString();
    }

    /**
     * 删除存储空间
     * 
     * @param bucketName
     * @param configValue
     */
    public void deleteBucket(String bucketName, AliConfigValue configValue) {
        // 创建OSSClient实例。
        OSS ossClient = configValue.getOssClient(false);

        // 删除存储空间。
        ossClient.deleteBucket(bucketName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 设置Bucket标签
     *
     * @param tags
     * @param bucketName
     * @param configValue
     */
    public void setBucketTag(Map<String, String> tags, String bucketName, AliConfigValue configValue) {
        // 创建OSSClient实例。
        OSS ossClient = configValue.getOssClient(false);

        // 设置Bucket标签。
        SetBucketTaggingRequest request = new SetBucketTaggingRequest(bucketName);

        Iterator<Map.Entry<String, String>> entries = tags.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            request.setTag(entry.getKey(), entry.getValue());
        }
        ossClient.setBucketTagging(request);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 获取存储空间标签
     * 
     * @param bucketName
     * @param configValue
     * @return
     */
    public Map<String, String> getBucketTags(String bucketName, AliConfigValue configValue) {
        // 创建OSSClient实例。
        OSS ossClient = configValue.getOssClient(false);

        // 获取Bucket标签信息。
        TagSet tagSet = ossClient.getBucketTagging(new GenericRequest(bucketName));
        Map<String, String> tags = tagSet.getAllTags();

        // 关闭OSSClient。
        ossClient.shutdown();
        return tags;
    }
}
