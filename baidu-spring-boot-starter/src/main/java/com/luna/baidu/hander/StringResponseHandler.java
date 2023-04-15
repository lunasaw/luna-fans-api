package com.luna.baidu.hander;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.HttpResponse;

import com.luna.common.net.HttpUtils;
import com.luna.common.net.hander.ValidatingResponseHandler;

import java.util.List;

/**
 * @author luna
 * @description
 * @date 2023/4/12
 */
@Data
@Slf4j
public class StringResponseHandler extends ValidatingResponseHandler<String> {

    private List<Integer> statusList;

    @Override
    public String handleResponse(HttpResponse httpResponse) {
        this.validateResponse(httpResponse);

        // 如果statusList不为空
        if (CollectionUtils.isNotEmpty(statusList)) {
            return HttpUtils.checkResponseAndGetResult(httpResponse, statusList);
        }
        return HttpUtils.checkResponseAndGetResult(httpResponse);
    }
}
