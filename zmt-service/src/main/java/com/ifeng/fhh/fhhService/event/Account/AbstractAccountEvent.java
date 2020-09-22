package com.ifeng.fhh.fhhService.event.Account;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-15
 *
 * 账号上下线：online,(updateUserId,updateTime,lastReason)
 * 账号来源：dataSource,(updateUserId,updateTime,notes)
 * 媒体类型：weMediaType(updateUserId,updateTime,notes)
 * 版权：fhhCopyright,(updateUserId,updateTime,notes)
 * 禁言：silenceBeginTime,silenceEndTime,silenceOperator,silenceOperateTime,lastReason
 * 行为控制：behavioralControl,(updateUserId,updateTime,notes)
 * 优质：highQuality,(updateUserId,updateTime,notes)
 *
 */
public abstract class AbstractAccountEvent {

    private final String topic;

    protected AbstractAccountEvent(String topic) {
        this.topic = topic;
    }


    public abstract String detail();

    public String getTopic() {
        return topic;
    }
}
