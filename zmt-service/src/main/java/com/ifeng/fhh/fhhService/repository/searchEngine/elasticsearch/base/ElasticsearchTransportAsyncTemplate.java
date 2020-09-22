package com.ifeng.fhh.fhhService.repository.searchEngine.elasticsearch.base;

import com.ifeng.fhh.zmt.web.worker.Aladdin;
import com.ifeng.fhh.zmt.web.worker.WorkingContext;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-14
 */
@Repository
public class ElasticsearchTransportAsyncTemplate extends ElasticSearchTemplate {

    @Autowired
    private Aladdin aladdin;

    @Autowired
    private TransportClient transportClient;

    @Override
    public CompletableFuture<IndexResponse> index(String index, String type, String id, Map<String, Object> source) {
        return null;
    }

    @Override
    public CompletableFuture<GetResponse> get(String index, String type, String id) {
        return null;
    }

    @Override
    public CompletableFuture<MultiGetResponse> multiGet(String index, String type, List<String> ids) {
        return null;
    }

    @Override
    public CompletableFuture<SearchResponse> search(String[] indices, String[] types, QueryBuilder queryBuilder) {
        return null;
    }

    @Override
    public CompletableFuture<SearchResponse> searchWithOptions(String[] indices, String[] types, QueryBuilder queryBuilder, QueryOptions options) {
        Objects.requireNonNull(indices, "index list cannot be null");
        Objects.requireNonNull(types, "type list cannot be null");
        Objects.requireNonNull(queryBuilder, "queryBuilder cannot be null");


        if (indices.length < 1) {
            throw new RuntimeException("index list cannot be empty");
        }
        if (types.length < 1) {
            throw new RuntimeException("type list cannot be empty");
        }
        CompletableFuture<SearchResponse> searchFuture = new CompletableFuture();
        //requestBuilder:查询器
        SearchRequestBuilder requestBuilder = transportClient.prepareSearch(indices);
        //设置查询类型
        requestBuilder.setTypes(types);
        //设置查询条件
        requestBuilder.setQuery(queryBuilder);
        List<SortBuilder<?>> sortBuilders = options.getSortBuilders();
        if (sortBuilders != null && sortBuilders.size() > 0) {
            sortBuilders.forEach(requestBuilder::addSort);
        }
        int from = options.getFrom();
        requestBuilder.setFrom(from);
        int size = options.getSize();
        requestBuilder.setSize(size);
        requestBuilder.execute(wrapListener(searchFuture));
        return searchFuture;
    }

    public TransportClient getTransportClient() {
        return transportClient;
    }

    public void setTransportClient(TransportClient transportClient) {
        this.transportClient = transportClient;
    }

    @FunctionalInterface
    interface CallbackListener<T> extends ActionListener<T> {

        void callback(T t, Exception e);

        @Override
        default void onResponse(T t) {
            callback(t, null);
        }

        @Override
        default void onFailure(Exception e) {
            callback(null, e);
        }
    }

    private <T> ActionListener<T> wrapListener(CompletableFuture<T> future) {
        Objects.requireNonNull(aladdin);
        WorkingContext context = this.aladdin.getOrCreateContext();
        return (CallbackListener<T>) (t, e) -> context.runOnContext(() -> {
            if (e != null) {
                future.completeExceptionally(e);
            } else {
                future.complete(t);
            }
        });
    }
}
