package com.ifeng.fhh.fhhService.repository.database;

import com.ifeng.fhh.fhhService.model.domain.Category;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 分类数据访问接口
 * <p>
 * Created by chenwj3 on 2017/1/19.
 */
public interface CategoryDBDao {

    /**
     * 根据类型查询
     *
     * @return 分类集合
     */
    CompletableFuture<List<Category>> findAll();

}
