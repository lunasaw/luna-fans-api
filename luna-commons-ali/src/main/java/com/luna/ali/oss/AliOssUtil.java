package com.luna.ali.oss;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.File;
import java.net.URL;
import java.util.Date;

/**
 * @author Luna@win10
 * @date 2020/4/26 15:22
 */
public class AliOssUtil {

    /**
     * 上传
     * 
     * @param imagePath
     * @param name
     */
    public static void ossUpload(String imagePath, String name, String bucketName, OSS ossClient) {
        String objectName = name;
        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest =
            new PutObjectRequest(bucketName, objectName, new File(imagePath));

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);

        /** 上传文件 */
        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
        /** 保留返回tag */
        String eTag = putObjectResult.getETag();
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 视频截帧
     * 
     * @param name
     */
    public static void movie2Image(String name, String bucketName, OSS ossClient) {
        String objectName = name;
        // 设置视频截帧操作。
        String style = "video/snapshot,t_50000,f_jpg,w_1024,h_768";
        // 指定过期时间为10分钟。
        Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 10);
        GeneratePresignedUrlRequest req =
            new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        System.out.println(signedUrl);
        // 关闭OSSClient。
        ossClient.shutdown();
        ossClient.shutdown();
    }

    /**
     * oss 文件下载
     * 
     * @param file
     * @param name
     */
    public static void download(String file, String name, String bucketName, OSS ossClient) {

        String objectName = name;

        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(file));

        // 关闭OSSClient。
        ossClient.shutdown();

    }
}
