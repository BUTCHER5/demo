package com.jingyx.service;

import com.github.pagehelper.PageInfo;
import com.jingyx.entity.SystemLog;

import javax.servlet.http.HttpServletResponse;

public interface ISystemLogService {

	int add(SystemLog log);

	PageInfo<SystemLog> queryPage(Integer pageNo, Integer pageSize,
								  String operator, String operAction,
								  String operResut, String startTime, String endTime);

	void download(String operator, String operAction, String operResut,
				  String startTime, String endTime, HttpServletResponse response);
}
