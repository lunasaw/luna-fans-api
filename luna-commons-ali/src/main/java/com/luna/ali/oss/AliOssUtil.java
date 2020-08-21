package com.luna.ali.oss;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.internal.OSSHeaders;
import com.aliyun.oss.model.*;
import com.luna.ali.config.AliConfigValue;
import com.luna.common.utils.text.RandomStrUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @author Luna@win10
 * @date 2020/4/26 15:22
 */
public class AliOssUtil {

    /**
     * 上传文件
     * 
     * @param imagePath
     * @param bucketName
     * @param imgFolder
     * @param configVale
     */
    public static String ossUpload(String imagePath, String bucketName, String imgFolder,
        AliConfigValue configVale) {
        // 创建PutObjectRequest对象。
        File file = new File(imagePath);
        String fileName = file.getName();
        if (!imgFolder.endsWith("/")) {
            imgFolder = imgFolder + "/";
        }
        fileName = System.currentTimeMillis() + "_" + RandomStrUtil.generateNonceStrWithUUID() + "_" + fileName;
        PutObjectRequest putObjectRequest =
            new PutObjectRequest(bucketName, imgFolder + fileName, file);

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        metadata.setObjectAcl(CannedAccessControlList.PublicRead);
        putObjectRequest.setMetadata(metadata);
        OSS ossClient = configVale.getOssClient(false);
        /** 上传文件 */
        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
        return configVale.getDomain() + "/" + imgFolder + file.getName();
    }

    /**
     * 创建自定义域名oss服务
     * 使用自定义域名时无法使用ossClient.listBuckets方法。
     * 
     * @param configVale
     * @return
     */
    public static OSS createOssWithCname(AliConfigValue configVale) {
        return configVale.getOssClient(true);
    }

    /**
     * 上传图片并返回图片的url地址的方法
     * 
     * @param img
     * @return
     * @throws Exception
     */
    public static String uploadImg(MultipartFile img, String imgFolder, String bucketName, AliConfigValue configVale)
        throws Exception {
        String fileName = img.getName();
        InputStream inputStream = img.getInputStream();
        // 创建OSSClient实例。 阿里云OSSapi
        OSS ossClient = configVale.getOssClient(false);
        fileName = System.currentTimeMillis() + "_" + RandomStrUtil.generateNonceStrWithUUID() + "_" + fileName;
        if (!imgFolder.endsWith("/")) {
            imgFolder = imgFolder + "/";
        }
        PutObjectResult putObjectResult = ossClient.putObject(bucketName, imgFolder + fileName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        return configVale.getDomain() + "/" + imgFolder + fileName;
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
