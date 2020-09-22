package com.ifeng.fhh.fhhService.repository.searchEngine;

import com.ifeng.fhh.fhhService.model.domain.Account;
import com.ifeng.fhh.fhhService.model.transform.PagedAccount;
import com.ifeng.fhh.fhhService.model.transform.account.AccountEsReq;

import java.util.concurrent.CompletableFuture;

/**
 * 账号搜索引擎数据访问接口
 * <p>
 * Created by licheng1 on 2017/5/3.
 */
public interface AccountSEDao {

    /**
     * 添加账号
     *
     * @param account 账号对象
     * @return void
     */
    CompletableFuture<Void> add(Account account);

    /**
     * 添加账号
     *
     * @param account 账号对象
     * @return void
     */
    CompletableFuture<Void> addV2(Account account);

    /**
     * 按权重排序查询账号（支持分页）
     *
     * @param from   开始记录
     * @param size   记录数
     * @return 分页记录
     */
    CompletableFuture<PagedAccount> findAllSortedByWeight(int from, int size);

    /**
     * 根据自媒体号名称查询有视频的用户信息
     *
     * @param weMediaName 根据自媒体号名称
     * @return 客户端账号数据展示对象
     */
    CompletableFuture<PagedAccount> findVideoAccountByWeMediaName(String weMediaName, int from, int size);

    /**
     *
     * @param accountParam
     * @return 客户端账号数据展示对象
     */
    CompletableFuture<PagedAccount> findAccountByCondition(AccountEsReq accountParam);


}
