package com.jingyx.utils;

/**
 * TODO
 * 返回对象
 * @author jingyx
 * @version 1.0
 * @date 2020/9/30 13:48
 */
public class ReturnMsg {
	// 返回码
	private Integer code;
	// 返回消息
	private String msg;
	// 返回数据
	private Object data;

	public ReturnMsg() {
	}

	public ReturnMsg(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ReturnMsg(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
