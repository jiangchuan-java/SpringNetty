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
public class AccountBehavioralControlEvent extends AbstractAccountEvent {

    private static final String TOPIC = "account_behavioralControl";
    private String detail;

    public AccountBehavioralControlEvent() {
        super(TOPIC);
    }


    public static AbstractAccountEvent checkBehavioralControl(Account updateAccount, Account oldAccount) throws Exception {
        //behavioralControl,(updateUserId,updateTime,notes)
        String behavioralControl = updateAccount.getBehavioralControl();
        if (StringUtils.isBlank(behavioralControl)) {
            return null;
        }
        String updateUserId = updateAccount.getUpdateUserId();
        Date updateTime = updateAccount.getUpdateTime();
        String notes = updateAccount.getNotes();
        if (StringUtils.isBlank(updateUserId) || updateTime == null || StringUtils.isBlank(notes)) {
            throw new Exception("checkBehavioralControl param is illegal");
        }

        AccountBehavioralControlEvent behavioralControlEvent = new AccountBehavioralControlEvent();
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("eAccountId", oldAccount.geteAccountId());

        Map<String, Object> newMap = new HashMap<>();
        newMap.put("behavioralControl", behavioralControl);
        newMap.put("updateUserId", updateUserId);
        newMap.put("updateTime", updateTime);
        newMap.put("notes", notes);
        Map<String, Object> oldMap = new HashMap<>();
        oldMap.put("behavioralControl", oldAccount.getBehavioralControl());
        oldMap.put("updateUserId", oldAccount.getUpdateUserId());
        oldMap.put("updateTime", oldAccount.getUpdateTime());
        oldMap.put("notes", oldAccount.getNotes());

        detailMap.put("newValue", newMap);
        detailMap.put("oldValue", oldMap);
        behavioralControlEvent.detail = JSON.toJSONString(detailMap);
        return behavioralControlEvent;
    }

    @Override
    public String detail() {
        return detail;
    }
}
