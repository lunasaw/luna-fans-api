package com.luna.elasticsearch.exception;

import com.luna.common.exception.base.BaseException;

/**
 * ElasticsearchException
 *
 * @author fxbin
 * @version v1.0
 * @since 2019/8/26 1:53
 */
public class ElasticsearchException extends BaseException {

    public ElasticsearchException(int code, String message) {
        super(code, message);
    }

    public ElasticsearchException() {
    }

    public ElasticsearchException(int code, String message, Object[] args) {
        super(code, message, args);
    }
}
