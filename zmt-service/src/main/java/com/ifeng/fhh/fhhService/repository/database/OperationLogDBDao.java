package com.ifeng.fhh.fhhService.repository.database;

import com.ifeng.fhh.fhhService.model.domain.OperationLog;

import java.util.concurrent.CompletableFuture;

/**
 * 操作日志数据访问接口
 * <p>
 * Created by penghb on 2017/4/21.
 */
public interface OperationLogDBDao {

    /**
     * 插入日志
     *
     * @param operationLog 日志对象
     * @return 是否成功
     */
    CompletableFuture<Void> insertOne(OperationLog operationLog);
}
