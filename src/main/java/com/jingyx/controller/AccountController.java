package com.jingyx.controller;

import com.github.pagehelper.PageInfo;
import com.jingyx.entity.Account;
import com.jingyx.service.IAccountService;
import com.jingyx.utils.ReturnMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("用户账单api")
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

	@Autowired
	IAccountService accountService;

	@PostMapping("add")
	@ApiOperation(value="新增账单")
	public ReturnMsg addAccount(@RequestBody Account account){
		accountService.addAccount(account);
		log.info("新增账单成功");
		return new ReturnMsg(200, "新增账单成功", account);
	}

	@PostMapping("update")
	@ApiOperation(value="修改账单")
	public ReturnMsg updateAccount(@RequestBody Account account){
		int i = accountService.updateAccount(account);
		return new ReturnMsg(200, "修改账单成功", account);
	}

	@PostMapping("delete")
	@ApiOperation(value="删除账单")
	public ReturnMsg deleteAccount(@RequestParam("id") Integer id){
		int i = accountService.deleteAccount(id);
		return new ReturnMsg(200, "删除账单成功");
	}

	@PostMapping("deleteBatch")
	@ApiOperation(value="批量删除账单")
	public ReturnMsg deleteBatch(@RequestParam("ids") List<Integer> ids){
		int i = accountService.deleteBatchAccount(ids);
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

}
