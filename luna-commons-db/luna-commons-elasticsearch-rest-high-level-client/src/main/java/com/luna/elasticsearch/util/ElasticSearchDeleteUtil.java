package com.luna.elasticsearch.util;

import com.luna.common.dto.constant.ResultCode;
import com.luna.common.utils.text.StringUtils;
import com.luna.elasticsearch.exception.ElasticsearchException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Package: com.luna.elasticsearch.util
 * @ClassName: ElasticSearchDeleteUtil
 * @Author: luna
 * @CreateTime: 2020/8/25 12:54
 * @Description:
 */
public class ElasticSearchDeleteUtil {

    private static final Logger log = LoggerFactory.getLogger(ElasticSearchDeleteUtil.class);

    /**
     * 删除索引
     *
     * @param index elasticsearch index name
     * @author fxbin
     */
    public static void deleteIndexRequest(String index) {
        DeleteIndexRequest deleteIndexRequest = buildDeleteIndexRequest(index);
        try {
            ElasticsearchBase.client.indices().delete(deleteIndexRequest, ElasticsearchBase.COMMON_OPTIONS);
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION, "删除索引 {" + index + "} 失败");
        }
    }

    /**
     * 构造删除索引请求
     *
     * @param index elasticsearch index name
     * @author fxbin
     */
    public static DeleteIndexRequest buildDeleteIndexRequest(String index) {
        if (StringUtils.isEmpty(index)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, "index不能为空");
        }
        return new DeleteIndexRequest(index);
    }

    /**
     * 删除数据请求
     *
     * @param index elasticsearch index name
     * @param id Document id
     * @author fxbin
     */
    public static void deleteRequest(String index, String id) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest(index, id);
            DeleteResponse response = ElasticsearchBase.client.delete(deleteRequest, ElasticsearchBase.COMMON_OPTIONS);
            log.info("deleteDataById response status:{},id:{}", response.status().getStatus(), response.getId());
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "删除索引 {" + index + "} 数据id {" + id + "} 失败");
        }
    }
}
