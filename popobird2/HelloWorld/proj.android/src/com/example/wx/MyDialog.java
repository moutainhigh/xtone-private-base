package com.example.wx;

import com.example.HelloWorld.R;
import com.example.aasdf.GuWei;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyDialog extends Dialog{

	
	private EditText editText;
	private Button btn,btn2;
	
	private Context context;
	
	private MyHander hander;
	
	public MyDialog(Context context,MyHander hander) {
		super(context);
		this.context = context;
		this.hander = hander;
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setCancelable(false);
		setContentView(R.layout.my_dialog);
		initView();
	}

	private void initView() {
		editText = (EditText) findViewById(R.id.my_editText1);
		btn = (Button) findViewById(R.id.my_button1);
		btn2 = (Button) findViewById(R.id.my_button2);
		
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(TextUtils.isEmpty(editText.getText())){
					Toast.makeText(context, "领取码不能为空", Toast.LENGTH_SHORT).show();
					return ;
				}
				if(editText.getText().length()!=4){
					Toast.makeText(context, "领取码请输入四位数", Toast.LENGTH_SHORT).show();
					return ;
				}
				getWXCode();
				
			}
		});
		
		
       btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hander.onFail();
				dismiss();
			}
		});
	}
	
	
	private void getWXCode(){
		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {
				String str = GuWei.getMethod(editText.getText()+"");
				return str;
			}
			
			protected void onPostExecute(String result) {
				Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
				if("请输入有效的悦换码".equals(result)){
					hander.onFail();
					dismiss();
				}else if ("领取成功".equals(result)) {
					hander.onSuc();
					dismiss();
				}
			};
			
		}.execute();
	}
	
	
	public static interface MyHander{
		public void onSuc();
		public void onFail();
	}
	

}
