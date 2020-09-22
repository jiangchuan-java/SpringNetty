package com.ifeng.fhh.fhhService.repository.database.mongodb.impl;

import com.ifeng.fhh.fhhService.model.domain.Honor;
import com.ifeng.fhh.fhhService.repository.database.HonorDBDao;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @Auther: zhunn
 * @Date: 2018/10/11 15:18
 * @Description:
 */
@Repository
public class HonorDBDaoImpl extends MongodbBaseDaoImpl implements HonorDBDao {

    @Value("honor")
    private String collectionName;

    @Override
    public CompletableFuture<Honor> findById(String _id) {
        CompletableFuture<Honor> honorFuture = new CompletableFuture();
        if (!ObjectId.isValid(_id)) {
            honorFuture.completeExceptionally(new Throwable("id is illegal"));
            return honorFuture;
        }

        ObjectId objectId = new ObjectId(_id);
        Bson filter = Filters.eq("_id", objectId);
        mongoTemplate.findOne(this.collectionName, filter).whenComplete((document, throwable) -> {
            if(throwable != null){
                honorFuture.completeExceptionally(throwable);
            } else {
                try {
                    Honor honor = null;
                    if (Objects.nonNull(document)) {
                        honor = deserialize(document, Honor.class);
                    }
                    honorFuture.complete(honor);
                } catch (Exception e) {
                    honorFuture.completeExceptionally(e);
                }
            }
        });
        return honorFuture;
    }

    @Override
    public CompletableFuture<Map<String, Honor>> findAll() {
        CompletableFuture<Map<String, Honor>> honorFuture = new CompletableFuture<>();

        mongoTemplate.findAll(this.collectionName).whenComplete((document, throwable) -> {
            if(throwable != null){
                honorFuture.completeExceptionally(throwable);
            } else {
                try {
                    List<Honor> honorList = null;
                    if (Objects.nonNull(document)) {
                        honorList = deserializeList(document, Honor.class);
                    }
                    if (honorList == null || honorList.size() <= 0) {
                        honorFuture.complete(null);
                    } else {
                        Map<String, Honor> honorMap = new HashMap<>();
                        for (Honor honor : honorList) {
                            honorMap.put(honor.get_id(), honor);
                        }
                        honorFuture.complete(honorMap);
                    }
                } catch (Exception e) {
                    honorFuture.completeExceptionally(e);
                }
            }
        });
        return honorFuture;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
