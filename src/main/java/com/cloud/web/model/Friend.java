package com.cloud.web.model;

import java.util.Date;

public class Friend {
    private Long id;

    private Long myMeiId;

    private Long myFriendMeiId;

    private String myRemark;

    private String myFriendRemark;

    private Date createTime;

    private Date updateTime;

    private Integer invalid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMyMeiId() {
        return myMeiId;
    }

    public void setMyMeiId(Long myMeiId) {
        this.myMeiId = myMeiId;
    }

    public Long getMyFriendMeiId() {
        return myFriendMeiId;
    }

    public void setMyFriendMeiId(Long myFriendMeiId) {
        this.myFriendMeiId = myFriendMeiId;
    }

    public String getMyRemark() {
        return myRemark;
    }

    public void setMyRemark(String myRemark) {
        this.myRemark = myRemark == null ? null : myRemark.trim();
    }

    public String getMyFriendRemark() {
        return myFriendRemark;
    }

    public void setMyFriendRemark(String myFriendRemark) {
        this.myFriendRemark = myFriendRemark == null ? null : myFriendRemark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getInvalid() {
        return invalid;
    }

    public void setInvalid(Integer invalid) {
        this.invalid = invalid;
    }
}