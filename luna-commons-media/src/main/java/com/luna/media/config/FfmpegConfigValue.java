package com.luna.media.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Luna@win10
 * @date 2020/5/26 19:45
 */
@Component
@ConfigurationProperties(prefix = "luna.ffmpeg")
public class FfmpegConfigValue {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
