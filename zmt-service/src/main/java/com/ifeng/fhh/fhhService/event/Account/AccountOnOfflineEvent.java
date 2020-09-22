package com.ifeng.fhh.fhhService.event.Account;

import com.alibaba.fastjson.JSON;
import com.ifeng.fhh.fhhService.model.domain.Account;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-15
 */
public class AccountOnOfflineEvent extends AbstractAccountEvent {

    private static final String TOPIC = "account_on_off_line";
    private String detail;

    public AccountOnOfflineEvent() {
        super(TOPIC);
    }


    public static AbstractAccountEvent checkOnOffLine(Account updateAccount, Account oldAccount) throws Exception {
        //online,(updateUserId,updateTime,lastReason)
        Integer online = updateAccount.getOnline();
        if (online == null) {
            return null;
        }
        String updateUserId = updateAccount.getUpdateUserId();
        Date updateTime = updateAccount.getUpdateTime();
        String lastReason = updateAccount.getLastReason();
        if (StringUtils.isBlank(updateUserId) || updateTime == null || StringUtils.isBlank(lastReason)) {
            throw new Exception("checkOnOffLine param is illegal");
        }

        AccountOnOfflineEvent onOfflineEvent = new AccountOnOfflineEvent();
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("eAccountId", oldAccount.geteAccountId());

        Map<String, Object> newMap = new HashMap<>();
        newMap.put("online", online);
        newMap.put("updateUserId", updateUserId);
        newMap.put("updateTime", updateTime);
        newMap.put("lastReason", lastReason);
        Map<String, Object> oldMap = new HashMap<>();
        oldMap.put("online", oldAccount.getOnline());
        oldMap.put("updateUserId", oldAccount.getUpdateUserId());
        oldMap.put("updateTime", oldAccount.getUpdateTime());
        oldMap.put("lastReason", oldAccount.getLastReason());

        detailMap.put("newValue", newMap);
        detailMap.put("oldValue", oldMap);
        onOfflineEvent.detail = JSON.toJSONString(detailMap);
        return onOfflineEvent;
    }


    @Override
    public String detail() {
        return detail;
    }
}
