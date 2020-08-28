package com.luna.api.jd.service;

import com.luna.api.jsoup.JsoupUtil;
import com.luna.api.jd.dto.SearchJDDTO;
import com.luna.elasticsearch.constants.ElastcSearchConstants;
import com.luna.elasticsearch.util.BulkRestUtil;
import com.luna.elasticsearch.util.DocRestUtil;
import com.luna.elasticsearch.util.SearchRestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.luna.api.jd.service
 * @ClassName: SearchService
 * @Author: luna
 * @CreateTime: 2020/8/26 20:16
 * @Description:
 */
@Service
public class SearchJDService {
    private static final Logger log = LoggerFactory.getLogger(SearchJDService.class);

    @Autowired
    private DocRestUtil         docRestUtil;

    @Autowired
    private SearchRestUtil      searchRestUtil;

    @Autowired
    private BulkRestUtil        bulkRestUtil;

    /**
     * 讲获取到的数据放入elasticsearch
     * 
     * @param keyWord
     * @return
     */
    public boolean parseJD(String keyWord) {
        log.info("parseJD start keyWord={}", keyWord);
        List<SearchJDDTO> searchJDDTOS = JsoupUtil.parseJd(keyWord);
        return bulkRestUtil.addList(ElastcSearchConstants.INDEX_NAME, searchJDDTOS, false);
    }

    /**
     * 精准条件分页搜索
     * 
     * @param keyWord
     * @param keyValue
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<Map<String, Object>> searchJDPage(String keyWord, String keyValue, int pageNo, int pageSize) {
        log.info("searchJDPage start keyWord={},  keyValue={},  pageNo={},  pageSize={}", keyWord, keyValue, pageNo,
            pageSize);
        if (pageNo <= 1) {
            pageNo = 1;
        }
        // 条件搜索
        return searchRestUtil.searchByField(ElastcSearchConstants.INDEX_NAME, keyWord, keyValue, pageNo, pageSize,
            keyWord);
    }

}
