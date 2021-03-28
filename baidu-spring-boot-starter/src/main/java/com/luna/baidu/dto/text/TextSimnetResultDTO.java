package com.luna.baidu.dto.text;

/**
 * @Package: com.luna.baidu.dto.text
 * @ClassName: TextSimnetResulet
 * @Author: luna
 * @CreateTime: 2020/8/14 17:18
 * @Description:
 */
public class TextSimnetResultDTO {

    private TextSimilarityDTO texts;

    private Double            score;

    public TextSimilarityDTO getTexts() {
        return texts;
    }

    public void setTexts(TextSimilarityDTO texts) {
        this.texts = texts;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
