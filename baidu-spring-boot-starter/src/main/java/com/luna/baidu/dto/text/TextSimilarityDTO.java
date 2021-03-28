package com.luna.baidu.dto.text;

/**
 * @Package: com.luna.baidu.dto.text
 * @ClassName: TextSimilarityDTO
 * @Author: luna
 * @CreateTime: 2020/8/14 17:15
 * @Description:
 */
public class TextSimilarityDTO {

    private String text1;

    private String text2;

    private String model;

    public TextSimilarityDTO(String text1, String text2, String model) {
        this.text1 = text1;
        this.text2 = text2;
        this.model = model;
    }

    public TextSimilarityDTO(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
    }

    public TextSimilarityDTO() {}

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
