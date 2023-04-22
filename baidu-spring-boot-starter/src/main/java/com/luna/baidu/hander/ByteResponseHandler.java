package com.luna.baidu.hander;

import com.luna.common.net.HttpUtils;
import com.luna.common.net.hander.ValidatingResponseHandler;
import org.apache.hc.core5.http.ClassicHttpResponse;

/**
 * @author luna
 * @description
 * @date 2023/4/12
 */
public class ByteResponseHandler extends ValidatingResponseHandler<byte[]> {
    @Override
    public byte[] handleResponse(ClassicHttpResponse httpResponse) {
        this.validateResponse(httpResponse);
        return HttpUtils.checkResponseStreamAndGetResult(httpResponse);
    }
}
