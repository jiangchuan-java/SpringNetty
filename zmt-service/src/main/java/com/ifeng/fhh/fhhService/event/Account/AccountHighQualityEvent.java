package com.ifeng.fhh.fhhService.event.Account;

import com.alibaba.fastjson.JSON;
import com.ifeng.fhh.fhhService.model.domain.Account;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhunn
 * @Date: 2020/9/16 14:08
 * @Description:
 */
public class AccountHighQualityEvent extends AbstractAccountEvent {

    private static final String TOPIC = "account_highQuality";
    private String detail;

    public AccountHighQualityEvent() {
        super(TOPIC);
    }


    public static AbstractAccountEvent checkHighQuality(Account updateAccount, Account oldAccount) throws Exception {
        //highQuality,(updateUserId,updateTime,notes)
        Integer highQuality = updateAccount.getHighQuality();
        if (highQuality == null) {
            return null;
        }
        String updateUserId = updateAccount.getUpdateUserId();
        Date updateTime = updateAccount.getUpdateTime();
        String notes = updateAccount.getNotes();
        if (StringUtils.isBlank(updateUserId) || updateTime == null || StringUtils.isBlank(notes)) {
            throw new Exception("checkHighQuality param is illegal");
        }

        AccountHighQualityEvent highQualityEvent = new AccountHighQualityEvent();
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("eAccountId", oldAccount.geteAccountId());

        Map<String, Object> newMap = new HashMap<>();
        newMap.put("highQuality", highQuality);
        newMap.put("updateUserId", updateUserId);
        newMap.put("updateTime", updateTime);
        newMap.put("notes", notes);
        Map<String, Object> oldMap = new HashMap<>();
        oldMap.put("highQuality", oldAccount.getHighQuality());
        oldMap.put("updateUserId", oldAccount.getUpdateUserId());
        oldMap.put("updateTime", oldAccount.getUpdateTime());
        oldMap.put("notes", oldAccount.getNotes());

        detailMap.put("newValue", newMap);
        detailMap.put("oldValue", oldMap);
        highQualityEvent.detail = JSON.toJSONString(detailMap);
        return highQualityEvent;
    }

    @Override
    public String detail() {
        return detail;
    }
}
