package com.luna.elasticsearch.util;

import com.luna.common.dto.constant.ResultCode;
import com.luna.common.utils.text.StringUtils;
import com.luna.elasticsearch.exception.ElasticsearchException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.luna.elasticsearch.util
 * @ClassName: SearchRestUtil
 * @Author: luna
 * @CreateTime: 2020/8/26 16:25
 * @Description:
 */
@Component
public class SearchRestUtil {

    private static final Logger log = LoggerFactory.getLogger(SearchRestUtil.class);

    @Autowired
    private RestHighLevelClient client;

    /**
     * 查找索引全部数据
     *
     * @param index elasticsearch index name
     * @return {@link SearchResponse}
     * @author fxbin
     */
    public List<Map<String, Object>> searchAll(String index) {
        if (StringUtils.isEmpty(index)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            List<Map<String, Object>> objects = getMaps(searchResponse);
            return objects;
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "通获取数据 {" + index + "} 失败" + e.getMessage());
        }
    }

    @NotNull
    private List<Map<String, Object>> getMaps(SearchResponse searchResponse) {
        SearchHit[] hits = searchResponse.getHits().getHits();
        List<Map<String, Object>> objects = new ArrayList<>();
        Arrays.stream(hits).forEach(hit -> {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            objects.add(sourceAsMap);
        });
        return objects;
    }

    /**
     * 精准匹配
     *
     * @param index
     * @param keyWord
     * @param keyValue
     * @return
     */
    public List<Map<String, Object>> searchByField(String index, String keyWord, Object keyValue, int pageNo,
        int pageSize) {
        if (StringUtils.isEmpty(index)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        try {
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder
                .query(QueryBuilders.matchPhraseQuery(keyWord, keyValue))
                .from(pageNo)
                .size(pageSize);
            searchRequest.source(searchSourceBuilder);
            SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
            return getMaps(search);
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "通获取数据 {" + index + "} 失败" + e.getMessage());
        }
    }

}
