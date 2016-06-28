package com.huaxuan.heart.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Debug2 {
	
	public static boolean isDebug = false;
	
	public static String TAG = "zgt"; 
	
	public static void e(Context c,String msg){
		if(isDebug){
			Log.e(TAG, msg);
			Toast.makeText(c, msg, Toast.LENGTH_SHORT).show();
		}
	}

}
