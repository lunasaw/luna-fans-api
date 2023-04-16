package com.luna.ali.oss;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Luna@win10
 * @date 2020/4/20 11:46
 */
@Component
@Slf4j
public class AliOssBucketApi {

    @Autowired
    private AliOssClientSupport aliOssClientSupport;

    /**
     * 创建对象存储空间
     *
     * @param bucketName
     * 只能包括小写字母、数字和短划线（-）。
     * 必须以小写字母或者数字开头和结尾。
     * 长度必须在3~63字节之间。
     */
    public void createBucket(String bucketName, String access, String type) {
        Assert.notNull(bucketName, "存储空间名称不能为空");

        // 创建CreateBucketRequest对象。
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);

        // 设置存储空间的权限为公共读，默认是私有。
        if (StringUtils.isEmpty(access)) {
            access = CannedAccessControlList.Private.toString();
        }
        createBucketRequest.setCannedACL(CannedAccessControlList.parse(access));

        // 设置存储空间的存储类型为低频访问类型，默认是标准类型。
        if (StringUtils.isEmpty(type)) {
            type = StorageClass.Standard.toString();
        }
        createBucketRequest.setStorageClass(StorageClass.parse(type));

        // 创建存储空间。
        aliOssClientSupport.getInstanceClient().createBucket(createBucketRequest);
    }

    /**
     * 列举所有的存储空间
     * 
     * @return
     */
    public List<Bucket> listBuckets(String prefix, Integer maxKeys, String marker) {
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
        BucketList bucketList = aliOssClientSupport.getInstanceClient().listBuckets(listBucketsRequest);
        List<Bucket> buckets = bucketList.getBucketList();

        // 关闭aliOssClientSupport.getInstanceClient()。
        aliOssClientSupport.getInstanceClient().shutdown();
        return buckets;
    }

    /**
     * 判断存储空间是否存在
     * 
     * @param bucketName
     * @return
     */
    public boolean isBucket(String bucketName) {
        Assert.notNull(bucketName, "存储空间名称不能为空");

        boolean exists = aliOssClientSupport.getInstanceClient().doesBucketExist(bucketName);

        // 关闭aliOssClientSupport.getInstanceClient()。
        aliOssClientSupport.getInstanceClient().shutdown();
        return exists;
    }

    /**
     * 获取存储空间的地域
     * 
     * @param bucketName
     * @return
     */
    public String getBucketRegion(String bucketName) {
        Assert.notNull(bucketName, "存储空间名称不能为空");

        String location = aliOssClientSupport.getInstanceClient().getBucketLocation(bucketName);

        // 关闭aliOssClientSupport.getInstanceClient()。
        aliOssClientSupport.getInstanceClient().shutdown();
        return location;
    }

    /**
     * 获取存储空间的信息
     * 
     * @param bucketName
     * @return
     */
    public BucketInfo getBucketInfo(String bucketName) {
        Assert.notNull(bucketName, "存储空间名称不能为空");

        // 存储空间的信息包括地域（Region或Location）、创建日期（CreationDate）、拥有者（Owner）、权限（Grants）等。
        BucketInfo info = aliOssClientSupport.getInstanceClient().getBucketInfo(bucketName);
        // 获取地域。
        String location = info.getBucket().getLocation();
        // 获取创建日期。
        Date creationDate = info.getBucket().getCreationDate();
        // 获取拥有者信息。
        Owner owner = info.getBucket().getOwner();
        // 获取容灾类型。
        DataRedundancyType dataRedundancyType = info.getDataRedundancyType();

        // 关闭aliOssClientSupport.getInstanceClient()。
        aliOssClientSupport.getInstanceClient().shutdown();
        return info;
    }

    /**
     * 设置存储空间访问权限
     * 
     * @param bucketName
     *
     * 私有 存储空间的拥有者和授权用户有该存储空间内的文件的读写权限，其他用户没有权限操作该存储空间内的文件。 CannedAccessControlList.Private
     * 公共读 存储空间的拥有者和授权用户有该存储空间内的文件的读写权限，其他用户只有该存储空间内的文件的读权限。请谨慎使用该权限。 CannedAccessControlList.PublicRead
     * 公共读写 所有用户都有该存储空间内的文件的读写权限。请谨慎使用该权限。 CannedAccessControlList.PublicReadWrite
     */
    public void setBucketAccess(String bucketName, String access) {
        Assert.notNull(access, "权限名称不能为空");

        // 设置存储空间的访问权限为私有。
        aliOssClientSupport.getInstanceClient().setBucketAcl(bucketName, CannedAccessControlList.parse(access));

        // 关闭aliOssClientSupport.getInstanceClient()。
        aliOssClientSupport.getInstanceClient().shutdown();
    }

    /**
     * 获取存储空间的访问权限
     *
     * @param bucketName
     * @return
     */
    public String getBucketAccess(String bucketName) {
        Assert.notNull(bucketName, "存储空间名称不能为空");

        // 获取存储空间的访问权限。
        AccessControlList acl = aliOssClientSupport.getInstanceClient().getBucketAcl(bucketName);

        // 关闭aliOssClientSupport.getInstanceClient()。
        aliOssClientSupport.getInstanceClient().shutdown();
        return acl.toString();
    }

    /**
     * 删除存储空间
     * 
     * @param bucketName
     */
    public void deleteBucket(String bucketName) {
        Assert.notNull(bucketName, "存储空间名称不能为空");

        // 删除存储空间。
        aliOssClientSupport.getInstanceClient().deleteBucket(bucketName);

        // 关闭aliOssClientSupport.getInstanceClient()。
        aliOssClientSupport.getInstanceClient().shutdown();
    }

    /**
     * 设置Bucket标签
     *
     * @param tags
     * @param bucketName
     */
    public void setBucketTag(Map<String, String> tags, String bucketName) {
        Assert.notNull(bucketName, "存储空间名称不能为空");

        // 设置Bucket标签。
        SetBucketTaggingRequest request = new SetBucketTaggingRequest(bucketName);

        Iterator<Map.Entry<String, String>> entries = tags.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            request.setTag(entry.getKey(), entry.getValue());
        }
        aliOssClientSupport.getInstanceClient().setBucketTagging(request);

        // 关闭aliOssClientSupport.getInstanceClient()。
        aliOssClientSupport.getInstanceClient().shutdown();
    }

    /**
     * 获取存储空间标签
     * 
     * @param bucketName
     * @return
     */
    public Map<String, String> getBucketTags(String bucketName) {
        Assert.notNull(bucketName, "存储空间名称不能为空");

        // 获取Bucket标签信息。
        TagSet tagSet = aliOssClientSupport.getInstanceClient().getBucketTagging(new GenericRequest(bucketName));
        Map<String, String> tags = tagSet.getAllTags();

        // 关闭aliOssClientSupport.getInstanceClient()。
        aliOssClientSupport.getInstanceClient().shutdown();
        return tags;
    }
}
