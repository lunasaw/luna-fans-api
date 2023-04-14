package com.luna.baidu.hander;

import com.luna.common.net.HttpUtils;
import com.luna.common.net.hander.ValidatingResponseHandler;
import org.apache.http.HttpResponse;

/**
 * @author weidian
 * @description
 * @date 2023/4/12
 */
public class ByteResponseHandler extends ValidatingResponseHandler<byte[]> {
    @Override
    public byte[] handleResponse(HttpResponse httpResponse) {
        this.validateResponse(httpResponse);
        return HttpUtils.checkResponseStreamAndGetResult(httpResponse);
    }
}
