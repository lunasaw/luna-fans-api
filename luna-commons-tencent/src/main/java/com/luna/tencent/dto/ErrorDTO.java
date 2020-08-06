package com.luna.tencent.dto;

/**
 * @Package: com.luna.dto.dto
 * @ClassName: ErrorDTO
 * @Author: luna
 * @CreateTime: 2020/8/6 15:23
 * @Description:
 */
public class ErrorDTO {

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
