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
public class HotWordDTO {

    @JSONField(name = "Word")
    private String  word;
    @JSONField(name = "Weight")
    private Integer weight;

    @Override
    public String toString() {
        return "HotWordDTO{" +
            "word='" + word + '\'' +
            ", weight=" + weight +
            '}';
    }

    public HotWordDTO() {}

    public HotWordDTO(String word, Integer weight) {
        this.word = word;
        this.weight = weight;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
