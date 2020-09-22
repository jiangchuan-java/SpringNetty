package com.ifeng.fhh.fhhService.service.account.impl;

import com.alibaba.fastjson.JSON;
import com.ifeng.fhh.fhhService.event.Account.AbstractAccountEvent;
import com.ifeng.fhh.fhhService.event.Account.AccountEventHelper;
import com.ifeng.fhh.fhhService.model.domain.Account;
import com.ifeng.fhh.fhhService.model.transform.PagedAccount;
import com.ifeng.fhh.fhhService.model.transform.account.AccountEsReq;
import com.ifeng.fhh.fhhService.repository.database.AccountDBDao;
import com.ifeng.fhh.fhhService.repository.searchEngine.AccountSEDao;
import com.ifeng.fhh.fhhService.service.account.AccountService;
import com.ifeng.fhh.zmt.tools.EmptyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-14
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountScoreRecordServiceImpl.class);

    @Autowired
    private AccountSEDao accountSEDao;

    @Autowired
    private AccountDBDao accountDBDao;

    @Override
    public CompletableFuture<PagedAccount> findAccountInfoFromEs(AccountEsReq accountEsParam) {
        CompletableFuture<PagedAccount> listFuture = new CompletableFuture<>();

        try {
            if (EmptyUtils.isEmpty(accountEsParam))
                throw new Exception("find account from es, param cannot be empty");

            accountSEDao.findAccountByCondition(accountEsParam)
                    .whenComplete((pagedAccount, throwable) -> {
                        if(throwable!=null){
                            listFuture.completeExceptionally(throwable);
                        } else {
                            listFuture.complete(pagedAccount);
                        }
                    });
        } catch (Exception e) {
            LOGGER.error("findAccountInfoFromEs accountEsParam:{}, find account info from es error:{}",accountEsParam == null ? null : JSON.toJSONString(accountEsParam), e);
            listFuture.completeExceptionally(e);
        }

        return listFuture;
    }

    @Override
    public CompletableFuture<Long> saveAccount(Account updateAccount) {
        CompletableFuture<Long> finalFuture = new CompletableFuture<>();
        try {
            if (EmptyUtils.isEmpty(updateAccount))
                throw new Exception("save account, param cannot be empty");

            CompletableFuture<Boolean> nameFuture = new CompletableFuture<>();
            CompletableFuture<Void> aliasFuture = new CompletableFuture<>();
            String weMediaName = updateAccount.getWeMediaName();
            String accountAlias = updateAccount.getAccountAlias();

            if (StringUtils.isNotBlank(weMediaName)) {
                accountDBDao.findOnlineListByMediaName(weMediaName).whenComplete((nameRes, throwable) -> {
                    if(throwable != null){
                        nameFuture.completeExceptionally(throwable);
                    } else {
                        if (nameRes != null && nameRes.size() > 0) {
                            nameFuture.completeExceptionally(new Throwable("账号名称重复"));
                        } else {
                            nameFuture.complete(true);
                        }
                    }
                });
            } else {
                nameFuture.complete(true);
            }

            if (StringUtils.isNotBlank(accountAlias)) {
                accountDBDao.findByAccountAlias(accountAlias).whenComplete((aliasRes, throwable) -> {
                    if(throwable != null){
                        aliasFuture.completeExceptionally(throwable);
                    } else {
                        if (aliasRes != null) {
                            LOGGER.error("eAccountId:{}, alias:{} account alias repeat",aliasRes.geteAccountId(), aliasRes.getAccountAlias());
                            aliasFuture.completeExceptionally(new Throwable("账号别名重复"));
                        } else {
                            aliasFuture.complete(null);
                        }
                    }
                });
            } else {
                aliasFuture.complete(null);
            }

            CompletableFuture.allOf(nameFuture, aliasFuture).whenComplete((aVoid, throwable) -> {
                long eAccountId = updateAccount.geteAccountId();
                accountDBDao.findByeAccountId(eAccountId).whenComplete((oldAccount, findThrowable)->{
                    if(findThrowable != null){
                        finalFuture.completeExceptionally(findThrowable);
                    } else {
                        try {
                            List<AbstractAccountEvent> eventList = AccountEventHelper.parser(updateAccount, oldAccount);
                            accountDBDao.update(oldAccount.get_id(), this.buildAccount(oldAccount, updateAccount)).whenComplete((updateLong, updateThrowable)->{
                                if(updateThrowable != null){
                                    finalFuture.completeExceptionally(updateThrowable);
                                } else {
                                    finalFuture.complete(updateLong);
                                    // TODO: 20-9-17 事件分发器
                                }
                            });
                        } catch (Exception e) {
                            finalFuture.completeExceptionally(e);
                        }
                    }
                });
            });


        }catch (Exception e){
            finalFuture.completeExceptionally(e);
        }
        return finalFuture;
    }

    @Override
    public CompletableFuture<List<Account>> batchFindByeAccountId(List<Long> eAccountIds) {
        CompletableFuture<List<Account>> accountFuture = new CompletableFuture<>();

        try {
            if (EmptyUtils.isEmpty(eAccountIds))
                throw new Exception("batch find account  by eAccountId, eAccountId cannot be empty");

            Objects.requireNonNull(accountDBDao, "account dbDao entity cannot be null");

            accountDBDao.batchFindByeAccountId(eAccountIds)
                    .whenComplete((list,throwable)->{
                        if(throwable != null){
                            accountFuture.completeExceptionally(throwable);
                        } else {
                            accountFuture.complete(list);
                        }
                    });
        } catch (Exception e) {
            LOGGER.error("batchFindByeAccountId eAccountId:{}, batch find account  by eAccountId error",eAccountIds, e);
            accountFuture.completeExceptionally(e);
        }

        return accountFuture;
    }

    private Account buildAccount(Account oldAccount, Account newAccount) {
        String beCon = oldAccount.getBehavioralControl();
        String beConNew = newAccount.getBehavioralControl();
        if(StringUtils.isNotBlank(beCon) && StringUtils.isNotBlank(beConNew)) {
            Map<String,Object> beConJson = JSON.parseObject(beCon,Map.class);
            Map<String,Object> beConJsonNew = JSON.parseObject(beConNew,Map.class);
            for (String s : beConJsonNew.keySet()) {
                beConJson.put(s, beConJsonNew.get(s));
            }
            newAccount.setBehavioralControl(JSON.toJSONString(beConJson));
        }
        // 备注长度小于500追加
        String notes = oldAccount.getNotes();
        String newNote = newAccount.getNotes();
        if(StringUtils.isNotBlank(notes) && StringUtils.isNotBlank(newNote) && notes.length() < 500) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(notes).append(newNote);
            newAccount.setNotes(buffer.toString());
        }
        return newAccount;
    }
}
