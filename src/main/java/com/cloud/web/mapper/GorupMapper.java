package com.cloud.web.mapper;

import com.cloud.web.model.Gorup;

public interface GorupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Gorup record);

    int insertSelective(Gorup record);

    Gorup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Gorup record);

    int updateByPrimaryKey(Gorup record);
}