package com.ifeng.fhh.fhhService.repository.database.mongodb.impl;

import com.ifeng.fhh.fhhService.model.constant.AccountConstant;
import com.ifeng.fhh.fhhService.model.domain.Account;
import com.ifeng.fhh.fhhService.model.transform.account.AccountMetaData;
import com.ifeng.fhh.fhhService.repository.database.AccountDBDao;
import com.ifeng.fhh.zmt.tools.EmptyUtils;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.inc;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-8
 */
@Repository
public class AccountDBDaoImpl extends MongodbBaseDaoImpl implements AccountDBDao {


    @Value("wemedia_account")
    private String collectionName;

    @Override
    public CompletableFuture<Account> findById(String id) {
        return null;
    }

    @Override
    public CompletableFuture<Account> findByeAccountId(Long eAccountId) {
        CompletableFuture<Account> accountFuture = new CompletableFuture();

        try {
            if (EmptyUtils.isEmpty(eAccountId))
                throw new Exception("account eAccountId cannot be empty");

            Bson filter = eq("eAccountId", eAccountId);
            mongoTemplate.findOne(this.collectionName, filter).whenComplete((document, throwable) -> {
                if(throwable != null){
                    accountFuture.completeExceptionally(throwable);
                } else {
                    try {
                        Account account = null;
                        if (Objects.nonNull(document)) {
                            account = deserialize(document, Account.class);
                        }
                        accountFuture.complete(account);
                    } catch (Exception e) {
                        accountFuture.completeExceptionally(e);
                    }
                }
            });
        } catch (Exception e) {
            accountFuture.completeExceptionally(e);
        }

        return accountFuture;
    }

    @Override
    public CompletableFuture<List<Account>> batchFindByeAccountId(List<Long> eAccountIds) {
        CompletableFuture<List<Account>> accountFuture = new CompletableFuture<>();

        try {
            if (EmptyUtils.isEmpty(eAccountIds))
                throw new Exception("batchFindByeAccountId account eAccountId cannot be empty");

            Bson filter = in("eAccountId", eAccountIds);
            mongoTemplate.find(this.collectionName, filter).whenComplete((documents, throwable) -> {
                if(throwable != null){
                    accountFuture.completeExceptionally(throwable);
                } else {
                    try {
                        List<Account> accountList = null;
                        if (Objects.nonNull(documents) && !documents.isEmpty()) {
                            accountList = deserializeList(documents, Account.class);
                        }
                        accountFuture.complete(accountList);
                    } catch (Exception e) {
                        accountFuture.completeExceptionally(e);
                    }
                }
            });
        } catch (Exception e) {
            accountFuture.completeExceptionally(e);
        }
        return accountFuture;
    }

    @Override
    public CompletableFuture<Account> findByeId(String eId) {
        return null;
    }

    @Override
    public CompletableFuture<Account> findByeIdAndName(String eId, String weMediaName) {
        return null;
    }

    @Override
    public CompletableFuture<String> insertOne(Account account) {
        return null;
    }

    @Override
    public CompletableFuture<Void> insertMany(List<Account> accountList) {
        return null;
    }

    @Override
    public CompletableFuture<Long> update(String id, Account account) {
        CompletableFuture<Long> updateFuture = new CompletableFuture<>();

        try {
            Objects.requireNonNull(id);
            Objects.requireNonNull(account);
            Bson filter = eq("_id", objectId(id));
            Bson update = serialize(account);
            update = new Document("$set", update);
            mongoTemplate.updateOne(this.collectionName, filter, update)
                    .whenComplete((updateResult, throwable) -> {
                        if(throwable != null){
                            updateFuture.completeExceptionally(throwable);
                        } else {
                            updateFuture.complete(updateResult.getMatchedCount());
                        }
                    });
        } catch (Exception e) {
            updateFuture.completeExceptionally(e);
        }

        return updateFuture;
    }

    @Override
    public CompletableFuture<AccountMetaData> getMetaDataByeAccountId(Long eAccountId) {
        return null;
    }

    @Override
    public CompletableFuture<AccountMetaData> getMetaDataById(String id) {
        return null;
    }

    @Override
    public CompletableFuture<AccountMetaData> getMetaDataByeId(String eId) {
        return null;
    }

    @Override
    public CompletableFuture<Account> findAccountByeIdAndAccountType(String eId, Integer accountType) {
        return null;
    }

    @Override
    public CompletableFuture<List<Account>> findListByMediaName(String weMediaName) {
        return null;
    }

    @Override
    public CompletableFuture<List<Account>> findListByMediaNameV2(String weMediaName, String categoryId) {
        return null;
    }

    @Override
    public CompletableFuture<List<Account>> findOnlineListByMediaName(String weMediaName) {
        CompletableFuture<List<Account>> accountListFuture = new CompletableFuture<>();
        try {
            Objects.requireNonNull(weMediaName, "weMediaName cannot be null");
            Bson filter = and(
                    eq("weMediaName", weMediaName),
                    eq("online", AccountConstant.ONLINE_ONLINE)
            );
            mongoTemplate.find(this.collectionName, filter).whenComplete((documents, throwable) -> {
                if(throwable != null){
                    accountListFuture.completeExceptionally(throwable);
                } else {
                    try {
                        List<Account> accountList = null;
                        if (Objects.nonNull(documents) && !documents.isEmpty()) {
                            accountList = deserializeList(documents, Account.class);
                        }
                        accountListFuture.complete(accountList);
                    } catch (Exception e) {
                        accountListFuture.completeExceptionally(e);
                    }
                }
            });
        } catch (Exception e) {
            accountListFuture.completeExceptionally(e);
        }
        return accountListFuture;
    }

    @Override
    public CompletableFuture<Account> findByFhtId(String fhtId) {
        return null;
    }

    @Override
    public CompletableFuture<AccountMetaData> getMetaDataByFhtId(String fhtId) {
        return null;
    }

    @Override
    public CompletableFuture<List<Account>> findQualityListByCategory(String cate_id, List<String> includeFields) {
        return null;
    }

    @Override
    public CompletableFuture<Account> updateAccountScoreDouble(Long eAccountId, Double score) {
        CompletableFuture<Account> updateScoreFuture = new CompletableFuture();
        try {
            Objects.requireNonNull(eAccountId);
            Objects.requireNonNull(score);

            Bson filter = eq("eAccountId", eAccountId);
            Bson update = inc("score_double", score);
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.returnDocument(ReturnDocument.AFTER);
            mongoTemplate.findOneAndModify(this.collectionName, filter, update, options).whenComplete((document, throwable) -> {
                if(throwable != null){
                    updateScoreFuture.completeExceptionally(throwable);
                } else {
                    try {
                        Account account = null;
                        if (document != null) {
                            account = deserialize(document, Account.class);
                        }
                        updateScoreFuture.complete(account);
                    } catch (Exception e) {
                        updateScoreFuture.completeExceptionally(e);
                    }
                }

            });
        } catch (Exception e) {
            updateScoreFuture.completeExceptionally(e);
        }
        return updateScoreFuture;
    }

    @Override
    public CompletableFuture<Account> findByAccountAlias(String accountAlias) {
        CompletableFuture<Account> accountFuture = new CompletableFuture<>();

        try {
            if (EmptyUtils.isEmpty(accountAlias))
                throw new Exception("account alias cannot be empty");

            Bson filter = eq("accountAlias", accountAlias);
            mongoTemplate.findOne(this.collectionName, filter).whenComplete((document, throwable) -> {
                if(throwable != null){
                    accountFuture.completeExceptionally(throwable);
                } else {
                    try {
                        Account account = null;
                        if (Objects.nonNull(document)) {
                            account = deserialize(document, Account.class);
                        }
                        accountFuture.complete(account);
                    } catch (Exception e) {
                        accountFuture.completeExceptionally(e);
                    }
                }
            });
        } catch (Exception e) {
            accountFuture.completeExceptionally(e);
        }
        return accountFuture;
    }
}
