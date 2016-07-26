package com.thirdpay.utils;

public class Contents {
	public static final String successCondition = "200";
	public static final int forwarding = 1001; //状态:转发中
	public static final int forwardsuccess = 1002;//状态:转发成功
	
	/**
     * 此处使用AES-128-ECB加密模式，key需要为16位。
     */
	public static final String cKey = "6010401031024102";
}
