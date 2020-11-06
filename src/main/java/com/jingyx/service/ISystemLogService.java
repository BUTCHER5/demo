package com.jingyx.service;

import com.github.pagehelper.PageInfo;
import com.jingyx.entity.SystemLog;

public interface ISystemLogService {

	int add(SystemLog log);

	PageInfo<SystemLog> queryList(Integer pageNo, Integer pageSize);
}
