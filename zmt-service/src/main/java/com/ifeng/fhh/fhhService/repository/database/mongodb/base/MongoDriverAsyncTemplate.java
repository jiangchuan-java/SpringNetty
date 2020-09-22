package com.ifeng.fhh.fhhService.repository.database.mongodb.base;

import com.ifeng.fhh.zmt.web.worker.Aladdin;
import com.ifeng.fhh.zmt.web.worker.WorkingContext;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-8
 */
@Repository
public class MongoDriverAsyncTemplate extends MongoTemplate {

    private static final Logger LOG = LoggerFactory.getLogger(MongoDriverAsyncTemplate.class);

    @Autowired
    private Aladdin aladdin;

    @Autowired
    private MongoDatabase database;



    @Override
    public CompletableFuture<Long> countByOptions(String collection, Bson filter) {
        return null;
    }

    @Override
    public CompletableFuture<Long> count(String collection) {
        return null;
    }

    @Override
    public CompletableFuture<Document> findOne(String collection, Bson filter) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(filter, "filter cannot be null");
        CompletableFuture<Document> findFuture = new CompletableFuture();
        this.getCollection(collection)
                .find(filter)
                .first(wrapCallback(findFuture));
        return findFuture;
    }

    @Override
    public CompletableFuture<Document> findOneWithProjection(String collection, Bson filter, List<String> includeFields) {
        return null;
    }

    @Override
    public CompletableFuture<List<Document>> find(String collection, Bson filter) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(filter, "filter cannot be null");
        CompletableFuture<List<Document>> findFuture = new CompletableFuture();
        List<Document> documents = new ArrayList<>();
        this.getCollection(collection)
                .find(filter)
                .into(documents, wrapCallback(findFuture));
        return findFuture;
    }

    @Override
    public CompletableFuture<List<Document>> findAll(String collection) {
        CompletableFuture<List<Document>> findFuture = new CompletableFuture();
        List<Document> documents = new ArrayList<>();
        this.getCollection(collection)
                .find()
                .into(documents, wrapCallback(findFuture));
        return findFuture;
    }

    @Override
    public CompletableFuture<List<Document>> findWithOptions(String collection, Bson filter, FindOptions options) {
        return null;
    }

    @Override
    public CompletableFuture<Void> insertOne(String collection, Document document) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(document, "insert document cannot be null");
        CompletableFuture<Void> insertFuture = new CompletableFuture();
        this.getCollection(collection)
                .insertOne(document, wrapCallback(insertFuture));
        return insertFuture;
    }

    @Override
    public CompletableFuture<Void> insertOneWithOptions(String collection, Document document, InsertOneOptions options) {
        return null;
    }

    @Override
    public CompletableFuture<Void> insertMany(String collection, List<Document> documents) {
        return null;
    }

    @Override
    public CompletableFuture<Void> insertManyWithOptions(String collection, List<Document> documents, InsertManyOptions options) {
        return null;
    }

    @Override
    public CompletableFuture<UpdateResult> updateOne(String collection, Bson filter, Bson update) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(filter, "filter cannot be null");
        Objects.requireNonNull(update, "update cannot be null");
        CompletableFuture<UpdateResult> updateFuture = new CompletableFuture<>();
        this.getCollection(collection)
                .updateOne(filter, update, wrapCallback(updateFuture));
        return updateFuture;
    }

    @Override
    public CompletableFuture<UpdateResult> updateOneWithOptions(String collection, Bson filter, Bson update, UpdateOptions options) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(filter, "filter cannot be null");
        Objects.requireNonNull(update, "update cannot be null");
        Objects.requireNonNull(options, "options cannot be null");
        CompletableFuture<UpdateResult> updateFuture = new CompletableFuture();
        Document doc = new Document("$set",update);
        this.getCollection(collection)
                .updateOne(filter, doc, options, wrapCallback(updateFuture));
        return updateFuture;
    }

    @Override
    public CompletableFuture<UpdateResult> updateMany(String collection, Bson filter, Bson update) {
        return null;
    }

    @Override
    public CompletableFuture<UpdateResult> updateManyWithOptions(String collection, Bson filter, Bson update, UpdateOptions options) {
        return null;
    }

    @Override
    public CompletableFuture<Document> findOneAndModify(String collection, Bson filter, Bson update, FindOneAndUpdateOptions options) {
        Objects.requireNonNull(collection, "collection cannot be null");
        Objects.requireNonNull(filter, "filter cannot be null");
        Objects.requireNonNull(update, "update cannot be null");
        Objects.requireNonNull(options, "find options cannot be null");
        CompletableFuture<Document> findAndUpdateFuture = new CompletableFuture<>();
        this.getCollection(collection)
                .findOneAndUpdate(filter, update, options, wrapCallback(findAndUpdateFuture));
        return findAndUpdateFuture;
    }

    @Override
    public CompletableFuture<DeleteResult> deleteOne(String collection, Bson filter) {
        return null;
    }

    @Override
    public CompletableFuture<DeleteResult> deleteOneWithOptions(String collection, Bson filter, DeleteOptions options) {
        return null;
    }

    @Override
    public CompletableFuture<DeleteResult> deleteMany(String collection, Bson filter) {
        return null;
    }

    @Override
    public CompletableFuture<DeleteResult> deleteManyWithOptions(String collection, Bson filter, DeleteOptions options) {
        return null;
    }

    @Override
    public CompletableFuture<List<Document>> aggregate(String collection, List<Bson> list) {
        return null;
    }

    @Override
    public CompletableFuture<Document> runCommand(Bson command) {
        return null;
    }

    @Override
    public MongoCollection<Document> getCollection(String collection) {
        return getDatabase().getCollection(collection);
    }

    @Override
    public MongoDatabase getDatabase() {
        return this.database;
    }

    @Override
    public MongoClient getClient() {
        return null;
    }

    private <T> SingleResultCallback<T> wrapCallback(CompletableFuture<T> future) {
        Objects.requireNonNull(aladdin, "aladdin cannot be null");
        //执行异步操作之前，获取当前线程绑定的context，一定是自己当前执行的，异步之后在获取肯定就不是自己了
        //每个线程会绑定多个context，当执行某个context时，获取的才是当前自身的
        //context绑定了执行线程，回来通过context获取之前的线程投放任务
        WorkingContext context = this.aladdin.getOrCreateContext();
        return (result, error) -> context.runOnContext(() -> {
            if (error != null) {
                LOG.error("wrapCallback errorMessage:{}, errorLocalMessage:{}, mongo callback error", error.getMessage(), error.getLocalizedMessage());
                future.completeExceptionally(error);
            } else {
                future.complete(result);
            }
        });
    }
}
