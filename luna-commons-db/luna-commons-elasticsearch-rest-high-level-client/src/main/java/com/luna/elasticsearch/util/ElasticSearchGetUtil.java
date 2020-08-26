package com.luna.elasticsearch.util;

import com.google.common.collect.Lists;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.utils.text.StringUtils;
import com.luna.elasticsearch.dtp.EsPageDTO;
import com.luna.elasticsearch.exception.ElasticsearchException;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.luna.elasticsearch.util
 * @ClassName: ElasticSearchGetUtil
 * @Author: luna
 * @CreateTime: 2020/8/25 13:04
 * @Description:
 */
public class ElasticSearchGetUtil {

    private static final Logger log = LoggerFactory.getLogger(ElasticSearchGetUtil.class);

    /**
     * 构造数据请求
     *
     * @param index elasticsearch index name
     * @param id request object id
     * @param id
     * @return
     */
    public static GetRequest buildGetRequest(String index, String id, String includes) {
        if (StringUtils.isEmpty(index) || StringUtils.isEmpty(id)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, "index, id 不能为空");
        }

        if (StringUtils.isNotEmpty(includes)) {
            String[] includesField = includes.split(",");
            FetchSourceContext  fetchSourceContext = new FetchSourceContext(true, includesField, null);
            return new GetRequest(index).id(id).fetchSourceContext(fetchSourceContext);
        }
        return new GetRequest(index).id(id);
    }

    /**
     * 通过ID获取数据
     *
     * @param index 索引，类似数据库
     * @param id 数据ID
     * @param includes 需要显示的字段，逗号分隔（缺省为全部字段）
     * @return
     */
    public static Map<String, Object> searchDataById(String index, String id, String includes) {
        try {
            GetResponse response =
                ElasticsearchBase.client.get(buildGetRequest(index, id, includes), ElasticsearchBase.COMMON_OPTIONS);
            ElasticsearchBase.client.close();
            return response.getSourceAsMap();
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "通过ID获取数据 {" + index + "} 数据id {" + id + "} 失败 {" + includes + "}");
        }
    }

    /**
     * 查找索引全部数据
     *
     * @param index elasticsearch index name
     * @return {@link SearchResponse}
     * @author fxbin
     */
    public static List<Map<String, Object>> searchAll(String index) {
        if (StringUtils.isEmpty(index)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, "index 不能为空");
        }
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = ElasticsearchBase.client.search(searchRequest, ElasticsearchBase.COMMON_OPTIONS);
            ElasticsearchBase.client.close();
            SearchHit[] hits = searchResponse.getHits().getHits();
            List<Map<String, Object>> objects = new ArrayList<>();
            Arrays.stream(hits).forEach(hit -> {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                objects.add(sourceAsMap);
            });
            return objects;
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "通获取数据 {" + index + "} 失败" + e.getMessage());
        }
    }

    /**
     * 构建查找信息
     * 
     * @param startPage 开始页数
     * @param pageSize 每页条数
     * @param fields 查询字段
     * @param include 包含字段
     * @param export 排除字段
     * @param miniMactch 匹配百分比
     * @param sortField 排序字段
     * @param sortOrder 排序方式
     * @param size 数据量
     * @param query 查询条件
     * @param highlightField
     * @return
     */
    public static SearchSourceBuilder buildSearchSourceBuilder(int startPage, int pageSize, String fields,
        String include,
        String export, String miniMactch,
        String sortField, SortOrder sortOrder, Integer size, QueryBuilder query,
        String highlightField) {
        // 搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 排序
        if (StringUtils.isNotEmpty(sortField)) {
            if (sortOrder == null) {
                sortOrder = SortOrder.DESC;
            }
            searchSourceBuilder.sort(sortField, sortOrder);
        }
        if (size != null && size > 0) {
            searchSourceBuilder.size(size);
        }

        if (query != null) {
            searchSourceBuilder.query();
        }

        // 分页应用
        if (startPage > 0 || pageSize > 0) {
            searchSourceBuilder.from(startPage).size(pageSize);
        }

        // 搜索方式
        // multiMatchQuery(Object text, String... fieldNames)

        if (StringUtils.isNotEmpty(fields) && StringUtils.isNotEmpty(miniMactch)) {
            searchSourceBuilder.query(QueryBuilders.multiMatchQuery(fields.split(","))
                .minimumShouldMatch(miniMactch));
        }

        // 设置源字段过虑,第一个参数结果集包括哪些字段，第二个参数表示结果集不包括哪些字段
        if (StringUtils.isNotEmpty(include) || StringUtils.isNotEmpty(export)) {
            searchSourceBuilder.fetchSource(include.split(","), export.split(","));
        }

        if (StringUtils.isNotEmpty(highlightField)) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            // 设置高亮字段
            highlightBuilder.field(highlightField);
            searchSourceBuilder.highlighter(highlightBuilder);
        }
        return searchSourceBuilder;
    }

    /**
     * 使用分词查询,并分页
     *
     * @param index 索引名称
     * @param startPage 当前页
     * @param pageSize 每页显示条数
     * @param query 查询条件
     * @param fields 需要显示的字段，逗号分隔（缺省为全部字段）
     * @param sortField 排序字段
     * @param highlightField 高亮字段
     * @return
     */
    public static EsPageDTO searchDataPage(String index, int startPage, int pageSize, String fields,
        String include,
        String export,
        String miniMactch,
        String sortField, SortOrder sortOrder, Integer size, QueryBuilder query,
        String highlightField) {
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(
            buildSearchSourceBuilder(startPage, pageSize, fields, include, export, miniMactch, sortField, sortOrder,
                size,
                query, highlightField));
        try {
            // 执行搜索,向ES发起http请求
            SearchResponse searchResponse =
                ElasticsearchBase.client.search(searchRequest, ElasticsearchBase.COMMON_OPTIONS);
            ElasticsearchBase.client.close();
            // 搜索结果
            SearchHits hits = searchResponse.getHits();
            // 匹配到的总记录数
            TotalHits totalHits = hits.getTotalHits();
            List<Map<String, Object>> sourceList = setSearchResponse(searchResponse, highlightField);
            return new EsPageDTO(startPage, pageSize, Math.toIntExact(totalHits.value), sourceList);
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "使用分词查询,并分页失败" + e.getMessage());
        }
    }

    /**
     * 使用分词查询
     *
     * @param index 索引名称
     * @param query 查询条件
     * @param size 文档大小限制
     * @param fields 需要显示的字段，逗号分隔（缺省为全部字段）
     * @param sortField 排序字段
     * @param highlightField 高亮字段
     * @return
     */
    public static List<Map<String, Object>> searchListData(String index, QueryBuilder query, Integer size,
        String fields, String sortField, SortOrder sortOrder, String highlightField) {
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(
            buildSearchSourceBuilder(0, 0, fields, null, null, null, sortField, sortOrder, size,
                query, highlightField));
        try {
            // 执行搜索,向ES发起http请求
            SearchResponse searchResponse =
                ElasticsearchBase.client.search(searchRequest, ElasticsearchBase.COMMON_OPTIONS);
            // 搜索结果
            SearchHit[] hits = searchResponse.getHits().getHits();
            List<Map<String, Object>> list = Lists.newArrayList();
            Arrays.stream(hits).forEach(hit -> {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                list.add(sourceAsMap);
            });
            return setSearchResponse(searchResponse, highlightField);
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "使用分词查询" + e.getMessage());
        }
    }

    /**
     * 使用分词查询 不显示高亮
     *
     * @param index 索引名称
     * @param query 查询条件
     * @param size 文档大小限制
     * @param fields 需要显示的字段，逗号分隔（缺省为全部字段）
     * @param sortField 排序字段
     * @return
     */
    public static List<Map<String, Object>> searchListData(String index, QueryBuilder query, Integer size,
        String fields, String sortField, SortOrder sortOrder) {
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(
            buildSearchSourceBuilder(0, 0, fields, null, null, null, sortField, sortOrder, size,
                query, null));
        try {
            // 执行搜索,向ES发起http请求
            SearchResponse searchResponse =
                ElasticsearchBase.client.search(searchRequest, ElasticsearchBase.COMMON_OPTIONS);
            // 搜索结果
            SearchHit[] hits = searchResponse.getHits().getHits();
            List<Map<String, Object>> list = Lists.newArrayList();
            Arrays.stream(hits).forEach(hit -> {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                list.add(sourceAsMap);
            });
            return list;
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "使用分词查询" + e.getMessage());
        }
    }

    /**
     * 高亮结果集 特殊处理
     *
     * @param searchResponse
     * @param highlightField
     */
    private static List<Map<String, Object>> setSearchResponse(SearchResponse searchResponse, String highlightField) {
        List<Map<String, Object>> sourceList = new ArrayList<Map<String, Object>>();
        StringBuffer stringBuffer = new StringBuffer();
        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            searchHit.getSourceAsMap().put("id", searchHit.getId());
            if (StringUtils.isNotEmpty(highlightField)) {
                log.info("遍历 高亮结果集，覆盖 正常结果集:" + searchHit.getSourceAsMap());
                Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                Text[] text = searchHit.getHighlightFields().get(highlightField).getFragments();
                if (text != null) {
                    for (Text str : text) {
                        stringBuffer.append(str.string());
                    }
                    // 遍历 高亮结果集，覆盖 正常结果集
                    searchHit.getSourceAsMap().put(highlightField, stringBuffer.toString());
                }
            }
            sourceList.add(searchHit.getSourceAsMap());
        }
        return sourceList;
    }
}
