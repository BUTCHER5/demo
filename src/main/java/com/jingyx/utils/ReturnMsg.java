package com.jingyx.utils;

import lombok.Data;

/**
 * TODO
 * 返回对象
 * @author jingyx
 * @version 1.0
 * @date 2020/9/30 13:48
 */
@Data
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

}
