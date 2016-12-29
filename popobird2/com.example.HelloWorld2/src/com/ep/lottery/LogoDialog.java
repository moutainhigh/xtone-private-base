package com.ep.lottery;

import com.example.HelloWorld.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

public class LogoDialog extends Dialog{

	public LogoDialog(Context context) {
		super(context);
		setCancelable(false);
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(R.layout.logo_dialog);
		getWindow().setBackgroundDrawable(new ColorDrawable(0));
		
		
	}
	
	
	
	

}
