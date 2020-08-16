package com.luna.api.xuexiaoyi.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Package: com.luna.api.xuexiaoyi.dto
 * @ClassName: XueXiaoYiResultDTO
 * @Author: luna
 * @CreateTime: 2020/8/16 20:39
 * @Description:
 */
public class XueXiaoYiResultDTO {

    @JSONField(name = "a")
    private String answer;

    @JSONField(name = "q")
    private String question;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
