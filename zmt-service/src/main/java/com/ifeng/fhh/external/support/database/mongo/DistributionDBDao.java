package com.ifeng.fhh.external.support.database.mongo;

import com.ifeng.fhh.external.support.entity.DistributionDocument;
import com.ifeng.fhh.fhhService.repository.database.mongodb.base.MongoTemplate;
import com.ifeng.fhh.fhhService.repository.database.mongodb.impl.MongodbBaseDaoImpl;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-31
 */
@Repository
public class DistributionDBDao extends MongodbBaseDaoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static Map<String,String> channeCollectionlMap = new HashMap<>();
    static {
        channeCollectionlMap.put("hwBrowser","channel_hwBrowser");
        channeCollectionlMap.put("xiaomiBrowser","channel_xiaomiBrowser");
        channeCollectionlMap.put("vivoBrowser","channel_vivoBrowser");
        channeCollectionlMap.put("vivofyp","channel_vivofyp");
        channeCollectionlMap.put("hwVideo","channel_hwVideo");
    }

    private static final String defaultCollectionName = "channel_default";

    public CompletableFuture<Boolean> insertOrUpdate(DistributionDocument document){
        CompletableFuture<Boolean> insertFuture = new CompletableFuture<>();
        try {
            Document doc = this.serialize(document);
            Bson filter = and(eq("documentId", document.getDocumentId()),
                    eq("channel", document.getChannel())
            );
            UpdateOptions updateOptions = new UpdateOptions().upsert(true);
            String collectionName = channeCollectionlMap.get(document.getChannel());
            if(collectionName == null){
                collectionName = defaultCollectionName;
            }
            mongoTemplate.updateOneWithOptions(collectionName,filter, doc,updateOptions).whenComplete(new BiConsumer<UpdateResult, Throwable>() {
                @Override
                public void accept(UpdateResult updateResult, Throwable throwable) {
                    if(throwable == null){
                        insertFuture.complete(true);
                    } else {
                        insertFuture.completeExceptionally(throwable);
                    }
                }
            });
        }catch (Exception e){
            insertFuture.completeExceptionally(e);
        }
        return insertFuture;
    }
}
