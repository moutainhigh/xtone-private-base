package com.example.testdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import com.epplus.publics.EPPayHelper;
import com.epplus.view.PayParams;

public class XTSDK {
	
	private static XTSDK xtsdk;
	
	private boolean isInit;
	
	
	private Handler mHandler;
	
	private XTSDK(){
		isInit = false;
	} 
	
	/**
	 * 获取XTSDK
	 * @return
	 */
	public static XTSDK getInstance(){
		if(xtsdk==null){
			xtsdk = new XTSDK();
		}
		return xtsdk;
	}
	
	/**
	 * 初始化
	 */
	public void init(final Activity ac,String payContact,Handler handler){
		this.mHandler = handler;
		EPPayHelper.getInstance(ac).initPay(true,payContact);//"4001059566"
		EPPayHelper.getInstance(ac).setPayListen(handler);
	}
	
	
	
	
	
	/**
	 * 支付 
	 */
	public void  pay(Activity ac,PayParams params){
		EPPayHelper.getInstance(ac).pay(params);
	}
	
	
	/**
	 * 退出
	 */
	public void exit(Activity ac){
		try {
			EPPayHelper.getInstance(ac).exit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 支付回调结果
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	public void payCallResult(Activity ac,int requestCode, int resultCode, Intent data){
		EPPayHelper.getInstance(ac).onActivityResult(requestCode, resultCode, data);
	}
	

}
