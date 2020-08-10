package com.luna.baidu.dto;

import java.util.List;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: HotEventDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 16:20
 * @Description:
 */
public class HotEventDTO {

    /** 热点的pv数；pv为-1表示无法获取其pv的数据（如刚发布的高时效性新闻）如：8892 */
    private Integer                  pv;

    /** 检测到该热点的时间，时间格式(20190124 20:46:00) */
    private String                   ctime;

    /** 和该热点相关的新闻列表，包含新闻对应的url和发布时间 */
    private List<HotEventContentDTO> related_urls;

    /** 本数据计算更新的时间，时间格式(20190124 22:11:27) */
    private String                   mtime;

    /** 热点名称，如：越南欲冲击日本防线 */
    private String                   name;

    /** 热点摘要 */
    private String                   summary;

    /** 热点关键词，从name字段中抽出，如[“防线”, “越南”,”冲击”] */
    private List<String>             keywords;

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public List<HotEventContentDTO> getRelated_urls() {
        return related_urls;
    }

    public void setRelated_urls(List<HotEventContentDTO> related_urls) {
        this.related_urls = related_urls;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
