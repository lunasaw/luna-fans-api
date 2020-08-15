package com.luna.tencent.dto.group;

import com.luna.tencent.dto.error.ErrorDTO;

/**
 * @Package: com.luna.tencent.dto.personGroup
 * @ClassName: CompareFaceResultDTO
 * @Author: luna
 * @CreateTime: 2020/8/14 23:28
 * @Description:
 */
public class CompareFaceResultDTO {

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
