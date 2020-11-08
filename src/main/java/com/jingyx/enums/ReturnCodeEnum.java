package com.jingyx.enums;

public enum ReturnCodeEnum {

	OK(0, "执行成功"),
	ERROR(-1, "执行失败"),
	CLIENT_ERROR(400, "客户端出错了"),
	SERVER_ERROR(500, "服务器出错了");

	private Integer code;
	private String codeMsg;

	ReturnCodeEnum(Integer code, String codeMsg) {
		this.code = code;
		this.codeMsg = codeMsg;
	}

	public Integer getCode() {
		return code;
	}

	public String getCodeMsg() {
		return codeMsg;
	}
}
