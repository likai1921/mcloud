package com.cloud.web.mapper;

import com.cloud.web.model.GroupMsg;

public interface GroupMsgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GroupMsg record);

    int insertSelective(GroupMsg record);

    GroupMsg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupMsg record);

    int updateByPrimaryKey(GroupMsg record);
}