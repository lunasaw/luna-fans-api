package com.luna.elasticsearch.util;

import cn.hutool.core.bean.BeanUtil;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.utils.text.StringUtils;
import com.luna.elasticsearch.exception.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Package: com.luna.elasticsearch.util
 * @ClassName: IndexRestUtil
 * @Author: luna
 * @CreateTime: 2020/8/26 13:38
 * @Description:
 */
@Component
public class IndexRestUtil {

    private static final Logger log = LoggerFactory.getLogger(IndexRestUtil.class);

    @Autowired
    private RestHighLevelClient client;

    /**
     * 只创建索引
     * 
     * @param index
     * @return
     */
    public String createIndex(String index) {
        if (StringUtils.isEmpty(index)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        try {
            CreateIndexRequest request = new CreateIndexRequest(index);
            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
            log.info("createIndex response index={}", createIndexResponse.index());
            return createIndexResponse.index();
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "创建索引{" + index + "}失败" + e.getMessage());
        }
    }

    /**
     * 判断索引是否存在
     *
     * @param index
     * @return
     */
    public boolean isIndexExist(String index) {
        if (StringUtils.isEmpty(index)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        try {
            GetIndexRequest getIndexRequest = new GetIndexRequest(index);
            boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
            log.info("isIndexExist start index={}, exists={}", index, exists);
            return exists;
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION, "判断索引 {" + index + "} 失败");
        }
    }



    /**
     * 构造数据索引请求
     *
     * @param index elasticsearch index name
     * @param id request object id
     * @param source request object
     * @return {@link IndexRequest}
     * @author fxbin
     */
    public IndexRequest buildIndexRequest(String index, String id, Object source) {
        if (StringUtils.isEmpty(index) || StringUtils.isEmpty(id)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        return new IndexRequest(index).id(id).source(BeanUtil.beanToMap(source), XContentType.JSON);
    }

    /**
     * 数据添加，正定ID
     *
     * @param source 要增加的数据
     * @param index 索引，类似数据库
     * @param id 数据ID
     * @return
     */
    public String addData(Object source, String index, String id) {
        try {
            IndexResponse response = client.index(buildIndexRequest(index, id, source), RequestOptions.DEFAULT);
            log.info("addData response status:{},id:{}", response.status().getStatus(), response.getId());
            return response.getId();
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "新增数据失败" + e.getMessage());
        }
    }

}
