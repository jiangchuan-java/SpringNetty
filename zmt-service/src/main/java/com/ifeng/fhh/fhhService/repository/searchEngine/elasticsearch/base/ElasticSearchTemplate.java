package com.ifeng.fhh.fhhService.repository.searchEngine.elasticsearch.base;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * elasticsearch数据访问模板
 * <p>
 * Created by licheng1 on 2017/4/28.
 */
public abstract class ElasticSearchTemplate {

    /**
     * 插入一个记录
     *
     * @param index  索引
     * @param type   类型
     * @param id     id
     * @param source 文档
     * @return indexResponse
     */
    public abstract CompletableFuture<IndexResponse> index(String index, String type, String id, Map<String, Object> source);

    /**
     * 查询一个记录
     *
     * @param index 索引
     * @param type  类型
     * @param id    id
     * @return getResponse
     */
    public abstract CompletableFuture<GetResponse> get(String index, String type, String id);

    /**
     * 查询多个记录
     *
     * @param index 索引
     * @param type  类型
     * @param ids   id集合
     * @return multiGetResponse
     */
    public abstract CompletableFuture<MultiGetResponse> multiGet(String index, String type, List<String> ids);

    /**
     * 搜索
     *
     * @param indices      索引列表
     * @param types        类型列表
     * @param queryBuilder 搜索过滤构造器
     * @return 搜索响应
     */
    public abstract CompletableFuture<SearchResponse> search(String[] indices, String[] types, QueryBuilder queryBuilder);

    /**
     * 搜索(带有配置信息)
     * 配置中可以设置分页和排序
     *
     * @param indices      索引列表
     * @param types        类型列表
     * @param queryBuilder 搜索过滤构造器
     * @param options      搜索配置
     * @return 搜索响应
     */
    public abstract CompletableFuture<SearchResponse> searchWithOptions(String[] indices, String[] types, QueryBuilder queryBuilder, QueryOptions options);
}
