package com.luna.elasticsearch.util;

import cn.hutool.core.bean.BeanUtil;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.utils.text.StringUtils;
import com.luna.elasticsearch.exception.ElasticsearchException;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @Package: com.luna.elasticsearch.util
 * @ClassName: ElasticSearchUpdateUtil
 * @Author: luna
 * @CreateTime: 2020/8/25 12:55
 * @Description:
 */
public class ElasticSearchUpdateUtil {

    /**
     * 更新请求
     *
     * @param index elasticsearch index name
     * @param id Document id
     * @param object request object
     * @author fxbin
     */
    public static void updateRequest(String index, String id, Object object) {
        if (StringUtils.isEmpty(index) || StringUtils.isEmpty(id)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, "index, id 不能为空");
        }
        try {
            UpdateRequest updateRequest =
                    new UpdateRequest(index, id).doc(BeanUtil.beanToMap(object), XContentType.JSON);
            ElasticsearchBase.client.update(updateRequest, ElasticsearchBase.COMMON_OPTIONS);
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                    "更新索引 {" + index + "} 数据 {" + object + "} 失败");
        }
    }
}
