package com.jingyx.dao;

import com.jingyx.entity.SystemLog;

import java.util.List;

public interface SystemLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    SystemLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);

    List<SystemLog> queryList();
}