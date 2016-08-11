package com.unionpay.test;

import java.util.concurrent.locks.Lock;

import com.unionpay.utils.LockConfig;

public class test {
	public static void main(String[] args) {
		String url = LockConfig.unionpay_notify_url;
		System.out.println(url);
	}
}
