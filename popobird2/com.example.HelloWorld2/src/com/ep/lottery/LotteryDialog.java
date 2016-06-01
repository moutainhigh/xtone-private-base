package com.ep.lottery;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.ep.lottery.bean.LotteryBean;
import com.example.HelloWorld.R;

public class LotteryDialog extends Dialog{

	private Context context;
	
	
	private TextView txt_xl,txt_code,txt_time;
	
	private LotteryBean bean;
	
	public LotteryDialog(Context context,LotteryBean bean) {
		super(context);
		this.context =context;
		this.bean = bean;
		setCancelable(false);
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(R.layout.lottery_dilaog);
		getWindow().setBackgroundDrawable(new ColorDrawable(0));
		
		initView();
		initData();
		initClick();
		
	}

	private void initData() {
		txt_xl.setText(bean.getExchangeCode());
		txt_code.setText(bean.getPasswordCode());
		txt_time.setText(bean.getExpireTime());
	}


	private void initView() {
		txt_xl = (TextView) findViewById(R.id.txt_xl);
		txt_code = (TextView) findViewById(R.id.txt_code);
		txt_time = (TextView) findViewById(R.id.txt_time);
	}

	private void initClick() {
		findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		findViewById(R.id.btn_copy).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				copyCode(bean.getExchangeCode());
				dismiss();
			}
			
		});
		
	}

	
	@SuppressLint("NewApi") 
	private void copyCode(String data) {
		ClipboardManager clipboard = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clip = ClipData.newPlainText("simple text",data);
		clipboard.setPrimaryClip(clip);
		Toast.makeText(context, "序列号拷贝成功", Toast.LENGTH_SHORT).show();
	}

	

}
