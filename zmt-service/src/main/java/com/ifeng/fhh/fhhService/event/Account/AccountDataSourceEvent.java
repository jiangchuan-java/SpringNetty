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
public class AccountDataSourceEvent extends AbstractAccountEvent {

    private static final String TOPIC = "account_dataSource";
    private String detail;

    public AccountDataSourceEvent() {
        super(TOPIC);
    }


    public static AbstractAccountEvent checkDataSource(Account updateAccount, Account oldAccount) throws Exception {
        //dataSource,(updateUserId,updateTime,notes)
        Integer dataSource = updateAccount.getDataSource();
        if (dataSource == null) {
            return null;
        }
        String updateUserId = updateAccount.getUpdateUserId();
        Date updateTime = updateAccount.getUpdateTime();
        String notes = updateAccount.getNotes();
        if (StringUtils.isBlank(updateUserId) || updateTime == null || StringUtils.isBlank(notes)) {
            throw new Exception("checkDataSource param is illegal");
        }

        AccountDataSourceEvent dataSourceEvent = new AccountDataSourceEvent();
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("eAccountId", oldAccount.geteAccountId());

        Map<String, Object> newMap = new HashMap<>();
        newMap.put("dataSource", dataSource);
        newMap.put("updateUserId", updateUserId);
        newMap.put("updateTime", updateTime);
        newMap.put("notes", notes);
        Map<String, Object> oldMap = new HashMap<>();
        oldMap.put("dataSource", oldAccount.getDataSource());
        oldMap.put("updateUserId", oldAccount.getUpdateUserId());
        oldMap.put("updateTime", oldAccount.getUpdateTime());
        oldMap.put("notes", oldAccount.getNotes());

        detailMap.put("newValue", newMap);
        detailMap.put("oldValue", oldMap);
        dataSourceEvent.detail = JSON.toJSONString(detailMap);
        return dataSourceEvent;
    }


    @Override
    public String detail() {
        return detail;
    }
}
