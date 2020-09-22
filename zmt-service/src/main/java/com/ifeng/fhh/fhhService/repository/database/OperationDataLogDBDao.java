package com.ifeng.fhh.fhhService.repository.database;

import com.ifeng.fhh.fhhService.model.domain.OperationDataLog;

import java.util.concurrent.CompletableFuture;

/**
 * 操作日志详情数据访问接口
 * <p>
 * Created by penghb on 2017/4/24.
 */
public interface OperationDataLogDBDao {

    /**
     * 插入日志
     *
     * @param operationDataLog 日志对象
     * @return 是否成功
     */
    CompletableFuture<Void> insertOne(OperationDataLog operationDataLog);
}
