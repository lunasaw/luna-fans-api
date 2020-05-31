package com.luna.commons.exception.base;

/**
 * 异常类
 * 
 * @author 15272
 *
 */
public class BaseException extends RuntimeException {

    private int      code;
    private String   message;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    public BaseException() {
        super();
    }

    public BaseException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException(int code, String message, Object[] args) {
        this.code = code;
        this.message = message;
        this.args = args;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
