package com.jingyx.service;

import com.jingyx.entity.Account;

import java.util.List;

public interface IAccountService {

	int addAccount(Account account);

	int updateAccount(Account account);

	Account queryAccount(Integer id);

	int deleteAccount(Integer id);

	int deleteBatchAccount(List<Integer> ids);

}
