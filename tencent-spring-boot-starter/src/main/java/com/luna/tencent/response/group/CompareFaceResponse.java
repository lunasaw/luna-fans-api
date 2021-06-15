package com.luna.tencent.response.group;

import com.luna.tencent.dto.error.ErrorDTO;

/**
 * @author luna
 * 2021/6/14
 */
public class CompareFaceResponse {

    private Float    score;

    private ErrorDTO error;

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public ErrorDTO getError() {
        return error;
    }

    public void setError(ErrorDTO error) {
        this.error = error;
    }
}
