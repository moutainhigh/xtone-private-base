package com.ep.sdk;

import java.util.concurrent.atomic.AtomicBoolean;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.cmnpay.api.Payment;
import com.ep.sdk.YKSDKComponents.IYKSDKComCall;
import com.epplus.publics.EPPayHelper;
import com.epplus.view.PayParams;
import com.huaxuan.heart.login.AccountUtils;
import com.huaxuan.heart.login.IAccount;
import com.huaxuan.heart.login.UserInfo;
import com.huaxuan.heart.utils.UserInfoUtils;

public class XTSDK2 {
	
	private static XTSDK2 xtsdk;
	
	private boolean isInit;
	
	private UserInfo userInfo;
	
	private Handler mHandler;
	
	private XTSDK2(){
		isInit = false;
	} 
	
	/**
	 * 获取XTSDK
	 * @return
	 */
	public static XTSDK2 getInstance(){
		if(xtsdk==null){
			xtsdk = new XTSDK2();
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
		Payment.init(ac);
		isInit = true;
		
		//如果用户登录过自动登陆
		AtomicBoolean initialized = new AtomicBoolean(false);
		if(initialized.compareAndSet(false, true)){
		   //自动登陆
			AccountUtils.instances(ac).autoLogin2(new IAccount(){
				
				@Override
				public void loginSuccess(Object obj) {
					userInfo = UserInfoUtils.instal(ac).getInfo();
					Message msg = mHandler.obtainMessage();
					msg.what = 4011;
					msg.obj =obj; 
					mHandler.sendMessage(msg);
				}
				
			});
			
		}
		
	}
	
	
	/**
	 * 用户登录
	 */
	public void login(final Activity ac){
		if(isInit){
		
			AccountUtils.instances(ac).login( new IAccount() {
			
				@Override
				public void loginSuccess(Object arg0) {
					userInfo = UserInfoUtils.instal(ac).getInfo();
					Toast.makeText(ac, "登陆成功", Toast.LENGTH_SHORT).show();
					Message msg = mHandler.obtainMessage();
					msg.what = 4012;
					msg.obj =userInfo; 
					mHandler.sendMessage(msg);
				}
				@Override
				public void registSuccess(Object obj) {
					
					userInfo = UserInfoUtils.instal(ac).getInfo();
					Toast.makeText(ac, "注册成功", Toast.LENGTH_SHORT).show();
					Message msg = mHandler.obtainMessage();
					msg.what = 4012;
					msg.obj =userInfo; 
					mHandler.sendMessage(msg);
					
				}
				
		});
		}
	}
	
	
	
	
	
	/**
	 * 支付 
	 */
	public boolean pay(final Activity ac,final PayParams params){
		
		if(!ac.isFinishing()){
			if(userInfo!=null&&!TextUtils.isEmpty(userInfo.getUid())&&isInit){
				if(YKSDKComponents.checkYKSDK()){
					
					YKSDKComponents.instances(ac).activities(new IYKSDKComCall() {
						
						@Override
						public void getDataSuccess() {
							YKSDKComponents.instances(ac).dismiss();
							params.setUid(userInfo.getUid());
							EPPayHelper.getInstance(ac).pay(params);
						}
					});
					
				}else {
					params.setUid(userInfo.getUid());
					EPPayHelper.getInstance(ac).pay(params);
				}
				
				
				
				return true;
			}else {
				return false;
			}
		}
		return false;
		
	}
	
	/**
	 * 登出
	 */
	public void logout(final Activity ac){
		userInfo = null;
		UserInfoUtils.instal(ac).exitUserInfo();
		AccountUtils.instances(ac).logout();
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

	public UserInfo getUserInfo() {
		return userInfo;
	}
	
	
	

}
