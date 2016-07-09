package com.huaxuan.heart.utils;


import com.huaxuan.heart.login.UserInfo;

import android.content.Context;
import android.text.TextUtils;

public class UserInfoUtils {
	
	
	private String UID = "uid";
	private String UNAME = "uname";
	private String UPWD = "upwd";
	private String UEMAIL = "uemail";
	
	private static UserInfoUtils userInfoUtils;
	
	public static UserInfoUtils instal(Context context){
		if(userInfoUtils == null){
			userInfoUtils = new UserInfoUtils();
		}
		userInfoUtils.context = context;
		return userInfoUtils;
	}
	
	private Context context;
	
	
	public void saveUserInfo(String uid,String uname,String upwd,String uemail){
		PreferencesUtils.putString(context, UID,uid);
		PreferencesUtils.putString(context, UNAME,uname);
		if(!TextUtils.isEmpty(upwd))PreferencesUtils.putString(context, UPWD,upwd);
		PreferencesUtils.putString(context, UEMAIL,uemail);
	}
	
	public void saveUserInfo(UserInfo userInfo){
		PreferencesUtils.putString(context, UID,userInfo.getUid());
		PreferencesUtils.putString(context, UNAME,userInfo.getUname());
		if(!TextUtils.isEmpty(userInfo.getPassword()))PreferencesUtils.putString(context, UPWD,userInfo.getPassword());
		PreferencesUtils.putString(context, UEMAIL,userInfo.getEmail());
	}
	
	
	public  UserInfo getInfo(){
		
		String uid=PreferencesUtils.getString(context, UID,null);
		String uname=PreferencesUtils.getString(context, UNAME,null);
		String upwd=PreferencesUtils.getString(context, UPWD,null);
		String uemail=PreferencesUtils.getString(context, UEMAIL,null);
		
		UserInfo userInfo = new UserInfo(uid, uemail, uname, upwd);
		
		if(TextUtils.isEmpty(userInfo.getEmail())){
			return null;
		}
		return userInfo;
	}
	
	public void exitUserInfo(){
		PreferencesUtils.putString(context, UID,null);
		PreferencesUtils.putString(context, UNAME,null);
		PreferencesUtils.putString(context, UPWD,null);
		PreferencesUtils.putString(context, UEMAIL,null);
	}
	
	

}
