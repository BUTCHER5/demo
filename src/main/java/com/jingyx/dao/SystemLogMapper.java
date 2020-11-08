package com.jingyx.dao;

import com.jingyx.entity.SystemLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    SystemLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);

    List<SystemLog> queryList(@Param("operator") String operator, @Param("operAction") String operAction,
                              @Param("operResut") String operResut,
                              @Param("startTime") String startTime, @Param("endTime") String endTime);
}