package com.luna.baidu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Package: com.luna.baidu.config
 * @ClassName: BaiduWriterConfigValue
 * @Author: luna
 * @CreateTime: 2020/8/10 16:56
 * @Description:
 */
@Component
public class BaiduWriterConfigValue {

    @Value("${luna.baidu.projectId}")
    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
