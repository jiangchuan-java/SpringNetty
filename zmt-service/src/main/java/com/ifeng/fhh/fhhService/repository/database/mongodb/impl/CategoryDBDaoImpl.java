package com.ifeng.fhh.fhhService.repository.database.mongodb.impl;

import com.ifeng.fhh.fhhService.model.domain.Category;
import com.ifeng.fhh.fhhService.repository.database.CategoryDBDao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;


/**
 * 分类数据访问接口实现
 * <p>
 * Created by chenwj3 on 2017/1/19.
 */
@Repository
public class CategoryDBDaoImpl extends MongodbBaseDaoImpl implements CategoryDBDao {

    @Value("category")
    private String collectionName;

    @Override
    public CompletableFuture<List<Category>> findAll() {
        CompletableFuture<List<Category>> future = new CompletableFuture<>();
        try {
            mongoTemplate.findAll(this.collectionName).whenComplete((documents, throwable) -> {
                if(throwable != null){
                    future.completeExceptionally(throwable);
                } else {
                    try {
                        List<Category> categoryList = null;
                        if (Objects.nonNull(documents) && !documents.isEmpty()) {
                            categoryList = deserializeList(documents, Category.class);
                        }
                        future.complete(categoryList);
                    } catch (Exception e) {
                        future.completeExceptionally(e);
                    }
                }
            });
        } catch (Exception e) {
            future.completeExceptionally(e);
        }

        return future;
    }


    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
