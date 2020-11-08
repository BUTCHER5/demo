package com.jingyx.service;

import com.github.pagehelper.PageInfo;
import com.jingyx.entity.Account;

import java.util.List;

public interface IAccountService {

	int add(Account account);

	int update(Account account);

	Account queryOne(Integer id);

	int delete(Integer id);

	int deleteBatch(List<Integer> ids);

	PageInfo<Account> queryPage(Integer pageNum, Integer pageSize);

}
