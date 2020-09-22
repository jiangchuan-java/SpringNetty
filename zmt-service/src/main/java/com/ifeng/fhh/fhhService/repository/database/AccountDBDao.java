package com.ifeng.fhh.fhhService.repository.database;

import com.ifeng.fhh.fhhService.model.domain.Account;
import com.ifeng.fhh.fhhService.model.transform.account.AccountMetaData;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 账号数据访问接口
 * <p>
 * Created by penghb on 2016/12/26.
 */
public interface AccountDBDao {

    /**
     * 根据账号主键查询账号(ObjectId)
     *
     * @param id id ObjectId::hexString
     * @return 文章
     */
    CompletableFuture<Account> findById(String id);

    /**
     * 根据自媒体账号自增id查询账号
     *
     * @param eAccountId 自增id
     * @return 账号
     */
    CompletableFuture<Account> findByeAccountId(Long eAccountId);

    /**
     *  查询账号数据
     * @return 账号对象
     */
    CompletableFuture<List<Account>> batchFindByeAccountId(List<Long> eAccountIds);

    /**
     * 根据外部id查询账号
     *
     * @param eId 外部id
     * @return 账号
     */
    CompletableFuture<Account> findByeId(String eId);

    /**
     * 根据外部id和账号名称查询账号
     *
     * @param eId         外部id
     * @param weMediaName 账号名
     * @return 账号
     */
    CompletableFuture<Account> findByeIdAndName(String eId, String weMediaName);

    /**
     * 插入一个账号
     *
     * @param account 账号信息
     * @return void
     */
    CompletableFuture<String> insertOne(Account account);

    /**
     * 插入多个账号
     *
     * @param accountList 账号信息集合
     * @return void
     */
    CompletableFuture<Void> insertMany(List<Account> accountList);

    /**
     * 更新账号信息
     *
     * @param id      主键
     * @param account 账号信息
     * @return 影响记录数
     */
    CompletableFuture<Long> update(String id, Account account);

    /**
     * 根据eAccountId获取账号元数据
     *
     * @param eAccountId eAccountId
     * @return 账号元数据
     */
    CompletableFuture<AccountMetaData> getMetaDataByeAccountId(Long eAccountId);

    /**
     * 根据主键获取账号元数据
     *
     * @param id 账号主键ObjectId::hexString
     * @return 账号元数据
     */
    CompletableFuture<AccountMetaData> getMetaDataById(String id);

    /**
     * 根据外部id获取账号元数据
     *
     * @param eId 外部id
     * @return 账号元数据
     */
    CompletableFuture<AccountMetaData> getMetaDataByeId(String eId);

    /**
     * @author WD
     * 根据eId，type查询账号数据
     * @param eId 外部id
     * @param accountType 账号类型
     * @return 账号数据
     */
    CompletableFuture<Account> findAccountByeIdAndAccountType(String eId, Integer accountType);

    /**
     * @author WD
     * 根据名字查询账号数据
     * @param weMediaName 自媒体号名称
     * @return 账号数据
     */
    CompletableFuture<List<Account>> findListByMediaName(String weMediaName);

    /**
     * @author WD
     * 根据名字查询账号数据 优质账号，无版权、无凤凰通id、上线
     * @param weMediaName 自媒体号名称
     * @return 账号数据
     */
    CompletableFuture<List<Account>> findListByMediaNameV2(String weMediaName, String categoryId);

    /**
     * @author WD
     * 根据名字查询上线账号数据
     * @param weMediaName 自媒体号名称
     * @return 账号数据
     */
    CompletableFuture<List<Account>> findOnlineListByMediaName(String weMediaName);

    /**
     * 根据fhtId查询账号
     *
     * @param fhtId 凤凰通
     * @return 账号
     */
    CompletableFuture<Account> findByFhtId(String fhtId);

    /**
     * 根据凤凰通取账号元数据
     *
     * @param fhtId 凤凰通
     * @return 账号元数据
     */
    CompletableFuture<AccountMetaData> getMetaDataByFhtId(String fhtId);

    /**
     * 根据分类id查找用户列表
     *
     * @param cate_id 分类id
     * @return eAccountId列表
     */
    CompletableFuture<List<Account>> findQualityListByCategory(String cate_id, List<String> includeFields);


    CompletableFuture<Account> updateAccountScoreDouble(Long eAccountId, Double score);

    /**
     * 根据账号别名查询账号
     * @param accountAlias
     * @return
     */
    CompletableFuture<Account> findByAccountAlias(String accountAlias);
}
