package com.huaxuan.heart.login;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.huaxuan.heart.utils.Debug2;
import com.huaxuan.heart.utils.HttpUtils;
import com.huaxuan.heart.utils.IHttpResult;
import com.huaxuan.heart.utils.MD5;
import com.huaxuan.heart.utils.UrlUtils;
import com.huaxuan.heart.utils.UserInfoUtils;
import com.youka.youkaxiaoxiao.R;

public class LoginDialog extends BaseDialog{

	
	public static int LOGIN = 888;
	public static int REGIST = 887;
	
	private Button loginBtn;
	
	private EditText loginName;
	private EditText loginPwd;
	
	private IAccount account;
	
	public LoginDialog(Context context,IAccount account) {
		super(context);
		this.account = account;
	}

	@Override
	protected void onCreate() {
		super.setContentView(R.layout.dialog_login);
		initView();
		initOnClick();
	}
	
	private void initView() {
		loginBtn = (Button) findViewById(R.id.login_btn);
		loginName = (EditText) findViewById(R.id.login_name_edit);
		loginPwd = (EditText) findViewById(R.id.login_paw_edit);
		
	}
	
	
	
	private boolean checkUname() {
		if(TextUtils.isEmpty(loginName.getText())){
			loginName.setError("用户名不能为空");
			return false;
		}
		
		if(TextUtils.isEmpty(loginPwd.getText())){
			loginPwd.setError("密码不能为空");
			return false;
		}
		
		loginName.setError(null);
		loginPwd.setError(null);
		
		return true;
	}

	private void initOnClick() {
		loginBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(!checkUname())
				{
					return ;
				}
				
				final String pwd=MD5.md5Digest(loginPwd.getText()+"");
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", loginName.getText()+"");
				map.put("pwd", pwd);
				HttpUtils.asyPost(UrlUtils.login, map, new IHttpResult() {
					
					@Override
					public void result(Object obj) {
						
						if(obj!=null){
							Debug2.e(getContext(), obj.toString());
						try {
							JSONObject object = new JSONObject(obj.toString());
							int STATUS = object.getInt("STATUS");
							if(STATUS==0){
								Toast.makeText(context, "登陆失败", Toast.LENGTH_SHORT).show();
							}else if (STATUS==1) {
								String UUID = object.getString("UUID");
								String EMAIL = object.getString("EMAIL");
								String NAME = object.getString("NAME");
								
								UserInfo userInfo = new UserInfo(UUID,EMAIL, NAME, pwd);
								UserInfoUtils.instal(context).saveUserInfo(userInfo);
								account.loginSuccess(userInfo);
								dismiss();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						}
						
					}
				});
				
				
			}

			
		});
		
		findViewById(R.id.regist_txt).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
				RegistDialog dialog = new RegistDialog(context, account);
				dialog.show();
			}
		});
		
		
		findViewById(R.id.cancle_btn).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();				
			}
		});
		
	}

	

}
