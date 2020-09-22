package com.ifeng.fhh.fhhService.model.transform.account;

import java.util.Date;

/**
 * @Auther: zhunn
 * @Date: 2020/9/7 11:18
 * @Description:
 */
public class AccountSilenceUpdateReq {

    //自媒体id，更新时必传
    private Long eAccountId;

    // 禁言开始时间
    private Date silenceBeginTime;

    // 禁言结束时间
    private Date silenceEndTime;

    // 禁言操作者
    private String silenceOperator;

    // 禁言操作时间
    private Date silenceOperateTime;

    // 最后下线/禁言理由
    private String lastReason;

    public Long geteAccountId() {
        return eAccountId;
    }

    public void seteAccountId(Long eAccountId) {
        this.eAccountId = eAccountId;
    }

    public Date getSilenceBeginTime() {
        return silenceBeginTime;
    }

    public void setSilenceBeginTime(Date silenceBeginTime) {
        this.silenceBeginTime = silenceBeginTime;
    }

    public Date getSilenceEndTime() {
        return silenceEndTime;
    }

    public void setSilenceEndTime(Date silenceEndTime) {
        this.silenceEndTime = silenceEndTime;
    }

    public String getSilenceOperator() {
        return silenceOperator;
    }

    public void setSilenceOperator(String silenceOperator) {
        this.silenceOperator = silenceOperator;
    }

    public Date getSilenceOperateTime() {
        return silenceOperateTime;
    }

    public void setSilenceOperateTime(Date silenceOperateTime) {
        this.silenceOperateTime = silenceOperateTime;
    }

    public String getLastReason() {
        return lastReason;
    }

    public void setLastReason(String lastReason) {
        this.lastReason = lastReason;
    }
}
