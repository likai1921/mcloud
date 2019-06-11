package com.cloud.web.model;

import java.util.Date;

public class Gorup {
    private Long id;

    private String groupName;

    private Long groupMeiId;

    private String groupSnId;

    private Integer groupRole;

    private Date groupCreatetime;

    private Integer groupUsernumber;

    private Integer groupStatus;

    private String groupPhote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Long getGroupMeiId() {
        return groupMeiId;
    }

    public void setGroupMeiId(Long groupMeiId) {
        this.groupMeiId = groupMeiId;
    }

    public String getGroupSnId() {
        return groupSnId;
    }

    public void setGroupSnId(String groupSnId) {
        this.groupSnId = groupSnId == null ? null : groupSnId.trim();
    }

    public Integer getGroupRole() {
        return groupRole;
    }

    public void setGroupRole(Integer groupRole) {
        this.groupRole = groupRole;
    }

    public Date getGroupCreatetime() {
        return groupCreatetime;
    }

    public void setGroupCreatetime(Date groupCreatetime) {
        this.groupCreatetime = groupCreatetime;
    }

    public Integer getGroupUsernumber() {
        return groupUsernumber;
    }

    public void setGroupUsernumber(Integer groupUsernumber) {
        this.groupUsernumber = groupUsernumber;
    }

    public Integer getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(Integer groupStatus) {
        this.groupStatus = groupStatus;
    }

    public String getGroupPhote() {
        return groupPhote;
    }

    public void setGroupPhote(String groupPhote) {
        this.groupPhote = groupPhote == null ? null : groupPhote.trim();
    }
}