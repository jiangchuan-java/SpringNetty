package com.ifeng.fhh.fhhService.repository.database;

import com.ifeng.fhh.fhhService.model.domain.AccountScoreRecord;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Auther: zhunn
 * @Date: 2019/12/3 16:01
 * @Description:
 */
public interface AccountScoreRecordDBDao {

    /**
     * 插入一个账号分值操作记录表
     *
     * @param record 记录信息
     * @return void
     */
    CompletableFuture<Void> insertOne(AccountScoreRecord record);

    /**
     * 根据账号id获取账号分值记录
     * @param eAccountId
     * @return
     */
    CompletableFuture<List<AccountScoreRecord>> findRecordsByAccountId(Long eAccountId);
}
