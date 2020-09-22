package com.ifeng.fhh.fhhService.repository.database;


import com.ifeng.fhh.fhhService.model.domain.Honor;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @Auther: zhunn
 * @Date: 2018/10/11 15:14
 * @Description: 荣誉信息接口
 */
public interface HonorDBDao {

    /**
     * 根据主键id获取荣誉信息
     * @param _id 主键id
     * @return
     */
    CompletableFuture<Honor> findById(String _id);

    CompletableFuture<Map<String, Honor>> findAll();
}
