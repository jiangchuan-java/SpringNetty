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
public class AccountSilenceEvent extends AbstractAccountEvent {

    private static final String TOPIC = "account_silence";
    private String detail;

    public AccountSilenceEvent() {
        super(TOPIC);
    }


    public static AbstractAccountEvent checkSilence(Account updateAccount, Account oldAccount) throws Exception {
        //silenceBeginTime,silenceEndTime,silenceOperator,silenceOperateTime,lastReason
        Date silenceBeginTime = updateAccount.getSilenceBeginTime();
        Date silenceEndTime = updateAccount.getSilenceEndTime();
        String silenceOperator = updateAccount.getSilenceOperator();
        Date silenceOperateTime = updateAccount.getSilenceOperateTime();
        if (silenceBeginTime == null && silenceEndTime == null && StringUtils.isBlank(silenceOperator)
                && silenceOperateTime == null) {
            return null;
        }

        String notes = updateAccount.getNotes();
        if (silenceBeginTime == null || silenceEndTime == null || StringUtils.isBlank(silenceOperator)
                || silenceOperateTime == null || StringUtils.isBlank(notes)) {
            throw new Exception("checkSilence param is illegal");
        }

        AccountSilenceEvent silenceEvent = new AccountSilenceEvent();
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("eAccountId", oldAccount.geteAccountId());

        Map<String, Object> newMap = new HashMap<>();
        newMap.put("silenceBeginTime", silenceBeginTime);
        newMap.put("silenceEndTime", silenceEndTime);
        newMap.put("silenceOperator", silenceOperator);
        newMap.put("silenceOperateTime", silenceOperateTime);
        newMap.put("notes", notes);
        Map<String, Object> oldMap = new HashMap<>();
        oldMap.put("silenceBeginTime", oldAccount.getSilenceBeginTime());
        oldMap.put("silenceEndTime", oldAccount.getSilenceEndTime());
        oldMap.put("silenceOperator", oldAccount.getSilenceOperator());
        oldMap.put("silenceOperateTime", oldAccount.getSilenceOperateTime());
        oldMap.put("notes", oldAccount.getNotes());

        detailMap.put("newValue", newMap);
        detailMap.put("oldValue", oldMap);
        silenceEvent.detail = JSON.toJSONString(detailMap);
        return silenceEvent;
    }

    @Override
    public String detail() {
        return detail;
    }
}
