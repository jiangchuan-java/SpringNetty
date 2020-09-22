package com.ifeng.fhh.fhhService.repository.database.mongodb.base;

import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * mongodb数据访问模板
 * <p>
 * Created by licheng1 on 2016/12/23.
 */
public abstract class MongoTemplate {

    /**
     * 根据条件统计符合条件的数据的数量
     * @param collection
     * @param filter
     * @return
     */
    public abstract CompletableFuture<Long> countByOptions(String collection, Bson filter);

    /**
     * 根据统计某张表的数据总量
     * @param collection
     * @return
     */
    public abstract CompletableFuture<Long> count(String collection);

    /**
     * 查询单个文档
     *
     * @param collection 集合名称
     * @param filter     过滤条件
     * @return 查询结果
     */
    public abstract CompletableFuture<Document> findOne(String collection, Bson filter);

    /**
     * 查询单个文档伴随要显示的信息
     *
     * @param collection 集合名称
     * @param filter     过滤条件
     * @param includeFields    要显示的字段
     * @return 查询结果
     */
    public abstract CompletableFuture<Document> findOneWithProjection(String collection, Bson filter,List<String> includeFields);

    /**
     * 查询复数文档
     *
     * @param collection 集合名称
     * @param filter     过滤条件
     * @return 查询结果
     */
    public abstract CompletableFuture<List<Document>> find(String collection, Bson filter);


    /**
     * 查询复数文档
     *
     * @param collection 集合名称
     * @return 查询结果
     */
    public abstract CompletableFuture<List<Document>> findAll(String collection);

    /**
     * 查询复数文档，带有配置信息
     *
     * @param collection 集合名称
     * @param filter     过滤条件
     * @param options    查询配置
     * @return 查询结果
     */
    public abstract CompletableFuture<List<Document>> findWithOptions(String collection, Bson filter, FindOptions options);

    /**
     * 插入一个文档
     *
     * @param collection 集合名称
     * @param document   文档
     * @return 空
     */
    public abstract CompletableFuture<Void> insertOne(String collection, Document document);

    /**
     * 插入一个文档, 带有配置信息
     *
     * @param collection 集合名称
     * @param document   文档
     * @param options    配置
     * @return 空
     */
    public abstract CompletableFuture<Void> insertOneWithOptions(String collection, Document document, InsertOneOptions options);

    /**
     * 插入复数文档
     *
     * @param collection 集合名称
     * @param documents  文档集合
     * @return 空
     */
    public abstract CompletableFuture<Void> insertMany(String collection, List<Document> documents);

    /**
     * 插入复数文档, 带有配置信息
     *
     * @param collection 集合名称
     * @param documents  文档集合
     * @param options    配置
     * @return 空
     */
    public abstract CompletableFuture<Void> insertManyWithOptions(String collection, List<Document> documents, InsertManyOptions options);

    /**
     * 更新一个文档
     *
     * @param collection 集合名称
     * @param filter     过滤条件
     * @param update     更新内容
     * @return 更新结果
     */
    public abstract CompletableFuture<UpdateResult> updateOne(String collection, Bson filter, Bson update);

    /**
     * 更新一个文档, 带有配置信息
     *
     * @param collection 集合名称
     * @param filter     过滤条件
     * @param update     更新内容
     * @param options    更新配置
     * @return 更新结果
     */
    public abstract CompletableFuture<UpdateResult> updateOneWithOptions(String collection, Bson filter, Bson update, UpdateOptions options);

    /**
     * 更新复数文档
     *
     * @param collection 集合名称
     * @param filter     过滤条件
     * @param update     更新内容
     * @return 更新结果
     */
    public abstract CompletableFuture<UpdateResult> updateMany(String collection, Bson filter, Bson update);

    /**
     * 更新复数文档, 带有配置信息
     *
     * @param collection 集合名称
     * @param filter     过滤条件
     * @param update     更新内容
     * @param options    更新配置
     * @return 更新结果
     */
    public abstract CompletableFuture<UpdateResult> updateManyWithOptions(String collection, Bson filter, Bson update, UpdateOptions options);

    /**
     * 查询并更新一个文档
     *
     * @param collection 集合名称
     * @param filter     过滤条件
     * @param update     更新内容
     * @param options    选项
     * @return 查询并修改返回结果
     */
    public abstract CompletableFuture<Document> findOneAndModify(String collection, Bson filter, Bson update, FindOneAndUpdateOptions options);

    /**
     * 删除一个文档
     *
     * @param collection 集合名称
     * @param filter     过滤条件
     * @return 删除结果
     */
    public abstract CompletableFuture<DeleteResult> deleteOne(String collection, Bson filter);

    /**
     * 删除一个文档, 带有配置信息
     *
     * @param collection 集合名称
     * @param filter     过滤条件
     * @return 删除结果
     */
    public abstract CompletableFuture<DeleteResult> deleteOneWithOptions(String collection, Bson filter, DeleteOptions options);

    /**
     * 删除多个文档
     *
     * @param collection 集合名称
     * @param filter     过滤条件
     * @return 删除结果
     */
    public abstract CompletableFuture<DeleteResult> deleteMany(String collection, Bson filter);

    /**
     * 删除多个文档, 带有配置信息
     *
     * @param collection 集合名称
     * @param filter     过滤条件
     * @return 删除结果
     */
    public abstract CompletableFuture<DeleteResult> deleteManyWithOptions(String collection, Bson filter, DeleteOptions options);

    /**
     * 聚合操作
     *
     * @param collection 集合名称
     * @param list       聚合命令
     * @return 聚合结果
     */
    public abstract CompletableFuture<List<Document>> aggregate(String collection, List<Bson> list);

    /**
     * 运行mongodb命令
     *
     * @param command 命令
     * @return 命令执行结果
     */
    public abstract CompletableFuture<Document> runCommand(Bson command);

    /**
     * 获取集合对象
     *
     * @param collection 集合名称
     * @return 集合
     */
    public abstract MongoCollection<Document> getCollection(String collection);

    /**
     * 获取数据库对象
     *
     * @return database
     */
    public abstract MongoDatabase getDatabase();

    /**
     * 获取mongodb 客户端对象
     *
     * @return mongodb客户端
     */
    public abstract MongoClient getClient();
}
