package com.huaxuan.heart.lottery;

import android.content.Context;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.Toast;

import com.huaxuan.heart.utils.ApkUtils;

class MyURLSpan extends URLSpan{
		
		private Context context;
		public MyURLSpan(Context context,String url){
			super(url);
			this.context = context;
		}

		@Override
		public void onClick(View widget) {
			
			String packageName = "com.youkala.zhongcaibao";
			boolean b = ApkUtils.checkBrowser(packageName, context);
			if(b){
				ApkUtils.startActivityForPackage(context, packageName);
			}else {
				ApkUtils.startWeb(context, getURL());
			}
			
			
		}
		
	}