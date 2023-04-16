//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.luna.ali.fileUrl;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyuncs.AcsRequest;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.kms.Endpoint;
import com.aliyuncs.transform.UnmarshallerContext;
import com.luna.ali.config.AliConfigProperties;
import com.luna.common.constant.StrPoolConstant;
import com.luna.common.file.FileNameUtil;
import com.luna.common.io.IoUtil;
import com.luna.common.net.HttpUtils;
import com.luna.common.text.CharsetUtil;
import com.luna.common.text.RandomStrUtil;

import com.aliyun.oss.OSSClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PreDestroy;

/**
 * 上传生成阿里云访问的私有地址 只有一天有效
 * 
 * @author weidian
 */
@Component
public class OssFileTools implements InitializingBean {
    private static final String ENDPOINT        = "http://oss-cn-shanghai.aliyuncs.com";
    private static final String ALIYUNCS_COM    = "http://viapi-customer-temp.oss-cn-shanghai.aliyuncs.com/";
    private static final String BUCKET_NAME     = "viapi-customer-temp";
    private static final String ENDPOINT_DOMAIN = "viapiutils.cn-shanghai.aliyuncs.com";

    @Autowired
    private AliConfigProperties aliConfigProperties;

    private OSSClient           ossClient;

    public String upload(String filePath) {
        String fileName = FileNameUtil.getName(filePath);
        InputStream ins = null;
        try {
            if (HttpUtils.isUrl(filePath)) {
                String decode = URLDecoder.decode(filePath, CharsetUtil.defaultCharsetName());
                URL url = new URL(decode);
                URLConnection urlConnection = url.openConnection();
                ins = urlConnection.getInputStream();
            } else {
                ins = IoUtil.toStream(filePath, Charset.defaultCharset());
            }
            return upload(fileName, ins);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IoUtil.close(ins);
        }
    }

    public String upload(String fileName, InputStream stream) {
        Assert.notNull(stream, "输入流不能为空");
        Assert.notNull(fileName, "文件名不能为空");
        try {
            String key = aliConfigProperties.getAccessKey() + StrPoolConstant.SLASH + RandomStrUtil.getUUID() + fileName;
            ossClient.putObject(BUCKET_NAME, key, stream);
            return ALIYUNCS_COM + key;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", aliConfigProperties.getAccessKey(), aliConfigProperties.getSecretKey());
        DefaultProfile.addEndpoint("cn-shanghai", "viapiutils", ENDPOINT_DOMAIN);
        GetOssStsTokenResponse acsResponse = new DefaultAcsClient(profile).getAcsResponse(new GetOssStsTokenRequest());
        DefaultCredentialProvider credentialProvider =
            new DefaultCredentialProvider(acsResponse.getAccessKeyId(), acsResponse.getAccessKeySecret(), acsResponse.getSecurityToken());
        ossClient = new OSSClient(ENDPOINT, credentialProvider, new ClientConfiguration());
    }

    @PreDestroy
    public void destroy() {
        if (ossClient != null) {
            ossClient.shutdown();
        }
    }

    @Data
    public static class GetOssStsTokenResponse extends AcsResponse {

        private String securityToken;

        private String accessKeyId;

        private String accessKeySecret;

        public GetOssStsTokenResponse getInstance(UnmarshallerContext context) {
            securityToken = context.stringValue("OssFileTools$GetOssStsTokenResponse.Data.SecurityToken");
            accessKeyId = context.stringValue("OssFileTools$GetOssStsTokenResponse.Data.AccessKeyId");
            accessKeySecret = context.stringValue("OssFileTools$GetOssStsTokenResponse.Data.AccessKeySecret");
            return this;
        }

    }

    public static class GetOssStsTokenRequest extends RpcAcsRequest<GetOssStsTokenResponse> {
        public GetOssStsTokenRequest() {
            super("viapiutils", "2020-04-01", "GetOssStsToken", "viapiutils");
            this.setMethod(MethodType.POST);

            try {
                AcsRequest.class.getDeclaredField("productEndpointMap").set(this, Endpoint.endpointMap);
                AcsRequest.class.getDeclaredField("productEndpointRegional").set(this, Endpoint.endpointRegionalType);
            } catch (Exception var2) {
            }

        }

        public Class<GetOssStsTokenResponse> getResponseClass() {
            return GetOssStsTokenResponse.class;
        }
    }
}
