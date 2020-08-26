package com.luna.elasticsearch.util;

import com.alibaba.fastjson.JSON;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.utils.text.RandomStrUtil;
import com.luna.common.utils.text.StringUtils;
import com.luna.elasticsearch.exception.ElasticsearchException;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.luna.elasticsearch.util
 * @ClassName: DocRestUtil
 * @Author: luna
 * @CreateTime: 2020/8/26 14:29
 * @Description:
 */
@Component
public class DocRestUtil<T> {

    private static final Logger log = LoggerFactory.getLogger(DocRestUtil.class);

    @Autowired
    private RestHighLevelClient client;

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
        if (StringUtils.isEmpty(index)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        if (StringUtils.isNotEmpty(id)) {
            return new IndexRequest(index).id(id).source(JSON.toJSONString(source), XContentType.JSON);
        }
        return new IndexRequest(index).source(JSON.toJSONString(source), XContentType.JSON);
    }

    /**
     * 数据添加
     *
     * @param soucre 要增加的数据
     * @param index 索引，类似数据库
     * @return
     */
    public String addData(Object soucre, String index) {
        return indexAddDoc(index, RandomStrUtil.getUUID(), soucre);
    }

    /**
     * 文档添加，正定Id
     *
     * @param source 要增加的文档
     * @param index 索引，类似文档库
     * @param id 文档ID
     * @return
     */
    public String indexAddDoc(String index, String id, Object source) {
        try {
            IndexResponse response = client.index(buildIndexRequest(index, id, source), RequestOptions.DEFAULT);
            log.info("addDoc response status:{},id:{}", response.status().getStatus(), response.getId());
            return response.getId();
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "新增索引 {" + index + "} 文档 {" + source + "} 失败" + e.getMessage());
        }
    }

    /**
     * 构造文档请求
     *
     * @param index elasticsearch index name
     * @param id request object id
     * @param id
     * @return
     */
    public static GetRequest buildGetRequest(String index, String id, String includes) {
        if (StringUtils.isEmpty(index) || StringUtils.isEmpty(id)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        if (StringUtils.isNotEmpty(includes)) {
            String[] includeFields = includes.split(",");
            FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includeFields, null);
            return new GetRequest(index, id).fetchSourceContext(fetchSourceContext);
        }
        return new GetRequest(index, id);
    }

    /**
     * 通过index和id获取文档
     *
     * @param index 索引，类似文档库
     * @param id 文档id
     * @param includes 需要显示的字段，逗号分隔（缺省为全部字段）
     * @return
     */
    public Map<String, Object> getDocById(String index, String id, String includes) {
        try {
            GetResponse response = client.get(buildGetRequest(index, id, includes), RequestOptions.DEFAULT);
            log.info("getDocById response index={},id={},includes={}", index, id, includes);
            return response.getSourceAsMap();
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "通过ID获取文档 {" + index + "} 文档id {" + id + "} 失败 {" + includes + "}" + e.getMessage());
        }
    }

    /**
     * 判断文档是否存在
     * 
     * @param index
     * @param id
     * @return
     */
    public boolean docIsExist(String index, String id) {
        try {
            GetRequest getRequest = buildGetRequest(index, id, null);
            // 不返回_source 上下文
            getRequest.fetchSourceContext(new FetchSourceContext(false));
            getRequest.storedFields("_none_");
            boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
            return exists;
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "判断文档是否存在 {" + index + "} 文档id {" + id + "} 失败" + e.getMessage());
        }
    }

    /**
     * 更新文档
     * 
     * @param index
     * @param id
     * @param source
     */
    public void updateDoc(String index, String id, Object source) {
        if (StringUtils.isEmpty(index) || StringUtils.isEmpty(id)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        try {
            UpdateRequest updateRequest = new UpdateRequest(index, id);
            updateRequest.doc(JSON.toJSONString(source), XContentType.JSON);
            UpdateResponse update = client.update(updateRequest, RequestOptions.DEFAULT);
            log.info("updateDoc response status:{},id:{}", update.status().getStatus(), update.getId());
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "更新文档 {" + index + "} 文档id {" + id + "} 失败" + e.getMessage());
        }
    }

    /**
     * 删除文档
     * 
     * @param index
     * @param id
     */
    public void deleteDoc(String index, String id) {
        if (StringUtils.isEmpty(index) || StringUtils.isEmpty(id)) {
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        try {
            DeleteRequest deleteRequest = new DeleteRequest(index, id);
            DeleteResponse delete = client.delete(deleteRequest, RequestOptions.DEFAULT);
            log.info("updateDoc response status:{},id:{}", delete.status().getStatus(), delete.getId());
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "删除文档 {" + index + "} 文档id {" + id + "} 失败" + e.getMessage());
        }
    }

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
            BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            return !bulk.hasFailures();
        } catch (IOException e) {
            throw new ElasticsearchException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                "批量添加文档 {" + index + "} 失败" + e.getMessage());
        }
    }

}
