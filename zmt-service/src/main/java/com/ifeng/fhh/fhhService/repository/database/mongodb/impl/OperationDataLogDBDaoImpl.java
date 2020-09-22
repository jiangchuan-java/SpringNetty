package com.ifeng.fhh.fhhService.repository.database.mongodb.impl;

import com.ifeng.fhh.fhhService.model.domain.OperationDataLog;
import com.ifeng.fhh.fhhService.repository.database.OperationDataLogDBDao;
import com.ifeng.fhh.zmt.tools.DateUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * 操作日志详情数据访问接口实现
 * <p>
 * Created by penghb on 2017/4/24.
 */
public class OperationDataLogDBDaoImpl extends MongodbBaseDaoImpl implements OperationDataLogDBDao {

    @Value("operation_data_log_new")
    private String collectionName;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    @Override
    public CompletableFuture<Void> insertOne(OperationDataLog operationDataLog) {
        CompletableFuture<Void> insertFuture = new CompletableFuture<>();

        try {
            Objects.requireNonNull(operationDataLog, "operationDataLog cannot be null");
            operationDataLog.setId(objectId().toHexString());
            Date expireTime = operationDataLog.getExpireTime();
            if(Objects.isNull(expireTime)){
                operationDataLog.setExpireTime(DateUtils.getSomeMonthLater(new Date(),6));
            }
            Document document = serialize(operationDataLog);
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
