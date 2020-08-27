package com.luna.elasticsearch.util;

import com.alibaba.fastjson.JSON;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.utils.text.StringUtils;
import com.luna.elasticsearch.exception.ElasticsearchException;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.luna.elasticsearch.util
 * @ClassName: BulkRestUtil
 * @Author: luna
 * @CreateTime: 2020/8/27 19:29
 * @Description:
 */
@Component
public class BulkRestUtil<T> {

    private static final Logger log = LoggerFactory.getLogger(BulkRestUtil.class);

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 批量数据插入
     *
     * @param index
     * @param lists
     * @return
     */
    public boolean addList(String index, List<T> lists, boolean hasId) {
        if (StringUtils.isEmpty(index) || StringUtils.isEmpty(lists)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        BulkRequest bulkRequest = new BulkRequest();
        if (hasId) {
            for (int i = 0; i < lists.size(); i++) {
                bulkRequest.add(new IndexRequest(index).id("" + (i + 1)).source(
                    JSON.toJSONString(lists.get(i)), XContentType.JSON));
            }
        } else {
            for (int i = 0; i < lists.size(); i++) {
                bulkRequest.add(new IndexRequest(index).source(JSON.toJSONString(lists.get(i)), XContentType.JSON));
            }
        }

        try {
            BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            return !bulk.hasFailures();
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "批量添加文档 {" + index + "} 失败" + e.getMessage());
        }
    }

    /**
     * 批量删除文档
     *
     * @param index
     * @param idList
     * @return
     */
    public boolean deleteList(String index, List<String> idList) {
        if (StringUtils.isEmpty(index)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        BulkRequest bulkRequest = new BulkRequest();
        if (StringUtils.isNotEmpty(idList)) {
            for (int i = 0; i < idList.size(); i++) {
                bulkRequest.add(new DeleteRequest(index, idList.get(i)));
            }
        }

        try {
            BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            return !bulk.hasFailures();
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "批量删除文档 {" + index + "} 失败" + e.getMessage());
        }
    }

    /**
     * 批量更新数据
     *
     * @param index
     * @param updateList
     * @return
     */
    public boolean updateList(String index, Map<String, Object> updateList) {
        if (StringUtils.isEmpty(index)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        BulkRequest bulkRequest = new BulkRequest();
        if (StringUtils.isNotEmpty(updateList)) {
            Iterator<Map.Entry<String, Object>> entries = updateList.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Object> entry = entries.next();
                bulkRequest.add(new UpdateRequest(entry.getKey(),JSON.toJSONString(entry.getValue())));
            }
        }

        try {
            BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            return !bulk.hasFailures();
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                    "批量更新文档 {" + index + "} 失败" + e.getMessage());
        }
    }
}
