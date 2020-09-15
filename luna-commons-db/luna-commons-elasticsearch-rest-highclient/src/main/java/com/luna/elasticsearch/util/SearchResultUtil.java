package com.luna.elasticsearch.util;

import com.luna.common.entity.Page;
import com.luna.common.utils.text.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.luna.elasticsearch.util
 * @ClassName: SearchByPageUtil
 * @Author: luna
 * @CreateTime: 2020/8/27 17:35
 * @Description:
 */
public class SearchResultUtil {

    /**
     * 解析普通字段
     *
     * @param searchResponse
     * @return
     */
    public static List<Map<String, Object>> getMaps(SearchResponse searchResponse) {
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
    public static List<Map<String, Object>> getMapsByHighLight(String highlightField, SearchResponse searchResponse) {
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
     * 解析高亮字段并分页显示
     *
     * @param pageNo
     * @param pageSize
     * @param highlightField
     * @param searchResponse
     * @return
     */
    public static Page getMapsPageByHighLight(Integer pageNo, Integer pageSize, String highlightField,
        SearchResponse searchResponse) {
        int i = Math.toIntExact(searchResponse.getHits().getTotalHits().value);
        List<Map<String, Object>> mapsByHighLight = getMapsByHighLight(highlightField, searchResponse);
        return new Page(pageNo, pageSize, i, mapsByHighLight);
    }

    /**
     * 解析普通字段并分页显示
     *
     * @param searchResponse
     * @return
     */
    public static Page getMapsPageByNormal(Integer pageNo, Integer pageSize, SearchResponse searchResponse) {
        int i = Math.toIntExact(searchResponse.getHits().getTotalHits().value);
        List<Map<String, Object>> mapsByHighLight = getMaps(searchResponse);
        return new Page(pageNo, pageSize, i, mapsByHighLight);
    }
}
