package com.jingyx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingyx.dao.SystemLogMapper;
import com.jingyx.entity.SystemLog;
import com.jingyx.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 *
 * @author Jingyx
 * @version 1.0
 * @date 2020/11/6 16:27
 */
@Service
public class SystemLogServiceImpl implements ISystemLogService {

	@Autowired
	private SystemLogMapper systemLogMapper;

	@Transactional
	public int add(SystemLog systemLog) {
		return systemLogMapper.insertSelective(systemLog);
	}

	@Override
	public PageInfo<SystemLog> queryList(Integer pageNo, Integer pageSize,
										 String operator, String operAction,
										 String operResut, String startTime, String endTime) {
		PageHelper.startPage(pageNo, pageSize);
		List<SystemLog> logList = systemLogMapper.queryList(operator, operAction, operResut, startTime, endTime);
		PageInfo<SystemLog> systemLogPageInfo = new PageInfo<>(logList);
		return systemLogPageInfo;
	}
}
