//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.luna.tencent.dto.hotword;

import com.alibaba.fastjson2.annotation.JSONField;

/**
 * @author luna
 * 2021/6/14
 */
public class VocabDTO {
    @JSONField(name = "Name")
    private String       name;

    @JSONField(name = "Description")
    private String       description;

    @JSONField(name = "VocabId")
    private String       vocabId;

    @JSONField(name = "WordWeights")
    private HotWordDTO[] wordWeights;

    @JSONField(name = "CreateTime")
    private String       createTime;

    @JSONField(name = "UpdateTime")
    private String       updateTime;

    @JSONField(name = "State")
    private Long         state;

    @JSONField(name = "TagInfos")
    private String[]     tagInfos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVocabId() {
        return vocabId;
    }

    public void setVocabId(String vocabId) {
        this.vocabId = vocabId;
    }

    public HotWordDTO[] getWordWeights() {
        return wordWeights;
    }

    public void setWordWeights(HotWordDTO[] wordWeights) {
        this.wordWeights = wordWeights;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String[] getTagInfos() {
        return tagInfos;
    }

    public void setTagInfos(String[] tagInfos) {
        this.tagInfos = tagInfos;
    }
}
