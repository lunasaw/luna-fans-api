package com.luna.elasticsearch.util;

import org.elasticsearch.index.query.*;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import com.luna.common.dto.constant.ResultCode;
import com.luna.common.utils.text.StringUtils;
import com.luna.elasticsearch.exception.ElasticsearchException;

/**
 * @Package: com.luna.elasticsearch.util
 * @ClassName: GetBuildersUtil
 * @Author: luna
 * @CreateTime: 2020/8/27 16:24
 * @Description:
 */
public class GetBuildersUtil {

    /**
     *
     * TermQueryBuilder
     *
     * 词条查询是ElasticSearch的一个简单查询。它仅匹配在给定字段中含有该词条的文档，而且是确切的、未经分析的词条。term查询会查找我们设定的准确值。term查询本身很简单，它接受一个字段名和我们希望查找的值。
     *
     * @param keyWord
     * @param keyValue
     * @return
     */
    public static TermQueryBuilder getTermQueryBuilder(String keyWord, Object keyValue) {
        if (StringUtils.isEmpty(keyWord) || keyValue != null) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        return QueryBuilders.termQuery(keyWord, keyValue);
    }

    /**
     * 匹配所有文档的查询。
     *
     * @return
     */
    public static MatchAllQueryBuilder getMatchAllQueryBuilder() {
        return QueryBuilders.matchAllQuery();
    }

    /**
     * 匹配字段的查询
     *
     * @param keyWord
     * @return
     */
    public static QueryStringQueryBuilder getQueryStringQueryBuilder(String keyWord) {
        if (StringUtils.isEmpty(keyWord) ) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        return QueryBuilders.queryStringQuery(keyWord);
    }

    /**
     * 查询分析文本并且从分析文本中创建一个phrase查询
     * 完整字段匹配
     *
     * @param keyWord
     * @param keyValue
     * @return
     */
    public static MatchPhraseQueryBuilder getMatchPhraseQueryBuilder(String keyWord, Object keyValue) {
        if (StringUtils.isEmpty(keyWord) ) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        return QueryBuilders.matchPhraseQuery(keyWord, keyValue);
    }

    /**
     * 返回显示字段
     *
     * @param include 包含字段
     * @param export 排除字段
     * @return
     */
    public static FetchSourceContext getFetchSourceContext(String include, String export) {
        if (StringUtils.isEmpty(include) && StringUtils.isEmpty(export)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        return new FetchSourceContext(true, include.split(","), export.split(","));
    }

    /**
     * 设置高亮字段
     *
     * @param highlightField
     * @return
     */
    public static HighlightBuilder getHighlightBuilder(String highlightField, boolean notOnlyFirst) {
        if (StringUtils.isEmpty(highlightField)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
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
}
