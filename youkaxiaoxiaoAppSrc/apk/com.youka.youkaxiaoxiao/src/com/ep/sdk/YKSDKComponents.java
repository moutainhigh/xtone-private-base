package com.ep.sdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.youka.sdk.YKSdk;

import android.app.Activity;
import android.widget.Toast;

/**
 * 加载有卡的sdk
 * @author zgt
 *
 */
public class YKSDKComponents {
	
	private IYKSDKComCall ykCall;
	private static YKSDKComponents yksdkComponents;
	private Activity activity;
	
	private final static String ykSdk = "com.youka.sdk.YKSdk";
	

	public static YKSDKComponents instances(Activity activity){
		if(yksdkComponents==null){
			yksdkComponents = new YKSDKComponents();
		}
		yksdkComponents.activity =activity;
		return yksdkComponents;
	}
	
	public YKSDKComponents() {
	
		
		
	}
	
	


	
	
 class ICallBackPorx implements InvocationHandler{
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		String methodName = method.getName();
//		if("getDataSuccess".equals(methodName)){
//			ykCall.getDataSuccess();
//		}
		
		if("click".equals(methodName)){
			ykCall.getDataSuccess();
		}
		
		return null;
	}
	 
 }
	
	public void activities(IYKSDKComCall yCall){
		if(checkYKSDK()==false)return;
		this.ykCall = yCall;
		try {
			Class clazz = Class.forName(ykSdk);
			Method instance = clazz.getMethod("instances", android.content.Context.class); 
			Object obj = instance.invoke(null, activity);
			Class iCallBackClass = Class.forName("com.youka.sdk.ICallBack");
			Method activities = clazz.getDeclaredMethod("activities",iCallBackClass); 
			Class[] interfaces = {iCallBackClass};
			Object object=Proxy.newProxyInstance(ICallBackPorx.class.getClassLoader(),  
	                interfaces, new ICallBackPorx());
			activities.invoke(obj,object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public void dismiss(){
		if(checkYKSDK()==false)return;
		try {
			Class clazz = Class.forName(ykSdk);
			Method instance = clazz.getMethod("instances", android.content.Context.class); 
			Object obj = instance.invoke(null, activity);
			
			Method activities = clazz.getMethod("dismiss"); 
			activities.invoke(obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 送彩票
	 * @param uid
	 * @param money
	 */
	public void sendLottery(String uid,String money){
		
		//YKSdk.instances(activity).sendLottery(uid, money);
		
		if(checkYKSDK()==false)return;
		try {
			Class clazz = Class.forName(ykSdk);
			Method instance = clazz.getMethod("instances", android.content.Context.class); 
			Object obj = instance.invoke(null, activity);
			
			Method sendLottery = clazz.getMethod("sendLottery",String.class,String.class); 
			sendLottery.invoke(obj,uid,money);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 我的彩票
	 * @param uid
	 */
	public void myLottery(String uid){
		if(checkYKSDK()==false)return;
		try {
			Class clazz = Class.forName(ykSdk);
			Method instance = clazz.getMethod("instances", android.content.Context.class); 
			Object obj = instance.invoke(null, activity);
			
			Method myLottery = clazz.getMethod("myLottery",String.class); 
			myLottery.invoke(obj,uid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

	
	/**
	 * 检查是否配置了有卡的sdk
	 * @return
	 */
	public static  boolean checkYKSDK(){
		try {
			Class.forName(ykSdk);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public static interface IYKSDKComCall{
		void getDataSuccess();
	}

}
