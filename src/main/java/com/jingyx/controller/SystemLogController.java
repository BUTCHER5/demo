package com.jingyx.controller;

import com.github.pagehelper.PageInfo;
import com.jingyx.service.ISystemLogService;
import com.jingyx.entity.SystemLog;
import com.jingyx.enums.ReturnCodeEnum;
import com.jingyx.utils.ReturnMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 * 日志控制器
 * @author Jingyx
 * @version 1.0
 * @date 2020/11/6 16:30
 */
@Api(tags={"日志控制器"})
@RestController
@Slf4j
@RequestMapping("logManage/log")
public class SystemLogController {

	@Autowired
	private ISystemLogService systemLogService;

	@GetMapping("queryPage")
	@ApiOperation(value="分页日志列表")
	public ReturnMsg queryPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,
                               @RequestParam(value = "operator", required = false) String operator,
                               @RequestParam(value = "operAction", required = false) String operAction,
                               @RequestParam(value = "operResut", required = false) String operResut,
                               @RequestParam(value = "startTime", required = false) String startTime,
                               @RequestParam(value = "endTime", required = false) String endTime){
		PageInfo<SystemLog> logList = systemLogService.queryPage(pageNo, pageSize, operator, operAction, operResut, startTime, endTime);
		return new ReturnMsg(ReturnCodeEnum.OK.getCode(), "查询日志信息成功", logList);
	}

	@GetMapping(value = "download")
	@ApiOperation(value="日志备份")
	public void logDownload(@RequestParam(value = "operator", required = false) String operator,
							@RequestParam(value = "operAction", required = false) String operAction,
							@RequestParam(value = "operResut", required = false) String operResut,
							@RequestParam(value = "startTime", required = false) String startTime,
							@RequestParam(value = "endTime", required = false) String endTime, HttpServletResponse response) {
		systemLogService.download(operator, operAction, operResut, startTime, endTime, response);
	}
}
