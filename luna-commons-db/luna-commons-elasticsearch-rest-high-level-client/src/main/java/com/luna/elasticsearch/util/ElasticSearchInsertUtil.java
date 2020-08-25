package com.luna.elasticsearch.util;

import cn.hutool.core.bean.BeanUtil;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.utils.text.RandomStrUtil;
import com.luna.common.utils.text.StringUtils;
import com.luna.elasticsearch.exception.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Package: com.luna.elasticsearch.util
 * @ClassName: ElasticSearchInsertUtil
 * @Author: luna
 * @CreateTime: 2020/8/25 12:56
 * @Description:
 */
public class ElasticSearchInsertUtil {

    private static final Logger log = LoggerFactory.getLogger(ElasticSearchInsertUtil.class);

    /**
     * 判断索引是否存在
     *
     * @param index
     * @return
     */
    public static boolean isIndexExist(String index) {
        try {
            GetIndexRequest getIndexRequest = new GetIndexRequest(index);
            log.info("isIndexExist start index={}", index);
            return ElasticsearchBase.client.indices().exists(getIndexRequest, ElasticsearchBase.COMMON_OPTIONS);
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION, "判断索引 {" + index + "} 失败");
        }
    }

    /**
     * 异步创建索引
     *
     * @param index elasticsearch index
     * @author fxbin
     */
    public static void createIndexRequest(String index) {
        try {
            CreateIndexRequest request = new CreateIndexRequest(index);
            // Settings for this index
            request.settings(
                Settings.builder()
                    .put("index.number_of_shards", ElasticsearchBase.properties.getIndex().getNumberOfShards())
                    .put("index.number_of_replicas", ElasticsearchBase.properties.getIndex().getNumberOfReplicas()));

            CreateIndexResponse createIndexResponse =
                ElasticsearchBase.client.indices().create(request, ElasticsearchBase.COMMON_OPTIONS);
            log.info(" whether all of the nodes have acknowledged the request : {}",
                createIndexResponse.isAcknowledged());
            log.info(
                " Indicates whether the requisite number of shard copies were started for each shard in the index before timing out :{}",
                createIndexResponse.isShardsAcknowledged());
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION, "创建索引 {" + index + "} 失败");
        }
    }

    /**
     * 构造索引请求
     *
     * @param index elasticsearch index name
     * @param id request object id
     * @param source request object
     * @return {@link IndexRequest}
     * @author fxbin
     */
    public static IndexRequest buildIndexRequest(String index, String id, Object source) {
        if (StringUtils.isEmpty(index) || StringUtils.isEmpty(id)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, "index, id 不能为空");
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
    public static String addData(Object source, String index, String id) {
        try {
            IndexResponse response = ElasticsearchBase.client.index(buildIndexRequest(index, id, source),
                ElasticsearchBase.COMMON_OPTIONS);
            log.info("addData response status:{},id:{}", response.status().getStatus(), response.getId());
            ElasticsearchBase.client.close();
            return response.getId();
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "新增索引 {" + index + "} 数据 {" + source + "} 失败");
        }
    }

    /**
     * 数据添加
     *
     * @param soucre 要增加的数据
     * @param index 索引，类似数据库
     * @return
     */
    public static String addData(Object soucre, String index) {
        return addData(soucre, index, RandomStrUtil.getUUID());
    }
}
