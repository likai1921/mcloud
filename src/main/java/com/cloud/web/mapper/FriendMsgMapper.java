package com.cloud.web.mapper;

import com.cloud.web.model.FriendMsg;

public interface FriendMsgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FriendMsg record);

    int insertSelective(FriendMsg record);

    FriendMsg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FriendMsg record);

    int updateByPrimaryKey(FriendMsg record);
}