package com.huaxuan.heart.login;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.huaxuan.heart.utils.Debug2;
import com.huaxuan.heart.utils.HttpUtils;
import com.huaxuan.heart.utils.IHttpResult;
import com.huaxuan.heart.utils.MD5;
import com.huaxuan.heart.utils.UrlUtils;
import com.huaxuan.heart.utils.UserInfoUtils;

import android.app.Activity;
import android.widget.Toast;

public class AccountUtils {

	private static AccountUtils au;

	private Activity ac;

	public static AccountUtils instances(Activity ac) {
		if (au == null) {
			au = new AccountUtils();
		}
		au.ac = ac;
		return au;
	}

	public void login(IAccount account) {
		LoginDialog dialog = new LoginDialog(ac, account);
		dialog.show();
	}
	
	//autoLogin
	public void getGift(final IAccount account) {
		UserInfo info=UserInfoUtils.instal(ac).getInfo();
		
		Map<String, String> map = new HashMap<String, String>();
		if(info!=null){
			map.put("email", info.getEmail());
		}else {
			map.put("email", null);
		}
		
		HttpUtils.asyPost(UrlUtils.activity, map, new IHttpResult() {
			
			@Override
			public void result(Object obj) {
				if(obj!=null&&!"".equals(obj)){
					Debug2.e(ac, obj.toString());
					
					try {
						JSONObject jsonObject = new JSONObject(obj.toString());
						int STATUS = jsonObject.getInt("STATUS");
						String DESCRIPTION = jsonObject.getString("DESCRIPTION");
						
						account.loginSuccess(STATUS);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				}
			}
		});
		
	}
	
	
	public void autoLogin2(final IAccount account){
		UserInfo info=UserInfoUtils.instal(ac).getInfo();
		if(info==null){
			return ;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", info.getUname());
		map.put("pwd", info.getPassword());
		HttpUtils.asyPost(UrlUtils.login, map, new IHttpResult() {
			
			@Override
			public void result(Object obj) {
				
				if(obj!=null){
					Debug2.e(ac, obj.toString());
				try {
					JSONObject object = new JSONObject(obj.toString());
					int STATUS = object.getInt("STATUS");
					if(STATUS==0){
						
					}else if (STATUS==1) {
//						String UUID = object.getString("UUID");
//						String EMAIL = object.getString("EMAIL");
//						String NAME = object.getString("NAME");
						account.loginSuccess(STATUS);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
				
			}
		});
	}
	
	

	public void regist(IAccount account) {
		RegistDialog dialog = new RegistDialog(ac, account);
		dialog.show();
	}
	
	public void logout(){
		
	}
	
	
	

}
