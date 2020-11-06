package com.jingyx.controller;

import com.github.pagehelper.PageInfo;
import com.jingyx.annotation.SystemLogAnno;
import com.jingyx.entity.Account;
import com.jingyx.service.IAccountService;
import com.jingyx.utils.ReturnMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags={"用户账单控制器"})
@RestController
@RequestMapping("account")
@Slf4j
public class AccountController {

	@Value("${template.file}")
	private String filePath;

	@Autowired
	IAccountService accountService;

	@PostMapping("add")
	@ApiOperation(value="新增账单")
	@SystemLogAnno(module = "账单", action = "新增")
	public ReturnMsg addAccount(@RequestBody Account account){
		accountService.addAccount(account);
		return new ReturnMsg(200, "新增账单成功", account);
	}

	@PostMapping("update")
	@ApiOperation(value="修改账单")
	public ReturnMsg updateAccount(@RequestBody Account account){
		accountService.updateAccount(account);
		return new ReturnMsg(200, "修改账单成功", account);
	}

	@PostMapping("delete")
	@ApiOperation(value="删除账单")
	public ReturnMsg deleteAccount(@RequestParam("id") Integer id){
		accountService.deleteAccount(id);
		return new ReturnMsg(200, "删除账单成功");
	}

	@PostMapping("deleteBatch")
	@ApiOperation(value="批量删除账单")
	public ReturnMsg deleteBatch(@RequestParam("ids") List<Integer> ids){
		accountService.deleteBatchAccount(ids);
		return new ReturnMsg(200, "删除账单成功");
	}

	@GetMapping("queryAccount")
	@ApiOperation(value="查询账单信息")
	public ReturnMsg queryAccount(@RequestParam("id") Integer id){
		Account account = accountService.queryAccount(id);
		return new ReturnMsg(200, "查询账单信息成功", account);
	}

	@GetMapping("getAccountList")
	@ApiOperation(value="账单分页列表")
	public ReturnMsg getAccountList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize){
		PageInfo<Account> accountList = accountService.getAccountList(pageNum, pageSize);
		return new ReturnMsg(200, "查询账单信息成功", accountList);
	}

	@PostMapping("upload")
	@ApiOperation(value="模板上传")
	public ReturnMsg upload(MultipartFile file) {
		//MultipartFile对象的名称必须和html中的文件上传标签的名字相同
		if (file.getSize() == 0) return new ReturnMsg(400, "不能上传空文件");
		if (!"txt".equals(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1)))
			return new ReturnMsg(400, "请上传txt格式文件");
		try {
			String filename = file.getOriginalFilename();
			File targetPath = new File(filePath);
			if (!targetPath.exists()) targetPath.mkdir();
			String pathName = filePath + filename;
			File targetFile = new File(pathName);
			file.transferTo(targetFile);
			Account account = getAccountEntityFromTxt(targetFile);
			accountService.addAccount(account);
		} catch (Exception e) {
			log.error("文件内容不符合标准，请核查");
			return new ReturnMsg(500, "文件内容不符合标准，请核查");
		}
		return new ReturnMsg(200, "上传成功");
	}

	/**
	 * 功能描述: <br>
	 * 〈把模板文件信息转成用户账单对象〉
	 * @Param: [file]
	 * @Return: com.jingyx.entity.Account
	 * @Author: Jingyx
	 * @Date: 2020/11/5 17:36
	 */
	private Account getAccountEntityFromTxt(File file) {
		// 字符输入流进行读取操作读取
		BufferedReader bufferedReader = null;
		// 每一行的内容
		String tempString;
		// 返回的密钥模板
		Account account = null;
		// 行号
		int line =1;
		try {
			// 输入字节流，FileInputStream主要用来操作文件输入流
			FileInputStream fileInputStream = new FileInputStream(file);
			// InputStreamReader是转换流，将字节流转成字符流
			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
			Map<Integer, String> map = new HashMap();
			while ((tempString = bufferedReader.readLine()) != null) {
				map.put(line, tempString);
				line++;
			}
			account = new Account();
			account.setAccountNum(map.get(1));
			account.setAccountType(Byte.parseByte(map.get(2)));
			account.setQuota(Float.parseFloat(map.get(3)));account.setCostType(Byte.parseByte(map.get(4)));
			account.setRemark(map.get(5));
			account.setCreateTime(new Date());
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return account;
	}

}
