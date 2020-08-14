package com.luna.baidu.dto.write;

import java.util.List;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: HotEventContentDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 16:22
 * @Description:
 */
public class HotEventContentDTO {

    /** 新闻的url */
    private String       url;

    /** 新闻正文，按段落划分 */
    private List<String> content;

    /** 新闻发布时间，如：20190124 20:46:00 */
    private String       publish_time;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }
}
