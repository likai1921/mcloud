package com.cloud.web.model;

import java.util.Date;

public class FriendMsg {
    private Long id;

    private Long msgSendMeiId;

    private Long msgRecMeiId;

    private Integer msgType;

    private Date msgSendTime;

    private String msgText;

    private Integer msgTimeout;

    private Integer msgStatus;

    private String deviceId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMsgSendMeiId() {
        return msgSendMeiId;
    }

    public void setMsgSendMeiId(Long msgSendMeiId) {
        this.msgSendMeiId = msgSendMeiId;
    }

    public Long getMsgRecMeiId() {
        return msgRecMeiId;
    }

    public void setMsgRecMeiId(Long msgRecMeiId) {
        this.msgRecMeiId = msgRecMeiId;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Date getMsgSendTime() {
        return msgSendTime;
    }

    public void setMsgSendTime(Date msgSendTime) {
        this.msgSendTime = msgSendTime;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText == null ? null : msgText.trim();
    }

    public Integer getMsgTimeout() {
        return msgTimeout;
    }

    public void setMsgTimeout(Integer msgTimeout) {
        this.msgTimeout = msgTimeout;
    }

    public Integer getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Integer msgStatus) {
        this.msgStatus = msgStatus;
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