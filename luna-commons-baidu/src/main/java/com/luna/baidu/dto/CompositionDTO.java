package com.luna.baidu.dto;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: CompositionDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 16:45
 * @Description:
 */
public class CompositionDTO {

    /** 文章摘要 */
    private String   summary;

    /** 文章正文数组，每个数组元素代表一个段落，顺序为文章模板的段落顺序 */
    private String[] texts;

    /** 文章标题 */
    private String   title;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String[] getTexts() {
        return texts;
    }

    public void setTexts(String[] texts) {
        this.texts = texts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
