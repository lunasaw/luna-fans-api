package com.luna.elasticsearch.client;

import com.alibaba.fastjson.JSON;
import com.luna.common.utils.text.DateUtil;
import com.luna.elasticsearch.ElasticsearchApplicationTests;
import com.luna.elasticsearch.model.Person;
import com.luna.elasticsearch.util.DocRestUtil;
import com.luna.elasticsearch.util.IndexRestUtil;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @Package: com.luna.elasticsearch.client
 * @ClassName: ClientTest
 * @Author: luna
 * @CreateTime: 2020/8/26 13:08
 * @Description:
 */
public class ClientTest extends ElasticsearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private IndexRestUtil       indexRestUtil;

    @Autowired
    private DocRestUtil         docRestUtil;

    @Test
    public void createIndex() throws IOException {
        // 创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("luna-spring");
        // 执行请求
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    @Test
    public void getIndex() throws IOException {
        // 获取索引请求
        GetIndexRequest request = new GetIndexRequest("luna-spring");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    public void deleteIndex() throws IOException {
        // 删除索引请求
        DeleteIndexRequest request = new DeleteIndexRequest("luna-spring");
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    @Test
    public void addData() throws IOException {
        if (indexRestUtil.isIndexExist("luna-person2") == false) {
            indexRestUtil.createIndex("luna-person2");
        }
        Person person = new Person(5L, "小李", "中国", 20, DateUtil.parseDate("1999-11-07"), "C#程序员");
        IndexRequest indexRequest = new IndexRequest("luna-person2");

        // 设置ID
        indexRequest.id(String.valueOf(person.getId()));
        // 超时1秒
        indexRequest.timeout(TimeValue.timeValueSeconds(1));
        indexRequest.timeout("1s");

        indexRequest.source(JSON.toJSONString(person), XContentType.JSON);

        IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(index.status()));
    }

    @Test
    public void addDoc() {
        Person person = new Person(7L, "小红", "中国", 19, DateUtil.parseDate("1999-11-07"), "JAVA程序员");
        docRestUtil.indexAddDoc("luna-person", String.valueOf(person.getId()), person);
    }

    @Test
    public void getDataById() {
        Map<String, Object> str = docRestUtil.getDocById("luna-person2", "5", "name,birthday");
        Date birthday1 = new Date(Long.parseLong(str.get("birthday").toString()));
        System.out.println(birthday1);
        System.out.println(str);
    }

    @Test
    public void updateDoc() {
        Person person = new Person(5L, "小李", "中国", 18, DateUtil.parseDate("1999-11-07"), "C++程序员");
        docRestUtil.updateDoc("luna-person2", String.valueOf(person.getId()), person);
    }

    @Test
    public void deleteDoc() {
        docRestUtil.deleteDoc("luna-person2", "5");
    }

    @Test
    public void searchList() throws IOException {
        SearchRequest searchRequest = new SearchRequest("jd-goods");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder
            // 查询全部
            // .query(QueryBuilders.matchAllQuery())
            // 精确查找 注意这里 中文需要IK分词器
            .query(QueryBuilders.termQuery("title", "java"))
            // 从3开始的查1个
            .from(0).size(20)
            // 按age排序
            // .sort("age")
            // 按age排序降序
            // .sort("age", SortOrder.DESC)
            // 按age排序升序
            // .sort("age", SortOrder.ASC)
            // 排除或者包含那些字段显示
            .fetchSource(new FetchSourceContext(true, null, new String[] {""}))
            .storedField("").highlighter();

        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(search));
        System.out.println(JSON.toJSONString(search.getHits()));
        System.out.println("======================");
        System.out.println(search.getHits().getHits().length);
    }
}
