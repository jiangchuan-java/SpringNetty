package com.ifeng.fhh.fhhService.repository.searchEngine.elasticsearch.base;

import org.elasticsearch.search.sort.SortBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询操作配置
 * <p>
 * Created by licheng1 on 2017/7/5.
 */
public class QueryOptions {

    public static final int DEFAULT_FROM = 0;
    public static final int DEFAULT_SIZE = 20;

    private List<SortBuilder<?>> sortBuilders;

    private int from;
    private int size;

    public QueryOptions() {
        this.from = DEFAULT_FROM;
        this.size = DEFAULT_SIZE;
    }

    public QueryOptions(QueryOptions other) {
        this.from = other.from;
        this.size = other.size;
        this.sortBuilders = other.sortBuilders;
    }

    public QueryOptions addSort(SortBuilder<?> sortBuilder) {
        if (sortBuilders == null) {
            sortBuilders = new ArrayList<>();
        }
        sortBuilders.add(sortBuilder);
        return this;
    }

    public List<SortBuilder<?>> getSortBuilders() {
        return sortBuilders;
    }

    public QueryOptions setSortBuilders(List<SortBuilder<?>> sortBuilders) {
        this.sortBuilders = sortBuilders;
        return this;
    }

    public int getFrom() {
        return from;
    }

    public QueryOptions setFrom(int from) {
        this.from = from;
        return this;
    }

    public int getSize() {
        return size;
    }

    public QueryOptions setSize(int size) {
        this.size = size;
        return this;
    }

    @Override
    public int hashCode() {
        int result = this.sortBuilders != null ? this.sortBuilders.hashCode() : 0;
        result = 31 * result + this.from;
        result = 31 * result + this.size;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            QueryOptions options = (QueryOptions) o;
            if (this.size != options.size) {
                return false;
            } else if (this.from != options.from) {
                return false;
            } else {
                label38:
                {
                    if (this.sortBuilders != null) {
                        if (this.sortBuilders.equals(options.sortBuilders)) {
                            break label38;
                        }
                    } else if (options.sortBuilders == null) {
                        break label38;
                    }

                    return false;
                }

                return true;
            }
        } else {
            return false;
        }
    }
}
