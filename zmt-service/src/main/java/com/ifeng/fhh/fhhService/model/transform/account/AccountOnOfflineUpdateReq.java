package com.ifeng.fhh.fhhService.model.transform.account;

import java.util.Date;

/**
 * @Auther: zhunn
 * @Date: 2020/9/7 11:18
 * @Description:
 */
public class AccountOnOfflineUpdateReq {

    //自媒体id，更新时必传
    private Long eAccountId;

    //上下线
    private Integer online;

    // 最后下线/禁言理由
    private String lastReason;

    // 更新人
    private String updateUserId;

    // 更新时间
    private Date updateTime;

    public Long geteAccountId() {
        return eAccountId;
    }

    public void seteAccountId(Long eAccountId) {
        this.eAccountId = eAccountId;
    }

    public String getLastReason() {
        return lastReason;
    }

    public void setLastReason(String lastReason) {
        this.lastReason = lastReason;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }
}
