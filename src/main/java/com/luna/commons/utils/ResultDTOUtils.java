package com.luna.commons.utils;

import com.luna.commons.dto.ResultDTO;

/**
 * @author Tony
 */
public class ResultDTOUtils {
    public static <T> T checkResultAndGetData(ResultDTO<T> resultDTO) {
        if (resultDTO.isSuccess() == false) {
            throw new RuntimeException("code=" + resultDTO.getCode() + ", message=" + resultDTO.getMessage());
        }
        return resultDTO.getData();
    }
}
