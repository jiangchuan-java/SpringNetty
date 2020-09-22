package com.ifeng.fhh.fhhService.repository.database.mongodb.impl;

import com.ifeng.fhh.fhhService.model.domain.AccountScoreRecord;
import com.ifeng.fhh.fhhService.repository.database.AccountScoreRecordDBDao;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import static com.mongodb.client.model.Filters.*;

/**
 * @Auther: zhunn
 * @Date: 2019/12/3 16:04
 * @Description:
 */
@Repository
public class AccountScoreRecordDBDaoImpl extends MongodbBaseDaoImpl implements AccountScoreRecordDBDao {

    @Value("account_score_record")
    private String collectionName;

    @Value("account_score_record_V2")
    private String collectionName_v2;

    @Override
    public CompletableFuture<Void> insertOne(AccountScoreRecord record) {
        CompletableFuture<Void> insertFuture = new CompletableFuture<>();
        try {
            Objects.requireNonNull(record);
            record.set_id(objectId().toHexString());
            Document document = serialize(record);
            mongoTemplate.insertOne(this.collectionName_v2, document)
                    .whenComplete((aVoid, throwable) -> {
                        if (throwable != null) {
                            insertFuture.completeExceptionally(throwable);
                        } else {
                            insertFuture.complete(aVoid);
                        }
                    });
        } catch (Exception e) {
            insertFuture.completeExceptionally(e);
        }
        return insertFuture;
    }

    @Override
    public CompletableFuture<List<AccountScoreRecord>> findRecordsByAccountId(Long eAccountId) {
        CompletableFuture<List<AccountScoreRecord>> recordFuture = new CompletableFuture<>();

        try {
            Objects.requireNonNull(eAccountId, "eAccountId cannot be null");
            Bson filter = and(eq("eAccountId", eAccountId));
            mongoTemplate.find(this.collectionName_v2, filter).whenComplete((documents, throwable) -> {
                if(throwable != null){
                    recordFuture.completeExceptionally(throwable);
                } else {
                    try {
                        List<AccountScoreRecord> records = null;
                        if (documents != null && !documents.isEmpty()) {
                            records = deserializeList(documents, AccountScoreRecord.class);
                        }
                        recordFuture.complete(records);
                    } catch (Exception e) {
                        recordFuture.completeExceptionally(e);
                    }
                }
            });
        } catch (Exception e) {
            recordFuture.completeExceptionally(e);
        }
        return recordFuture;
    }


    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionName_v2() {
        return collectionName_v2;
    }

    public void setCollectionName_v2(String collectionName_v2) {
        this.collectionName_v2 = collectionName_v2;
    }
}
