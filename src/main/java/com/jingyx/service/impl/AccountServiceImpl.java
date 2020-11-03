package com.jingyx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingyx.dao.AccountMapper;
import com.jingyx.entity.Account;
import com.jingyx.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	AccountMapper accountMapper;

	@Override
	public int addAccount(Account account) {
		return accountMapper.insertSelective(account);
	}

	@Override
	public int updateAccount(Account account) {
		return accountMapper.updateByPrimaryKeySelective(account);
	}

	@Override
	public Account queryAccount(Integer id) {
		return accountMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteAccount(Integer id) {
		return accountMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteBatchAccount(List<Integer> ids) {
		return accountMapper.deleteBatch(ids);
	}

	@Override
	public PageInfo<Account> getAccountList(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Account> userList = accountMapper.getAccountPage();
		PageInfo<Account> userPage = new PageInfo<>(userList);
		return userPage;
	}
}
