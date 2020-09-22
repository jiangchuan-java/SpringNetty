package com.ifeng.fhh.fhhService.service.account;

import com.ifeng.fhh.fhhService.model.domain.Account;
import com.ifeng.fhh.fhhService.model.transform.PagedAccount;
import com.ifeng.fhh.fhhService.model.transform.account.AccountEsReq;

import java.util.List;
import java.util.concurrent.CompletableFuture;


/**
 * 账号业务接口
 * <p>
 * Created by licheng1 on 2016/12/30.
 */
public interface AccountService {


    /**
     *
     * @param accountEsParam
     * @return
     */
    CompletableFuture<PagedAccount> findAccountInfoFromEs(AccountEsReq accountEsParam);

    /**
     * 保存账号信息
     * @param account
     * @return
     */
    CompletableFuture<Long> saveAccount(Account account);

    /**
     *  查询账号数据
     * @return 账号对象
     */
    CompletableFuture<List<Account>> batchFindByeAccountId(List<Long> eAccountIds);

}
