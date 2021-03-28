package com.luna.baidu.dto.write;

import java.util.List;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: EventKeyDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 16:34
 * @Description:
 */
public class EventKeyDTO {

    private String       title;

    private String       url;

    private String       summary;

    private List<String> content;

    private String       publishtime;

    private String       publishTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
}
