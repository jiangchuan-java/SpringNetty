package com.ifeng.fhh.fhhService.repository.database.mongodb.impl;

import com.ifeng.fhh.fhhService.model.domain.OperationLog;
import com.ifeng.fhh.fhhService.repository.database.OperationLogDBDao;
import com.ifeng.fhh.zmt.tools.DateUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * 操作日志数据访问实现
 * <p>
 * Created by penghb on 2017/4/21.
 */
public class OperationLogDBDaoImpl extends MongodbBaseDaoImpl implements OperationLogDBDao {

    @Value("operation_log_new")
    private String collectionName;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    @Override
    public CompletableFuture<Void> insertOne(OperationLog operationLog) {
        CompletableFuture<Void> insertFuture = new CompletableFuture<>();
        try {
            Objects.requireNonNull(operationLog, "operationLog cannot be null");
            operationLog.setId(objectId().toHexString());
            Date expireTime = operationLog.getExpireTime();
            if(Objects.isNull(expireTime)){
                operationLog.setExpireTime(DateUtils.getSomeMonthLater(new Date(),6));
            }
            Document document = serialize(operationLog);
            mongoTemplate.insertOne(this.collectionName, document)
                    .whenComplete(((aVoid, throwable) -> {
                        if(throwable != null){
                            insertFuture.completeExceptionally(throwable);
                        } else {
                            insertFuture.complete(null);
                        }
                    }));
        } catch (Exception e) {
            insertFuture.completeExceptionally(e);
        }

        return insertFuture;
    }
}
