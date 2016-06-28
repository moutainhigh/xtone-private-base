package com.huaxuan.heart.login;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.huaxuan.heart.utils.Debug2;
import com.huaxuan.heart.utils.HttpUtils;
import com.huaxuan.heart.utils.IHttpResult;
import com.huaxuan.heart.utils.MD5;
import com.huaxuan.heart.utils.UrlUtils;
import com.huaxuan.heart.utils.UserInfoUtils;
import com.youka.youkaxiaoxiao.R;

public class RegistDialog extends BaseDialog {

	private IAccount account;
	
	private EditText registName,registEmail,registPwd;
	

	public RegistDialog(Context context, IAccount account) {
		super(context);
		this.account = account;
	}

	@Override
	protected void onCreate() {
		super.setContentView(R.layout.dialog_regist);

		initView();
		initOnclick();
	}

	private void initView() {
          registName = (EditText) findViewById(R.id.regist_name_edit);
          registEmail = (EditText) findViewById(R.id.regist_email_edit);
          registPwd = (EditText) findViewById(R.id.regist_pwd_edit);
	}

	
	
	private boolean checkRegist() {
		if(TextUtils.isEmpty(registName.getText())){
			registName.setError("用户名不能为空");
			return false;
		}
		
		if(TextUtils.isEmpty(registPwd.getText())){
			registPwd.setError("密码不能为空");
			return false;
		}
		if(TextUtils.isEmpty(registEmail.getText())){
			registEmail.setError("邮箱不能为空");
			return false;
		}
		
		if(!checkEmail(registEmail.getText()+"")){
			registEmail.setError("邮箱格式不正确");
			return false;
		}
		
		registName.setError(null);
		registPwd.setError(null);
		registEmail.setError(null);
		
		return true;
	}
	
	/**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
                String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                Pattern regex = Pattern.compile(check);
                Matcher matcher = regex.matcher(email);
                flag = matcher.matches();
            }catch(Exception e){
                flag = false;
            }
        return flag;
    	
    }
	
	
	private void initOnclick() {
		findViewById(R.id.regist_btn).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						
						if(!checkRegist()){
							return ;
						}
						final String pwd = MD5.md5Digest(registPwd.getText()+"");
						Map<String, String> map = new HashMap<String, String>();
						map.put("email", registEmail.getText()+"");
						map.put("name", registName.getText()+"");
						map.put("pwd",pwd );
						HttpUtils.asyPost(UrlUtils.regist, map, new IHttpResult() {
							
							@Override
							public void result(Object obj) {
								if(obj==null){
									return ;
								}
								Debug2.e(getContext(), obj.toString());
								
								try {
									JSONObject object = new JSONObject(obj.toString());
									int STATUS = object.getInt("STATUS");
									if(STATUS==0){
										String DESCRIPTION = object.getString("DESCRIPTION");
										Toast.makeText(context, DESCRIPTION, Toast.LENGTH_SHORT).show();
									}else if (STATUS==1) {
										
										String UUID = object.getString("UUID");
										
										UserInfo userInfo = new UserInfo(UUID, registEmail.getText()+"", registName.getText()+"", pwd);
										UserInfoUtils.instal(context).saveUserInfo(userInfo);
										account.registSuccess(STATUS);
										dismiss();
									}
									
									
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								
							}
						});
						
						
						
						
//						UserInfo userInfo = new UserInfo("uid", "email",
//								"uname", "password");
//						account.registSuccess(userInfo);
					}

					
				});

		findViewById(R.id.regist_cancle_btn).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						LoginDialog dialog = new LoginDialog(context, account);
						dialog.show();
						dismiss();
						//account.cancle("registcancle");
					}
				});
		findViewById(R.id.cancle_btn).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						dismiss();
					}
				});
	}

}
