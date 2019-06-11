package com.cloud.web.mapper;

import com.cloud.web.model.UserMsg;

public interface UserMsgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserMsg record);

    int insertSelective(UserMsg record);

    UserMsg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMsg record);

    int updateByPrimaryKey(UserMsg record);
}