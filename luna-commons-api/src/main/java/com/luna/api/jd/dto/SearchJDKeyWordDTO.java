package com.luna.api.jd.dto;

import javax.validation.constraints.NotBlank;

/**
 * @Package: com.luna.api.jd.dto
 * @ClassName: JDSearchDTO
 * @Author: luna
 * @CreateTime: 2020/8/28 21:42
 * @Description:
 */
public class SearchJDKeyWordDTO {

    /**
     *
     */
    @NotBlank(message = "字段不能为空")
    private String keyWord;

    /**
     *
     */
    @NotBlank(message = "字段值不能为空")
    private String keyValue;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }
}
