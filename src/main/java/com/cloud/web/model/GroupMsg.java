package com.cloud.web.model;

import java.util.Date;

public class GroupMsg {
    private Long id;

    private Long groupId;

    private Integer groupMsgType;

    private String groupFileId;

    private Long groupMsgSendMeiId;

    private String groupMsg;

    private Date groupSendTime;

    private Integer groupMsgTimeout;

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

    public Integer getGroupMsgType() {
        return groupMsgType;
    }

    public void setGroupMsgType(Integer groupMsgType) {
        this.groupMsgType = groupMsgType;
    }

    public String getGroupFileId() {
        return groupFileId;
    }

    public void setGroupFileId(String groupFileId) {
        this.groupFileId = groupFileId == null ? null : groupFileId.trim();
    }

    public Long getGroupMsgSendMeiId() {
        return groupMsgSendMeiId;
    }

    public void setGroupMsgSendMeiId(Long groupMsgSendMeiId) {
        this.groupMsgSendMeiId = groupMsgSendMeiId;
    }

    public String getGroupMsg() {
        return groupMsg;
    }

    public void setGroupMsg(String groupMsg) {
        this.groupMsg = groupMsg == null ? null : groupMsg.trim();
    }

    public Date getGroupSendTime() {
        return groupSendTime;
    }

    public void setGroupSendTime(Date groupSendTime) {
        this.groupSendTime = groupSendTime;
    }

    public Integer getGroupMsgTimeout() {
        return groupMsgTimeout;
    }

    public void setGroupMsgTimeout(Integer groupMsgTimeout) {
        this.groupMsgTimeout = groupMsgTimeout;
    }
}