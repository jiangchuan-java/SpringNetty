package com.ifeng.fhh.fhhService.model.domain;

import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DBType;
import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DatabaseType;

import java.util.Date;

/**
 * @Des: 账号评级信息实体
 * @Author: jiangchuan
 * <p>
 * @Date: 18-11-30 上午11:01
 */
public class AccountRatings {

    //主键_id
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String _id;

    //自媒体eAccountId
    private Long eAccountId;

    //自媒体名称
    private String weMediaName;

    //领域类
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String categoryId;

    //分类名称
    private String categoryName;

    //自媒体版权信息
    private Integer fhhCopyright;

    //图文等级
    private String doc_level;

    //图文分类
    private String doc_category;

    //视频等级
    private String video_level;

    //视频分类
    private String video_categor;

    //视频时效性
    private String video_timeliness;

    //算法媒体类型
    private String mediaType;

    //安全等级
    private String safeLevel;

    //评级人域账号id
    private String userId;

    //评级人姓名
    private String userName;

    private Integer isRatings;

    private Date updateTime;

    //上下线状态 同账号的上下线状态
    private Integer online;

    //评级可选项
    private String optionalRatingItem;

    //评级时间
    private Date ratingsDate;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long geteAccountId() {
        return eAccountId;
    }

    public void seteAccountId(Long eAccountId) {
        this.eAccountId = eAccountId;
    }

    public String getWeMediaName() {
        return weMediaName;
    }

    public void setWeMediaName(String weMediaName) {
        this.weMediaName = weMediaName;
    }

    public Integer getFhhCopyright() {
        return fhhCopyright;
    }

    public void setFhhCopyright(Integer fhhCopyright) {
        this.fhhCopyright = fhhCopyright;
    }

    public String getDoc_level() {
        return doc_level;
    }

    public void setDoc_level(String doc_level) {
        this.doc_level = doc_level;
    }

    public String getDoc_category() {
        return doc_category;
    }

    public void setDoc_category(String doc_category) {
        this.doc_category = doc_category;
    }

    public String getVideo_level() {
        return video_level;
    }

    public void setVideo_level(String video_level) {
        this.video_level = video_level;
    }

    public String getVideo_categor() {
        return video_categor;
    }

    public void setVideo_categor(String video_categor) {
        this.video_categor = video_categor;
    }

    public String getVideo_timeliness() {
        return video_timeliness;
    }

    public void setVideo_timeliness(String video_timeliness) {
        this.video_timeliness = video_timeliness;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getSafeLevel() {
        return safeLevel;
    }

    public void setSafeLevel(String safeLevel) {
        this.safeLevel = safeLevel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getIsRatings() {
        return isRatings;
    }

    public void setIsRatings(Integer isRatings) {
        this.isRatings = isRatings;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getOptionalRatingItem() {
        return optionalRatingItem;
    }

    public void setOptionalRatingItem(String optionalRatingItem) {
        this.optionalRatingItem = optionalRatingItem;
    }

    public Date getRatingsDate() {
        return ratingsDate;
    }

    public void setRatingsDate(Date ratingsDate) {
        this.ratingsDate = ratingsDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
