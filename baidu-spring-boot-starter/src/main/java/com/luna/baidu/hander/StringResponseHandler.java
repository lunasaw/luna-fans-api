package com.luna.baidu.hander;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpResponse;

import com.luna.common.net.HttpUtils;
import com.luna.common.net.hander.ValidatingResponseHandler;

import java.io.IOException;
import java.util.List;

/**
 * @author luna
 * @description
 * @date 2023/4/12
 */
@Data
@Slf4j
public class StringResponseHandler extends BasicHttpClientResponseHandler {

    private List<Integer> statusList;

    @Override
    public String handleResponse(ClassicHttpResponse response) throws IOException {
        // 如果statusList不为空
        if (CollectionUtils.isNotEmpty(statusList)) {
            return HttpUtils.checkResponseAndGetResult(response, statusList);
        }
        return HttpUtils.checkResponseAndGetResult(response);
    }
}
