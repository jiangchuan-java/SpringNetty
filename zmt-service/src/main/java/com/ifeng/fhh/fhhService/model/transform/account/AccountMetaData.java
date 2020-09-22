package com.ifeng.fhh.fhhService.model.transform.account;

/**
 * 账号元数据
 *
 * Created by licheng1 on 2016/12/29.
 */
public class AccountMetaData {

    /**
     * 数据库主键 ObjectId
     */
    private String id;

    /**
     * 账号自增id
     */
    private Long eAccountId;

    /**
     * 对外id
     */
    private String eId;

    /**
     * 凤凰通id
     */
    private String fhtId;

    /**
     * 账号评论状态
     */
    private Integer commentStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long geteAccountId() {
        return eAccountId;
    }

    public void seteAccountId(Long eAccountId) {
        this.eAccountId = eAccountId;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getFhtId() {
        return fhtId;
    }

    public void setFhtId(String fhtId) {
        this.fhtId = fhtId;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }
}
