package com.ifeng.fhh.fhhService.service.account;

import com.ifeng.fhh.fhhService.model.domain.AccountScoreRecord;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-8
 */
public interface AccountScoreRecordService {

    /**
     * 保存一条账号分数记录，并执行该记录
     * @param newRecord
     * @return
     */
    CompletableFuture<Boolean> insertAndExecuteScoreRecord(AccountScoreRecord newRecord);


    /**
     * 根据eAccountId获取账号分值记录表
     * @param eAccountId
     * @return
     */
    CompletableFuture<List<AccountScoreRecord>> findAccountScoreListByeAccountId(Long eAccountId);
}
