package com.luna.elasticsearch.util;

import com.luna.common.entity.Page;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

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
public class SearchByPageUtil {

    /**
     * 解析普通字段并分页显示
     *
     * @param searchResponse
     * @return
     */
    public static Page getMapsByPage(Integer pageNo, Integer pageSize, SearchResponse searchResponse) {
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hitList = hits.getHits();
        List<Map<String, Object>> objects = new ArrayList<>();
        Arrays.stream(hitList).forEach(hit -> {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            objects.add(sourceAsMap);
        });
        TotalHits totalHits = hits.getTotalHits();
        return new Page(pageNo, pageSize, Math.toIntExact(totalHits.value), objects);
    }

}
