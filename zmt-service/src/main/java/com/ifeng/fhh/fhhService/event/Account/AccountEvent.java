package com.ifeng.fhh.fhhService.event.Account;

import com.ifeng.fhh.fhhService.model.domain.Account;

import java.lang.reflect.Method;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-15
 */
public enum AccountEvent {

    ACCOUNT_ON_OFF_LINE(AccountOnOfflineEvent.class, "checkOnOffLine"),
    ACCOUNT_DATASOURCE(AccountDataSourceEvent.class, "checkDataSource"),
    ACCOUNT_COPYRIGHT(AccountCopyrightEvent.class, "checkCopyright"),
    ACCOUNT_SILENCE(AccountSilenceEvent.class, "checkSilence"),
    ACCOUNT_BEHAVIORALCONTROL(AccountBehavioralControlEvent.class, "checkBehavioralControl");

    private Class<?> tclass;

    private String checkMethod;

    AccountEvent(Class<?> tclass, String checkMethod) {
        this.tclass = tclass;
        this.checkMethod = checkMethod;
    }

    public AbstractAccountEvent checkEvent(Account updateAccount, Account oldAccount) throws Exception {
        Method method = this.tclass.getMethod(checkMethod, Account.class, Account.class);
        return (AbstractAccountEvent) method.invoke(null,updateAccount, oldAccount);
    }
}
