package com.luna.baidu.dto.write;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: WriterResultCheckDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 16:49
 * @Description:
 */
public class WriterResultCheckDTO {

    private String         error_code;

    private String         error_msg;

    private CompositionDTO result;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public CompositionDTO getResult() {
        return result;
    }

    public void setResult(CompositionDTO result) {
        this.result = result;
    }
}
