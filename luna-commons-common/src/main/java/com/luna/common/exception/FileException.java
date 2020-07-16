package com.luna.common.exception;

import com.luna.common.exception.base.BaseException;

/**
 * 文件信息异常类
 *
 * @author ruoyi
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(int code, String message, Object[] args) {
        super(code, message, args);
    }

    public FileException(int code, String message) {
        super(code, message);
    }
}
