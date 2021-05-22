package com.luna.api.smms.api;

import com.alibaba.fastjson.JSON;
import com.luna.api.smms.config.SmMsConfigValue;
import com.luna.api.smms.dto.UploadResultDTO;
import com.luna.api.smms.dto.UserProfileDTO;
import com.luna.common.file.FileTools;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author luna@mac
 * @className UserApiFromFile.java
 * @description TODO
 * @createTime 2021年03月28日 13:40:00
 */
public class UserApiFromFile {

    private static SmMsConfigValue smMsConfigValue;

    public static SmMsConfigValue getInstance() {
        if (smMsConfigValue == null) {
            smMsConfigValue = new SmMsConfigValue();
        }
        return smMsConfigValue;
    }

    /**
     * 根据配置文件获取Token并设置Token
     * 
     * @param file
     */
    public static String getAuthToken(String file) {
        SmMsConfigValue configValue = checkFile(file);
        String authToken = UserApiFromString.getAuthToken(configValue.getUsername(), configValue.getPassword());
        smMsConfigValue.setAuthorizationCode(authToken);
        FileTools.write(JSON.toJSONString(smMsConfigValue), file);
        return authToken;
    }

    /**
     * 获取用户信息
     *
     * @param file
     * @return
     */
    public static UserProfileDTO getUserProfile(String file) {
        SmMsConfigValue smMsConfig = checkFile(file);
        return UserApiFromString.getUserProfile(smMsConfig.getAuthorizationCode());
    }

    /**
     * 上传文件
     * 
     * @param tokenFile token文件
     * @param path 需要上传的文件
     * @return
     */
    public static UploadResultDTO upload(String tokenFile, String path) {
        SmMsConfigValue smMsConfigValue = checkFile(tokenFile);
        return ImageApiFromString.upload(smMsConfigValue.getAuthorizationCode(), path);
    }

    /**
     * 获取最近上传历史
     *
     * @param tokenFile
     * @return
     */
    public static List<UploadResultDTO> getHistory(String tokenFile) {
        SmMsConfigValue smMsConfigValue = checkFile(tokenFile);
        return ImageApiFromString.getHistory(smMsConfigValue.getAuthorizationCode());
    }

    /**
     * 删除文件
     * 
     * @param tokenFile
     * @param hash
     * @return
     */
    public static boolean deleteFile(String tokenFile, String hash) {
        SmMsConfigValue smMsConfigValue = checkFile(tokenFile);
        return ImageApiFromString.deleteFile(smMsConfigValue.getAuthorizationCode(), hash);
    }

    /**
     * 获取所有上传记录
     *
     * @param tokenFile
     * @return
     */
    public static List<UploadResultDTO> getAllHistory(String tokenFile) {
        SmMsConfigValue smMsConfigValue = checkFile(tokenFile);
        return ImageApiFromString.getAllHistory(smMsConfigValue.getAuthorizationCode());
    }

    private static SmMsConfigValue checkFile(String file) {
        try {
            if (getInstance().getAuthorizationCode() == null) {
                String s = FileUtils.readFileToString(new File(file), Charset.defaultCharset());
                smMsConfigValue = JSON.parseObject(s, SmMsConfigValue.class);
            }
            return smMsConfigValue;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
