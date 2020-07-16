package com.luna.common.exception;

import com.luna.common.exception.base.BaseException;

/**
 * @author Luna@win10
 * @date 2020/6/7 20:24
 */
public class JavaCvException extends BaseException {

    public JavaCvException() {}

    public JavaCvException(int code, String message) {
        super(code, message);
    }

    public JavaCvException(int code, String message, Object[] args) {
        super(code, message, args);
    }
}
