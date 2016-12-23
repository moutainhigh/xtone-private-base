//package com.ep.lottery;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.view.View;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.account.bean.UserInfo;
//import com.example.HelloWorld.R;
//
//public class MsgDialog extends Dialog{
//	
//	private String msg=null;
//	
//	private TextView tvStuff;
//	private Button tvOk;
//	
//	private MsgCallBack callBack=null;
//	
//	public MsgDialog(Context context,String stuff,MsgCallBack msgCallBack) {
//		super(context);
//		msg=stuff;
//		this.callBack=msgCallBack;
//		setCancelable(true);
//	}
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		super.setContentView(R.layout.msg_dialog);
//		tvStuff=(TextView)findViewById(R.id.tv_stuff);
//		tvStuff.setText(msg);
//		tvOk=(Button)findViewById(R.id.tv_ok);
//		tvOk.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				callBack.clickOk();
//				dismiss();
//			}
//		});
//		getWindow().setBackgroundDrawable(new ColorDrawable(0));
//	}
//}