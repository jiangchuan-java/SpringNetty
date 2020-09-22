package com.ifeng.fhh.fhhService.service.account.impl;

import com.ifeng.fhh.fhhService.model.domain.AccountScoreRecord;
import com.ifeng.fhh.fhhService.repository.database.AccountDBDao;
import com.ifeng.fhh.fhhService.repository.database.AccountScoreRecordDBDao;
import com.ifeng.fhh.fhhService.service.account.AccountScoreRecordService;
import com.ifeng.fhh.zmt.tools.EmptyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-7
 */
@Service
public class AccountScoreRecordServiceImpl implements AccountScoreRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountScoreRecordServiceImpl.class);

    @Autowired
    private AccountScoreRecordDBDao accountScoreRecordDBDao;

    @Autowired
    private AccountDBDao accountDBDao;

    @Override
    public CompletableFuture<Boolean> insertAndExecuteScoreRecord(AccountScoreRecord newRecord) {
        CompletableFuture<Boolean> finalFuture = new CompletableFuture();
        try {
            long eAccountId = newRecord.geteAccountId();
            accountDBDao.findByeAccountId(eAccountId).whenComplete((account, findThrowable)->{
                if(findThrowable != null){
                    finalFuture.completeExceptionally(findThrowable);
                } else {
                    double currentScore = account.getScore_double();
                    double afterScore = currentScore + newRecord.getNegativeScore();
                    newRecord.setBeforeExecuteScore(currentScore);
                    newRecord.setAfterExecuteScore(afterScore); //record中是负数，所以做加法就行
                    newRecord.setOperateTime(new Date());
                    LOGGER.info("insertAndExecuteScoreRecord currentScore:{}, afterScore:{}, eAccountId:{}, update Account score",currentScore,afterScore, eAccountId);
                    accountScoreRecordDBDao.insertOne(newRecord).whenComplete((Void, insertThrowable)->{
                        if(insertThrowable != null){
                            finalFuture.completeExceptionally(insertThrowable);
                        } else {
                            accountDBDao.updateAccountScoreDouble(eAccountId, newRecord.getNegativeScore()).whenComplete((updateAccount, updateThrowable)->{
                                if(updateThrowable != null){
                                    finalFuture.completeExceptionally(updateThrowable);
                                } else {
                                    finalFuture.complete(true);
                                }
                            });
                        }

                    });
                }
            });
        }catch (Exception e){
            finalFuture.completeExceptionally(e);
        }
        return finalFuture;
    }

    @Override
    public CompletableFuture<List<AccountScoreRecord>> findAccountScoreListByeAccountId(Long eAccountId) {
        CompletableFuture<List<AccountScoreRecord>> listFuture = new CompletableFuture();

        try {
            if (EmptyUtils.isEmpty(eAccountId))
                throw new Exception("find account  by eAccountId, eAccountId cannot be empty");

            accountScoreRecordDBDao.findRecordsByAccountId(eAccountId)
                    .whenComplete((list,throwable)->{
                        if(throwable != null) {
                            listFuture.completeExceptionally(throwable);
                        } else {
                            listFuture.complete(list);
                        }
                    });
        } catch (Exception e) {
            LOGGER.error("findAccountScoreListByeAccountId eAccountId:{}, find account score list by eAccountId error:{}", eAccountId, e);
            listFuture.completeExceptionally(e);
        }

        return listFuture;
    }

    public AccountScoreRecordDBDao getAccountScoreRecordDBDao() {
        return accountScoreRecordDBDao;
    }

    public void setAccountScoreRecordDBDao(AccountScoreRecordDBDao accountScoreRecordDBDao) {
        this.accountScoreRecordDBDao = accountScoreRecordDBDao;
    }

    public AccountDBDao getAccountDBDao() {
        return accountDBDao;
    }

    public void setAccountDBDao(AccountDBDao accountDBDao) {
        this.accountDBDao = accountDBDao;
    }
}
