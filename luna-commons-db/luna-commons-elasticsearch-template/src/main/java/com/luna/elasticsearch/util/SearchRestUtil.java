package com.luna.elasticsearch.util;

import com.luna.common.dto.constant.ResultCode;
import com.luna.common.utils.text.StringUtils;
import com.luna.elasticsearch.exception.ElasticsearchException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
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
import java.util.concurrent.TimeUnit;

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

    /**
     * 解析普通字段
     * 
     * @param searchResponse
     * @return
     */
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
     * 解析高亮字段
     * 
     * @param highlightField
     * @param searchResponse
     * @return
     */
    private List<Map<String, Object>> getMapsByHighLight(String highlightField, SearchResponse searchResponse) {
        SearchHit[] hits = searchResponse.getHits().getHits();
        List<Map<String, Object>> lists = new ArrayList<>();
        for (SearchHit hit : hits) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField field = highlightFields.get(highlightField);
            Map<String, Object> hitSourceAsMap = hit.getSourceAsMap();
            if (field != null) {
                StringBuilder newField = new StringBuilder(StringUtils.EMPTY);
                Text[] fragments = field.fragments();
                for (Text fragment : fragments) {
                    newField.append(fragment.toString());
                }
                hitSourceAsMap.put(highlightField, newField.toString());
            }
            lists.add(hitSourceAsMap);
        }
        return lists;
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

    /**
     * 为提供的字段名和文本创建一个带有“短语”类型的文本查询。
     *
     * @param index 索引
     * @param keyWord 搜索字段
     * @param keyValue 搜索字段值
     * @param pageNo 起始编号
     * @param pageSize 返回条目
     * @param highlightField 高亮字段
     * @return
     */
    public List<Map<String, Object>> searchByField(String index, String keyWord, Object keyValue, int pageNo,
        int pageSize, String highlightField) {
        if (StringUtils.isEmpty(index)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        try {
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder
                .query(QueryBuilders.matchPhraseQuery(keyWord, keyValue))
                .from(pageNo)
                .size(pageSize)
                .highlighter(getHighlightBuilder(highlightField));
            searchRequest.source(searchSourceBuilder);
            SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
            return getMapsByHighLight(highlightField, search);
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "通获取数据 {" + index + "} 失败" + e.getMessage());
        }
    }

    /**
     * 查询数据
     *
     * @param index 索引
     * @param timeOut 超时
     * @param keyWord 关键字段
     * @param keyValue 字段值
     * @param pageNo 启始编号
     * @param pageSize 返回条目
     * @param highlightField 高亮字段
     * @param notOnlyFirst 是否只需第一个匹配字段高亮
     * @param sortField 排序字段
     * @param sortType 排序方式 DESC 降序,ASC 升序
     * @param include 显示包含字段
     * @param exclude 显示排除字段
     * @return
     */
    public List<Map<String, Object>> searchByField(String index, Integer timeOut, String keyWord, Object keyValue,
        int pageNo,
        int pageSize, String highlightField, boolean notOnlyFirst, String sortField, String sortType, String include,
        String exclude) {
        if (StringUtils.isEmpty(index)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        try {
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.timeout(new TimeValue(timeOut, TimeUnit.SECONDS));
            searchSourceBuilder
                .query(QueryBuilders.matchPhraseQuery(keyWord, keyValue))
                .from(pageNo)
                .size(pageSize)
                .sort(sortField, SortOrder.fromString(sortType))
                .fetchSource(getFetchSourceContext(include, exclude))
                .highlighter(getHighlightBuilder(highlightField, notOnlyFirst));
            searchRequest.source(searchSourceBuilder);
            SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
            return getMapsByHighLight(highlightField, search);
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "通获取数据 {" + index + "} 失败" + e.getMessage());
        }
    }

    /**
     * 查询数据
     *
     * @param index 索引
     * @param timeOut 超时
     * @param keyWord 关键字段
     * @param keyValue 字段值
     * @param pageNo 启始编号
     * @param pageSize 返回条目
     * @param sortField 排序字段
     * @param sortType 排序方式 DESC 降序,ASC 升序
     * @param include 显示包含字段
     * @param exclude 显示排除字段
     * @return
     */
    public List<Map<String, Object>> searchByField(String index, Integer timeOut, String keyWord, Object keyValue,
        int pageNo,
        int pageSize, String sortField, String sortType, String include, String exclude) {
        if (StringUtils.isEmpty(index)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        try {
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.timeout(new TimeValue(timeOut, TimeUnit.SECONDS));
            searchSourceBuilder
                .query(QueryBuilders.matchPhraseQuery(keyWord, keyValue))
                .from(pageNo)
                .size(pageSize)
                .sort(sortField, SortOrder.fromString(sortType))
                .fetchSource(getFetchSourceContext(include, exclude));
            searchRequest.source(searchSourceBuilder);
            SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
            return getMaps(search);
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "通获取数据 {" + index + "} 失败" + e.getMessage());
        }
    }

    /**
     * 查询数据
     *
     * @param index 索引
     * @param timeOut 超时
     * @param keyWord 关键字段
     * @param keyValue 字段值
     * @param pageNo 启始编号
     * @param pageSize 返回条目
     * @param sortField 排序字段 默认使用降序
     * @param include 显示包含字段
     * @param exclude 显示排除字段
     * @return
     */
    public List<Map<String, Object>> searchByField(String index, Integer timeOut, String keyWord, Object keyValue,
        int pageNo,
        int pageSize, String sortField, String include, String exclude) {
        if (StringUtils.isEmpty(index)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        try {
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.timeout(new TimeValue(timeOut, TimeUnit.SECONDS));
            searchSourceBuilder
                .query(QueryBuilders.matchPhraseQuery(keyWord, keyValue))
                .from(pageNo)
                .size(pageSize)
                .sort(sortField, SortOrder.DESC)
                .fetchSource(getFetchSourceContext(include, exclude));
            searchRequest.source(searchSourceBuilder);
            SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
            return getMaps(search);
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "通获取数据 {" + index + "} 失败" + e.getMessage());
        }
    }

    /**
     * 查询数据
     *
     * @param index 索引
     * @param timeOut 超时
     * @param pageNo 启始编号
     * @param pageSize 返回条目
     * @param sortField 排序字段
     * @param sortType 排序方式 DESC 降序,ASC 升序
     * @param include 显示包含字段
     * @param exclude 显示排除字段
     * @return
     */
    public List<Map<String, Object>> searchByField(String index, Integer timeOut,
        int pageNo,
        int pageSize, String sortField, String sortType, String include, String exclude) {
        if (StringUtils.isEmpty(index)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        try {
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.timeout(new TimeValue(timeOut, TimeUnit.SECONDS));
            searchSourceBuilder
                .from(pageNo)
                .size(pageSize)
                .sort(sortField, SortOrder.fromString(sortType))
                .fetchSource(getFetchSourceContext(include, exclude));
            searchRequest.source(searchSourceBuilder);
            SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
            return getMaps(search);
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "通获取数据 {" + index + "} 失败" + e.getMessage());
        }
    }

    /**
     * 查询数据
     *
     * @param index 索引
     * @param timeOut 超时
     * @param sortField 排序字段
     * @param sortType 排序方式 DESC 降序,ASC 升序
     * @param include 显示包含字段
     * @param exclude 显示排除字段
     * @return
     */
    public List<Map<String, Object>> searchByField(String index, Integer timeOut, String sortField, String sortType,
        String include, String exclude) {
        if (StringUtils.isEmpty(index)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        try {
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.timeout(new TimeValue(timeOut, TimeUnit.SECONDS));
            searchSourceBuilder
                .sort(sortField, SortOrder.fromString(sortType))
                .fetchSource(getFetchSourceContext(include, exclude));
            searchRequest.source(searchSourceBuilder);
            SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
            return getMaps(search);
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "通获取数据 {" + index + "} 失败" + e.getMessage());
        }
    }

    /**
     * 设置高亮字段
     * 
     * @param highlightField
     * @return
     */
    public HighlightBuilder getHighlightBuilder(String highlightField, boolean notOnlyFirst) {
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        if (StringUtils.isNotEmpty(highlightField)) {
            // 多相同字段高亮关闭, 只高亮第一个字段
            highlightBuilder.field(highlightField);
            highlightBuilder.requireFieldMatch(notOnlyFirst);
            highlightBuilder.preTags("<span style='color:red'>");
            highlightBuilder.postTags("</span>");
        }
        return highlightBuilder;
    }

    /**
     * 设置高亮字段
     *
     * @param highlightField
     * @return
     */
    public HighlightBuilder getHighlightBuilder(String highlightField) {
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        if (StringUtils.isNotEmpty(highlightField)) {
            // 多相同字段高亮关闭, 只高亮第一个字段, 默认开启全部高亮
            highlightBuilder.field(highlightField);
            highlightBuilder.requireFieldMatch(true);
            highlightBuilder.preTags("<span style='color:red'>");
            highlightBuilder.postTags("</span>");
        }
        return highlightBuilder;
    }

    /**
     * 返回显示字段
     * 
     * @param include 包含字段
     * @param export 排除字段
     * @return
     */
    public FetchSourceContext getFetchSourceContext(String include, String export) {
        if (StringUtils.isEmpty(include) && StringUtils.isEmpty(export)) {
            return new FetchSourceContext(false);
        }
        return new FetchSourceContext(true, include.split(","), export.split(","));
    }

}
