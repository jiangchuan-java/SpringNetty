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
public class AccountMediaTypeEvent extends AbstractAccountEvent {

    private static final String TOPIC = "account_mediaType";
    private String detail;

    public AccountMediaTypeEvent() {
        super(TOPIC);
    }


    public static AbstractAccountEvent checkMediaType(Account updateAccount, Account oldAccount) throws Exception {
        //weMediaType(updateUserId,updateTime,notes)
        Integer weMediaType = updateAccount.getWeMediaType();
        if (weMediaType == null) {
            return null;
        }
        String updateUserId = updateAccount.getUpdateUserId();
        Date updateTime = updateAccount.getUpdateTime();
        String notes = updateAccount.getNotes();
        if (StringUtils.isBlank(updateUserId) || updateTime == null || StringUtils.isBlank(notes)) {
            throw new Exception("checkMediaType param is illegal");
        }

        AccountMediaTypeEvent mediaTypeEvent = new AccountMediaTypeEvent();
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("eAccountId", oldAccount.geteAccountId());

        Map<String, Object> newMap = new HashMap<>();
        newMap.put("weMediaType", weMediaType);
        newMap.put("updateUserId", updateUserId);
        newMap.put("updateTime", updateTime);
        newMap.put("notes", notes);
        Map<String, Object> oldMap = new HashMap<>();
        oldMap.put("weMediaType", oldAccount.getWeMediaType());
        oldMap.put("updateUserId", oldAccount.getUpdateUserId());
        oldMap.put("updateTime", oldAccount.getUpdateTime());
        oldMap.put("notes", oldAccount.getNotes());

        detailMap.put("newValue", newMap);
        detailMap.put("oldValue", oldMap);
        mediaTypeEvent.detail = JSON.toJSONString(detailMap);
        return mediaTypeEvent;
    }

    @Override
    public String detail() {
        return detail;
    }
}
