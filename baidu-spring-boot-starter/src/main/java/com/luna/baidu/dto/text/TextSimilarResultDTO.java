package com.luna.baidu.dto.text;

/**
 * @Package: com.luna.baidu.dto.text
 * @ClassName: TextSimilarDTO
 * @Author: luna
 * @CreateTime: 2020/8/14 18:43
 * @Description:
 */
public class TextSimilarResultDTO {

    private TextSimilarDTO words;

    private Double         score;

    public TextSimilarDTO getWords() {
        return words;
    }

    public void setWords(TextSimilarDTO words) {
        this.words = words;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
