package com.cloud.web.model;

import java.util.Date;

public class FriendVerify {
    private Long id;

    private Long meiid;

    private Long friendMeiid;

    private Integer status;

    private Date createTime;

    private Integer isSend;

    private String deviceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMeiid() {
        return meiid;
    }

    public void setMeiid(Long meiid) {
        this.meiid = meiid;
    }

    public Long getFriendMeiid() {
        return friendMeiid;
    }

    public void setFriendMeiid(Long friendMeiid) {
        this.friendMeiid = friendMeiid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }
}