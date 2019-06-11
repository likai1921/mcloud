package com.cloud.web.model;

import java.util.Date;

public class OpenMsg {
    private Long id;

    private Long groupId;

    private Long meiid;

    private Long recMeiid;

    private String msgContent;

    private String deviceId;

    private Integer msgStatus;

    private Date createTime;

    private Integer functionId;

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

    public Long getMeiid() {
        return meiid;
    }

    public void setMeiid(Long meiid) {
        this.meiid = meiid;
    }

    public Long getRecMeiid() {
        return recMeiid;
    }

    public void setRecMeiid(Long recMeiid) {
        this.recMeiid = recMeiid;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public Integer getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Integer msgStatus) {
        this.msgStatus = msgStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }
}