package com.luna.api.email.exception;

import com.luna.common.exception.BaseException;

/**
 * 异常类
 * 
 * @author luna
 */
public class MessageException extends BaseException {

    public MessageException(int code, String message) {
        super(code, message);
    }
}
