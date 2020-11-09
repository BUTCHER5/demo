package com.jingyx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingyx.dao.SystemLogMapper;
import com.jingyx.entity.SystemLog;
import com.jingyx.service.ISystemLogService;
import com.jingyx.utils.ZIPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
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

	@Value("${system.logPath}")
	private String systemLogPath;
	@Value("${system.logName}")
	private String systemLogName;
	@Autowired
	private SystemLogMapper systemLogMapper;

	@Transactional
	public int add(SystemLog systemLog) {
		return systemLogMapper.insertSelective(systemLog);
	}

	@Override
	public PageInfo<SystemLog> queryPage(Integer pageNo, Integer pageSize,
										 String operator, String operAction,
										 String operResut, String startTime, String endTime) {
		PageHelper.startPage(pageNo, pageSize);
		List<SystemLog> logList = systemLogMapper.queryList(operator, operAction, operResut, startTime, endTime);
		PageInfo<SystemLog> systemLogPageInfo = new PageInfo<>(logList);
		return systemLogPageInfo;
	}

	@Override
	public void download(String operator, String operAction, String operResut,
									 String startTime, String endTime, HttpServletResponse response) {
		List<SystemLog> systemLogs = systemLogMapper.queryList(operator, operAction, operResut, startTime, endTime);
		writeSystemLog(systemLogs);
		toZIP();
		// 签名。。。
		logDownload(response);
	}

	/**
	 * 文件下载
	 * @param response
	 */
	private void logDownload(HttpServletResponse response) {
		String zipFileName= systemLogName.substring(0, systemLogName.indexOf(".")) + ".zip";
		File file = new File(systemLogPath + zipFileName);
		response.setContentType("application/force-download");
		try {
			response.addHeader("Content-Disposition", "attachment;" +
					"filename=" + new String(zipFileName.getBytes("UTF-8"), "ISO8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] buffer = new byte[1024];
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			OutputStream os = response.getOutputStream();
			int i = bis.read(buffer);
			while (i != -1) {
				os.write(buffer, 0, i);
				i = bis.read(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 压缩zip
	 */
	private void toZIP() {
		String sourceFilePath = systemLogPath + systemLogName;
		String targetFilePath = systemLogPath + systemLogName.substring(0, systemLogName.indexOf(".")) + ".zip";
		ZIPUtil.compress(sourceFilePath, targetFilePath);
	}

	/**
	 * 服务器端写日志
	 * @param systemLogs
	 */
	private void writeSystemLog(List<SystemLog> systemLogs) {
		BufferedWriter bufferedWriter = null;
		try {
			File filePath = new File(systemLogPath);
			if (!filePath.exists()) filePath.mkdirs();
			File filePathName = new File(systemLogPath + systemLogName);
			if (!filePathName.exists()) filePathName.createNewFile();
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePathName)));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
			for (SystemLog systemLog : systemLogs) {
				bufferedWriter.write(simpleDateFormat.format(systemLog.getCreateTime()) +
						" INFO " + systemLog.getOperInfo() + " 结果 " + systemLog.getOperResut());
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
