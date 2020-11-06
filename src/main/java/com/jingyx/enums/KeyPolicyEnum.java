package com.jingyx.enums;

/**
 * TODO
 * 策略类型枚举类
 * @author Jingyx
 * @version 1.0
 * @date 2020/11/6 15:48
 */
public enum KeyPolicyEnum {

	COMMON((byte) 1, "基础配置"),
	GENERATE((byte)2, "密钥生成"),
	SEND((byte)3, "密钥分发");

	private byte value;
	private String name;

	KeyPolicyEnum(byte value, String name) {
		this.value = value;
		this.name = name;
	}

	public byte getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static String getNameByValue(byte value) {
		String name = null;
		KeyPolicyEnum[] values = KeyPolicyEnum.values();
		for (KeyPolicyEnum keyPolicyEnum : values) {
			if (value == keyPolicyEnum.getValue()) {
				name = keyPolicyEnum.getName();
			}
		}
		return name;
	}

}
