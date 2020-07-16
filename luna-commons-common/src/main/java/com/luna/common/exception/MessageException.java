package com.luna.common.exception;

/**
 * 异常类
 * 
 * @author 15272
 *
 */
public class MessageException extends RuntimeException {

    private int    code;
    private String message;

    public MessageException() {
        super();
    }

    public MessageException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
