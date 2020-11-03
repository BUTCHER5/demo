package com.jingyx.dao;

import com.jingyx.entity.Account;

import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    int deleteBatch(List<Integer> ids);

    List<Account> getAccountPage();
}