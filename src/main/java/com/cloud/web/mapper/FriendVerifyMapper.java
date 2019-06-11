package com.cloud.web.mapper;

import com.cloud.web.model.FriendVerify;

public interface FriendVerifyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FriendVerify record);

    int insertSelective(FriendVerify record);

    FriendVerify selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FriendVerify record);

    int updateByPrimaryKey(FriendVerify record);
}