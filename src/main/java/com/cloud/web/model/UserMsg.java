package com.cloud.web.model;

import java.util.Date;

public class UserMsg {
    private Long id;

    private Long groupId;

    private Long groupMsgId;

    private Long sendMeiid;

    private Long recMeiid;

    private Date sendtime;

    private Integer status;

    private String msgContent;

    private Integer msgType;

    private String deviceId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupMsgId() {
        return groupMsgId;
    }

    public void setGroupMsgId(Long groupMsgId) {
        this.groupMsgId = groupMsgId;
    }

    public Long getSendMeiid() {
        return sendMeiid;
    }

    public void setSendMeiid(Long sendMeiid) {
        this.sendMeiid = sendMeiid;
    }

    public Long getRecMeiid() {
        return recMeiid;
    }

    public void setRecMeiid(Long recMeiid) {
        this.recMeiid = recMeiid;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}