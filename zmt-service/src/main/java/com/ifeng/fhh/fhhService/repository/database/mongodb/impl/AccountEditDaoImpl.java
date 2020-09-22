package com.ifeng.fhh.fhhService.repository.database.mongodb.impl;

import com.ifeng.fhh.fhhService.model.domain.AccountEdit;
import com.ifeng.fhh.fhhService.repository.database.AccountEditDao;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.CompletableFuture;

/**
 * @Auther: zhunn
 * @Date: 2019/6/19 17:23
 * @Description:
 */
public class AccountEditDaoImpl extends MongodbBaseDaoImpl implements AccountEditDao {

    @Value("account_edit")
    private String collectionName;

    @Override
    public CompletableFuture<String> insert(AccountEdit accountEdit) {
        CompletableFuture<String> insertFuture = new CompletableFuture<>();
        try {
            accountEdit.set_id(objectId().toHexString());
            Document document = serialize(accountEdit);
            mongoTemplate.insertOne(this.collectionName, document).whenComplete((result,throwable)->{
                if(throwable != null){
                    insertFuture.completeExceptionally(throwable);
                } else {
                    insertFuture.complete(accountEdit.get_id());
                }
            });
        } catch (Exception e) {
            insertFuture.completeExceptionally(e);
        }
        return insertFuture;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
