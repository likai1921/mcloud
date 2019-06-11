package com.cloud.web.mapper;

import com.cloud.web.model.OpenMsg;

public interface OpenMsgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OpenMsg record);

    int insertSelective(OpenMsg record);

    OpenMsg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OpenMsg record);

    int updateByPrimaryKey(OpenMsg record);
}