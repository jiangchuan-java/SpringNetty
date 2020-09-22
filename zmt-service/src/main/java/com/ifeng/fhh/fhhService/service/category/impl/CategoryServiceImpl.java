package com.ifeng.fhh.fhhService.service.category.impl;

import com.ifeng.fhh.fhhService.model.domain.Category;
import com.ifeng.fhh.fhhService.repository.database.CategoryDBDao;
import com.ifeng.fhh.fhhService.service.account.impl.AccountScoreRecordServiceImpl;
import com.ifeng.fhh.fhhService.service.category.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * 分类业务接口实现
 * <p>
 * Created by chenwj3 on 2017/1/19.
 */
@Service
public class CategoryServiceImpl implements CategoryService {


    private static final Logger LOG = LoggerFactory.getLogger(AccountScoreRecordServiceImpl.class);


    @Autowired
    private CategoryDBDao categoryDBDao;


    @Override
    public CompletableFuture<Map<String, String>> findAll() {
        CompletableFuture<Map<String, String>> future = new CompletableFuture<>();

        try {
            Objects.requireNonNull(categoryDBDao, "category db repository cannot be null");

            categoryDBDao.findAll().whenComplete((categoryDOs, throwable) -> {
                if(throwable != null){
                    future.completeExceptionally(throwable);
                } else {
                    if (categoryDOs == null || categoryDOs.size() <= 0) {
                        future.complete(null);
                    } else {
                        Map<String, String> finalMap = new HashMap<>();
                        for (Category categoryDO : categoryDOs) {
                            finalMap.put(categoryDO.get_id(), categoryDO.getName());
                        }
                        future.complete(finalMap);
                    }
                }
            });
        } catch (Exception e) {
            LOG.error("findAll find category list all fail {}",e);
            future.completeExceptionally(e);
        }

        return future;
    }


    public CategoryDBDao getCategoryDBDao() {
        return categoryDBDao;
    }

    public void setCategoryDBDao(CategoryDBDao categoryDBDao) {
        this.categoryDBDao = categoryDBDao;
    }
}
