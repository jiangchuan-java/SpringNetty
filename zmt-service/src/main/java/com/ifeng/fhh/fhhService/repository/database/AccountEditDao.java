package com.ifeng.fhh.fhhService.repository.database;

import com.ifeng.fhh.fhhService.model.domain.AccountEdit;

import java.util.concurrent.CompletableFuture;

/**
 * @Auther: zhunn
 * @Date: 2019/6/19 17:24
 * @Description:
 */
public interface AccountEditDao {

    /**
     * 插入账号数据
     *
     * @param accountEdit 账号对象
     * @return 主键
     */
    CompletableFuture<String> insert(AccountEdit accountEdit);
}
