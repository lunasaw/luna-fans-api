package com.luna.elasticsearch.util;

import com.alibaba.fastjson.JSON;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.entity.Page;
import com.luna.common.utils.text.StringUtils;
import com.luna.elasticsearch.exception.ElasticsearchException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
    private RestHighLevelClient restHighLevelClient;

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
        try {
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
            searchRequest.source(searchSourceBuilder);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            List<Map<String, Object>> objects = SearchResultUtil.getMaps(searchResponse);
            return objects;
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "searchByField failed " + e.getMessage());
        }
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
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = GetBuildersUtil.getMatchPhraseQueryBuilder(keyWord, keyValue);
        return SearchResultUtil.getMaps(searchByField(index, null, matchPhraseQueryBuilder, pageNo, pageSize,
            null, null, null, null));
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
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = GetBuildersUtil.getMatchPhraseQueryBuilder(keyWord, keyValue);
        HighlightBuilder highlightBuilder = GetBuildersUtil.getHighlightBuilder(highlightField, true);
        return SearchResultUtil.getMapsByHighLight(highlightField,
            searchByField(index, null, matchPhraseQueryBuilder, pageNo, pageSize,
                null, null, null, highlightBuilder));
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
    public Page searchByField2Page(String index, String keyWord, Object keyValue, int pageNo,
        int pageSize, String highlightField) {
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = GetBuildersUtil.getMatchPhraseQueryBuilder(keyWord, keyValue);
        HighlightBuilder highlightBuilder = GetBuildersUtil.getHighlightBuilder(highlightField, true);
        SearchResponse searchResponse = searchByField(index, null, matchPhraseQueryBuilder, pageNo, pageSize,
            null, null, null, highlightBuilder);
        return SearchResultUtil.getMapsPageByHighLight(pageNo, pageSize, highlightField, searchResponse);
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
    public List<Map<String, Object>> searchPageByField(String index, String keyWord, Object keyValue, int pageNo,
        int pageSize, String highlightField) {
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = GetBuildersUtil.getMatchPhraseQueryBuilder(keyWord, keyValue);
        HighlightBuilder highlightBuilder = GetBuildersUtil.getHighlightBuilder(highlightField, true);
        return SearchResultUtil.getMapsByHighLight(highlightField,
            searchByField(index, null, matchPhraseQueryBuilder, pageNo, pageSize,
                null, null, null, highlightBuilder));
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
        FetchSourceContext fetchSourceContext = GetBuildersUtil.getFetchSourceContext(include, exclude);
        TermQueryBuilder termQueryBuilder = GetBuildersUtil.getTermQueryBuilder(keyWord, keyValue);
        HighlightBuilder highlightBuilder = GetBuildersUtil.getHighlightBuilder(highlightField, notOnlyFirst);
        return SearchResultUtil.getMapsByHighLight(highlightField,
            searchByField(index, timeOut, termQueryBuilder, pageNo, pageSize,
                sortField, sortType, fetchSourceContext, highlightBuilder));
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
        FetchSourceContext fetchSourceContext = GetBuildersUtil.getFetchSourceContext(include, exclude);
        TermQueryBuilder termQueryBuilder = GetBuildersUtil.getTermQueryBuilder(keyWord, keyValue);
        return SearchResultUtil.getMaps(searchByField(index, timeOut, termQueryBuilder, pageNo, pageSize,
            sortField, sortType, fetchSourceContext, null));
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
     * @return
     */
    public List<Map<String, Object>> searchByField(String index, Integer timeOut, String keyWord, Object keyValue,
        int pageNo, int pageSize, String sortField, String sortType) {
        TermQueryBuilder termQueryBuilder = GetBuildersUtil.getTermQueryBuilder(keyWord, keyValue);
        return SearchResultUtil.getMaps(searchByField(index, timeOut, termQueryBuilder, pageNo, pageSize,
            sortField, sortType, null, null));
    }

    /**
     * 查询数据
     *
     * @param index 索引
     * @param timeOut 超时
     * @param keyWord 关键字段
     * @param pageSize 返回条目
     * @param sortField 排序字段
     * @param sortType 排序方式 DESC 降序,ASC 升序
     * @return
     */
    public List<Map<String, Object>> searchByField(String index, Integer timeOut, String keyWord, int pageNo,
        int pageSize, String sortField, String sortType) {
        QueryStringQueryBuilder queryStringQueryBuilder = GetBuildersUtil.getQueryStringQueryBuilder(keyWord);
        return SearchResultUtil.getMaps(searchByField(index, timeOut, queryStringQueryBuilder, pageNo, pageSize,
            sortField, sortType, null, null));
    }

    /**
     * 查询数据 TermQueryBuilder
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
        TermQueryBuilder termQueryBuilder = GetBuildersUtil.getTermQueryBuilder(keyWord, keyValue);
        FetchSourceContext fetchSourceContext = GetBuildersUtil.getFetchSourceContext(include, exclude);
        return SearchResultUtil.getMaps(searchByField(index, timeOut, termQueryBuilder, pageNo, pageSize,
            sortField, null, fetchSourceContext, null));
    }

    /**
     * 查询数据 MatchAllQueryBuilder
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
        int pageNo, int pageSize, String sortField, String sortType, String include, String exclude) {
        FetchSourceContext fetchSourceContext = GetBuildersUtil.getFetchSourceContext(include, exclude);
        MatchAllQueryBuilder matchAllQueryBuilder = GetBuildersUtil.getMatchAllQueryBuilder();
        return SearchResultUtil.getMaps(searchByField(index, timeOut, matchAllQueryBuilder, pageNo, pageSize,
            sortField, sortType, fetchSourceContext, null));

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
        FetchSourceContext fetchSourceContext = GetBuildersUtil.getFetchSourceContext(include, exclude);
        MatchAllQueryBuilder matchAllQueryBuilder = GetBuildersUtil.getMatchAllQueryBuilder();
        return SearchResultUtil.getMaps(searchByField(index, timeOut, matchAllQueryBuilder, 0, 0,
            sortField, sortType, fetchSourceContext, null));
    }

    /**
     * 查询数据
     *
     * @param index 索引
     * @param timeOut 超时
     * @param sortField 排序字段
     * @param include 显示包含字段
     * @param exclude 显示排除字段
     * @return
     */
    public List<Map<String, Object>> searchByField(String index, Integer timeOut, String sortField, String include,
        String exclude) {
        FetchSourceContext fetchSourceContext = GetBuildersUtil.getFetchSourceContext(include, exclude);
        MatchAllQueryBuilder matchAllQueryBuilder = GetBuildersUtil.getMatchAllQueryBuilder();
        return SearchResultUtil.getMaps(searchByField(index, timeOut, matchAllQueryBuilder, 0, 0,
            sortField, null, fetchSourceContext, null));
    }

    /**
     * 查询数据
     *
     * @param index 索引
     * @param timeOut 超时
     * @param include 显示包含字段
     * @param exclude 显示排除字段
     *
     * @return
     */
    public List<Map<String, Object>> searchByField(String index, Integer timeOut, String include, String exclude) {
        FetchSourceContext fetchSourceContext = GetBuildersUtil.getFetchSourceContext(include, exclude);
        MatchAllQueryBuilder matchAllQueryBuilder = GetBuildersUtil.getMatchAllQueryBuilder();
        return SearchResultUtil.getMaps(
            searchByField(index, timeOut, matchAllQueryBuilder, 0, 0, null, null, fetchSourceContext, null));
    }

    /**
     *
     * 查询数据
     *
     * @param index 索引
     * @param timeOut 超时
     * @param queryBuilder 查询方式
     * @param pageNo 启始编号
     * @param pageSize 返回条目
     * @param sortField 排序字段
     * @param sortType 排序方式 DESC 降序,ASC 升序
     * @param highlightBuilder 高亮字段 是否仅首个匹配字段高亮 true: 匹配字段均高亮 false: 仅首个匹配字段高亮
     * @param fetchSourceContext 排除或包含那些字段显示
     * @return
     */
    public SearchResponse searchByField(String index, Integer timeOut, QueryBuilder queryBuilder,
        int pageNo, int pageSize, String sortField, String sortType,
        FetchSourceContext fetchSourceContext, HighlightBuilder highlightBuilder) {
        if (StringUtils.isEmpty(index)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        try {
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            if (timeOut != null && timeOut > 0) {
                searchSourceBuilder.timeout(new TimeValue(timeOut, TimeUnit.SECONDS));
            } else {
                searchSourceBuilder.timeout(new TimeValue(1, TimeUnit.SECONDS));
            }

            if (queryBuilder != null) {
                searchSourceBuilder.query(queryBuilder);
            }

            if (pageNo < 0) {
                pageNo = 0;
                searchSourceBuilder.from(pageNo);
            } else {
                searchSourceBuilder.from(pageNo);
            }

            if (pageSize <= 0) {
                pageSize = 10;
                searchSourceBuilder.size(pageSize);
            } else {
                searchSourceBuilder.size(pageSize);
            }

            if (StringUtils.isNotEmpty(sortField)) {
                if (StringUtils.isNotEmpty(sortType)) {
                    searchSourceBuilder.sort(sortField, SortOrder.fromString(sortType));
                }
                searchSourceBuilder.sort(sortField, SortOrder.DESC);
            }

            if (fetchSourceContext != null) {
                searchSourceBuilder.fetchSource(fetchSourceContext);
            }

            if (highlightBuilder != null) {
                searchSourceBuilder.highlighter(highlightBuilder);
            }

            searchRequest.source(searchSourceBuilder);
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            log.info(
                "searchByField success  index={},  timeOut={},  queryBuilder={},  pageNo={},  pageSize={}, sortField={},  sortType={},  highlightField={},"
                    + " fetchSourceContext={}",
                index, timeOut, JSON.toJSONString(queryBuilder), pageNo, pageSize, sortField, sortType,
                JSON.toJSONString(highlightBuilder),
                JSON.toJSONString(fetchSourceContext));
            return search;
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "searchByField failed " + e.getMessage());
        }
    }
}
