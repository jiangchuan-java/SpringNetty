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
public class AccountCopyrightEvent extends AbstractAccountEvent {

    private static final String TOPIC = "account_copyright";
    private String detail;

    public AccountCopyrightEvent() {
        super(TOPIC);
    }


    public static AbstractAccountEvent checkCopyright(Account updateAccount, Account oldAccount) throws Exception {
        //fhhCopyright,(updateUserId,updateTime,notes)
        Integer fhhCopyright = updateAccount.getFhhCopyright();
        if (fhhCopyright == null) {
            return null;
        }
        String updateUserId = updateAccount.getUpdateUserId();
        Date updateTime = updateAccount.getUpdateTime();
        String notes = updateAccount.getNotes();
        if (StringUtils.isBlank(updateUserId) || updateTime == null || StringUtils.isBlank(notes)) {
            throw new Exception("checkCopyright param is illegal");
        }

        AccountCopyrightEvent copyrightEvent = new AccountCopyrightEvent();
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("eAccountId", oldAccount.geteAccountId());

        Map<String, Object> newMap = new HashMap<>();
        newMap.put("fhhCopyright", fhhCopyright);
        newMap.put("updateUserId", updateUserId);
        newMap.put("updateTime", updateTime);
        newMap.put("notes", notes);
        Map<String, Object> oldMap = new HashMap<>();
        oldMap.put("fhhCopyright", oldAccount.getFhhCopyright());
        oldMap.put("updateUserId", oldAccount.getUpdateUserId());
        oldMap.put("updateTime", oldAccount.getUpdateTime());
        oldMap.put("notes", oldAccount.getNotes());

        detailMap.put("newValue", newMap);
        detailMap.put("oldValue", oldMap);
        copyrightEvent.detail = JSON.toJSONString(detailMap);
        return copyrightEvent;
    }

    @Override
    public String detail() {
        return detail;
    }
}
